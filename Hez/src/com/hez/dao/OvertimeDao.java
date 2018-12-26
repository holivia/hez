package com.hez.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.FetchType;

import com.hez.domain.Overtime;
import com.hez.provider.OvertimeDynaSqlProvider;
import com.hez.provider.StaffTeamDynaSqlProvider;

public interface OvertimeDao {
	
	/*【工时查询】
	 * 先查staffteam表，再查overtime表
	 * 
	 * 工号、姓名	staffteam
	 * 团队	staffteam
	 * 查询日期		overtime
	 * 
	 * select * from overtime join staffteam on overtime.staffteam_id = staffteam.id
	 * 
	 * select * from overtime where staffteam_id 
		in(select id from staffteam where staff_id =1)
		
	 *
	 *one=@One
	 *根据 staffteam_id在staffteam表查到一个记录（这里staffteam_id是staffteam表的主键，所以肯定是onetoOne关系）
	 *many=@Many
	 *一个id在xx表中 查到一组记录
	 */
	
	@SelectProvider(type=OvertimeDynaSqlProvider.class,method="selectOvertimeByPage")	//分页查询	
	@Results({
		@Result(column="staffteam_id",property="staffteam",one=@One(select="com.hez.dao.StaffTeamDao.selectStaffTeamView",fetchType=FetchType.EAGER))
	})
	public List<Overtime> selectOvertimeByPage(Map params);//user、pageNow、pageSize封装在Map中

	
	/*
	 *  饼图
	 	select team_id,sum(duration)as totalduration  from overtime
		where date>='2018-5-11' and date<='2019-1-1'
	 	group by team_id
	 	
	 	散点图
	 	select staff_id,sum(duration)as totalduration ,count(duration) as times from overtime
		group by staff_id

	 	排序图
	 	select staff_id,sum(duration)as totalduration,count(duration) as times  from overtime
		group by staff_id
		order by totalduration asc,
		times desc 
	 */
	@SelectProvider(type=OvertimeDynaSqlProvider.class,method="selectForPie")	//分页查询	
	@Results({
		@Result(column="totalduration",property="duration"),
		@Result(column="team_id",property="team",one=@One(select="com.hez.dao.TeamDao.selectTeamView",fetchType=FetchType.EAGER))		
	})
	public List<Overtime> selectForPie(Map params);
	
	@SelectProvider(type=OvertimeDynaSqlProvider.class,method="selectForScatter_List")	//分页查询	
	@Results({
		@Result(column="times",property="mealcoupon"),
		@Result(column="totalduration",property="duration"),
		@Result(column="staff_id",property="staff",one=@One(select="com.hez.dao.StaffDao.selectStaffView",fetchType=FetchType.EAGER))
	})
	public List<Overtime> selectForScatter_List(Map params);
	
	
	
	/*
	 * 	我的近三个月工时统计
	 *  select convert(varchar,month(date))+'月' as monthDate ,team_id,sum(duration) as totalduration
		from overtime	
		where staff_id=1 and date>(select dateadd(month,-3,getdate()))
		group by month(date), team_id	
		order by monthDate	
	 */
	@Select("select convert(varchar,month(date))+'月' as monthDate , team_id, sum(duration) as totalduration "+
			"from overtime "+
			"where staff_id=1 and date>(select dateadd(month,-3,getdate()))"+
			"group by month(date), team_id "+
			"order by monthDate,team_id")
	@Results({
		@Result(column="monthDate",property="mealcoupon"),
		@Result(column="totalduration",property="duration"),
		@Result(column="team_id",property="team",one=@One(select="com.hez.dao.TeamDao.selectTeamView",fetchType=FetchType.EAGER))
	})
	public List<Overtime> selectForDurationt3Month();
	
	
	
