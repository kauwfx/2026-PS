Link do repositorio: https://github.com/kauwfx/2026-PS/tree/fa94247f921f5693a85d07df5ba2144290be40ec/aula32-projeto-secretaria
Conceito pretendido: B ( Dei meu maximo mais estou com pressentimento q esta faltando alguma coisa)

Por que este conceito:
- Criterio 1 (classe e encapsulamento): Aluno.java, linha 10 — os atributos sao private , o acesso e feito por getters e setters e a classe possui o metodo toString() para exibir
- Criterio 2 (colecao e menu): Main.java, linha 16 — o ArrayList armazena os objetos e o laço while roda o menu interativo com as opcoes de 0 a 7
- Criterio 3 (busca e CRUD): Main.java, linha 59 — o metodo buscarPorMatricula e reaproveitado nas operacoes de verificacao, exibicao, edicao e remocao
- Criterio 4 (relatorio, melhoria e identidade): Main.java, linha 20 e linha 161 — menu assinado ("por Kauê"), relatorio no padrao preparar-percorrer-usar e melhoria (a) de busca por nome na opcao [7]

Explicacao 1: Ao alterar a.setCurso(novoCurso) no objeto retornado pela busca, a lista muda junto porque a variavel 'a' guarda uma referencia ao objeto original que esta armazenado dentro do ArrayList, e nao uma copia dele.
Explicacao 2: A busca devolve null para que quem a chamou decida como tratar a informacao. Isso permite que o mesmo metodo seja usado no cadastro (onde null e o esperado para poder cadastrar) e na remocao ou edicao (onde null indica erro de aluno nao encontrado).

Usei IA em: Sim eu usei pq tem certas coisas que eu não estava entendo mais n coloquei ele pra fazer pra mim apenas tirei duvidas e a ia nao mexeu no meu codigo.