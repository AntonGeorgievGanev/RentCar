package bg.rentacar.web.advice;

import bg.rentacar.exception.ObjectNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFound.class)
    public ModelAndView handleObjectNotFound(ObjectNotFound onfe) {
        ModelAndView mav = new ModelAndView("object-not-found");
        mav.addObject("message", onfe.getMessage());

        return mav;
    }
}
