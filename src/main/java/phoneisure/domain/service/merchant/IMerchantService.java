package phoneisure.domain.service.merchant;

import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.merchant.Merchant;
import phoneisure.domain.service.auth.command.RegisterCommand;
import phoneisure.domain.service.merchant.command.UpdatePasswordCommand;

import java.util.List;

/**
 * Created by YJH on 2016/4/22.
 */
public interface IMerchantService {
    void register(RegisterCommand command) throws ApiRemoteCallFailedException;

    List<Merchant> searchAgent() throws ApiRemoteCallFailedException;

    void updatePassword(UpdatePasswordCommand command) throws ApiRemoteCallFailedException;

    Merchant searchByID(String id) throws ApiRemoteCallFailedException;
}
