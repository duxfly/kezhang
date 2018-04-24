package com.lvzheng.service.sealcarve.util;

import org.junit.Assert;
import org.junit.Test;

public class DingTalkHelperTest {

    @Test
    public void getConfig() throws Exception {
        DingTalkHelper.Config conf = DingTalkHelper.getConfig("http://www.lz.com");

        Assert.assertNotNull(conf.getAgentID());
        Assert.assertNotNull(conf.getCorpID());
        Assert.assertNotNull(conf.getNonce());
        Assert.assertNotNull(conf.getSignature());
        Assert.assertNotNull(conf.getTicket());
        Assert.assertNotNull(conf.getTimeStamp());
        Assert.assertNotNull(conf.getToken());
    }

}