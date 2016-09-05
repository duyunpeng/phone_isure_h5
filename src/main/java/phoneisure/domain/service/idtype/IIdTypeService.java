package phoneisure.domain.service.idtype;

import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.idtype.IdType;

import java.util.List;

/**
 * Created by YJH on 2016/4/25.
 */
public interface IIdTypeService {
    List<IdType> list() throws ApiRemoteCallFailedException;
}
