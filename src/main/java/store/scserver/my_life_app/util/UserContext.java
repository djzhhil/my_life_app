package store.scserver.my_life_app.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户上下文工具类
 * 使用 ThreadLocal 存储当前请求的用户信息
 */
public class UserContext {

    // 用户 ID 的 ThreadLocal
    private static final ThreadLocal<Long> USER_ID = new ThreadLocal<>();

    // 用户名的 ThreadLocal
    private static final ThreadLocal<String> USERNAME = new ThreadLocal<>();

    /**
     * 获取当前请求的用户 ID
     */
    public static Long getCurrentUserId() {
        return USER_ID.get();
    }

    /**
     * 设置当前请求的用户 ID
     */
    public static void setCurrentUserId(Long userId) {
        USER_ID.set(userId);
    }

    /**
     * 获取当前请求的用户名
     */
    public static String getCurrentUsername() {
        return USERNAME.get();
    }

    /**
     * 设置当前请求的用户名
     */
    public static void setCurrentUsername(String username) {
        USERNAME.set(username);
    }

    /**
     * 清除当前请求的用户信息
     */
    public static void clear() {
        USER_ID.remove();
        USERNAME.remove();
    }

    /**
     * 获取所有已注册的上下文变量，用于请求结束时统一清理
     */
    public static List<ThreadLocal<?>> getAllThreadLocals() {
        List<ThreadLocal<?>> locals = new ArrayList<>();
        locals.add(USER_ID);
        locals.add(USERNAME);
        return locals;
    }
}
