package com.freya.pojo;

/**
 *  code integer 代码
 *  success boolean 请求状态
 *  message string 返回消息
 */
public class Response {
    private int code;
    private boolean success;
    private String message;
    private Object data;

    public Response() {
    }

    public Response(int code, boolean success, String message, Object object) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = object;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
