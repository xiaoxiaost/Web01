// js中创建类的二种方法
//类其实就方法
function user(){	// 方法1：通过方法进行创建类
	// 创建属性
	this.name = 'tom' ;
	this.age = 25 ;
	this.email = '1193758587@qq.com' ;
	//创建方法
	this.getEmail = function(){
		return this.email ;
	}
}

var u  = new user() ;   // 实例化类
alert ('Name属性：'+u.name+"====方法的调用:"+u.getEmail()) ;

/////////////////////////////////////////////////////////////////////////

var person = {   // 方法2：以JSON数据的形式，这种方式不用实例化类
	name:'sun',
	age:24,
	getName:function(){
		return this.name ;
	}
};

alert("属性的调用："+person.name+"\n方法的调用:"+person.getName());
