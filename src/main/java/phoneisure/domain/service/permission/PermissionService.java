package phoneisure.domain.service.permission;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.core.api.ApiRequest;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.IApiService;
import phoneisure.core.common.Constants;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.permission.Permission;
import phoneisure.domain.service.permission.command.ListPermissionCommand;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
@Service("permissionService")
public class PermissionService implements IPermissionService {

    @Autowired
    private IApiService adminApiService;

    @Override
    public List<Permission> list() throws ApiRemoteCallFailedException {
        ListPermissionCommand command = new ListPermissionCommand();
        command.setAppKey(Constants.APP_KRY);
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/permission/api/list");
        apiRequest.setObject(command);
        ApiResponse response = adminApiService.request(apiRequest);
        List<Permission> data = response.convertJsonTo(new TypeReference<List<Permission>>() {
        });
        return data;
    }
}
