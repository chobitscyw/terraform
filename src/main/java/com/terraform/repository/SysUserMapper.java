package com.terraform.repository;

import com.terraform.model.SysUser;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {

    SysUser findUserByName(@Param("userName") String userName);

    List<SysUser> findAll();

//    int deleteByExample(SysUserExample example);
//
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(SysUser record);
//
//    int insertSelective(SysUser record);
//
//    List<SysUser> selectByExample(SysUserExample example);
//
//    SysUser selectByPrimaryKey(Integer id);
//
//    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);
//
//    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);
//
//    int updateByPrimaryKeySelective(SysUser record);
//
//    int updateByPrimaryKey(SysUser record);
}