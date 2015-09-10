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
				},
				saveToExcel : function(){
					$("#edgRailways").datagrid('toExcel','Railways');
				}

			}),
			onSelect : function(index, row) {
				//console.log("onSelectRailway");
				//console.log(row);
				currentRailwayId = row.id;
				if (row.id != null) {
					$("#edgRailwayNames").edatagrid({
						url : '/protofront/service/railways/' + row.id + '/names',

					});
				}
			},
			onDestroy : function(index,row){
				$.ajax({
					url : '/protofront/service/railways/' + row.id,
					method : "DELETE"
				});
			}

		});	
	};

	function initNameGrid(){
		$("#edgRailwayNames").edatagrid({
			onDestroy : function(index,row){
				$.ajax({
					url : '/protofront/service/railways/names/' + row.id,
					method : "DELETE"
				});
			},
			saveUrl : '/protofront/service/railways/names',
			updateUrl :'/protofront/service/railways/names',
			toolbar : IndexLib.edgmenu({
				add : function(){
					$("#edgRailwayNames").edatagrid('addRow', {row : {railwayId : currentRailwayId}});
				},
				save : function(){
					$("#edgRailwayNames").edatagrid('saveRow');
				},
				destroy : function(){
					$("#edgRailwayNames").edatagrid('destroyRow');
				},
				saveToExcel : function(){
					$("#edgRailwayNames").datagrid('toExcel','RailwayNames');
				}

			})
		});		
	};
	
	return {
		init : function() {
			//debugger;
			$("#test").panel({
				href : '/protofront/forms/railway.html', 
				onLoad : function() {
					initRailwayGrid();
					initNameGrid();
				}
			});
		}
	}
}

)();

RailwayLib.init();