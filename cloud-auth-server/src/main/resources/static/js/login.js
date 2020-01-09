
var hostname = "//"+ location.hostname+":9527/auth/server/";


//监控当前分辨率
var maxWidth = window.screen.width;


$(function () {

    //如果使电脑
    if (maxWidth > 1360) {
        if (document.body.scrollWidth < (maxWidth * 0.3125)) {
            //隐藏电脑
            document.getElementById("pcdiv").style.display = 'none';
            document.getElementById("phonediv").style.display = null;
        } else {
            document.getElementById("pcdiv").style.display = null;
            document.getElementById("phonediv").style.display = 'none';
        }
    }
    //如果是手机
    if (maxWidth < 1360) {
        document.getElementById("pcdiv").style.display = 'none';
        document.getElementById("phonediv").style.display = null;
    }
});

$(window).resize(function () {
    //如果是电脑
    if (maxWidth > 1360) {
        //隐藏
        if (document.body.scrollWidth < (maxWidth * 0.3125)) {
            document.getElementById("pcdiv").style.display = 'none';
            document.getElementById("phonediv").style.display = null;
        } else {
            document.getElementById("pcdiv").style.display = null;
            document.getElementById("phonediv").style.display = 'none';
        }
    }
});


$(function () {
    var loadingFlag;
    $("#pcsub").click(function () {
        var username = $("#pcusername").val();
        var password = $("#pcpassword").val();
        var remember = $("#pcrem").is(":checked");
        if (!remember)
            remember = false;
        $.ajax({
            url:hostname+ "/auth/login",
            type: "POST",
            // contentType: "application/json;charset=UTF-8",
            data: {
                "username":username,
                "password":password,
                "remember-me":remember
            },
            beforeSend: function () {
                //注意，layer.msg默认3秒自动关闭，如果数据加载耗时比较长，需要设置time
                loadingFlag= layer.msg('登录……', { icon: 16, shade: 0.01,shadeClose:false,time:60000 });
            },
            success: function (data) {
                layer.close(loadingFlag);
                if(data.data == null) {
                    layer.alert("用户名或密码错误",{icon:5});
                    return;
                }
                localStorage.setItem("userDetails",JSON.stringify(data.data));
                window.location.href=data.requestURI;
            }
        })

    });

    $(document).keydown(function (event) {
        if (event.keyCode===13)
            $("#pcsub").triggerHandler('click');
    });



    $("#phsub").click(function () {
        var username = $("#phusername").val();
        var password = $("#phpassword").val();
        var remember = $("#rem").is(":checked");
        if (!remember)
            remember = false;
        $.ajax({
            url: hostname+"/auth/login",
            type: "POST",
            // contentType: "application/json;charset=UTF-8",
            data: {
                "username":username,
                "password":password,
                "remember-me":remember
            },
            beforeSend: function () {
                //注意，layer.msg默认3秒自动关闭，如果数据加载耗时比较长，需要设置time
                loadingFlag= layer.msg('登录……', { icon: 16, shade: 0.01,shadeClose:false,time:60000 });
            },
            success: function (data) {

                if(data.data == null) {
                    layer.alert("用户名或密码错误",{icon:5});
                    return;
                }
                localStorage.setItem("userDetails",data.data);
                localStorage.setItem("sessionId",data.sessionId);
                window.location.href=data.requestURI;
                layer.close(loadingFlag);
            }
        })

    })

});

