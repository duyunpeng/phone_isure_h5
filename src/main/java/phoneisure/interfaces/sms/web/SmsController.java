package phoneisure.interfaces.sms.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import phoneisure.core.redis.RedisService;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.service.account.AccountService;
import phoneisure.interfaces.shared.web.BaseController;
import phoneisure.interfaces.shared.web.JsonMessage;

/**
 * Created by YJH on 2016/4/26.
 */
@Controller
@RequestMapping("/sms")
public class SmsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisService redisService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/send_reset_password")
    @ResponseBody
    public JsonMessage sendResetPassword(String userName) {
        JsonMessage jsonMessage = new JsonMessage();
        try {
            if (!redisService.exists(userName)) {
                if (null == accountService.searchByAccountName(userName)) {
                    jsonMessage.setCode("1");
                    jsonMessage.setMessage("该号码未注册!");
                    jsonMessage.setData(null);
                } else {
                    String code = CoreStringUtils.randomNum(6);
//                    smsSender.send(userName, code, SmsTemplate.RESETPWD);
                    redisService.addCache(userName, code);
                    jsonMessage.setCode("0");
                    jsonMessage.setMessage("发送成功!");
                    jsonMessage.setData(null);
                }
            } else {
                jsonMessage.setCode("1");
                jsonMessage.setMessage("验证码未过期!");
                jsonMessage.setData(null);
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
            jsonMessage.setCode("1");
            jsonMessage.setMessage("发送失败!");
            jsonMessage.setData(null);
        }
        return jsonMessage;
    }
}
