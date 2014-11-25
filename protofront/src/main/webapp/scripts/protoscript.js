/**
 * 
 */

function jQueryRequest(reqURL) {
	$.ajax(reqURL).done(function(data) {
		$("#message")[0].innerHTML = data;
	})
}

function menuAction(itemId) {
	$.ajaxSetup({
		cache : false
	});
	if (itemId == "enterprise") {
		$.ajax('/protofront/forms/enterprise.html').done(function(data) {
			$("#desktop").html(data);
		});
	}
	if (itemId == "person") {
		$.ajax('/protofront/forms/person.html').done(function(data) {
			$("#desktop").html(data);
		});
	}
}

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};