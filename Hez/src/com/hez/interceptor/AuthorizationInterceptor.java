package com.hez.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthorizationInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		if(arg0.getSession().getAttribute("staff")==null){
			System.out.println("��������");
			arg0.setAttribute("message", "�������ȵ�¼�ٷ��ʱ���վ");
			arg0.getRequestDispatcher("login.jsp").forward(arg0,arg1); //����login.jsp��ȥ��¼ҳ��
			return false;
		}else{
			System.out.println("��������");
			return true;
		}
	}

}
