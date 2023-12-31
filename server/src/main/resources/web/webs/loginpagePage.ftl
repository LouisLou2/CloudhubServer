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
    <title>登录</title>
</head>
<body>
<div class="container">
    <div class="forms-container">
        <div class="signin-signup">
            <form action="/webs/storeuserinfo" class="sign-in-form">
                <h2 class="title">登录</h2>
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" name="username" placeholder="用户名" />
                </div>
                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" name="psw" placeholder="密码" />
                </div>
                <input type="submit" value="立即登录" class="btn solid" />
                <p class="social-text">通过其他方式</p>
                <div class="social-media">
                    <a href="#" class="social-icon">
                        <i class="fab fa-qq"></i>
                    </a>
                    <a href="#" class="social-icon">
                        <i class="fab fa-weixin"></i>
                    </a>
                    <a href="#" class="social-icon">
                        <i class="fab fa-weibo"></i>
                    </a>
                    <a href="#" class="social-icon">
                        <i class="fab fa-alipay"></i>
                    </a>
                </div>
            </form>
            <form action="#" class="sign-up-form">
                <h2 class="title">注册</h2>
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="用户名" />
                </div>
                <div class="input-field">
                    <i class="fas fa-envelope"></i>
                    <input type="email" placeholder="邮箱" />
                </div>
                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="密码" />
                </div>
                <input type="submit" class="btn" value="立即注册" />
                <p class="social-text">通过其他方式</p>
                <div class="social-media">
                    <a href="#" class="social-icon">
                        <i class="fab fa-qq"></i>
                    </a>
                    <a href="#" class="social-icon">
                        <i class="fab fa-weixin"></i>
                    </a>
                    <a href="#" class="social-icon">
                        <i class="fab fa-weibo"></i>
                    </a>
                    <a href="#" class="social-icon">
                        <i class="fab fa-alipay"></i>
                    </a>
                </div>
            </form>
        </div>
    </div>

    <div class="panels-container">
        <div class="panel left-panel">
            <div class="content">
                <h3>加入我们</h3>
                <p>
                    加入我们，成为本站的一份子。
                </p>
                <button class="btn transparent" id="sign-up-btn">
                    去注册
                </button>
            </div>
            <img src="css/img/log.svg" class="image" alt="" />
        </div>
        <div class="panel right-panel">
            <div class="content">
                <h3>已有帐号？</h3>
                <p>
                    立即登录已有帐号，享受独家权益。
                </p>
                <button class="btn transparent" id="sign-in-btn">
                    去登录
                </button>
            </div>
            <img src="css/img/register.svg" class="image" alt="" />
        </div>
    </div>
</div>
<script src="js/login.js"></script>
</body>
</html>
