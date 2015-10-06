/**
 * File uom.util.js
 * Created 15.09.15
 * @author Valentin Pogrebinsky
 */

define(['jquery'], function($){
	'use strict';

	var parameterList = null;
	var parameterMap = {};
	var d = $.Deferred();
	var parameterEditor = null;
	function loadParameters () {
		$.ajax('/protofront/service/parameters/?languageId=' + language.id()).done(function(dataArray) {
			parameterList = dataArray;
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				parameterMap[dataArray[i].parameterId] = dataArray[i].parameterName;
			}
			parameterEditor = {
		  			type:'combobox',
		          	options:{
		               valueField:'parameterId',
		               textField:'parameterName',
		               data : dataArray,
		               required:true
		           }
				};
			d.resolve();
		});
	}

	
	return {
		init : function() {
			console.log("parameter.util");
			loadParameters();
			return d.promise();
		},

		parameterFormatter : function(value, row) {
        	return parameterMap[value];
        },

		getParameterEditor : function() {
			return parameterEditor;
		}
	};
});