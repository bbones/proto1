/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
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
 * GNU v2 license text in root directory of project
 *******************************************************************************/
/**
 * File uom.util.js
 * Created 15.09.15
 * @author Valentin Pogrebinsky
 */

define(['jquery', 'language'], function($, language){
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