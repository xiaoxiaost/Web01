(function(){
	Ext.Loader.setConfig({   // 设置预加载程序
		enabled: true,
	      paths: {     // 设置命名空间
	          myapp: 'script/appjs'
	      }
	}) ;
	
	Ext.onReady(function(){
		//  直接创建一个窗口
		var win = new Ext.window.Window({
			width:400,
			height:300,
			title:'myWin',
			layout:'fit'
		});
		// win.show();
		// 通过一个按钮调用，打开一个窗口
		Ext.get("mybut").on("click",function(){
			ww.show();
		});
		
		// 通过create 方法 打开一个窗口  ---推荐使用Crate进行创建
		var createWin = Ext.create("Ext.window.Window",{
			width:400,
			height:300,
			title:'myCreatWin'
		});
		// createWin.show() ;
		
		var ww = Ext.create('appjs.mywin',{
			title:'haha',
			requires:['appjs.mywin']
		});
	});
})();