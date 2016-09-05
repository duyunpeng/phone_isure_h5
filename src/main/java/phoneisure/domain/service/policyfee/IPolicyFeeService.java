package phoneisure.domain.service.policyfee;

import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.policyfee.PolicyFee;
import phoneisure.domain.service.policyfee.command.ListPoliceFeeCommand;

import java.util.List;

/**
 * Created by YJH on 2016/4/25.
 */
public interface IPolicyFeeService {
    List<PolicyFee> list(ListPoliceFeeCommand command) throws ApiRemoteCallFailedException;
}
