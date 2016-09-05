package phoneisure.domain.service.account;

import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.account.Account;
import phoneisure.domain.service.account.command.ResetPasswordCommand;
import phoneisure.domain.service.auth.command.LoginCommand;
import phoneisure.domain.service.auth.command.RegisterCommand;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IAccountService {

    Account login(LoginCommand command) throws ApiRemoteCallFailedException;

    Account searchByAccountName(String userName) throws ApiRemoteCallFailedException;

    void resetPassword(ResetPasswordCommand command) throws ApiRemoteCallFailedException;
}
