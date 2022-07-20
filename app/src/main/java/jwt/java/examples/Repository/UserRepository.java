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
                users = List.of(
                                UserEntity.builder()
                                                .username("admin_user_john")
                                                .password("byrd123")
                                                .employeeId(1l)
                                                .roles(Stream.of(UserRole.ADMIN).collect(Collectors.toSet()))
                                                .build(),
                                UserEntity.builder()
                                                .username("accounting_user_shelly")
                                                .password("apple123")
                                                .employeeId(2l)
                                                .roles(Stream.of(UserRole.ACCOUNTING).collect(Collectors.toSet()))
                                                .build(),
                                UserEntity.builder()
                                                .username("accounting_user_roger")
                                                .password("donut123")
                                                .employeeId(3l)
                                                .roles(Stream.of(UserRole.ACCOUNTING).collect(Collectors.toSet()))
                                                .build(),
                                // Not an Employee
                                UserEntity.builder()
                                                .username("customer_user")
                                                .password("pizza123")
                                                .roles(Stream.of(UserRole.CUSTOMER).collect(Collectors.toSet()))
                                                .build(),
                                UserEntity.builder()
                                                .username("support_user_bob")
                                                .password("pear123")
                                                .employeeId(4l)
                                                .roles(Stream.of(UserRole.TELLER).collect(Collectors.toSet()))
                                                .build(),
                                UserEntity.builder()
                                                .username("support_user_tim")
                                                .password("orange123")
                                                .employeeId(5l)
                                                .roles(Stream.of(UserRole.TELLER).collect(Collectors.toSet()))
                                                .build());
        }

        public Optional<UserEntity> findUserByName(String name) {
                return users.stream()
                                .filter(user -> user.getUsername().equals(name))
                                .findFirst();
        }

}
