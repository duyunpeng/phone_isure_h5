package phoneisure.domain.service.account;


import com.alibaba.fastjson.TypeReference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.core.api.ApiRequest;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.IApiService;
import phoneisure.core.common.Constants;
import phoneisure.core.enums.UserType;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.core.shiro.UsernamePasswordAppKeyToken;
import phoneisure.domain.model.account.Account;
import phoneisure.domain.service.account.command.ResetPasswordCommand;
import phoneisure.domain.service.account.command.SearchAccountCommand;
import phoneisure.domain.service.account.command.UpdateLoginCommand;
import phoneisure.domain.service.auth.command.LoginCommand;
import phoneisure.domain.service.auth.command.RegisterCommand;

/**
 * Created by YJH on 2016/3/30.
 */
@Service("accountService")
public class AccountService implements IAccountService {

    @Autowired
    private IApiService adminApiService;

    @Override
    public Account login(LoginCommand command) throws ApiRemoteCallFailedException {
        Account account = this.searchByAccountName(command.getUserName());
        if (null == account) {
            throw new UnknownAccountException();
        }
        if (!account.getRoles().get(0).getName().equals("merchant")) {
            throw new UnknownAccountException();
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordAppKeyToken token = new UsernamePasswordAppKeyToken(command.getUserName(), command.getPassword(), Constants.APP_KRY);
        subject.login(token);

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/account/api/update_login");
        UpdateLoginCommand updateCommand = new UpdateLoginCommand();
        updateCommand.setId(account.getId());
        updateCommand.setAppKey(Constants.APP_KRY);
        updateCommand.setLoginIP(command.getLoginIP());
        updateCommand.setLoginPlatform(command.getLoginPlatform());
        apiRequest.setObject(updateCommand);
        ApiResponse apiResponse = adminApiService.request(apiRequest);
        account = apiResponse.convertJsonTo(new TypeReference<Account>() {
        });
        return account;
    }

    @Override
    public Account searchByAccountName(String userName) throws ApiRemoteCallFailedException {
        SearchAccountCommand command = new SearchAccountCommand();
        command.setUserName(userName);
        command.setAppKey(Constants.APP_KRY);
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/account/api/search_by_name");
        apiRequest.setObject(command);
        ApiResponse apiResponse = adminApiService.request(apiRequest);
        Account account = apiResponse.convertJsonTo(new TypeReference<Account>() {
        });
        return account;
    }

    @Override
    public void resetPassword(ResetPasswordCommand command) throws ApiRemoteCallFailedException {
        command.setAppKey(Constants.APP_KRY);
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl("/account/api/reset_password");
        apiRequest.setObject(command);
        adminApiService.request(apiRequest);
    }


}
