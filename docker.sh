# Rerunable: shut down all containers
docker compose stop demo-helloworld-app
docker compose stop demo-calculator-app
docker compose stop demo-api-app

docker rm demo-helloworld-app
docker rm demo-calculator-app
docker rm demo-api-app

cd demo-helloworld
mvn clean install
docker build -t demo-helloworld:0.0.1 -f Dockerfile .
cd ..

cd demo-calculator
mvn clean install
docker build -t demo-calculator:0.0.1 -f Dockerfile .
cd ..

cd demo-api
mvn clean install
docker build -t demo-api:0.0.1 -f Dockerfile .
cd ..

# docker run all containers
docker compose up -d

