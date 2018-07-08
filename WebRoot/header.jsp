<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="header">
    <div class="main">
        <!-- banner -->
        <a class="title" href="index.html" target="_parent" title="浦江"> 
            <i class="iconfont layui-icon-login-weibo layui-hide-xs" style="font-size: 22px;"></i>
            微博
        </a>

        <!-- nav -->
        <div class="nav">
            <a class="nav-this" href="index.html">
                <i class="iconfont icon-wenda"></i>你问我答</a>
        </div>

        <!-- 右侧用户相关nav -->
        <div class="nav-user">
            <s:if test="%{#variable=='String 1'}">
                <!-- 已登录 -->
                <a class="avatar" href="">
                    <img src="res/images/avatar/11.jpg">
                    <cite>贤心</cite>
                </a>
                <div class="nav">
                    <a href=""><i class="iconfont icon-tuichu" style="top: 0; font-size: 22px;"></i>退出</a>
                </div>
            </s:if>
            <s:else>
                <!-- 未登录 -->
                <a class="iconfont icon-touxiang layui-hide-xs" style="margin-top: 4px; display: inline-block;"></a>
                <div class="nav"  style="font-size:14px;color: white;margin-top: -5px;margin-left: 1px; "  />
                    <a href="<s:url value="login" />"  target="_parent" >登录</a>
                    <a href="<s:url value="signup" />" target="_parent" >注册</a>
                </div>
            </s:else>
        </div>  
    </div>
</div>


