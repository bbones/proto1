/**
 * File uom.util.js
 * Created 07.09.15
 * 
 */

define(['jquery'], function($){
	var uomList = null;
	var uomMap = new Object;
	var d = $.Deferred();
	var uomEditor = null;
	function loadUoms () {
		$.ajax('/protofront/service/uoms/?languageId=' + language.id()).done(function(dataArray) {
			uomList = dataArray;
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				uomMap[dataArray[i].id] = dataArray[i].shortName;
			};
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
			return uomEditor
		}
	}
});

