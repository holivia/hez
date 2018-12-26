package com.hez.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hez.domain.StaffTeam;
import com.hez.domain.Team;

public interface StaffTeamService {
	public List<StaffTeam> selectStaffTeamView(int id);
	public List<StaffTeam> selectTeams(int staffid);
	public int insertStaffTeam(int staff_id, int team_id);
	public int deleteStaffTeam(int id);
	
	
	public int updateTeamid(@Param("teamid")Integer teamid,@Param("staffteamid")Integer staffteamid);
	public List<StaffTeam> selectStaffTeamById(int id);
	public int selectId(@Param("teamid")int teamid,@Param("staffid")int staffid);
}
