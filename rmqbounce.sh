# bounce the docker volume xoom-lattice-exchange-rabbitmq in the docker-compose.yml
docker-compose -p "dev" down
docker volume rm xoom-lattice-exchange-rabbitmq
docker-compose -p "dev" up -d
