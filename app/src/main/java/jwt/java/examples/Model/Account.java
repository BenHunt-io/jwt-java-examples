package jwt.java.examples.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Account {

    // Checking / Savings
    private String name;
    private String type;
    private int balance;
}
