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
	$.fn.pagination.defaults.beforePageText = 'Էջ';
	$.fn.pagination.defaults.afterPageText = 'ից {pages}';
	$.fn.pagination.defaults.displayMsg = 'Դիտել {from}-ից {to}-ը {total} գրառումից';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = 'Մշակվում է, խնդրում ենք սպասել ...';
}
if ($.fn.treegrid && $.fn.datagrid){
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
	$.messager.defaults.ok = 'Այո';
	$.messager.defaults.cancel = 'Փակել';
}
$.map(['validatebox','textbox','filebox','searchbox',
		'combo','combobox','combogrid','combotree',
		'datebox','datetimebox','numberbox',
		'spinner','numberspinner','timespinner','datetimespinner'], function(plugin){
	if ($.fn[plugin]){
		$.fn[plugin].defaults.missingMessage = 'Այս դաշտը պարտադիր է.';
	}
});
if ($.fn.validatebox){
	$.fn.validatebox.defaults.rules.email.message = 'Խնդրում ենք մուտքագրել գործող e-mail հասցե.';
	$.fn.validatebox.defaults.rules.url.message = 'Խնդրում ենք մուտքագրել գործող URL.';
	$.fn.validatebox.defaults.rules.length.message = 'Խնդրում ենք մուտքագրել արժեք {0}  {1}.';
	$.fn.validatebox.defaults.rules.remote.message = 'Խնդրում ենք ուղղել այս դաշտը.';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.firstDay = 1;
	$.fn.calendar.defaults.weeks  = ['Կ.','Ե.','Ե.','Չ.','Հ.','Ու.','Շ.'];
	$.fn.calendar.defaults.months = ['Հունվար', 'Փետրվար', 'Մարտ', 'Ապրիլ', 'Մայիս', 'Հունիս', 'Հուլիս', 'Օգոստոս', 'Սեպտեմբեր', 'Հոկտեմբեր', 'Նոյեմբեր', 'Դեկտեմբեր'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = 'Այսօր';
	$.fn.datebox.defaults.closeText = 'Փակել';
	$.fn.datebox.defaults.okText = 'Այո';
}
if ($.fn.datetimebox && $.fn.datebox){
	$.extend($.fn.datetimebox.defaults,{
		currentText: $.fn.datebox.defaults.currentText,
		closeText: $.fn.datebox.defaults.closeText,
		okText: $.fn.datebox.defaults.okText
	});
}
