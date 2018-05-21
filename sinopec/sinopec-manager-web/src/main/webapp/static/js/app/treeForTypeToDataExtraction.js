var zTree;
var dcids = new Array();//在当前页面用于记录所选择的基础数据的类型的id
var dcid_values = new Array();
var TableHeads;//用来记录后台回传的基础数据类型所拥有的属性
var tableinformation;//用来记录TableHeads在页面中拼接成的表头信息
var dataextrac_infors;
var dcids_max = new Array();//用来记录每个类型的实例的个数
var TabaleHead_ForDataExtraction;
//树的配置
var setting_ForType = {
		treeId: "treeForType",//用来区别不同的树
		view: {
          			dblClickExpand: false,
                    showLine: true,
                    selectedMulti: false
                },
         data: {
                    simpleData: {
                    	enable:true,
                    	idKey: "id",
                    	pIdKey: "pId",
                    	rootPId: ""
                    }
                },
         callback: {
        	 //单击父节点的事件:展开
        	 beforeClick: function(treeId, treeNode) {
                 var zTree = $.fn.zTree.getZTreeObj("treeForType");
                 if (treeNode.isParent) {
                     zTree.expandNode(treeNode);
                     return false;
                 } else {
                     return true;
                 }
             },
             //单击叶节点的事件:自定义响应
             onClick:function(event, treeId, treeNode) {
            	var flag = true;
             	if(dcids.length == 0){
             		dcid_values.push(treeNode.name);
            		dcids.push(parseInt(treeNode.id));
            	}else{
            		for(var i=0;i<dcid_values.length;i++){
            			if(dcid_values[i] === treeNode.name){
            				  alert("已经添加过了!");
            				  flag = false;
            			}  
            		}
            		if(flag){
            			dcid_values.push(treeNode.name);
            			dcids.push(parseInt(treeNode.id));
            		}
            	}
             	showdatatype();
             }
         }
};
//初始化树         
$.ajax({
          url : '/sinopec-manager-web/getALLDataClass',
          type : "post",
          success : function(TypeTree) {
        	  var zNodes =  TypeTree ;
        	  $(document).ready(function(){
        		  var t = $("#treeForType");
        		  /**
        		   * zTree 初始化方法：$.fn.zTree.init(t, setting, zNodes)
        		   * t:用于展现 zTree 的 DOM 容器
        		   * setting:zTree 的配置数据
        		   * zNodes:zTree 的节点数据
        		   * 
        		   */
        		  t = $.fn.zTree.init(t, setting_ForType, zNodes);
        	  });
          },
          error : function() {
        	  alert("获取角色失败!","提示信息");
          }
}); 
//展示选中的基础数据类型
function showdatatype(){
	$("#delete_data_choosed").empty();
	if(dcid_values.length != 0){
		for(i=0;i<dcid_values.length;i++){
			var content = "<dt>"+dcid_values[i]+"<input type='button' value='取消查询' onclick=\"deletecheck_datatype('" + dcid_values[i] + "')\"/></dt>";
			$("#delete_data_choosed").append(content);
		}
		
	}
}
//删除选中的基础数据类型
function deletecheck_datatype(s){
	for(i=0;i<dcid_values.length;i++){
		if(dcid_values[i] == s){
			dcid_values.splice(i, 1);
			dcids.splice(i,1);
		}
	}
	showdatatype();
}


