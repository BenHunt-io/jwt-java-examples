package jwt.java.examples.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

// The balance sheet of the bank
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class BalanceSheet {

    private long assets;
    private long liabilities;
    private long equity;

}
