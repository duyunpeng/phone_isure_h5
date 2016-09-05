package phoneisure.core.pay.wechat;

import phoneisure.core.common.Constants;

import java.net.URLEncoder;

/**
 * Created by YJH on 2016/5/5.
 */
public class GetWeiXinCode {

    public static String getCodeRequest(String no) {
        return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + urlEnodeUTF8(Constants.WECHAT_APPID) + "&redirect_uri=" + urlEnodeUTF8(Constants.WECHAT_REDIRECT_URL + "?rechargeNo=" + no) + "&response_type=code&scope=" + Constants.WECHAT_REDIRECT_SCOPE + "&state=STATE#wechat_redirect";
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
