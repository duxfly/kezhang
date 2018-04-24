package com.lvzheng.service.sealcarve.util;

import com.dingtalk.oapi.lib.aes.DingTalkJsApiSingnature;
import com.dingtalk.open.client.ServiceFactory;
import com.dingtalk.open.client.api.model.corp.CorpUserBaseInfo;
import com.dingtalk.open.client.api.model.corp.CorpUserDetail;
import com.dingtalk.open.client.api.model.corp.JsapiTicket;
import com.dingtalk.open.client.api.service.corp.CorpConnectionService;
import com.dingtalk.open.client.api.service.corp.CorpUserService;
import com.dingtalk.open.client.api.service.corp.JsapiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DingTalkHelper {

    private static final Logger logger = LoggerFactory.getLogger(DingTalkHelper.class);

    // 企业 ID ，在“微应用设置”页面能看到，相当于 OAuth2 中的 app_id
    private static final String CORP_ID = "ding034efc1905471752";

    // 在“微应用设置”页面能看到，相当于 OAuth2 中的 app_secret ，换access_token 时会用到
    private static final String CORP_SECRET = "D5TGXBG0_4esj7FLib5FysPlw_dyJzsGi6XZmrdlgajW9YR0YjZPIJq21lKRI7lQ";

    // 应用的 ID 标识，在具体的应用设置页面能找到
    private static final String AGENT_ID = "150934552";

    // 调整到30分钟
    private static final long TOKEN_CACHE_TIME = 1000 * 60 * 30;

    private static Token token = null;

    private static final Map<String, UserInfo> userMap = new HashMap<>();


    /**
     * 获得token & ticket
     * @return token token
     * @throws Exception error
     */
    public static Token getToken() throws Exception {
        long curTime = System.currentTimeMillis();
        if (token != null && curTime - token.getTime() < TOKEN_CACHE_TIME) {
            return token;
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        CorpConnectionService corpConnectionService = serviceFactory.getOpenService(CorpConnectionService.class);
        String accToken = corpConnectionService.getCorpToken(CORP_ID, CORP_SECRET);
        logger.info("get dingding token success:" + accToken);

        JsapiService jsapiService = serviceFactory.getOpenService(JsapiService.class);
        JsapiTicket jsapiTicket = jsapiService.getJsapiTicket(accToken, "jsapi");
        String jsTicket = jsapiTicket.getTicket();
        logger.info("get dingding jsTicket success:" + jsTicket);

        token = new Token();
        token.setToken(accToken);
        token.setTicket(jsTicket);
        token.setTime(System.currentTimeMillis());

        return token;
    }

    /**
     * 签名
     * @param ticket ticket
     * @param nonce 随机值
     * @param timeStamp 时间戳
     * @param url 当前页面url
     * @return 签名后的字符串
     * @throws Exception error
     */
    public static String sign(String ticket, String nonce, long timeStamp, String url) throws Exception {
        return DingTalkJsApiSingnature.getJsApiSingnature(url, nonce, timeStamp, ticket);
    }

    /**
     * 根据免登授权码查询免登用户userId
     * @param code dingTalkCode
     * @return CorpUserBaseInfo
     * @throws Exception error
     */
    public static CorpUserBaseInfo getUserInfo(String code) throws Exception {
        CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
        return corpUserService.getUserinfo(getToken().getToken(), code);
    }

    /**
     * 获取成员
     * @param userID 钉钉里配置的用户ID
     * @return CorpUserDetail
     * @throws Exception error
     */
    public static CorpUserDetail getUser(String userID) throws Exception {
        CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
        return corpUserService.getCorpUser(getToken().getToken(), userID);
    }

    /**
     * 跟据code获取用户ID, code有效期30分钟
     * @param code code
     * @return 钉钉里配置的用户ID
     * @throws Exception error
     */
    public static String getUserID(String code) throws Exception {
        UserInfo userInfo = userMap.get(code);
        if (userInfo != null) {
            long now = System.currentTimeMillis();
            if (now - userInfo.getAddTime().getTime() < TOKEN_CACHE_TIME) { //30分钟有效
                return userInfo.getUserID();
            }
        }

        CorpUserBaseInfo baseInfo = getUserInfo(code);
        if (baseInfo == null) {
            return null;
        }

        userMap.put(code, new UserInfo(code, baseInfo.getUserid(), new Date()));
        return baseInfo.getUserid();
    }

    /**
     * 计算当前请求的jsapi的签名数据
     * 如果签名数据是通过ajax异步请求的话，签名计算中的url必须是给用户展示页面的url
     * @param url 当前页面URL
     * @return
     */
    public static Config getConfig(String url) throws Exception {
        Token tk = getToken();

        url = URLDecoder.decode(url, "utf-8");
        String nonce = UUID.randomUUID().toString().replaceAll("-", "");
        long timeStamp = System.currentTimeMillis() / 1000;

        String signature = sign(tk.getTicket(), nonce, timeStamp, url);

        Config conf = new Config();
        conf.setToken(tk.getToken());
        conf.setTicket(tk.getTicket());
        conf.setSignature(signature);
        conf.setNonce(nonce);
        conf.setTimeStamp(timeStamp);
        conf.setCorpID(CORP_ID);
        conf.setAgentID(AGENT_ID);

        return conf;
    }


    public static class Config {
        private String token;
        private String ticket;
        private String signature;
        private String nonce;
        private long timeStamp;
        private String corpID;
        private String agentID;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getNonce() {
            return nonce;
        }

        public void setNonce(String nonce) {
            this.nonce = nonce;
        }

        public long getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getCorpID() {
            return corpID;
        }

        public void setCorpID(String corpID) {
            this.corpID = corpID;
        }

        public String getAgentID() {
            return agentID;
        }

        public void setAgentID(String agentID) {
            this.agentID = agentID;
        }
    }

    private static class Token {
        private String token;
        private String ticket;
        private long time;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }

    private static class UserInfo {
        private String code;
        private String userID;
        private Date addTime;

        public UserInfo(String code, String userID, Date addTime) {
            this.code = code;
            this.userID = userID;
            this.addTime = addTime;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public Date getAddTime() {
            return addTime;
        }

        public void setAddTime(Date addTime) {
            this.addTime = addTime;
        }
    }
}
