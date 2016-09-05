package phoneisure.domain.service.merchant;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.core.api.ApiRequest;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.IApiService;
import phoneisure.core.common.Constants;
import phoneisure.core.common.SharedCommand;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.merchant.Merchant;
import phoneisure.domain.service.auth.command.RegisterCommand;
import phoneisure.domain.service.merchant.command.ListMerchantCommand;
import phoneisure.domain.service.merchant.command.UpdatePasswordCommand;

import java.util.List;

/**
 * Created by YJH on 2016/4/22.
 */
@Service("merchantService")
public class MerchantService implements IMerchantService {

    @Autowired
    private IApiService adminApiService;

    @Override
    public void register(RegisterCommand command) throws ApiRemoteCallFailedException {
        command.setAppKey(Constants.APP_KRY);
        ApiRequest request = new ApiRequest();
        request.setUrl("/merchant/api/register");
        request.setObject(command);
        adminApiService.request(request);
    }

    @Override
    public List<Merchant> searchAgent() throws ApiRemoteCallFailedException {
        ListMerchantCommand command = new ListMerchantCommand();
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/merchant/api/search_agent");
        apiRequest.setObject(command);
        ApiResponse apiResponse = adminApiService.request(apiRequest);
        List<Merchant> data = apiResponse.convertJsonTo(new TypeReference<List<Merchant>>() {
        });
        return data;
    }

    @Override
    public void updatePassword(UpdatePasswordCommand command) throws ApiRemoteCallFailedException {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/merchant/api/update_password");
        apiRequest.setObject(command);
        adminApiService.request(apiRequest);
    }

    @Override
    public Merchant searchByID(String id) throws ApiRemoteCallFailedException {
        SharedCommand command = new SharedCommand();
        command.setId(id);
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/merchant/api/search_by_id");
        apiRequest.setObject(command);
        ApiResponse apiResponse = adminApiService.request(apiRequest);
        Merchant merchant = apiResponse.convertJsonTo(new TypeReference<Merchant>() {
        });
        return merchant;
    }

}
