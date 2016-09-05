package phoneisure.domain.model.account;

import phoneisure.core.enums.EnableStatus;
import phoneisure.core.enums.UserType;
import phoneisure.core.id.ConcurrencySafeEntity;
import phoneisure.domain.model.appkey.AppKey;
import phoneisure.domain.model.picture.Picture;
import phoneisure.domain.model.role.Role;

import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public class Account extends ConcurrencySafeEntity {

    private String userName;        //用户名
    private String password;        //密码
    private String salt;            //密码加密盐
    private String lastLoginIP;     //最后登录ip
    private Date lastLoginDate;     //最后登录时间
    private String lastLoginPlatform;//最后登录平台
    private List<Role> roles;               //用户角色
    private String email;           //邮箱
    private AppKey appKey;          //应用标识
    private EnableStatus status;     //状态
    private Picture headPic;        //头像

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginPlatform() {
        return lastLoginPlatform;
    }

    public void setLastLoginPlatform(String lastLoginPlatform) {
        this.lastLoginPlatform = lastLoginPlatform;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AppKey getAppKey() {
        return appKey;
    }

    public void setAppKey(AppKey appKey) {
        this.appKey = appKey;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public Picture getHeadPic() {
        return headPic;
    }

    public void setHeadPic(Picture headPic) {
        this.headPic = headPic;
    }

}
