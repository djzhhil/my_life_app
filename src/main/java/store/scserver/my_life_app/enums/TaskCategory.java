package store.scserver.my_life_app.enums;

import lombok.Getter;

/**
 * 任务分类枚举
 */
@Getter
public enum TaskCategory {
    /** 通用 */
    GENERAL("general", "通用"),
    /** 学习 */
    STUDY("study", "学习"),
    /** 工作 */
    WORK("work", "工作"),
    /** 生活 */
    LIFE("life", "生活"),
    /** 健康 */
    HEALTH("health", "健康");

    private final String code;
    private final String name;

    TaskCategory(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据 code 获取枚举
     */
    public static TaskCategory fromCode(String code) {
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
    public static String getNameByCode(String code) {
        return fromCode(code).getName();
    }
}
