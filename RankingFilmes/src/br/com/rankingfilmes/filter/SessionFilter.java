package br.com.rankingfilmes.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class SessionFilter implements Filter {  
    public void init(FilterConfig config) {  
    }  
  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {  
        HttpServletRequest httpRequest = (HttpServletRequest)request;  
  
        try {  
            if (!httpRequest.isRequestedSessionIdValid()) {  
                HttpServletResponse httpResponse = (HttpServletResponse)response;  
                httpResponse.sendRedirect(httpRequest.getContextPath() + "index.jsp");  
            } else {  
                chain.doFilter(request, response);  
            }  
        } catch (Exception e) {  
            HttpServletResponse httpResponse = (HttpServletResponse)response;  
            httpResponse.sendRedirect(httpRequest.getContextPath() + "index.jsp");  
        }  
    }  
  
    public void destroy() {  
    }  
}