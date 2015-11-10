/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/
/**
 * 
 */

define(function(){
	'use strict';
    function dateFormatter (value) {
    	var d = new Date(value);
    	return d.toLocaleDateString();
    }
    
    function dateParser(s){
    	if (!isNaN(s)) {
    		return new Date(s);
    	}
        if (!s) { return new Date(); }
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