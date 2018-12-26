package com.hez.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.FetchType;

import com.hez.domain.Team;
import com.hez.domain.TeamSub;
import com.hez.provider.TeamDynaSqlProvider;
/*
 * insert team 不管子节点
 * updae  team 不管子节点
 * delete team 本身删除、子节点删除
 * 
 * insert teamSub 本身插入，父节点id插入
 * updae  teamSub 不管父节点
 * delete teamSub 不父节点
 * 
 */

public interface TeamDao {
	@Select("select * from team where id=#{id}")	// #{id}:Team类的id属性
	public Team selectTeamView(int id);//找到指定数据显示在要修改的页面	

	@SelectProvider(type=TeamDynaSqlProvider.class,method="selectTeamOfStaffTeam")
	public int selectTeamOfStaffTeam(String teamname);
	
	
	
	@Select("select * from teamSub where team_id=#{id}")	
	public List<TeamSub> selectTeamSubsView(int id);	
	
	@Select("select * from teamSub where id=#{id}")	// #{id}:Team类的id属性
	public TeamSub selectTeamSubView(int id);//找到指定数据显示在要修改的页面	
	
	@Select("select id from team where code=#{code}")	
	public int selectTeamCode(String code);	
	
	
	
	@SelectProvider(type=TeamDynaSqlProvider.class,method="selectTeams")	
	@Results({
				//1：n
		@Result(column="id",property="teamSubs",many=@Many(select="com.hez.dao.TeamDao.selectTeamSubsView",fetchType=FetchType.EAGER))
	})
	public List<Team> selectTeams(Team team);
	
	@UpdateProvider(type=TeamDynaSqlProvider.class,method="updateTeam")
	public int updateTeam(Team team);
	
	@Delete("delete from Team where id=#{id}")  
	public int deleteTeam(int id);
	@Delete("delete from TeamSub where team_id is null")
	public int deleteTeamSubOfTeam();
	
	
	
	@InsertProvider(type=TeamDynaSqlProvider.class,method="insertTeam") 
	public int insertTeam(Team team);
	
	
	
	@UpdateProvider(type=TeamDynaSqlProvider.class,method="updateTeamSub")
	public int updateTeamSub(TeamSub teamSub);
	
	@Delete("delete from TeamSub where id=#{id}")  
	public int deleteTeamSub(int id);
	
	@InsertProvider(type=TeamDynaSqlProvider.class,method="insetTeamSub") 
	public int insetTeamSub(TeamSub teamSub);
	
	
	//张
//	@SelectProvider(type=com.hez.provider.TeamDynaSqlProvider.class,method="selectTeam")
//	public List<Team> selectTeam(Team team); 
	
	@Select("select * from team where id=#{id}")
	public Team selectTeamById(int id);
	
	@Select("select * from team")
	public List<Team> findAllTeam();
//	@InsertProvider(type=com.hez.provider.OvertimeDynaSqlProvider.class,method="insertTeam")
//	public int insertTeam(Team team);
	
}
