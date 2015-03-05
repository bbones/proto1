/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * 
 */

function jQueryRequest(reqURL) {
	$.ajaxSetup({
		cache : false
	});
	$.ajax(reqURL).done(function(data) {
		$("#message")[0].innerHTML = data;
	})
}

function menuAction(itemId) {
	if (itemId != "") {
		$.ajaxSetup({
			cache : false
		});
		$.ajax('/protofront/forms/' + itemId + '.html').done(function(data) {
			$("#desktop").html(data);
		});
	}
}

$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

function renderJSON(data, renderList) {
	var jo = data; //JSON.parse(data);
	for (i in renderList) {
		fname = renderList[i];
		if ($('#' + fname).attr('type')=='date') {
			var d = new Date;
			d.setTime(jo[fname]);
			$('#' + fname).val(d.getFullYear() + '-' + ('0' + (d.getMonth() + 1)).slice(-2) + '-' + ('0' + d.getDate()).slice(-2));
		} else {
			$('#' + fname).val(jo[fname]);
		}
	}

}

function renderJSONbyNames(data, renderList) {
	for (i in renderList) {
		fname = renderList[i];
		if ($("input[value='" + fname + "']").attr('type')=='date') {
			var d = new Date;
			d.setTime(data[fname]);
			$("input[name='" + fname + "']").val(d.getFullYear() + '-' + ('0' + (d.getMonth() + 1)).slice(-2) + '-' + ('0' + d.getDate()).slice(-2));
		} else
			$("input[name='" + fname + "']").val(data[fname]);
	}

}


function submitBtnAction(reqURL, renderList) {
	jsonDATA = JSON.stringify($('form').serializeObject());
	console.log(jsonDATA);
	$.ajax({
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		mimeType : 'application/json',
		data : jsonDATA,
		url : reqURL,
		success : function(respdata) {
			renderJSON(respdata, renderList);
		},
		error : function(data, status, er) {
			alert("error: " + data + " status: " + status + " er:" + er);
		}
	});

}

function findBEAction(reqURL, renderList) {
	var urlid = reqURL + $('#id').val();
	$.ajax({
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json',
		mimeType : 'application/json',
		url : urlid,
		success : function(respdata) {
			renderJSON(respdata, renderList);
		},
		error : function(data, status, er) {
			alert("error: " + data + " status: " + status + " er:" + er);
		}

	});
}

function deleteBtnAction(reqURL) {
	var urlid = reqURL + $('#id').val();
	$.ajax({
		type : 'POST',
		url : urlid,
		success : function(respdata) {
			$('form[role="form"')[0].reset();
		},
		error : function(data, status, er) {
			alert("error: " + data + " status: " + status + " er:" + er);
		}

	});
}

function clearForm() {
	$('form[role="form"')[0].reset();
}