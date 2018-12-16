package com.hez.send;
//https://javaee.github.io/javamail/
//https://www.oracle.com/technetwork/java/index-138643.html
//qq授权码：ttei ckcc pzdd jbfi

//163及126邮箱帐户登录："huanglihua_0912@163.com","hlh123"
//qq帐户登录："1492389250@qq.com","tteickccpzddjbfi"

/*
 * 单件模式
 */
public class MailSendCollection {
	
	private static MailSender mails=new MailSender("huanglihua_0912@163.com","hlh123");
							//JVM加载mails时创建uniqueInstance，构造方法是private权限
		
	public static MailSender getMailSendColletion(){ //类方法
		return mails;
	}
	
	private MailSendCollection(){}

}