/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
(function($){
function _1(_2){
var _3=$.data(_2,"spinner");
var _4=_3.options;
var _5=$.extend(true,[],_4.icons);
_5.push({iconCls:"spinner-arrow",handler:function(e){
_6(e);
}});
$(_2).addClass("spinner-f").textbox($.extend({},_4,{icons:_5}));
var _7=$(_2).textbox("getIcon",_5.length-1);
_7.append("<a href=\"javascript:void(0)\" class=\"spinner-arrow-up\" tabindex=\"-1\"></a>");
_7.append("<a href=\"javascript:void(0)\" class=\"spinner-arrow-down\" tabindex=\"-1\"></a>");
$(_2).attr("spinnerName",$(_2).attr("textboxName"));
_3.spinner=$(_2).next();
_3.spinner.addClass("spinner");
};
function _6(e){
var _8=e.data.target;
var _9=$(_8).spinner("options");
var up=$(e.target).closest("a.spinner-arrow-up");
if(up.length){
_9.spin.call(_8,false);
_9.onSpinUp.call(_8);
$(_8).spinner("validate");
}
var _a=$(e.target).closest("a.spinner-arrow-down");
if(_a.length){
_9.spin.call(_8,true);
_9.onSpinDown.call(_8);
$(_8).spinner("validate");
}
};
$.fn.spinner=function(_b,_c){
if(typeof _b=="string"){
var _d=$.fn.spinner.methods[_b];
if(_d){
return _d(this,_c);
}else{
return this.textbox(_b,_c);
}
}
_b=_b||{};
return this.each(function(){
var _e=$.data(this,"spinner");
if(_e){
$.extend(_e.options,_b);
}else{
_e=$.data(this,"spinner",{options:$.extend({},$.fn.spinner.defaults,$.fn.spinner.parseOptions(this),_b)});
}
_1(this);
});
};
$.fn.spinner.methods={options:function(jq){
var _f=jq.textbox("options");
return $.extend($.data(jq[0],"spinner").options,{width:_f.width,value:_f.value,originalValue:_f.originalValue,disabled:_f.disabled,readonly:_f.readonly});
}};
$.fn.spinner.parseOptions=function(_10){
return $.extend({},$.fn.textbox.parseOptions(_10),$.parser.parseOptions(_10,["min","max",{increment:"number"}]));
};
$.fn.spinner.defaults=$.extend({},$.fn.textbox.defaults,{min:null,max:null,increment:1,spin:function(_11){
},onSpinUp:function(){
},onSpinDown:function(){
}});
})(jQuery);

