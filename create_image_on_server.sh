#!/bin/bash




if [ -z $1 ] || [ -z $2 ]; then
    echo "Erro: É necessário informar o usuario e senha do dockerhub como parâmetros."
    exit 1
fi
# Defina as variáveis de ambiente para o seu projeto
mvn clean install
status_saida=$?
if [ $status_saida -ne 0 ]; then
    echo "Ocorreu erro no build."
    exit 1  # Encerra o script com código de erro 1
fi

DOCKER_USERNAME=$1
DOCKER_PASSWORD=$2
DOCKER_HUB_USERNAME="srochg"
DOCKER_HUB_REPO=$(mvn help:evaluate -Dexpression=project.name -q -DforceStdout)
DOCKER_HUB_TAG=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
name_image="$DOCKER_HUB_USERNAME/$DOCKER_HUB_REPO:$DOCKER_HUB_TAG"
NOME_ARQUIVO_JAR="$DOCKER_HUB_REPO-$DOCKER_HUB_TAG.jar"
# Construa a imagem Docker
sudo docker build -t $name_image --build-arg NOME_ARQUIVO_JAR=$NOME_ARQUIVO_JAR .

# Faça o login no Docker Hub
echo "${DOCKER_PASSWORD}" | sudo docker login -u="${DOCKER_USERNAME}" --password-stdin

# Faça o push da imagem para o Docker Hub
sudo docker push $name_image

# Limpeza: Remova a imagem localmente, se desejar
sudo docker image rm $name_image

echo "Imagem processada ${name_image}"