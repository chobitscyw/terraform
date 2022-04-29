package com.terraform.service;

import com.terraform.model.SysUser;

import java.util.List;

public interface UserService {

    public List<SysUser> findAll();

    public List<String> getCacheListByRedission();

    public List<String> getCacheListByRedis();
}
