<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>登录</title>
        <link rel="stylesheet" href="res/layui/css/layui.css">
		<link rel="stylesheet" href="res/css/global.css">
		<script src="res/layui/layui.js"></script>
    </head>

    <body>
        <div class="main layui-clear">
            <div class=" layui-container fly-marginTop">
                <div class="fly-panel fly-panel-user" pad20="">
                    <div class="layui-tab layui-tab-brief" lay-filter="user">
                        <!-- tab栏 -->
                        <ul class="layui-tab-title">
                            <li class="layui-this">登入</li>
                            <li>
                                <a href="<s:url value="register"/>">注册</a>
                            </li>
                        </ul>

                        <!-- 登录框 -->
                        <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                            <div class="layui-tab-item layui-show">
                                <div class="layui-form layui-form-pane">
                                    <form action="login" method="post">
                                        <div class="layui-form-item"> <label for="L_email" class="layui-form-label">用户名</label>
                                            <div class="layui-input-inline"> <input type="text" id="username" name="username" required=""  placeholder="请输入用户名" lay-verify="required" autocomplete="off" class="layui-input"> </div>
                                        </div>
                                        <div class="layui-form-item"> <label for="L_pass" class="layui-form-label">密码</label>
                                            <div class="layui-input-inline"> <input type="password" id="password" name="password"  placeholder="请输入密码" required="" lay-verify="required" autocomplete="off" class="layui-input"> </div>
                                        </div>
                                        <div class="layui-form-item"> <input type="submit" class="layui-btn" lay-filter="*" lay-submit="" title="立即登录" /> <span style="padding-left:20px;"> 
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>