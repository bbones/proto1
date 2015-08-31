
requirejs.config({
	'baseUrl' : 'scripts',
	'paths' : {
		'jquery' : '../easyui/jquery.min',
		'easyui' : '../easyui/jquery.easyui.min',
		'edatagrid' : '../easyui/jquery.edatagrid',
		'app' : 'app',
		'clientRepo' : 'clientrepo'
	},
	shim : {
		easyui : {
			deps : [ 'jquery' ]
		}
	}
});

require([ 'jquery', 'easyui', 'app' ], function($, easyui, app) {
	AppModule.init();
});
