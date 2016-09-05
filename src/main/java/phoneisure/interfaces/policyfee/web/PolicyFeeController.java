package phoneisure.interfaces.policyfee.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.policyfee.PolicyFee;
import phoneisure.domain.service.policyfee.IPolicyFeeService;
import phoneisure.domain.service.policyfee.command.ListPoliceFeeCommand;
import phoneisure.interfaces.shared.web.BaseController;

import java.util.List;

/**
 * Created by YJH on 2016/4/25.
 */
@Controller
@RequestMapping("/policy_fee")
public class PolicyFeeController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IPolicyFeeService policyFeeService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<PolicyFee> list(ListPoliceFeeCommand command) {
        List<PolicyFee> data;
        try {
            data = policyFeeService.list(command);
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            return null;
        }
        return data;
    }

}
