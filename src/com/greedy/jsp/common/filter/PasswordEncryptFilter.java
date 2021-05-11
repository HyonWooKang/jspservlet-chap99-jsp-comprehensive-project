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

import com.greedy.jsp.common.wrapper.EncryptRequestWrapper;

/**
 * Servlet Filter implementation class PasswordEncryptFilter
 */
@WebFilter("/member/*") // 경로를 /*로 지정하여 로그인 외에도 필요한 경우 사용하도록 확장성을 확보함
public class PasswordEncryptFilter implements Filter {

	// 사용자가 입력한 문자를 암호화 처리하는 필터
    public PasswordEncryptFilter() {
        
    }


	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hrequest = (HttpServletRequest) request;
		// uri 메소드를 사용하기 위해 downcasting
		
		String uri = hrequest.getRequestURI();	
		System.out.println("uri : " + uri); // 결과 /jsp/member/regist
		// url의 상의가 uri이다 (uri: 절대경로, url: http protcol의 경로)
		// 여기서 Local에 있는 자원(Resource)의 경로를 찾기 위해 쓰는 것
		
		String intent = uri.substring(uri.lastIndexOf("/"));
		System.out.println("intent : " + intent); // 결과 /regist
		
		/*
		 * 필터와 래퍼는 아래와 같은 이유로 쌍으로 함께 가는 경우가 많다.
		 */
		
		/* regist만 암호화 처리 */
		if(!"/login".equals(intent)) {
			
			/* wrapper로 보내서 확인 */
			EncryptRequestWrapper wrapper = new EncryptRequestWrapper(hrequest);
			
			/* request자리에 wrapper 넣어주기 (wrapper는 request가 wrapping된 것이다) */
			chain.doFilter(wrapper, response);
		} else {
			/* Servlet이 자동으로 hrequest를 request로 만든다 */ 
			chain.doFilter(request, response);
		}	

	}

	public void init(FilterConfig fConfig) throws ServletException {
		
		
	}

}
