package com.hez.send;
/**
 * @Description: 
 *
 * @Title: MailSender.java
 * @Package com.joyce.service.impl
 * @Copyright: Copyright (c) 2014
 *
 * @author Comsys-LZP
 * @date 2014-5-28 上午09:03:08
 * @version V2.0
 */


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/*import com.hupu.nac.mail.bean.Mail;
import com.hupu.nac.mail.bean.MailAuthenticator;
import com.hupu.nac.mail.bean.MailConstant;
*/
/**
 * @Description:邮件发送类
 * 
 * @ClassName: MailSender
 * @Copyright: Copyright (c) 2014
 * 
 * @author Comsys-LZP
 * @date 2014-5-28 上午09:03:08
 * @version V2.0
 */
public class MailSender {
	/**
	 * 发送邮件的props文件
	 */
	private final transient Properties props = new Properties();
	/**
	 * 邮件服务器登录验证
	 */
	private transient MailAuthenticator authenticator;

	/**
	 * 邮箱session
	 */
	private transient Session session;

	/**
	 * 初始化邮件发送器
	 * 
	 * @param smtpHostName
	 *            SMTP邮件服务器地址
	 * @param username
	 *            发送邮件的用户名(地址)
	 * @param password
	 *            发送邮件的密码
	 */
	public MailSender(final String smtpHostName, final String username,
			final String password) {
		init(username, password, smtpHostName);
	}

	/**
	 * 初始化邮件发送器
	 * 
	 * @param username
	 *            发送邮件的用户名(地址)，并以此解析SMTP服务器地址
	 * @param password
	 *            发送邮件的密码
	 */
	public MailSender(final String username, final String password) {
		// 通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
		final String smtpHostName = "smtp." + username.split("@")[1];
		init(username, password, smtpHostName);

	}

	/**
	 * @Description: 初始化
	 * 
	 * @param username
	 *            发送邮件的用户名(地址)
	 * @param password
	 *            密码
	 * @param smtpHostName
	 *            SMTP主机地址
	 * 
	 * @Title: MailSender.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-5-28 上午09:18:31
	 * @version V2.0
	 */
	private void init(String username, String password, String smtpHostName) {
		// 初始化props
		props.put("mail.smtp.host", smtpHostName);
		//props.put("mail.smtp.host", "smtp.qq.com");
		props.put("mail.smtp.auth", "true");
		// 验证
		authenticator = new MailAuthenticator(username, password);
		// 创建session
		session = Session.getInstance(props, authenticator);
		// 打印一些调试信息
		session.setDebug(MailConstant.MAIL_IFDEBUG); 
	}

	/**
	 * @Description: 发送邮件
	 * 
	 * @param recipient
	 *            收件人邮箱地址
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @throws AddressException
	 * @throws MessagingException
	 * 
	 * @Title: MailSender.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-5-28 上午09:19:16
	 * @version V2.0
	 */
	public void send(String recipient, String subject, Object content) throws Exception {
		send(recipient, subject, content, null);
	}

	/**
	 * 发送邮件
	 * 
	 * @param recipient
	 *            收件人邮箱地址
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @param files
	 *            附件
	 * @throws Exception
	 * @author Joyce.Luo
	 * @date 2014-10-15 上午10:23:09
	 * @version V3.0
	 * @since Tomcat6.0,Jdk1.6
	 * @copyright: Copyright (c) 2014
	 */
	public void send(String recipient, String subject, Object content, Vector<File> files) throws Exception {
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		message.setFrom(new InternetAddress(authenticator.getUsername()));
		// 设置收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(
				recipient));
		// 设置主题
		message.setSubject(subject);
		// 设置邮件内容
		if (null == files || files.size() == 0) {
			message.setContent(content.toString(), MailConstant.MAIL_CONTENT_CHARSET);
		} else {
			//创建 Mimemultipart添加内容(可包含多个附件)
			MimeMultipart multipart = new MimeMultipart();
			//MimeBodyPart(用于信件内容/附件)
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(content.toString(), MailConstant.MAIL_CONTENT_CHARSET);
			//添加到MimeMultipart对象中
			multipart.addBodyPart(bodyPart);
			for (int i = 0; i < files.size(); i++) {
				File file = (File) files.elementAt(i);
				String fname = file.getName();
				//创建FileDAtaSource(用于添加附件)
				FileDataSource fds = new FileDataSource(file);
				BodyPart fileBodyPart = new MimeBodyPart();
				// 字符流形式装入文件
				fileBodyPart.setDataHandler(new DataHandler(fds));
				// 设置附件文件名
				fileBodyPart.setFileName(fname);
				multipart.addBodyPart(fileBodyPart);
				message.setContent(multipart);
			}
		}
		// 设置发信时间
		message.setSentDate(new Date());
		// 存储邮件信息
		message.saveChanges();
