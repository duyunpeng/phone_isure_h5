<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh_cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>密码修改</title>
    <link type="text/css" href="[@spring.url '/resources/css/bootstrap.min.css'/]" rel="stylesheet">
    <link type="text/css" href="[@spring.url '/resources/css/style.css'/]" rel="stylesheet">
    <link type="text/css" href="[@spring.url '/resources/css/login.css'/]" rel="stylesheet">
    <script type="application/javascript" src="[@spring.url '/resources/js/jquery-2.1.1.min.js'/]"></script>
    <script type="application/javascript" src="[@spring.url '/resources/js/bootstrap.min.js'/]"></script>
</head>
<body>
<header>
    <div class="am-heade">
        <h1 class="am-header-title">密码修改</h1>
    </div>
</header>

<div class="container sign-box">
[@mc.showAlert /]
    <form class="regist-box update-from" action="/merchant/update_password" method="post">
        <label class="sign-only">旧密码</label>
        <input class="form-control" placeholder="请输入旧密码" name="oldPassword" minlength=6 required autofocus
               type="password">
        <label class="sign-only">新密码</label>
        <input class="form-control" name="newPassword" minlength=6 placeholder="请输入新密码" required autofocus
               type="password">
        <label class="sign-only">确认密码</label>
        <input class="form-control" name="confirmPassword" placeholder="请输入确认密码" required autofocus
               type="password">
        <div class="botm-but">
            <button class="btn btn-block sign-btn" type="submit">提交</button>
            <a class="btn btn-block sign-btn" href="javascript:history.go(-1);">取消</a>
        </div>
    </form>
</div>
<script type="application/javascript" src="[@spring.url '/resources/js/layer/layer.js'/]"></script>
<script type="text/javascript">
    $(".update-from").submit(function () {
        if (($("input[name='confirmPassword']").val()) != ($("input[name='newPassword']").val())) {
            layer.msg("两次密码输入不一致");
            return false;
        }
        return true;
    })

</script>
</body>
</html>