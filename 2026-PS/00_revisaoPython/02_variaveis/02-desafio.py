
# ===============================================
# Controle de estoque de produtos
# ===============================================
# Disciplina : Programação de Sistema (PS)
# Aula       : 04 - Revisão: Variaveis, Tipos e controle de Fluxo
# Autor      : [Kauê Mendes]
# Data       : [01/03/2026]
# Repisitório: https://github.com/kauwfx/2026-PS
# ===============================================
#
# DESCRIÇÃO: 
# sistema de controle de estoque 
# ===============================================

print("==CONTROLE DE ESTOQUE DE PRODUTOS==")
print()
# Entrada de dados
estoque = [
    {"nome": "Mouse", "quantidade": 3},
    {"nome": "Teclado", "quantidade": 12},
    {"nome": "Monitor", "quantidade": 25},
]
# classificar estoque
def classificar(quantidade):
    if quantidade < 5:
        return "CRÍTICO"
    elif quantidade < 20:
        return "ADEQUADO"
    else:
        return "EXCESSO"
# RELATORIO
print("== Relatorio de estoque ==")
print()

critico = 0
adequado = 0
excesso = 0

for produto in estoque:
    nome = produto["nome"]
    quantidade = produto["quantidade"]
    situacao = classificar(quantidade)
    
    if situacao == "CRÍTICO":
        critico += 1
    elif situacao == "ADEQUADO":
        adequado += 1
    else:
        excesso += 1

    print(f"Produto   : {nome}")
    print(f"Quantidade: {quantidade}")
    print(f"Situação  : {situacao}")
    print("-" * 30)
    print("\n--- RESUMO ---")
    print(f"Críticos : {critico}")
    print(f"Adequados: {adequado}")
    print(f"Excesso  : {excesso}")
 # consultar produto pelo nome 
while True:
    resposta = input("\nDeseja consultar um produto? (sim/não): ").lower()

    if resposta == "n":
        break

    nome_busca = input("Digite o nome do produto: ")
    encontrado = False

    for produto in estoque:
        if produto["nome"].lower() == nome_busca.lower():
            situacao = classificar(produto["quantidade"])
            print(f"\nProduto encontrado!")
            print(f"Quantidade: {produto['quantidade']}")
            print(f"Situação  : {situacao}")
            encontrado = True
            break

    if not encontrado:
        print("Produto não encontrado no estoque.")
# adicionar novos produtos 
while True:
    resposta = input("\nDeseja adicionar um novo produto? (s/n): ").lower()

    if resposta == "n":
        break

    nome = input("Nome do produto: ")

    while True:
        try:
            quantidade = int(input("Quantidade em estoque: "))

            if quantidade < 0:
                print("Erro: quantidade não pode ser negativa.")
            else:
                break

        except ValueError:
            print("Erro: digite um número válido.")

    estoque.append({"nome": nome, "quantidade": quantidade})
    print("Produto adicionado com sucesso!")
# produto critico
menor = estoque[0]

for produto in estoque:
    if produto["quantidade"] < menor["quantidade"]:
        menor = produto

print("\n--- PRODUTO COM MENOR ESTOQUE ---")
print(f"Produto   : {menor['nome']}")
print(f"Quantidade: {menor['quantidade']}")
print("Situação  :", classificar(menor["quantidade"]))

print("\nPrograma encerrado.")