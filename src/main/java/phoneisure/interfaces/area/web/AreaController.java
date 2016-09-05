package phoneisure.interfaces.area.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.area.Area;
import phoneisure.domain.service.area.IAreaService;
import phoneisure.domain.service.area.command.ListAreaCommand;
import phoneisure.interfaces.shared.web.BaseController;

import java.util.List;

/**
 * Created by YJH on 2016/4/23.
 */
@Controller
@RequestMapping("/area")
public class AreaController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IAreaService areaService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<Area> list(@RequestBody ListAreaCommand command) {
        try {
            return areaService.listJSON(command);
        } catch (ApiRemoteCallFailedException e) {
            return null;
        }
    }
}
