<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<title> 导出到Excel </title>


		<script>
    var tablesToExcel = (function() {
        var uri = 'data:application/vnd.ms-excel;base64,'
            , tmplWorkbookXML = '<?xml version="1.0"?><?mso-application progid="Excel.Sheet"?><Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet" xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet">'
            + '<DocumentProperties xmlns="urn:schemas-microsoft-com:office:office"><Author>Axel Richter</Author><Created>{created}</Created></DocumentProperties>'
            + '<Styles>'
            + '<Style ss:ID="Currency"><NumberFormat ss:Format="Currency"></NumberFormat></Style>'
            + '<Style ss:ID="Date"><NumberFormat ss:Format="Medium Date"></NumberFormat></Style>'
            + '</Styles>'
            + '{worksheets}</Workbook>'
            , tmplWorksheetXML = '<Worksheet ss:Name="{nameWS}"><Table>{rows}</Table></Worksheet>'
            , tmplCellXML = '<Cell{attributeStyleID}{attributeFormula}><Data ss:Type="{nameType}">{data}</Data></Cell>'
            , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
            , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
        return function(tables, wsnames, wbname, appname) {
            var ctx = "";
            var workbookXML = "";
            var worksheetsXML = "";
            var rowsXML = "";

            for (var i = 0; i < tables.length; i++) {
                if (!tables[i].nodeType) tables[i] = document.getElementById(tables[i]);

//              控制要导出的行数
                for (var j = 0; j < tables[i].rows.length; j++) {
                    rowsXML += '<Row>';

//                    控制导出的列数（在本例中，最后一列为button,导出的文件会出错，所以导出到倒数第二列
                    for (var k = 0; k < tables[i].rows[j].cells.length-1; k++) {
                        var dataType = tables[i].rows[j].cells[k].getAttribute("data-type");
                        var dataStyle = tables[i].rows[j].cells[k].getAttribute("data-style");
                        var dataValue = tables[i].rows[j].cells[k].getAttribute("data-value");
                        dataValue = (dataValue)?dataValue:tables[i].rows[j].cells[k].innerHTML;
                        var dataFormula = tables[i].rows[j].cells[k].getAttribute("data-formula");
                        dataFormula = (dataFormula)?dataFormula:(appname=='Calc' && dataType=='DateTime')?dataValue:null;
                        ctx = {  attributeStyleID: (dataStyle=='Currency' || dataStyle=='Date')?' ss:StyleID="'+dataStyle+'"':''
                            , nameType: (dataType=='Number' || dataType=='DateTime' || dataType=='Boolean' || dataType=='Error')?dataType:'String'
                            , data: (dataFormula)?'':dataValue
                            , attributeFormula: (dataFormula)?' ss:Formula="'+dataFormula+'"':''
                        };
                        rowsXML += format(tmplCellXML, ctx);
                    }
                    rowsXML += '</Row>'
                }
                ctx = {rows: rowsXML, nameWS: wsnames[i] || 'Sheet' + i};
                worksheetsXML += format(tmplWorksheetXML, ctx);
                rowsXML = "";
            }

            ctx = {created: (new Date()).getTime(), worksheets: worksheetsXML};
            workbookXML = format(tmplWorkbookXML, ctx);

//          查看后台的打印输出
            console.log(workbookXML);

            var link = document.createElement("A");
            link.href = uri + base64(workbookXML);
            link.download = wbname || 'Workbook.xls';
            link.target = '_blank';
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        }
    })();
    </script>

	</head>
	<body>
		<div class="container">
		
			<table id="backViewTable" border='1';>
				<tr>
					<td align="center">
					团队名称
					</td>
					<td align="center">
						日期
					</td>
					<td align="center">
						餐券数
					</td>
					<td align="center">
						开始时间~结束时间
					</td>
					<td align="center">
						加班时长
					</td>
					<td align="center">
						加班类型
					</td>
					<td align="center">
						值班方式
					</td>
					<td align="center">
						备注
					</td>
			
				</tr>
			
						
				<c:forEach items="${requestScope.OvertimeList}" var="overtime">
							<tr >
								<td align="center">
									<!-- 团队 -->
									${overtime.staffteam.team.name}
								</td >
								<td align="center">
								<fmt:formatDate value="${overtime.date}" pattern="yyyy年MM月dd日"/>
									
								</td>
								<td align="center">
									${overtime.mealcoupon}
								</td>
								<td align="center">
									${overtime.startime}~${overtime.endtime}
								</td>
								<td align="center">
									${overtime.duration}
								</td>
								<td align="center">
									${overtime.type}

								</td>
								<td align="center">
									${overtime.dutymode}

								</td>
								<td align="center">
									${overtime.notes}
								</td>
							</tr>
						</c:forEach>
						
			</table>
			
		</div>
		<button class="btn btn-primary btn-sm" onclick="tablesToExcel( [ 'backViewTable' ], [ 'ProductDay1' ], 'OverTimeBook.xls',
			'Excel')">
			将数据导出到EXCEL
		</button>

		  
	</body>
</html>
