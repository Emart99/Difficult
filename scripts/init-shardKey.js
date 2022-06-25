use difficult;
sh.enableSharding("difficult"); 
db.createCollection("Producto");
db.Producto.createIndex({ _id: "hashed" })
sh.shardCollection("difficult.Producto", { _id: "hashed" });
db.createCollection("ClickLog");
db.ClickLog.createIndex({ _id: "hashed" })
sh.shardCollection("difficult.ClickLog", { _id: "hashed" });