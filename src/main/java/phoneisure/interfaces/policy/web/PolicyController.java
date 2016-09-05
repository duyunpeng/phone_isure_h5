package phoneisure.interfaces.policy.web;

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
import phoneisure.core.api.ApiPagination;
import phoneisure.core.common.SharedCommand;
import phoneisure.core.enums.PolicyStatus;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.core.exception.NoLoginException;
import phoneisure.core.util.CoreHttpUtils;
import phoneisure.domain.model.account.Account;
import phoneisure.domain.model.policy.Policy;
import phoneisure.domain.model.policy.PolicyCount;
import phoneisure.domain.service.policy.IPolicyService;
import phoneisure.domain.service.policy.command.CreatePolicyCommand;
import phoneisure.domain.service.policy.command.ListPolicyCommand;
import phoneisure.interfaces.shared.web.AlertMessage;
import phoneisure.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by YJH on 2016/4/25.
 */
@Controller
@RequestMapping("/policy")
public class PolicyController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IPolicyService policyService;

    @RequestMapping(value = "/list")
    public ModelAndView pagination(ListPolicyCommand command, HttpSession session) {
        ApiPagination<Policy> pagination = null;
        try {
            Account account = CoreHttpUtils.getSessionAccount(session);
            command.setMerchant(account.getId());
            pagination = policyService.pagination(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            AlertMessage alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/page/queryPolicy", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            return new ModelAndView("redirect:/logout");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("/page/queryPolicy", "pagination", pagination).addObject("command", command);
    }

    @RequestMapping(value = "/return_list")
    public ModelAndView returnPagination(ListPolicyCommand command, HttpSession session) {
        ApiPagination<Policy> pagination = null;
        try {
            Account account = CoreHttpUtils.getSessionAccount(session);
            command.setMerchant(account.getId());
            command.setBack(true);
            pagination = policyService.pagination(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            AlertMessage alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/page/returnPolicy", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            return new ModelAndView("redirect:/logout");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("/page/returnPolicy", "pagination", pagination).addObject("command", command);
    }

    @RequestMapping(value = "/claim_list")
    public ModelAndView claimPagination(ListPolicyCommand command, HttpSession session) {
        ApiPagination<Policy> pagination = null;
        try {
            Account account = CoreHttpUtils.getSessionAccount(session);
            command.setMerchant(account.getId());
            command.setClaim(true);
            pagination = policyService.pagination(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            AlertMessage alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/page/claimPolicy", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            return new ModelAndView("redirect:/logout");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("/page/claimPolicy", "pagination", pagination).addObject("command", command);
    }

    @RequestMapping(value = "/add_policy", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreatePolicyCommand command) {
        return new ModelAndView("/page/addPolicy");
    }

    @RequestMapping(value = "/add_policy", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreatePolicyCommand command, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, HttpSession session, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/page/addPolicy", "command", command);
        }
        AlertMessage alertMessage;
        try {
            Account account = CoreHttpUtils.getSessionAccount(session);
            command.setMerchant(account.getId());
            policyService.create(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/page/addPolicy", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            return new ModelAndView("redirect:/logout");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ModelAndView("redirect:/");
        }
        alertMessage = new AlertMessage(this.getMessage("default.create.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/policy/list");
    }

    @RequestMapping(value = "/return_policy")
    public ModelAndView returnPolicy(SharedCommand command, RedirectAttributes redirectAttributes, HttpSession session, Locale locale) {
        AlertMessage alertMessage;
        try {
            CoreHttpUtils.getSessionAccount(session);
            policyService.returnPolicy(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/policy/list");
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            return new ModelAndView("redirect:/logout");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ModelAndView("redirect:/");
        }
        alertMessage = new AlertMessage(this.getMessage("policy.return.policy.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/policy/list");
    }

    @RequestMapping(value = "/count")
    @ResponseBody
    public PolicyCount policyCount(HttpSession session) {
        PolicyCount policyCount;
        try {
            Account account = CoreHttpUtils.getSessionAccount(session);
            policyCount = policyService.policyCount(account.getId());
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            return null;
        }
        return policyCount;
    }
}
