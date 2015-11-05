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
		debugger;
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