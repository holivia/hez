package com.hez.send;
//https://javaee.github.io/javamail/
//https://www.oracle.com/technetwork/java/index-138643.html
//qq��Ȩ�룺ttei ckcc pzdd jbfi

//163��126�����ʻ���¼��"huanglihua_0912@163.com","hlh123"
//qq�ʻ���¼��"1492389250@qq.com","tteickccpzddjbfi"

/*
 * ����ģʽ
 */
public class MailSendCollection {
	
	private static MailSender mails=new MailSender("huanglihua_0912@163.com","hlh123");
							//JVM����mailsʱ����uniqueInstance�����췽����privateȨ��
		
	public static MailSender getMailSendColletion(){ //�෽��
		return mails;
	}
	
	private MailSendCollection(){}

}