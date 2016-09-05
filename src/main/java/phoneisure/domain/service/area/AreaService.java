package phoneisure.domain.service.area;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.core.api.ApiRequest;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.IApiService;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.area.Area;
import phoneisure.domain.service.area.command.ListAreaCommand;

import java.util.List;


/**
 * Created by YJH on 2016/4/14.
 */
@Service("areaService")
public class AreaService implements IAreaService {

    @Autowired
    private IApiService adminApiService;

    @Override
    public List<Area> listJSON(ListAreaCommand command) throws ApiRemoteCallFailedException {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/area/api/list");
        apiRequest.setObject(command);
        ApiResponse response = adminApiService.request(apiRequest);
        List<Area> data = response.convertJsonTo(new TypeReference<List<Area>>() {
        });
        return data;
    }
}