//"基础数据抽取确认"按钮的响应事件
function dataextraction(){
	//判空
	if(checklist_ids.length == 0 || dcids.length == 0){
		alert("请添加筛选条件(加油站和基础数据)");
		return;
	}else{
		$.ajax({
			url : '/sinopec-manager-web/take',	
			type : "post",
			data:{
				oids : checklist_ids,
				dcids : dcids
			},
			success : function(values) {
				dataextrac_infors = JSON.parse(values);
				//console.log(dataextrac_infors.length);
				//var test = dataextrac_infors[0].thisDepartmentAllData;
				//console.log(test[0].DataclassName);
				console.log(values);
				//此处显示格式:'3265代码-加油站名称'+'要抽取的数据类型1'+'要抽取的数据类型2'+......
				$("#table_tbody").empty();
				var str = "<tr><td><pre>加油站信息</pre></td>";
				for(var k = 0;k<dcid_values.length;k++){
					str += "<td><pre>基础数据"+(k+1)+"</pre></td>";
				}
				$("#table_tbody").append(str+"</tr>");
				for(var i=0;i<checklists.length;i++){
					str = "<tr><td><pre>"+checklists[i]+"</pre></td>";
					for(var j=0;j<dcid_values.length;j++){
						str +="<td><pre>"+dcid_values[j]+"</pre></td>";
					}
					$("#table_tbody").append(str+"</tr>");
				}
			},
			error : function() {
				alert("获取数据失败!","提示信息");
			}
		}); 
	}
}
//在弹窗中显示预览信息
//弹出窗相关
//获取窗口的高度  
var windowHeight;  
//获取窗口的宽度  
var windowWidth;  
//获取弹窗的宽度  
var popWidth;  
//获取弹窗高度  
var popHeight;  
function init() {  
 windowHeight=$(window).height();  
 windowWidth=$(window).width();  
 popHeight=$(".window").height();  
 popWidth=$(".window").width();  
}  
//关闭窗口的方法  
function closeWindow(){  
	  addNewData_rows=0;
    $("#center").hide();
}  
function popWindow(){
	if(dcids.length == 0 || checklists.length == 0){
		alert("请添加数据抽取的条件");
		return;
	}else{
		init();  
	    //计算弹出窗口的左上角Y的偏移量  
	    var popY=(windowHeight-popHeight)/2; //垂直方向偏移量  
	    var popX=(windowWidth-popWidth)/2;   //水平方向偏移量  
	    //设定窗口的位置  
	    $("#center").css("top",popY).css("left",popX).slideToggle("fast"); 
	    //拼接表头信息
	    //表头第一行
	  	$("#newdata_tbody").empty();
	  	var str = "<tr><td align='center' colspan='2'><pre>加油站</pre></td>";
	  	for(var i=0;i<dcids.length;i++){
	  		var AttributeNumbers = getAttributes(dcids[i]).length;
	  		var num = max(dcids[i],dataextrac_infors);
	  		if(num == 0){
	  			str +="<td align='center' colspan='"+AttributeNumbers+"'><pre>"
	  				+dcid_values[i]+"1</pre></td>";
	  		}else{
	  			for(var j=0;j<num;j++)
	  				str +="<td align='center' colspan='"+AttributeNumbers+"'><pre>"
	  					+dcid_values[i]+(j+1)+"</pre></td>";
	  		}
	  	}
	  	$("#newdata_tbody").append(str+"</tr>");
	  	//表头第二行
	  	str = "<tr><td align='center'><pre>3265代码</pre></td><td align='center'><pre>加油站名称</pre></td>";
	  	//dcids表示有多少个数据类型
	  	for(var t=0;t<dcids.length;t++){
	  		var num = max(dcids[t],dataextrac_infors);
	  		var TabaleHead_ForDataExtraction = getAttributes(dcids[t]);
	  		if(num == 0){
	  			for(var j=0;j<TabaleHead_ForDataExtraction.length;j++){
	  				str +="<td align='center'><pre>"+TabaleHead_ForDataExtraction[j].name+"</pre></td>";
	  			}
	  		}else{
	  			for(var i=0;i<num;i++){
		  			for(var j=0;j<TabaleHead_ForDataExtraction.length;j++){
		  				str +="<td align='center'><pre>"+TabaleHead_ForDataExtraction[j].name+"</pre></td>";
		  			}
		  		}
	  		}
	  	}
	  	$("#newdata_tbody").append(str+"</tr>");
	  	//数据行
	  	var str_data_one="";
	  	var str_data_two = "";
	  	//记录表头第一行有多少个不同的实例
	  	var count_TableHead1 = new Array();
	  	var count_TableHead2 = new Array();
	  	var count_TableHead3 = new Array();
	  	$("#newdata_tbody").find("tr").each(function(t){
  			if(t==0){
  				$(this).find("td").each(function(k){
  					if(k==0){
  						//不做操作
  					}else{
  						count_TableHead1.push($(this).find("pre").html());
  						count_TableHead2.push($(this).attr('colspan'));
  					}
  				});
  			}
  		});
	  	$("#newdata_tbody").find("tr").each(function(t){
  			if(t==1){
  				$(this).find("td").each(function(k){
  					if(k==0||k==1){
  						//不做操作
  					}else{
  						count_TableHead3.push($(this).find("pre").html());
  					}
  				});
  			}
  		});
	  	//数据对应地放置
	  	for(var i=0;i<dataextrac_infors.length;i++){
	  		str_data_one = "<td align='center'><pre>"+dataextrac_infors[i].Code3265+"</pre></td>"
	  					+"<td align='center'><pre>"+dataextrac_infors[i].name+"</pre></td>";
	  		//每一行为一个加油站的数据
	  		var DepartmentAllData = dataextrac_infors[i].thisDepartmentAllData;
	  		str_data_two = "";
	  		//问题
	  		//1、类型A的实例都有，存在属性缺失的可能，解决：需要对thisTypeDataAllInstanceValue下面的各属性的值是否空进行判断
	  		//2、类型A的实例1有，但实例2没有，也存在属性缺失的可能,解决：需要对thisDepartmentAllData下的主机的数量进行统计，表头中对应类型的列数超出这个数量时，全部附上空值
	  		//3、类型A没有实例
	  		var count=0;
	  		//数据类型
	  		for(var j=0;j<dcid_values.length;j++){
	  			//同一数据类型的不同实例
	  			for(var k=0;k<count_TableHead1.length;k++){
	  				
	  				var dataclassnameInfo = [];
			  		dataclassnameInfo.push(count_TableHead1[k].substring(0,count_TableHead1[k].length-1));
			  		dataclassnameInfo.push(count_TableHead1[k].substring(count_TableHead1[k].length-1,count_TableHead1[k].length));
	  				if(dataclassnameInfo[0] == dcid_values[j]){
	  					var attrs=[];//属性对应上表头
		  				var step = 0;//跨越步长
		  				for(var w=0;w<k;w++){
		  					step += count_TableHead2[w];
		  				}
		  				for(var n=0;n<count_TableHead2[k];n++){
		  					attrs.push(count_TableHead3[step+n]);
		  				}
				  		for(var t=0;t<DepartmentAllData.length;t++){
				  			console.log(t+"###"+DepartmentAllData[t]);
				  			if(DepartmentAllData[t].DataclassName == dataclassnameInfo[0]){
				  				
				  				var TypeDataAllInstanceValue = DepartmentAllData[t].thisTypeDataAllInstanceValue;
				  				//有问题
				  				//针对问题3
				  				if(typeof(TypeDataAllInstanceValue)=="undefined"||typeof(TypeDataAllInstanceValue) ==""||TypeDataAllInstanceValue.length==0){
				  					for(var m=0;m<attrs.length;m++)
				  						str_data_two += "<td align='center'><pre></pre></td>";
				  				}else{
				  					//针对问题2
				  					var max_num = max(dcids[j],dataextrac_infors);
				  					if(TypeDataAllInstanceValue.length<max_num){
				  						for(var z=0;z<max_num;z++){
				  							if(z<TypeDataAllInstanceValue.length){
				  								for(var r=0;r<TypeDataAllInstanceValue.length;r++){
							  						if(r==(dataclassnameInfo[1]-1)){
							  							for(var n=0;n<attrs.length;n++){
										  					var content = attrs[n];
										  					var InstanceValue = TypeDataAllInstanceValue[r];
										  					if(typeof(InstanceValue)   !=   "undefined" ||typeof(InstanceValue) ==""){
										  						if(typeof(InstanceValue[content].Value)!="undefined"){
										  							str_data_two += "<td align='center'><pre>"+InstanceValue[content].Value+"</pre></td>";
										  						}
										  						else{
										  							str_data_two += "<td align='center'><pre></pre></td>";
										  						}
											  				}else{
											  					str_data_two += "<td align='center'><pre></pre></td>";
											  				}
										  				}
							  						}
								  				}
				  							}else{
				  								if(r==(dataclassnameInfo[1]-1)){
				  									for(var m=0;m<attrs.length;m++)
				  										str_data_two += "<td align='center'><pre></pre></td>";
				  								}
				  							}
				  						}
				  					}else{
				  						for(var r=0;r<TypeDataAllInstanceValue.length;r++){
					  						if(r==(dataclassnameInfo[1]-1)){
					  							for(var n=0;n<attrs.length;n++){
								  					var content = attrs[n];
								  					var InstanceValue = TypeDataAllInstanceValue[r];
								  					if(typeof(InstanceValue)   !=   "undefined" ||typeof(InstanceValue) ==""||InstanceValue.length==0){
								  						if(typeof(InstanceValue[content].Value)!="undefined"){
								  							str_data_two += "<td align='center'><pre>"+InstanceValue[content].Value+"</pre></td>";
								  						}
								  						else{
								  							str_data_two += "<td align='center'><pre></pre></td>";
								  						}
									  				}else{
									  					str_data_two += "<td align='center'><pre></pre></td>";
									  				}
								  				}
					  						}
						  				}
				  					}
				  				}
				  			}
				  		}
	  				}
	  			}
	  		}
	  		str_data_one += str_data_two;
  			$("#newdata_tbody").append("<tr>"+str_data_one+"</tr>");	
	  	}
	}
}
function max(dataclassId,allData){
	var maxValue=[];
	for(var i=0;i<allData.length;i++){
		var departmentAllData = allData[i].thisDepartmentAllData;
		for(var j=0;j<departmentAllData.length;j++){
			if(dataclassId == departmentAllData[j].DataclassId){
				var TypeDataAllInstanceValue = departmentAllData[j].thisTypeDataAllInstanceValue;
				if(TypeDataAllInstanceValue.length == 0)
					maxValue.push(0);
				else
					maxValue.push(TypeDataAllInstanceValue.length);
			}
		}
	}
	//返回当前类型的实例最多的数值
	return Math.max.apply(null,maxValue);
}
function getAttributes(t){
	 var TabaleHead_ForDataExtraction;
	 $.ajax({
        url : '/sinopec-manager-web/getAttributeListByDataClassID',
        type : "post",
        "async" : false,
        data:{
       	 	DataClassID:t 
        },
        success : function(TableHead) {
        	TabaleHead_ForDataExtraction = TableHead;
        },
        error : function() {
       	 alert("获取数据失败!","提示信息");
        }
	 }); 
	 return TabaleHead_ForDataExtraction;
}
//获取json数据的长度
function getJsonLength(jsonData) {  
	var length=0;  
	for(var ever in jsonData) {  
		length++;  
	}  
	return length;  
} 
//将Table数据导出为excel相关代码
var idTmr;  
function  getExplorer() {  
    var explorer = window.navigator.userAgent ;  
    //ie  
    if (explorer.indexOf("MSIE") >= 0) {  
        return 'ie';  
    }  
    //firefox  
    else if (explorer.indexOf("Firefox") >= 0) {  
        return 'Firefox';  
    }  
    //Chrome  
    else if(explorer.indexOf("Chrome") >= 0){  
        return 'Chrome';  
    }  
    //Opera  
    else if(explorer.indexOf("Opera") >= 0){  
        return 'Opera';  
    }  
    //Safari  
    else if(explorer.indexOf("Safari") >= 0){  
        return 'Safari';  
    }  
}  

