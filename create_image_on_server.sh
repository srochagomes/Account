#!/bin/bash

# Defina as variáveis de ambiente para o seu projeto
DOCKER_HUB_USERNAME="srochg"
DOCKER_HUB_REPO="account"
DOCKER_HUB_TAG=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

# Construa a imagem Docker
sudo docker build -t "$DOCKER_HUB_USERNAME/$DOCKER_HUB_REPO:$DOCKER_HUB_TAG" .

# Faça o login no Docker Hub
sudo docker login

# Faça o push da imagem para o Docker Hub
sudo docker push "$DOCKER_HUB_USERNAME/$DOCKER_HUB_REPO:$DOCKER_HUB_TAG"

# Limpeza: Remova a imagem localmente, se desejar
# docker image rm "$DOCKER_HUB_USERNAME/$DOCKER_HUB_REPO:$DOCKER_HUB_TAG"