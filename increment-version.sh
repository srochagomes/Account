#!/bin/bash

# Obtém a versão atual do pom.xml
current_version=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

# Define as partes da versão (major, minor, patch)
IFS='.' read -ra version_parts <<< "$current_version"
major=${version_parts[0]}
minor=${version_parts[1]}
patch=${version_parts[2]}

# Função para incrementar o número correto
increment_version() {
    if [[ "$1" == "major" ]]; then
        ((major++))
        minor=0
        patch=0
    elif [[ "$1" == "minor" ]]; then
        ((minor++))
        patch=0
    elif [[ "$1" == "patch" ]]; then
        ((patch++))
    fi
}

# Verifica o argumento passado para determinar o incremento
if [[ "$1" == "major" || "$1" == "minor" || "$1" == "patch" ]]; then
    increment_version "$1"

    # Cria a nova versão com o número incrementado
    new_version="$major.$minor.$patch"

    # Atualiza a versão no pom.xml
    mvn versions:set -DnewVersion=$new_version

    echo "Versão atual: $current_version"
    echo "Nova versão: $new_version"
else
    echo "Erro: Parâmetro inválido. Use 'major', 'minor' ou 'patch'."
fi