package com.hez.domain;

import java.util.Date;
import java.util.List;

public class Team {
	//id or teamSubs
	Integer id;
	List<TeamSub> teamSubs;
	
	public List<TeamSub> getTeamSubs() {
		return teamSubs;
	}
	public void setTeamSubs(List<TeamSub> teamSubs) {
		this.teamSubs = teamSubs;
	}
	String name;
	String code;//�Ŷӱ���
	private String place;//��������
	private String responsible;//������
	private Date createTime;//����ʱ��
	private String notes;//����ʱ��
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getResponsible() {
		return responsible;
	}
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
