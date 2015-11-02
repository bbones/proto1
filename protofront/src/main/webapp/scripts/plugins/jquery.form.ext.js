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
		var postData = opts.onBeforePost.call(target, $(target));
		function _submit(postData) {
			var form = $(target);
			var url = opts.postUrl ? opts.postUrl : opts.url;
			$.ajax({
				type : opts.method,
				url : url,
				data : postData,
				dataType : opts.datatype,
				contentType:opts.contentType,
				success : function(data) {
					opts.success(data);
				},
				error : function() {
					opts.onPostError.apply(target, arguments);
				}
			});
		}
		_submit(postData);
	}

	$.extend($.fn.form.defaults, {
		onPostError : function(data) {
		},
		onBeforePost : function(formData) {
			return formData.serialize();
		},
		datatype : 'json',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		postUrl : null,
		method : 'POST'
	});

	$.extend($.fn.form.methods, {
		submitAjax : function(jq, options) {
			return jq.each(function() {
				submitAjax(this, options);
			});
		}
	});

})(jQuery);