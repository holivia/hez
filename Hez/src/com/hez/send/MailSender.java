package com.hez.send;
/**
 * @Description: 
 *
 * @Title: MailSender.java
 * @Package com.joyce.service.impl
 * @Copyright: Copyright (c) 2014
 *
 * @author Comsys-LZP
 * @date 2014-5-28 ����09:03:08
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
 * @Description:�ʼ�������
 * 
 * @ClassName: MailSender
 * @Copyright: Copyright (c) 2014
 * 
 * @author Comsys-LZP
 * @date 2014-5-28 ����09:03:08
 * @version V2.0
 */
public class MailSender {
	/**
	 * �����ʼ���props�ļ�
	 */
	private final transient Properties props = new Properties();
	/**
	 * �ʼ���������¼��֤
	 */
	private transient MailAuthenticator authenticator;

	/**
	 * ����session
	 */
	private transient Session session;

	/**
	 * ��ʼ���ʼ�������
	 * 
	 * @param smtpHostName
	 *            SMTP�ʼ���������ַ
	 * @param username
	 *            �����ʼ����û���(��ַ)
	 * @param password
	 *            �����ʼ�������
	 */
	public MailSender(final String smtpHostName, final String username,
			final String password) {
		init(username, password, smtpHostName);
	}

	/**
	 * ��ʼ���ʼ�������
	 * 
	 * @param username
	 *            �����ʼ����û���(��ַ)�����Դ˽���SMTP��������ַ
	 * @param password
	 *            �����ʼ�������
	 */
	public MailSender(final String username, final String password) {
		// ͨ�������ַ������smtp���������Դ�������䶼����
		final String smtpHostName = "smtp." + username.split("@")[1];
		init(username, password, smtpHostName);

	}

	/**
	 * @Description: ��ʼ��
	 * 
	 * @param username
	 *            �����ʼ����û���(��ַ)
	 * @param password
	 *            ����
	 * @param smtpHostName
	 *            SMTP������ַ
	 * 
	 * @Title: MailSender.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-5-28 ����09:18:31
	 * @version V2.0
	 */
	private void init(String username, String password, String smtpHostName) {
		// ��ʼ��props
		props.put("mail.smtp.host", smtpHostName);
		//props.put("mail.smtp.host", "smtp.qq.com");
		props.put("mail.smtp.auth", "true");
		// ��֤
		authenticator = new MailAuthenticator(username, password);
		// ����session
		session = Session.getInstance(props, authenticator);
		// ��ӡһЩ������Ϣ
		session.setDebug(MailConstant.MAIL_IFDEBUG); 
	}

	/**
	 * @Description: �����ʼ�
	 * 
	 * @param recipient
	 *            �ռ��������ַ
	 * @param subject
	 *            �ʼ�����
	 * @param content
	 *            �ʼ�����
	 * @throws AddressException
	 * @throws MessagingException
	 * 
	 * @Title: MailSender.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-5-28 ����09:19:16
	 * @version V2.0
	 */
	public void send(String recipient, String subject, Object content) throws Exception {
		send(recipient, subject, content, null);
	}

