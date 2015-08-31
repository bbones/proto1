/**
 * File language.js
 * language module definition
 */

define(['jquery'], function($) {
	var languageList = [];
	var languageMap = new Object();
	
	function loadLanguages() {
		$.ajax('/protofront/service/masterdata/languages').done(function(dataArray) {
			languageList = dataArray;
			
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				languageMap[dataArray[i].id] = dataArray[i].name;
			};
			languageEditor = {
					type:'combobox',
			      	options:{
			           valueField:'id',
			           textField:'name',
			           data : getLanguageList(),
			           required:true
			       }
				};
			checkCounter();
		});
		
	};
	
	function init() {
		loadLanguages();
	}
	
	return {
		init : init,
		languageList : languageList
	}
	
})