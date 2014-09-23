(function(){
	Ext.onReady(function(){
		 Ext.define('MyApp.foo.Bar', function (Bar) {
		     return {
		         statics: {
		             staticMethod: function () {
		                 Ext.MessageBox.alert("title",'Method');
		             }
		         },
		
		         method: function () {
		             return Bar.staticMethod();
		         }
		     };
		 });
		 
		 var b = new MyApp.foo.Bar() ;
		 b.method();
	});
	
	
})();