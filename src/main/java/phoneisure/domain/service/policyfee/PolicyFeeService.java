package phoneisure.domain.service.policyfee;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.core.api.ApiRequest;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.IApiService;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.idtype.IdType;
import phoneisure.domain.model.policyfee.PolicyFee;
import phoneisure.domain.service.policyfee.command.ListPoliceFeeCommand;

import java.util.List;

/**
 * Created by YJH on 2016/4/25.
 */
@Service("policyFeeService")
public class PolicyFeeService implements IPolicyFeeService {

    @Autowired
    private IApiService adminApiService;

    @Override
    public List<PolicyFee> list(ListPoliceFeeCommand command) throws ApiRemoteCallFailedException {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/policy_fee/api/list");
        apiRequest.setObject(command);
        ApiResponse apiResponse = adminApiService.request(apiRequest);
        List<PolicyFee> data = apiResponse.convertJsonTo(new TypeReference<List<PolicyFee>>() {
        });
        return data;
    }

}
