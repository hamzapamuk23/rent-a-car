package kodlama.io.rentacar.core.configuration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kodlama.io.rentacar.core.exceptions.BusinessException;
import kodlama.io.rentacar.core.utils.results.ExceptionResult;

@RestControllerAdvice // Controller'dan gelen tüm istekleri dinler. Ardında bir hata oluşursa bu
                      // sınıftaki methodları kontrol eder. Oluşan hatayı yakalan method varsa o
                      // methodu çalıştırır.
public class RestExcepitonHandler {

    @ExceptionHandler // Oluşan hatayı yakalar. Fonksiyonda parametre olarak kontrol eder. Eğer
                      // eşleşirse bulunduğu fonksiyon çalışır.
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // 422
    public ExceptionResult<BusinessException> handleBusinessException(BusinessException exception) {
        return new ExceptionResult<BusinessException>(BusinessException.class, exception.getMessage());
    }

    /**
     * 
     * @param exception
     * @return ExceptionResult<T>
     *         Bu fonksiyon olsaydı eğer polimorfizm den dolayı tüm run time
     *         exceptionları yakalardı
     */
    // @ExceptionHandler // Oluşan hatayı yakalar. Fonksiyonda parametre olarak
    // kontrol eder. Eğer eşleşirse bulunduğu fonksiyon çalışır.
    // @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) //500
    // public ExceptionResult<RuntimeException>
    // handleBusinessException(RuntimeException exception){
    // return new ExceptionResult<RuntimeException>(RuntimeException.class,
    // exception.getMessage());
    // }
}
