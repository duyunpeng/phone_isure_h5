<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh_cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>保单查询</title>
    <link type="text/css" href="[@spring.url '/resources/css/bootstrap.min.css'/]" rel="stylesheet">
    <link type="text/css" href="[@spring.url '/resources/css/style.css'/]" rel="stylesheet">
    <link type="text/css" href="[@spring.url '/resources/css/login.css'/]" rel="stylesheet">
    <script type="application/javascript" src="[@spring.url '/resources/js/jquery-2.1.1.min.js'/]"></script>
    <script type="application/javascript" src="[@spring.url '/resources/js/bootstrap.min.js'/]"></script>
</head>

<body>
<header>
    <div class="heade">
        <div class="header-left">
            <a href="#"><img src="${Session["sessionUser"].headPic.picPath!}" alt="" class="img-circle"/></a>
        </div>
        <div class="header-title">
            <div class="heade-phone">${Session["sessionUser"].userName!}</div>
            <div class="heade-account">账户余额：<span id="money-span"></span>元 <a class="recharge"
                                                                              href="/recharge/recharge">充值</a></div>
        </div>
        <div class="header-right">
            <div class="text-right"><a href="/logout">退出</a> &nbsp;&nbsp;<a
                    href="[@spring.url '/merchant/update_password'/]">设置</a></div>
            <div><a class="recharge" href="[@spring.url '/policy/add_policy'/]">新机参保</a></div>
        </div>
    </div>
</header>
<div class="content">
[@mc.showAlert /]

    <!--导航-->
    <div class="nav">
        <ul class="clearfix">
            <li class="no"><a href="[@spring.url '/policy/list'/]">保单查询</a><span class="policy_all green badge"></span>
            </li>
            <li><a href="[@spring.url '/policy/return_list'/]">退单查询</a><span class="policy_back red badge"></span></li>
            <li><a href="[@spring.url '/policy/claim_list'/]">理赔查询</a><span
                    class="policy_claim light-green badge"></span></li>
        </ul>
    </div>
    <!--查询-->
    <div class="query clearfix">
        <form action="/policy/list" method="post">
            <div class="item1"><input type="text" name="beginDate" value="${command.beginDate!}"
                                      placeholder="起始时间 格式(年-月-日 时:分:秒)"/></div>
            <div class="item2"><input type="text" name="endDate" value="${command.endDate!}"
                                      placeholder="截至时间 格式(年-月-日 时:分:秒"/></div>
            <div class="item3"><input type="text" name="policyNo" value="${command.policyNo!}" placeholder="保单号"/></div>
            <div class="item4">
                <button class="but" type="submit">查询</button>
            </div>
        </form>
    </div>
    <!--表单-->
    <div class="grid-box">
        <table class="table table-bordered">
            <thead>
            <tr style="background-color:#f4f4f4;">
                <th scope="row">PICC保单号</th>
                <th>用户姓名</th>
                <th>手机型号</th>
                <th>保单状态</th>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            [#if pagination.data??]
                [#list pagination.data as policy ]
                <tr>
                    <td scope="row">${policy.policyNo!}</td>
                    <td>${policy.insuredName!}</td>
                    <td>${policy.phoneModel!}</td>
                    <td>${(policy.policyStatus.getName())!}</td>
                    <td>
                        [#if policy.policyStatus == "NORMAL"]
                            <a data="[@spring.url '/policy/return_policy?id=${policy.id!}'/]"
                               data-toggle="tooltip" data-placement="top" title="点击申请退单" class="return-policy">
                                <span class="label label-danger">申请退单</span>
                            </a>
                        [/#if]
                    </td>
                </tr>
                [/#list]
            [/#if]
            </tbody>
        </table>
    [#if pagination!]
        [@mc.showPagination '/policy/list' /]
    [/#if]
    </div>
</div>
<script type="text/javascript">
    $.ajax({
        url: "/merchant/get_money",
        type: "post",
        success: function (data) {
            $("#money-span").text(data);
        }
    })
    $.ajax({
        url: "/policy/count",
        type: "post",
        success: function (data) {
            var data;
            if (typeof data == "object") {
                data = data
            } else {
                data = JSON.parse(data)
            }
            console.log(data);
            $(".policy_all").text(data["all"]);
            $(".policy_back").text(data["back"]);
            $(".policy_claim").text(data["claim"]);
        }
    })

    $(".return-policy").on("click", function () {
        if (confirm("确认退单?")) {
            window.location.href = $(this).attr("data");
        } else {
            return;
        }
    })
</script>
</body>

</html>