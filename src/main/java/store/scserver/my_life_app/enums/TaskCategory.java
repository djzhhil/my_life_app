package store.scserver.my_life_app.enums;

import lombok.Getter;

/**
 * 任务分类枚举
 */
@Getter
public enum TaskCategory {
    /** 通用 */
    GENERAL(0, "通用"),
    /** 学习 */
    STUDY(1, "学习"),
    /** 工作 */
    WORK(2, "工作"),
    /** 运动 */
    SPORT(3, "运动"),
    /** 生活 */
    LIFE(4, "生活"),
    /** 创意 */
    CREATIVE(5, "创意");

    private final Integer code;
    private final String name;

    TaskCategory(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据 code 获取枚举
     */
    public static TaskCategory fromCode(Integer code) {
        if (code == null) {
            return GENERAL;
        }
        for (TaskCategory category : values()) {
            if (category.code.equals(code)) {
                return category;
            }
        }
        return GENERAL;
    }

    /**
     * 获取分类名称
     */
    public static String getNameByCode(Integer code) {
        return fromCode(code).getName();
    }
}
