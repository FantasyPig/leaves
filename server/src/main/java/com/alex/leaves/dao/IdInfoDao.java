package com.alex.leaves.dao;

import com.alex.leaves.entity.IdInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface IdInfoDao {

    @Select("select * from id_info where name = #{name}")
    IdInfo getByName(@Param("name") String name);

    @Insert("insert into id_info (name,max,delta,step) values (#{name}, #{max}, #{delta}, #{step})")
    Integer insert(IdInfo idInfo);

    @Update("update id_info set max = max + delta where name = #{name}")
    Integer updateMax(String name);
}
