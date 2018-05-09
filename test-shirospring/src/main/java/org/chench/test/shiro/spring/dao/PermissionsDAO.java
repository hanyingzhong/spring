package org.chench.test.shiro.spring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.chench.test.shiro.spring.bean.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsDAO {

	public final String TABLE_PERMISSIONS = "permissions"; // 角色权限表

	@Insert("insert into " + TABLE_PERMISSIONS + "(perm, name) values(#{perm},#{name})")
	public Integer addPerm(@Param("perm") String perm, @Param("name") String name);

	@Insert("insert into " + TABLE_PERMISSIONS + "(perm, name) values(#{perm},#{name})")
	public Integer addPerm2(Permission permission);

	@Insert("insert into " + TABLE_PERMISSIONS + "(perm, name) values(#{perm},#{name})")
	public Integer addPerm3(Map<String, String> map);
	
	@Select("select perm from " + TABLE_PERMISSIONS)
	public List<String> getAllPerm();	
}
