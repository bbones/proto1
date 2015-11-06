/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
define(["commonlib", "language", "edatagrid"],function(commonlib, language, edatagrid){
	'use strict';

	function initTree() {
		$('#productTypes').tree({
			url : '/protofront/service/productTypes/parents/?languageId='
					+ language.id(),
			method : "GET",
			dnd : true,
			animated : true,
			onSelect :function(data) {
				console.log(data);
				$('#edg').edatagrid({
					url : '/protofront/service/productTypes/' + data.id + '/names/',
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
	}
	
 
	
	function initTranslationGrid() {
		$('#edg').edatagrid({
			method : 'GET',
			saveUrl : '/protofront/service/productTypes/names',
			updateUrl : '/protofront/service/productTypes/names',
			toolbar : commonlib.edgmenu({
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
				if (row.languageId === language.id()) {
					var node = $('#productTypes').tree('getSelected');
					$('#productTypes').tree('update', {
						target: node.target,
						text: row.productTypeName
					});
				}
			}
		});
	}
	
	 
	return {
		init : function() {
			window.location.hash = "#productType/"; 
			$("#spa-cntr").off();
			$("#spa-cntr").panel({
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
				url : '/protofront/service/productTypes/?languageId='+ language.id(),
				success : function(respdata) {
					var node = {
						id : respdata.id, 
						text : respdata.localizedProductName
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
						parentId : parentnode.id,
						languageId : language.id()
					},
					success : function(respdata) {
						var node = {
							id : respdata.id,
							text : respdata.localizedProductName
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
					url : '/protofront/service/productTypes/' + node.id,
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
});

