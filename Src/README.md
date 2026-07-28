SysControl 

##  Variante
Classe `Produto` para cadastro de produtos em estoque


- Todos os atributos são `private`
-Construtor com todos os parâmetros
 Validações:
  Nome não pode ser nulo nem vazio
   Preço não pode ser negativo
   Quantidade não pode ser negativa
- Métodos de comportamento:
 `adicionarEstoque()`
  -`removerEstoque()` 
     `calcularValorTotalEstoque()`
- Personalização:
  - Primeiras letras do nome: `Te` (Teclado)


##  Desafios escolhido
1. Criar método que retorna resumo textual do objeto (`exibirResumo()`)
2. Impedir operação inválida retornando `false` (`removerEstoque()`)

##  Resultado dos testes
1. Objetos criados com sucesso ✅
2. Nome vazio recusado ✅
3. Valores negativos recusados ✅
4. Operações válidas alteram o estado ✅
5. Operações impossíveis mantêm o estado ✅

