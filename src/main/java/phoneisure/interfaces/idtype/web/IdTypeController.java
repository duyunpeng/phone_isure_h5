package phoneisure.interfaces.idtype.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import phoneisure.core.api.ApiRequest;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.idtype.IdType;
import phoneisure.domain.service.idtype.IIdTypeService;
import phoneisure.interfaces.shared.web.BaseController;

import java.util.List;

/**
 * Created by YJH on 2016/4/25.
 */
@Controller
@RequestMapping("/id_type")
public class IdTypeController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IIdTypeService idTypeService;

    @RequestMapping("/list")
    @ResponseBody
    public List<IdType> list() {
        List<IdType> data;
        try {
            data = idTypeService.list();
        } catch (ApiRemoteCallFailedException e) {
            logger.error(e.getMessage());
            return null;
        }
        return data;
    }

}
