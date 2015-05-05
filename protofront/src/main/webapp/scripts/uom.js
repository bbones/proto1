/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * File uom.js
 * author Pogrebinsky Valentyn
 * Created 04.05.15 
 */

var UOMLib = (function(){
	function initUOMGrid(){
		$("#edgUOMs").edatagrid({
			url : '/protofront/service/uoms/?languageId=' + IndexLib.lang(),
			saveUrl : '/protofront/service/uoms/?languageId=' + IndexLib.lang(),
			updateUrl : '/protofront/service/uoms/?languageId=' + IndexLib.lang(),
			onSelect : function(index, row) {
				console.log(row);
				if (row.uomId != null) {
					$("#edgNames").edatagrid({
						url : '/protofront/service/uoms/' + row.uomId + '/names',
						saveUrl : '/protofront/service/uoms/' + row.uomId + '/names',
						updateUrl :'/protofront/service/uoms/' + row.uomId + '/names'
					});
				}
			}
		});	
	};
	
	function initTranslationGrid(){
		$("#edgNames").edatagrid();		
	};
	
	return {
		init : function() {
			$("#test").panel({
				href : '/protofront/forms/uom.html', 
				onLoad : function() {
					initUOMGrid();
					initTranslationGrid();
				}
			});
		},
		appendUOM : function() {
			$("#edgUOMs").edatagrid('addRow');
		},
		removeUOM : function() {
			$("#edgUOMs").edatagrid('destroyRow');
		},
		acceptUOM : function() {
			$("#edgUOMs").edatagrid('saveRow');
		},
		// Names
		appendName : function() {
			$("#edgNames").edatagrid('addRow');
		},
		removeName : function() {
			$("#edgNames").edatagrid('destroyRow');
		},
		acceptName : function() {
			$("#edgNames").edatagrid('saveRow');
		}
	};	
})();

UOMLib.init();

