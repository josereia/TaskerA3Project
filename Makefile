.PHONY: deps run build docker

default: run

deps:
	@echo "Instalando dependencias"
	@mvn install:install-file -Dfile=./deps/rs2xml.jar -DgroupId=net.proteanit -DartifactId=rs2xml -Dversion=1.0 -Dpackaging=jar

run:
	@echo "Executando projeto"
	@java -jar ./target/tasker-1.0.0.jar

build:
	@echo "Contruindo projeto"
	@mvn clean install
	@mv ./target/tasker-1.0.0-jar-with-dependencies.jar ./target/tasker-1.0.0.jar

docker:
	@echo "Criando containers"
	@docker compose up -d
