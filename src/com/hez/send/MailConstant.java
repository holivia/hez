package com.hez.send;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: �ʼ����ͳ�����
 *
 * @ClassName: MailConstant
 * @Copyright: Copyright (c) 2014
 *
 * @author Comsys-LZP
 * @date 2014-5-28 ����11:01:16
 * @version V2.0
 */
public class MailConstant {
	/*public static final String MAIL_USER = "huanglihua_0912@163.com"; 
	public static final String MAIL_PWD = "******";*/
	public static final boolean MAIL_IFDEBUG = true;
	public static final String MAIL_CONTENT_CHARSET = "text/html;charset=utf-8"; 
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HHʱmm��ss�� E");
	public static final String MAIL_TITLE = "*********�˺ż�����" + sdf.format(new Date());
	public static String getMailContent(String content){
		StringBuffer sb = new StringBuffer();
		sb.append("<div style='width:1024px;height:auto;margin:0px auto;background-color:#66CCFF;font-size:14px;font-family:΢���ź�;border-radius:5px;padding:5px;'><center><h1>");
		sb.append("</h1></center><div style='margin-left:20px;margin-bottom:10px;'><b>�𾴵��û������ã�</b><br/><br/>");
		sb.append("    <b></b>"+content);		
		sb.append("</div></div>");
		return sb.toString();
	}
}
