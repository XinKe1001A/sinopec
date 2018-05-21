var zTree;
var dcid;//在当前页面用于记录所选择的基础数据的类型的id
var TableHeads;//用来记录后台回传的基础数据类型所拥有的属性
var tableinformation;//用来记录TableHeads在页面中拼接成的表头信息
var addNewData_ocid;//用来记录在弹出框中所选择的加油站的id
var addNewData_rows = 0;//用来记录弹出框中新增数据的条数
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
            	 dcid = treeNode.id;
            	 $.ajax({
                     url : '/sinopec-manager-web/getAttributeListByDataClassID',
                     type : "post",
                     data:{
                    	 DataClassID:treeNode.id 
                     },
                     success : function(TableHead) {
                    	//表头信息
                    	TableHeads = TableHead;
                    	//显示表头
        				$("#table_tbody").empty();
                   	 	var str = "";
                    	for(var i=0;i<TableHead.length;i++){
                    		str +="<td class='attrID_"+TableHead[i].id+"'><pre>"
                    			+TableHead[i].name+"</pre></td>";
                    	}
                    	str = "<tr><td><pre>加油站3265代码</pre></td><td><pre>加油站名称</pre></td>"+str+"<td><pre>操作</pre></td>"+"<td><pre>操作</pre></td>"+"</tr>";
                    	tableinformation = str;
                    	$("#table_tbody").append(str);
                     },
                     error : function() {
                    	 alert("获取角色失败!","提示信息");
                     }
            	 }); 
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
//筛选按钮的响应事件
function choose(){
	if(checklist_ids.length == 0 || dcid == null){
		alert("请添加筛选条件(加油站和基础数据)");
		return;
	}else{
		$.ajax({
			url : '/sinopec-manager-web/getValuesByOrgsAndClass',
			type : "post",
			data:{
				oids : checklist_ids,
				dcid : dcid
			},
			success : function(values) {
				//值为2说明没有数据，给出提示
				if(values.length == 2){
					alert("没有该基础数据的详细数据");
				}else{
					var strs = "";
					var str = "";
					var name = "";
					var objs = JSON.parse(values);
					$("#table_tbody").empty();
					$("#table_tbody").append(tableinformation);
					for(var obj in objs){
						str = "";
						for(var i=0;i<TableHeads.length;i++){
							name = TableHeads[i].name;
							var value = eval('objs['+obj+'].'+name);
							if(value == undefined){
								str += "<td class='attrID_"+TableHeads[i].id+"' id='-1'><pre>暂无数据</pre></td>";
							}else{
								str += "<td class='attrID_"+TableHeads[i].id+"' id='"
										+value['ValueId']+"'><pre>"+value['Value']+"</pre></td>";
							}
						}
						strs = "<tr class='"+objs[obj].instanceId+"'>"
								+"<td><pre>"+objs[obj].code3265
								+"</pre></td><td><pre>"+objs[obj].organizationName
								+"</pre></td>"+str
								+"<td><pre><input type='button' value='修改' onclick=\"editdata('" 
								+objs[obj].instanceId+ "')\"></pre></td>"
								+"<td><pre><input type='button' value='删除' onclick=\"deletedata('" 
								+objs[obj].instanceId+"')\"></pre></td>"+"</tr>";
						$("#table_tbody").append(strs);
					}
				}
			},
			error : function() {
				alert("获取数据失败!","提示信息");
			}
		}); 
		
	}
}
//删除数据
function deletedata(insid){
	if(dcid == 1 || dcid ==103 || dcid == 118){
		alert("所选中的基础数据条数属于不能删除范围!");
		return;
	}
	if(confirm("确定增加这些基础数据条数吗?")){
		$.ajax({
			url:'/sinopec-manager-web/delete',
			type:"post",
			data:{
				insid:insid
			},
			success:function(){
				$("."+insid).remove();
				alert("删除数据成功!");
			},
			error:function(){
				alert("删除数据失败!");
			}
			
		});
	}
}
//修改数据提交
function confirmdata(insid){
	var instance_value_new;
	
	$("."+insid).find("td").each(function(j){
		if(j == 0 || j==1){
			//前两项不作操作
		}
		else if(j>1 && j<TableHeads.length+2){
			instance_value_new = $("#instance_value_newinput_"+TableHeads[j-2].id).val();
			$("."+insid+" td:nth-child("+(j+1)+") pre").empty();
			$("."+insid+" td:nth-child("+(j+1)+") pre").html(instance_value_new);
		}else if(j == TableHeads.length+2){
			$("."+insid+" td:nth-child("+(j+1)+")").empty();
			$("."+insid+" td:nth-child("+(j+1)+")").html("<pre><input type='button' value='修改' onclick=\"editdata('" +insid+ "')\"></pre>");
		}else{
			$("."+insid+" td:nth-child("+(j+1)+") pre").toggle();
		}
		
	});
	
	
	var jsonList=[],attributesId,instance_value,instance_value_id,s="";
	
	$("."+insid).find("td").each(function(j){
		s = $("."+insid+" td:nth-child("+(j+1)+")").attr("class");
		attributesId      =	s.substring(7);
		instance_value 	  = $("."+insid+" td:nth-child("+(j+1)+") pre").html();
		instance_value_id = $("."+insid+" td:nth-child("+(j+1)+")").attr("id");
		if(instance_value =="暂无数据" || j>TableHeads.length+1 || j<2){
			//不操作
		}else{
			var data = {};
			data.AttributesId = attributesId;
			data.ValueId = instance_value_id;
			data.Value = instance_value;
			data.note  = "";
			jsonList.push(JSON.stringify(data));
		}
	});
	
	var jsonListStr=JSON.stringify(jsonList);
	//确认
	$.ajax({
		url:'/sinopec-manager-web/edit',
		type:"post",
		data:{
			insid:insid,
			jsonListStr:jsonListStr
		},
		success:function(){
			choose();
			alert("修改成功!");
		},
		error:function(){
			alert("修改数据失败!");
		}
	});
}
//修改数据
function editdata(insid){
	var instance_value_new;
	$("."+insid).find("td").each(function(j){
		if(j == 0 || j==1){
			//前两项的显示信息不作操作，j的操作记录应对应后移2位
		}else if(j>1 && j<TableHeads.length+2){
			instance_value_new = $("."+insid+" td:nth-child("+(j+1)+") pre").html();
			$("."+insid+" td:nth-child("+(j+1)+") pre").empty();
			$("."+insid+" td:nth-child("+(j+1)+") pre")
			.html("<input id='instance_value_newinput_"+TableHeads[j-2].id+"' type='text' value='"+instance_value_new+"'/>");
		}else if(j == TableHeads.length+2){
			$("."+insid+" td:nth-child("+(j+1)+") pre").toggle();
			$("."+insid+" td:nth-child("+(j+1)+")").html("<pre><input class='confirm' type='button' value='确认' onclick=\"confirmdata('" +insid+"')\"></pre>");
		}else{
			$("."+insid+" td:nth-child("+(j+1)+") pre").toggle();
		}
		
	});
}
//增加数据
function addData(){
	if(confirm("确定增加这些基础数据条数吗?")){
		var newdatas = [];
		//收集新增的数据，拼接成json传回后台
		var dataValue_new_attrId,dataValue_new_value;
		for(var k=1;k<=addNewData_rows;k++){
			var jsonstr="[]";  
			var newdata = eval('('+jsonstr+')'); 
			$("#addDataDetail_"+k).find("td").each(function(j){
				var newdataobj = {};
				dataValue_new_attrId = $("#addDataDetail_"+k+" td:nth-child("+(j+1)+")").attr("class").substring(7);
				dataValue_new_value = $("#addDataDetail_"+k+" td:nth-child("+(j+1)+") pre input").val();
				if(dataValue_new_value == ""){
				
				}else{
					newdataobj.AttributesId = dataValue_new_attrId;
					newdataobj.value = dataValue_new_value;
					newdataobj.note = "";
					newdata.push(newdataobj);
				}
			});
			var newdatastr = JSON.stringify(newdata)
			newdatas.push(newdatastr);
		}
	
		var newdatasstring=JSON.stringify(newdatas);
		$.ajax({
			url:'/sinopec-manager-web/add',
			type:"post",
			data:{
				oid:addNewData_ocid,
				dcid:dcid,
				jaList:newdatasstring
			},
			success:function(msg){
				addNewData_rows = 0;
				alert("新增基础数据成功!");
				closeWindow();
			},
			error:function(){
				alert("添加数据失败!");
			}
		
		});
	}else{
		
	}
}

