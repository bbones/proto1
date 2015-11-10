/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
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
var MyTaskLib = (function() {

	'use strict';

	function initTaskGrid() {
		function setAcceptedtoTask(accepted) {
			var row = $('#edgTask').datagrid('getSelected');
			var result;
			if (row) {
				if (accepted) {
					result = 'accepted';
				} else {
					result = 'rejected';
				}

				$.ajax({
					url : '/protofront/service/activiti/tasks/' + row.id + '/' + result,
					method : "POST"
				});
				$.messager.alert('Info', "This task was " + result);
			}

		}
		var toolbar = [ {
			text : 'Одобрить',
			iconCls : 'icon-add',
			handler : function() {
				setAcceptedtoTask(true);
			}
		}, {
			text : 'Отвергнуть',
			iconCls : 'icon-cut',
			handler : function() {
				setAcceptedtoTask(false);
			}
		} ];
		$("#edgTask").datagrid({
			url : "/protofront/service/activiti/tasks/",
			method : 'GET',
			toolbar : toolbar
		});
	}

	return {
		init : function() {
			$("#test").off();
			$("#test").panel({
				href : '/protofront/forms/activiti/myTask.html',
				onLoad : function() {
					initTaskGrid();
				}
			});
		}
	};
})();

MyTaskLib.init();