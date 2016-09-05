package phoneisure.domain.service.auth;

import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.domain.model.account.Account;
import phoneisure.domain.model.permission.Permission;
import phoneisure.domain.service.account.command.ResetPasswordCommand;
import phoneisure.domain.service.auth.command.LoginCommand;
import phoneisure.domain.service.auth.command.RegisterCommand;

import java.util.List;

/**
 * Created by YJH on 2016/4/23.
 */
public interface IAuthService {
    Account login(LoginCommand command) throws ApiRemoteCallFailedException;

    Account searchByAccountName(String userName) throws ApiRemoteCallFailedException;

    List<Permission> findAllPermission();

    void register(RegisterCommand command) throws ApiRemoteCallFailedException;

    void resetPassword(ResetPasswordCommand command) throws ApiRemoteCallFailedException;
}
