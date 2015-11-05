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
 * File language.js language module definition
 */

define([ 'jquery' ], function($) {
	
	'use strict';

	console.log("Language module");

	var languageList = [];
	var languageMap = {};
	var languageEditor = {};
	var currentLanguage = 3; // Russian
	var d = $.Deferred();

	function loadLanguages() {
		$.ajax('/protofront/service/masterdata/languages').done(
				function(dataArray) {
					languageList = dataArray;

					var length = dataArray.length;
					for (var i = 0; i < length; i++) {
						languageMap[dataArray[i].id] = dataArray[i].name;
					}
					
					languageEditor = {
						type : 'combobox',
						options : {
							valueField : 'id',
							textField : 'name',
							data : getLanguageList(),
							required : true
						}
					};
					console.log('Trigger languageInited');
					$('body').trigger($.Event('languageInited'));
					d.resolve();
				});
	}
	

	function getLanguageList() {
		return languageList;
	}
	

	function getLanguageMap() {
		return languageMap;
	}

	function getCurrentLanguage() {
		return currentLanguage;
	}

	function init() {
		loadLanguages();
		$("body").on("LanguageChanged", function(event) {
			currentLanguage = event.id;
		});
		window.language = this;
		return d;

	}

	return {
		init : init,
		getLanguageList : getLanguageList,
		id : getCurrentLanguage,
		languageFormatter : function(value, row) {
			return languageMap[value];
		},
		languageEditor : function() {
			return languageEditor;
		}

	};

});