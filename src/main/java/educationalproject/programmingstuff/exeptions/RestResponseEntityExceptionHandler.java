package educationalproject.programmingstuff.exeptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotEnoughItemsAtStoreException.class)
    protected ResponseEntity<Object> handle(
            NotEnoughItemsAtStoreException notEnoughItemsAtStoreException,
            WebRequest request) {
        String bodyOfResponse = notEnoughItemsAtStoreException.getMessage();
        return handleExceptionInternal(notEnoughItemsAtStoreException, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
