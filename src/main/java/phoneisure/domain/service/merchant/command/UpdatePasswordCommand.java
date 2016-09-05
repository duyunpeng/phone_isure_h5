package phoneisure.domain.service.merchant.command;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by YJH on 2016/4/26.
 */
public class UpdatePasswordCommand {

    private String merchant;
    @NotBlank(message = "{merchant.oldPassword.NotBlank.message}")
    private String oldPassword;
    @NotBlank(message = "{merchant.newPassword.NotBlank.message}")
    private String newPassword;

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
