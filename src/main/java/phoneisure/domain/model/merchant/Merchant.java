package phoneisure.domain.model.merchant;

import phoneisure.core.enums.AuthStatus;
import phoneisure.core.enums.EnableStatus;
import phoneisure.core.enums.UserType;
import phoneisure.domain.model.account.Account;
import phoneisure.domain.model.appkey.AppKey;
import phoneisure.domain.model.area.Area;
import phoneisure.domain.model.picture.Picture;
import phoneisure.domain.model.role.Role;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/4/21.
 */
public class Merchant extends Account {

    private String merchantName;    //商户名称
    private String contacts;        //商户联系人
    private String contactsPhone;   //商户联系人电话
    private Area area;              //地区
    private String detailedArea;    //详细地区
    private String remarks;         //备注
    private BigDecimal money;       //余额
    private AuthStatus authStatus;  //认证状态
    private String authFailureExplain;  //认证失败原因
    private UserType userType;
    private Merchant agent;   //代理

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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public AuthStatus getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(AuthStatus authStatus) {
        this.authStatus = authStatus;
    }

    public String getAuthFailureExplain() {
        return authFailureExplain;
    }

    public void setAuthFailureExplain(String authFailureExplain) {
        this.authFailureExplain = authFailureExplain;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Merchant getAgent() {
        return agent;
    }

    public void setAgent(Merchant agent) {
        this.agent = agent;
    }
}
