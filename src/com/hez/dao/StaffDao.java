package com.hez.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.hez.domain.Staff;
import com.hez.provider.OvertimeDynaSqlProvider;
import com.hez.provider.StaffDynaSqlProvider;

public interface StaffDao {
	@Select("select * from staff where jobnumber=#{jobnumber} and password=#{password}")
	public Staff staff_login(Staff staff);
	
	@Update("update staff set password = #{password} where id = #{id}")
	public int staffUpdatePassword(Staff staff);  //��staff���У������ṩ��staff.id�ҵ�Ҫupdate��������¼
	
	@Select("select * from staff where id=#{id}")//
	public Staff selectStaffView(int id);//�ҵ�ָ��������ʾ��Ҫ�޸ĵ�ҳ��
	
	@SelectProvider(type=StaffDynaSqlProvider.class,method="selectStaffOfStaffTeam")
	public int selectStaffOfStaffTeam(@Param("staffjobnumber")String staffjobnumber,
										@Param("staffname")String staffname);
	
	@Select("select * from staff where jobnumber=#{jobnumber}")
	public Staff selectJobnumber(Staff staff);

	@UpdateProvider(type=StaffDynaSqlProvider.class,method="updateStaffinfo")
	public int updateStaffinfo(Staff staff);
}
