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
 * File uom.js author Pogrebinsky Valentyn Created 04.05.15
 */

define(['commonlib', 'edatagrid', 'language'], function(commonlib, edatagrid, language) {

  'use strict';

  var currentUOMId = null;

  function initUOMGrid() {
    $("#edgUOMs").edatagrid({
      url: '/protofront/service/uoms/?languageId=' + language.id(),
      saveUrl: '/protofront/service/uoms/?languageId=' + language.id(),
      updateUrl: '/protofront/service/uoms/?languageId=' + language.id(),
      // autoSave: true,
      toolbar: commonlib.edgmenu({
        add: function() {
          $("#edgUOMs").edatagrid('addRow');
        },
        save: function() {
          $("#edgUOMs").edatagrid('saveRow');
        },
        destroy: function() {
          $("#edgUOMs").edatagrid('destroyRow');
        }

      }),
      onSelect: function(index, row) {
        console.log(row);
        currentUOMId = row.id;
        if (row.id !== null) {
          var optsDG = $("#edgNames").datagrid('options');
          optsDG.url = '/protofront/service/uoms/' + row.id + '/names';
          var optsEDG = $("#edgNames").edatagrid('options');
          optsEDG.url = '/protofront/service/uoms/' + row.id + '/names';
          $("#edgNames").edatagrid('reload');
        }
      },
      onDestroy: function(index, row) {
        $.ajax({
          url: '/protofront/service/uoms/' + row.id,
          method: "DELETE"
        });
      }

    });
  }

  function initTranslationGrid() {
    $("#edgNames").edatagrid({
      idField : 'nameId',
      url: '',
      saveUrl: '/protofront/service/uoms/names',
      updateUrl: '/protofront/service/uoms/names',
/*      onSelect: function(index, row) {
        var opts = $("#edgNames").edatagrid('options');
        var idValue = row[opts.idField||'id'];
        if (idValue !== null) {         
          opts.destroyUrl = opts.updateUrl+'/' + idValue;
        }
      },*/
      // autoSave: true,
      toolbar: commonlib.edgmenu({
        add: function() {
          $("#edgNames").edatagrid('addRow', {
            row: {
              uomId: currentUOMId
            }
          });
        },
        save: function() {
          $("#edgNames").edatagrid('saveRow');
        },
        destroy: function() {
          $("#edgNames").edatagrid('destroyRow');
        },
        cancel: function() {
          $("#edgNames").edatagrid('cancelRow');
        }
      })
    });
  }

  function init() {
    window.location.hash = "#uom/";
    $("#spa-cntr").off();
    $.when(language.init()).done(function() {
      $("#spa-cntr").panel({
        href: '/protofront/forms/uom.html',
        onLoad: function() {
          initUOMGrid();
          initTranslationGrid();
        }
      });
    });
  }

  return {
    init: init
  };
});
