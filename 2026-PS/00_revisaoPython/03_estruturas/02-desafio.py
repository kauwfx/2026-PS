# ===============================================
# Bilioteca
# ===============================================
# Disciplina : Programação de Sistema (PS)
# Aula       : 05 - Estruturas de Dados
# Autor      : [Kauê Mendes]
# Data       : [12/03/2026]
# Repisitório: https://github.com/kauwfx/2026-PS
# ===============================================
catalogo = [
    {"titulo": "A arte da guerra", "autor": "Sun Tzu", "ano": 500, "disponivel": True},
    {"titulo": "1984", "autor": "George Orwell", "ano": 1949, "disponivel": True},
    {"titulo": "abosolute batman", "autor": "Scott Snyder", "ano": 2025, "disponivel": False}
]

def main():
    while True:
        print("\n" + "="*30)
        print("   SISTEMA DE BIBLIOTECA 2.0")
        print("="*30)
        print("1. Catálogo")
        print("2. Cadastrar Novo Livro")
        print("3. Nome do autor")
        print("4. Empréstimo / Devolução")
        print("5. Relatório e Sair")
        
        opcao = input("\nEscolha uma opção: ")

        # --- FUNCIONALIDADE: Listar Catálogo ---
        if opcao == "1":
            print("\n--- CATÁLOGO ATUAL ---")
            for livro in catalogo:
                status = "Disponível" if livro["disponivel"] else "Emprestado"
                print(f"Título: {livro['titulo']} | Autor: {livro['autor']} | Status: {status}")

        # --- FUNCIONALIDADE: Cadastrar Novo Livro ---
        elif opcao == "2":
            print("\n--- CADASTRO DE LIVRO ---")
            novo_titulo = input("Título: ")
            novo_autor = input("Autor: ")
            novo_ano = int(input("Ano: "))
            
            novo_livro = {
                "titulo": novo_titulo,
                "autor": novo_autor,
                "ano": novo_ano,
                "disponivel": True
            }
            catalogo.append(novo_livro)
            print(f"\n '{novo_titulo}' adicionado com sucesso!")

        # --- FUNCIONALIDADE: Busca por Autor (Case-Insensitive) ---
        elif opcao == "3":
            busca = input("\nDigite o nome do autor (ou parte dele): ").lower()
            encontrados = [l for l in catalogo if busca in l["autor"].lower()]
            
            if encontrados:
                print(f"\nLivros encontrados para '{busca}':")
                for l in encontrados:
                    print(f"- {l['titulo']} ({l['ano']})")
            else:
                print("\n Nenhum livro encontrado para este autor.")

        # --- FUNCIONALIDADE: Empréstimo / Devolução ---
        elif opcao == "4":
            print("\n--- ALTERAR STATUS DE DISPONIBILIDADE ---")
            busca_titulo = input("Digite o título exato do livro: ").strip()
            encontrado = False

            for livro in catalogo:
                if livro["titulo"].lower() == busca_titulo.lower():
                    # Inverte o booleano (True vira False e vice-versa)
                    livro["disponivel"] = not livro["disponivel"]
                    novo_status = "Disponível" if livro["disponivel"] else "Emprestado"
                    print(f"\n✅ Status alterado! O livro '{livro['titulo']}' agora está: {novo_status}")
                    encontrado = True
                    break
            
            if not encontrado:
                print("\nErro: Título não localizado no sistema.")

        # --- FUNCIONALIDADE: Relatório Final e Saída ---
        elif opcao == "5":
            total = len(catalogo)
            disponiveis = sum(1 for l in catalogo if l["disponivel"])
            emprestados = total - disponiveis
            titulos_emprestados = [l["titulo"] for l in catalogo if not l["disponivel"]]

            print("\n" + "="*30)
            print("       RELATÓRIO FINAL")
            print("="*30)
            print(f"Total de livros: {total}")
            print(f"Disponíveis: {disponiveis}")
            print(f"Emprestados: {emprestados}")
            
            if titulos_emprestados:
                print(f"Lista de Emprestados: {', '.join(titulos_emprestados)}")
            
            print("\nEncerrando sistema... Até logo!")
            break

        else:
            print("\n Opção inválida. Tente novamente.")

if __name__ == "__main__":
    main()
