/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
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