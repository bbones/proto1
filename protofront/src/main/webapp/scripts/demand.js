/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 */

var DemandLib = (function(){
	
	var initParameterGrid = function () {
		$("#dgParameters").datagrid({});
		
	}
	
	var initProductSelector = function() {
		$("#productType").combotree({
			url:'/protofront/service/productType/getByParentTypeIdByLanguageId/' + IndexLib.lang(),
			onSelect : function(record) {
				$("#dgProducts").datagrid({
					url : '/protofront/service/product/prodListByProdTypeIdByLanguageId/'
									+ record.id + '&' + IndexLib.lang(),
					onSelect : function(index, row) {
						console.log(row);
						$("#dgParameters").datagrid({
							url : '/protofront/service/product/parameters/'
										+ row.id + '&' + IndexLib.lang()
						});
					} // OnSelect
				}); // edatagrid

			}
		});
		$("#dgProducts").datagrid();
	}
	
	
	var initDemandGrid = function() {
		var selectedParams = $("#dgParameters").datagrid('getSelections');
		var paramList = new Array();
		var columnList = new Array();
		for(var key in selectedParams) {
			paramList.push(selectedParams[key].parameterId);
			columnList.push({field:'F'+selectedParams[key].parameterId,title:selectedParams[key].parameterName,width:80});
		};
		columnList.push({field:'qnty',title:'qnty',width:80});
		console.log(paramList);
		$.ajax({
			dataType: 'json',
			url:'/protofront/service/demand/getconsol/' + IndexLib.lang() + "&" + $("#dgProducts").datagrid('getSelected').id,
			data: JSON.stringify(paramList), // {paramList : JSON.stringify(paramList)},
			type: 'post',
			contentType: 'application/json',
		    mimeType: 'application/json'
		}).done(function(result) {
			console.log(result);
			console.log(columnList);
			$("#dgDemand").datagrid({
				columns : [columnList],
				data : result
			})
		}); 
	}

	return {
		init : function() {
			initProductSelector();
			initParameterGrid();
		},
		showDemand : function() {
			initDemandGrid();
		}
	}
})();