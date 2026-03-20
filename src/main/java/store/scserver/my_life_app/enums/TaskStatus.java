package store.scserver.my_life_app.enums;

/**
 * 任务状态枚举
 */
public enum TaskStatus {

    /**
     * 待完成
     */
    PENDING("pending", "待完成"),

    /**
     * 已完成
     */
    DONE("done", "已完成");

    /**
     * 状态码
     */
    private final String code;

    /**
     * 状态描述
     */
    private final String description;

    TaskStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
