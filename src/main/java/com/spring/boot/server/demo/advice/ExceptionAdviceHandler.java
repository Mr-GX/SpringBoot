package com.spring.boot.server.demo.advice;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@ControllerAdvice
class ExceptionAdviceHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView("error");
        Logger.getLogger("test").info("logger test!!!");
        mav.addObject("e", e);
        mav.addObject("url", req.getRequestURL());
        return mav;
    }
}