var fileName = "数据汇总" + new Date().toISOString().replace(/[\-\:\.]/g, ""); //自定义excel文件名
function exportExcel() {  
	
    if(getExplorer()=='ie')  
    {  
        
        var curTbl = document.getElementById("table_to_excel");  
        var oXL = new ActiveXObject("Excel.Application");  
        var oWB = oXL.Workbooks.Add();  
        var xlsheet = oWB.Worksheets(1);  
        var sel = document.body.createTextRange();  
        sel.moveToElementText(curTbl);  
        sel.select();  
        sel.execCommand("Copy");  
        xlsheet.Paste();  
        oXL.Visible = true;  
        try {  
            //var fname = oXL.Application.GetSaveAsFilename("Excel.xls",filename);
        	
        	var fname = oXL.Application.GetSaveAsFilename(fileName + ".xls",
            "Excel Spreadsheets (*.xls), *.xls");
        } catch (e) {  
            print("Nested catch caught " + e);  
        } finally {  
            oWB.SaveAs(fname);  
            oWB.Close(savechanges = false);  
            oXL.Quit();  
            oXL = null;  
            idTmr = window.setInterval("Cleanup();", 1);  
        }  
    }  
    else  
    {  
        tableToExcel("table_to_excel");
    }  
}  
function Cleanup() {  
    window.clearInterval(idTmr);  
    CollectGarbage();  
}  


