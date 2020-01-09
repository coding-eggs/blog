var maxWidth = window.screen.width;

var loadingFlag;

var hostname = "//" + location.hostname+":9527";

$(function () {

    /**
     * 统一加载动画
     */
    $.ajaxSetup({
        beforeSend: function () {
            //注意，layer.msg默认3秒自动关闭，如果数据加载耗时比较长，需要设置time
            loadingFlag = layer.msg('加载……', {icon: 16, shade: 0.01, shadeClose: false, time: 60000});
        }
    });

    /*
    加载用户头像，用户信息
     */
    $.ajax({
        type:"get",
        url: hostname + "/blog/user/is_expired",
        success: function (data) {
            //判断会话是否过期
            var isExpired = data.data.expired;
            //获取当前会话的userinfo
            if (isExpired) {
                localStorage.setItem("userDetails", JSON.stringify(data.data.userDetails));
                var userinfo = JSON.parse(localStorage.getItem("userDetails"));
                document.getElementById("login").style.display = 'none';
                $(".figureURL")[0].src = userinfo.figureUrl;
                $(".figureURL")[1].src = userinfo.figureUrl;
                $("#username").html(userinfo.nickname);
                document.getElementById("userDetails").style.display = null;

            } else {
                localStorage.setItem("userDetails", null);
            }
            layer.close(loadingFlag);

        }
    });


    /*
    加载主页文章
     */
    $.ajax({
        url: hostname + "/write/get_index_article",
        type: "get",
        success: function (data) {
            var articleObj = $("#index-article-list");
            var contentTopObj = $(".contenttop");
            data = data.data;

            // contentTopObj.append(
            //     '                <a id=' + data[0].articleId + '>' +
            //     '                    <strong>博主置顶</strong>' +
            //     '                    <h3 class="title">' + data[0].title + '</h3>' +
            //     '                    <p class="overView">' + data[0].markdownContent + '</p>' +
            //     '                </a>'
            // );
            //
            // document.getElementById(data[0].articleId).href = "https://ailuoli.cn/article_details?articleId=" + data[0].articleId;

            for (var i = 0; i < data.length; i++) {

                var hrefs = "https://127.0.0.1：9527/article_details?articleId=" + data[i].articleId;

                if(data[i].isTop ===1){
                    contentTopObj.append(
                        '                <a id=' + data[i].articleId + '>' +
                        '                    <strong>博主置顶</strong>' +
                        '                    <h3 class="title">' + data[i].title + '</h3>' +
                        '                    <p class="overView">' + data[i].markdownContent + '</p>' +
                        '                </a>'
                    );
                }else {
                    articleObj.append(
                        '<div class="panel panel-default">' +
                        '     <div class="panel-body">' +
                        '           <div class="contentleft">' +
                        '                <h4>' +
                        '                      <a class="title article_details" id=' + data[i].articleId + '>' + data[i].title + '</a>' +
                        '                </h4>' +
                        '                <p>' +
                        '                      <a class="label label-default">' + data[i].keyWord + '</a>' +
                        '                </p>' +
                        '                <p class="overView">' + data[i].markdownContent + '</p>' +
                        '                <p>' +
                        '                    <span class="count">' +
                        '                          <i class="glyphicon glyphicon-user"></i>' +
                        '                          <a href="#">' + data[i].blogUser.nickname + '</a>' +
                        '                    </span>' +
                        '                    <span class="count">' +
                        '                          <i class="glyphicon glyphicon-eye-open"></i>阅读:' + data[i].visitCount +
                        '                    </span>' +
                        '                    <span class="count">' +
                        '                          <i class="glyphicon glyphicon-comment"></i>评论:' + data[i].commentCount +
                        '                    </span>' +
                        '                    <span class="count">' +
                        '                          <i class="glyphicon glyphicon-time"></i>' + data[i].createTimeStr +
                        '                    </span>' +
                        '                 </p>' +
                        '             </div>' +
                        '      </div>' +
                        '</div>');
                }




                document.getElementById(data[i].articleId).href = hrefs;
            }

            layer.close(loadingFlag);
        }
    });

    var noticeObj = $("#notice");

    var kewWordObj = $("#keyWordList");

    var newArticleTitle = $("#new_article_title");

    $.ajax({
        url: hostname + "/index/preview/get_right_preview",
        type: "get",
        success: function (data) {
            data = data.data;
            var noticeMes = data.notice;
            var keyWordMes = data.key_word;
            var articleTitle = data.article;
            noticeObj.append(
                '    <a href="#">' +
                '       <strong>站点公告</strong>' +
                '       <h3 class="title">' + noticeMes.noticeTitle + '</h3>' +
                '       <p class="overView">' + noticeMes.noticeContent + '</p>' +
                '    </a>'
            );

            for (var i = 0; i < keyWordMes.length; i++) {
                kewWordObj.append('<a class="label label-default">' + keyWordMes[i] + '</a>')
            }
            for (var i = 0; i < articleTitle.length; i++) {
                newArticleTitle.append('<li><a href="#">' + articleTitle[i].title + '</a></li>')
            }
            layer.close(loadingFlag);
        }
    });


    if (maxWidth > 1279) {
        if (document.body.scrollWidth < 0.567 * maxWidth) {
            //隐藏小搜索
            document.getElementById("search").style.display = "none";
            //暴漏大搜索
            document.getElementById("headSearch").style.display = null;
        } else {
            document.getElementById("search").style.display = null;
            document.getElementById("headSearch").style.display = 'none';
        }

        if (document.body.scrollWidth < 0.515 * maxWidth) {
            document.getElementById("right").style.display = 'none';
        } else {
            document.getElementById("right").style.display = null;
        }
    } else {
        //隐藏小搜索
        document.getElementById("search").style.display = "none";
        document.getElementById("headSearch").style.display = null;
        document.getElementById("right").style.display = 'none';
    }


});

$(window).resize(function () {

    if (maxWidth > 1279) {
        if (document.body.scrollWidth < 0.567 * maxWidth) {
            //隐藏小搜索
            document.getElementById("search").style.display = "none";
            document.getElementById("headSearch").style.display = null;
        } else {
            document.getElementById("search").style.display = null;
            document.getElementById("headSearch").style.display = 'none';
        }


        if (document.body.scrollWidth < 0.515 * maxWidth) {
            document.getElementById("right").style.display = 'none';
        } else {
            document.getElementById("right").style.display = null;
        }
    } else {
        //隐藏小搜索
        document.getElementById("search").style.display = "none";
        document.getElementById("headSearch").style.display = null;
        document.getElementById("right").style.display = 'none';
    }

});
$(function () {
    $(".dropdown").mouseover(function () {
        $(this).addClass("open");
    });

    $(".dropdown").mouseleave(function () {
        $(this).removeClass("open");
    })
});
