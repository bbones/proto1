define([ 'language' ], function(language) {

	'use strict';
	

	function position(taskId, processDefinitionId) {
		if ((taskId !== 'undefined') && (processDefinitionId !== 'undefined')) {
			$.ajax(
					'/protofront/service/activiti/form/form-data/?taskId='
							+ taskId + '&processDefinitionId='
							+ processDefinitionId).done(function(data) {
				var properties = data.formProperties;
				var length = properties.length;
				for (var i = 0; i < length; i++) {
					console.log(properties[i]);
				}
			});
		}
	}

	return {
		position : position
	};
});
