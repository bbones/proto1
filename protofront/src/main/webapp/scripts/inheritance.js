/**
 * New node file
 */

function Sup() {
}

Sup.prototype.m1  = function() {
	console.log('sup.m1');
};

Sup.prototype.m2  = function() {
	this.m1();
};


function Subsup() {
}

Subsup.prototype = new Sup();


Subsup.prototype.m1 =  function() {
	console.log('subsup.m1');
	Sup.prototype.m1.call(this);
};


var subsup = new Subsup();
subsup.m2();