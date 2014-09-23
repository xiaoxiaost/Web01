(function(){
	
	Ext.onReady(function(){
		//  数据
		var userData = {
			total:200,
			number:'jsd',
			user:{
				id:'1',
				name:'zhangsan',
				email:'122222@qq.com',
				orders:[
					{
						id:'001',name:'pen',user_id:'1'
					},{
						id:'002',name:'book',user_id:'1'
					}
				]
			}
		};
		//创建Model
		// 创建User模型
		Ext.define('User', {
		    extend: 'Ext.data.Model',
		    fields: ['id', 'name', 'email'],
		
		    hasMany: {model: 'Order', name: 'orders'}
		});

		Ext.define('Order', {
		    extend: 'Ext.data.Model',
		    fields: ['id', 'user_id', 'status', 'price'],
		
		    belongsTo: 'User'
		});
		
		var store = Ext.create('Ext.data.Store', {
	    	autoLoad: true,
		    model: 'User',
		    data : userData,
		    proxy: {
		        type: 'memory',
		        reader: {
		            type: 'json',
		            root:'user'
		        }
		    }
		}); 

		console.info(store.data.items[0].data.name) ;
	})
})();