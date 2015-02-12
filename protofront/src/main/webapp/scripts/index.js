/**
 * 
 */

var IndexLib = (function(){
	
	function selectLang(rec){
		console.log(rec.id);
	}
	
	function setDefaultLang () {
		$('#langSelector').combobox('setValue', '1');
		console.log($('#langSelector').combobox('getValue'));
	}
	
	function initMenu() {
		$('#mainMenu').tree({
			onClick : function(node) { 
				if (node.id != "") {
					$("#test").panel('refresh', '/protofront/forms/' + node.id + '.html');
				} 
			}
		});
	}
	
	function initLangSelector() {
		$('#langSelector').combobox({
			onSelect: function (rec) {
				selectLang(rec);
			},
			onLoadSuccess: function() {
				setDefaultLang();
			}
		});
	}
	
	return {
		init:function() {
			initLangSelector();
			initMenu();
		}
	}
})();


