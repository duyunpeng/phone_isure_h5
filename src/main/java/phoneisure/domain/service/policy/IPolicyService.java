package phoneisure.domain.service.policy;

import phoneisure.core.api.ApiPagination;
import phoneisure.core.common.SharedCommand;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.policy.Policy;
import phoneisure.domain.model.policy.PolicyCount;
import phoneisure.domain.service.policy.command.CreatePolicyCommand;
import phoneisure.domain.service.policy.command.ListPolicyCommand;

/**
 * Created by LvDi on 2016/4/22.
 */
public interface IPolicyService {
    ApiPagination<Policy> pagination(ListPolicyCommand command) throws ApiRemoteCallFailedException;

    void create(CreatePolicyCommand command) throws ApiRemoteCallFailedException;

    void returnPolicy(SharedCommand command) throws ApiRemoteCallFailedException;

    PolicyCount policyCount(String id) throws ApiRemoteCallFailedException;
}
