package phoneisure.domain.service.recharge;

import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.recharge.Recharge;
import phoneisure.domain.service.recharge.command.RechargeCommand;

/**
 * Created by YJH on 2016/5/5.
 */
public interface IRechargeService {
    Recharge recharge(RechargeCommand command) throws ApiRemoteCallFailedException;

    Recharge searchByNo(String rechargeNo) throws ApiRemoteCallFailedException;
}
