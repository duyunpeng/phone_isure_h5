package phoneisure.domain.service.idtype;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.core.api.ApiRequest;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.IApiService;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.idtype.IdType;

import java.util.List;

/**
 * Created by YJH on 2016/4/25.
 */
@Service("idTypeService")
public class IdTypeService implements IIdTypeService {

    @Autowired
    private IApiService adminApiService;

    @Override
    public List<IdType> list() throws ApiRemoteCallFailedException {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/id_type/api/list");
        ApiResponse apiResponse = adminApiService.request(apiRequest);
        List<IdType> data = apiResponse.convertJsonTo(new TypeReference<List<IdType>>() {
        });
        return data;
    }
}
