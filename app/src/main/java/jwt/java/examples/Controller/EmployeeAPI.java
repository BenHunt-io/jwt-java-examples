package jwt.java.examples.Controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;

import io.quarkus.security.UnauthorizedException;
import jwt.java.examples.Controller.Common.JwtUtil;
import jwt.java.examples.Model.EmployeePerformance;
import jwt.java.examples.Repository.EmployeeEntity;
import jwt.java.examples.Repository.EmployeeRepository;
import jwt.java.examples.Security.Vault;

@ApplicationScoped
@Path("/employee")
public class EmployeeAPI {

    @Inject
    private Vault vault;

    @Inject
    private EmployeeRepository employeeRepo;

    @GET
    @Path("/performance/{id}")
    public EmployeePerformance getEmployeePerformance(@HeaderParam("authorization") String authHeader,
            @PathParam("id") long employeeId) {

        if (!isAuthenticated(authHeader, employeeId)) {
            throw new UnauthorizedException("Not Authorized");
        }

        return EmployeePerformance.builder()
                .grade("A+")
                .comment("Good Job. You met your goals")
                .metGoals(true)
                .build();
    }

    private boolean isAuthenticated(String authHeader, long employeeId) {

        String jsonWebToken = JwtUtil.parseJWT(authHeader);

        Algorithm algo = Algorithm.RSA256(vault.getPublicKey(), vault.getPrivateKey());

        // Need to add functionality to check to see if it's the session user's account
        JWTVerifier verifier = JWT.require(algo)
                .withIssuer("BankOfUSA")
                .withClaim("employeeId", (claim, jwt) -> {

                    EmployeeEntity employee = employeeRepo.findEmployeeById(claim.asLong());

                    if (employee != null && employee.getId() == employeeId || employee.getSubordinates().stream()
                            .anyMatch(e -> e.getId() == employeeId)) {
                        return true;
                    }

                    return false;
                })
                .build();

        try {
            verifier.verify(jsonWebToken);
        } catch (JWTVerificationException exception) {
            return false;
        }

        return true;
    }

}
