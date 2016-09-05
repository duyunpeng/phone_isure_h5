package phoneisure.core.pay.wechat;

import phoneisure.core.common.Constants;

import java.net.URLEncoder;

/**
 * Created by YJH on 2016/5/5.
 */
public class GetWeiXinOpenId {
    public static String getCodeRequest(String code) {
        return "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Constants.WECHAT_APPID + "&secret=" + Constants.WECHAT_APPSECRET + "&code=" + code + "&grant_type=authorization_code";
    }

    public static String urlEnodeUTF8(String str) {
        String result = str;
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
