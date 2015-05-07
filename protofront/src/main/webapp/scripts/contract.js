/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * File contract.js
 * Created 17.04.15
 * 
 */

var ContractLib = (function(){
	var edgmenu = function(onClick) {
		return [
				    {
				    	iconCls: 'icon-add',
				    	handler: onClick.add,
				    	plain : true,
				    	text : 'New'
				    }, 
				    {
				    	iconCls: 'icon-save',
				    	handler: onClick.save,
				    	plain : true,
				    	text : 'Save'
				    }
				];
	};
		

	function initContractGrid() {
		var event = jQuery.Event( "contractSelected" );
		$("#edgContract").edatagrid({
			toolbar : edgmenu({ 
				add : function(){$("#edgContract").edatagrid('addRow');},
				save : function(){$("#edgContract").edatagrid('saveRow');}
			}),
			url : "/protofront/service/contracts/",
			method : 'GET',
			onSelect : function(index, row) {
				event.contractId = row.id;
				$(document).trigger(event);
			} // OnSelect
		});
	};

	function initSidesGrid(){
		$(document).on("contractSelected", function(event){
			$("#edgSides").edatagrid({
				url : "/protofront/service/contracts/" + event.contractId + "/sides?languageId=" + IndexLib.lang()
			});
		});
		$("#edgSides").edatagrid({
			toolbar : edgmenu({
					add : function(){console.log('add');},
					save : function(){console.log('save');}
			}),
			method : 'GET'
		});
		
	};

	function initSupplementGrid(){
		$(document).on("contractSelected", function(event){
			$("#edgSupplement").edatagrid({
				url : "/protofront/service/contracts/" + event.contractId + "/supplements"
			});
		});
		$("#edgSupplement").edatagrid({
			toolbar : edgmenu({
					add : function(){console.log('add');},
					save : function(){console.log('save');}
			}),
			method : 'GET'
		});
		
	};

	return {
		init : function() {
			$("#test").panel({
				href : '/protofront/forms/contract.html', 
				onLoad : function() {
					initContractGrid();
					initSidesGrid();
					initSupplementGrid();
				}
			});
		}
	};
})();

ContractLib.init();
