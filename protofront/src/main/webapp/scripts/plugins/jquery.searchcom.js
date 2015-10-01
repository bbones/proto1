(function($){
	
	function init(target) {
		console.log('init searchCom');
		var opts = $.data(target, 'searchcom').options;
		console.log(opts);
		$(target).html("<div class='easyui-panel'><form id='srchform'></form><table id='srchGrid'></table></div>");
		$("#srchGrid").datagrid(opts.grid);
		$("#srchform").load(opts.url)
	}
	
	$.fn.searchcom = function(options, param){
		if (typeof options == 'string'){
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
	}
	
	$.fn.searchcom.parseOptions = function(target){
		return $.extend({}, $.parser.parseOptions(target, ['width','height','url']));
	};
	

	
})(jQuery);