package phoneisure.interfaces.phonebrand.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.phonebrand.PhoneBrand;
import phoneisure.domain.service.phonebrand.IPhoneBrandService;
import phoneisure.interfaces.shared.web.BaseController;

import java.util.List;

/**
 * Created by YJH on 2016/4/25.
 */
@Controller
@RequestMapping("/phone_brand")
public class PhoneBrandController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IPhoneBrandService phoneBrandService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<PhoneBrand> list() {
        List<PhoneBrand> data;
        try {
            data = phoneBrandService.list();
        } catch (ApiRemoteCallFailedException e) {
            logger.warn(e.getMessage());
            return null;
        }
        return data;
    }
}
