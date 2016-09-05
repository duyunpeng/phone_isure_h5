<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh_cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>充值</title>
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
<header>
    <div class="am-heade">
        <h1 style="letter-spacing: 5px;" class="am-header-title">充值</h1>
    </div>
</header>

<div class="regist-box">
    <form id="recharge_form" action="/recharge/recharge" method="post">
        <input type="hidden" name="code" value="${codes}"/>
        <div class="for-item clearfix">
            <div class="name">充值账户：</div>
            <div class="conter"><input class="form-control" disabled value="${Session["sessionUser"].userName!}"
                                       required autofocus type="text"></div>
        </div>
        <div class="for-item clearfix">
            <div class="name">充值金额：</div>
            <div class="conter"><input class="form-control" name="money" placeholder="最多输入两为小数" required autofocus
                                       pattern="^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$"
                                       type="text"></div>
            <span class="red-symbol">*</span>
        </div>

        <div class="botm-but">
            <button class="btn btn-block sign-btn" type="submit">充值</button>
            <a class="btn btn-block sign-btn" href="javascript:history.go(-1);">取消</a>
        </div>
    </form>
</div>
<!-- 加载中 -->
<div class="file_upload_load"></div>
<script type="application/javascript" src="[@spring.url '/resources/js/layer/layer.js'/]"></script>
<script type="text/javascript">

    $('#recharge_form').ajaxForm({  //ajax登录
        beforeSubmit: function () {
            var money = $("input[name='money']").val();
            if (money < 500) {
                alert("充值金额不能小于500");
                return false;
            }
            $('html').addClass('.file_upload_mask');
            $('.file_upload_load').show();
        },
        success: function (msg) {
            var result;
            if (typeof msg == "object") {
                result = msg
            } else {
                result = JSON.parse(msg)
            }

            if (result.code == 0) {//成功
                $('html').removeClass('.file_upload_mask');
                $('.file_upload_load').hide();

                window.location.href = "/recharge/wechat_pay?rechargeNo=" + result.data.rechargeNo;
            } else {
                layer.msg(result.message);
            }
        }
    });

</script>
</body>
</html>
