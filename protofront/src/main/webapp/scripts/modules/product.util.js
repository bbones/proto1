/**
 * File uom.util.js
 * Created 15.09.15
 * @author Valentin Pogrebinsky
 */

define(['jquery', 'language'], function($, language){
	'use strict';

	var productList = null;
	var productMap = {};
	var d = $.Deferred();
	var productEditor = null;
	function loadProducts () {
		$.ajax('/protofront/service/products/?languageId=' + language.id()).done(function(dataArray) {
			productList = dataArray;
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				productMap[dataArray[i].productId] = dataArray[i].productName;
			}
			productEditor = {
		  			type:'combobox',
		          	options:{
		               valueField:'productId',
		               textField:'productName',
		               data : dataArray,
		               required:true
		           }
				};
			d.resolve();
		});
	}

	
	return {
		init : function() {
			console.log("product.util");
			loadProducts();
			return d.promise();
		},

		productFormatter : function(value, row) {
        	return productMap[value];
        },

		getProductEditor : function() {
			return productEditor;
		}
	};
});