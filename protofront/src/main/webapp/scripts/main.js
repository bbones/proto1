requirejs.config({
	'baseUrl' : 'scripts',
	'paths' : {
		'jquery' : '../easyui/jquery.min',
		'easyui' : '../easyui/jquery.easyui.min',
		'edatagrid' : '../easyui/jquery.edatagrid',
		'datagrid.excel' : '../easyui/jquery.datagrid.excel',
		'easyui.form.ext' : 'plugins/jquery.form.ext',
		'easyui.grid.ext' : 'plugins/jquery.grid.ext',
		'app' : 'app',
		'language' : 'language',
		'uomUtil' : 'modules/uom.util',
		'commonlib' : 'modules/commonlib',
		'enterprise' : 'modules/enterprise',
		'person' : 'modules/person',
		'productType' : 'modules/productType',
		'product' : 'modules/product',
		'parameter' : 'modules/parameter',
		'uom' : 'modules/uom',
		'railway' : 'modules/railway'
	},
	shim : {
		'easyui' : {
			deps : [ 'jquery' ],
			exports : 'jquery'
		},
		'easyui.edatagrid' : {
			exports : 'jquery',
			deps : [ 'jquery', 'easyui' ]
		},
		'datagrid.excel' : {
			exports : 'jquery',
			deps : [ 'jquery', 'easyui' ]
		},
		'easyui.form.ext' : {
			exports : 'jquery',
			deps : [ 'jquery', 'easyui' ]
		}
	}
});

var app = require([ 'app' ], function(app) {
	app.init();
});
