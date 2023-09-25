package com.dev.myservlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SuppressWarnings("serial")
public class BasicBankingDispatcherServlet extends DispatcherServlet {

	public BasicBankingDispatcherServlet() {
		super();
	}

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {

		super.doService(request, response);
	}

	public BasicBankingDispatcherServlet(WebApplicationContext webApplicationContext) {
		super(webApplicationContext);
	}
}
