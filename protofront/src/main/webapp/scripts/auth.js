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
			if (ajaxOptions.url.indexOf("/service/") != -1) {
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
