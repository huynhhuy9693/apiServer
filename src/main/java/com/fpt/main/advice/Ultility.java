package com.fpt.main.advice;



import javax.servlet.http.HttpServletRequest;
public class Ultility {
	
	public static String getSiteUrl(HttpServletRequest request)
	{
		String siteURL = request.getRequestURI().toString();
		return siteURL.replace( request.getServletPath(), "");
	}

}
