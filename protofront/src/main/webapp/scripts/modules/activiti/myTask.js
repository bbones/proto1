define(['jquery'], function($) {

  'use strict';

  function initTaskGrid() {
    function setAcceptedtoTask(accepted) {
      var row = $('#edgTask').datagrid('getSelected');
      var result = '';
      if (row) {
        if (accepted) {
          result = 'accepted';
        } else {
          result = 'rejected';
        }

        $.ajax({
          url: '/protofront/service/activiti/tasks/' + row.id + '/' + result,
          method: "POST"
        });
        $.messager.alert('Info', "This task was " + result);
      }

    }
    var toolbar = [{
      text: 'Одобрить',
      iconCls: 'icon-add',
      handler: function() {
        setAcceptedtoTask(true);
      }
    }, {
      text: 'Отвергнуть',
      iconCls: 'icon-cut',
      handler: function() {
        setAcceptedtoTask(false);
      }
    }];
    $("#edgTask").datagrid({
      url: "/protofront/service/activiti/tasks/",
      method: 'GET',
      toolbar: toolbar
    });
  }

  return {
    init: function() {
      window.location.hash = "#myTask/";
      $("#spa-cntr").off();
      $("#spa-cntr").panel({
        href: '/protofront/forms/activiti/myTask.html',
        onLoad: function() {
          initTaskGrid();
        }
      });

    }
  };
});