//		message.setFileName(filename)
		// 发送邮件
		Transport.send(message);
	}

	/**
	 * @Description: 群发邮件
	 * 
	 * @param recipients
	 *            收件人们
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @throws AddressException
	 * @throws MessagingException
	 * 
	 * @Title: MailSender.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-5-28 上午09:20:24
	 * @version V2.0
	 */
	public void send(List<String> recipients, String subject, Object content) throws Exception {
		send(recipients, subject, content, null);
	}

	/**
	 * 群发邮件
	 * 
	 * @param recipients
	 *            收件人们
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @param files
	 *            附件
	 * @throws Exception
	 * @author Joyce.Luo
	 * @date 2014-10-15 上午10:26:35
	 * @version V3.0
	 * @since Tomcat6.0,Jdk1.6
	 * @copyright: Copyright (c) 2014
	 */
	public void send(List<String> recipients, String subject, Object content, Vector<File> files) throws Exception {
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		message.setFrom(new InternetAddress(authenticator.getUsername()));
		// 设置收件人们
		final int num = recipients.size();
		InternetAddress[] addresses = new InternetAddress[num];
		for (int i = 0; i < num; i++) {
			addresses[i] = new InternetAddress(recipients.get(i));
		}
		message.setRecipients(Message.RecipientType.TO, addresses);
		// 设置主题
		message.setSubject(subject);
		// 设置邮件内容
		if (null == files || files.size() == 0) {
			message.setContent(content.toString(), MailConstant.MAIL_CONTENT_CHARSET);
		} else {
			 //创建 Mimemultipart添加内容(可包含多个附件)
			MimeMultipart multipart = new MimeMultipart();
			  //MimeBodyPart(用于信件内容/附件)
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(content.toString(), MailConstant.MAIL_CONTENT_CHARSET);
			 //添加到MimeMultipart对象中
			multipart.addBodyPart(bodyPart);
			for (int i = 0; i < files.size(); i++) {
				File file = (File) files.elementAt(i);
				String fname = file.getName();
				//创建FileDAtaSource(用于添加附件)
				FileDataSource fds = new FileDataSource(file);
				BodyPart fileBodyPart = new MimeBodyPart();
				// 字符流形式装入文件
				fileBodyPart.setDataHandler(new DataHandler(fds));
				// 设置附件文件名
				fname = new String(fname.getBytes("UTF-8"), "ISO-8859-1");
				fileBodyPart.setFileName(fname);
				multipart.addBodyPart(fileBodyPart);
				message.setContent(multipart);
			}
		}
		// 设置发信时间
		message.setSentDate(new Date());
		// 存储邮件信息
		message.saveChanges();
		// 发送邮件
		Transport.send(message);
	}

	/**
	 * @Description: 发送邮件
	 * 
	 * @param recipient
	 *            收件人邮箱地址
	 * @param mail
	 *            邮件对象
	 * @throws Exception
	 * 
	 * @Title: MailSender.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-5-28 上午09:20:54
	 * @version V2.0
	 */
	public void send(String recipient, Mail mail) throws Exception {
		send(recipient, mail.getSubject(), mail.getContent());
	}

	/**
	 * @Description: 群发邮件
	 * 
	 * @param recipients
	 *            收件人们
	 * @param mail
	 *            邮件对象
	 * @throws Exception
	 * 
	 * @Title: MailSender.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-5-28 上午09:21:19
	 * @version V2.0
	 */
	public void send(List<String> recipients, Mail mail) throws Exception {
		send(recipients, mail.getSubject(), mail.getContent());
	}

	/**
	 * 群发邮件
	 * 
	 * @param recipients
	 *            收件人们
	 * @param mail
	 *            邮件对象
	 * @param files
	 *            附件
	 * @throws Exception
	 * @author Joyce.Luo
	 * @date 2014-10-15 上午10:28:38
	 * @version V3.0
	 * @since Tomcat6.0,Jdk1.6
	 * @copyright: Copyright (c) 2014
	 */
	public void send(List<String> recipients, Mail mail, Vector<File> files) throws Exception {
		send(recipients, mail.getSubject(), mail.getContent(), files);
	}
}
