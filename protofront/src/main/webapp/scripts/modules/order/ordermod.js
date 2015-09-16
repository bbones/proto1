/**
 * File ordermod.js
 * Created 12/06/15
 * Author Valentin Pogrebinsky
 */

define (['language', 'commonlib', 'edatagrid', 'uomUtil', 'productUtil'], function(language, commonlib, edatagrid, uomUtil, productUtil) {
	var serviceURL = null;
	var currentOrderId = null;
	var currentOrderLineId = null;
	var options = null;
	
	function initOrderGrid() {
		console.log(options.serviceUrl);
		$("#edgOrder").edatagrid({
			url : options.serviceUrl + '?languageId=' + language.id(),
			saveUrl : options.serviceUrl + '?languageId=' + language.id(),
			updateUrl : options.serviceUrl + '?languageId=' + language.id(),
			method:'GET',
			toolbar : commonlib.edgmenu({
				add : function(){
					$("#edgOrder").edatagrid('addRow', {row : {documentNo : "DOC", issueDate : new Date()}});
					var addPressed = jQuery.Event('addPressed');
					$("#spa-cntr").trigger(addPressed);
				},
				save : function(){
					var savePressed = jQuery.Event('savePressed');
					$("#spa-cntr").trigger(savePressed);
					// $("#edgOrder").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgOrder").edatagrid('destroyRow');
				}

			}),
			onSelect : function(index, row) {
				if (row.id != null) {
					currentOrderId  = row.id;
					var event = jQuery.Event( 'orderSelected' );
					$("#spa-cntr").trigger(event, row.id);
				} // if
			}, // OnSelect edgOrder
			onDestroy : function(index,row){
				$.ajax({
					url : options.serviceUrl + row.id,
					method : "DELETE"
				});
			}
		});
	};
	
	function initLinesGrid() {
		$("#spa-cntr").on('orderSelected', 
			function(event, id) {
				console.log("Order Selected ->" + id);
				$("#edgLines").edatagrid({
					url : options.serviceUrl  +  id + '/lines?languageId=' 
							+ language.id()
				});
			}
		);
		
		$("#edgLines").edatagrid({
			method:'GET',
			saveUrl : options.serviceUrl + 'lines?languageId=' + language.id(),
			updateUrl : options.serviceUrl + 'lines?languageId=' + language.id(),
			toolbar : commonlib.edgmenu({
				add : function(){
					$("#edgLines").edatagrid('addRow', {row : {orderId : currentOrderId}});
				},
				save : function(){
					$("#edgLines").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgLines").edatagrid('destroyRow');
				}

			}),
			onSelect : function(index, row) {
				if (row.orderLineId != null) {
					currentOrderLineId = row.orderLineId;
					var event = jQuery.Event( 'orderLineSelected' );
					$("#spa-cntr").trigger(event, row.orderLineId);
				} // if
			}, // OnSelect edgLines

			onDestroy : function(index,row){
				$.ajax({
					url : options.serviceUrl + 'lines/' + row.orderLineId,
					method : "DELETE"
				});
			}
		});
	};
	
	function fillParameterTemplate() {
		console.log("Fill Parameter Template");
		$.ajax({
			url : options.serviceUrl + 'lines/' + currentOrderLineId + '/fillparameters',
			method : "POST"
		});
	}
	
	function initLineParam() {
		$("#spa-cntr").on('orderLineSelected', 
			function(event, olId) {
				$("#edgLineParameters").edatagrid({
					url : options.serviceUrl + 'lines/'+ olId + 
						'/lineparameters?languageId=' + language.id()
				});
			}
		);
		$("#edgLineParameters").edatagrid({
			method:'GET',
			saveUrl : options.serviceUrl + 'lines/lineparameters?languageId=' + language.id(),
			updateUrl : options.serviceUrl + 'lines/lineparameters?languageId=' + language.id(),
			toolbar : commonlib.edgmenu({
					add : function(){
						$("#edgLineParameters").edatagrid('addRow', {row : {orderLineId : currentOrderLineId}});
					},
					save : function(){
						$("#edgLineParameters").edatagrid('saveRow');
					},
					destroy : function(){
						$("#edgLineParameters").edatagrid('destroyRow');
					}

				}).concat([{iconCls: 'icon-add',
			    	handler: fillParameterTemplate,
			    	plain : true,
			    	text : 'Fill template'}]), // toolbar
			onDestroy : function(index,row){
				$.ajax({
					url : options.serviceUrl + 'lines/lineparameters' + row.olpId,
					method : "DELETE"
				});
			},
			onBeforeEdit : function(index, row) {
				$.ajax({
					async : false,
					url : '/protofront/service/parameters/' + row.paramId + '/uoms',
					method : "GET",
					success : function(data) {
						var uoms = [];
						var length = data.length;
						for (var i = 0; i < length; i++) {
							uoms.push({
								id : data[i],
								shortName : ClientRepo.getUOMMap()[data[i]]
							});
						}
						var col = $("#edgLineParameters").datagrid('getColumnOption','uomId');
						col.editor = {
								type:'combobox',
						      	options:{
						           valueField:'id',
						           textField:'shortName',
						           data : uoms,
						           required:true
						       }
						};
					}
				});
				
			}

		});
	};

	function initForm() {
		$("#orderDetails").panel({
			href: options.formUrl,
			onLoad : options.onLoad
		});
	};
	
	function load() {
		console.log('Order.load');
		$("#spa-cntr").off();
		$.when(uomUtil.init(), language.init(), productUtil.init()).done(function() {
			$("#spa-cntr").panel({
				href : '/protofront/forms/order.html', 
				onLoad : function(){
					initOrderGrid();
					initLinesGrid();
					initLineParam();
					initForm();
					if (typeof options.onLoad != 'undefined') {
						options.onLoad();
					}
				}
			});
		});
	};

	return {
		init : function(opt) {
			options = opt;
			window.location.hash = "#" + options.type + "/";
			load();
		},
		button : function (value, row, index) {
            return '<input type="button" onclick="alert(' + row.olpId + ')" value="Add" class="GridButton"/>';
        }
	};
});

