/**
 * File clientrepo.js
 * Created 24/06/15
 * Master data repository
 */

var ClientRepo = (function() {
	var languageMap = new Object();
	var languageList = null;
	var currencyMap = new Object();
	var currencyList = null;
	var afterInit = null;
	
	var counter = 0;
	
	function checkCounter() {
		counter++;
		if (counter === 1) {
			var event = $.Event( 'RepoLoaded' );
			$("#desktop").trigger(event);
			afterInit();
		}
	}
	
	function loadLanguages() {
		$.ajax('/protofront/service/masterdata/languages').done(function(dataArray) {
			languageList = dataArray;
			
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				languageMap[dataArray[i].id] = dataArray[i].name;
			};

			checkCounter();
		});
		
	};
	
	function loadCurrencies() {
		$.ajax('/protofront/service/masterdata/currencies?languageId=' + IndexLib.lang()).done(function(dataArray) {
			currencyList = dataArray;
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				currencyMap[dataArray[i].numCode] = dataArray[i].charCode;
			};
		});

	}
	
	function getLanguageList() {
		return languageList;
	};
	
	function getLanguageMap() {
		return languageMap;
	}
	
	function init(after) {
		afterInit = after;
		loadLanguages();
		loadCurrencies();
	}
	return {
		init : init,
		getLanguageList : getLanguageList,
		getLanguageMap : getLanguageMap
	}
})();



