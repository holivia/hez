package com.hez.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hez.dao.TeamDao;

import com.hez.domain.Team;
import com.hez.domain.TeamSub;
import com.hez.service.TeamService;
@Service("teamService")
public class TeamServiceImpl implements TeamService {
	@Autowired
	TeamDao teamDao;
	public int selectTeamOfStaffTeam(String teamname) {
		// TODO Auto-generated method stub
		return teamDao.selectTeamOfStaffTeam(teamname);
	}

	public Team selectTeamView(int id) {
		// TODO Auto-generated method stub
		return teamDao.selectTeamView(id);
	}

	public int deleteTeam(int id) {
		// TODO Auto-generated method stub
		return teamDao.deleteTeam(id);
	}

	public int insertTeam(Team team) {
		// TODO Auto-generated method stub
		return teamDao.insertTeam(team);
	}

	
	public int updateTeam(Team team) {
		// TODO Auto-generated method stub
		return teamDao.updateTeam(team);
	}

	public int deleteTeamSub(int id) {
		// TODO Auto-generated method stub
		return teamDao.deleteTeamSub(id);
	}

	public int insetTeamSub(TeamSub teamSub) {
		// TODO Auto-generated method stub
		return teamDao.insetTeamSub(teamSub);
	}

	public int updateTeamSub(TeamSub teamSub) {
		// TODO Auto-generated method stub
		return teamDao.updateTeamSub(teamSub);
	}

	public List<TeamSub> selectTeamSubView(int id) {
		// TODO Auto-generated method stub
		return teamDao.selectTeamSubsView(id);
	}

	public List<Team> selectTeams(Team team) {
		// TODO Auto-generated method stub
		return teamDao.selectTeams(team);
	}

	public int deleteTeamSubOfTeam() {
		// TODO Auto-generated method stub
		return teamDao.deleteTeamSubOfTeam();
	}

	
	

}
