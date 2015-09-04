requirejs.config({
	'baseUrl' : 'scripts',
	'paths' : {
		'jquery' : '../easyui/jquery.min',
		'easyui' : '../easyui/jquery.easyui.min',
		'edatagrid' : '../easyui/jquery.edatagrid',
		'easyui.form.ext' : 'plugins/jquery.form.ext',
		'easyui.grid.ext' : 'plugins/jquery.grid.ext',
		'app' : 'app',
		'language' : 'language',
		'commonlib' : 'modules/commonlib',
		'clientRepo' : 'clientrepo',
		'enterprise' : 'modules/enterprise',
		'productType' : 'modules/productType',
		'person' : 'modules/person'
	},
	shim : {
		easyui : {
			deps : [ 'jquery' ],
			exports : 'jquery'
		},

		'easyui.edatagrid' : {
			exports : 'jquery',
			deps : [ 'jquery', 'easyui' ]
		},
		'easyui.form.ext' : {
			exports : 'jquery',
			deps : [ 'jquery', 'easyui' ]
		},
		'productType' : {
			exports : 'productType'
		}
	}
});

require([ 'jquery', 'easyui', 'app' ], function($, easyui, app) {
	app.init();
});
