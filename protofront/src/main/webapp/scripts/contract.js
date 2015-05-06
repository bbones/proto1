/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
/**
 * File contract.js
 * Created 17.04.15
 * 
 */

var ContractLib = (function(){
	function initContractGrid() {
		$("#edgContract").edatagrid();
	};
	
	function initSupplementGrid(){
		$("#edgSupplement").edatagrid();
		
	};

	return {
		init : function() {
			initContractGrid();
			initSupplementGrid();
		}
	}
})();
