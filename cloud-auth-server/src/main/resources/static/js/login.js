
var hostname = "http://127.0.0.1:9527/auth/server";


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
        var remember = $("#rem").is(":checked");
        if (!remember)
            remember = false;
        var userPWD = {
            "username" :username,
            "password" :password,
            "remember-me": remember
        };
        $("#username").val(username);
        $("#password").val(password);
        $("#remember-me").val(remember);
        $("#loginInfoForm").submit();
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
        var userPWD = {
            "username" :username,
            "password" :password,
            "remember-me": remember
        };
        $("#loginInfo").val(JSON.stringify(userPWD));
        $("#loginInfoForm").submit();
    })

});

