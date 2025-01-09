package com.example.demo.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.domein.MstUser;
import com.example.demo.model.form.LoginForm;
import com.example.demo.model.form.RegistForm;

@Mapper
public interface MstUserMapper {
	
	@Select(value = "SELECT * FROM mst_user WHERE user_name = #{userName} AND password = #{password}")
	MstUser findByUserNameAndPassword(LoginForm form);
	
	@Select(value = "SELECT * FROM mst_user WHERE user_name = #{userName}")
	MstUser findByUserName(RegistForm form);
	
	@Select(value = "SELECT * FROM mst_user WHERE password = #{password}")
	MstUser findByUserPassword(RegistForm form);
	
	@Insert(value = "INSERT INTO mst_user (user_name, password, full_name, is_admin) "
					+ "VALUES (#{userName}, #{password}, #{fullName}, 0)")
	int save(RegistForm form);

}
