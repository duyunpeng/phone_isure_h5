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
    <title>注册</title>
    <link type="text/css" href="[@spring.url '/resources/css/bootstrap.min.css'/]" rel="stylesheet">
    <link type="text/css" href="[@spring.url '/resources/css/style.css'/]" rel="stylesheet">
    <script type="application/javascript" src="[@spring.url '/resources/js/jquery-2.1.1.min.js'/]"></script>
    <script type="application/javascript" src="[@spring.url '/resources/js/bootstrap.min.js'/]"></script>
</head>

<body>
<header>
    <div class="am-heade">
        <h1 style="letter-spacing: 5px;" class="am-header-title">注册</h1>
    </div>
</header>

<div class="regist-box">
[@mc.showAlert /]
    <form action="[@spring.url '/register'/]" method="post" id="register_form">
        <div class="for-item clearfix">
            <div class="name">登录帐号：</div>
            <div class="conter"><input class="form-control" name="userName"
                                       pattern="^1[345678][0-9]{9}$" value="${command.userName!}"
                                       placeholder="手机号" required autofocus type="text">
            </div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">密码：</div>
            <div class="conter"><input class="form-control" name="password" minlength=6 value="${command.password!}"
                                       placeholder="6位以上数字或字母" required autofocus type="password">
            </div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">确认密码：</div>
            <div class="conter"><input class="form-control" name="confirmPassword" value="${command.confirmPassword!}"
                                       placeholder="再次输入密码" required autofocus type="password">
            </div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">店铺名称：</div>
            <div class="conter"><input class="form-control" name="merchantName" value="${command.merchantName!}"
                                       placeholder="店铺名称" required autofocus type="text"></div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">联系人：</div>
            <div class="conter"><input class="form-control" name="contacts" value="${command.contacts!}"
                                       placeholder="联系人姓名" required autofocus type="text"></div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">联系电话：</div>
            <div class="conter"><input class="form-control" name="contactsPhone"
                                       pattern="^1[345678][0-9]{9}$" value="${command.contactsPhone!}"
                                       placeholder="手机号码" required autofocus type="text"></div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">地址：</div>
            <div class="conter area-cascade">
                <div class="sfzy-list">
                    <select class="form-control">
                        <option value="">洲/省</option>
                        <option value="">重庆市</option>
                        <option value="">四川省</option>
                    </select>
                </div>
            </div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">详细地址：</div>
            <div class="conter"><input class="form-control" name="detailedArea" value="${command.detailedArea!}"
                                       placeholder="详细地址" required autofocus type="text"></div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">输入代理账号：</div>
            <div class="conter">
                <input type="text" name="agent" value="${command.agent!}" class="form-control" required autofocus/>
            </div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">备注：</div>
            <div class="conter"><input class="form-control" name="remarks" value="${command.remarks!}"
                                       placeholder="备注" type="text"></div>
            <span class="red-symbol"></span>
        </div>

        <div class="botm-but">
            <button class="btn btn-block sign-btn" type="submit">注册</button>
            <a class="btn btn-block sign-btn" href="/login">已有帐号直接登录</a>
        </div>
    </form>
</div>
<script type="application/javascript" src="[@spring.url '/resources/js/area.js'/]"></script>
<script type="application/javascript" src="[@spring.url '/resources/js/layer/layer.js'/]"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $(".area-cascade").areaCascade("area");

        $("#register_form").submit(function () {
            if ($("input[name='confirmPassword']").val() != $("input[name='password']").val()) {
                layer.msg("两次密码输入不一致");
                return false;
            }
            if ($("select[name='userType']").val() == "MERCHANT") {
                if ($(".area-cascade").find("div").size() != 3) {
                    layer.msg("您选择的区域必须精确到区县");
                    return false;
                }
            }
            return true;
        })

        $("input[name='confirmPassword']").on("blur", function () {
            if ($(this).val() != $("input[name='password']").val()) {
                layer.msg("两次密码输入不一致");
            }
        })
    })
</script>
</body>

</html>