package phoneisure.domain.service.permission;


import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.permission.Permission;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IPermissionService {

    List<Permission> list() throws ApiRemoteCallFailedException;
}
