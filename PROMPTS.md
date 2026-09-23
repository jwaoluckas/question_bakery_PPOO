# Documentação do uso de IA — Questão 3 (Decorator)

Conforme o enunciado, a IA foi usada para pedir **um passo-a-passo**, não a
solução pronta. Abaixo estão os prompts utilizados, o que veio de resposta e os
ajustes feitos por cima.

---

## Prompt 1 — estudo dos conteúdos da disciplina

Prompt usado uma vez, no início da lista, para revisar os padrões antes de
começar a resolver:

> Use a extensão Claude in Chrome para fazer o seguinte:
> - Entre no Classroom e veja todos os conteúdos relacionados a disciplina
>   PPOO, assim você poderá explicar melhor cada padrão que o professor ensinou
>   até agora.
> - Ainda no Classroom, veja os conteúdos da turma arquivada de Programação
>   Orientada a Objetos (POO), assim você poderá me relembrar todos os conceitos
>   de OO. Não nego, estou enferrujado!
> - Não faça nenhuma alteração, envio de mensagem ou algo que possa me
>   'prejudicar' nas turmas, apenas visualize os conteúdos e me ensine.

---

## Prompt 2 — entender o código inicial

> Me explique a questão da padaria:
> - Me mostre o código já existente e explique ele parte por parte (Explique
>   através de comentários o que cada linha / comando faz).
> - Me diga qual o padrão já aplicado (se já tiver um) e explique esse padrão.
> - Se for para aplicar outro padrão, informe qual e explique-o também.

**O que veio:** a explicação de `Cake`, `VanillaCake`, `ChocolateCake`, `Order`
e `Main`, e a observação de que o código inicial não usa padrão nenhum — só tem
herança simples, e o custo fixo de $10 mora na classe abstrata. O padrão a
aplicar era o Decorator.

---

## Prompt 3 — o roteiro da solução

> Estou implementando o padrão Decorator num exercício de padaria em Java.
> Tenho uma classe abstrata Cake com getCost() (retorna um custo fixo) e
> getDescription() (abstrato), e duas subclasses simples, VanillaCake e
> ChocolateCake. Também tenho um Order que guarda uma lista de Cake e imprime
> custo + descrição de cada um. Me ajude com o seguinte:
> - Preciso conseguir "empilhar" decorações em cima de um bolo: multicamadas
>   (soma $5, coloca "Multi-layered" antes do nome), granulado (soma $2, coloca
>   "with sprinkles" depois do nome) e um "dizer" customizado (não muda o custo,
>   coloca with saying "X" depois do nome, podendo ter mais de um dizer no mesmo
>   bolo).
> - Preciso adicionar um bolo novo, de morango, que custa o dobro do padrão, sem
>   alterar nada que já existe.
> - Antes de me dar o código pronto, explica em passos como estruturar isso,
>   onde entra a classe abstrata dos decoradores, como cada decorador delega pro
>   bolo que está embrulhando, e como isso deixa o Order funcionando sem mudar
>   nada nele, pra eu ir implementando e entendendo cada parte.

**Etapas sugeridas:**

1. Criar uma classe abstrata de decorador que **estenda** `Cake` e **guarde** um
   `Cake` dentro (é o que permite empilhar).
2. Fazer essa classe delegar custo e descrição para o bolo embrulhado.
3. Criar um decorador concreto por decoração, sobrescrevendo só o que muda.
4. Criar o bolo de morango como uma subclasse comum de `Cake`.
5. Montar no `Main` os pedidos que o enunciado pede.

---

## Ajustes feitos sobre o que a IA respondeu

### Ajuste 1 — o decorador base delega tudo por padrão

A primeira versão deixava `getCost()` e `getDescription()` abstratos no
`CakeDecorator`, obrigando cada decorador concreto a implementar os dois.

**Por que mudou:** o `SayingDecorator` não mexe no custo, e o
`MultiLayerDecorator` e o `SprinklesDecorator` mexem no custo e na descrição de
formas diferentes. Com os dois métodos abstratos, todo decorador era obrigado a
reescrever `return wrappedCake.getCost()` só para não alterar nada.

No `CakeDecorator` deste repositório os dois métodos já delegam para o bolo
embrulhado, então **cada decorador concreto sobrescreve só o que realmente
muda** — o `SayingDecorator`, por exemplo, só tem `getDescription()`. Menos
código repetido e menos chance de um decorador novo esquecer de repassar algo.

### Ajuste 2 — o custo do morango sai do bolo padrão, não de um número novo

A sugestão era o `StrawberryCake` retornar `20` direto.

**Por que mudou:** o enunciado diz "o dobro de um bolo padrão", não "vinte". O
código usa `super.getCost() * 2`, então se a padaria reajustar o preço base na
classe `Cake`, o morango acompanha sozinho. É a regra do enunciado escrita como
regra, e não o resultado dela.

### Ajuste 3 — o "dizer" aceita ser empilhado mais de uma vez

A versão inicial guardava o dizer como um campo do bolo, o que só permitia um.
O `SayingDecorator` recebe o texto pelo construtor e é um decorador como os
outros, então empilhar dois dizeres funciona e sai na ordem certa — que é
exatamente o último item do enunciado.

### Ajuste 4 — o `Main` monta os quatro pedidos do enunciado

O `Main` é a prova do padrão: os quatro itens pedidos, montados por
empilhamento, com a saída batendo com o exemplo — inclusive o bolo de morango
multicamadas com granulado duplo e dois dizeres, que fecha em 29.

---

## Mapa: etapa → commit

| Etapa / ajuste                                | Commit                                            |
|-----------------------------------------------|---------------------------------------------------|
| Código inicial do professor                    | `Adiciona código inicial do professor:`           |
| Etapas 1 e 2 (**Ajuste 1**)                    | `Adiciona CakeDecorator, base do padrão Decorator:` |
| Etapa 3 — granulado                            | `Adiciona SprinklesDecorator:`                    |
| Etapa 3 — multicamadas                         | `Adiciona MultiLayerDecorator:`                   |
| Etapa 3 (**Ajuste 3**) — dizer                 | `Adiciona SayingDecorator:`                       |
| Etapa 4 (**Ajuste 2**) — bolo de morango       | `Adiciona StrawberryCake:`                        |
| Etapa 5 (**Ajuste 4**) — pedidos do enunciado  | `Main monta o pedido do enunciado:`               |
| Documentação da solução                        | `Adiciona README:`                                |
