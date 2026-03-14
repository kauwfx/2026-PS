catalogo = [
    {"titulo": "Turma da Mônica: Laços", "autor": "Vitor Cafaggi e Lu Cafaggi", "disponivel": True},
    {"titulo": "Turma da Mônica: Lições", "autor": "Vitor Cafaggi e Lu Cafaggi", "disponivel": True},
    {"titulo": "Astronauta: Magnetar", "autor": "Danilo Beyruth", "disponivel": False},
    {"titulo": "Jeremias: Pele", "autor": "Rafael Calça e Jefferson Costa", "disponivel": True},
    {"titulo": "Bidú: Caminhos", "autor": "Eduardo Damasceno e Luís Felipe Garrocho", "disponivel": True},
]

print("Primeiro livro:", catalogo[0]["titulo"])

print("\nLivros disponíveis:")
for livro in catalogo:
    if livro["disponivel"] == True:
        print(f' ✅ {livro["titulo"]}')

total = len(catalogo)
print(f"\nTotal de livros: {total}")

for chave, valor in catalogo[0].items():
    print(f" {chave}: {valor}")

primeiro_autor = catalogo[0]["autor"]
print("\nAutor do primeiro livro:", primeiro_autor)
