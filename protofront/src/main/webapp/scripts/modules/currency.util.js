/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * File currency.util.js
 * author Pogrebinsky Valentyn
 * Created 11.09.15 
 */

define(function() {
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