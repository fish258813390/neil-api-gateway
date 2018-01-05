<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台管理系统</title>
    <%--<link rel="icon" type="image/x-icon" href="<c:url value ='/platform-resource/images/favicon.ico'/>"/>--%>
    <link rel="stylesheet" type="text/css" href="<c:url value='/platform-resource/easyui/themes/basic/easyui.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/platform-resource/easyui/themes/icon.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/platform-resource/common.css'/>">
    <style type="text/css">
        table.tb_compare {
            border-collapse: collapse;
            border-spacing: 0px;
            width: 100%;
        }

        table.tb_compare th {
            white-space: nowrap;
        }

        table.tb_compare th,
        table.tb_compare td {
            border: 1px solid #ccc;
            padding: 0.5em 1em;
        }
    </style>
    <script type="text/javascript" src="<c:url value='/platform-resource/jquery-1.8.0.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/platform-resource/easyui/jquery.easyui.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/platform-resource/easyui/easyui-lang-zh_CN.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/platform-resource/easyui/validate-methods.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/platform-resource/commons/jsMap.js'/>"></script>
    <script type="text/javascript">
        var _loadmodal = "<div class='mask' id='loadmodal'><div class='loding'>正在努力加载中。。。</div></div>";

        function showLoad() {
            window.document.write(_loadmodal);
            window.onload = function () {
                document.getElementById("loadmodal").style.display = "none";
            }
        }

        var aisleLoader = function aisleComboboxLoader(param, success, error) {
            var q = param.q || '';
            $.ajax({
                url: "<c:url value='/agentRecharge/getAisle.do'/>",
                dataType: 'json',
                type: 'post',
                data: {
                    aisleName: q
                },
                success: function (data) {
                    var items = $.map(data, function (item, index) {
                        return {
                            id: item.id,
                            aislename: item.aislename
                        };
                    });
                    success(items);
                },
                error: function () {
                    error.apply(this, arguments);
                }
            });
        }

        $.ajaxSetup({
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            dataFilter: function (data, type) {
                if (data == 'no_session') {
                    window.top.$.messager.show({
                        title: "系统消息",
                        msg: "登录超时，请您重新登录",
                        timeout: 3000,
                        showType: 'slide'
                    });
                    setTimeout(function () {
                        window.top.location.href = '${sessionScope.platform_loing_page_url}';
                    }, 3000);
                } else if (data == 'no_access') {
                    window.top.$.messager.show({
                        title: "系统消息",
                        msg: "您没有权限访问该模块",
                        timeout: 3000,
                        showType: 'slide'
                    });
                } else {
                    return data;
                }
            }
        });

        function validateDate() {
            var startTime = $('#startTime').datebox("getValue");
            var endTime = $('#endTime').datebox("getValue");
            if (startTime == "" && endTime != "") {
                alert("请选择开始时间");
                return false;
            }
            if (startTime != "" && endTime == "") {
                alert("请选择结束时间");
                return false;
            }
            if (startTime.length == 10 || endTime.length == 10) {
                startTime = startTime.substring(0, 10);
                endTime = endTime.substring(0, 10);
            }
            if (startTime > endTime) {
                alert("开始时间不能大于结束时间");
                return false;
            }

            var start = new Date(startTime.replace(/-/g, "/"));
            var end = new Date(endTime.replace(/-/g, "/"));
            var d3 = end.getTime() - start.getTime();
            var d4 = 1000 * 3600 * 24 * 31;//（一个月按31天计算）
            if (d3 > d4) {
                topmsg('系统消息', "日期跨度超出1个月");
                return false;
            }
            return true;
        }

        function validateDateDubbo() {
            var startTime = $('#page_query_startTime').datebox("getValue");
            var endTime = $('#page_query_endTime').datebox("getValue");
            if (startTime == "" && endTime != "") {
                alert("请选择开始时间");
                return false;
            }
            if (startTime != "" && endTime == "") {
                alert("请选择结束时间");
                return false;
            }
            if (startTime.length == 10 || endTime.length == 10) {
                startTime = startTime.substring(0, 10);
                endTime = endTime.substring(0, 10);
            }
            if (startTime > endTime) {
                alert("开始时间不能大于结束时间");
                return false;
            }

            var start = new Date(startTime.replace(/-/g, "/"));
            var end = new Date(endTime.replace(/-/g, "/"));
            var d3 = end.getTime() - start.getTime();
            var d4 = 1000 * 3600 * 24 * 31;//（一个月按31天计算）
            if (d3 > d4) {
                topmsg('系统消息', "日期跨度超出1个月");
                return false;
            }
            return true;
        }

        function topmsg(title, msg) {
            window.top.$.messager.show({
                title: title,
                msg: msg,
                timeout: 3000,
                showType: 'slide'
            });
        }

        function getRegionObject(docId, regionCode) {
            if (regionCode == null || regionCode == "") {
                return;
            }
            $.ajax({
                url: "<c:url value='/system/base/configure/region/queryByRegionCode.do'/>",
                dataType: 'json',
                type: 'post',
                data: {
                    regionCode: regionCode
                },
                success: function (data) {
                    if (data.status) {
                        $("#" + docId).combobox("setValue", regionCode);
                        $("#" + docId).combobox("setText", data.result.regionName);
                    }
                }
            });
        }

        function initRegionCombobox(provinceCodeDocId, cityCodeDocId, countyCodeDocId, townCodeDocId, streetCodeDocId) {
            if (provinceCodeDocId != null) {
                $('#' + provinceCodeDocId).combobox({
                    loader: function (param, success, error) {
                        var q = param.q || '';
                        $.ajax({
                            url: "<c:url value='/system/base/configure/region/listRegionByParentCodeAndLikeName.do'/>",
                            dataType: 'json',
                            type: 'post',
                            data: {
                                regionParentCode: "",
                                regionName: q
                            },
                            success: function (data) {
                                var items = $.map(data, function (item, index) {
                                    return {
                                        regionCode: item.regionCode,
                                        regionName: item.regionName
                                    };
                                });
                                success(items);
                            },
                            error: function () {
                                error.apply(this, arguments);
                            }
                        });
                    },
                    mode: 'remote',
                    width: 200,
                    heigth: 30,
                    valueField: 'regionCode',
                    textField: 'regionName',
                    onSelect: function (record) {
                        if (cityCodeDocId != null && cityCodeDocId != "") {
                            $("#" + cityCodeDocId).combobox("setValue", "");
                            $("#" + cityCodeDocId).combobox("setText", "");
                            $("#" + cityCodeDocId).combobox("reload");
                        } else {
                            return;
                        }

                        if (countyCodeDocId != null && countyCodeDocId != "") {
                            $("#" + countyCodeDocId).combobox("setValue", "");
                            $("#" + countyCodeDocId).combobox("setText", "");
                        } else {
                            return;
                        }

                        if (townCodeDocId != null && townCodeDocId != "") {
                            $('#' + townCodeDocId).combobox("setValue", "");
                            $('#' + townCodeDocId).combobox("setText", "");
                        } else {
                            return;
                        }

                        if (streetCodeDocId != null && streetCodeDocId != "") {
                            $('#' + streetCodeDocId).combobox("setValue", "");
                            $('#' + streetCodeDocId).combobox("setText", "");
                        } else {
                            return;
                        }
                    }
                });
            }

            if (cityCodeDocId != null && cityCodeDocId != "") {
                $('#' + cityCodeDocId).combobox({
                    loader: function (param, success, error) {
                        var parentCode = $("#" + provinceCodeDocId).combobox("getValue");
                        if (parentCode == null || parentCode == "") {
                            return;
                        }
                        var q = param.q || '';
                        $.ajax({
                            url: "<c:url value='/system/base/configure/region/listRegionByParentCodeAndLikeName.do'/>",
                            dataType: 'json',
                            type: 'post',
                            data: {
                                regionParentCode: parentCode,
                                regionName: q
                            },
                            success: function (data) {
                                var items = $.map(data, function (item, index) {
                                    return {
                                        regionCode: item.regionCode,
                                        regionName: item.regionName
                                    };
                                });
                                success(items);
                            },
                            error: function () {
                                error.apply(this, arguments);
                            }
                        });
                    },
                    mode: 'remote',
                    width: 200,
                    heigth: 30,
                    valueField: 'regionCode',
                    textField: 'regionName',
                    onSelect: function (record) {
                        if (countyCodeDocId != null && countyCodeDocId != "") {
                            $("#" + countyCodeDocId).combobox("setValue", "");
                            $("#" + countyCodeDocId).combobox("setText", "");
                            $('#' + countyCodeDocId).combobox("reload");
                        } else {
                            return;
                        }

                        if (townCodeDocId != null && townCodeDocId != "") {
                            $('#' + townCodeDocId).combobox("setValue", "");
                            $('#' + townCodeDocId).combobox("setText", "");
                        } else {
                            return;
                        }

                        if (streetCodeDocId != null && streetCodeDocId != "") {
                            $('#' + streetCodeDocId).combobox("setValue", "");
                            $('#' + streetCodeDocId).combobox("setText", "");
                        } else {
                            return;
                        }
                    }
                });
            }

            if (countyCodeDocId != null && countyCodeDocId != "") {
                $("#" + countyCodeDocId).combobox({
                    loader: function (param, success, error) {
                        var parentCode = $("#" + cityCodeDocId).combobox("getValue");
                        if (parentCode == null || parentCode == "") {
                            return;
                        }
                        var q = param.q || '';
                        $.ajax({
                            url: "<c:url value='/system/base/configure/region/listRegionByParentCodeAndLikeName.do'/>",
                            dataType: 'json',
                            type: 'post',
                            data: {
                                regionParentCode: parentCode,
                                regionName: q
                            },
                            success: function (data) {
                                var items = $.map(data, function (item, index) {
                                    return {
                                        regionCode: item.regionCode,
                                        regionName: item.regionName
                                    };
                                });
                                success(items);
                            },
                            error: function () {
                                error.apply(this, arguments);
                            }
                        });
                    },
                    mode: 'remote',
                    width: 200,
                    heigth: 30,
                    valueField: 'regionCode',
                    textField: 'regionName',
                    onSelect: function (record) {
                        if (townCodeDocId != null && townCodeDocId != "") {
                            $('#' + townCodeDocId).combobox("setValue", "");
                            $('#' + townCodeDocId).combobox("setText", "");
                            $('#' + townCodeDocId).combobox("reload");
                        } else {
                            return;
                        }

                        if (streetCodeDocId != null && streetCodeDocId != "") {
                            $('#' + streetCodeDocId).combobox("setValue", "");
                            $('#' + streetCodeDocId).combobox("setText", "");
                        } else {
                            return;
                        }


                    }
                });
            }

            if (townCodeDocId != null && townCodeDocId != "") {
                $('#' + townCodeDocId).combobox({
                    loader: function (param, success, error) {
                        var parentCode = $("#" + countyCodeDocId).combobox("getValue");
                        if (parentCode == null || parentCode == "") {
                            return;
                        }
                        var q = param.q || '';
                        $.ajax({
                            url: "<c:url value='/system/base/configure/region/listRegionByParentCodeAndLikeName.do'/>",
                            dataType: 'json',
                            type: 'post',
                            data: {
                                regionParentCode: parentCode,
                                regionName: q
                            },
                            success: function (data) {
                                var items = $.map(data, function (item, index) {
                                    return {
                                        regionCode: item.regionCode,
                                        regionName: item.regionName
                                    };
                                });
                                success(items);
                            },
                            error: function () {
                                error.apply(this, arguments);
                            }
                        });
                    },
                    mode: 'remote',
                    width: 200,
                    heigth: 30,
                    valueField: 'regionCode',
                    textField: 'regionName',
                    onSelect: function (record) {
                        if (streetCodeDocId != null && streetCodeDocId != "") {
                            $('#' + streetCodeDocId).combobox("setValue", "");
                            $('#' + streetCodeDocId).combobox("setText", "");
                            $('#' + streetCodeDocId).combobox("reload");
                        } else {
                            return;
                        }
                    }
                });
            }

            if (streetCodeDocId != null && streetCodeDocId != "") {
                $('#' + streetCodeDocId).combobox({
                    loader: function (param, success, error) {
                        var parentCode = $("#" + townCodeDocId).combobox("getValue");
                        if (parentCode == null || parentCode == "") {
                            return;
                        }
                        var q = param.q || '';
                        $.ajax({
                            url: "<c:url value='/system/base/configure/region/listRegionByParentCodeAndLikeName.do'/>",
                            dataType: 'json',
                            type: 'post',
                            data: {
                                regionParentCode: parentCode,
                                regionName: q
                            },
                            success: function (data) {
                                var items = $.map(data, function (item, index) {
                                    return {
                                        regionCode: item.regionCode,
                                        regionName: item.regionName
                                    };
                                });
                                success(items);
                            },
                            error: function () {
                                error.apply(this, arguments);
                            }
                        });
                    },
                    mode: 'remote',
                    width: 200,
                    heigth: 30,
                    valueField: 'regionCode',
                    textField: 'regionName'
                });
            }
        }

        function getSelectData(param) {
            if (param != null) {
                var ids = "";
                $.each(param, function (i, obj) {
                    ids += obj.id + ",";
                });
                return ids.substring(0, ids.length - 1);
            }
        }

        function getSingleSelectData(param) {
            if (param != null) {
                return param[0].id;
            }
        }

        function cls() {
            $("#toolbar input").val("");
            $("#toolbar select").val("");
            $('#datalist').datagrid('clearSelections');
        }
    </script>
</head>
<body>
</body>
</html>