/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 */

var ParameterLib = (function(){
	
	function initParameterGrid() {
		$("#edgParameters").edatagrid({
			url : '/protofront/service/parameters/?languageId=' + IndexLib.lang(),
			saveUrl : '/protofront/service/parameters/?languageId=' + IndexLib.lang(),
			updateUrl : '/protofront/service/parameters/?languageId=' + IndexLib.lang(),
			onSelect : function(index, row) {
				console.log(row);
				if (row.parameterId != null) {
					$("#edgNames").edatagrid({
						url : '/protofront/service/parameters/' + row.parameterId + '/names',
						saveUrl : '/protofront/service/parameters/' + row.parameterId + '/names',
						updateUrl :'/protofront/service/parameters/' + row.parameterId + '/names'

					});
					$.ajax('/protofront/service/parameters/' + row.parameterId + '/uoms').done(function (data) {
						console.log(data);
						for (var i=0; i < data.length()) {
							$('#dlUOM').datagrid('selectRow', );
						}
					});
				}
			} // OnSelect
		});
		
	};
	
	function initNamesGrid() {
		$("#edgNames").edatagrid();
	};
	
	function initUOMS() {
		$('#dlUOM').datalist({
		    url: '/protofront/service/masterdata/uoms?languageId=' + IndexLib.lang()
		});
	};
	
	return {
		init : function() {
			initParameterGrid();
			initNamesGrid();
			initUOMS();
		},
		appendParameter : function() {
			$("#edgParameters").edatagrid('addRow');
		},
		removeParameter :  function() {
			$("#edgParameters").edatagrid('destroyRow');
		},
		acceptParameter : function() {
			$("#edgParameters").edatagrid('saveRow');
		},
		appendName : function() {
			$("#edgNames").edatagrid('addRow');
		},
		removeName :  function() {
			$("#edgNames").edatagrid('destroyRow');
		},
		acceptName : function() {
			$("#edgNames").edatagrid('saveRow');
		},
		getChanges : function() {
			console.log($('#dlUOM').datagrid('getSelections'));
		}
	};
})();