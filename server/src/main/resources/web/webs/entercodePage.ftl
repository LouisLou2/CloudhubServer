<!--
Name：CSS响应式动态登录页面
Share：大碗绿豆沙
Link：https://www.bilibili.com/video/BV1XA4y1D7DC
-->

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script
            src="https://kit.fontawesome.com/64d58efce2.js"
            crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="css/login.css" />
    <title>getFile</title>
</head>
<body>
<div class="container">
    <div class="forms-container">
        <div class="signin-signup">
            <form action="/webs/checkshare" method="get" class="sign-in-form">
                <h2 class="title">输入提取码</h2>
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="psw" name="psw",id="psw" placeholder="提取码" />
                </div>
                <input type="submit" value="提取文件" class="btn solid" />
            </form>
        </div>
    </div>
    <div class="panels-container">
        <div class="panel left-panel">
            <img src="css/img/register.svg" class="image" alt="" />
        </div>
    </div>
</div>
<script src="js/login.js"></script>
</body>
</html>