//获取json数据的长度
function getJsonLength(jsonData) {  
	var length=0;  
	for(var ever in jsonData) {  
		length++;  
	}  
	return length;  
} 

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
	//不能进行基础数据条数新增的类型的id有:1=加油站,103=油库,118=科室
	if(dcid == null){
		alert("请添加要增加的基础数据条件");
		return;
	}else if(dcid == 1 || dcid ==103 || dcid == 118){
		alert("所选中的基础数据不能在此新增条数");
	}else{
		init();  
	    //计算弹出窗口的左上角Y的偏移量  
	    var popY=(windowHeight-popHeight)/2; //垂直方向偏移量  
	    var popX=(windowWidth-popWidth)/2;   //水平方向偏移量  
	    //设定窗口的位置  
	    $("#center").css("top",popY).css("left",popX).slideToggle("fast"); 
	    //弹出框里的组织机构树形结构
	    //ztree的配置
	    var setting_treeForOrganizationToChoose = {
	    		treeId : "treeForOrganizationToChoose",
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
	                     var zTree = $.fn.zTree.getZTreeObj("treeForOrganizationToChoose");
	                     if (treeNode.isParent) {
	                         zTree.expandNode(treeNode);
	                         return false;
	                     } else {
	                         return true;
	                     }
	                 },
	                 //ztree的点击事件
	                 onClick:function(event, treeId, treeNode) {
	                	 addNewData_ocid = treeNode.id;
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
	            		  var t = $("#treeForOrganizationToChoose");
	            		  /**
	            		   * zTree 初始化方法：$.fn.zTree.init(t, setting, zNodes)
	            		   * t:用于展现 zTree 的 DOM 容器
	            		   * setting:zTree 的配置数据
	            		   * zNodes:zTree 的节点数据
	            		   * 
	            		   */
	            		  t = $.fn.zTree.init(t, setting_treeForOrganizationToChoose, zNodes);
	            	  });
	              },
	              error : function() {
	            	  alert("获取数据失败!");
	              }
	    }); 
	    //表头信息
	  	$("#newdata_tbody").empty();
	  	var str = "<tr>";
	  	for(var i=0;i<TableHeads.length;i++){
	  		str += "<td class='attrID_"+TableHeads[i].id+"'><pre>"
	  				+TableHeads[i].name+"</pre></td>";
	  	}
	  	str += "</tr>";
	  	$("#newdata_tbody").append(str);
	}	
}  

//给出添加数据的输入框
function addDataDetail(){
	if(addNewData_ocid == null){
		alert("请选择要增加基础数据条数的加油站!");
	}else{
		addNewData_rows++;
		var str = "<tr id='addDataDetail_"+addNewData_rows+"'>";
	  	for(var i=0;i<TableHeads.length;i++){
	  		str += "<td class='attrID_"+TableHeads[i].id+"'><pre><input id='new_instance_value_newinput_"
	  		+TableHeads[i].id+"' type='text'/></pre></td>";
	  	}
	  	str += "</tr>";
	  	$("#newdata_tbody").append(str);
	}
	
}
