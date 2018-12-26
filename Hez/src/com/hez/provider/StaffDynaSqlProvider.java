package com.hez.provider;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.hez.domain.Overtime;
import com.hez.domain.Staff;


public class StaffDynaSqlProvider {
	
	
	
	//#{jobnumber}: class com.hez.domain.Staff 的属性
	public String selectStaffOfStaffTeam(final @Param("staffjobnumber")String staffjobnumber,
										 final @Param("staffname")String staffname){
		return new SQL(){{
			SELECT("id");
			FROM("staff");
			if(staffjobnumber!=null && !staffjobnumber.equals("")){
		    	WHERE("jobnumber = #{staffjobnumber} "); 	
		    }
			if(staffname!=null && !staffname.equals("")){
		    	WHERE("name = #{staffname} ");    		
		    }
			
		}}.toString();
	}
	
	public String updateStaffinfo(final Staff staff){
		return new SQL(){{
			UPDATE("staff");
			if(staff.getPhone()!=null && !staff.getPhone().equals("")){
				SET("phone=#{phone}");
			}
			if(staff.getEmail()!=null && !staff.getEmail().equals("")){
				SET("email=#{email}");
			}
			if(staff.getRtxaccount()!=null && !staff.getRtxaccount().equals("")){
				SET("rtxaccount=#{rtxaccount}");
			}
			if(staff.getNotificationway()!=null && !staff.getNotificationway().equals("")){
				SET("notificationway=#{notificationway}");
			}
			WHERE("id=#{id}");
		}}.toString();
	}
	
	public String selectStaff(final Staff staff){
		return new SQL(){{
			SELECT("*");
			FROM("staff_info");
			if(staff.getName()!=null&&staff.getName().equals("")){        //根据用户名信息

				WHERE("name=#{name}"); 
			}
			if(staff.getPassword()!=null&&staff.getPassword().equals("")){        //根据用户名mima

				WHERE("password=#{password}");    
			}
		}}.toString();
	}
	
}
