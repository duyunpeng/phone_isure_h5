<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh_cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>找回密码</title>
    <link type="text/css" href="[@spring.url '/resources/css/bootstrap.min.css'/]" rel="stylesheet">
    <link type="text/css" href="[@spring.url '/resources/css/style.css'/]" rel="stylesheet">
    <link type="text/css" href="[@spring.url '/resources/css/login.css'/]" rel="stylesheet">
    <script type="application/javascript" src="[@spring.url '/resources/js/jquery-2.1.1.min.js'/]"></script>
    <script type="application/javascript" src="[@spring.url '/resources/js/bootstrap.min.js'/]"></script>
</head>
<body>
<header>
    <div class="am-heade">
        <h1 class="am-header-title">找回密码</h1>
    </div>
</header>

<div class="container sign-box">
[@mc.showAlert /]
    <form class="regist-box update-from" action="/reset_password" method="post">
        <label class="sign-only">账号</label>
        <input class="form-control" placeholder="请输入账号" name="userName" id="userName" value="${command.userName!}" required autofocus
               type="text">
        <label class="sign-only">验证码</label>
        <input class="form-control" name="verificationCode" placeholder="请输入验证码" value="${command.verificationCode!}" required autofocus
               type="text">
        <a href="#" class="btn btn-block sign-btn" id="send_verification_code">发送验证码</a>
        <label class="sign-only">新密码</label>
        <input class="form-control" name="password" minlength=6 placeholder="请输入新密码" required autofocus
               type="password">
        <div class="botm-but">
            <button class="btn btn-block sign-btn" type="submit">提交</button>
            <a class="btn btn-block sign-btn" href="javascript:history.go(-1);">取消</a>
        </div>
    </form>
</div>
<script type="application/javascript" src="[@spring.url '/resources/js/layer/layer.js'/]"></script>
<script type="text/javascript">
    $("#send_verification_code").click(function () {
        var userName = $("#userName").val();
        var _this = $(this);
        var re = /^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57])[0-9]{8}|177\\d{8}$/;
        if (re.test(userName)) {
            $.ajax({
                type: "post",
                url: "/sms/send_reset_password",
                data: "userName=" + userName,
                dataType: "json",
                success: function (data) {
                    if (typeof data == 'object') {
                        data = data;
                    } else {
                        data = JSON.parse(data);
                    }
                    if (data.code == "0") {
                        layer.msg("发送成功!", {icon: 1});
                        countdown(_this);
                    } else {
                        layer.msg(data.message);
                    }
                }
            })
        } else {
            layer.msg("请输入正确的11位手机号码");
        }
    })

    function countdown(sendMessageBtn) {
        var timer = 120;
        var clearTimer = setInterval(sendMessage, 1000);
        sendMessage();
        function sendMessage() {
            if (timer == 0) {
                sendMessageBtn.html("重新发送").removeAttr("disabled");
                sendMessageBtn.css({
                    'cursor': 'pointer',
                    'background': '#FFC100'
                });
                clearInterval(clearTimer);
            } else {
                timer--;
                sendMessageBtn.attr("disabled", true);
                sendMessageBtn.css({
                    'cursor': 'no-drop',
                    'background': '#ccc'
                });
                sendMessageBtn.html(timer + " 秒后重试");
            }
        }
    }
</script>
</body>
</html>