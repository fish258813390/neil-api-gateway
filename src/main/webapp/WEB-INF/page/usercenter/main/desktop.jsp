<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="/include.jsp"></jsp:include>
<html>
<head>
    <title>后台管理平台</title>
    <script type="text/javascript">
        showLoad();
        $(function () {
            <%--var tb = $('#easytabs').tabs({--%>
                <%--onContextMenu: function (e, title, index) {--%>
                    <%--e.preventDefault();--%>
                    <%--$('#tabmenu').menu('show', {--%>
                        <%--left: e.pageX, top: e.pageY--%>
                    <%--});--%>
                <%--}--%>
            <%--});--%>
            <%--$.each(tb, function (i, n) {--%>
                <%--$('#easytabs').tabs('close', 0);--%>
            <%--});--%>
            <%--$("#updatepwd").dialog({--%>
                <%--closed: true,--%>
                <%--modal: true,--%>
                <%--resizable: true--%>
            <%--});--%>
            <%--currentTime();--%>

            <%--var uls = $("#accordion").find("ul");--%>
            <%--for (var i = 0; i < uls.length; ++i) {--%>
                <%--var id = $(uls[i]).attr("id");--%>
                <%--$(uls[i]).tree({--%>
                    <%--url: "<c:url value='/usercenter/baseInfo/menu/queryModuleByParentId.do?parentId="+id+"'/>",--%>
                    <%--animate: false,--%>
                    <%--dnd: true,--%>
                    <%--lines: true,--%>
                    <%--onDblClick: function (node) {--%>
                        <%--$(this).tree(node.state === 'closed' ? 'expand' : 'collapse', node.target);--%>
                        <%--node.state = node.state === 'closed' ? 'open' : 'closed';--%>
                    <%--},--%>
                    <%--onClick: function (node) {--%>
                        <%--if (node.attributes.url != "") {--%>
                            <%--toaction(node.text, node.attributes.url);--%>
                        <%--}--%>
                    <%--},--%>
                    <%--onBeforeExpand: function (node, param) {--%>
                        <%--$(this).tree('options').url = "<c:url value='/usercenter/baseInfo/menu/queryModuleByParentId.do?parentId="+node.id+"'/>";--%>
                    <%--}--%>
                <%--});--%>
            <%--}--%>
        });

        function toaction(title, url) {
            url = $.trim(url);
            if (url == null || url == "" || url == undefined) {
                $(this).tree('expandAll');
                return;
            }
            var easytabs = $('#easytabs');
            var tabs = easytabs.tabs('tabs');
            if (tabs.length > 5) {
                $('#easytabs').tabs('close', 1);
            }
            var content = $('<iframe/>', {
                "src": "<c:url value='/"+url+"' />",
                "scrolling": "auto",
                "frameborder": "0",
                "width": "100%",
                "height": "100%"
            });
            easytabs.tabs('add', {
                title: title,
                content: content,
                closable: true
            });
        }

        function clstab(type) {
            var tabs = $('#easytabs').tabs('tabs');
            var tab = $('#easytabs').tabs('getSelected');
            var curr_index = $('#easytabs').tabs('getTabIndex', tab);
            if (type == '1') {
                $('#easytabs').tabs('close', curr_index);
            } else if (type == '2') {
                $.each(tabs, function (i, n) {
                    if (i < curr_index) {
                        $('#easytabs').tabs('close', 0);
                    }
                    if (i > curr_index) {
                        $('#easytabs').tabs('close', 1);
                    }
                });
            } else if (type == '3') {
                $.each(tabs, function (i, n) {
                    $('#easytabs').tabs('close', 0);
                });
            }
        }

        function editMyPassword() {
            $("<div></div>").dialog({
                id: 'editMyPasswordDialog',
                width: 500,
                height: 300,
                closed: false,
                modal: true,
                href: "<c:url value='/usercenter/baseInfo/operator/editMyPasswordPage.do'/>",
                resizable: false,
                title: '修改我的登录密码',
                onClose: function () {
                    $(this).dialog('destroy');
                },
                buttons: [{
                    text: '保存',
                    handler: function () {
                        doSaveEditMyPassword();
                    }
                }, {
                    text: '取消',
                    handler: function () {
                        $("#editMyPasswordDialog").dialog("destroy");
                    }
                }]
            });
        }

        function doSaveEditMyPassword() {
            if (validateEditMyPasswordData()) {
                $("#editMyPasswordDataForm").form("submit", {
                    method: "post",
                    success: function (data) {
                        var obj = jQuery.parseJSON(data);
                        if (obj.status) {
                            $('#editMyPasswordDialog').dialog("destroy");
                            topmsg("操作成功", "修改密码成功");
                        } else {
                            topmsg("操作失败", obj.externalMessage);
                        }
                    }
                });
            }
        }

        function editMyDetailInfo() {
            $("<div></div>").dialog({
                id: 'editMyDetailInfoDialog',
                width: 600,
                height: 500,
                closed: false,
                modal: true,
                href: "<c:url value='/usercenter/baseInfo/operator/editMyPasswordPage.do'/>",
                resizable: false,
                title: '修改我的信息',
                onClose: function () {
                    $(this).dialog('destroy');
                },
                buttons: [{
                    text: '保存',
                    handler: function () {
                        doSaveEditMyDetailInfo();
                    }
                }, {
                    text: '取消',
                    handler: function () {
                        $("#editMyDetailInfoDialog").dialog("destroy");
                    }
                }]
            });
        }

        function doSaveEditMyDetailInfo() {
            if (validateEditMyDetailInfoData()) {
                $("#editMyDetailInfoDataForm").form("submit", {
                    method: "post",
                    success: function (data) {
                        var obj = jQuery.parseJSON(data);
                        if (obj.status) {
                            $('#editMyDetailInfoDialog').dialog("destroy");
                            topmsg("操作成功", "修改密码成功");
                        } else {
                            topmsg("操作失败", obj.externalMessage);
                        }
                    }
                });
            }
        }

        function logout() {
            if (confirm("是否确定退出？")) {
                window.location.href = "<c:url value='/usercenter/main/logout.do'/>";
            }
        }

        function currentTime() {
            var today = new Date();
            var hour = today.getHours();
            var minute = today.getMinutes();
            if (minute < 10) {
                minute = "0" + minute;
            }
            var second = today.getSeconds();
            if (second < 10) {
                second = "0" + second;
            }
            var te = today.getDate();
            var month = today.getMonth() + 1;
            var year = today.getFullYear();
            $("#time").html(year + "-" + month + "-" + te + " " + hour + ":" + minute + ":" + second);
            setTimeout('currentTime()', 500);
        }

        function topmsg(title, msg) {
            window.top.$.messager.show({
                title: title,
                msg: msg,
                timeout: 3000,
                showType: 'slide'
            });
        }
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height:50px">
    <div class="icon-bj1">
        <div class="icon-logo">后台管理平台</div>
        <div class="icon-right1">
            <a class="easyui-menubutton" data-options="menu:'#addmenu'">${operator.operatorName}</a>
            <span id="time"></span>
            <div id="addmenu" style="width:80px;display: none">
                <div onclick="editMyPassword()">修改密码</div>
                <div onclick="logout()">退出</div>
            </div>
        </div>
    </div>
</div>

<div data-options="region:'west',split:true,title:'菜单导航'" style="width:220px;">
    <div id="accordion" class="easyui-accordion" data-options="fit:true,border:false,animate:false">
        <c:forEach items="${menus}" var="menu">
            <div title="${menu.menuName}">
                <ul id="${menu.id}" class="easyui-tree"></ul>
            </div>
        </c:forEach>
    </div>
</div>

<div data-options="region:'center',noheader:true">
    <div data-options="fit:true,border:false" class="easyui-tabs" id="easytabs">
        <div title="工作台" data-options="closable:true">
            <iframe id="pageframe" frameborder="0" marginheight="0" marginwidth="0" width="100%" height="100%"
                    scrolling="auto"></iframe>
        </div>
    </div>
</div>

<div id="tabmenu" class="easyui-menu" style="width:120px; display: none">
    <div onclick="clstab('1');">关闭当前</div>
    <div onclick="clstab('2');">关闭其他</div>
    <div onclick="clstab('3');">关闭所有</div>
</div>
</body>
</html>