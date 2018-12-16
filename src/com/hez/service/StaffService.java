package com.hez.service;

import java.util.List;

import com.hez.domain.Staff;

public interface StaffService {
	public Staff staff_login(Staff staff);
	public int staffUpdatePassword(Staff staff);
	public Staff selectStaffView(int id);
	public int selectStaffOfStaffTeam(String staffjobnumber,String staffname);
	public Staff selectJobnumber(Staff staff);
	public int updateStaffinfo(Staff staff);
}
