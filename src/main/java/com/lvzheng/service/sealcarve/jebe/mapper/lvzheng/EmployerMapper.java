package com.lvzheng.service.sealcarve.jebe.mapper.lvzheng;

import com.lvzheng.service.sealcarve.jebe.Employer;
import com.lvzheng.service.sealcarve.util.mybatis.INLanguageDriver;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerMapper {

    /**
     * 跟据钉钉code获取员工实体
     * @param ddUserID DingTalk UserID
     * @return
     */
    @Select("SELECT E.empid, E.empname, EXT.dataValue AS dingTalkCode"
            + " FROM t_employers AS E INNER JOIN t_employers_ext AS EXT ON E.empid=EXT.empid"
            + " WHERE EXT.dataValue = #{ddUserID} AND EXT.dataKey='dingdingId'")
    Employer getEmployerByDingTalkCode(@Param("ddUserID") String ddUserID);


    /**
     * 跟据empID获取员工实体
     * @param empID 员工ID
     * @return
     */
    @Select("SELECT empid, empname FROM t_employers WHERE empid = #{empID}")
    Employer getEmployerByID(@Param("empID") long empID);


    /**
     * 跟据员工ID集合批量获取员工
     * @param empIds 员工ID
     * @return
     */
    @Lang(INLanguageDriver.class)
    @Select("SELECT empid, empname FROM t_employers WHERE empid IN (#{empIds})")
    List<Employer> getEmployerByIds(@Param("empIds") List<Long> empIds);

}
