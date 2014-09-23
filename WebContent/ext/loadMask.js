(function(){
	Ext.onReady(function(){
		var nodes = {
			text:'河北省',
			expanded:true,
			leaf:false,
			children:[
				{
					id:'boss',
					text:'石家庄',
					leaf:false,
					expanded:true,
					children:[{
						text:'一区',
						leaf:true
					},{
						text:'二区',
						leaf:true
					}]
				},
				{
					id:'hd',
					text:'邯郸市',
					leaf:false,
					expanded:true,
					children:[{
						text:'丛台区',
						leaf:true
					},{
						text:'赵园',
						leaf:true
					}]
				}
			]
		};
		
		var leftTree = new Ext.tree.Panel({
			id:'leftTree',
			root:nodes,
			region:'west',
			animate:true,
			autoScroll:true,
			rootVisible:true,
			width:150
		});
		
		var panel = new Ext.Panel({
			title:'右侧面板',
			region:'center',
			width:200
		});
		var mask = null ;
		var win = new Ext.Window({
			title:'LoadMask',
			width:400,
			height:300,
			layout:'border',
			items:[leftTree,panel],
			buttons:[{
				text:'刷新',
				handler:function(){
					if(!mask){
						mask = new Ext.LoadMask(leftTree,{msg:'正在加载。。。'});
					}
					mask.show();
				}
			},{
				text:'停止',
				handler:function(){
					mask.hide() ;
				}
			}]
		})
		win.show();
	});
})();