package store.scserver.my_life_app.context;

/**
 * 用户上下文
 * 用于获取当前登录用户ID
 */
public class UserContext {
    private static final ThreadLocal<Long> userIdHolder = new ThreadLocal<>();

    /**
     * 设置当前用户ID
     */
    public static void setCurrentUserId(Long userId) {
        userIdHolder.set(userId);
    }

    /**
     * 获取当前用户ID
     */
    public static Long getCurrentUserId() {
        return userIdHolder.get();
    }

    /**
     * 清除当前用户ID
     */
    public static void clear() {
        userIdHolder.remove();
    }
}
