package jwt.java.examples.Repository;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class EmployeeEntity {

    private long id;
    private String firstName;
    private String lastName;
    private EmployeeEntity manager;
    private List<EmployeeEntity> subordinates = new ArrayList<>();

}
