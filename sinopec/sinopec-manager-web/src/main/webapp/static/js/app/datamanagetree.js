var setting = {
			view: {
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false
			},
			edit: {
				enable: true,
				editNameSelectAll: true,
				showRenameBtn: false

			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: zTreeBeforeClick,
				beforeDrag: beforeDrag,
				beforeEditName: beforeEditName,
				beforeRemove: beforeRemove,
				beforeRename: beforeRename,
				onRemove: onRemove,
				onRename: onRename
			}
		};
//删除属性
function deleteattr(id,i){
	$.ajax({
        url : '/sinopec-manager-web/deleteAttributeByID',
        type : "post",
        data : {
        	attributesID : id
        	},
        success : function() {
        	
        },
        error : function(msg) {
        			alert("删除失败!","提示信息");
        }
}); 
	$("#"+i).remove();
	
}
//新增属性确认
function addconfirm(i){
	//alert($("#nodeID").html());
	$.ajax({
        url : '/sinopec-manager-web/insertAttribute',
        type : "post",
        data : {
        	DataclassId:$("#nodeID").html(),
        	name:$("#"+i+">.name>#nameinput").val(),
        	priority:0,//优先级暂时没有让用户填写
        	note:$("#"+i+">.note>#noteinput").val(),
        	},
        success : function(attrid) {
        	var name =$("#"+i+">.name>#nameinput").val();
        	var note =$("#"+i+">.note>#noteinput").val();
        	$("#"+i+">.id").html(attrid);
        	//$("#"+i+">.DataclassId").html(attr.DataclassId);
        	//$("#"+i+">.priority").html(attr.priority);
        	
        	$("#"+i+">.name").html(name);
        	$("#"+i+">.note").html(note);
        	
        	var str="<input class='modify' type='button' value='修改' onclick=\"modifyattr('" +attrid+"','"+i+"')\">" +
    				"<input class='delete' type='button' value='删除' onclick=\"deleteattr('" +attrid+"','"+i+"')\">";
        	
        	$("#"+i+">.operation").html(str);
    				
        },
        error : function() {
        			alert("添加失败!","提示信息");
        }
}); 
	
}
//新增属性
function addattr(){
	
	//alert($("#ID").html());
	var tb = document.getElementById("attrtable"); 
	var newTr = tb.insertRow(1);//添加新行，trIndex就是要添加的位置 
	var rows = new Number(tb.rows.length);
	//alert(rows);
   var str = "";
	   str+= "<tr>";
	   
	str+="<td class='id' style='display:none'>"+"</td>"
	str+="<td class='DataclassId' style='display:none'>"+$("#ID").html()+"</td>"
	str+="<td class='priority' style='display:none'>"+0+"</td>"
		str+="<td class='name'>"+"<input id='nameinput' type='text' value=''/>"+"</td>"
		str+="<td class='note'>"+"<input id='noteinput' type='text' value=''/>"+"</td>"
		str+="<td class='operation'><input class='add' type='button' value='确认' " +
				"onclick=\"addconfirm('" +rows+"')\">"+"</td>"
		//str+="<td class='operation'><input class='modify' type='button' value='修改' onclick=\"modifyattr('" +attrList[i].id+"','"+rows+"')\">" +
		//		"<input class='delete' type='button' value='删除' onclick=\"deleteattr('" +attrList[i].id+"','"+i+"')\"></td>"
		
	str+= "</tr>";
	//alert(str);
	
    newTr.innerHTML = str;
    newTr.setAttribute("id",rows);
   // tb.append(str);
	
}
//提交修改
function confirmattr(id,i){
	
	//ajax提交
	$.ajax({
        url : '/sinopec-manager-web/updateAttribute',
        type : "post",
        data : {
        	id : id,
        	DataclassId:$("#"+i+">.DataclassId").html(),
        	name:$("#"+i+">.name>#nameinput").val(),
        	priority:$("#"+i+">.priority").html(),
        	note:$("#"+i+">.note>#noteinput").val(),
        	},
        success : function(attrid) {
        	var name =$("#"+i+">.name>#nameinput").val();
        	var note =$("#"+i+">.note>#noteinput").val();
        	$("#"+i+">.operation>.confirm").hide();
        	$("#"+i+">.operation>.delete").toggle();
        	$("#"+i+">.operation>.modify").toggle();
        	$("#"+i+">.name").html(name);
        	$("#"+i+">.note").html(note);
        	
        	
        	
    				
        },
        error : function(msg) {
        			alert("添加失败!","提示信息");
        }
}); 
	
}
//修改属性
function modifyattr(id,i){
	var name = $("#"+i+">.name").text();
	var note = $("#"+i+">.note").text();
	$("#"+i+">.name").empty();
	$("#"+i+">.note").empty();
	$("#"+i+">.name").html("<input id='nameinput' type='text' value='"+name+"'/>");
	$("#"+i+">.note").html("<input id='noteinput' type='text' value='"+note+"'/>");
	$("#"+i+">.operation>.delete").toggle();
	$("#"+i+">.operation>.modify").toggle();
	
	$("#"+i+">.operation").append("<input class='confirm' type='button' value='确认' onclick=\"confirmattr('" +id+"','"+i+"')\">");
}
//编辑节点信息
function editnode(){
	$("#editnode").hide();
	var name = $("#nodename").html();
	var creationAccountName = "admin";
	var responsibilityDepartment = $("#responsibilityDepartment").html();
	var note = $("#nodenote").html();
	//alert(name);
	$("#nodename").html("<input id='nodenameinput' type='text' value='"+name+"'/>");
	$("#responsibilityDepartment").html("<input id='responsibilityDepartmentinput' type='text' value='"+responsibilityDepartment+"'/>");
	$("#nodenote").html("<input id='nodenoteinput' type='text' value='"+note+"'/>");
	$("#Detial").append("<input id='editconfirm' type='button' value='确认' onclick=\"editconfirm()\">");
}
//编辑节点确认
function editconfirm(){
	$.ajax({
        url : '/sinopec-manager-web/updateDataClass',
        type : "post",
        data : {
        	ID:$("#nodeID").html(),
        	name:$("#nodenameinput").val(),
        	priority:0,//暂时不考虑
        	note:$("#nodenoteinput").val(),
        	responsibilityDepartment:$("#responsibilityDepartment").val(),
        	},
        success : function() {
        	$("#nodename").html($("#nodenameinput").val());
        	$("#responsibilityDepartment").html($("#responsibilityDepartment").val());
        	$("#nodenote").html($("#nodenoteinput").val());
        	$("#editnode").show();
        	$("#editconfirm").remove();
        	updatetree();
        },
        error : function(msg) {
        			alert("更新数据失败!","提示信息");
        }
}); 
}
//点击节点事件
function zTreeBeforeClick(treeId, treeNode, clickFlag) {
	
	$.ajax({
        url : '/sinopec-manager-web/getDataClassDetailByID',
        type : "post",
        data : {
        	ID:treeNode.id
        	},
        success : function(nodeDetail) {
        	$("#Detial").empty();
        	$("#Detial").append("<input id='editnode' type='button' value='修改节点信息' onclick=\"editnode('"+"')\">");
        	$("#Detial").append("<table>");
        	
        	$("#Detial").append("<tr><td><div id='nodeID' style='display:none'>"+nodeDetail.id+"</div></tr>");
        	$("#Detial").append("<tr><td><div>节点名称 ：</td><td><span id='nodename'>"+nodeDetail.name+"</span></td></div></tr>");
        	$("#Detial").append("<tr><td><div>创建时间 ：</td><td>"+nodeDetail.creationTime+"</td></div></tr>");
        	$("#Detial").append("<tr><td><div>创建者 ：</td><td>"+nodeDetail.creationAccountName+"</td></div></tr>");
        	$("#Detial").append("<tr><td><div>责任部门 ：</td><td><span id='responsibilityDepartment'>"+nodeDetail.responsibilityDepartment+"</span></td></div></tr>");
        	$("#Detial").append("<tr><td><div>备注 ：</td><td><span id='nodenote'>"+nodeDetail.note+"</span></td></div></tr>");
        	$("#Detial").append("</table>");
        	//$("#Detial").append("<br/><br/><br/><br/>");
        },
        error : function(msg) {
        			alert("获取数据失败!","提示信息");
        }
}); 
	$.ajax({
        url : '/sinopec-manager-web/getAttributeListByDataClassID',
        type : "post",
        data : {

        	DataClassID:treeNode.id
        	},
        success : function(attrList) {
        	$("#attrTable").empty();
        	var str = "<input id='add' type='button' value='新增属性' onclick=\"addattr('"+"')\">" +
			"<table id='attrtable' border='4'>";
	str+="<tr><td>属性名</td><td style='width:200px'>备注</td><td>操作</td></tr>";
        	if(attrList.length!=0){
        		//alert(attrList[0].name);
        		
        		
        		for(var i=0;i<attrList.length;i++){
        			str+= "<tr id = " +i+">";
        			str+="<td class='id' style='display:none'>"+attrList[i].id+"</td>"
        			str+="<td class='DataclassId' style='display:none'>"+attrList[i].dataclassId+"</td>"
        			str+="<td class='priority' style='display:none'>"+attrList[i].priority+"</td>"
        				str+="<td class='name'>"+attrList[i].name+"</td>"
        				str+="<td class='note'>"+attrList[i].note+"</td>"
        				str+="<td class='operation'><input class='modify' type='button' value='修改' onclick=\"modifyattr('" +attrList[i].id+"','"+i+"')\">" +
        						"<input class='delete' type='button' value='删除' onclick=\"deleteattr('" +attrList[i].id+"','"+i+"')\"></td>"
        				
        			str+= "</tr>";
        		}
        		str+="</table>";
        		
        	}
        	$("#attrTable").append(str);
        },
        error : function(msg) {
        			alert("获取数据失败!","提示信息");
        }
}); 
	var zTree = $.fn.zTree.getZTreeObj("tree");
    if (treeNode.isParent) {
        zTree.expandNode(treeNode);
        return false;
    } else {
        return true;
    }
    return (treeNode.id !== 1);
};
var log, className = "dark";
function beforeDrag(treeId, treeNodes) {
	return false;
}
function beforeEditName(treeId, treeNode) {
	className = (className === "dark" ? "":"dark");
	showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.selectNode(treeNode);
	//return confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？");
}
function beforeRemove(treeId, treeNode) {
	
	
	className = (className === "dark" ? "":"dark");
	showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.selectNode(treeNode);
	$.ajax({
        url : '/sinopec-manager-web/deleteDataClassByID',
        type : "post",
        data : {

        	ID:treeNode.id
        	},
        success : function() {
        	
        	
        },
        error : function(msg) {
        			alert("获取数据失败!","提示信息");
        }
}); 
	
	return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
}
function onRemove(e, treeId, treeNode) {
	showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
}
function beforeRename(treeId, treeNode, newName) {
	className = (className === "dark" ? "":"dark");
	showLog("[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	if (newName.length == 0) {
		alert("节点名称不能为空.");
		var zTree = $.fn.zTree.getZTreeObj("tree");
		setTimeout(function(){zTree.editName(treeNode)}, 10);
		return false;
	}
	return true;
}
function onRename(e, treeId, treeNode) {
	showLog("[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
}
function showLog(str) {
	if (!log) log = $("#log");
	log.append("<li class='"+className+"'>"+str+"</li>");
	if(log.children("li").length > 8) {
		log.get(0).removeChild(log.children("li")[0]);
	}
}
function getTime() {
	var now= new Date(),
	h=now.getHours(),
	m=now.getMinutes(),
	s=now.getSeconds(),
	ms=now.getMilliseconds();
	return (h+":"+m+":"+s+ " " +ms);
}


function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.id).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.id
		+ "' title='增加子节点' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.id);
	

	if (btn) btn.bind("click", function(){
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var ID;
		$.ajax({
	        url : '/sinopec-manager-web/insertDataClass',
	        type : "post",
	        data : {
	        	name:"",
	        	PID:treeNode.id,
	        	priority:0
	        	},
	        success : function(nodeid) {
	        	zTree.addNodes(treeNode, {id:nodeid, pId:treeNode.id, name:"new node"});
	        	zTreeBeforeClick("tree", zTree.getNodeByParam("id",nodeid), true)
	        	//document.getElementById("editnode").onclick();
//	        	$(function(){
//	        		editnode();
//	        	});
	        	setTimeout("editnode();",100);
//	        	var e = document.createEvent("MouseEvents");
//	            e.initEvent("click", true, true);
//	        	document.getElementById("editnode").dispatchEvent(e);
	        },
	        error : function(msg) {
	        			alert("新增节点失败!","提示信息");
	        }
	}); 
		
		
		return false;
	});
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.id).unbind().remove();
};
function selectAll() {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
}

