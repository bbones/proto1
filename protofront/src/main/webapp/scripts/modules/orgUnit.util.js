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
 * File orgUnit.util.js
 * Created 15.09.15
 * @author Valentin Pogrebinsky
 */

define(['jquery', 'language'], function($, language){
	'use strict';

	var orgUnitList = null;
	var orgUnitMap = {};
	var d = $.Deferred();
	var orgUnitEditor = null;
	function loadOrgUnits () {
		$.ajax('/protofront/service/orgunits/enterprise/' + 45 +'?languageId=' + language.id()).done(function(dataArray) {
			orgUnitList = dataArray;
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				orgUnitMap[dataArray[i].id] = dataArray[i].name;
			}
			orgUnitEditor = {
		  			type:'combobox',
		          	options:{
		               valueField:'id',
		               textField:'name',
		               data : dataArray,
		               required:true
		           }
				};
			d.resolve();
		});
	}

	
	return {
		init : function() {
			console.log("orgUnit.util");
			loadOrgUnits();
			return d.promise();
		},

		orgUnitFormatter : function(value, row) {
        	return orgUnitMap[value];
        },

		getOrgUnitEditor : function() {
			return orgUnitEditor;
		},
		getOrgUnitList : function() {
			return orgUnitList;
		}
	};
});