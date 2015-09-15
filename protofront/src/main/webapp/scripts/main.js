requirejs.config({
	'baseUrl' : 'scripts',
	'paths' : {
		'jquery' : '../lib/jquery-2.1.1',
		'easyui' : '../easyui/jquery.easyui.min',
		'edatagrid' : '../easyui/jquery.edatagrid',
		'datagrid.excel' : '../easyui/jquery.datagrid.excel',
		'easyui.form.ext' : 'plugins/jquery.form.ext',
		'easyui.grid.ext' : 'plugins/jquery.grid.ext',
		'app' : 'app',
		'language' : 'language',
		'uomUtil' : 'modules/uom.util',
		'currencyUtil' : 'modules/currency.util',
		'commonlib' : 'modules/commonlib',
		'enterprise' : 'modules/enterprise',
		'orgUnitUtil' : 'modules/orgUnit.util',
		'person' : 'modules/person',
		'productType' : 'modules/productType',
		'product' : 'modules/product',
		'productUtil' : 'modules/product.util',
		'parameter' : 'modules/parameter',
		'orgunit' : 'modules/orgunit',
		'uom' : 'modules/uom',
		'contract' : 'modules/contract',
		'railway' : 'modules/railway',
		'ordermod' : 'modules/order/ordermod',
		'request' : 'modules/order/request'
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
