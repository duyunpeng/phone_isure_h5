package phoneisure.domain.service.policy.command;

import phoneisure.core.common.BasicPaginationCommand;
import phoneisure.core.enums.PolicyStatus;

/**
 * Created by YJH on 2016/4/25.
 */
public class ListPolicyCommand extends BasicPaginationCommand {

    private String merchant;

    private PolicyStatus policyStatus;

    private boolean claim;   //理赔查询

    private boolean back;   //退单查询

    private String beginDate;

    private String endDate;

    private String policyNo;

    public PolicyStatus getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(PolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public boolean isClaim() {
        return claim;
    }

    public void setClaim(boolean claim) {
        this.claim = claim;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public boolean isBack() {
        return back;
    }

    public void setBack(boolean back) {
        this.back = back;
    }
}
