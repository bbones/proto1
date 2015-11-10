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
