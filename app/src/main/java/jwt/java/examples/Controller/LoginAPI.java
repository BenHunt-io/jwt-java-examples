package jwt.java.examples.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import jwt.java.examples.Model.User;
import jwt.java.examples.Repository.UserEntity;
import jwt.java.examples.Repository.UserRepository;
import jwt.java.examples.Repository.UserRole;
import jwt.java.examples.Security.Vault;

@Path("/auth")
public class LoginAPI {

    @Inject
    private UserRepository userRepo;

    @Inject
    private Vault vault;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/login")
    public String login(User user) throws Exception {

        Optional<UserEntity> userEntity = userRepo.findUserByName(user.getUsername());

        if (userEntity.isPresent() && userEntity.get().getPassword().equals(user.getPassword())) {

            List<String> userRoles = userEntity.get().getRoles().stream()
                    .map(UserRole::toString)
                    .collect(Collectors.toList());

            Algorithm algo = Algorithm.RSA256(vault.getPublicKey(), vault.getPrivateKey());
            var jwtBuilder = JWT.create()
                    .withIssuer("BankOfUSA")
                    .withClaim("role", userRoles)
                    .withSubject(userEntity.get().getUsername());

            if (userEntity.get().getEmployeeId() != null) {
                jwtBuilder.withClaim("employeeId", userEntity.get().getEmployeeId());
            }

            return jwtBuilder.sign(algo);
        } else {
            return "Invalid Username or Password";
        }

    }
}
