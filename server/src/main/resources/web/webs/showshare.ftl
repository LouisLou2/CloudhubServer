<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script
            src="https://kit.fontawesome.com/64d58efce2.js"
            crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="css/showfile.css" />
    <style>
        .header {
            position: fixed;
            top: 0;
            /*left:50% ;*/ /* 使用 right 属性将元素右对齐 */
            width: 100%;
            padding: 25px 13%;
            /*background: transparent;*/
            display: flex;
            justify-content: space-between;
            align-items: center;
            z-index: 100;
            background-color: #4d84e2;
        }
        .navbar a{
            left:200%;
            position: relative;
            font-size: 20px;
            font-family: "Microsoft YaHei", sans-serif;
            font-weight: bold;
            color: gainsboro;
            margin-right: 30px;
            text-decoration: none;
        }
        .navbar a::after{
            content: "";
            position: absolute;
            left: 0;
            width: 100%;
            height: 2px;
            background: gainsboro;
            bottom: -5px;
            border-radius: 5px;
            transform: translateY(10px);
            opacity: 0;
            transition: .5s ease;
        }
        .navbar a:hover:after{
            transform: translateY(0);
            opacity: 1;
        }
        .fileimg{
            width: 200px;
        }
        .file-info {
            background-color: #f5f5f5; /* 背景颜色 */
            padding: 10px; /* 内边距 */
            border: 1px solid #ddd; /* 边框 */
            border-radius: 5px; /* 圆角 */
            width: 300px; /* 宽度 */
        }

        .file-name,
        .file-size {
            display: block; /* 将文本显示为块级元素 */
            font-size: 16px; /* 字体大小 */
            margin-bottom: 5px; /* 底部间距 */
        }

        .file-name {
            color: #333; /* 文件名文本颜色 */
            font-weight: bold; /* 粗体 */
        }

        .file-size {
            color: #777; /* 文件大小文本颜色 */
        }

    </style>
    <title>响应式登录页面</title>
</head>
<body>
<header class="header">
    <nav class="navbar">
        <a href="#">首页</a>
        <a href="#">关于</a>
        <a href="#">登录</a>
        <a href="#">注册</a>
        <a href="#">帮助</a>
    </nav>
</header>
<div class="container">
    <form action="/webs/reservefile" class="sign-in-form">
        <h2 class="title">保存文件</h2>
        <img src="img/file.svg" class="fileimg">
        <div class="file-info">
            <span class="file-name">文件名：${name}</span>
            <span class="file-size">文件大小：${size}</span>
        </div>
        <input type="submit" value="转存文件" class="btn solid" />
    </form>
</div>
<script src="js/login.js"></script>
</body>
</html>