/**
 * File producType.js
 * Created 08.04.2015
 * Copyright Valentin Pogrebinsky 
 */

var ProductTypeLib = (function(){
	function initTreeGrid() {
		$('#productTypes').tree({
			url : '/protofront/service/productTypes/parents/?languageId='
					+ IndexLib.lang(),
			method : "GET",
			idField : 'id',
			treeField : 'name',
			dnd : true,
			onSelect :function(data) {
				console.log(data);
				$('#edg').edatagrid({
					url : '/protofront/service/productTypes/' + data['id'] + '/names/',
	             	saveUrl: '/protofront/service/productTypes/',
	             	updateUrl: '/protofront/service/productTypes/'
	             	// destroyUrl: '/protofront/service/productTypes/deleteName/'
	 
				});
			},
			onDrop: function(targetNode, source, point){
				console.log(targetNode);
				console.log(source);
				console.log(point);
			},
            onContextMenu: function(e,row){
                e.preventDefault();
                $(this).treegrid('select',row.id);
                $('#mm').menu('show',{
                    left: e.pageX,
                    top: e.pageY
                });
            }
		});
	};
	
	function initTranslationGrid() {
		$('#edg').edatagrid({method : 'GET'});
	}
	
	 
	return {
		init : function() {
			initTreeGrid();
			initTranslationGrid();
		},
		newNode : function() {
			 var node = $('#productTypes').treegrid('getSelected');
			 if (node){
				 $.ajax({
						type : 'POST',
						url : '/protofront/service/productType/getNewProductType',
						data:{
							parentId : node['id'], 
							languageId : IndexLib.lang()
						},
						success : function(respdata) {
							var node = {
								id : respdata['id'], 
								name : respdata['localizedProductName']
							};
							$('#productTypes').treegrid('append', {
						         parent:node.id,
						         data :nodes,
						     });
						},
						error : function(data, status, er) {
							alert("error: " + data + " status: " + status + " er:" + er);
						}

					});
			 }
		},
		removeNode : function() {
			 var node = $('#productTypes').treegrid('getSelected');
			 if(node) {
				 $.ajax({
					 type: 'POST',
					 url: '/protofront/service/productType/delete',
					 data: {
						 id : node['id']
					 },
					 success : function() {
						 $('#productTypes').treegrid('remove', node.id);
						 $('#dg').datagrid('loadData', [{}]);
					 },
					 error : function(data, status, er) {
						alert("error: " + data + " status: " + status + " er:" + er);
					}
				 });
			 }
		}
	};
})();

