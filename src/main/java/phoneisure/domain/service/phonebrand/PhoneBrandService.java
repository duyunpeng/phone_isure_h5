package phoneisure.domain.service.phonebrand;


import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.core.api.ApiRequest;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.IApiService;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.phonebrand.PhoneBrand;

import java.util.List;

/**
 * Created by LvDi on 2016/4/22.
 */
@Service("phoneBrand")
public class PhoneBrandService implements IPhoneBrandService {

    @Autowired
    private IApiService adminApiService;

    @Override
    public List<PhoneBrand> list() throws ApiRemoteCallFailedException {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/phone_brand/api/list");
        ApiResponse apiResponse = adminApiService.request(apiRequest);
        List<PhoneBrand> data = apiResponse.convertJsonTo(new TypeReference<List<PhoneBrand>>() {
        });
        return data;
    }
}
