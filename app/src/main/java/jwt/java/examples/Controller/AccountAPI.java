package jwt.java.examples.Controller;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jwt.java.examples.Controller.Common.JwtUtil;
import jwt.java.examples.Model.Account;
import jwt.java.examples.Repository.UserRole;
import jwt.java.examples.Security.Vault;

@Path("/account")
public class AccountAPI {

    @Inject
    private Vault vault;

    private boolean isAuthenticated(String authHeader) {

        Set<String> allowedRoles = Stream.of(UserRole.ADMIN.toString(), UserRole.TELLER.toString())
                .collect(Collectors.toSet());

        String jsonWebToken = JwtUtil.parseJWT(authHeader);

        Algorithm algo = Algorithm.RSA256(vault.getPublicKey(), vault.getPrivateKey());

        // Need to add functionality to check to see if it's the session user's account
        JWTVerifier verifier = JWT.require(algo)
                .withIssuer("BankOfUSA")
                .withClaim("role", (claim, jwt) -> Stream.of(claim.asArray(String.class))
                        .anyMatch(role -> allowedRoles.contains(role.toUpperCase())))
                .build();

        try {
            verifier.verify(jsonWebToken);
        } catch (JWTVerificationException exception) {
            return false;
        }

        return true;
    }

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCheckingAccount(@HeaderParam("Authorization") String authHeader,
            @PathParam("username") String username) throws Exception {

        if (!isAuthenticated(authHeader)) {
            return Response.status(401)
                    .entity("Not Authorized")
                    .build();
        }

        return Response.ok(Account.builder()
                .name("Primary Checking Account")
                .balance(1_000)
                .type("Checking")
                .build()).build();
    }

}
