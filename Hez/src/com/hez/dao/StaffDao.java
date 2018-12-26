package com.hez.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.FetchType;

import com.hez.domain.Staff;
import com.hez.domain.StaffTeam;
import com.hez.provider.OvertimeDynaSqlProvider;
import com.hez.provider.StaffDynaSqlProvider;

public interface StaffDao {
	@Select("select * from staff where jobnumber=#{jobnumber} and password=#{password}")
	public Staff staff_login(Staff staff);
	
	@Update("update staff set password = #{password} where id = #{id}")
	public int staffUpdatePassword(Staff staff);  //在staff表中，根据提供的staff.id找到要update的那条记录
	
	@Select("select * from staff where id=#{id}")//
	public Staff selectStaffView(int id);//找到指定数据显示在要修改的页面
	
	@SelectProvider(type=StaffDynaSqlProvider.class,method="selectStaffOfStaffTeam")
	public int selectStaffOfStaffTeam(@Param("staffjobnumber")String staffjobnumber,
										@Param("staffname")String staffname);
	
	@Select("select * from staff where jobnumber=#{jobnumber}")
	public Staff selectJobnumber(Staff staff);

	@UpdateProvider(type=StaffDynaSqlProvider.class,method="updateStaffinfo")
	public int updateStaffinfo(Staff staff);
	
	
	
	@Select("select * from staffteam where staff_id=#{id}")
	@Results({
		@Result(column="team_id",property="team",one=@One(select="com.hez.dao.TeamDao.selectTeamById",fetchType=FetchType.EAGER))		
	})
	public List<StaffTeam> selectStaff(Integer id);

	
	@Select("select * from staff where id=#{id}")
	public Staff selectStaffById(Integer id);
	
	
}
