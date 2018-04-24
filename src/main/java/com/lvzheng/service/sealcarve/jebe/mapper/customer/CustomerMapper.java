package com.lvzheng.service.sealcarve.jebe.mapper.customer;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerMapper {

    @Select("SELECT id FROM `t_customer` WHERE name LIKE #{customerName}")
    List<Long> getEnterpriseIdsByCustomerName(@Param("code") String customerName);

}