/*$(document).ready(function(){
	$.fn.zTree.init($("#tree"), setting, zNodes);
	$("#selectAll").bind("click", selectAll);
});*/
$.ajax({
          url : '/sinopec-manager-web/getALLDataClass',
          type : "post",
       
          success : function(OrgTree) {
        	  
        	  var zNodes =  OrgTree;
        	  $(document).ready(function(){
        		  var t = $("#tree");
        		  
        		  /**
        		   * zTree 初始化方法：$.fn.zTree.init(t, setting, zNodes)
        		   * t:用于展现 zTree 的 DOM 容器
        		   * setting:zTree 的配置数据
        		   * zNodes:zTree 的节点数据
        		   * 
        		   */

        		  t = $.fn.zTree.init(t, setting, zNodes);
        	  });
          },
          error : function(msg) {
          			alert("获取数据失败!","提示信息");
          }
}); 
function updatetree(){
	$.ajax({
        url : '/sinopec-manager-web/getALLDataClass',
        type : "post",
     
        success : function(OrgTree) {
      	  
      	  var zNodes =  OrgTree;
      	  $(document).ready(function(){
      		  var t = $("#tree");
      		  
      		  /**
      		   * zTree 初始化方法：$.fn.zTree.init(t, setting, zNodes)
      		   * t:用于展现 zTree 的 DOM 容器
      		   * setting:zTree 的配置数据
      		   * zNodes:zTree 的节点数据
      		   * 
      		   */

      		  t = $.fn.zTree.init(t, setting, zNodes);
      	  });
        },
        error : function(msg) {
        			alert("获取数据失败!","提示信息");
        }
}); 
}
