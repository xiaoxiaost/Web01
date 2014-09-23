(function(){
	Ext.onReady(function(){
		Ext.Msg.alert("helloworld");
		var array = [1,2,3,0,-1,-2];
		Ext.Array.every(array,function(item){
		
		},this)
	})
})();


//////////////////////

var columns = [
	{header:'编号',dataIndex:'id'},
	{header:'名称',dataIndex:'name'},
	{header:'描述',dataIndex:'descn'}
];

var data = [
	['1','name1','desc1'],
	['2','name2','desc2'],
	['3','name3','desc3']
];

var store = Ext.create('Ext.data.ArrayStore',{
	storeId :'myfirstStore',
	data : data,
	fields:[
		{name:'id',mapping:1},
		{name:'name',mapping:0},
		{name:'descn',mapping:2}
	],
	 proxy: {
        type: 'memory',
        reader: {
            type: 'json'
        }
    }
});

Ext.create('Ext.grid.Panel', {
    title: 'Simpsons',
    store: Ext.data.StoreManager.lookup('myfirstStore'),
    columns:columns,
    height: 200,
    width: 400,
    renderTo: Ext.getBody()
});