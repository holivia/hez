package com.hez.domain;

import java.util.Date;

public class TeamSub {
	Integer id;
	Integer team_id;
	String name;
	String code;//�Ŷӱ���
	private String place;//��������
	private String responsible;//������
	private Date createTime;//����ʱ��
	public Integer getTeam_id() {
		return team_id;
	}
	public void setTeam_id(Integer teamId) {
		team_id = teamId;
	}
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
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
}
