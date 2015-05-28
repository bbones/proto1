/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * 
 */

var IndexLib = (function(){
	var languageMap = new Object();
	var languageList = null;
	var currencyMap = new Object();
	var currencyList = null;
	
	
	function initMenu() {
		$('#mainMenu').tree({
			onClick : function(node) {
				if (node.module != null) {
					console.log("module->" + node.module);
					$.getScript("/protofront/scripts/" + node.module).done();
				} else {
					if (node.id != "") {
						$("#test").panel('refresh', '/protofront/forms/' + node.id + '.html');
					}
				} 
			}
		});
	}
	
	function initEasyUIEditors() {
		
		// Extending datebox editor to treat Date as long
		$.extend($.fn.datagrid.defaults.editors, {
			datebox2: {
				init: function(container, options){
					var input = $('<input>').appendTo(container);
					input.datebox(options);
					return input;
				},
				destroy: function(target){
					$(target).datebox('destroy');
				},
				getValue: function(target){
					var opts = $(target).datebox('options');
					var v = $(target).datebox('getValue');
					return opts.parser.call(target,v).getTime();
				},
				setValue: function(target, value){
					var opts = $(target).datebox('options');
					var d = new Date(parseInt(value));
					var s = opts.formatter.call(target, d);
					$(target).datebox('setValue', s);
				},
				resize: function(target, width){
					$(target).datebox('resize', width);
				}
			}
		});
	}
	
	function initLanguageList() {
		$.ajax('/protofront/service/masterdata/languages').done(function(dataArray) {
			console.log(dataArray);
			languageList = dataArray;
			$('#langSelector').combobox({
    			valueField:'id',
    			textField:'name',
    			data : dataArray,
    			onSelect : IndexLib.changeLanguage

			});
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				languageMap[dataArray[i].id] = dataArray[i].name;
			};
		});
	}

	function initCurrencyList() {
		$.ajax('/protofront/service/masterdata/currencies?languageId=' + IndexLib.lang()).done(function(dataArray) {
			console.log(dataArray);
			currencyList = dataArray;
			var length = dataArray.length;
			for(var i = 0; i < length; i++) {
				currencyMap[dataArray[i].numCode] = dataArray[i].charCode;
			};
		});
	}

	return {
		init : function() {
			initMenu();
			initEasyUIEditors();
			initLanguageList();
			initCurrencyList();
		},
		lang : function() {
			return $('#langSelector').combobox('getValue');
		},
        languageFormatter : function(value, row) {
        	return languageMap[value];
        },
        languageEditor:{
  			type:'combobox',
          	options:{
               valueField:'id',
               textField:'name',
               method:'get',
               url:'/protofront/service/masterdata/languages',
               required:true
           }
		},
        currencyFormatter : function(value, row) {
        	return currencyMap[value];
        },
        currencyEditor:{
  			type:'combobox',
          	options:{
               valueField:'id',
               textField:'name',
               method:'get',
               url:'/protofront/service/masterdata/languages',
               required:true
           }
		},
		getRootProductType : function() {
			return 5;
		},
		productFormatter : function(value,row){
			return row.productName;
		},
		uomFormatter : function(value,row){
			return row.uomName;
		},
        dateFormatter : function(value) {
        	var d = new Date(value);
        	return d.toLocaleDateString();
        },
        dateParser : function(s){
        	if (!isNaN(s))
        		return new Date(s);
            if (!s) return new Date();
            var ss = (s.split('.'));
            var d = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var y = parseInt(ss[2],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                return new Date(y,m-1,d);
            } else {
                return new Date();
            }
        },
        changeLanguage : function(rec) {
        	alert("main_menu_" + rec.locale + ".json");
        	$("#mainMenu").tree({url : "main_menu_" + rec.locale + ".json", method : "GET"});
        	initCurrencyList();
        },
 		edgmenu : function(onClick) {
			return [
			    {
			    	iconCls: 'icon-add',
			    	handler: onClick.add,
			    	plain : true,
			    	text : 'New'
			    }, 
			    {
			    	iconCls: 'icon-save',
			    	handler: onClick.save,
			    	plain : true,
			    	text : 'Save'
			    }, 
			    {
			    	iconCls: 'icon-remove',
			    	handler: onClick.destroy,
			    	plain : true,
			    	text : 'Delete'
			    }
			];
		}
	};
})();


