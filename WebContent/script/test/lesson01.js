Ext.define("HelloWindow",{
	extend:'Ext.window.Window',
	height:500,
	width:400,
	title:'我是一个500*400的窗口',

	 constructor: function (config) {
         this.callParent(arguments); // 调用Ext.panel.Panel的构造
         //...
     }
	
});