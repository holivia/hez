package com.hez.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hez.dao.OvertimeDao;
import com.hez.domain.Overtime;

import com.hez.service.OvertimeService;
@Service("overtimeService")
public class OvertimeServiceImpl implements OvertimeService {
	
	@Autowired 
	OvertimeDao overtimeDao;
	
	public List<Overtime> selectOvertimeByPage(Map params) {
		// TODO Auto-generated method stub
		return overtimeDao.selectOvertimeByPage(params);
	}

	public List<Overtime> selectForPie(Map params) {
		// TODO Auto-generated method stub
		return overtimeDao.selectForPie(params);
	}

	public List<Overtime> selectForScatter_List(Map params) {
		// TODO Auto-generated method stub
		return overtimeDao.selectForScatter_List(params);
	}

	public List<Overtime> selectForAssignment() {
		// TODO Auto-generated method stub
		return overtimeDao.selectForAssignment();
	}

	public List<Overtime> selectForDayoff() {
		// TODO Auto-generated method stub
		return overtimeDao.selectForDayoff();
	}

	public List<Overtime> selectForDuration() {
		// TODO Auto-generated method stub
		return overtimeDao.selectForDuration();
	}

	public List<Overtime> selectForDurationt3Month() {
		// TODO Auto-generated method stub
		return overtimeDao.selectForDurationt3Month();
	}

	public int deleteOvertime(Integer id) {
		// TODO Auto-generated method stub
		return overtimeDao.deleteOvertime(id);
	}



	public Overtime selectByOvertimeId(int id) {
		// TODO Auto-generated method stub
		return overtimeDao.selectByOvertimeId(id);
	}

	public int updataOverTime(HashMap params) {
		// TODO Auto-generated method stub
		return overtimeDao.updataOverTime(params);
	}

	public int insertOvertime(java.util.HashMap params) {
		// TODO Auto-generated method stub
		return overtimeDao.insertOvertime(params);
	}

	public List<Overtime> selectOvertimeByPageTwo(Map params) {
		// TODO Auto-generated method stub
		return overtimeDao.selectOvertimeByPageTwo(params);
	}

	

	
	

}
