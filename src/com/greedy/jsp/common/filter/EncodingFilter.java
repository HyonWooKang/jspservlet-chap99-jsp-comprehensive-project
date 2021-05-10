package com.greedy.jsp.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.sun.net.httpserver.HttpServer;

/**
 * Servlet Filter implementation class EncodingFilter
 */
/* xml 방식으로 필터 적용 */
public class EncodingFilter implements Filter {

	/* web.xml에 선언한 encoding-type을 이용하여 여기서 선언 후 사용 */
	private String encodingType;
		
    /**
     * Default constructor. 
     */
    public EncodingFilter() {
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hrequest = (HttpServletRequest) request;
		if("POST".equals(hrequest.getMethod())) {
			request.setCharacterEncoding(encodingType);
			System.out.println("변경된 인코딩 타입 : " + request.getCharacterEncoding());
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		/* xml에 설정한 init-param의 key를 이용하여 fConfig에서 값을 꺼내올 수 있다. */
		encodingType = fConfig.getInitParameter("encoding-type");
	}

}
