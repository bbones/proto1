/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * 
 */

var IndexLib = (function(){
	
	function initMenu() {
		$('#mainMenu').tree({
			onClick : function(node) { 
				if (node.id != "") {
					$("#test").panel('refresh', '/protofront/forms/' + node.id + '.html');
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
	
	return {
		init : function() {
			initMenu();
			initEasyUIEditors();
		},
		lang : function() {
			return $('#langSelector').combobox('getValue');
		},
		getRootProductType : function() {
			return 5;
		}
	}
})();


