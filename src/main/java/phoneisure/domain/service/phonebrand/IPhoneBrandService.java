package phoneisure.domain.service.phonebrand;

import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.phonebrand.PhoneBrand;

import java.util.List;

/**
 * Created by LvDi on 2016/4/22.
 */
public interface IPhoneBrandService {

    List<PhoneBrand> list() throws ApiRemoteCallFailedException;

}
