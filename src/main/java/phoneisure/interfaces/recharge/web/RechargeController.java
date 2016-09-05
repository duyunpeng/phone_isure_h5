package phoneisure.interfaces.recharge.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;
import phoneisure.core.common.Constants;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.core.exception.NoLoginException;
import phoneisure.core.pay.wechat.*;
import phoneisure.core.pay.wechat.util.Signature;
import phoneisure.core.pay.wechat.util.XMLParser;
import phoneisure.core.util.CoreHttpUtils;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.core.util.HttpUtil;
import phoneisure.core.util.IPUtil;
import phoneisure.domain.model.recharge.Recharge;
import phoneisure.domain.service.recharge.IRechargeService;
import phoneisure.domain.service.recharge.command.RechargeCommand;
import phoneisure.interfaces.shared.web.BaseController;
import phoneisure.interfaces.shared.web.JsonMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YJH on 2016/5/5.
 */
@Controller
@RequestMapping("/recharge")
public class RechargeController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IRechargeService rechargeService;

    @RequestMapping(value = "/recharge", method = RequestMethod.GET)
    public ModelAndView recharge() {
        return new ModelAndView("/page/recharge");
    }

    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage recharge(RechargeCommand command, HttpServletRequest request, HttpSession session) {

        JsonMessage jsonMessage = new JsonMessage();
        if (null == command.getMoney()) {
            jsonMessage.setCode("1");
            jsonMessage.setMessage("money不能为空");
            return jsonMessage;
        }
        try {
            String ip = IPUtil.getIpAddress(request);
            logger.info("--------------------------------------------------------------->" + ip);
            command.setIpAddress(ip);
            command.setMerchant(CoreHttpUtils.getSessionAccount(session).getId());
            Recharge data = rechargeService.recharge(command);
            jsonMessage.setMessage("成功");
            jsonMessage.setCode("0");
            jsonMessage.setData(data);
            return jsonMessage;
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            jsonMessage.setCode("1");
            jsonMessage.setMessage(e.getMessage());
            return jsonMessage;
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            jsonMessage.setCode("1");
            jsonMessage.setMessage("下单失败");
            return jsonMessage;
        }
    }

    @RequestMapping(value = "/wechat_pay")
    public void wechatPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(CoreHttpUtils.getClientIP(request));
        String rechargeNo = request.getParameter("rechargeNo");
        String code = request.getParameter("code");
        if (null != code && !CoreStringUtils.isEmpty(code)) {//如果code不等于空
            Recharge recharge = null;
            try {
                recharge = rechargeService.searchByNo(rechargeNo);

                String json = HttpUtil.SendGET(GetWeiXinOpenId.getCodeRequest(code));
                JSONObject jsonObject = JSON.parseObject(json);
                String openId = jsonObject.getString("openid");

                if (null != openId && !CoreStringUtils.isEmpty(openId)) {
                    logger.info("openID>>>>>>>>>>>>>>>>>>>" + openId);
                    String body = "订单号：" + rechargeNo;
                    String detail = "描述";
                    UnifiedRequest unifiedRequest = new UnifiedRequest(body, detail, rechargeNo, recharge.getRechargeMoney().multiply(new BigDecimal(100)).intValue(), recharge.getIpAddress(), Constants.WECHAT_NOTIFY_URL, openId);
                    UnifiedResponse unifiedResponse = null;
                    try {
                        String sign = Signature.getSign(unifiedRequest.toMap());
                        unifiedRequest.setSign(sign);
                        XStream xStream = new XStream(new DomDriver("utf-8", new XmlFriendlyNameCoder("-_", "_")));
                        xStream.alias("xml", UnifiedRequest.class);
                        String s = HttpUtil.wechatUnified(Constants.WECHAT_UNIFIED_URL, xStream.toXML(unifiedRequest));
                        unifiedResponse = (UnifiedResponse) XMLParser.getObjFromXML(s, UnifiedResponse.class);
                        if (unifiedResponse != null) {
                            unifiedResponse.setTime_stamp(System.currentTimeMillis() / 1000);
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("appId", unifiedResponse.getAppid());
                            map.put("package", "prepay_id=" + unifiedResponse.getPrepay_id());
                            map.put("nonceStr", unifiedResponse.getNonce_str());
                            map.put("timeStamp", unifiedResponse.getTime_stamp());
                            map.put("signType", "MD5");
                            unifiedResponse.setSign(Signature.getSign(map));
                        }
                        WechatPayHandle wechatPayHandle = new WechatPayHandle();
                        wechatPayHandle.setAppId(unifiedResponse.getAppid());
                        wechatPayHandle.setTimeStamp(unifiedResponse.getTime_stamp());
                        wechatPayHandle.setNonceStr(unifiedResponse.getNonce_str());
                        wechatPayHandle.setPackages("prepay_id=" + unifiedResponse.getPrepay_id());
                        wechatPayHandle.setSignType("MD5");
                        wechatPayHandle.setSign(unifiedResponse.getSign());
                        request.setAttribute("weChatPay", wechatPayHandle);
                        request.getRequestDispatcher("/recharge/wechat_order_success").forward(request, response);
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    response.sendRedirect("/logged");
                } else {
                    response.sendRedirect("/recharge/wechat_pay?id=" + rechargeNo);
                }
            } catch (ApiRemoteCallFailedException e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.info("code为空，重定向到" + GetWeiXinCode.getCodeRequest(rechargeNo) + "获取code");
            response.sendRedirect(GetWeiXinCode.getCodeRequest(rechargeNo));
        }
    }

    @RequestMapping(value = "/wechat_order_success")
    public ModelAndView wechatPaySuccess() {
        return new ModelAndView("page/wechatPay");
    }
}
