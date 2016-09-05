package phoneisure.domain.model.policyfee;

import phoneisure.core.enums.EnableStatus;
import phoneisure.core.id.ConcurrencySafeEntity;
import phoneisure.domain.model.phonebrand.PhoneBrand;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/4/22.
 */
public class PolicyFee extends ConcurrencySafeEntity {

    private PhoneBrand phoneBrand;      //手机品牌
    private String phoneModel;          //手机型号
    private BigDecimal policyFee;       //保单费用
    private Integer sort;               //排序
    private EnableStatus status;        //状态

    public PhoneBrand getPhoneBrand() {
        return phoneBrand;
    }

    public void setPhoneBrand(PhoneBrand phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public BigDecimal getPolicyFee() {
        return policyFee;
    }

    public void setPolicyFee(BigDecimal policyFee) {
        this.policyFee = policyFee;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
