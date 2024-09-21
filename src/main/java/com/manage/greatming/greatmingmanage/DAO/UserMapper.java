package com.manage.greatming.greatmingmanage.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from Users")
    List<User> findAllUsers();

    @Select("select * from Users where id=#{id}")
    User findById(long id);

    @Select("select balance from Users where id=#{id}")
    double findBalance(long id);

    @Select("select * from Users where name=#{name}")
    User findByName(String name);

    @Update("update Users set name=#{name},passwd=#{passwd},tag=#{tag},ranks=#{ranks},company=#{company},kills=#{kills},attendance=#{attendance},balance=#{balance},userpic=#{userpic},updatetime=NOW() where id=#{id}")
    void updateUser(User user);

    @Update("update Users set balance=balance+#{balance} where id=#{id}")
    void updateBalance(long id, double balance);

    @Update("update Users set attendance=attendance+#{attendance} where id=#{id}")
    void updateAttendance(long id, double attendance);

    @Update("update Users set kills=kills+#{kills} where id=#{id}")
    void updateKills(long id, double kills);

    @Insert("insert into Users(name, passwd, tag, ranks, company, kills, attendance, balance, userpic, createtime, updatetime)" + 
    " values(#{name}, #{passwd}, #{tag}, #{ranks}, #{company}, #{kills}, #{attendance}, #{balance}, #{userpic}, now(), now())")
    void addNewUser(User user);

    @Delete("delete from Users where id=#{id}")
    void deleteById(long id);

    List<User> userPageList(String company);




}
