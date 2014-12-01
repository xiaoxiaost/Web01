var ioc = {
	config:{
		type : "org.nutz.ioc.impl.PropertiesProxy",
        fields : {
            paths : ["DBMarker.properties"]
        }
	},
    dao : {
        type : "org.nutz.dao.impl.NutDao",
        args : [{refer:"dataSource"}]
    },
    nutFilePool : {
        type : "org.nutz.filepool.NutFilePool",
        args : ['~/tmp/files', 2000]
    },
    dataSource : {
        type : "org.nutz.dao.impl.SimpleDataSource",
        fields : {
            jdbcUrl : {java :"$config.get('db-url')"},
            username :{java :"$config.get('db-username')"},
            password :{java :"$config.get('db-password')"},
        }
    }
}