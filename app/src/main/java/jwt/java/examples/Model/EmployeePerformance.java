package jwt.java.examples.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class EmployeePerformance {

    private String grade;
    private String comment;
    private boolean metGoals;

}
