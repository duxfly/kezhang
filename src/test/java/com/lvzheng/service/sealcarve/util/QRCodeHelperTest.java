package com.lvzheng.service.sealcarve.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class QRCodeHelperTest {

    @Test
    public void createBase64Code() throws Exception {
        String url = "http://test";
        String code = QRCodeHelper.createBase64Code("url", 200);
        System.out.println(code);
    }

}