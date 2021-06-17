package cn.zyity.attendancemanage.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println(request.getRequestURI());
        if (request.getRequestURI().contains("/login") || request.getRequestURI().contains("/index.jsp") || request.getRequestURI().contains("/static")) {
//            登陆界面相关，放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
//            其他界面，检查是否登陆
            try {
                Object _loginId = request.getSession().getAttribute("loginId");
                if (_loginId == null) {
                    response.sendRedirect("/index.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
