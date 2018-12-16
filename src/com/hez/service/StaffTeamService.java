package com.hez.service;

import java.util.List;

import com.hez.domain.StaffTeam;
import com.hez.domain.Team;

public interface StaffTeamService {
	public List<StaffTeam> selectStaffTeamView(int id);
	public List<StaffTeam> selectTeams(int staffid);
	
}
