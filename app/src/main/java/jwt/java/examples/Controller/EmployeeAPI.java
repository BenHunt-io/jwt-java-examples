package jwt.java.examples.Controller;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import jwt.java.examples.Model.EmployeePerformance;

@ApplicationScoped
@Path("/employee")
public class EmployeeAPI {

    @GET
    @Path("/performance/{id}")
    public EmployeePerformance getEmployeePerformance(@PathParam("id") long employeeId) {

        return EmployeePerformance.builder()
                .grade("A+")
                .comment("Good Job. You met your goals")
                .metGoals(true)
                .build();
    }

}
