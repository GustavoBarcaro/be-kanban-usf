ifneq (,$(wildcard ./.env))
    include .env
    export
endif

.DEFAULT_GOAL := help

.PHONY: help
help:
	@echo "usage: make [target] ..."
	@echo ""
	@echo "targets:"
	@fgrep -h "##" $(MAKEFILE_LIST) | fgrep -v fgrep | sed -e 's/\\$$//' | sed -e 's/##//'

.PHONY: run
run: ## Run service
	@docker compose -f compose.yaml up --build --remove-orphans -d

.PHONY: setup
setup: ## Setup required environment
	@echo "creating .env file"
	@cp .env.example .env

.PHONY: connect
connect: ##Connect to docker postgres
	@docker exec -it be-kanban-postgres psql -U user_name database

.PHONY: create-database
create-database: ## Create postgres container
	@docker run -e POSTGRES_USER=user_name -e POSTGRES_PASSWORD=pass123 -e POSTGRES_DB=database --name demo -p 5432:5432 -d postgres