	/*
	 *  我的工时统计
	 *  select convert(varchar,year(date))+'-'+convert(varchar,month(date)) as year_month,team_id,sum(duration)  as totalduration
		from overtime	
		where staff_id=1
		group by year(date),month(date), team_id
		order by year(date),month(date), team_id
		我的请假统计		
		select convert(varchar,year(date))+'-'+convert(varchar,month(date)) as year_month,team_id,sum(dayoff)  as totaldayoff
		from overtime	
		where staff_id=1
		group by year(date),month(date), team_id
		order by year(date),month(date), team_id		
		我的任务单统计
		select convert(varchar,year(date))+'-'+convert(varchar,month(date)) as year_month,team_id,sum(assignment)  as totalassignment
		from overtime	
		where staff_id=1
		group by year(date),month(date), team_id
		order by year(date),month(date), team_id	
		
		notes:按照别名year_month排序，不能达到效果；应该是：order by year(date),month(date)；
	 */	
	//我的工时统计
	@Select("select convert(varchar,year(date))+'年'+convert(varchar,month(date))+'月' as year_month, team_id, sum(duration)  as totalduration " +
			"from overtime	where staff_id=1 group by year(date),month(date), team_id "+	//team_id后面一定要记得加空格" "
			"order by year(date),month(date), team_id")
	@Results({
		@Result(column="year_month",property="mealcoupon"),
		@Result(column="totalduration",property="duration"),
		@Result(column="team_id",property="team",one=@One(select="com.hez.dao.TeamDao.selectTeamView",fetchType=FetchType.EAGER))
	})
	public List<Overtime> selectForDuration();
	
	
	//我的请假统计
	@Select("select convert(varchar,year(date))+'年'+convert(varchar,month(date))+'月' as year_month, team_id, sum(dayoff)  as totaldayoff "+
			"from overtime	where staff_id=1 group by year(date),month(date), team_id "+
			"order by year(date),month(date), team_id")
	@Results({
		@Result(column="year_month",property="mealcoupon"),
		@Result(column="totaldayoff",property="dayoff"),
		@Result(column="team_id",property="team",one=@One(select="com.hez.dao.TeamDao.selectTeamView",fetchType=FetchType.EAGER))
	})	
	public List<Overtime> selectForDayoff();
	
	//我的任务单统计
	@Select("select convert(varchar,year(date))+'年'+convert(varchar,month(date))+'月' as year_month, team_id, sum(assignment)  as totalassignment "+
			"from overtime where staff_id=1 group by year(date),month(date), team_id "+
			"order by year(date),month(date), team_id") 
	@Results({
		@Result(column="year_month",property="mealcoupon"),
		@Result(column="totalassignment",property="assignment"),
		@Result(column="team_id",property="team",one=@One(select="com.hez.dao.TeamDao.selectTeamView",fetchType=FetchType.EAGER))
	})
	public List<Overtime> selectForAssignment();
	
	
	
	
	
	
	
	
	@SelectProvider(type=com.hez.provider.OvertimeDynaSqlProvider.class,method="selectOvertimeByPageTwo")
	@Results({
		@Result(column="team_id",property="team",one=@One(select="com.hez.dao.TeamDao.selectTeamById",fetchType=FetchType.EAGER)),
		@Result(column="staff_id",property="staff",one=@One(select="com.hez.dao.StaffDao.selectStaffById",fetchType=FetchType.EAGER))
	})
	public  List<Overtime> selectOvertimeByPageTwo(Map params);
	
	
	@InsertProvider(type=com.hez.provider.OvertimeDynaSqlProvider.class,method="insertovertime")
	@Results({
		@Result(column="team_id",property="team",one=@One(select="com.hez.dao.TeamDao.selectTeamById",fetchType=FetchType.EAGER)),
		@Result(column="staff_id",property="staff",one=@One(select="com.hez.dao.StaffDao.selectStaffById",fetchType=FetchType.EAGER))
	})
	public int insertOvertime(HashMap params);
	
	@UpdateProvider(type=com.hez.provider.OvertimeDynaSqlProvider.class,method="updataOverTime")
	public  int updataOverTime(HashMap params);
	
	@Delete("delete from overtime where id=#{id}")
	public int deleteOvertime(Integer id);

	@Select("select * from overtime where id=#{id}")
	@Results({
			@Result(column="team_id",property="team",one=@One(select="com.hez.dao.TeamDao.selectTeamById",fetchType=FetchType.EAGER)),
			@Result(column="staff_id",property="staff",one=@One(select="com.hez.dao.StaffDao.selectStaffById",fetchType=FetchType.EAGER))
	
	})
	public Overtime selectByOvertimeId(int id);
}
