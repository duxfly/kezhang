package com.lvzheng.service.sealcarve.mapper;

import com.lvzheng.service.sealcarve.domain.SealCarveTask;
import com.lvzheng.service.sealcarve.domain.SealCarveTaskRemark;
import com.lvzheng.service.sealcarve.domain.SealCarveTaskStatus;
import com.lvzheng.service.sealcarve.util.mybatis.INLanguageDriver;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface SealCarveTaskMapper {

    /**
     * 根据ID获得任务
     * @param id 任务ID（主键ID）
     * @return
     */
    @Select("SELECT * FROM t_seal_carve_task WHERE id = #{id}")
    SealCarveTask getTaskByID(@Param("id") long id);

    /**
     * 根据条件获取任务
     * @param status 状态
     * @param startTime 开始时间 >=
     * @param endTime 结束时间 <=
     * @param enterpriseName 企业名（模糊查询）
     * @param agentName 经办人名（模糊查询）
     * @param offset 游标
     * @param count 记录数
     * @return
     */
    @Select("<script> "
            + "SELECT * FROM t_seal_carve_task "
            + " <where> "
            + " <if test=\"status > -1\">status = #{status}</if> "
            + " <if test=\"startTime != null\"> AND add_time &gt;= #{startTime}</if> "
            + " <if test=\"endTime != null\"> AND add_time &lt;= #{endTime}</if> "
            + " <if test=\"enterpriseName != null and enterpriseName != ''\"> AND enterprise_name LIKE #{enterpriseName}</if> "
            + " <if test=\"agentName != null and agentName != ''\"> AND agent_name LIKE #{agentName}</if> "
            + " </where> "
            + " ORDER BY id DESC "
            + " LIMIT #{offset},#{count}"
            + " </script> ")
    List<SealCarveTask> getTasks(@Param("status") int status,
                                 @Param("startTime") Date startTime,
                                 @Param("endTime") Date endTime,
                                 @Param("enterpriseName") String enterpriseName,
                                 @Param("agentName") String agentName,
                                 @Param("offset") int offset,
                                 @Param("count") int count);

    /**
     * 获得总记录数
     * @param status 状态
     * @param startTime 开始时间 >=
     * @param endTime 结束时间 <=
     * @param enterpriseName 企业名（模糊查询）
     * @param agentName 经办人名（模糊查询）
     * @return
     */
    @Select("<script> "
            + "SELECT COUNT(1) FROM t_seal_carve_task "
            + " <where> "
            + " <if test=\"status > -1\">status = #{status}</if> "
            + " <if test=\"startTime != null\"> AND add_time &gt;= #{startTime}</if> "
            + " <if test=\"endTime != null\"> AND add_time &lt;= #{endTime}</if> "
            + " <if test=\"enterpriseName != null and enterpriseName != ''\"> AND enterprise_name LIKE #{enterpriseName}</if> "
            + " <if test=\"agentName != null and agentName != ''\"> AND agent_name LIKE #{agentName}</if> "
            + " </where> "
            + " </script> ")
    int getTaskCount(@Param("status") int status,
                     @Param("startTime") Date startTime,
                     @Param("endTime") Date endTime,
                     @Param("enterpriseName") String enterpriseName,
                     @Param("agentName") String agentName);

    /**
     * 更新任务的经办人信息
     * @param taskID 任务ID
     * @param agentName 经办人名
     * @return
     */
    @Update("UPDATE t_seal_carve_task SET agent_name=#{agentName} WHERE id =#{taskID}")
    int updateTaskAgentName(@Param("taskID") long taskID, @Param("agentName") String agentName);

    /**
     * 添刻意任务
     * @param task SealCarveTask
     */
    @Insert("INSERT INTO t_seal_carve_task "
            + "(enterprise_id, master_task_id, order_id, product_id, appointment_time, status, add_time, enterprise_name, agent_name) "
            + "VALUES(#{enterpriseID}, #{masterTaskID}, #{orderID}, #{productID}, #{appointmentTime}, #{status}, #{addTime}, #{enterpriseName}, #{agentName})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addTask(SealCarveTask task);

    /**
     * 获得指定任务的所有备注
     * @param taskID 任务ID
     * @return
     */
    @Select("SELECT * FROM t_seal_carve_task_remark WHERE task_id = #{taskID}")
    List<SealCarveTaskRemark> getAllTaskRemarks(@Param("taskID") long taskID);

    /**
     * 添加任务备注
     * @param remark SealCarveTaskRemark
     */
    @Insert("INSERT INTO t_seal_carve_task_remark "
            + "(task_id, add_time, emp_id, remark) "
            + "VALUES(#{taskID}, #{addTime}, #{empID}, #{remark})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void addTaskRemark(SealCarveTaskRemark remark);

    /**
     * 更新任务的状态
     * @param taskID 任务ID
     * @param status 状态
     * @return
     */
    @Update("UPDATE t_seal_carve_task SET status=#{status} WHERE id =#{taskID}")
    int updateTaskStatus(@Param("taskID") long taskID, @Param("status") int status);

    /**
     * 批量更新任务状态
     * @param taskIds 任务ID
     * @param status 状态
     * @return
     */
    @Lang(INLanguageDriver.class)
    @Update("UPDATE t_seal_carve_task SET status=#{status} WHERE id IN (#{taskIds})")
    int updateTaskStatusMulti(@Param("taskIds") long[] taskIds, @Param("status") int status);

    /**
     * 添加状态记录
     * @param status SealCarveTaskStatus
     */
    @Insert("INSERT INTO t_seal_carve_task_status "
            + "(task_id, status, add_time, emp_id) "
            + "VALUES(#{taskID}, #{status}, #{addTime}, #{empID})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void addTaskStatusLog(SealCarveTaskStatus status);

    /**
     * 获得所有刻章状态记录
     * @param taskID 任务ID
     * @return
     */
    @Select("SELECT * FROM t_seal_carve_task_status WHERE task_id = #{taskID}")
    List<SealCarveTaskRemark> getAllTaskStatus(@Param("taskID") long taskID);
}
