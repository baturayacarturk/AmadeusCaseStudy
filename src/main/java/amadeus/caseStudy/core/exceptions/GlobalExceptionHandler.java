package amadeus.caseStudy.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessProblemDetails> handleBusinessException(BusinessException ex) {
        BusinessProblemDetails problemDetails = new BusinessProblemDetails();
        problemDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        problemDetails.setType("https://example.com/baturayacarturk/business");
        problemDetails.setTitle("Business exception");
        problemDetails.setDetail(ex.getMessage());
        problemDetails.setInstance("");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetails);
    }
}
