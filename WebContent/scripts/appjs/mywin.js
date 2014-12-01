//定义一个自己的窗口，继承Ext.window.Window
	Ext.define("appjs.mywin",{
		extend:'Ext.window.Window',
		width:800,
		height:600,
		title:'win86',
		initialConfig:function(){  // 构造方法
			 this.callParent();   // 必须调用父类的构造
		}
	});