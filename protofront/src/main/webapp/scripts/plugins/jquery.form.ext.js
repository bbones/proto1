(function($) {

	'use strict';

	function submitAjax(target, options) {
		var opts = $.data(target, 'form').options;
		$.extend(opts, options || {});
		var param = $.extend({}, opts.queryParams);
		if (opts.onSubmit.call(target, param) === false) {
			return;
		}
		$(target).find('.textbox-text:focus').blur();

		_submit(param);
		function _submit(param) {
			var form = $(target);
			$.ajax({
				type : 'POST',
				url : opts.url,
				data : form.serialize(),
				dataType : opts.datatype,
				success : function(data) {
					opts.success(data);
				},
				error : function() {
					opts.onPostError.apply(target, arguments);
				}
			});

		}

	}
	

	$.extend($.fn.form.defaults, {
		onPostError : function(data) {
		},
		dataType: 'json'
	});

	$.extend($.fn.form.methods, {
		submitAjax : function(jq, options) {
			return jq.each(function() {
				submitAjax(this, options);
			});
		}
	});

})(jQuery);