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
	
	function initMenu() {
		$('#mainMenu').tree({
			onClick : function(node) {
				if (node.module != null) {
					$.getScript("/protofront/scripts/" + node.module).done();
				} else {
					if (node.id != "") {
//					if (node.id == "purchaseOrder") {
//						$.getScript("/protofront/scripts/order.js").done(function() {
//							OrderLib.init("#test", {
//								orderURL : "/protofront/service/purchaseorders/",
//								scriptURL : "/protofront/scripts/purchaseOrder.js"
//							});
//						});
//					} else {
//						if (node.id == "uom") {
//						} else {
							$("#test").panel('refresh', '/protofront/forms/' + node.id + '.html');
//						}
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
		
		$.extend($.fn.datebox.defaults, {
			getValue: function(target){
				var opts = $(target).datebox('options');
				var v = $(target).datebox('getValue');
				return opts.parser.call(target,v).getTime();
			},
			setValue: function(target, value){
				console.log('setValue');
				var opts = $(target).datebox('options');
				var d = new Date(parseInt(value));
				var s = opts.formatter.call(target, d);
				$(target).datebox('setValue', s);
			},
			formatter:function(t){
				console.log('format ' + t);
				date = new Date(t);
				var d = date.getDate();
				var m = date.getMonth();
				var y = date.getFullYear();

				return d+'.'+m+'.'+y;
			},
			parser:function(s){
				console.log('parse ' + s);
				if (!s){return new Date();}
				var ss = s.split('.');
				var d = parseInt(ss[0],10);
				var m = parseInt(ss[1],10);
				var y = parseInt(ss[2],10);
				return new Date(y,m,d);
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
	
	return {
		init : function() {
			initMenu();
			initEasyUIEditors();
			initLanguageList();
		},
		lang : function() {
			return $('#langSelector').combobox('getValue');
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
        languageFormatter : function(value, row) {
        	return languageMap[value];
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
		}
	};
})();


