package store.scserver.my_life_app.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import store.scserver.my_life_app.util.JwtUtils;
import store.scserver.my_life_app.util.UserContext;

/**
 * JWT 拦截器
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取 Token
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "未登录或 Token 无效");
            return false;
        }

        String token = authHeader.substring(7);

        // 验证 Token
        if (!jwtUtils.validateToken(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token 无效或已过期");
            return false;
        }

        // 检查 Token 是否过期
        if (jwtUtils.isTokenExpired(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token 已过期");
            return false;
        }

        // 将用户 ID 放入请求属性
        Long userId = jwtUtils.getUserIdFromToken(token);
        String username = jwtUtils.getUsernameFromToken(token);

        request.setAttribute("userId", userId);
        request.setAttribute("username", username);

        // 设置到 UserContext，方便 Service 层使用
        UserContext.setCurrentUserId(userId);
        UserContext.setCurrentUsername(username);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清理 UserContext，避免内存泄漏
        UserContext.clear();
    }
}
