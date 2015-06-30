/**
 * File clientrepo.js
 * Created 24/06/15
 * Master data repository
 * Language
 * Currency
 * UOM
 */

var ClientRepo = (function() {
	
	var languageMap = new Object();
	var languageList = null;
	var languageEditor = null;
	var currencyMap = new Object();
	var currencyList = null;
	var uomList = null;
	var uomMap = new Object;
	var uomEditor = null;
	var afterInit = null;
	
	var counter = 0;
	
	function initChangeLanguageListener() {
		$("#desktop").on('LanguageChanged', function(rec){
			init();
		});
	}
	
	function checkCounter() {
		counter++;
		if (counter === 3) {
			var event = $.Event( 'RepoLoaded' );
			$("#desktop").trigger(event);
		}
	}
	
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
	
	function loadCurrencies() {
		$.ajax('/protofront/service/masterdata/currencies?languageId=' + IndexLib.lang()).done(function(dataArray) {
			currencyList = dataArray;
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				currencyMap[dataArray[i].numCode] = dataArray[i].charCode;
			};
		});

		checkCounter();
	}
	
	function getLanguageList() {
		console.log('getlanguageList');
		console.log(languageList);
		return languageList;
	};
	
	function getLanguageMap() {
		return languageMap;
	}
	
	function getCurrencyList () {
		return currencyList;
	}
	
	function getUOMList () {
		return uomList;
	}
	
	function loadUoms () {
		$.ajax('/protofront/service/uoms/?languageId=' + IndexLib.lang()).done(function(dataArray) {
			uomList = dataArray;
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				uomMap[dataArray[i].uomId] = dataArray[i].uomShortName;
			};
			uomEditor = {
		  			type:'combobox',
		          	options:{
		               valueField:'uomId',
		               textField:'uomShortName',
		               data : dataArray,
		               required:true
		           }
				};
		});
		console.log(uomList);
		checkCounter();
		
	}

	function init() {
		counter = 0;
		initChangeLanguageListener();
		loadLanguages();
		loadCurrencies();
		loadUoms();
	}
	return {
		init : init,
		getLanguageList : getLanguageList,
		getLanguageMap : getLanguageMap,
	    languageFormatter : function(value, row) {
	    	return languageMap[value];
	    },
	    languageEditor : function () { 
	    	return languageEditor; 
	    },
	    getCurrencyList : getCurrencyList,
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
	    getUOMList : getUOMList,
        uomFormatter : function(value, row) {
        	return uomMap[value];
        },
        uomEditor : function () {
        	console.log('uomEditor:');
        	console.log(uomEditor);
        	return uomEditor;
		}

	}
})();


