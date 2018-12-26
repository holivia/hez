package com.hez.domain;

public class StaffTeam {
	Integer id;
	Staff staff;
	Team team;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
