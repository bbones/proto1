/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * File uom.js
 * author Pogrebinsky Valentyn
 * Created 04.05.15 
 */

define(['commonlib'], function(commonlib){
	
	var currentUOMId = null; 
	
	function initUOMGrid(){
		$("#edgUOMs").edatagrid({
			url : '/protofront/service/uoms/?languageId=' + language.id(),
			saveUrl : '/protofront/service/uoms/?languageId=' + language.id(),
			updateUrl : '/protofront/service/uoms/?languageId=' + language.id(),
			toolbar : commonlib.edgmenu({
				add : function(){
					$("#edgUOMs").edatagrid('addRow');
				},
				save : function(){
					$("#edgUOMs").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgUOMs").edatagrid('destroyRow');
				}

			}),
			onSelect : function(index, row) {
				console.log(row);
				currentUOMId = row.id;
				if (row.id != null) {
					$("#edgNames").edatagrid({
						url : '/protofront/service/uoms/' + row.id + '/names',

					});
				}
			},
			onDestroy : function(index,row){
				$.ajax({
					url : '/protofront/service/uoms/' + row.id,
					method : "DELETE"
				});
			}

		});	
	};
	
	function initTranslationGrid(){
		$("#edgNames").edatagrid({
			onDestroy : function(index,row){
				$.ajax({
					url : '/protofront/service/uoms/names/' + row.nameId,
					method : "DELETE"
				});
			},
			saveUrl : '/protofront/service/uoms/names',
			updateUrl :'/protofront/service/uoms/names',
			toolbar : commonlib.edgmenu({
				add : function(){
					$("#edgNames").edatagrid('addRow', {row : {uomId : currentUOMId}});
				},
				save : function(){
					$("#edgNames").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgNames").edatagrid('destroyRow');
				}

			})
		});		
	};
	
	function init() {
		window.location.hash = "#uom/"; 
		$("#spa-cntr").off();
		$.when(uomUtil.init(), language.init()).done(function() {
			$("#spa-cntr").panel({
				href : '/protofront/forms/uom.html', 
				onLoad : function() {
					initUOMGrid();
					initTranslationGrid();
				}
			});
		});
	}
	
	return {
		init : init
	};	
});



