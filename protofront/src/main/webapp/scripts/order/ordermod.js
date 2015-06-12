/**
 * File ordermod.js
 * Created 12/06/15
 * Author Valentin Pogrebinsky
 */

var OrderMod = (function() {
	var orderURL = null;

	function Order() {
	};

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
					$("#edgLines").edatagrid('addRow');
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
			method:'GET'
		});
	};


	Order.prototype.load  = function(orderUrl) {
		console.log('OrderMod.load');
		$("#test").panel({
			href : '/protofront/forms/order.html', 
			onLoad : function(){
				orderURL = orderUrl;
				initOrderGrid();
				initLinesGrid();
				initLineParam();
			}
		});
	};

	Order.prototype.m2  = function() {
		this.m1();
	};

	return {
		init : function() {},
		getInstance : function() { return new Order();},
		getOrder : function() { return Order },
		button : function (value, row, index) {
            return '<input type="button" onclick="alert(' + row.olpId + ')" value="Add" class="GridButton"/>';
        }

	};
})();

