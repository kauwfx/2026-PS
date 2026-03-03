# =========================================================
# SISTEMA DE BIBLIOTECA
# =========================================================
# Disciplina : Programação de Sistemas (PS)
# Aula       : 05 – Revisão: Estruturas de Dados
# Autor      : [Kaue Mendes]
# Data       : [26/02/2026]
# Repositório: https://github.com/kauwfx/2026-PS
# =========================================================
#
# DESCRIÇÃO:
# Catálogo de livros que demonstra o uso de listas
# e dicionários para armazenar, consultar e filtrar
# dados estruturados.
# =========================================================



titulos = [
    "O Programador Pragmático",
    "Código Limpo",
    "Entendendo Algoritmos",
]


print("Primeiro livro:", titulos[0])
print("Último livro :", titulos[-1])  
print("Total de livros:", len(titulos))


print("\n--- Operações na lista ---")


titulos.append("Python Fluente")
print("Após append:", titulos)


busca = "Código Limpo"
if busca in titulos:
    print(f"'{busca}' está no catálogo.")
else:
    print(f"'{busca}' não encontrado.")


titulos.sort()
print("Lista ordenada:", titulos)


titulos.remove("Entendendo Algoritmos")
print("Após remove:", titulos)


livro = {
    "titulo":      "O Programador Pragmático",
    "autor":       "Andrew Hunt",
    "ano":         1999,                   # int, não string
    "disponivel":  True,                   # bool
}


print("Título :", livro["titulo"])
print("Autor  :", livro["autor"])
print("Ano    :", livro["ano"])
print("Status :", "Disponível" if livro["disponivel"] else "Emprestado")



livro["disponivel"] = False  # livro foi emprestado
print("\nApós empréstimo:", livro["disponivel"])

livro["paginas"] = 352
print("Páginas:", livro["paginas"])


editora = livro.get("editora", "Não informada")
print("Editora:", editora) 


catalogo = [
    {"titulo": "O Programador Pragmático", "autor": "Andrew Hunt", "ano": 1999, "disponivel": True},
    {"titulo": "Código Limpo", "autor": "Robert C. Martin", "ano": 2008, "disponivel": False},
    {"titulo": "Entendendo Algoritmos", "autor": "Aditya Bhargava", "ano": 2016, "disponivel": True},
]

print("=== Catálogo da Biblioteca ===")
print()


for livro in catalogo:
    status = "✅ Disponível" if livro["disponivel"] else "📕 Emprestado"
    print(f' {livro["titulo"]} ({livro["ano"]})')
    print(f' Autor: {livro["autor"]} | {status}')
    print(" " + "-" * 40)
   

print("\n=== Livros disponíveis ===")
for livro in catalogo:
    if livro["disponivel"]:                     
        print(f' ✅ {livro["titulo"]}')


print("\n=== Busca por título ===")
busca = input("Digite o título (ou parte): ").lower()
encontrado = False

for livro in catalogo:
    if busca in livro["titulo"].lower():       
        print(f' Encontrado: {livro["titulo"]} - {livro["autor"]}')
        encontrado = True

if not encontrado:
    print(" Nenhum livro encontrado com esse termo.")


print("\n=== Atributos do primeiro livro ===")
for chave, valor in catalogo[0].items():      
    print(f" {chave}: {valor}")
  
catalogo = [
    {"titulo": "Código Limpo",          "autor": "Robert C. Martin", "disponivel": True},
    {"titulo": "Entendendo Algoritmos", "autor": "Aditya Bhargava",  "disponivel": False},
    {"titulo": "Python Fluente",        "autor": "Luciano Ramalho",   "disponivel": True},
]

print("Primeiro livro:", catalogo[3]["titulo"])

print("\nLivros disponíveis:")
for livro in catalogo:
    if livro["disponivel"] == False:
        print(f' ✅ {livro["titulo"]}')

total = len(catalogo)
print(f"\nTotal de livros: {total}")

for chave, valor in catalogo[0]:
    print(f" {chave}: {valor}")

primeiro_autor = catalogo[0]["autor"]
print("\nAutor do primeiro livro:", primeiro_autor)