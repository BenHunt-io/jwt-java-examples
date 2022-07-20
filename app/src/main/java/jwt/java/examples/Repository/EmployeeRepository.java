package jwt.java.examples.Repository;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepository {

    private Map<Long, EmployeeEntity> employees;

    public EmployeeRepository() {

        // Owner of Bank
        EmployeeEntity john = EmployeeEntity.builder()
                .firstName("john")
                .lastName("smith")
                .id(1l)
                .manager(null)
                .build();

        // Accountants
        EmployeeEntity shelly = EmployeeEntity.builder()
                .firstName("shelly")
                .lastName("banks")
                .id(2l)
                .manager(john)
                .build();

        EmployeeEntity roger = EmployeeEntity.builder()
                .firstName("roger")
                .lastName("racks")
                .id(3l)
                .manager(john)
                .build();

        // Customer Support
        EmployeeEntity bob = EmployeeEntity.builder()
                .firstName("bob")
                .lastName("lunk")
                .id(4l)
                .manager(roger)
                .build();

        // Customer Support (Hourly Workers)
        EmployeeEntity tim = EmployeeEntity.builder()
                .firstName("tim")
                .lastName("dove")
                .id(5l)
                .manager(shelly)
                .build();

        john.setSubordinates(List.of(shelly, roger));
        roger.setSubordinates(List.of(tim));
        shelly.setSubordinates(List.of(bob));

        employees = Map.of(
                john.getId(), john,
                shelly.getId(), shelly,
                roger.getId(), roger,
                bob.getId(), bob,
                tim.getId(), tim);
    }

    public EmployeeEntity findEmployeeById(long id) {
        return employees.get(id);
    }

}
