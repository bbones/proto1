/**
 * File railway.js Rsk 27.07.2014
 */

var RailwayLib = (function() {
	
var currentRailwayId = null; 
	
	function initRailwayGrid(){
		$("#edgRailways").edatagrid({
			url : '/protofront/service/railways/?languageId=' + IndexLib.lang(),
			saveUrl : '/protofront/service/railways/?languageId=' + IndexLib.lang(),
			updateUrl : '/protofront/service/railways/?languageId=' + IndexLib.lang(),
			toolbar : IndexLib.edgmenu({
				add : function(){
					$("#edgRailways").edatagrid('addRow');
				},
				save : function(){
					$("#edgRailways").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgRailways").edatagrid('destroyRow');
				}

			}),
			/*onSelect : function(index, row) {
				console.log(row);
				currentRailwayId = row.id;
				if (row.id != null) {
					$("#edgNames").edatagrid({
						url : '/protofront/service/railways/' + row.id + '/names',

					});
				}
			},*/
			onDestroy : function(index,row){
				$.ajax({
					url : '/protofront/service/railways/' + row.id,
					method : "DELETE"
				});
			}

		});	
	};

	return {
		init : function() {
			//debugger;
			$("#test").panel({
				href : '/protofront/forms/railway.html', 
				onLoad : function() {
					initRailwayGrid();
					//initNameGrid();
				}
			});
		}
	}
})();

RailwayLib.init();