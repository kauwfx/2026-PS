"""
agenda.py - aula 23 (programação de sistema,2026)
Agenda de Contatos: classe inicial.
autor : Kaue.M
"""
import pickle

class Contato:
    """Representa um contato simples na agenda."""
    def __init__(self, nome, telefone, email):
        self.nome = nome 
        self.telefone = telefone
        self.email = email

    def exibir(self):
        print(f" Nome : {self.nome}")
        print(f" Telefone: {self.telefone}")
        print(f" Email : {self.email}")

    def para_linha_txt(self):
        return f"{self.nome};{self.telefone};{self.email}"
    

def salvar_em_txt(contatos, caminho):
    """Grava cada contato como uma linha no arquivo de texto"""
    with open(caminho, "w", encoding="utf-8") as arquivo:
        for contato in contatos:
            arquivo.write(contato.para_linha_txt() + "\n")
    print(f"{len(contatos)} contatos salvos em {caminho}.")

def carregar_de_txt(caminho):
    """Lê contatos de um arquivo de texto e retorna uma lista de objetos Contato"""
    contatos = []
    try:
        with open(caminho, "r", encoding="utf-8") as arquivo:
            for linha in arquivo:
                linha = linha.strip()
                if not linha:
                    continue
                partes = linha.split(";")
                nome, telefone, email = partes [0], partes[1], partes[2]
                contatos.append(Contato(nome, telefone, email))
    except FileNotFoundError:
        print(f"Arquivo {caminho} não encontrado. Retornando lista vazia.")
    return contatos
# ============================================================
# Persistencia binaria (pickle)
# ============================================================
def salvar_em_binario(contatos, caminho):
    with open(caminho, "wb") as arquivo:
        pickle.dump(contatos, arquivo)
    print(f"{len(contatos)} contato(s) salvos em {caminho}.")

def carregar_de_binario(caminho):
    try:
        with open(caminho, "rb") as arquivo:
            return pickle.load(arquivo)
    except FileNotFoundError:
        print(f"Arquivo {caminho} ainda não existe. Começando vazio.")
        return []
    

# ============================================================
#crud em memoria
# ============================================================
def cadastrar(contatos):
    """Solicita os dados do contato e adiciona à lista"""
    print("\n--- Novo Contato ---")
    nome = input("Nome: ")
    telefone = input("Telefone: ")
    email = input("Email: ")
    contatos.append(Contato(nome, telefone, email))
    print("Contato cadastrado.")

def listar(contatos):
    if not contatos:
        print("\n(agenda vazia)")
        return
    print(f"\n--- Agenda ({len(contatos)} contatos) ---")
    for i, c in enumerate(contatos, start=1):
        print(f"\n[{i}]")
        c.exibir()

def remover(contatos):
    '''Mostra a lista de contatos e solicita o número do contato a ser removido.'''
    listar(contatos)
    if not contatos:
        return
    indice = int(input("\nDigite o número do contato a remover: ")) - 1
    if 0 <= indice < len(contatos):
        removido = contatos.pop(indice)
        print(f" Contato '{removido.nome}' removido com sucesso!")
    else:
        print("Indice inválido.")
    
# ============================================================
# Menu principal
# ============================================================
def menu():
    contatos = carregar_de_binario("agenda.bin")

    while True:
        print("\n===== AGENDA DE CONTATOS =====")
        print("1. Cadastrar contato")
        print("2. Listar contatos")
        print("3. Remover contato")
        print("4. Salvar em .txt")
        print("5. Salvar em .bin")
        print("0. Sair")
        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            cadastrar(contatos)
        elif opcao == "2":
            listar(contatos)
        elif opcao == "3":
            remover(contatos)
        elif opcao == "4":
            salvar_em_txt(contatos, "agenda.txt")
        elif opcao == "5":
            salvar_em_binario(contatos, "agenda.bin")
        elif opcao == "0":
            salvar_em_binario(contatos, "agenda.bin")
            print("Até logo!")
            break
        else:
            print("Opção inválida. Tente novamente.")
# ============================================================
# PONTO DE ENTRADA
# ============================================================
if __name__ == "__main__":
    menu()
