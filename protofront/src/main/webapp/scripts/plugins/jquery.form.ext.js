/*******************************************************************************
 * Copyright (C) 2015   Boris Efimenko
 *
 * mail:Boris.Efimenko@isd.com.ua
 * https://github.com/BorisEfimenko
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/
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