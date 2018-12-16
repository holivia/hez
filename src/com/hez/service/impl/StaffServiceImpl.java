package com.hez.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hez.dao.StaffDao;
import com.hez.domain.Staff;
import com.hez.service.StaffService;

@Service("staffService")
public class StaffServiceImpl implements StaffService{
	@Autowired
	StaffDao staffDao;
//µÇÂ¼ÅÐ¶Ï
	public Staff staff_login(Staff staff) {
		// TODO Auto-generated method stub
		return  staffDao.staff_login(staff);
	}
//ÐÞ¸ÄÃÜÂë
	public int staffUpdatePassword(Staff staff) {
		// TODO Auto-generated method stub
		return staffDao.staffUpdatePassword(staff);
	}
	
	public Staff selectStaffView(int id) {
		// TODO Auto-generated method stub
		return staffDao.selectStaffView(id);
	}
	public int selectStaffOfStaffTeam(String staffjobnumber, String staffname) {
		// TODO Auto-generated method stub
		return staffDao.selectStaffOfStaffTeam(staffjobnumber, staffname);
	}
	public Staff selectJobnumber(Staff staff) {
		// TODO Auto-generated method stub
		return staffDao.selectJobnumber(staff);
	}
	public int updateStaffinfo(Staff staff) {
		// TODO Auto-generated method stub
		return staffDao.updateStaffinfo(staff);
	}


}
