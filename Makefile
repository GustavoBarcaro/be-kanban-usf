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
	@./mvnw spring-boot:run

.PHONY: create-database
create-database: ## Create postgres container
	@docker run -e POSTGRES_USER=user -e POSTGRES_PASSWORD=pass123 -e POSTGRES_DB=database --name demo -p 5432:5432 -d postgres
