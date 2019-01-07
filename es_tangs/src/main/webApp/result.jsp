<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="easyui/themes/IconExtension.css">
        <script type="text/javascript" src="easyui/jquery.min.js"></script>
        <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="easyui/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="easyui/form.validator.rules.js"></script>
        <script type="text/javascript">
            $(function () {
                $('#dg').datagrid({
                    height:'100%',
                    url:"${pageContext.request.contextPath}/sou/app?querys=${param.querys}",//数据库数据
                    remoteSort:false,//取消服务器排序
                    fitColumns:true,//自动填充满
                    pagination:true,
                    pageNumber:1,
                    pageSize:10,
                    pageList:[1,5,10,50],
                    columns:[[
                        {title:'id',field:'id',align:'center',width:20},
                        {title:'诗名',field:'title',align:'center',width:30},
                        {title:'内容',field:'content',align:'center',width:110},
                        {title:'作者',field:'poet.name',align:'center',width:20,
                            formatter:function(value,row,index){
                                return row.poet.name;
                            }
                        }
                    ]]
                });
            })
        </script>
    </head>

    <body>
        <table id="dg"></table>
    </body>
</html>
