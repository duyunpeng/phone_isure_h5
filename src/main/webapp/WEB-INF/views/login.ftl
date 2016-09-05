<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
                            _ooOoo_
                           o8888888o
                           88" . "88
                           (| -_- |)
                            O\ = /O
                        ____/`---'\____
                      .   ' \\| |// `.
                       / \\||| : |||// \
                     / _||||| -:- |||||- \
                       | | \\\ - /// | |
                     | \_| ''\---/'' | |
                      \ .-\__ `-` ___/-. /
                   ___`. .' /--.--\ `. . __
                ."" '< `.___\_<|>_/___.' >'"".
               | | : `- \`.;`\ _ /`;.`/ - ` : | |
                 \ \ `-. \_ __\ /__ _/ .-` / /
         ======`-.____`-.___\_____/___.-`____.-'======
                            `=---='

         .............................................
                  佛祖镇楼                  BUG辟易
          佛曰:
                  写字楼里写字间，写字间里程序员；
                  程序人员写程序，又拿程序换酒钱。
                  酒醒只在网上坐，酒醉还来网下眠；
                  酒醉酒醒日复日，网上网下年复年。
                  但愿老死电脑间，不愿鞠躬老板前；
                  奔驰宝马贵者趣，公交自行程序员。
                  别人笑我忒疯癫，我笑自己命太贱；
                  不见满街漂亮妹，哪个归得程序员？
-->
<html lang="zh_cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>登录</title>
    <link type="text/css" href="[@spring.url '/resources/css/bootstrap.min.css'/]" rel="stylesheet">
    <link type="text/css" href="[@spring.url '/resources/css/style.css'/]" rel="stylesheet">
    <link type="text/css" href="[@spring.url '/resources/css/login.css'/]" rel="stylesheet">
    <script type="application/javascript" src="[@spring.url 'resources/js/jquery-2.1.1.min.js'/]"></script>
    <script type="application/javascript" src="[@spring.url 'resources/js/bootstrap.min.js'/]"></script>
</head>
<body>
<header>
    <div class="am-heade">
        <h1 class="am-header-title">用户登录</h1>
    </div>
</header>

<div class="container sign-box">

[@mc.showAlert /]
    <form class="form-signin" action="/login" method="post">
    [@spring.bind "command.userName"/]
        <label class="sign-only">帐号</label>
        <input class="form-control" id="userName" value="${command.userName!}" name="userName" placeholder="请输入手机号"
               required autofocus type="text">
    [@spring.bind "command.password"/]
        <label class="sign-only">密码</label>
        <input id="inputPassword" class="form-control" name="password" placeholder="请输入密码" required type="password">
    [@spring.bind "command.verificationCode"/]
    [#--<label class="sign-only">验证码</label>--]
    [#--<div class="loginVerification">--]
    [#--<input class="form-control" type="text" name="verificationCode" placeholder="验证码"/>--]
    [#--[@mc.verificationCode /]--]
    [#--</div>--]
        <div class="checkbox">
        [#--<label><input value="remember-me" type="checkbox">记住密码</label>--]
            <label><a href="/reset_password">忘记密码?</a></label>
        </div>
        <button class="btn btn-block sign-btn" type="submit">登录</button>
        <a href="/register" class="btn btn-block sign-btn">还没有账号?点击注册</a>
    </form>

</div>
<script type="text/javascript">

</script>
</body>
</html>