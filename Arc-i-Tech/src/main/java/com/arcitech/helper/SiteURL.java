package com.arcitech.helper;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;

public class SiteURL {

	private SiteURL() throws OperationNotSupportedException {
		throw new OperationNotSupportedException();

	}

	public static String getSiteURL(HttpServletRequest httpServletRequest) {
		String siteURL = httpServletRequest.getRequestURI();
		return siteURL.replace(httpServletRequest.getServletPath(), "");
	}
}
