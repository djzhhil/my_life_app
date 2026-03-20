package store.scserver.my_life_app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 任务信息响应 VO
 */
@Data
public class TaskVO {

    /**
     * 任务ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 经验奖励
     */
    private Integer expReward;

    /**
     * 金币奖励
     */
    private Integer coinReward;

    /**
     * 任务状态
     * - pending: 待完成
     * - done: 已完成
     */
    private String status;

    /**
     * 创建时间
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;

    /**
     * 完成时间（完成时才有值）
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime completedAt;

    /**
     * 更新时间
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedAt;
}
