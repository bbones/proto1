/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
/**
 * File uom.util.js
 * Created 07.09.15
 * @author Valentin Pogrebinsky
 * 
 */

define(['jquery'], function($){
	'use strict';

	var uomList = null;
	var uomMap = {};
	var d = $.Deferred();
	var uomEditor = null;
	function loadUoms () {
		$.ajax('/protofront/service/uoms/?languageId=' + language.id()).done(function(dataArray) {
			uomList = dataArray;
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				uomMap[dataArray[i].id] = dataArray[i].shortName;
			}
			uomEditor = {
		  			type:'combobox',
		          	options:{
		               valueField:'id',
		               textField:'shortName',
		               data : dataArray,
		               required:true
		           }
				};
			d.resolve();
		});
	}

	
	return {
		init : function() {
			console.log("uom.util");
			loadUoms();
			return d.promise();
		},

		uomFormatter : function(value, row) {
        	return uomMap[value];
        },

		getUomEditor : function() {
			return uomEditor;
		},
		
		getUomMap : function() {
			return uomMap;
		}
	};
});

