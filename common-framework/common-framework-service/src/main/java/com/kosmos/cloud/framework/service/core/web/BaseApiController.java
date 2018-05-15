package com.kosmos.cloud.framework.service.core.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.kosmos.cloud.common.exception.CommonException;
import com.kosmos.cloud.common.exception.UnauthorizedException;
import com.kosmos.cloud.common.utils.result.Result;
import com.kosmos.cloud.common.utils.result.ResultBuilder;


public class BaseApiController {
	private static final Logger log = LoggerFactory.getLogger(BaseApiController.class);
	@Autowired  
	protected  HttpServletRequest request; 

    /**
     * 用于处理通用异常
     * @param e
     * @return
     */
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public Result<String> exception(Exception e) {
        log.warn("got a Exception",e);

        String status = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
        String message = e.getMessage();

        if (e instanceof CommonException){
            status = ((CommonException) e).getCode();
        }

        return new ResultBuilder<String>().code(status).message(message).build();
    }

    /**
     * 
     */
    @ExceptionHandler({ UnauthorizedException.class })
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Result<String> exception(UnauthorizedException e) {
        log.warn("got a UnauthorizedException",e);

        String message = e.getMessage();
        String status = e.getCode();
        Result<String> s = new ResultBuilder<String>().code(status).message(message).build();
        return s;
    }
    
    protected <T> Result<T> buildSuccess(T t) {
		return new Result<T>("0", null, t);
	}

	protected <T> Result<T> buildError(String message) {
		return buildError("500", message);
	}

	protected <T> Result<T> buildError(String status, String message) {
		return new Result<T>(status, message, null);
	}

}