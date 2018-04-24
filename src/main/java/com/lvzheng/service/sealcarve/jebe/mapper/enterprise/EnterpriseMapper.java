package com.lvzheng.service.sealcarve.jebe.mapper.enterprise;

import com.lvzheng.service.sealcarve.jebe.Enterprise;
import com.lvzheng.service.sealcarve.util.mybatis.INLanguageDriver;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnterpriseMapper {


    @Select("SELECT E.enterpriseID,E.name,E.transactorType,E.transactorName,E.transactorPhone,E.modifyUserType,E.modifyUserID,E.modifyDate, "
            + "EXT.dataValue AS unifiedSocialCreditCode, EXT.id AS extID, "
            + "R.id AS relationID, "
            + "P.id AS legalPersonID,P.phoneNum AS legalPersonTel,P.idType AS legalPersonIDType,P.idNum AS legalPersonIDNum, "
            + "P.residenceAddress AS legalPersonResidenceAddress,P.name AS legalPersonName "
            + "FROM lv_enterprise AS E "
            + "LEFT JOIN lv_enterprise_role_relation AS R ON E.enterpriseId=R.enterpriseId AND R.roleType='legalPerson' "
            + "LEFT JOIN lv_enterprise_person AS P ON R.roleId = P.id "
            + "LEFT JOIN lv_enterprise_ext AS EXT ON E.enterpriseId=EXT.enterpriseId AND EXT.dataKey='unifiedSocialCreditCode' "
            + "WHERE E.enterpriseId IN (#{enterpriseIds})")
    @Lang(INLanguageDriver.class)
    List<Enterprise> getEnterpriseByIds(@Param("enterpriseIds") List<Long> enterpriseIds);


    @Update("UPDATE lv_enterprise SET "
            + "name=#{name},"
            + "transactorType=#{transactorType},"
            + "transactorName=#{transactorName},"
            + "transactorPhone=#{transactorPhone},"
            + "modifyUserType=#{modifyUserType},"
            + "modifyUserID=#{modifyUserID},"
            + "modifyDate=#{modifyDate} "
            + "WHERE enterpriseID = #{enterpriseID}")
    int updateEnterpriseBase(Enterprise enterprise);


    @Update("UPDATE lv_enterprise_ext SET "
            + "dataValue=#{unifiedSocialCreditCode} "
            + "WHERE id = #{extID}")
    int updateEnterpriseEXT(Enterprise enterprise);


    @Insert("INSERT INTO lv_enterprise_ext "
            + "(enterpriseId,dataKey,dataValue,createDate) VALUES "
            + "(#{enterpriseID}, 'unifiedSocialCreditCode', #{unifiedSocialCreditCode}, #{modifyDate})")
    @Options(useGeneratedKeys = true, keyProperty = "extID", keyColumn = "id")
    int insertEnterpriseEXT(Enterprise enterprise);


    @Update("UPDATE lv_enterprise_person SET "
            + "name=#{legalPersonName}, "
            + "phoneNum=#{legalPersonTel}, "
            + "idType=#{legalPersonIDType}, "
            + "idNum=#{legalPersonIDNum}, "
            + "residenceAddress=#{legalPersonResidenceAddress} "
            + "WHERE id = #{legalPersonID}")
    int updateEnterprisePerson(Enterprise enterprise);


    @Insert("INSERT INTO lv_enterprise_person "
            + "(name,phoneNum,idType,idNum,residenceAddress,createDate) VALUES "
            + "(#{legalPersonName}, #{legalPersonTel}, #{legalPersonIDType}, #{legalPersonIDNum}, #{legalPersonResidenceAddress}, #{modifyDate})")
    @Options(useGeneratedKeys = true, keyProperty = "legalPersonID", keyColumn = "id")
    int insertEnterprisePerson(Enterprise enterprise);


    @Insert("INSERT INTO lv_enterprise_role_relation "
            + "(enterpriseId,roleId,roleType,createDate,status) VALUES "
            + "(#{enterpriseID}, #{legalPersonID}, 'legalPerson', #{modifyDate}, 1)")
    @Options(useGeneratedKeys = true, keyProperty = "relationID", keyColumn = "id")
    int insertEnterpriseRoleRelation(Enterprise enterprise);

}
