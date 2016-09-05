package phoneisure.interfaces.merchant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.core.exception.NoLoginException;
import phoneisure.core.util.CoreHttpUtils;
import phoneisure.domain.model.account.Account;
import phoneisure.domain.model.merchant.Merchant;
import phoneisure.domain.service.merchant.IMerchantService;
import phoneisure.domain.service.merchant.command.UpdatePasswordCommand;
import phoneisure.interfaces.shared.web.AlertMessage;
import phoneisure.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by YJH on 2016/4/25.
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IMerchantService merchantService;

    @RequestMapping(value = "/agent_list")
    @ResponseBody
    public List<Merchant> agentList() {
        List<Merchant> merchantList;
        try {
            merchantList = merchantService.searchAgent();
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            return null;
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return null;
        }
        return merchantList;
    }

    @RequestMapping(value = "/update_password", method = RequestMethod.GET)
    public ModelAndView updatePassword(@ModelAttribute("command") UpdatePasswordCommand command) {
        return new ModelAndView("/page/updatePassword", "command", "command");
    }

    @RequestMapping(value = "/update_password", method = RequestMethod.POST)
    public ModelAndView updatePassword(@Valid @ModelAttribute("command") UpdatePasswordCommand command, BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes, HttpSession session, Locale locale) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/page/updatePassword");
        }

        AlertMessage alertMessage;

        try {
            Account account = CoreHttpUtils.getSessionAccount(session);
            command.setMerchant(account.getId());
            merchantService.updatePassword(command);
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            return new ModelAndView("redirect:/logout");
        } catch (ApiRemoteCallFailedException e) {
            if (e.getResponse().getCode() == ApiReturnCode.ERROR_PASSWORD_NOT_EQ) {
                alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("merchant.oldPassword.Not.eq.message", null, locale));
                return new ModelAndView("/page/updatePassword", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            }
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/page/updatePassword", AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/policy/list");
    }

    @RequestMapping(value = "/get_money")
    @ResponseBody
    public String getMoney(HttpSession sessions) {
        try {
            Account account = CoreHttpUtils.getSessionAccount(sessions);
            return merchantService.searchByID(account.getId()).getMoney().toString();
        } catch (NoLoginException e) {
            return null;
        } catch (ApiRemoteCallFailedException e) {
            return null;
        }
    }
}
