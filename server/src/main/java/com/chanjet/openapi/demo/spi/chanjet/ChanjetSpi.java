package com.chanjet.openapi.demo.spi.chanjet;

import com.chanjet.openapi.demo.config.AppConfig;
import com.chanjet.openapi.sdk.java.ChanjetClient;
import com.chanjet.openapi.sdk.java.domain.GetAppAccessTokenContent;
import com.chanjet.openapi.sdk.java.domain.GetOrgAccessTokenContent;
import com.chanjet.openapi.sdk.java.domain.GetPermanentAuthCodeContent;
import com.chanjet.openapi.sdk.java.domain.GetTokenByPermanentCodeContent;
import com.chanjet.openapi.sdk.java.exception.ChanjetApiException;
import com.chanjet.openapi.sdk.java.request.*;
import com.chanjet.openapi.sdk.java.request.hsy.FindByEnterpriseIdRequest;
import com.chanjet.openapi.sdk.java.response.*;
import com.chanjet.openapi.sdk.java.response.hsy.FindByEnterpriseIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: zsc
 * @create: 2020/11/6 2:41 下午
 **/
@Component
public class ChanjetSpi {
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private ChanjetClient chanjetClient;

    /**
     * 获取应用凭证
     *
     * @param getAppAccessTokenContent
     * @return
     * @throws ChanjetApiException
     */
    public GetAppAccessTokenResponse getAppAccessToken(GetAppAccessTokenContent getAppAccessTokenContent)
            throws ChanjetApiException {
        GetAppAccessTokenRequest getAppAccessTokenRequest = new GetAppAccessTokenRequest();
        getAppAccessTokenRequest.setAppKey(appConfig.getAppKey());
        getAppAccessTokenRequest.setAppSecret(appConfig.getAppSecret());
        getAppAccessTokenRequest.setRequestUri("/auth/appAuth/getAppAccessToken");
        getAppAccessTokenRequest.setBizContent(getAppAccessTokenContent);
        return chanjetClient.execute(getAppAccessTokenRequest);
    }

    /**
     * 获取企业永久授权码
     *
     * @param getPermanentAuthCodeContent
     * @return
     * @throws ChanjetApiException
     */
    public GetPermanentAuthCodeResponse getPermanentAuthCode(GetPermanentAuthCodeContent getPermanentAuthCodeContent)
            throws ChanjetApiException {
        GetPermanentAuthCodeRequest getPermanentAuthCodeRequest = new GetPermanentAuthCodeRequest();
        getPermanentAuthCodeRequest.setAppKey(appConfig.getAppKey());
        getPermanentAuthCodeRequest.setAppSecret(appConfig.getAppSecret());
        getPermanentAuthCodeRequest.setRequestUri("/auth/orgAuth/getPermanentAuthCode");
        getPermanentAuthCodeRequest.setBizContent(getPermanentAuthCodeContent);
        return chanjetClient.execute(getPermanentAuthCodeRequest);
    }

    /**
     * 获取应用凭证
     *
     * @param getOrgAccessTokenContent
     * @return
     * @throws ChanjetApiException
     */
    public GetOrgAccessTokenResponse getOrgAccessToken(GetOrgAccessTokenContent getOrgAccessTokenContent)
            throws ChanjetApiException {
        GetOrgAccessTokenRequest getOrgAccessTokenRequest = new GetOrgAccessTokenRequest();
        getOrgAccessTokenRequest.setAppKey(appConfig.getAppKey());
        getOrgAccessTokenRequest.setAppSecret(appConfig.getAppSecret());
        getOrgAccessTokenRequest.setRequestUri("/auth/orgAuth/getOrgAccessToken");
        getOrgAccessTokenRequest.setBizContent(getOrgAccessTokenContent);
        return chanjetClient.execute(getOrgAccessTokenRequest);
    }

