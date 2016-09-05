package phoneisure.domain.service.auth.command;

import org.hibernate.validator.constraints.NotBlank;
import phoneisure.core.enums.UserType;

import javax.validation.constraints.NotNull;

/**
 * Created by YJH on 2016/4/23.
 */
public class RegisterCommand {

    @NotBlank(message = "{register.userName.NotBlank.message}")
    private String userName;
    @NotBlank(message = "{register.password.NotBlank.message}")
    private String password;
    @NotBlank(message = "{register.confirmPassword.NotBlank.message}")
    private String confirmPassword;
    @NotBlank(message = "{register.merchantName.NotBlank.message}")
    private String merchantName;
    @NotBlank(message = "{register.contacts.NotBlank.message}")
    private String contacts;        //商户联系人
    @NotBlank(message = "{register.contactsPhone.NotBlank.message}")
    private String contactsPhone;   //商户联系人电话
    @NotBlank(message = "{register.area.NotBlank.message}")
    private String area;              //地区
    @NotBlank(message = "{register.detailedArea.NotBlank.message}")
    private String detailedArea;    //详细地区
    //    @NotBlank(message = "{register.remarks.NotBlank.message}")
    private String remarks;         //备注
    private String appKey;          //AppKey
    @NotBlank(message = "{register.agent.NotBlank.message}")
    private String agent;           //代理

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetailedArea() {
        return detailedArea;
    }

    public void setDetailedArea(String detailedArea) {
        this.detailedArea = detailedArea;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
