/**
 * File ordermod.js
 * Created 12/06/15
 * Author Valentin Pogrebinsky
 */

var OrderMod = (function() {
	var orderURL = null;
	var currentOrderId = null;
	var currentOrderLineId = null;
	var options = null;
	
	function initOrderGrid() {
		console.log(orderURL);
		$("#edgOrder").edatagrid({
			url : orderURL + '?languageId=' + IndexLib.lang(),
			saveUrl : orderURL + '?languageId=' + IndexLib.lang(),
			updateUrl : orderURL + '?languageId=' + IndexLib.lang(),
			method:'GET',
			toolbar : IndexLib.edgmenu({
				add : function(){
					$("#edgOrder").edatagrid('addRow');
				},
				save : function(){
					$("#edgOrder").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgOrder").edatagrid('destroyRow');
				}

			}),
			onSelect : function(index, row) {
				if (row.id != null) {
					currentOrderId  = row.id;
					var event = jQuery.Event( 'orderSelected' );
					$("#test").trigger(event, row.id);
				} // if
			}, // OnSelect edgOrder
			onDestroy : function(index,row){
				$.ajax({
					url : orderURL + row.orderId,
					method : "DELETE"
				});
			}
		});
	};
	
	function initLinesGrid() {
		$("#test").on('orderSelected', 
			function(event, id) {
				console.log("Order Selected ->" + id);
				$("#edgLines").edatagrid({
					url : orderURL  +  id + '/lines?languageId=' 
							+ IndexLib.lang()
				});
			}
		);
		
		$("#edgLines").edatagrid({
			method:'GET',
			saveUrl : orderURL + 'lines?languageId=' + IndexLib.lang(),
			updateUrl : orderURL + 'lines?languageId=' + IndexLib.lang(),
			toolbar : IndexLib.edgmenu({
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
					$("#test").trigger(event, row.orderLineId);
				} // if
			}, // OnSelect edgLines

			onDestroy : function(index,row){
				$.ajax({
					url : orderURL + 'lines/' + row.orderLineId,
					method : "DELETE"
				});
			}
		});
	};
	
	function fillParameterTemplate() {
		console.log("Fill Parameter Template");
		$.ajax({
			url : orderURL + 'lines/' + currentOrderLineId + '/fillparameters',
			method : "POST"
		});
	}
	
	function initLineParam() {
		$("#test").on('orderLineSelected', 
			function(event, olId) {
				$("#edgLineParameters").edatagrid({
					url : orderURL + 'lines/'+ olId + 
						'/lineparameters?languageId=' + IndexLib.lang()
				});
			}
		);
		$("#edgLineParameters").edatagrid({
			method:'GET',
			saveUrl : orderURL + 'lines/lineparameters?languageId=' + IndexLib.lang(),
			updateUrl : orderURL + 'lines/lineparameters?languageId=' + IndexLib.lang(),
			toolbar : IndexLib.edgmenu({
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
					url : orderURL + 'lines/lineparameters' + row.olpId,
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

	function load(orderUrl) {
		console.log('Order.load');
		$("#test").unbind();
		$("#test").panel({
			href : '/protofront/forms/order.html', 
			onLoad : function(){
				orderURL = orderUrl;
				initOrderGrid();
				initLinesGrid();
				initLineParam();
				if (typeof options.onLoad != 'undefined') {
					options.onLoad();
				}
			}
		});
	};

	return {
		init : function(opt) {
			options = opt;
			load(options.url);
		},
		button : function (value, row, index) {
            return '<input type="button" onclick="alert(' + row.olpId + ')" value="Add" class="GridButton"/>';
        }
	};
})();

