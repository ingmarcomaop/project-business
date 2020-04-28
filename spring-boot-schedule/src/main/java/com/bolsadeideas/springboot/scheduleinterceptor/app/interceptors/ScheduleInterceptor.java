package com.bolsadeideas.springboot.scheduleinterceptor.app.interceptors;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("schedule")
public class ScheduleInterceptor implements HandlerInterceptor {
	
	@Value("${config.schedule.open}")
	private Integer open;
	
	@Value("${config.schedule.close}")
	private Integer close;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		
		if(hour >= open && hour < close) {
			
			StringBuilder message = new StringBuilder("Welcome to client support hour, ");
			
			message.append(" we serve from ");
			message.append(open);
			message.append(" hours");
			message.append(" to");
			message.append(close);
			message.append(" hours");
			message.append(". Thanks for your visit.");
			
			request.setAttribute("message", message.toString());
			
			return true;
			
		}
		
		response.sendRedirect(request.getContextPath().concat("/close"));
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String message = (String) request.getAttribute("message");
		if(modelAndView != null && handler instanceof HandlerMethod) {
			modelAndView.addObject("schedule", message);
		}
		
	}

}
