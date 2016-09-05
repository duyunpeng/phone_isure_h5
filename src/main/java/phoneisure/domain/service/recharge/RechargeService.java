package phoneisure.domain.service.recharge;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.core.api.ApiRequest;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.IApiService;
import phoneisure.core.common.SharedCommand;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.recharge.Recharge;
import phoneisure.domain.service.recharge.command.RechargeCommand;

/**
 * Created by YJH on 2016/5/5.
 */
@Service("rechargeService")
public class RechargeService implements IRechargeService {

    @Autowired
    private IApiService adminApiService;

    @Override
    public Recharge recharge(RechargeCommand command) throws ApiRemoteCallFailedException {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/recharge/api/recharge");
        apiRequest.setObject(command);
        ApiResponse apiResponse = adminApiService.request(apiRequest);
        Recharge data = apiResponse.convertJsonTo(new TypeReference<Recharge>() {
        });
        return data;
    }

    @Override
    public Recharge searchByNo(String rechargeNo) throws ApiRemoteCallFailedException {
        SharedCommand command = new SharedCommand();
        command.setId(rechargeNo);
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/recharge/api/search_by_no");
        apiRequest.setObject(command);
        ApiResponse apiResponse = adminApiService.request(apiRequest);
        Recharge data = apiResponse.convertJsonTo(new TypeReference<Recharge>() {
        });
        return data;
    }

}
