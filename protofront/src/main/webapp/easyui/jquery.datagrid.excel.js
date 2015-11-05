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
/**
 * allow to save datagrid in Excel file
 */

$.extend($.fn.datagrid.methods, {
	  toExcel: function(jq, filename){
	    return jq.each(function(){
	      var uri = 'data:application/vnd.ms-excel;base64,'
	      , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><meta charset="UTF-8"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
	      , base64 = function (s) { return window.btoa(unescape(encodeURIComponent(s))) }
	      , format = function (s, c) { return s.replace(/{(\w+)}/g, function (m, p) { return c[p]; }) }

	      var alink = $('<a style="display:none"></a>').appendTo('body');
	      var view = $(this).datagrid('getPanel').find('div.datagrid-view');
	      
	      var table = view.find('div.datagrid-view2 table.datagrid-btable').clone();
	      var tbody = table.find('>tbody');
	      view.find('div.datagrid-view1 table.datagrid-btable>tbody>tr').each(function(index){
	        $(this).clone().children().prependTo(tbody.children('tr:eq('+index+')'));
	      });
	      
	      var head = view.find('div.datagrid-view2 table.datagrid-htable').clone();
	      var hbody = head.find('>tbody');
	      view.find('div.datagrid-view1 table.datagrid-htable>tbody>tr').each(function(index){
	        $(this).clone().children().prependTo(hbody.children('tr:eq('+index+')'));
	      });    
	      hbody.prependTo(table);
	      
	      var ctx = { worksheet: name || 'Worksheet', table: table.html()||'' };
	      alink[0].href = uri + base64(format(template, ctx));
	      alink[0].download = filename;
	      alink[0].click();
	      alink.remove();
	    })
	  }
	});