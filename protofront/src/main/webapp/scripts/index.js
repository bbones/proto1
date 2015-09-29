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
	
	function initMenuItem() {	
		$.getScript("/protofront/scripts/auth.js").done(function() {
	        $('#miLogout').click(function() {
				AuthLib.logout();
			});
			$('#miLogin').click(function() {
				AuthLib.showForm();
			});
	    });
				
	}
	;	
	function initMenu() {
		$("#desktop").on('LanguageChanged', function(rec){
        	$("#mainMenu").tree({url : "main_menu_" + rec.locale + ".json", method : "GET"});
		});
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
		$("#desktop").on('RepoLoaded', function() {
			console.log('RepoLoaded');
			$('#langSelector').combobox({
    			valueField:'id',
    			textField:'name',
    			data : ClientRepo.getLanguageList(),
    			onSelect : function(rec) {
    				console.log(rec);
    				$("#desktop").trigger($.Event( 'LanguageChanged', rec));
    			}

			});
		});
	}

	function initRepo() {
		console.log("Init from init")
		$.getScript("/protofront/scripts/clientrepo.js").done(function() {
			ClientRepo.init();
		});
	}
	function setup() {
	    $(document).ajaxError(function(event, jqXHR, ajaxSettings, thrownError) {
		  if (jqXHR.status == 404) {
              alert("Element "+ajaxSettings.url +" not found.");
          } else {
              alert("Error: " + thrownError+ "Response: " +jqXHR);
          }
		});

	}

	return {
		init : function() {
			setup();
			initMenu();
            initMenuItem();
			initEasyUIEditors();
			initLanguageList();
			initRepo();
		},
		// Att!!! lang() return string value
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
			    },
			    {
			    	iconCls: 'icon-cancel',
			    	handler: onClick.cancel,
			    	plain : true,
			    	text : 'Cancel'
			    }

			];
		}
	};
})();


