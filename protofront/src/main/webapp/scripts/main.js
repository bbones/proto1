requirejs.config({
	"baseUrl" : "scripts",
	"paths" : {
		"jquery" : "../easyui/jquery.min",
		"easyui" : "../easyui/jquery.easyui.min",
		"edatagrid" : "../easyui/jquery.edatagrid",
		"index" : "index"
	},
	shim : {
		easyui : {
			deps : [ 'jquery' ]
		}
	}
});

require([ "easyui", "index" ], function() {
	$('body').layout();
	$('body').layout('add', {
		  region:'center',
		  title:'CENTER',
		  id:'spa-cntr'
		}).layout('add', {
		  region:'north',
		  height:60,
		  id:'spa-head',
		  href:"htmlparts/north.html"
		}).layout('add', {
		  region:'west',
		  width:300,
		  split:true,
		  id:'spa-west',
		  href:"htmlparts/west.html"
		});
	IndexLib.init();
});
