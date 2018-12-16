package com.hez.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hez.domain.Team;
import com.hez.domain.TeamSub;
import com.hez.service.TeamService;



@Controller
public class TeamController {

	@Autowired 
	@Qualifier("teamService")
	TeamService teamService;
	

	@RequestMapping("/select_team_teamSub") 
	public String selectTeams(Team team,HttpSession session,Model model){	
		List<Team> teamList = teamService.selectTeams(team);
		model.addAttribute("teamList",teamList);
		return "jsp/team.jsp";
	}
	
	@RequestMapping("/updateTeam") 
	public String updateTeam(Team team,String teamSubid,HttpSession session,Model model){	
		teamService.updateTeam(team);
		return "redirect:select_team_teamSub";
	}
	
	@RequestMapping("/deleteTeam") 
	public String deleteTeam(int id,HttpSession session,Model model){
		teamService.deleteTeam(id); //delete from Team where id=?
		teamService.deleteTeamSubOfTeam(); //delete from teamSub where team_id is null    解释： 对于一条记录：team删除后，由于数据库设置的约束关系，teamSub表的team_id字段会强制设为空
		return "redirect:select_team_teamSub";
	}
	
	@RequestMapping("/insetTeam") 
	public String insetTeam(Team team,HttpSession session,Model model){
		teamService.insertTeam(team);
		return "redirect:select_team_teamSub";
	}
	
	@RequestMapping("/insetTeamSub") 
	public String insetTeamSub(int teamid,TeamSub teamSub,HttpSession session,Model model){				
		teamSub.setTeam_id(teamid);
		teamService.insetTeamSub(teamSub);
		return "redirect:select_team_teamSub";
	}
	
	@RequestMapping("/updateTeamSub") 
	public String updateTeamSub(TeamSub teamSub,HttpSession session,Model model){
		teamService.updateTeamSub(teamSub);
		return "redirect:select_team_teamSub";
	}
	
	@RequestMapping("/deleteTeamSub") 
	public String deleteTeamSub(int id,HttpSession session,Model model){		
		teamService.deleteTeamSub(id); //delete from teamSub where id = ?
		return "redirect:select_team_teamSub";
	}
	
	
	
}
