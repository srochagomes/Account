#!/bin/bash

if [ -z $1 ] || [ -z $2 ]; then
    echo "Erro: É necessário informar o usuario e senha do dockerhub como parâmetros."
    exit 1
fi
# Defina as variáveis de ambiente para o seu projeto
DOCKER_USERNAME=$1
DOCKER_PASSWORD=$2
DOCKER_HUB_USERNAME="srochg"
DOCKER_HUB_REPO=$(mvn help:evaluate -Dexpression=project.name -q -DforceStdout)
DOCKER_HUB_TAG=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

# Construa a imagem Docker
sudo docker build -t "$DOCKER_HUB_USERNAME/$DOCKER_HUB_REPO:$DOCKER_HUB_TAG" .

# Faça o login no Docker Hub
echo "${DOCKER_PASSWORD}" | sudo docker login -u="${DOCKER_USERNAME}" --password-stdin

# Faça o push da imagem para o Docker Hub
sudo docker push "$DOCKER_HUB_USERNAME/$DOCKER_HUB_REPO:$DOCKER_HUB_TAG"

# Limpeza: Remova a imagem localmente, se desejar
sudo docker image rm "$DOCKER_HUB_USERNAME/$DOCKER_HUB_REPO:$DOCKER_HUB_TAG"