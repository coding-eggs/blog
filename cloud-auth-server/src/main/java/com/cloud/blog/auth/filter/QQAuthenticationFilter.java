package com.cloud.blog.auth.filter;

import com.cloud.blog.auth.config.pojo.QQAccessToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: blog
 * @description: qq登录过滤器
 * @author: Ailuoli
 * @create: 2019-05-27 14:31
 **/
public class QQAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    /**
     * response_type 返回类型
     */
    private final static String RESPONSE_TYPE = "code";

    /**
     * grant_type 由腾讯提供
     */
    private final static String GRANT_TYPE = "authorization_code";

    /**
     * client_id 由腾讯提供(即AppId)
     */
    private static final String CLIENT_ID = "101583722";

    /**
     * client_secret 由腾讯提供(即App Key)
     */
    private final static String CLIENT_SECRET = "e7a157c1c7228e7d08c332b3d6afeedc";

    /**
     * redirect_uri 腾讯回调地址
     */
    private final static String REDIRECT_URI = "https://ailuoli.cn/qqlogin/success";

    /**
     * 获取 access_token_url 的 API
     */
    private final static String ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token";

    /**
     * 获取 OpenID url地址
     */
    private final static String OPENID_URL = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     * 获取 token 的地址拼接
     */
    private final static String TOKEN_ACCESS_API = "%s?grant_type=%s&client_id=%s&client_secret=%s&code=%s&redirect_uri=%s";


    public QQAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl,"GET"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        UsernamePasswordAuthenticationToken authenticationToken = null;

        String code = request.getParameter(RESPONSE_TYPE);

        String accessTokenURL = String.format(TOKEN_ACCESS_API,ACCESS_TOKEN_URL,GRANT_TYPE,CLIENT_ID,CLIENT_SECRET,code,REDIRECT_URI);

        QQAccessToken qqAccessToken = getQQAccessToken(accessTokenURL);

        String openId = getOpenId(qqAccessToken.getAccessToken());
        if(null!= openId)
        {
            authenticationToken = new UsernamePasswordAuthenticationToken(qqAccessToken.getAccessToken(),openId);
        }
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }


    /**
     * 开始请求获取QQToken
     * @return
     */
    private QQAccessToken getQQAccessToken(String accessTokenURL){
        Assert.notNull(accessTokenURL,"accessTokenURL不能为空");
        RestTemplate template = new RestTemplate();
        QQAccessToken qqAccessToken = new QQAccessToken();
        String[] results = template.getForObject(accessTokenURL, String.class).split("&");
        if (results.length == 3){
            String accessToken = results[0].replace("access_token=", "");
            Integer expiresIn = Integer.valueOf(results[1].replace("expires_in=", ""));
            String refreshToken = results[2].replace("refresh_token=", "");
            qqAccessToken.setAccessToken(accessToken);
            qqAccessToken.setExpiresIn(expiresIn);
            qqAccessToken.setRefreshToken(refreshToken);
        }
        return qqAccessToken;
    }

    /**
     * 获取用户的唯一OpenId
     * @return
     */
    private String getOpenId(String accessToken){
        RestTemplate template = new RestTemplate();
        String openIdResult = template.getForObject(String.format(OPENID_URL,accessToken), String.class);
        Pattern pattern = Pattern.compile("\"openid\":\"(.*)\"");
        assert openIdResult != null;
        Matcher matcher = pattern.matcher(openIdResult);
        if (matcher.find()){
            return matcher.group(1);
        }
        return null;
    }
}

