package kodlama.io.rentacar.core.utils.results;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionResult<T extends Exception> {
    private LocalDate timestamp;
    private String type;
    private String message;

    public ExceptionResult(Class<T> type, String message) {
        this.type = type.getSimpleName();
        this.message = message;
        this.timestamp = LocalDate.now();
    }

}
