package com.hez.dao;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import com.hez.domain.StaffTeam;
import com.hez.provider.StaffTeamDynaSqlProvider;

public interface StaffTeamDao {
	/*
	 *  ����staff �� id �������� staffteam
	 * select * from staffteam where id = #{id}
	 */
		
	@Select("select * from staffteam where id = #{id} ")
	@Results({
		@Result(column="team_id",property="team",one=@One(select="com.hez.dao.TeamDao.selectTeamView",fetchType=FetchType.EAGER)),
		@Result(column="staff_id",property="staff",one=@One(select="com.hez.dao.StaffDao.selectStaffView",fetchType=FetchType.EAGER))
	})
	public List<StaffTeam> selectStaffTeamView(int id);
	
	/*
	 * ����staffid find teams
	 */
	@Select("select * from staffteam where staff_id = #{staffid} ")
	@Results({
		@Result(column="team_id",property="team",one=@One(select="com.hez.dao.TeamDao.selectTeamView",fetchType=FetchType.EAGER)),
	})
	public List<StaffTeam> selectTeams(int staffid);
}
