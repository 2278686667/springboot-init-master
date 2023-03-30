package com.zhby.config;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.zhby.utils.entity.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.alibaba.fastjson.JSON;


@Aspect
@Component
public class ValidatorAspect {
    
    //环绕增强
    @Around(value="execution(* com.zhby.controller..*(..)) && args(..,bindingResult)")
    public Object around(ProceedingJoinPoint pj,BindingResult bindingResult) throws Throwable{
    	 MethodSignature msg = (MethodSignature)pj.getSignature();
    	 Annotation[][] parameterAnnotations= msg.getMethod().getParameterAnnotations();
    	 Object retVal;
         if (bindingResult.hasErrors()) {
             retVal = resultToList(bindingResult);
         } else {
             retVal = pj.proceed();
         }
         return retVal;
    }

	public Result resultToList (BindingResult result){
		List<FieldError>  err=result.getFieldErrors();
	    FieldError fe;
	    List<String> list=new ArrayList<String>();
	    for (int i = 0; i < err.size(); i++) {
	        fe=err.get(i);
	        list.add(fe.getDefaultMessage());//得到错误消息
	    }
		return  new Result("error", String.join(",", list), null);
	}
	
}
