package com.chanjet.openapi.demo.web;

import com.chanjet.openapi.demo.common.ChanjetValueResult;
import com.chanjet.openapi.demo.exception.ErrorCodeException;
import com.chanjet.openapi.demo.exception.ErrorConst;
import com.chanjet.openapi.demo.utils.TraceUtil;
import com.chanjet.openapi.sdk.java.common.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @program: developer
 * @description:
 * @author: nxw
 * @create: 2020-02-12 09:17
 **/
@RestControllerAdvice(basePackages="com.chanjet.changsha.bank.pay.controller")
public class GlobalExceptionController {
	private static final Logger Log = LoggerFactory.getLogger(GlobalExceptionController.class);
	

	
	@ExceptionHandler(value = { ErrorCodeException.class,  Exception.class})
	@ResponseBody
    ResponseEntity<ChanjetValueResult<Object>> handleUnCatcheException(HttpServletRequest request, Throwable ex) {
		if(ex instanceof ErrorCodeException) {
			return errorCodeExceptionHandler((ErrorCodeException) ex);
		}
		if (ex instanceof MethodArgumentNotValidException){
			return validExceptionHandler( (MethodArgumentNotValidException) ex);
		}
		
		return this.defaultErrorHandler(ex);
	}
	
    public ResponseEntity< ChanjetValueResult<Object>> errorCodeExceptionHandler(ErrorCodeException e)  {
    	Log.warn(e.getMessage(),e);
    	ChanjetValueResult<Object> resp  = new ChanjetValueResult<>();
		ErrorInfo error = new ErrorInfo();
		error.setCode(e.getErrorCode());
		error.setMsg(e.getMessage());
		error.setHint(e.getHint());
		resp.setError(error);
		resp.setResult(false);
		resp.setValue(TraceUtil.getTraceId());
		return ResponseEntity.ok().body(resp);
    }

    public ResponseEntity< ChanjetValueResult<Object> > validExceptionHandler(MethodArgumentNotValidException e)  {
    	Log.warn(e.getMessage(),e);
    	ChanjetValueResult<Object> resp  = new ChanjetValueResult<>();
		ErrorInfo error = new ErrorInfo();
		error.setCode(BAD_REQUEST.value());
		error.setMsg("参数有误");
		error.setHint(toStr(e));
		resp.setError(error);
		resp.setResult(false);
		resp.setValue(TraceUtil.getTraceId());
		return ResponseEntity.ok().body(resp);
    }

	private String toStr(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getField()+":"+e.getDefaultMessage())
				.reduce("",(s1,s2) -> s1+"\n"+s2);
	}

    public ResponseEntity<ChanjetValueResult<Object>> defaultErrorHandler(Throwable e) {
    	Log.error(ErrorConst.INTENAL_ERROR.getMessage(),e);

    	
    	ChanjetValueResult<Object> resp  = new ChanjetValueResult<>();
		ErrorInfo error = new ErrorInfo();
		error.setCode(ErrorConst.INTENAL_ERROR.getCode());
		error.setMsg(ErrorConst.INTENAL_ERROR.getMessage());
		error.setHint(e.getMessage());
		resp.setError(error);
		resp.setResult(false);
		resp.setValue(TraceUtil.getTraceId());
		return ResponseEntity.ok().body(resp);
    }
	
	
}
