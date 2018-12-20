package com.hez.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hez.dao.StaffTeamDao;
import com.hez.domain.StaffTeam;
import com.hez.service.StaffTeamService;

@Service("staffTeamService")
public class StaffTeamServiceImpl implements StaffTeamService{
	@Autowired 
	StaffTeamDao StaffTeamDao;

	public List<StaffTeam> selectStaffTeamView(int id) {
		// TODO Auto-generated method stub
		return StaffTeamDao.selectStaffTeamView(id);
	}

	public List<StaffTeam> selectTeams(int staffid) {
		// TODO Auto-generated method stub
		return StaffTeamDao.selectTeams(staffid);
	}

	public int insertStaffTeam(int staff_id, int team_id) {
		// TODO Auto-generated method stub
		return StaffTeamDao.insertStaffTeam(staff_id, team_id);
	}

	public int deleteStaffTeam(int id) {
		// TODO Auto-generated method stub
		return StaffTeamDao.deleteStaffTeam(id);
	}

	
	
	
}
