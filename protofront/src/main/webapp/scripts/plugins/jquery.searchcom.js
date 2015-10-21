(function($){
	
	'use strict';

	function init(target) {
		console.log('init searchCom');
		var opts = $.data(target, 'searchcom').options;
		console.log(opts);
		$(target).panel({
			iconCls:'icon-ok',
			tools:[
 				{
 					iconCls:'icon-search',
 					handler:function () {
 						search(target);
 					}
 				},{
 					iconCls:'icon-ok',
 					handler:select
 				},{
 					iconCls:'icon-clear',
 					handler:clear
 				}
 				],
 			content : "<form id='srchform'></form><table id='srchGrid' rownumbers='true' pagination='true'></table>"
		});
		$("#srchGrid").datagrid(opts.grid);
		$("#srchform").load(opts.frm);
	}
	
	function search(target) {
		var opts = $.data(target, 'searchcom').options;
		var data = $("#srchform").serializeObject();
		$("#srchGrid").datagrid({
			url : opts.url, // + '&' + $("#srchform").serialize()
			queryParams : data
		});
	}
	
	function select() {
		alert("Select");
	}
	
	function clear() {
		alert("Clear");
	}

	$.fn.searchcom = function(options, param){
		if (typeof options === 'string'){
			return $.fn.searchcom.methods[options](this, param);
		}
		
		options = options || {};
		
		return this.each(function(){
			var state = $.data(this, 'searchcom');
			if (state){
				$.extend(state.options, options);
			} else {
				state = $.data(this, 'searchcom', {
					options:$.extend({}, $.fn.searchcom.defaults, $.fn.searchcom.parseOptions(this), options)
				});
				init(this);
			}
		});
	};

	$.fn.searchcom.methods = {
		options: function(jq){
			return $.data(jq[0], 'searchcom').options;
		}
	};
	
	$.fn.searchcom.parseOptions = function(target){
		return $.extend({}, $.parser.parseOptions(target, ['width','height','url']));
	};
	

	
})(jQuery);