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
	 * 简化登录：localhost:8080/Hez/Login。去往：Login.jsp
	 * 1. 验证登录
	 * 2. staff登录成功后，系统调用sendEmail，自动计算今天是否为工时填写 截止日期
	 */
	@RequestMapping(value="{pathname1}")
	public String login(@PathVariable String pathname1){
		return pathname1+".jsp";
	}	
		 
	@SuppressWarnings("static-access")
	@RequestMapping("/sign_in")
    public String selectAllSearch(Staff staff,HttpSession session,Model model){
		 //1. 验证登录
		 Staff staffInfo = staffService.staff_login(staff); 
		 if(staffInfo!=null){
			 session.setAttribute("staff",staffInfo);						//staff 存放 session 备用
			 
			 if(staff.getJobnumber().equals(staffInfo.getPassword())){
				 model.addAttribute("resetPassword","请尽快修改您初始登录密码");		//初始登录用户：用户名和密码相等；第一次登录需提示进行密码重置
			 }else{
				 model.addAttribute("resetPassword","");					//给默认值，resetPssword：""；否则redirect 的控制器下的注解@requestParam得不到参数会报错
			 }
				 
			//有网环境下完成实验
			 //2. staff登录成功后，系统调用sendEmail，自动计算今天是否为工时填写 截止日期。         y=30号为截至日期。到期前3天，通知填写工时记录y=(y-3)			
			 Calendar rightNow = Calendar.getInstance();
			 rightNow.setTime(new Date()); 
			 int nowday=rightNow.get(rightNow.DAY_OF_MONTH);
			 int endday=19;		//截止日期前3天
			if(nowday==endday){
				//发送者：
				MailSender mails=MailSendCollection.getMailSendColletion();
				//接受者
				try {
					mails.send(staffInfo.getEmail(),"【沃福信】| 工时填写通知","尊敬的["+staffInfo.getName()+"]：请尽快在"+endday+"号22:00前填写完您的工时信息");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				model.addAttribute("emailInfo","请前往您的邮箱"+staffInfo.getEmail()+"，您有一封新的邮件请查收！");
			}else{
				model.addAttribute("emailInfo","");
			}						 	 
			 return "redirect:mainForm";			 
		 }else{
			 model.addAttribute("message","用户名或密码错误/用户不存在");		 //未登录不允许访问系统
			 return "login.jsp";
		 }
    }
	
	
	/*
	 * 忘记密码，重置---邮箱得到验证码。
	 * 输入：账户号、邮箱
	 * 1. 账户（工号）存在，则
	 * 2. 发送验证码给输入的邮箱
	 * localhost:8080/Hez/forgetPassword.jsp进入，action跳到/forgetPassword，通过，则转
	 * resetPassword.jsp，action跳到/resetPassword，通过，则转
	 * resetPassword.jsp。
	 */
	@RequestMapping(value="/forgetPassword")
	public String forgetPassword(String accountNumber, String receiverMail ,Model model){		
		Staff staff=new Staff(); 
		staff.setJobnumber(accountNumber);
		Staff staff1=staffService.selectJobnumber(staff);
		//账户（工号）存在（邮箱验证放前端，账户验证是后端）
		if(staff1!=null){	
			long code=(int)((Math.random()*9+1)*100000);
			String strCode=String.valueOf(code);
			MailSender mails=MailSendCollection.getMailSendColletion(); //MailSendCollection 单件模式，工厂
			try {
				mails.send(receiverMail,"【沃福信】| 忘记密码","尊敬的["+staff1.getName()+"]：您的账户正在进行重置密码操作。验证码是："+strCode+" ，请尽快重置密码。（若非本人操作，请忽略。）");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("verificationCode",strCode);
			model.addAttribute("accountNumber",accountNumber);
			return "js/password_reset.jsp";		
		}else{
			model.addAttribute("accountinfo","账户输入错误，请重新输入。");
			return "js/password_forget.jsp";	
		}
	}
	
	/*
	 * 忘记密码，重置---验证邮箱，重设密码		【注：不知原密码（未登录）】
	 * 输入：验证码、新密码
	 * 1. 验证： 验证码
	 * 2. 重置密码
	 */
	@RequestMapping(value="/resetPassword")
	public String resetPassword(String verificationCode ,String inputCode,String newPasswod,String accountNumber,HttpSession session,Model model){		
		//1. 验证： 验证码
		if(inputCode.equals(verificationCode)){
			Staff staff = new Staff();			
			staff.setJobnumber(accountNumber);
			Staff staff1 = staffService.selectJobnumber(staff); //staff1.password:旧密码
		//	2. 重置密码
			staff1.setPassword(newPasswod);		//设置成新密码
			staffService.staffUpdatePassword(staff1); //update staff set password = ? where id = ? 
			model.addAttribute("message","请重新登录");
			return "login.jsp";		
		}else{
			model.addAttribute("codeinfo","验证码输入错误，请重新验证。");
			return "js/password_forget.jsp";		
		}
		
	}
		
	
	//修改密码:【注：知道原密码（已登录）】
	@RequestMapping(value="/staffUpdatePassword")
	public String staffUpdatePassword(String newpassword,String oldpassword,HttpSession session,Model model){
		Staff staff = (Staff)session.getAttribute("staff");
		if(oldpassword.equals(staff.getPassword())){
			staff.setPassword(newpassword);
			staffService.staffUpdatePassword(staff);
			model.addAttribute("message","请重新登录");
			session.removeAttribute("staff");
			return "login.jsp";
		}else{
			model.addAttribute("message","原密码错误");
			return "js/password_update.jsp";
		}
		
		
		
	} 
	 
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute("staff");
		return "login.jsp";
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * staff登录成功后，系统调用sendEmail，自动计算今天是否为工时填写 截止日期
	 */
/*	@RequestMapping(value="/sendEmail")
	public String sendEmail(Model model,HttpSession session,String receivedUser){
		Staff staff = (Staff)session.getAttribute("staff");
		String email=staff.getEmail();
		//15号为截至日期。到期前3天，通知填写工时记录
		Date date=new Date();
		if(date.getDay()==10){
			//MailSender mails=new MailSender("1492389250@qq.com","tteickccpzddjbfi");
			//mails.send("2352514838@qq.com","I love you","long time no see, I miss you, my baby!!!!");
			//发送者：
			MailSender mails=new MailSender("huanglihua_0912@163.com","hlh123");
			//接受者
			try {
				mails.send(receivedUser,"工时填写通知","请尽快在15号23:00前填写完您的工时信息");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		model.addAttribute("emailInfo","请前往您的邮箱，您有一封新的邮件请查收！");
		return "info.jsp";
	}
	 */
	 

}
