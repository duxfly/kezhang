package com.lvzheng.service.sealcarve.vo;

import com.lvzheng.service.sealcarve.vo.input.SealCarveTaskQueryVO;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;


public class SealCarveTaskQueryVOTest {

    @Test
    public void testValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        SealCarveTaskQueryVO vo = new SealCarveTaskQueryVO();
        vo.setAgentName("abc中文");
        vo.setBeginDate(new Date());
        vo.setEnterpriseName("enterprise中文");
        vo.setEndDate(new Date());
        vo.setPageIndex(1);
        vo.setPageSize(100);
        vo.setStatus(1);

        Set<ConstraintViolation<SealCarveTaskQueryVO>> constraintViolations = validator.validate(vo);
        for (ConstraintViolation<SealCarveTaskQueryVO> constraintViolation : constraintViolations) {
            System.out.println(constraintViolation.getMessage());
        }
    }

    @Test
    public void testString() {
        System.out.println(StringEscapeUtils.escapeHtml3("\"\"中文中文"));
    }

}