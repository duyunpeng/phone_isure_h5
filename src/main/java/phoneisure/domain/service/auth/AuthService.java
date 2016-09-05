package phoneisure.domain.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.core.exception.ExistException;
import phoneisure.core.exception.NoFoundException;
import phoneisure.core.redis.RedisService;
import phoneisure.domain.model.account.Account;
import phoneisure.domain.model.permission.Permission;
import phoneisure.domain.service.account.IAccountService;
import phoneisure.domain.service.account.command.ResetPasswordCommand;
import phoneisure.domain.service.auth.command.LoginCommand;
import phoneisure.domain.service.auth.command.RegisterCommand;
import phoneisure.domain.service.merchant.IMerchantService;
import phoneisure.domain.service.permission.IPermissionService;

import java.util.List;

/**
 * Created by YJH on 2016/4/23.
 */
@Service("AuthService")
public class AuthService implements IAuthService {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private RedisService redisService;

    @Override
    public Account login(LoginCommand command) throws ApiRemoteCallFailedException {
        return accountService.login(command);
    }

    @Override
    public Account searchByAccountName(String userName) throws ApiRemoteCallFailedException {
        return accountService.searchByAccountName(userName);
    }

    @Override
    public List<Permission> findAllPermission() {
        try {
            return permissionService.list();
        } catch (ApiRemoteCallFailedException e) {
            return this.findAllPermission();
        }
    }

    @Override
    public void register(RegisterCommand command) throws ApiRemoteCallFailedException {
        merchantService.register(command);
    }

    @Override
    public void resetPassword(ResetPasswordCommand command) throws ApiRemoteCallFailedException {
        if (redisService.exists(command.getUserName())) {
            if (redisService.getCache(command.getUserName()).equals(command.getVerificationCode())) {
                accountService.resetPassword(command);
                redisService.delete(command.getUserName());
            } else {
                throw new ExistException();
            }
        } else {
            throw new NoFoundException();
        }
    }
}