    /**
     * 使用code换取openToken
     *
     * @param code        授权码
     * @param redirectUri Oauth重定向地址
     * @return
     * @throws ChanjetApiException
     */
    public GetTokenResponse getToken(String code, String redirectUri) throws ChanjetApiException {
        GetTokenRequest getTokenRequest = new GetTokenRequest();
        getTokenRequest.setAppKey(appConfig.getAppKey());
        getTokenRequest.setAppSecret(appConfig.getAppSecret());
        getTokenRequest.setRequestUri("/auth/getToken");
        getTokenRequest.addQueryParam("redirectUri", redirectUri);
        getTokenRequest.addQueryParam("code", code);
        getTokenRequest.addQueryParam("grantType", "authorization_code");
        getTokenRequest.addQueryParam("appKey", appConfig.getAppKey());

        return chanjetClient.execute(getTokenRequest);
    }

    /**
     * 使用用户永久授权码获取openToken
     *
     * @param getTokenByPermanentCodeContent
     * @return
     * @throws ChanjetApiException
     */
    public GetTokenByPermanentCodeResponse getTokenByPermanentCode(GetTokenByPermanentCodeContent getTokenByPermanentCodeContent)
            throws ChanjetApiException {
        GetTokenByPermanentCodeRequest getTokenByPermanentCodeRequest = new GetTokenByPermanentCodeRequest();
        getTokenByPermanentCodeRequest.setAppKey(appConfig.getAppKey());
        getTokenByPermanentCodeRequest.setAppSecret(appConfig.getAppSecret());
        getTokenByPermanentCodeRequest.setRequestUri("/auth/token/getTokenByPermanentCode");
        getTokenByPermanentCodeRequest.setBizContent(getTokenByPermanentCodeContent);
        return chanjetClient.execute(getTokenByPermanentCodeRequest);
    }

    /**
     * 使用refreshToken刷新openToken
     *
     * @param refreshToken
     * @return
     * @throws ChanjetApiException
     */
    public RefreshTokenResponse refreshToken(String refreshToken) throws ChanjetApiException {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setAppKey(appConfig.getAppKey());
        refreshTokenRequest.setAppSecret(appConfig.getAppSecret());
        refreshTokenRequest.setRequestUri("/auth/refreshToken");
        refreshTokenRequest.addQueryParam("refreshToken", refreshToken);
        refreshTokenRequest.addQueryParam("grantType", "refresh_token");
        refreshTokenRequest.addQueryParam("appKey", appConfig.getAppKey());
        return chanjetClient.execute(refreshTokenRequest);
    }

    /**
     * 获取用户信息
     *
     * @param openToken 开放平台token
     * @return
     * @throws ChanjetApiException
     */
    public UserResponse user(String openToken) throws ChanjetApiException {
        UserRequest userRequest = new UserRequest();
        userRequest.setAppKey(appConfig.getAppKey());
        userRequest.setAppSecret(appConfig.getAppSecret());
        userRequest.setOpenToken(openToken);
        userRequest.setRequestUri("/accounting/cia/api/v1/user");
        return chanjetClient.execute(userRequest);
    }


    /**
     * 获取好生意账套列表
     *
     * @param openToken 开放平台token
     * @param queryType 查询类型
     * @return
     * @throws ChanjetApiException
     */
    public FindByEnterpriseIdResponse findByEnterpriseId(String openToken, String queryType) throws ChanjetApiException {
        FindByEnterpriseIdRequest findByEnterpriseIdRequest = new FindByEnterpriseIdRequest();
        findByEnterpriseIdRequest.setAppKey(appConfig.getAppKey());
        findByEnterpriseIdRequest.setAppSecret(appConfig.getAppSecret());
        findByEnterpriseIdRequest.setOpenToken(openToken);
        findByEnterpriseIdRequest.setRequestUri("/accounting/openapi/cc/book/findByEnterpriseId");
        findByEnterpriseIdRequest.addQueryParam("queryType", queryType);
        return chanjetClient.execute(findByEnterpriseIdRequest);
    }

}
