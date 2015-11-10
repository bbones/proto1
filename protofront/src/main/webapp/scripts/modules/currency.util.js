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
 * File currency.util.js
 * author Pogrebinsky Valentyn
 * Created 11.09.15 
 */

define(['language'], function(language) {
	'use strict';

	var d = $.Deferred();
	var currencyMap = {};
	var currencyList = null;

	
	function loadCurrencies() {
		$.ajax('/protofront/service/masterdata/currencies?languageId=' + language.id()).done(function(dataArray) {
			currencyList = dataArray;
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				currencyMap[dataArray[i].numCode] = dataArray[i].charCode;
			}
			
			d.resolve();
		});

	}

	function getCurrencyList () {
		return currencyList;
	}
	
	function init() {
		loadCurrencies();
		return d.promise();
	}
	
	return {
		init : init,
        currencyFormatter : function(value, row) {
        	return currencyMap[value];
        },
        currencyEditor:{
  			type:'combobox',
          	options:{
               valueField:'id',
               textField:'name',
               data : currencyList,
               required:true
           }
		},
		getCurrencyList : getCurrencyList

	};
});