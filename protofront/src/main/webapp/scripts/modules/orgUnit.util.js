/**
 * File orgUnit.util.js
 * Created 15.09.15
 * @author Valentin Pogrebinsky
 */

define(['jquery'], function($){
	var orgUnitList = null;
	var orgUnitMap = new Object;
	var d = $.Deferred();
	var orgUnitEditor = null;
	function loadOrgUnits () {
		$.ajax('/protofront/service/orgunits/enterprise/' + 45 +'?languageId=' + language.id()).done(function(dataArray) {
			orgUnitList = dataArray;
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				orgUnitMap[dataArray[i].id] = dataArray[i].name;
			};
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
        	return uomMap[value];
        },

		getOrgUnitEditor : function() {
			return uomEditor
		}
	}
});