package store.scserver.my_life_app.common;

import lombok.Data;

/**
 * 统一响应格式
 *
 * @param <T> 数据泛型类型
 */
@Data
public class Result<T> {

    /**
     * 响应码
     * - 200: 成功
     * - 400: 请求参数错误
     * - 401: 未授权
     * - 403: 禁止访问
     * - 404: 资源不存在
     * - 500: 服务器内部错误
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 成功响应（无数据）
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("操作成功");
        return result;
    }

    /**
     * 成功响应（带数据）
     *
     * @param data 响应数据
     * @return Result<T>
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    /**
     * 成功响应（自定义消息）
     *
     * @param message 消息
     * @param data    数据
     * @return Result<T>
     */
    public static <T> Result<T> success(String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 失败响应
     *
     * @param code    响应码
     * @param message 错误消息
     * @return Result<T>
     */
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 失败响应（默认500）
     *
     * @param message 错误消息
     * @return Result<T>
     */
    public static <T> Result<T> error(String message) {
        return error(500, message);
    }

    /**
     * 请求参数错误（400）
     *
     * @param message 错误消息
     * @return Result<T>
     */
    public static <T> Result<T> badRequest(String message) {
        return error(400, message);
    }

    /**
     * 未授权（401）
     *
     * @param message 错误消息
     * @return Result<T>
     */
    public static <T> Result<T> unauthorized(String message) {
        return error(401, message);
    }
}
