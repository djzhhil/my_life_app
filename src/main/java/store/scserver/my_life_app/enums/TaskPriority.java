package store.scserver.my_life_app.enums;

import lombok.Getter;

/**
 * 任务优先级枚举
 */
@Getter
public enum TaskPriority {
    /** 低优先级 */
    LOW(1, "低"),
    /** 中优先级 */
    MEDIUM(2, "中"),
    /** 高优先级 */
    HIGH(3, "高");

    private final Integer code;
    private final String name;

    TaskPriority(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据 code 获取枚举
     */
    public static TaskPriority fromCode(Integer code) {
        if (code == null) {
            return LOW;
        }
        for (TaskPriority priority : values()) {
            if (priority.code.equals(code)) {
                return priority;
            }
        }
        return LOW;
    }

    /**
     * 获取优先级名称
     */
    public static String getNameByCode(Integer code) {
        return fromCode(code).getName();
    }
}
