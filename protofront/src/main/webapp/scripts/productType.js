/**
 * File producType.js
 * Created 08.04.2015
 * Copyright Valentin Pogrebinsky 
 */

var ProductTypeLib = (function(){
	function initTree() {
		$('#productTypes').tree({
			url : '/protofront/service/productTypes/parents/?languageId='
					+ IndexLib.lang(),
			method : "GET",
			dnd : true,
			animated : true,
			onSelect :function(data) {
				console.log(data);
				$('#edg').edatagrid({
					url : '/protofront/service/productTypes/' + data['id'] + '/names/',
				});
			},
			onDrop: function(targetNode, source, point){
				console.log(targetNode);
				console.log(source);
				console.log(point);
			},
            onContextMenu: function(e, node){
                e.preventDefault();
        		$('#productTypes').tree('select', node.target);
                $('#mm').menu('show',{
                    left: e.pageX,
                    top: e.pageY
                });
            }
		});
	};
	
 
	
	function initTranslationGrid() {
		$('#edg').edatagrid({
			method : 'GET',
			saveUrl : '/protofront/service/productTypes/names',
			updateUrl : '/protofront/service/productTypes/names',
			toolbar : IndexLib.edgmenu({
				add : function(){
					$("#edg").edatagrid('addRow', {row : {productTypeId : $("#productTypes").tree('getSelected').id}});
				},
				save : function(){
					$("#edg").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edg").edatagrid('destroyRow');
				},
				cancel : function() {
					$("#edg").edatagrid('cancelRow');
				}

			}),
			onSave : function(index,row) {
				if (row.languageId == IndexLib.lang()) {
					var node = $('#productTypes').tree('getSelected');
					$('#productTypes').tree('update', {
						target: node.target,
						text: row.productTypeName
					});
				}
			}
		});
	};
	
	 
	return {
		init : function() {
			$("#test").panel({
				href : '/protofront/forms/productType.html', 
				onLoad : function() {
					initTree();
					initTranslationGrid();
				}
			});

		},
		newRootNode : function() {
			 $.ajax({
				type : 'POST',
				url : '/protofront/service/productTypes/?languageId='+ IndexLib.lang(),
				success : function(respdata) {
					var node = {
						id : respdata['id'], 
						text : respdata['localizedProductName']
					};
					$('#productTypes').tree('append', {
				         data : [node]
				     });
				},
				error : function(data, status, er) {
					alert("error: " + data + " status: " + status + " er:" + er);
				}

			});
		},
		newNode : function() {
			var parentnode = $('#productTypes').tree('getSelected');
			if (parentnode) {
				$.ajax({
					type : 'POST',
					url : '/protofront/service/productTypes/',
					data : {
						parentId : parentnode['id'],
						languageId : IndexLib.lang()
					},
					success : function(respdata) {
						var node = {
							id : respdata['id'],
							text : respdata['localizedProductName']
						};
						$('#productTypes').tree('append', {
							parent : parentnode.target,
							data : [ node ],
						});
					},
					error : function(data, status, er) {
						alert("error: " + data + " status: " + status + " er:"
								+ er);
					}

				});
			}
		},
		removeNode : function() {
			var node = $('#productTypes').tree('getSelected');
			if (node) {
				$.ajax({
					type : 'DELETE',
					url : '/protofront/service/productTypes/' + node['id'],
					success : function() {
						$('#productTypes').tree('remove', node.target);
						$('#edg').datagrid('loadData', [ {} ]);
					},
					error : function(data, status, er) {
						alert("error: " + data + " status: " + status + " er:"
								+ er);
					}
				});
			}
		}
	};
})();

ProductTypeLib.init();

