package jwt.java.examples.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository {

        private List<UserEntity> users;

        public UserRepository() {
                users = Stream.of(
                                UserEntity.builder()
                                                .username("admin_user")
                                                .password("byrd123")
                                                .roles(Stream.of(UserRole.ADMIN).collect(Collectors.toSet()))
                                                .build(),
                                UserEntity.builder()
                                                .username("accounting_user")
                                                .password("apple123")
                                                .roles(Stream.of(UserRole.ACCOUNTING).collect(Collectors.toSet()))
                                                .build(),
                                UserEntity.builder()
                                                .username("customer_user")
                                                .password("pizza123")
                                                .roles(Stream.of(UserRole.CUSTOMER).collect(Collectors.toSet()))
                                                .build(),
                                UserEntity.builder()
                                                .username("support_user")
                                                .password("pear123")
                                                .roles(Stream.of(UserRole.CUSTOMER_SUPPORT).collect(Collectors.toSet()))
                                                .build())
                                .collect(Collectors.toList());
        }

        public Optional<UserEntity> findUserByName(String name) {
                return users.stream()
                                .filter(user -> user.getUsername().equals(name))
                                .findFirst();
        }

}
