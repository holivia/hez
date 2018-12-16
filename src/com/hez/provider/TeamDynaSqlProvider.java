package com.hez.provider;

import java.util.List;

import org.apache.ibatis.jdbc.SQL;

import com.hez.domain.Team;
import com.hez.domain.TeamSub;

public class TeamDynaSqlProvider {
	public String selectTeamOfStaffTeam(final String teamname){
		return new SQL(){{
			SELECT("id");
			FROM("team");
			WHERE("name = #{teamname}");
		}}.toString();
	}
	
	

	public String selectTeams(final Team team){
		return new SQL(){{
			SELECT("*");
			FROM("team");
			if(team.getName()!=null && !team.getName().equals("")){
				WHERE("name = #{teamname}");
			}
			if(team.getResponsible()!=null && !team.getResponsible().equals("")){
				WHERE("responsible = #{responsible}");
			}
			
		}}.toString();
	}
	

	public String updateTeam(final Team team){
		return new SQL(){{
			UPDATE("team");
			if(team.getName()!=null && !team.getName().equals("")){
				SET("name = #{name}");
			}
			if(team.getPlace()!=null && !team.getPlace().equals("")){
				SET("place = #{place}");
			}
			if(team.getCode()!=null && !team.getCode().equals("")){
				SET("code = #{code}");
			}
			WHERE("id = #{id}");
		}}.toString();
	}
	
	
	
	
	public String insertTeam(final Team team){   
		return new SQL(){{
			INSERT_INTO("team");
			if(team.getName()!=null && !team.getName().equals("")){
				VALUES("name ", "#{name }");
			}
			if(team.getPlace()!=null && !team.getPlace().equals("")){
				VALUES("place", "#{place }");
			}
			if(team.getCode()!=null && !team.getCode().equals("")){
				VALUES("code ", "#{code }");
			}
		}}.toString();
	}
	
	
	public String insetTeamSub(final TeamSub teamSub){
		return new SQL(){{
			INSERT_INTO("teamSub");
			if(teamSub.getName()!=null && !teamSub.getName().equals("")){
				VALUES("name ", "#{name }");
			}
			if(teamSub.getTeam_id()!=null && teamSub.getTeam_id()!=0){
				VALUES("team_id ", "#{team_id }");
			}
			if(teamSub.getPlace()!=null && !teamSub.getPlace().equals("")){
				VALUES("place", "#{place }");
			}
			if(teamSub.getCode()!=null && !teamSub.getCode().equals("")){
				VALUES("code ", "#{code }");
			}
		}}.toString();
	}
	
	public String updateTeamSub(final TeamSub teamSub){
		return new SQL(){{
			UPDATE("teamSub");
			if(teamSub.getName()!=null && !teamSub.getName().equals("")){
				SET("name = #{name}");
			}
			if(teamSub.getPlace()!=null && !teamSub.getPlace().equals("")){
				SET("place = #{place}");
			}
			if(teamSub.getCode()!=null && !teamSub.getCode().equals("")){
				SET("code = #{code}");
			}
			WHERE("id = #{id}");
		}}.toString();
	}
		
	
	
}
