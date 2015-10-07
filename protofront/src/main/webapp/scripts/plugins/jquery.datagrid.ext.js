// $('#edgNames').datagrid('getColumnOption',
//                              'languageId').editor = getLangEditor();
(function($) {

	'use strict';

	function addColumnProperies(target, columnsoptions) {
		var opts = $.data(target, 'datagrid').options;

	}

	$.extend($.fn.datagrid.methods, {
		addColumnProperies : function(jq, columnsoptions) {
			return jq.each(function() {
				addColumnProperies(this, columnsoptions);
			});
		}
	});

})(jQuery);