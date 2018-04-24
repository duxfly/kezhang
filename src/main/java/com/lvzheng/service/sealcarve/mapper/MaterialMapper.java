package com.lvzheng.service.sealcarve.mapper;

import com.lvzheng.service.sealcarve.domain.Material;
import com.lvzheng.service.sealcarve.domain.MaterialConfig;
import com.lvzheng.service.sealcarve.domain.MaterialFlow;
import com.lvzheng.service.sealcarve.util.mybatis.INLanguageDriver;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialMapper {

    /**
     * 获得所有的'材料'
     * @return
     */
    @Select("SELECT * FROM t_material")
    List<Material> getAllMaterial();

    /**
     * 获得材料配置
     * @param ids 配置id
     * @return
     */
    @Lang(INLanguageDriver.class)
    @Select("SELECT * FROM t_material_config WHERE id IN (#{ids})")
    List<MaterialConfig> getMaterialConfigByIds(@Param("ids") int... ids);

    /**
     * 分页获得"我的材料"
     * @param empID 员工ID
     * @param enterpriseName 企业名（模糊查询）
     * @param offset 游标
     * @param count 记录数
     * @return
     */
    @Select("<script> "
            + " SELECT * FROM t_material_flow "
            + " <where> "
            + " emp_id = #{empID}"
            + " <if test=\"enterpriseName != null and enterpriseName != ''\"> AND enterprise_name LIKE #{enterpriseName}</if> "
            + " </where> "
            + " ORDER BY id DESC LIMIT #{offset},#{count}"
            + " </script> ")
    List<MaterialFlow> getMaterialFlow(@Param("empID") long empID,
                                       @Param("enterpriseName") String enterpriseName,
                                       @Param("offset") int offset,
                                       @Param("count")int count);

    /**
     * 获得我的材料总数量
     * @param empID 员工ID
     * @return
     */
    @Select("<script> "
            + " SELECT COUNT(0) FROM t_material_flow "
            + " <where> "
            + " emp_id = #{empID}"
            + " <if test=\"enterpriseName != null and enterpriseName != ''\"> AND enterprise_name LIKE #{enterpriseName}</if> "
            + " </where> "
            + " </script> ")
    int getMaterialFlowCount(@Param("empID") long empID, @Param("enterpriseName") String enterpriseName);

    /**
     * 获得指定公司的所有材料交接记录
     * @param enterpriseID 企业ID
     * @return
     */
    @Select("SELECT * FROM t_material_flow WHERE enterprise_id = #{enterpriseID} ORDER BY id DESC")
    List<MaterialFlow> getMaterialFlowByEnterpriseID(@Param("enterpriseID") long enterpriseID);

    /**
     * 添加我的认领
     * @param flow MaterialFlow
     */
    @Insert("INSERT INTO t_material_flow"
            + "(claim_time, enterprise_id, enterprise_name, emp_id, material_ids) "
            + "VALUES(#{claimTime}, #{enterpriseID}, #{enterpriseName}, #{empID}, #{materialIds})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addMaterialFlow(MaterialFlow flow);
}
