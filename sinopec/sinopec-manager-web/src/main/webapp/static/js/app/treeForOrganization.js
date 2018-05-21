var checklists = new Array();
var checklist_ids = new Array();
var zTree;
var i=0;
//鼠标移入移出
/*
$(function(){  
	$("#checklist").mouseover(function (){  
			show();
			
	}).mouseout(function (){  
		$("#delete").show();
	});
	$("#showdiv").mouseover(function (){
		$("#delete").show();
	}).mouseout(function (){  
		$("#delete").hide();
	});
}) ; 
*/
//展示选中的加油站
function show(){
	$("#delete_choosed").empty();
	if(checklists.length != 0){
		for(i=0;i<checklists.length;i++){
			var content = "<dt>"+checklists[i]+"<input type='button' value='取消查询' onclick=\"deletecheck('" + checklists[i] + "')\"/></dt>";
			$("#delete_choosed").append(content);
		}
		
	}
}
//删除选中的加油站
function deletecheck(s){
	for(i=0;i<checklists.length;i++){
		if(checklists[i] == s){
			checklists.splice(i, 1);
			checklist_ids.splice(i,1);
		}
	}
	show();
}

//ztree的配置
var setting_ForOrganization = {
		treeId : "treeForOrganization",
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
        	 beforeClick: function(treeId, treeNode) {
                 var zTree = $.fn.zTree.getZTreeObj("treeForOrganization");
                 if (treeNode.isParent) {
                     zTree.expandNode(treeNode);
                     return false;
                 } else {
                     return true;
                 }
             },
             //ztree的点击事件
             onClick:function(event, treeId, treeNode) {
            	var flag = true;
            	if(checklists.length == 0){
           		  checklists.push(treeNode.name);
           		  checklist_ids.push(parseInt(treeNode.id));
           	  	}else{
           		  for(var i=0;i<checklists.length;i++){
           			  
           			  if(checklists[i] === treeNode.name){
           				  alert("已经添加过了!");
           				  flag = false;
           			  }  
           		  }
           		  if(flag){
           			  checklists.push(treeNode.name);
           			  checklist_ids.push(parseInt(treeNode.id));
           		  }
           	  	}
           	  	show();
            }
         }
};
//初始化树          
$.ajax({
          url : '/sinopec-manager-web/getOrgTree',
          type : "post",
          success : function(OrgTree) {
        	  var zNodes = eval("(" + OrgTree + ")");
        	  $(document).ready(function(){
        		  var t = $("#treeForOrganization");
        		  /**
        		   * zTree 初始化方法：$.fn.zTree.init(t, setting, zNodes)
        		   * t:用于展现 zTree 的 DOM 容器
        		   * setting:zTree 的配置数据
        		   * zNodes:zTree 的节点数据
        		   * 
        		   */
        		  t = $.fn.zTree.init(t, setting_ForOrganization, zNodes);
        	  });
          },
          error : function() {
        	  alert("获取数据失败!");
          }
}); 