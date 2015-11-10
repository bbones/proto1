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
define([ 'jquery', 'easyui.form.ext' ], function($, form) {

	'use strict';
	function getTaskTemplate(task) {
		var result = '/protofront/forms/activiti/';
		if (new Date(task.createTime).getSeconds() % 2) {
			result = result + 'taskForm1.html';
		} else {
			result = result + 'taskForm2.html';
		}
		return result;
	}
	function initTaskForm(formData) {
		var properties = formData.formProperties;
		var length = properties.length;
		var data = {};
		for (var i = 0; i < length; i++) {
			data[properties[i].id] = properties[i].value;
		}
		var _taskForm = $('#task' + formData.taskId);
		_taskForm.form({
			postUrl : '/protofront/service/form/form-data/',
			contentType : 'application/json',
			onBeforePost : function(postData) {
				var result = {
					processDefinitionId : formData.processDefinitionId,
					taskId : formData.taskId,
					businessKey : formData.businessKey,
					properties : []
				};
				var formFields = postData.serializeArray();
				var map = {};
				var length = formFields.length;
				for (var i = 0; i < length; i++) {
					map[formFields[i].name] = formFields[i].value;
				}
				result.properties = $.map(formData.formProperties, function(formProperty) {

					return formProperty.writable ? {
						id : formProperty.id,
						value : map[formProperty.id]
					} : null;
				});
				return JSON.stringify(result);
			}
		});
		_taskForm.form('load', data);
		$("#btnSaveTask").click(function() {
			_taskForm.form('submitAjax');
		});
	}
	function initTaskGrid() {
		function setAcceptedtoTask(accepted) {
			var row = $('#edgTask').datagrid('getSelected');
			var result = '';
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
			toolbar : toolbar,
			onSelect : function(index, row) {
				if (row.id !== null) {
					var task=row;
					$.ajax('/protofront/service/form/form-data/?taskId=' + row.id).done(function(formData) {

						var taskForm = $('#taskForm');
						taskForm.panel({
							href :  getTaskTemplate(task),
							onLoad : function() {
								initTaskForm(formData);
							},
							extractor : function(data) {
								var html = $.fn.panel.defaults.extractor(data);
								var text = $('<form id="task' + formData.taskId + '"></form>').html(html);
								return text;
							}
						});

					});

				}
			},
		});
	}

	return {
		init : function() {
			window.location.hash = "#myTask/";
			$("#spa-cntr").off();
			$("#spa-cntr").panel({
				href : '/protofront/forms/activiti/myTask.html',
				onLoad : function() {
					initTaskGrid();
				}
			});

		}
	};
});
