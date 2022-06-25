#!/bin/bash
sleep 45
docker-compose exec config01 sh -c "mongosh --port 27017 < /scripts/init-configserver.js"
docker-compose exec shard01-a sh -c "mongosh --port 27017 < /scripts/init-shard01.js"  
docker-compose exec shard02-a sh -c "mongosh --port 27017 < /scripts/init-shard02.js"

sleep 20
docker-compose exec shard01-a sh -c "mongosh --port 27017 < /scripts/init-shard01-arbiter.js"
docker-compose exec shard02-a sh -c "mongosh --port 27017 < /scripts/init-shard02-arbiter.js"
docker-compose exec router sh -c "mongosh < /scripts/init-router.js"

sleep 20
docker-compose exec router sh -c "mongosh --port 27017 < /scripts/init-shardKey.js"