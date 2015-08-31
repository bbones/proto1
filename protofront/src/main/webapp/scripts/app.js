/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * File app.js
 * Created 31.08.15
 */

var AppModule = (function(){
	require(['clientRepo'], function() {
		ClientRepo.init();
	});
	
	function init() {
		$('body').layout();
		$('body').layout('add', {
			  region:'center',
			  title:'Work area',
			  id:'spa-cntr', 
			  onLoad : function() {
				  
			  }
			}).layout('add', {
			  region:'north',
			  height: 'auto',
			  id:'spa-head',
			  href:"htmlparts/north.html",
			  onLoad : function () {
				  console.log("North loaded");
			  }
			}).layout('add', {
			  region:'west',
			  title:'Navigator',
			  width:300,
			  split:true,
			  id:'spa-west',
			  href:"htmlparts/west.html"
			}).layout('add', {
				  region:'south',
				  title:'Messages',
				  id:'massages',
				  height : '100px',
				  href:"htmlparts/south.html"
				});
	}
	
	return {
		init : init,
		
	}
})();



