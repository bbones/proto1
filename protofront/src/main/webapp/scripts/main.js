
requirejs.config({
	'baseUrl' : 'scripts',
	'paths' : {
		'jquery' : '../easyui/jquery.min',
		'easyui' : '../easyui/jquery.easyui.min',
		'edatagrid' : '../easyui/jquery.edatagrid',
		'app' : 'app',
		'language' : 'language',
		'commonlib' : 'modules/commonlib',
		'clientRepo' : 'clientrepo',
		'enterprise' : 'modules/enterprise'
	},
	shim : {
		easyui : {
			deps : [ 'jquery' ]
		}
	}
});

require([ 'jquery', 'easyui', 'app' ], function($, easyui, app) {
	app.init();
});
