package com.hez.controller;

import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.hez.domain.Staff;
import com.hez.send.MailSendCollection;
import com.hez.send.MailSender;
import com.hez.service.StaffService;


@Controller
public class StaffController {
	 @Autowired
	 @Qualifier("staffService")
	 StaffService staffService;
	 
	 
	/*
	 * �򻯵�¼��localhost:8080/Hez/Login��ȥ����Login.jsp
	 * 1. ��֤��¼
	 * 2. staff��¼�ɹ���ϵͳ����sendEmail���Զ���������Ƿ�Ϊ��ʱ��д ��ֹ����
	 */
	@RequestMapping(value="{pathname1}")
	public String login(@PathVariable String pathname1){
		return pathname1+".jsp";
	}	
		 
	@SuppressWarnings("static-access")
	@RequestMapping("/sign_in")
    public String selectAllSearch(Staff staff,HttpSession session,Model model){
		 //1. ��֤��¼
		 Staff staffInfo = staffService.staff_login(staff); 
		 if(staffInfo!=null){
			 session.setAttribute("staff",staffInfo);						//staff ��� session ����
			 
			 if(staff.getJobnumber().equals(staffInfo.getPassword())){
				 model.addAttribute("resetPassword","�뾡���޸�����ʼ��¼����");		//��ʼ��¼�û����û�����������ȣ���һ�ε�¼����ʾ������������
			 }else{
				 model.addAttribute("resetPassword","");					//��Ĭ��ֵ��resetPssword��""������redirect �Ŀ������µ�ע��@requestParam�ò��������ᱨ��
			 }
				 
			//�������������ʵ��
			 //2. staff��¼�ɹ���ϵͳ����sendEmail���Զ���������Ƿ�Ϊ��ʱ��д ��ֹ���ڡ�         y=30��Ϊ�������ڡ�����ǰ3�죬֪ͨ��д��ʱ��¼y=(y-3)			
			 Calendar rightNow = Calendar.getInstance();
			 rightNow.setTime(new Date()); 
			 int nowday=rightNow.get(rightNow.DAY_OF_MONTH);
			 int endday=19;		//��ֹ����ǰ3��
			if(nowday==endday){
				//�����ߣ�
				MailSender mails=MailSendCollection.getMailSendColletion();
				//������
				try {
					mails.send(staffInfo.getEmail(),"���ָ��š�| ��ʱ��д֪ͨ","�𾴵�["+staffInfo.getName()+"]���뾡����"+endday+"��22:00ǰ��д�����Ĺ�ʱ��Ϣ");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				model.addAttribute("emailInfo","��ǰ����������"+staffInfo.getEmail()+"������һ���µ��ʼ�����գ�");
			}else{
				model.addAttribute("emailInfo","");
			}						 	 
			 return "redirect:mainForm";			 
		 }else{
			 model.addAttribute("message","�û������������/�û�������");		 //δ��¼���������ϵͳ
			 return "login.jsp";
		 }
    }
	
	
	/*
	 * �������룬����---����õ���֤�롣
	 * ���룺�˻��š�����
	 * 1. �˻������ţ����ڣ���
	 * 2. ������֤������������
	 * localhost:8080/Hez/forgetPassword.jsp���룬action����/forgetPassword��ͨ������ת
	 * resetPassword.jsp��action����/resetPassword��ͨ������ת
	 * resetPassword.jsp��
	 */
	@RequestMapping(value="/forgetPassword")
	public String forgetPassword(String accountNumber, String receiverMail ,Model model){		
		Staff staff=new Staff(); 
		staff.setJobnumber(accountNumber);
		Staff staff1=staffService.selectJobnumber(staff);
		//�˻������ţ����ڣ�������֤��ǰ�ˣ��˻���֤�Ǻ�ˣ�
		if(staff1!=null){	
			long code=(int)((Math.random()*9+1)*100000);
			String strCode=String.valueOf(code);
			MailSender mails=MailSendCollection.getMailSendColletion(); //MailSendCollection ����ģʽ������
			try {
				mails.send(receiverMail,"���ָ��š�| ��������","�𾴵�["+staff1.getName()+"]�������˻����ڽ������������������֤���ǣ�"+strCode+" ���뾡���������롣�����Ǳ��˲���������ԡ���");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("verificationCode",strCode);
			model.addAttribute("accountNumber",accountNumber);
			return "js/password_reset.jsp";		
		}else{
			model.addAttribute("accountinfo","�˻�����������������롣");
			return "js/password_forget.jsp";	
		}
	}
	
	/*
	 * �������룬����---��֤���䣬��������		��ע����֪ԭ���루δ��¼����
	 * ���룺��֤�롢������
	 * 1. ��֤�� ��֤��
	 * 2. ��������
	 */
	@RequestMapping(value="/resetPassword")
	public String resetPassword(String verificationCode ,String inputCode,String newPasswod,String accountNumber,HttpSession session,Model model){		
		//1. ��֤�� ��֤��
		if(inputCode.equals(verificationCode)){
			Staff staff = new Staff();			
			staff.setJobnumber(accountNumber);
			Staff staff1 = staffService.selectJobnumber(staff); //staff1.password:������
		//	2. ��������
			staff1.setPassword(newPasswod);		//���ó�������
			staffService.staffUpdatePassword(staff1); //update staff set password = ? where id = ? 
			model.addAttribute("message","�����µ�¼");
			return "login.jsp";		
		}else{
			model.addAttribute("codeinfo","��֤�����������������֤��");
			return "js/password_forget.jsp";		
		}
		
	}
		
	
	//�޸�����:��ע��֪��ԭ���루�ѵ�¼����
	@RequestMapping(value="/staffUpdatePassword")
	public String staffUpdatePassword(String newpassword,String oldpassword,HttpSession session,Model model){
		Staff staff = (Staff)session.getAttribute("staff");
		if(oldpassword.equals(staff.getPassword())){
			staff.setPassword(newpassword);
			staffService.staffUpdatePassword(staff);
			model.addAttribute("message","�����µ�¼");
			session.removeAttribute("staff");
			return "login.jsp";
		}else{
			model.addAttribute("message","ԭ�������");
			return "js/password_update.jsp";
		}
		
		
		
	} 
	 
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute("staff");
		return "login.jsp";
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * staff��¼�ɹ���ϵͳ����sendEmail���Զ���������Ƿ�Ϊ��ʱ��д ��ֹ����
	 */
/*	@RequestMapping(value="/sendEmail")
	public String sendEmail(Model model,HttpSession session,String receivedUser){
		Staff staff = (Staff)session.getAttribute("staff");
		String email=staff.getEmail();
		//15��Ϊ�������ڡ�����ǰ3�죬֪ͨ��д��ʱ��¼
		Date date=new Date();
		if(date.getDay()==10){
			//MailSender mails=new MailSender("1492389250@qq.com","tteickccpzddjbfi");
			//mails.send("2352514838@qq.com","I love you","long time no see, I miss you, my baby!!!!");
			//�����ߣ�
			MailSender mails=new MailSender("huanglihua_0912@163.com","hlh123");
			//������
			try {
				mails.send(receivedUser,"��ʱ��д֪ͨ","�뾡����15��23:00ǰ��д�����Ĺ�ʱ��Ϣ");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		model.addAttribute("emailInfo","��ǰ���������䣬����һ���µ��ʼ�����գ�");
		return "info.jsp";
	}
	 */
	 

}
