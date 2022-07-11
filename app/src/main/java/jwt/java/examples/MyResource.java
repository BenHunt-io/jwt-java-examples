package jwt.java.examples;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("dashboard")
@Singleton
public class MyResource {

    private static Logger logger = Logger.getLogger(MyResource.class.getName());

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    @GET
    @Path("/jwt")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() throws Exception {

        logger.info("get it");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        RSAKeyGenParameterSpec algoParams = new RSAKeyGenParameterSpec(1024, RSAKeyGenParameterSpec.F4);
        keyPairGenerator.initialize(algoParams);

        KeyPair keyPair =  keyPairGenerator.genKeyPair();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();


        Algorithm algo = Algorithm.RSA256(this.publicKey, this.privateKey);
        String jwt = JWT.create()
            .withIssuer("Benji")
            .sign(algo);

        return jwt;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDashboardSummary(@HeaderParam("Authorization") String authorization) throws Exception {

        logger.info("dashboard summary");


        String jsonWebToken = authorization.split(" ")[1];

        Algorithm algo = Algorithm.RSA256(this.publicKey, this.privateKey);

        JWTVerifier verifier = JWT.require(algo)
            .withIssuer("Benji")
            .build();
            
        try{
            verifier.verify(jsonWebToken);
            Dashboard dashboard = Dashboard.builder()
                .revenue(1_000_000)
                .roadmapProjects(Stream.of("Project Pheonix", "Project BTC").collect(Collectors.toList()))
                .incidents(5)
                .build();

            return Response.ok(dashboard)
                .build();
        }catch(JWTVerificationException exception){
            return Response.status(401)
                .entity("Not Authorized")
                .build();
        }
    }





}
