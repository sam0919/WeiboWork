<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>注册</title>
        <link rel="stylesheet" href="res/layui/css/layui.css">
        <link rel="stylesheet" href="res/css/global.css">
        <script src="res/layui/layui.js"></script>
    </head>
    <body>
        <!-- header -->
        <s:include value="header.jsp"></s:include>
        <div style="background-color: #c8c8c8">
            <!-- 单条信息-->
            <s:iterator value="weiboList" var="var">
                <div class="layui-card">
                    <div class="layui-card-header">卡片面板</div>
                    <div class="layui-card-body">
                        卡片式面板面板通常用于非白色背景色的主体内<br>
                        从而映衬出边框投影
                        <s:property value="msgId" />
                        <s:property value="id" />
                        <s:property value="imagePath" />
                        <s:property value="weibo.text" />
                    </div>
                </div>
            </s:iterator>
        </div>
    </body>
</html>