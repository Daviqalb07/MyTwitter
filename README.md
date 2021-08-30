# MyTwitter
Projeto final para a disciplina de Programação Orientada a Objetos 2021.1 da Universidade Federal do Ceará.


## Pré-requisitos
* Visual Studio Code
    * Extensões: Extension Pack for Java e JavaFX Support
* JDK 11+
* [OpenJFX 11.0.2](https://gluonhq.com/products/javafx/)

## Configurando o ambiente de execução
1. Extraia os arquivos do JavaFX
2. Abra o projeto no VS Code
3. Vá em Java Projects > Referenced Libraries e adicione as classes de /path/to/javafx-sdk-11.0.2/lib
4. Vá em Run > Add configuration... e coloque a seguinte linha
```
"vmArgs": "--module-path path/to/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml"
```
no segundo parênteses de "configurations".

### Ambiente configurado!

> Obs: Substitua /path/to/ pelo caminho correspondente em sua máquina.