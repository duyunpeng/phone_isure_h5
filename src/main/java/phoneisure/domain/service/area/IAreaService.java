package phoneisure.domain.service.area;


import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.area.Area;
import phoneisure.domain.service.area.command.ListAreaCommand;

import java.util.List;

/**
 * Created by YJH on 2016/4/14.
 */
public interface IAreaService {
    List<Area> listJSON(ListAreaCommand command) throws ApiRemoteCallFailedException;
}
