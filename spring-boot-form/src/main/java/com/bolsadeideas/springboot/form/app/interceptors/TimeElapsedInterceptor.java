package com.bolsadeideas.springboot.form.app.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("timeElapsedInterceptor")
public class TimeElapsedInterceptor implements HandlerInterceptor{
	
	private static final Logger logger = LoggerFactory.getLogger(TimeElapsedInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("post")) {
			return true;
		}
		
		if(handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			logger.info("Is a controller method: " + method.getMethod().getName());
			
		}
		
		logger.info("TimeElapsedInterceptor: preHandle() entering ..."); 
		long timeInitial = System.currentTimeMillis();
		request.setAttribute("timeInitial", timeInitial);
		
		Random random = new Random();
		Integer delay = random.nextInt(500);
		Thread.sleep(delay);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("post")) {
			return;
		}
		
		long timeFinal = System.currentTimeMillis();
		long timeInitial = (Long) request.getAttribute("timeInitial");
		long timeElapsed = timeFinal - timeInitial;
		
		if(handler instanceof HandlerMethod && modelAndView != null) {
			modelAndView.addObject("timeElapsed", timeElapsed);
		}
		
		logger.info("Time elapsed: " + timeElapsed + " mm");
		logger.info("TimeElapsedInterceptor: postHandle() coming out ...");
		
	}
	
	

}
