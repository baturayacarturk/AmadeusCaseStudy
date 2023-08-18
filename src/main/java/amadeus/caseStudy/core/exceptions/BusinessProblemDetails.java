package amadeus.caseStudy.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessProblemDetails {
    private int status;
    private String type;
    private String title;
    private String detail;
    private String instance;

}

