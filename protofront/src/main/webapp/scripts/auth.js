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
var AuthLib = (function() {
	'use strict';

	var storageKey = 'auth_token', headerKey = 'X-AUTH-TOKEN';
	function initialize() {
		console.log("AuthLib initialized");
		addHeader();
		// event handler for submit button
	}
	function addHeader() {
		$(document).ajaxSend(function(event, jqXHR, ajaxOptions) {
			if (ajaxOptions.url.indexOf("/service/") !== -1) {
				var token = localStorage.getItem(storageKey);
				if (token) {
					jqXHR.setRequestHeader(headerKey, token);
				}
			}		
		});
/*		
		$(document).ajaxStart({
			beforeSend : function(xhr, settings) {
				if (settings.url.indexOf("/service/") != -1) {
					var token = localStorage.getItem(storageKey);
					if (token) {
						xhr.setRequestHeader(headerKey, token);
					}
				}

			}
		});*/

	}
	function logout() {
		localStorage.removeItem(storageKey);
	}

	function authenticate(username, password) {
		$.ajax({
			url : "/protofront/login",
			data : JSON.stringify({
				username : username,
				password : password
			}),
			// dataType: "json",
			// contentType: "application/json; charset=utf-8",
			type : "POST",
			success : function(res, status, xhr) {
				// $scope.authenticated = true;
				localStorage.setItem(storageKey, xhr
						.getResponseHeader(headerKey));
				$("#loginForm").window('close');
			},
			error : function(xhr, status, error) {
				alert("Ошибка: " + xhr.responseText + " error: " + error);
			}
		});
	}

	function showForm() {
		$("#test").off();
		$("#test").panel({
			href : '/protofront/forms/loginForm.html',
			onLoad : function() {
				$('#loginForm').window('open');
				$("#btnSubmit").click(function() {
					// collect userName and password entered by users
					var username = $("#username").val();
					var password = $("#password").val();
					// var rememberme = ($('#rememberme').is(":checked"));
					// call the authenticate function
					authenticate(username, password);
				});
				$("#btnCancel").click(function() {
					$("#loginForm").window('close');
				});

			}
		});

	}
	return {
		init : function() {
			initialize();
		},
		showForm : function() {
			showForm();
		},
		logout : function() {
			logout();
		}
	};
})();
AuthLib.init();
