package com.hez.service;

import java.util.List;

import com.hez.domain.Overtime;
import com.hez.domain.Staff;
import com.hez.domain.StaffTeam;

public interface StaffService {
	public Staff staff_login(Staff staff);
	public int staffUpdatePassword(Staff staff);
	public Staff selectStaffView(int id);
	public int selectStaffOfStaffTeam(String staffjobnumber,String staffname);
	public Staff selectJobnumber(Staff staff);
	public int updateStaffinfo(Staff staff);



	
	public Staff selectStaffById(int id);

	public List<StaffTeam> selectStaff(Integer id);

	
}
