package com.liudan.cms.util;
/**
 * CMS中的自定义异常
 * @author LENOVO
 *
 */
public class CMSException extends RuntimeException{

	
	private static final long serialVersionUID = 2241089456706754860L;

	//无参函数
	public CMSException() {
		
	}
//	有参函数
	public CMSException(String message) {
		super(message);
	}
}
