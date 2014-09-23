(function(){
	Ext.onReady(function(){
		// 传统的弹出框，会将后续程序挂起
		var fn = function(){
			alert("导致程序挂起，后续代码无法进行");
			Ext.DomHelper.insertHtml("beforeEnd",Ext.getBody().dom,'执行到了这里了吗？');
		};
		
	//	fn();
		
		// Ext,MessageBox 不会终止后续程序的运行，其本质为DIV+CSS的组合
		var fn1 = function(){
			Ext.Msg.alert("友情提示：",'程序不会挂起奥');
			Ext.DomHelper.insertHtml("beforeEnd",Ext.getBody().dom,'执行到了这里了啊！！！');
		};
		
	//	fn1();
		
	// 简单的交互式弹出框
	/*	
	Ext.Msg.show({
		title:'提示',
		msg:'此操作无法回退，是否要集训？',
		buttons:Ext.Msg.YESNO,
		fn:function(btnId,text,opt){
			if(btnId=='yes'){
				Ext.DomHelper.insertHtml('beforeEnd',Ext.getBody().dom,'你选择此操作');
			}else if(btnId=='no'){
				Ext.DomHelper.insertHtml('beforeEnd',Ext.getBody().dom,'你取消了此操作');
			}
		},
		icon:Ext.Msg.WARNING
	});
	*/
	// prompt 的使用	
	/*
	Ext.Msg.prompt('温馨提示','请输入你的名字：',function(butId,text,opt){
		if(butId=='ok'){
			Ext.DomHelper.insertHtml('beforeEnd',Ext.getBody().dom,text);
		}
	},null,true);	
	*/
	/**
	 * 进度条的使用必须明确的获取到总值和当前值才可以准确的把握进度条的进度
	 */
	var p = Ext.create('Ext.ProgressBar', {
	   renderTo: Ext.getBody(),
	   width: 300,
	   animate:true
	});
	
	// Wait for 5 seconds, then update the status el (progress bar will auto-reset)
	p.wait({
	    interval: 500, //bar will move fast!
	    duration: 50000,
	    increment: 15,
	    text: 'Updating...',
	    scope: this,
	    fn: function(){
	        p.updateText('Done!');
	    }
	});
	// 强制停止进度条
	var btn = new Ext.Button({
		text:'强制停止进度条',
		handler:function(){
			p.reset();
			p.updateText("强制停止");
		},
		renderTo:Ext.getBody()
	});
	
	// 显示实时精度的进度条
	
	var pbar = new Ext.ProgressBar({
		id:'pbar',
		width:300,
		renderTo:Ext.getBody()
	});
	
	var i = 0;
	var task = {
		run:function(){
			i+=0.1;
			pbar.updateProgress(i,'加载进度：'+Math.round(i*100)+'%',true);
			if(Math.round(i*100)==100){
				pbar.updateText("加载完成");
				Ext.TaskManager.stop(task);
			}
		},
		interval:500
	};
	Ext.TaskManager.start(task);
	
	})
	
})();