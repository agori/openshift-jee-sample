package it.idealegno;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LanguageFilter implements Filter
{

	private Collection<Locale> supportedLanguages = Arrays.asList(Locale.ITALIAN, Locale.ENGLISH);

	public void init(FilterConfig filterConfig) throws ServletException
	{
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{

		HttpServletRequest httpReq = (HttpServletRequest) request;
		String reqUrl = httpReq.getRequestURL().toString();
		if (!reqUrl.contains("/ln/") && !httpReq.getRequestURI().contains("."))
		{
			Locale locale = detectLocale(httpReq);
			
			HttpServletResponse httpRes = (HttpServletResponse) response;
			String url = reqUrl.replace(httpReq.getContextPath(), httpReq.getContextPath() + "/ln/" + locale.getLanguage());
			if (url.endsWith("/"))
			{
				url = url.substring(0, url.length() - 1);
			}
			httpRes.sendRedirect(url);
			return;
		}
		
		chain.doFilter(request, response);
		
	}

	private Locale detectLocale(HttpServletRequest request)
	{
		Enumeration<Locale> locales = request.getLocales();
		while (locales.hasMoreElements())
		{
			Locale locale = (Locale) locales.nextElement();
			if (supportedLanguages.contains(locale))
			{
				return locale;
			}
		}
		return Locale.ITALIAN;
	}

	public void destroy()
	{

	}

}
