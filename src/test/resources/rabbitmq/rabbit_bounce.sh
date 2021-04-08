pushd ~/Projects/vlingo/xoom-lattice-exchange-rabbitmq
docker-compose down
docker volume rm $(docker volume ls -q)
docker-compose up -d
popd