	/**
	 * �����ʼ�
	 * 
	 * @param recipient
	 *            �ռ��������ַ
	 * @param subject
	 *            �ʼ�����
	 * @param content
	 *            �ʼ�����
	 * @param files
	 *            ����
	 * @throws Exception
	 * @author Joyce.Luo
	 * @date 2014-10-15 ����10:23:09
	 * @version V3.0
	 * @since Tomcat6.0,Jdk1.6
	 * @copyright: Copyright (c) 2014
	 */
	public void send(String recipient, String subject, Object content, Vector<File> files) throws Exception {
		// ����mime�����ʼ�
		final MimeMessage message = new MimeMessage(session);
		// ���÷�����
		message.setFrom(new InternetAddress(authenticator.getUsername()));
		// �����ռ���
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(
				recipient));
		// ��������
		message.setSubject(subject);
		// �����ʼ�����
		if (null == files || files.size() == 0) {
			message.setContent(content.toString(), MailConstant.MAIL_CONTENT_CHARSET);
		} else {
			//���� Mimemultipart�������(�ɰ����������)
			MimeMultipart multipart = new MimeMultipart();
			//MimeBodyPart(�����ż�����/����)
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(content.toString(), MailConstant.MAIL_CONTENT_CHARSET);
			//��ӵ�MimeMultipart������
			multipart.addBodyPart(bodyPart);
			for (int i = 0; i < files.size(); i++) {
				File file = (File) files.elementAt(i);
				String fname = file.getName();
				//����FileDAtaSource(������Ӹ���)
				FileDataSource fds = new FileDataSource(file);
				BodyPart fileBodyPart = new MimeBodyPart();
				// �ַ�����ʽװ���ļ�
				fileBodyPart.setDataHandler(new DataHandler(fds));
				// ���ø����ļ���
				fileBodyPart.setFileName(fname);
				multipart.addBodyPart(fileBodyPart);
				message.setContent(multipart);
			}
		}
		// ���÷���ʱ��
		message.setSentDate(new Date());
		// �洢�ʼ���Ϣ
		message.saveChanges();
//		message.setFileName(filename)
		// �����ʼ�
		Transport.send(message);
	}

	/**
	 * @Description: Ⱥ���ʼ�
	 * 
	 * @param recipients
	 *            �ռ�����
	 * @param subject
	 *            ����
	 * @param content
	 *            ����
	 * @throws AddressException
	 * @throws MessagingException
	 * 
	 * @Title: MailSender.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-5-28 ����09:20:24
	 * @version V2.0
	 */
	public void send(List<String> recipients, String subject, Object content) throws Exception {
		send(recipients, subject, content, null);
	}

	/**
	 * Ⱥ���ʼ�
	 * 
	 * @param recipients
	 *            �ռ�����
	 * @param subject
	 *            ����
	 * @param content
	 *            ����
	 * @param files
	 *            ����
	 * @throws Exception
	 * @author Joyce.Luo
	 * @date 2014-10-15 ����10:26:35
	 * @version V3.0
	 * @since Tomcat6.0,Jdk1.6
	 * @copyright: Copyright (c) 2014
	 */
	public void send(List<String> recipients, String subject, Object content, Vector<File> files) throws Exception {
		// ����mime�����ʼ�
		final MimeMessage message = new MimeMessage(session);
		// ���÷�����
		message.setFrom(new InternetAddress(authenticator.getUsername()));
		// �����ռ�����
		final int num = recipients.size();
		InternetAddress[] addresses = new InternetAddress[num];
		for (int i = 0; i < num; i++) {
			addresses[i] = new InternetAddress(recipients.get(i));
		}
		message.setRecipients(Message.RecipientType.TO, addresses);
		// ��������
		message.setSubject(subject);
		// �����ʼ�����
		if (null == files || files.size() == 0) {
			message.setContent(content.toString(), MailConstant.MAIL_CONTENT_CHARSET);
		} else {
			 //���� Mimemultipart�������(�ɰ����������)
			MimeMultipart multipart = new MimeMultipart();
			  //MimeBodyPart(�����ż�����/����)
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(content.toString(), MailConstant.MAIL_CONTENT_CHARSET);
			 //��ӵ�MimeMultipart������
			multipart.addBodyPart(bodyPart);
			for (int i = 0; i < files.size(); i++) {
				File file = (File) files.elementAt(i);
				String fname = file.getName();
				//����FileDAtaSource(������Ӹ���)
				FileDataSource fds = new FileDataSource(file);
				BodyPart fileBodyPart = new MimeBodyPart();
				// �ַ�����ʽװ���ļ�
				fileBodyPart.setDataHandler(new DataHandler(fds));
				// ���ø����ļ���
				fname = new String(fname.getBytes("UTF-8"), "ISO-8859-1");
				fileBodyPart.setFileName(fname);
				multipart.addBodyPart(fileBodyPart);
				message.setContent(multipart);
			}
		}
		// ���÷���ʱ��
		message.setSentDate(new Date());
		// �洢�ʼ���Ϣ
		message.saveChanges();
		// �����ʼ�
		Transport.send(message);
	}

	/**
	 * @Description: �����ʼ�
	 * 
	 * @param recipient
	 *            �ռ��������ַ
	 * @param mail
	 *            �ʼ�����
	 * @throws Exception
	 * 
	 * @Title: MailSender.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-5-28 ����09:20:54
	 * @version V2.0
	 */
	public void send(String recipient, Mail mail) throws Exception {
		send(recipient, mail.getSubject(), mail.getContent());
	}

	/**
	 * @Description: Ⱥ���ʼ�
	 * 
	 * @param recipients
	 *            �ռ�����
	 * @param mail
	 *            �ʼ�����
	 * @throws Exception
	 * 
	 * @Title: MailSender.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-5-28 ����09:21:19
	 * @version V2.0
	 */
	public void send(List<String> recipients, Mail mail) throws Exception {
		send(recipients, mail.getSubject(), mail.getContent());
	}

	/**
	 * Ⱥ���ʼ�
	 * 
	 * @param recipients
	 *            �ռ�����
	 * @param mail
	 *            �ʼ�����
	 * @param files
	 *            ����
	 * @throws Exception
	 * @author Joyce.Luo
	 * @date 2014-10-15 ����10:28:38
	 * @version V3.0
	 * @since Tomcat6.0,Jdk1.6
	 * @copyright: Copyright (c) 2014
	 */
	public void send(List<String> recipients, Mail mail, Vector<File> files) throws Exception {
		send(recipients, mail.getSubject(), mail.getContent(), files);
	}
}
