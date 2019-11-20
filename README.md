# Relatorio Leitura Correios 
![unit](https://user-images.githubusercontent.com/53620227/69211711-b07b4280-0b3d-11ea-8a01-454142e22327.png)
## Integrantes da Equipe:
* #### [Aldo Wanderley Costa Junior](https://github.com/Aldo-1)
* #### [Matheus Nunes de Almeida](https://github.com/matheus-nunes-Unit)
* #### [Antonio Rodrigo Doria Gonçalves Lima](https://github.com/Rodrigo-TI)
* #### [Erik Danilo Costa de Almeida](https://github.com/ErikDCAlmeida)
* #### [Bruno Matheus da Silva](https://github.com/brunomatheuss)
## Materia:
- #### Estrutura de Dados
## Estrutura de Dados utilizadas:
- #### HashMap
- #### CurrentHashMap
- #### Array
## Classes feitas:
- #### Bairro
- #### BancoDeDados
- #### Cidade 
- #### Estado
- #### LeitorDeBairros
- #### LeitorDeCidades
- #### LeitorDeLogradouros
- #### Logradouro
- #### RunnableLeitorDeLogradouros
- #### Correios(Main)
## Funcionalidade e Metodos:
* #### 1. Logo ao iniciar o arquivo  irá abrir um FileChooser, para escolher a pasta onde fica os arquivos dos correios para ler.
* #### 2. Irá aparecer todos os metodos:
  * ##### 2.1 Atualizar arquivo - Irá ler novamente o novo arquivo , atualizando a leitura antiga.
  * ##### 2.2 Visualizar estados - Irá retornar todos os estados.
  * ##### 2.3 Visualizar cidades de um estado - Pedirá no inicio para passar o estado que deseja retornar todas as cidades, após isso irá retornar.
  * ##### 2.4 Visualizar bairros de um estado - Terá que passar no inicio o estado  onde se localiza o bairro , após isso irá pedir para passar a cidade onde deseja retornar todos os bairros, por fim o programa retornará todos os bairros.
  * ##### 2.5 Visualizar logradouros de um bairro - Terá que fazer todo procedimento da parte 2.4 , porém no final em vez de retornar os bairros, ele irá pedir o bairro que voce deseja ver todos os lougradoros, passando ele o programa retornará todos os logradouros desse bairro.
  * ##### 2.6 Buscar logradouro por CEP - O programa exigirá o cep para retornar o seu respectivo logradouro.
  * ##### 2.7 Buscar logradouro por Nome - O programa irá pedir para passar o nome do logradouro desejado, passando o nome ele irá retornará todos com esse determinado nome.
  * ##### 2.8 Menu de testes - Acessará um novo menu , que nesse menu é uma area de testes para receber o tempo de cada execução, em nanosecundos.
    * ##### 2.8.1 Teste de leitura de arquivos - Retornará o tempo da leitura do arquivo.
    * ##### 2.8.2 Teste de busca por todos os estados - Retornará o tempo da busca de todos os estados.
    * ##### 2.8.3 Teste de busca por todas as cidades de um estado - Retornará o tempo da busca de todas as cidades a partir de um estado. 
    * ##### 2.8.4 Teste de busca por todos os bairros de uma cidade - Retornará o tempo da busca de todas os bairros a partir de uma cidade.
    * ##### 2.8.5 Teste de busca por todos os logradouros de um bairro - Retornará o tempo da busca de todos os logradouros a partir de um bairro.
    * ##### 2.8.6 Teste de busca por logradouro por CEP - Retornará o tempo da busca pesquisando o logradouro por um cep.
    * ##### 2.8.7 Teste de busca por logradouro por nome - Retornará o tempo da busca pesquisando o logradouro pelo seu nome.
    * ##### 2.8.0 Voltar ao menu anterior - Voltar ao menu anterior.
  * ##### 2.0 Finalizar programa - Acabará o programa. 
## Testes:
* #### 1. Teste de leitura de arquivos , executado 3 vezes :
![tempoDeLeitura](https://www.imagemhost.com.br/images/2019/11/20/temposLeituras.png)

#### Figura 1.1 - Grafico do tempo de leitura
#### Media da figura 1.1:
![MediaDoTempoDeLeitura](https://www.imagemhost.com.br/images/2019/11/20/tempoDeLeitura.png)

* #### 2. Teste de todos os estados, executado 7 vezes:
![tempoBuscaDosEstados](https://www.imagemhost.com.br/images/2019/11/20/tempoBuscaDosEstados.png)

#### Figura 2.1 - Grafico do tempo do teste de busca por todos os estados
#### Media figura 2.1:
![MediaDoTempoBuscaDosEstados](https://www.imagemhost.com.br/images/2019/11/20/tempoBuscaDosEstados6c7c15b982245a22.png)

