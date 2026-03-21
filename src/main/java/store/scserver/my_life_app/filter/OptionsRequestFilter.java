package store.scserver.my_life_app.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import java.io.IOException;

/**
 * OPTIONS 请求过滤器
 *
 * 过滤所有 OPTIONS 请求，直接返回 200 状态码
 * 解决 CORS 预检请求被拦截器拦截的问题
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class OptionsRequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 如果是 OPTIONS 请求（CORS 预检请求），直接返回 200
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setHeader("Access-Control-Allow-Origin", (String) request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            return;
        }

        // 非 OPTIONS 请求，继续后续的过滤器链
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化方法（不需要实现）
    }

    @Override
    public void destroy() {
        // 销毁方法（不需要实现）
    }
}
