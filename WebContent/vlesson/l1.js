/**
 * Ext数据模型
 */
(function(){
	Ext.onReady(function(){
		
	Ext.data.validations.lengthMessage = '错误的长度' ;	   // 提示的信息进行汉化
	
	//  自定义验证类   ---由于需要将此代码提前     第四步   
	Ext.apply(Ext.data.validations,{
		age: function(config, value) {
	        if (value === undefined || value === null) {
	            return false;
	        }
	        min = config.min,
	        max = config.max;
	       
	        if(value>max || value < min){
	        	return false ;
	        }else{
	        	return true ;
	        }
    	},
    	ageMessage:'错误的年龄'
	});
	
	
	//------------创建数据模型并且进行实例化-----------------------	
		//通过Ext.define 进行创建数据模型类
		Ext.define('Person',{    //  第一步
			extend:'Ext.data.Model',
			fields:[
				{name:'name',type:'auto'},
				{name:'age',type:'int'},
				{name:'email',type:'auto'}
			]
		});
		
		// 通过new方法进行实例化类
		var person = new Person({
			name:'zhangsan',
			age:11,
			email:'1111@qq.com'
		});
		
		// 通过Ext.create方法进行实例化  ---推荐使用 
		var p = Ext.create("Person",{
			name:'李四',
			age:12,
			email:'1111@qq.com'
		})
		//测试
//		alert(person.get('name'));
		Ext.Msg.alert('提示',p.get('name'));
		
	//------------------验证----------------------------
		// 定义带有验证信息的数据模型
	Ext.define('User', {       // 第二步
	    extend: 'Ext.data.Model',
	    fields: [
	        {name: 'name',     type: 'string'},
	        {name: 'age',      type: 'int'},
	        {name: 'phone',    type: 'string'},
	        {name: 'gender',   type: 'string'},
	        {name: 'username', type: 'string'},
	        {name: 'alive',    type: 'boolean', defaultValue: true}
	    ],
	
	    validations: [
		        {type: 'age',  field: 'age' ,min:18,max:60},   // 验证是否存在
		        {type: 'length',    field: 'name',     min: 2,max:10},    // 验证长度
		        {type: 'inclusion', field: 'gender',   list: ['Male', 'Female']},   // 包含在指定集合中
		        {type: 'exclusion', field: 'username', list: ['Admin', 'Operator']}, // 不包含在指定集合中
		        {type: 'format',    field: 'username', matcher: /([a-z]+)[0-9]{2,3}/}   // 正则表达式
		    ]
		});	
		
		// 实例化
		var instance = Ext.create('User', {
		    name: 'E',
		    age: 68,
		    gender: 'Male',
		    username: 'edspencer'
		});
		//  进行验证，并且收集错误信息      第三步
		var errors = instance.validate();
		var errArray = [] ;
		errors.each(function(item){
			errArray.push(item.field+" "+item.message);
		});
		alert(errArray.join("\n"));
	})
	
})();