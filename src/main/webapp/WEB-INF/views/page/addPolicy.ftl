<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh_cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>添加保单</title>
    <link type="text/css" href="[@spring.url '/resources/css/bootstrap.min.css'/]" rel="stylesheet">
    <link type="text/css" href="[@spring.url '/resources/css/style.css'/]" rel="stylesheet">
    <link type="text/css" href="[@spring.url '/resources/css/login.css'/]" rel="stylesheet">
    <script type="application/javascript" src="[@spring.url '/resources/js/jquery-2.1.1.min.js'/]"></script>
    <script type="application/javascript" src="[@spring.url '/resources/js/bootstrap.min.js'/]"></script>
    <!-- Nicescroll 滚动条 -->
    <script type="application/javascript" src="[@spring.url '/resources/js/nicescroll/jquery.nicescroll.js'/]"></script>
</head>
<body>
<header>
    <div class="am-heade">
        <h1 style="letter-spacing: 5px;" class="am-header-title">添加保单</h1>
    </div>
</header>

<div class="regist-box">
[@mc.showAlert /]
    <form action="/policy/add_policy" method="post">
        <div class="for-item clearfix">
            <div class="name">品牌：</div>
            <div class="conter">
                <select class="form-control" name="phoneBrand" id="phoneBrand" required autofocus>
                </select>
            </div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">型号：</div>
            <div class="conter">
                <select class="form-control" name="phoneModel" id="phoneModel" required autofocus>
                    <option value="">请选择品牌</option>
                </select>
            </div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">保费：</div>
            <div class="conter"><input class="form-control" disabled value="${command.policyFee!}" name="policyFee"
                                       placeholder="保费" required type="text"
                                       required autofocus>
            </div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">手机串号：</div>
            <div class="conter"><input class="form-control" value="${command.IMEI!}" name="IMEI"
                                       placeholder="手机串号" required autofocus
                                       type="text"></div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">姓名：</div>
            <div class="conter"><input class="form-control" value="${command.insuredName!}" name="insuredName"
                                       placeholder="姓名" required autofocus
                                       type="text"></div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">联系电话：</div>
            <div class="conter"><input class="form-control" value="${command.insuredPhone!}" name="insuredPhone"
                                       pattern="^1[345678][0-9]{9}$"
                                       placeholder="手机号码" required
                                       autofocus type="text"></div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">证件类型：</div>
            <div class="conter">
                <select class="form-control" name="idType" id="idType" required autofocus>
                </select>
            </div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">证件号码：</div>
            <div class="conter"><input class="form-control" value="${command.idNumber!}" placeholder="证件号码"
                                       name="idNumber" required autofocus
                                       type="text"></div>
            <span class="red-symbol">*</span>
        </div>
        <div class="for-item clearfix">
            <div class="name">上传新机图片：</div>
            <div class="conter">
                <a class="btn cctp-but sign-btn btn-sm img-upload">点击添加图片</a>
                <section class="overflow-auto nice-scrollbar">
                    <ul class="img-box">
                    </ul>
                </section>
            </div>
            <span class="red-symbol">*</span>
        </div>

        <div class="botm-but">
            <button class="btn btn-block sign-btn" type="submit">确认</button>
            <a class="btn btn-block sign-btn" href="[@spring.url "/policy/list"/]">取消</a>
        </div>
    </form>
</div>

[#--文件上传进度--]
<div class="file_upload_load"></div>
<script type="application/javascript" src="[@spring.url '/resources/js/layer/layer.js'/]"></script>
<script src="[@spring.url '/resources/js/upload/webuploader.js'/]"></script>
<script type="text/javascript">

    ajax_phoneBrand();
    ajax_idType();
    function ajax_phoneBrand() {
        $.ajax({
            url: "/phone_brand/list",
            type: "post",
            success: function (data) {
                var results = data;
                if (typeof data == "object") {
                    results = data
                } else {
                    results = JSON.parse(data)
                }
                if (undefined != results) {
                    var $this = $("#phoneBrand");
                    $this.empty();
                    $this.append("<option value='' selected>请选择</option>");
                    $.each(results, function (a, b) {
                        $this.append("<option value='" + b.id + "'>" + b.name + "</option>")
                    });
                    $("#phoneBrand").change(function () {
                        ajax_phoneModel($(this).val());
                    })
                }
            }
        })
    }

    function ajax_phoneModel(id) {
        $("#phoneModel").empty();
        $.ajax({
            url: "/policy_fee/list",
            type: "post",
            data: "phoneBrand=" + id,
            success: function (data) {
                var results = data;
                if (typeof data == "object") {
                    results = data
                } else {
                    results = JSON.parse(data)
                }
                console.log(results);
                if (undefined != results) {
                    var $this = $("#phoneModel");
                    $this.empty();
                    $this.append("<option value=''>请选择</option>");
                    $.each(results, function (a, b) {
                        $this.append("<option value='" + b.id + "' data='" + b.policyFee + "'>" + b.phoneModel + "</option>")
                    });
                }
            }
        })
    }

    $("#phoneModel").change(function () {
        $("input[name='policyFee']").val($(this).find("option:selected").attr("data"));
    })

    function ajax_idType() {
        $.ajax({
            url: "/id_type/list",
            type: "post",
            success: function (data) {
                var results = data;
                if (typeof data == "object") {
                    results = data
                } else {
                    results = JSON.parse(data)
                }
                if (undefined != results) {
                    var $this = $("#idType");
                    $this.empty();
                    $this.append("<option value=''>请选择</option>");
                    $.each(results, function (a, b) {
                        $this.append("<option value='" + b.id + "'>" + b.name + "</option>")
                    });
                }
            }
        })
    }

    // 初始化Web Uploader
    uploader = WebUploader.create({
        // 自动上传。
        auto: true,
        // 文件接收服务端。
        server: '/upload/img_upload',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '.img-upload',
        // 只允许选择文件，可选。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
    // 当有文件添加进来的时候
    uploader.on('fileQueued', function (file) {
        uploader.makeThumb(file, function (error, src) {
            if (error) {
                return;
            }
//                alert(src);
        });
    });
    uploader.on('uploadProgress', function (file, percentage) {
        $('html').addClass('.file_upload_mask');
        $('.file_upload_load').show();
    });

    uploader.on('uploadSuccess', function (file, result) {
        $('html').removeClass('.file_upload_mask');
        $('.file_upload_load').hide();
        layer.msg("上传成功！", {icon: 1});
        var url = result.files[0].url;
        var $ul = $(".img-box");
        $ul.append('<li><img src=' + url + '/><input type="hidden" value=' + url + ' name="insuredBeginPicture"/><div><a href="#" class="btn btn-block sign-btn del-img">删除</a></div></li>');
    });

    uploader.on('uploadError', function (handler) {
        $('html').removeClass('.file_upload_mask');
        $('.file_upload_load').hide();
        layer.msg("上传失败！");
    });

    $(".img-box").on("click", "a", function () {
        $(this).parent().parent().remove();
    })
</script>
</body>

</html>