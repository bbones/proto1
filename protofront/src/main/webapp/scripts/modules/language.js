/**
 * File language.js language module definition
 */

define([ 'jquery' ], function($) {
	console.log("Language module");

	var languageList = [];
	var languageMap = new Object();
	var currentLanguage = 3; // Russian
	var d = $.Deferred();

	function loadLanguages() {
		$.ajax('/protofront/service/masterdata/languages').done(
				function(dataArray) {
					languageList = dataArray;

					var length = dataArray.length;
					for (var i = 0; i < length; i++) {
						languageMap[dataArray[i].id] = dataArray[i].name;
					}
					;
					languageEditor = {
						type : 'combobox',
						options : {
							valueField : 'id',
							textField : 'name',
							data : getLanguageList(),
							required : true
						}
					};
					console.log('Trigger languageInited');
					$('body').trigger($.Event('languageInited'));
					d.resolve();
				});
	}
	;

	function getLanguageList() {
		return languageList;
	}
	;

	function getLanguageMap() {
		return languageMap;
	}

	function getCurrentLanguage() {
		return currentLanguage;
	}

	function init() {
		loadLanguages();
		$("body").on("LanguageChanged", function(event) {
			currentLanguage = event.id;
		});
		window.language = this;
		return d;

	}

	return {
		init : init,
		getLanguageList : getLanguageList,
		id : getCurrentLanguage,
		languageFormatter : function(value, row) {
			return languageMap[value];
		},
		languageEditor : function() {
			return languageEditor;
		}

	}

})