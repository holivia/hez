package com.hez.domain;
import java.util.Date;


public class Overtime {
	Integer id;
	Staff staff;
	Team team;
	Date date;		 //日期
	StaffTeam staffteam;
	String mealcoupon;  //餐券数
	Integer dayoff; //请假工时
	Integer assignment;  //任务单工时
	String startime;
	String endtime;
	Integer duration;     //加班工时	
	String type;
	String dutymode;    //值班方式
	String notes;
	
	public StaffTeam getStaffteam() {
		return staffteam;
	}
	public void setStaffteam(StaffTeam staffteam) {
		this.staffteam = staffteam;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMealcoupon() {
		return mealcoupon;
	}
	public void setMealcoupon(String mealcoupon) {
		this.mealcoupon = mealcoupon;
	}
	
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getDayoff() {
		return dayoff;
	}
	public void setDayoff(Integer dayoff) {
		this.dayoff = dayoff;
	}
	public Integer getAssignment() {
		return assignment;
	}
	public void setAssignment(Integer assignment) {
		this.assignment = assignment;
	}
	public String getStartime() {
		return startime;
	}
	public void setStartime(String startime) {
		this.startime = startime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDutymode() {
		return dutymode;
	}
	public void setDutymode(String dutymode) {
		this.dutymode = dutymode;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	


}
