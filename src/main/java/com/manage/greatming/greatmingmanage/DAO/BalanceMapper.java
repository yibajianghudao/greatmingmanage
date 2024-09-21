package com.manage.greatming.greatmingmanage.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface BalanceMapper {
    
    @Select("select * from balance")
    List<Balance> findAllBalance();

    @Select("select * from balance where userid=#{userid}")
    Balance findByUserId(long userid);

    @Insert("insert into balance(userid, sum, company, ranks, other, description)" + 
    " values(#{userid}, #{sum}, #{company}, #{ranks}, #{other}, #{description})")
    void addNewBalance(Balance balance);

    @Delete("delete from balance where id=#{balanceid}")
    void deleteByBalanceId(long balanceid);

    

}
