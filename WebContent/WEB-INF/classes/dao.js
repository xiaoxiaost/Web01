var ioc = {
    dao : {
        type : "org.nutz.dao.impl.NutDao",
        args : [{refer:"dataSource"}]
    },
    dataSource : {
        type : "org.nutz.dao.impl.SimpleDataSource",
        fields : {
            jdbcUrl : 'jdbc:mysql://localhost:3306/test?characterEncoding=utf-8',
            username : 'root',
            password : 'tiger'
        }
    }
}