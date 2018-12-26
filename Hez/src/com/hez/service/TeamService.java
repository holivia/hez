package com.hez.service;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.hez.domain.Team;
import com.hez.domain.TeamSub;
import com.hez.provider.TeamDynaSqlProvider;


public interface TeamService {
	public Team selectTeamView(int id);
	public TeamSub selectTeamSubView(int id);
	public int selectTeamOfStaffTeam(String teamname);
	public int selectTeamCode(String code);	
	public List<TeamSub> selectTeamSubsView(int id);
	public List<Team> selectTeams(Team team);
	public int updateTeam(Team team);
	public int deleteTeam(int id);
	public int insertTeam(Team team);
	public int deleteTeamSubOfTeam();
	public int insetTeamSub(TeamSub teamSub);
	public int updateTeamSub(TeamSub teamSub);
	public int deleteTeamSub(int id);
	
	public List<Team> findAllTeam();
	public Team selectTeamById(int id);
	
}
