# TP5 - Desafio Sincronização de Processos

## Integrantes

-   Jarelio Filho - 399683
-   Luan Carvalho - 400583
-   Tibet Teixeira - 400097
-   Victor Dantas - 384385

## Explicação do Problema

Ao analisar o programa, encontramos dois problemas: O primeiro problema é o de leitura dos consumidores, eles deveriam ler apenas os números conforme o filtro par ou ímpar, contudo esses consumidores estão lendo independente do número gerado, além disso não está feita nenhuma verificação de sincronização entre os consumidores, como eles compartilham um mesmo objeto de consumo é necessário fazer essa sincronização.

O outro problema é o de armazenamento dos números pela dropbox. Primeiro que caso o produtor produza dois números antes de algum dos consumidores leia o primeiro número, este será substituido pelo segundo número e o dado será perdido; Segundo que caso o Produtor gerasse um número par e o consumidor de números impares tentasse ler esse número e a dropbox gerasse outro número, o consumidor de números pares também não conseguiria ler esse primeiro número par.

## Solução do Problema

O primeiro problema foi resolvido fazendo uma validação dentro da dropbox que contém um monitor para lockar o seu estado durante as leituras dos consumidores, ou seja, os dois consumidores irão de forma concorrida tentar ler um número de dentro da dropbox, durante esse processo será verificado se a leitura está sendo feita peito consumidor par ou pelo ímpar, se estiver sendo pelo consumidor par e o produtor já tiver gerado um número (number >= 0 [default: number = -1]) e esse número for par, o consumidor par irá lockar o estado da dropbox para que a o segundo consumidor não consiga fazer operações de leitura e após o consumidor par ler o número ele irá dar um release permitindo que as duas threads consumidoras possam concorrer novamente pela dropbox.

O segundo problema foi resolvido de forma que o produtor não produza mais de um número sem este ser consumido, logo ele inicializará uma variável number com -1 e ao produzir irá substituir essa variável pelo número gerado e também irá através da flag producerReleased parar de produzir números até que o último seja consumido, este ao ser consumido retorna a flag producerReleased para true, permitindo que o produtor produza mais números.
