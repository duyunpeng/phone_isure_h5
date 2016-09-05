package phoneisure.domain.model.recharge;

import phoneisure.core.enums.PayType;
import phoneisure.core.enums.YesOrNoStatus;
import phoneisure.core.id.ConcurrencySafeEntity;
import phoneisure.domain.model.merchant.Merchant;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/4/21.
 */
public class Recharge extends ConcurrencySafeEntity {

    private String rechargeNo;            //充值流水号
    private Merchant merchant;          //充值商户
    private BigDecimal rechargeMoney;    //充值金额
    private Date payDate;               //支付时间
    private PayType payType;            //支付方式
    private String payNo;               //支付号
    private YesOrNoStatus isPay;        //是否支付
    private String ipAddress;           //ip

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public YesOrNoStatus getIsPay() {
        return isPay;
    }

    public void setIsPay(YesOrNoStatus isPay) {
        this.isPay = isPay;
    }

    public String getRechargeNo() {
        return rechargeNo;
    }

    public void setRechargeNo(String rechargeNo) {
        this.rechargeNo = rechargeNo;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
