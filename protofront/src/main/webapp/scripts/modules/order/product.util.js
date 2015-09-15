/**
 * File uom.util.js
 * Created 15.09.15
 * @author Valentin Pogrebinsky
 */

define(['jquery'], function($){
	var productList = null;
	var productMap = new Object;
	var d = $.Deferred();
	var productEditor = null;
	function loadProducts () {
		$.ajax('/protofront/service/products/?languageId=' + language.id()).done(function(dataArray) {
			productList = dataArray;
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				productMap[dataArray[i].id] = dataArray[i].name;
			};
			productEditor = {
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
			console.log("product.util");
			loadProducts();
			return d.promise();
		},

		productFormatter : function(value, row) {
        	return uomMap[value];
        },

		getProductEditor : function() {
			return uomEditor
		}
	}
});