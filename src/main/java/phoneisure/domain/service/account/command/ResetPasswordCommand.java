package phoneisure.domain.service.account.command;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by YJH on 2016/4/26.
 */
public class ResetPasswordCommand {

    @NotBlank(message = "{merchant.userName.NotBlank.message}")
    private String userName;
    @NotBlank(message = "{merchant.password.NotBlank.message}")
    private String password;
    @NotBlank(message = "{merchant.verificationCode.NotBlank.message}")
    private String verificationCode;

    private String appKey;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