var tableToExcel = (function() {  
    var uri = 'data:application/vnd.ms-excel;base64,',  
    template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel"'+
    'xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet>'
    +'<x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets>'
    +'</x:ExcelWorkbook></xml><![endif]-->'+
    ' <style type="text/css">'+
    '.excelTable  {'+
    'border-collapse:collapse;'+
     ' border:thin solid #999; '+
    '}'+
    '   .excelTable  th {'+
    '   border: thin solid #999;'+
    '  padding:20px;'+
    '  text-align: center;'+
    '  border-top: thin solid #999;'+
    ' background-color: #E6E6E6;'+
    ' }'+
    ' .excelTable  td{'+
    ' border:thin solid #999;'+
    '  padding:2px 5px;'+
    '  text-align: center;'+
    ' }</style>'+
    '</head><body ><table class="excelTable">{table}</table></body></html>',  
            base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))); },  
            format = function(s, c) {  
                return s.replace(/{(\w+)}/g,  
                 function(m, p) { return c[p]; });}  ;
    return function(table, name) {  
        if (!table.nodeType) table = document.getElementById(table)  ;
        var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML} ; 
        //window.location.href = uri + base64(format(template, ctx));
       //document.getElementById("exportExcel").href = uri + base64(format(template, ctx));
       //document.getElementById("exportExcel").download = filename;
     //  document.getElementById("exportExcel").click();
        a = document.createElement("a");
        a.download = fileName;
        a.href = uri + base64(format(template, ctx));
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
    }  ;
})()  ;


function Cleanup() {  
    window.clearInterval(idTmr);  
    CollectGarbage();  
}  