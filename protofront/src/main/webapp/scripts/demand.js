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
										+ row.id + '&' + IndexLib.lang(),
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
		for(var key in selectedParams) {
			paramList.push(selectedParams[key].parameterId);
		};
		console.log(paramList);
		$.ajax({
			dataType: 'json',
			url:'/protofront/service/demand/getconsol/' + IndexLib.lang(),
			data:JSON.stringify(paramList),
			type: 'get'
		}).done(function(result) {}); 
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