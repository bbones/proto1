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
/**
 * TODO CRUD
 */

define(["commonlib", "language", "edatagrid"],function(commonlib, language, edatagrid){
	
	'use strict';

	function initPersonGrid() {
		$("#edgPerson").edatagrid({
			url : "/protofront/service/persons/?languageId=" +	language.id(),
			method : 'GET',
			onSelect : function(index, row) {
				console.log(row);
				$("#edgNames").edatagrid({
					method : 'GET',
					url : '/protofront/service/persons/' + row.personId +'/names'
				});
			} // OnSelect
		});
	}
	
	function initNameGrid() {
		$("#edgNames").edatagrid({});
	}

	return {
		init : function() {
			window.location.hash = "#person/"; 
			$("#spa-cntr").off();
			$("#spa-cntr").panel({
				href : '/protofront/forms/person.html', 
				onLoad : function() {
					initPersonGrid();
					initNameGrid();
				}
			});
		}
	};
});

