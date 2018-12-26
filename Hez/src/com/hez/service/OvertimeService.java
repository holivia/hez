package com.hez.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hez.domain.Overtime;

public interface OvertimeService {
	public List<Overtime> selectOvertimeByPage(Map params);
	public List<Overtime> selectForPie(Map params);
	public List<Overtime> selectForScatter_List(Map params);
	public List<Overtime> selectForDurationt3Month();
	public List<Overtime> selectForDuration();
	public List<Overtime> selectForDayoff();
	public List<Overtime> selectForAssignment();



	public  List<Overtime> selectOvertimeByPageTwo(Map params);
	public int insertOvertime(HashMap params);

	public  int updataOverTime(HashMap params);

	public int deleteOvertime(Integer id);

	public Overtime selectByOvertimeId(int id);
}
