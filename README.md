# Bakery — Padrão Decorator

Exercício de Padrões de Projeto (IFPE): aplicar o padrão **Decorator** ao código inicial
de uma padaria fornecido pelo professor.

## O problema

O código inicial tem uma classe abstrata `Cake` (custo padrão 10), dois bolos concretos
(`VanillaCake` e `ChocolateCake`) e um `Order` que guarda os bolos e imprime custo e
descrição de cada um.

A padaria quer oferecer decorações que podem ser combinadas livremente no mesmo bolo:

- **camadas múltiplas** (+5, descrição ganha o prefixo `Multi-layered `);
- **granulado** (+2, sufixo ` with sprinkles`), podendo ser aplicado mais de uma vez;
- **dizeres personalizados** (sem custo, sufixo ` with saying "..."`), podendo haver vários;

além de um novo bolo de **morango**, que custa o dobro do bolo padrão.

Criar uma subclasse para cada combinação (`MultiLayerVanillaCakeWithSprinkles`, ...)
causaria uma explosão de classes. Com o Decorator, cada decoração é uma classe que
*envolve* outro `Cake` e acrescenta seu comportamento, e as combinações são montadas em
tempo de execução — sem alterar `Cake` nem `Order`.

## Papel de cada classe no Decorator

| Classe | Papel | O que faz |
|---|---|---|
| `Cake` | Componente | Abstração comum: `getCost()` (10 por padrão) e `getDescription()`. |
| `VanillaCake`, `ChocolateCake` | Componentes concretos | Bolos base do código do professor. |
| `StrawberryCake` | Componente concreto | Bolo novo, custa `super.getCost() * 2` (20). |
| `CakeDecorator` | Decorator abstrato | É um `Cake` e guarda o `Cake` envolvido; por padrão só delega custo e descrição. |
| `MultiLayerDecorator` | Decorator concreto | +5 no custo, prefixa `Multi-layered `. |
| `SprinklesDecorator` | Decorator concreto | +2 no custo, acrescenta ` with sprinkles`. |
| `SayingDecorator` | Decorator concreto | Não muda o custo, acrescenta ` with saying "<texto>"`. |
| `Order` | Cliente | Trata tudo como `Cake`, sem saber se está decorado. |
| `Main` | Cliente | Monta o pedido do enunciado e imprime. |

Como o decorator também é um `Cake`, decorações podem ser empilhadas em qualquer ordem e
quantidade. O 4º bolo do pedido, por exemplo, é:

```java
new MultiLayerDecorator(
        new SayingDecorator(
                new SayingDecorator(
                        new SprinklesDecorator(new SprinklesDecorator(new StrawberryCake())),
                        "One of"),
                "EVERYTHING"));
```

Custo: 20 (morango) + 2 + 2 (granulado) + 0 + 0 (dizeres) + 5 (camadas) = **29**.

## Como rodar

Requer JDK 8 ou superior. Na pasta do projeto:

```sh
javac -d out *.java
java -cp out Main
```

## Saída esperada

```
   10  Chocolate cake
   10  Vanilla cake with saying "PLAIN!"
   12  Vanilla cake with sprinkles with saying "FANCY!"
   29  Multi-layered Strawberry cake with sprinkles with sprinkles with saying "One of" with saying "EVERYTHING"
```
