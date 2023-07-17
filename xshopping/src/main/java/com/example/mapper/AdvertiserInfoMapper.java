package com.example.mapper;

import com.example.entity.AdvertiserInfo;
import com.example.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 公告相关的Mapper
 */
public interface AdvertiserInfoMapper extends Mapper<AdvertiserInfo> {
    /*根据公告标题模糊查询*/
    List<AdvertiserInfo> findByName(@Param("name") String name);

}