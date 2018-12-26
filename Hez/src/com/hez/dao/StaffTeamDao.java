package com.hez.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;
import com.hez.domain.StaffTeam;
import com.hez.provider.StaffTeamDynaSqlProvider;

public interface StaffTeamDao {
	/*
	 *  根据staff 的 id 查找所有 staffteam
	 * select * from staffteam where id = #{id}
	 */
		
	@Select("select * from staffteam where id = #{id} ")
	@Results({
		@Result(column="team_id",property="team",one=@One(select="com.hez.dao.TeamDao.selectTeamView",fetchType=FetchType.EAGER)),
		@Result(column="staff_id",property="staff",one=@One(select="com.hez.dao.StaffDao.selectStaffView",fetchType=FetchType.EAGER))
	})
	public List<StaffTeam> selectStaffTeamView(int id);
	
	/*
	 * 根据staffid find teams
	 */
	@Select("select * from staffteam where staff_id = #{staffid} ")
	@Results({
		@Result(column="team_id",property="team",one=@One(select="com.hez.dao.TeamDao.selectTeamView",fetchType=FetchType.EAGER)),
	})
	public List<StaffTeam> selectTeams(int staffid);
	
	@Insert("insert into staffteam values( #{staff_id},#{team_id} )")
	public int insertStaffTeam(@Param("staff_id")int staff_id, @Param("team_id")int team_id);
	
	@Delete("delete from staffteam where team_id=#{id}")  
	public int deleteStaffTeam(int id);
	
	
	
	
	
	
	
	
	
	
	
	@Select("select * from staffteam where id = #{id} ")
	@Results({
		@Result(column="team_id",property="team",one=@One(select="com.hez.dao.TeamDao.selectTeamById",fetchType=FetchType.EAGER)),
		@Result(column="staff_id",property="staff",one=@One(select="com.hez.dao.StaffDao.selectStaffById",fetchType=FetchType.EAGER))
	})
	public List<StaffTeam> selectStaffTeamById(int id);

	@Select("select id from staffteam where  team_id = #{teamid} and staff_id = #{staffid}")  
	public int selectId(@Param("teamid")int teamid,@Param("staffid")int staffid);
//type=com.hez.provider.OvertimeDynaSqlProvider.class,method="updateTeamid"
	
	@Update("update staffteam set team_id = #{teamid} where id=#{staffteamid}")
    public int updateTeamid(@Param("teamid")Integer teamid,@Param("staffteamid")Integer staffteamid);
	
}
