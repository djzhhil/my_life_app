package store.scserver.my_life_app.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 更新任务请求 DTO
 * 注：部分更新，所有字段均为可选
 */
@Data
public class TaskUpdateDTO {

    /**
     * 任务标题（可选更新）
     * 长度：1-100 字符
     */
    @Size(max = 100, message = "任务标题不能超过 100 个字符")
    private String title;

    /**
     * 任务描述（可选更新）
     * 长度：0-500 字符
     */
    @Size(max = 500, message = "任务描述不能超过 500 个字符")
    private String description;
}
