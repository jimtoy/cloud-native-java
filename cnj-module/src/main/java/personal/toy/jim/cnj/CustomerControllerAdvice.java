package main.java.personal.toy.jim.cnj;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class CustomerControllerAdvice {

    private final MediaType vndErrorMediaType = MediaType.parseMediaType("application/vnd.error+json");


    @ExceptionHandler(CustomerNotFoundException.class)
    ResponseEntity<VndErrors> notFoundException(CustomerNotFoundException e) {
        return this.error(e, HttpStatus.NOT_FOUND, "some error");
    }

    private <E extends Exception> ResponseEntity<VndErrors> error(E error, HttpStatus status, String message) {
        System.out.println("processing exception");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(this.vndErrorMediaType);

        return new ResponseEntity<>(new VndErrors(message, "new message"), headers, status);
    }
}
