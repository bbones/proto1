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
if ($.fn.pagination){
	$.fn.pagination.defaults.beforePageText = 'صفحة';
	$.fn.pagination.defaults.afterPageText = 'من {pages}';
	$.fn.pagination.defaults.displayMsg = 'عرض {from} إلى {to} من {total} عنصر';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = 'معالجة, الرجاء الإنتظار ...';
}
if ($.fn.treegrid && $.fn.datagrid){
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
	$.messager.defaults.ok = 'موافق';
	$.messager.defaults.cancel = 'إلغاء';
}
$.map(['validatebox','textbox','filebox','searchbox',
		'combo','combobox','combogrid','combotree',
		'datebox','datetimebox','numberbox',
		'spinner','numberspinner','timespinner','datetimespinner'], function(plugin){
	if ($.fn[plugin]){
		$.fn[plugin].defaults.missingMessage = 'هذا الحقل مطلوب.';
	}
});
if ($.fn.validatebox){
	$.fn.validatebox.defaults.rules.email.message = 'الرجاء إدخال بريد إلكتروني صحيح.';
	$.fn.validatebox.defaults.rules.url.message = 'الرجاء إدخال رابط صحيح.';
	$.fn.validatebox.defaults.rules.length.message = 'الرجاء إدخال قيمة بين {0} و {1}.';
	$.fn.validatebox.defaults.rules.remote.message = 'الرجاء التأكد من الحقل.';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['S','M','T','W','T','F','S'];
	$.fn.calendar.defaults.months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = 'اليوم';
	$.fn.datebox.defaults.closeText = 'إغلاق';
	$.fn.datebox.defaults.okText = 'موافق';
}
if ($.fn.datetimebox && $.fn.datebox){
	$.extend($.fn.datetimebox.defaults,{
		currentText: $.fn.datebox.defaults.currentText,
		closeText: $.fn.datebox.defaults.closeText,
		okText: $.fn.datebox.defaults.okText
	});
}
