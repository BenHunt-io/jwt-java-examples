package jwt.java.examples.Repository;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEntity {

    private String username;
    private String password;
    private Set<UserRole> roles;

}
