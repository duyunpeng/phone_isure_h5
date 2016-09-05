package phoneisure.domain.service.policy;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.core.api.ApiPagination;
import phoneisure.core.api.ApiRequest;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.IApiService;
import phoneisure.core.common.SharedCommand;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.policy.Policy;
import phoneisure.domain.model.policy.PolicyCount;
import phoneisure.domain.service.policy.command.CreatePolicyCommand;
import phoneisure.domain.service.policy.command.ListPolicyCommand;

/**
 * Created by LvDi on 2016/4/22.
 */
@Service("policyService")
public class PolicyService implements IPolicyService {

    @Autowired
    private IApiService adminApiService;

    @Override
    public ApiPagination<Policy> pagination(ListPolicyCommand command) throws ApiRemoteCallFailedException {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/policy/api/list");
        apiRequest.setObject(command);
        ApiResponse apiResponse = adminApiService.request(apiRequest);
        ApiPagination<Policy> data = apiResponse.convertJsonTo(new TypeReference<ApiPagination<Policy>>() {
        });
        return data;
    }

    @Override
    public void create(CreatePolicyCommand command) throws ApiRemoteCallFailedException {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/policy/api/create");
        apiRequest.setObject(command);
        adminApiService.request(apiRequest);
    }

    @Override
    public void returnPolicy(SharedCommand command) throws ApiRemoteCallFailedException {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/policy/api/return_policy");
        apiRequest.setObject(command);
        adminApiService.request(apiRequest);
    }

    @Override
    public PolicyCount policyCount(String id) throws ApiRemoteCallFailedException {
        SharedCommand command = new SharedCommand();
        command.setId(id);
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/policy/api/count");
        apiRequest.setObject(command);
        ApiResponse apiResponse = adminApiService.request(apiRequest);
        PolicyCount data = apiResponse.convertJsonTo(new TypeReference<PolicyCount>() {
        });
        return data;
    }
}
