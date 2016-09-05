<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh_cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>微信支付</title>
    <link type="text/css" href="[@spring.url '/resources/css/bootstrap.min.css'/]" rel="stylesheet">
    <link type="text/css" href="[@spring.url '/resources/css/style.css'/]" rel="stylesheet">
    <link type="text/css" href="[@spring.url '/resources/css/login.css'/]" rel="stylesheet">
    <script type="application/javascript" src="[@spring.url '/resources/js/jquery-2.1.1.min.js'/]"></script>
    <script type="application/javascript" src="[@spring.url '/resources/js/bootstrap.min.js'/]"></script>
    <!-- Nicescroll 滚动条 -->
    <script type="application/javascript" src="[@spring.url '/resources/js/nicescroll/jquery.nicescroll.js'/]"></script>

    <script type="application/javascript" src="[@spring.url '/resources/js/jqueryForm/jquery.form.js'/]"></script>
</head>
<body>
<div class="regist-box">
    <form>
        <input type="hidden" name="appid" value="${weChatPay.appId!}"/>
        <input type="hidden" name="timeStamp" value="${weChatPay.timeStamp!}"/>
        <input type="hidden" name="nonceStr" value="${weChatPay.nonceStr!}"/>
        <input type="hidden" name="package" value="${weChatPay.packages!}"/>
        <input type="hidden" name="signType" value="${weChatPay.signType!}"/>
        <input type="hidden" name="paySign" value="${weChatPay.sign!}"/>
    </form>
</div>
<script type="text/javascript">

    function getAppId() {
        return $("input[name='appid']").val();
    }
    function getTimeStamp() {
        return $("input[name='timeStamp']").val();
    }
    function getNonceStr() {
        return $("input[name='nonceStr']").val();
    }
    function getPackage() {
        return $("input[name='package']").val();
    }
    function getSignType() {
        return $("input[name='signType']").val();
    }
    function getPaySign() {
        return $("input[name='paySign']").val();
    }

    function onBridgeReady() {
        var appId = getAppId();
        var timeStamp = getTimeStamp();
        var nonceStr = getNonceStr();
        var packages = getPackage();
        var signType = getSignType();
        var paySign = getPaySign();
        WeixinJSBridge.invoke('getBrandWCPayRequest', {
            "appId": appId,//公众号名称，由商户传入
            "timeStamp": timeStamp,//时间戳，自1970年以来的秒数
            "nonceStr": nonceStr,//随机串
            "package": packages,//"prepay_id=",
            "signType": signType,//微信签名方式:
            "paySign": paySign,//微信签名
        }, function (res) { // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
            if (res.err_msg == "get_brand_wcpay_request:ok") {
                alert("支付成功");
                window.location = "/logged";
            } else {
                alert("支付失败");
                window.location = "/logged";
            }
        });
    }

    if (typeof WeixinJSBridge == "undefined") {
        if (document.addEventListener) {
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady,
                    false);
        } else if (document.attachEvent) {
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    } else {
        onBridgeReady();
    }
</script>
</body>
</html>