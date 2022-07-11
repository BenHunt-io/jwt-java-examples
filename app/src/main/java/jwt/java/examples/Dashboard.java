package jwt.java.examples;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

// Models some 'sensitive' data that we need to control access to
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Dashboard {

    private int revenue;
    private List<String> roadmapProjects;
    private int incidents;
        
}
