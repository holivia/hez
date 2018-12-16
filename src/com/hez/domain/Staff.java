package com.hez.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 员工登录
 **/
public class Staff {
	Integer id;
	String name;
	String password;
	String jobnumber;
	String gender;
	//已修改
	Date entrytime;
	String phone;
	String email;
	String rtxaccount; // RTX账号
	String notificationway; // 通知方式
	String notes;
	String position;
	Integer merit; // 功绩
	Integer hard; // 苦劳
	String points; // 积分

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobnumber() {
		return jobnumber;
	}

	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRtxaccount() {
		return rtxaccount;
	}

	public void setRtxaccount(String rtxaccount) {
		this.rtxaccount = rtxaccount;
	}

	public String getNotificationway() {
		return notificationway;
	}

	public void setNotificationway(String notificationway) {
		this.notificationway = notificationway;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public Date getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(Date entrytime) {
		this.entrytime = entrytime;
	}

	public Integer getMerit() {
		return merit;
	}

	public void setMerit(Integer merit) {
		this.merit = merit;
	}

	public Integer getHard() {
		return hard;
	}

	public void setHard(Integer hard) {
		this.hard = hard;
	}

	
}
