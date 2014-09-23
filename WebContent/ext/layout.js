(function(){
	Ext.onReady(function(){
		var viewprot = new Ext.Viewport({
			layout:'border',
			items:[{
				region:'north',
				height:30,
				html:'<h1>顶栏</h1>'
			},{
				region:'west',
				title:'菜单',
				width:200,
				collapsible:true,
				layout:'accordion',
				layoutConfig:{
					titleCollapse:true,
					animate:true
				},
				items:[{
					title:'第一栏',
					html:'第一栏'
				},{
					title:'第二栏',
					html:'第二栏'
				},{
					title:'第三栏',
					html:'第三栏'
				}]
			},{
				region:'east',
				width:100,
				html:'快捷'
			},{
				region:'south',
				height:50,
				html:'north'
			},{
				region:'center',
				layout:'border',
				items:[{
					region:'west',
					width:300,
					id:'cardLayout',
					title:'cardLayout向导',
					layout:'card',
					activeItem:0,
					bodyStyle:'padding:15px',
					defaults:{
						border:false
					},
					bbar:[{
						id:'move-prev',
						text:'上一步',
						handler:function(btn) {
			                navigate(btn.up("panel"), "prev");
			            },
						disabled:true
					},'->',{
						id:'move-next',
						text:'下一步',
						handler:function(btn) {
			                navigate(btn.up("panel"), "next");
			            }
					}],
					items:[{
						id:'card-0',
						loader:{
					        url: 'test.jsp',
					        autoLoad: true
					    }
					},{
						id:'card-1',
						html:'第二步'
					},{
						id:'card-2',
						html:'第三步'
					}]
				},{
					region:'center',
					border:true,
					split:true
				}]
			}]
			
		});
		var navigate = function(panel, direction){
		    var layout = panel.getLayout();
		    layout[direction]();
		    Ext.getCmp('move-prev').setDisabled(!layout.getPrev());
		    Ext.getCmp('move-next').setDisabled(!layout.getNext());
		};
	});
	
})();