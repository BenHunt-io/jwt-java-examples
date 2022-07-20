package jwt.java.examples.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jwt.java.examples.Model.BalanceSheet;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("bank")
public class BankAPI {

    @GET
    @Path("/balancesheet")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBalanceSheet() {

        return Response.ok(
                BalanceSheet.builder()
                        .assets(100_000)
                        .liabilities(70_000)
                        .equity(30_000)
                        .build())
                .build();

    }

}
