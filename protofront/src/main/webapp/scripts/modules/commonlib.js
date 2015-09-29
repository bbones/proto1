/**
 * 
 */

define(function(){
	
    function dateFormatter (value) {
    	var d = new Date(value);
    	return d.toLocaleDateString();
    }
    
    function dateParser(s){
    	if (!isNaN(s))
    		return new Date(s);
        if (!s) return new Date();
        var ss = (s.split('.'));
        var d = parseInt(ss[0],10);
        var m = parseInt(ss[1],10);
        var y = parseInt(ss[2],10);
        if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
            return new Date(y,m-1,d);
        } else {
            return new Date();
        }
    }

	return {
		dateFormatter : dateFormatter,
		dateParser : dateParser,
 		edgmenu : function(onClick) {
			return [
			    {
			    	iconCls: 'icon-add',
			    	handler: onClick.add,
			    	plain : true,
			    	text : 'New'
			    }, 
			    {
			    	iconCls: 'icon-save',
			    	handler: onClick.save,
			    	plain : true,
			    	text : 'Save'
			    }, 
			    {
			    	iconCls: 'icon-remove',
			    	handler: onClick.destroy,
			    	plain : true,
			    	text : 'Delete'
			    },
			    {
			    	iconCls: 'icon-cancel',
			    	handler: onClick.cancel,
			    	plain : true,
			    	text : 'Cancel'
			    }

			];
		}

	};
});