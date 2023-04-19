package kodlama.io.rentacar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kodlama.io.rentacar.core.exceptions.BusinessException;

@RestControllerAdvice
@SpringBootApplication
public class RentACarApplication {
    public static void main(String[] args) {
        SpringApplication.run(RentACarApplication.class, args);
    }

    @ExceptionHandler
    public String handleBusinessException(BusinessException exception) {
        return exception.getMessage();
    }
}
