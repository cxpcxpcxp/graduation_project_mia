package com.electricexam.pojo;
/**
 * @Author: MiaChen
 * @Description: 返回json数据实体类
 * @Date: 2020/3/25 19:50
 * @Version: 1.0
 */
public class AjaxStandardEntity {
	//状态码 非0为失败  0 为成功
	private Integer code;
	//数据
	private Object data;
	//提示
	private String message;

	public AjaxStandardEntity(Integer code, Object data, String message) {
		this.code = code;
		this.data = data;
		this.message = message;
	}

	@Override
	public String toString() {
		return "AjaxStandardEntity{" +
				"code=" + code +
				", data=" + data +
				", message='" + message + '\'' +
				'}';
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
