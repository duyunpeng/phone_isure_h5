package phoneisure.interfaces.auth.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.common.Constants;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.core.exception.ExistException;
import phoneisure.core.exception.NoFoundException;
import phoneisure.core.util.CoreHttpUtils;
import phoneisure.domain.model.account.Account;
import phoneisure.domain.service.auth.IAuthService;
import phoneisure.domain.service.auth.command.LoginCommand;
import phoneisure.domain.service.auth.command.RegisterCommand;
import phoneisure.domain.service.account.command.ResetPasswordCommand;
import phoneisure.interfaces.shared.web.AlertMessage;
import phoneisure.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

/**
 * Created by YJH on 2016/4/5.
 */
@Controller
public class AuthController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public IAuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@ModelAttribute("command") LoginCommand command) {
        return new ModelAndView("/login", "command", command);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@Valid @ModelAttribute("command") LoginCommand command, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, HttpServletRequest request,
                              HttpServletResponse response, HttpSession session, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/login", "command", command);
        }
        AlertMessage alertMessage;

//        boolean flag;

//        try {
//            flag = command.getVerificationCode().equals(request.getSession().getAttribute("code").toString());
//        } catch (Exception e) {
//            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("login.verificationCode.Error.messages", null, locale));
//            return new ModelAndView("/login", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
//        }


//        if (flag) {
        try {
            String loginIP = CoreHttpUtils.getClientIP(request);
            String loginPlatform = CoreHttpUtils.getLoginPlatform(request);
            command.setLoginIP(loginIP);
            command.setLoginPlatform(loginPlatform);
            Account user = authService.login(command);
            Subject subject = SecurityUtils.getSubject();
            if (subject.hasRole("merchant")) {

//                    CoreHttpUtils.writeCookie(command, request, response);

                logger.info(subject.getPrincipal() + "登录成功！时间:" + new Date());
                session.setAttribute(Constants.SESSION_USER, user);
                return new ModelAndView("redirect:/logged");
            } else {//用户没有对应角色 不让登录
                alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("login.account.NotPermission.messages", null, locale));
                redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
                return new ModelAndView("redirect:/login");
            }
        } catch (UnknownAccountException ue) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("login.account.NotExists.messages", null, locale));
        } catch (IncorrectCredentialsException ie) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("login.account.Error.messages", null, locale));
        } catch (LockedAccountException le) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("login.account.Disable.messages", null, locale));
        } catch (Exception e) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("login.login.Failure.messages", null, locale));
        }
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addFlashAttribute("command", command);
        return new ModelAndView("redirect:/login");
//        }
//        alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("login.verificationCode.Error.messages", null, locale));
//        return new ModelAndView("/login", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
//        CoreHttpUtils.clearCookie(request, response);
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/denied")
    public ModelAndView unauthorized() throws Exception {
        return new ModelAndView("/error/denied");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(@ModelAttribute("command") RegisterCommand command) {
        return new ModelAndView("/register", "command", command);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid @ModelAttribute("command") RegisterCommand command, BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/register", "command", command);
        }

        AlertMessage alertMessage;
        try {
            authService.register(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());

            return new ModelAndView("/register", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/register", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/registerSuccess");
    }

    @RequestMapping(value = "/reset_password", method = RequestMethod.GET)
    public ModelAndView resetPassword(@ModelAttribute("command") ResetPasswordCommand command) {
        return new ModelAndView("/resetPassword", "command", command);
    }

    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    public ModelAndView resetPassword(@Valid @ModelAttribute("command") ResetPasswordCommand command, BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/resetPassword", "command", command);
        }

        AlertMessage alertMessage;
        try {
            authService.resetPassword(command);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("merchant.verificationCode.NotFound.message", null, locale));
            return new ModelAndView("/resetPassword", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (ExistException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("merchant.verificationCode.NoEQ.message", null, locale));
            return new ModelAndView("/resetPassword", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getResponse().getMessage());
            return new ModelAndView("/resetPassword", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/login");
    }
}
