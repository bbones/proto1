/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky All rights reserved.
 ******************************************************************************/
/**
 * File app.js Created 31.08.15
 */

define(["jquery", "easyui", "require", "language", "commonlib", "modules/authlib"], function($, easyui, require, language, commonlib, authlib) {
  'use strict';

  function initLanguageList() {
    $("body").on('languageInited', function() {
      $('#langSelector').combobox({
        valueField: 'id',
        textField: 'name',
        data: language.getLanguageList(),
        onSelect: function(rec) {
          console.log('trigger LanguageChanged');
          $("body").trigger($.Event('LanguageChanged', rec));
        }

      });
    });
  }

  function initMenu() {
    $("body").on('LanguageChanged', function(rec) {
      console.log('fire LanguageChanged');
      $("#mainMenu").tree({
        url: "main_menu_" + rec.locale + ".json",
        method: "GET"
      });
    });
    $('#mainMenu').tree({
      url: 'main_menu_RU.json',
      method: 'GET',
      onClick: function(node) {
        if (node.module !== null) {
          console.log("module->" + node.module);
          require([node.module], function(module) {
            console.log(module);
            module.init();
          });
        }
      }
    });
    $('#miLogout').click(function() {
      authlib.logout();
    });
    $('#miLogin').click(function() {
      authlib.showForm();
    });

  }
  function init() {
    $('body').layout({
      id: 'desktop'
    });
    $('body').layout('add', {
      region: 'center',
      title: 'Work area',
      id: 'spa-cntr',
      onLoad: function() {
        console.log(window.location.hash);

      }
    }).layout('add', {
      region: 'north',
      height: 'auto',
      id: 'spa-head',
      href: "htmlparts/north.html",
      onLoad: function() {
        language.init();
        initLanguageList();
        authlib.init();
      }
    }).layout('add', {
      region: 'west',
      title: 'Navigator',
      width: 300,
      split: true,
      id: 'spa-west',
      href: "htmlparts/west.html",
      onLoad: function() {
        initMenu();
        if (window.location.hash) {
          var module = window.location.hash.split('#')[1].split('/')[0];
          console.log(module);
          require([module], function(module) {
            console.log(module);
            module.init();
          });
        }

      }

    }).layout('add', {
      region: 'south',
      title: 'Messages',
      id: 'massages',
      height: '100px',
      href: "htmlparts/south.html"
    });
  }

  return {
    init: init,
  };
});
