/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 */

var ParameterLib = (function(){
	var initUOM = true;
	
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
						var d = $('#dlUOM').datalist('getData').rows;
						var l = d.length;
						initUOM = true;
						for ( var i=0; i<l; i++ ) {
							var indx = data.indexOf(d[i].uomId);
							if (indx != -1) {
								$('#dlUOM').datagrid('checkRow', i);
							} else {
								$('#dlUOM').datagrid('uncheckRow', i);
							}
						};
						initUOM = false;
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
		    url: '/protofront/service/uoms/?languageId=' + IndexLib.lang(),
		    onCheck : function(index, row) {
		    	if (!initUOM) {
		    		var par = $("#edgParameters").edatagrid('getSelected').parameterId;
		    		$.post('/protofront/service/parameters/' + par + '/uoms',{
		    			'uomId' : row.uomId
		    		});
		    	};
		    },
		    onUncheck : function(index, row) {
		    	if (!initUOM) {
		    		var par = $("#edgParameters").edatagrid('getSelected').parameterId;
		    		$.ajax({
		    			url : '/protofront/service/parameters/' + par + '/uoms/' + row.uomId,
		    			method : 'DELETE'
		    		});
		    	};
		    }

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
		},
		acceptUOM : function() {
			console.log($('#dlUOM').datagrid('getChecked'));
		}
	};
})();