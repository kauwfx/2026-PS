'''
# ARQUIVO    : hotel_pets.py
# Disciplina : Programação de Sistemas
# Aula       : Aula 23
# Autor      : K.Mendes
# Sistema de hotel para pets com cadastro e persistência
'''

# importando bibliotecas
import pickle
import os


# =============================================================================
# CLASSE PET
# =============================================================================

class Pet:

    # criando o construtor da classe
    def __init__(self, nome, especie, idade, peso, vacinado, dono):

        self.nome = nome
        self.especie = especie
        self.idade = idade
        self.peso = peso
        self.vacinado = vacinado
        self.dono = dono
        self.hospedado = False


    # método para mostrar os dados do pet
    def exibir_dados(self):

        print("\n--------------------------------")
        print(f"Nome do pet: {self.nome}")
        print(f"Espécie: {self.especie}")
        print(f"Idade: {self.idade} anos")
        print(f"Peso: {self.peso} kg")
        print(f"Vacinado: {self.vacinado}")
        print(f"Dono: {self.dono}")
        print(f"Hospedado: {self.hospedado}")
        print("--------------------------------")


    # método de check-in
    def registrar_entrada(self):

        self.hospedado = True
        print(f"\n{self.nome} fez check-in no hotel!")


    # método de check-out
    def registrar_saida(self):

        self.hospedado = False
        print(f"\n{self.nome} fez check-out do hotel!")


    # método para calcular diária
    def calcular_diaria(self):

        if self.peso <= 10:
            return 50

        elif self.peso <= 25:
            return 80

        else:
            return 120


    # verificar vacinação
    def verificar_vacinacao(self):

        if self.vacinado.lower() == "sim":
            return True

        return False


    # atualizar peso
    def atualizar_peso(self, novo_peso):

        self.peso = novo_peso
        print(f"\nPeso atualizado com sucesso!")


    # resumo do pet
    def emitir_resumo(self):

        print("\n========== RESUMO ==========")
        print(f"Pet: {self.nome}")
        print(f"Espécie: {self.especie}")
        print(f"Dono: {self.dono}")
        print(f"Hospedado: {self.hospedado}")
        print(f"Valor da diária: R$ {self.calcular_diaria()}")
        print("================================")


# =============================================================================
# SALVAR EM TXT
# =============================================================================

def salvar_txt(lista_pets):

    arquivo = open("pets.txt", "w", encoding="utf-8")

    for pet in lista_pets:

        linha = f"{pet.nome};{pet.especie};{pet.idade};{pet.peso};{pet.vacinado};{pet.dono};{pet.hospedado}\n"

        arquivo.write(linha)

    arquivo.close()


def carregar_txt():

    lista = []

    # verifica se o arquivo existe
    if os.path.exists("pets.txt"):

        arquivo = open("pets.txt", "r", encoding="utf-8")

        for linha in arquivo:

            dados = linha.strip().split(";")

            pet = Pet(
                dados[0],
                dados[1],
                int(dados[2]),
                float(dados[3]),
                dados[4],
                dados[5]
            )

            pet.hospedado = dados[6] == "True"

            lista.append(pet)

        arquivo.close()

    return lista


# =============================================================================
# SALVAR EM BINÁRIO
# =============================================================================

def salvar_binario(lista_pets):

    arquivo = open("pets.pkl", "wb")

    pickle.dump(lista_pets, arquivo)

    arquivo.close()


def carregar_binario():

    if os.path.exists("pets.pkl"):

        arquivo = open("pets.pkl", "rb")

        lista = pickle.load(arquivo)

        arquivo.close()

        return lista

    return []

# =============================================================================
# INÍCIO DO SISTEMA
# =============================================================================

# carregando os dados já salvos
pets = carregar_binario()


# =============================================================================
# MENU PRINCIPAL
# =============================================================================

while True:

    print("\n=========== PETLÂNDIA =========")
    print("1 - Cadastrar pet")
    print("2 - Listar pets")
    print("3 - Fazer check-in")
    print("4 - Fazer check-out")
    print("5 - Atualizar peso")
    print("6 - Buscar pet por nome")
    print("7 - Relatório de hospedados")
    print("8 - Resumo individual")
    print("9 - Salvar em TXT")
    print("10 - Salvar em BINÁRIO")
    print("0 - Sair")
    print("================================")

    opcao = input("Escolha uma opção: ")


    # cadastrar pet
    if opcao == "1":

        print("\n=== Cadastro de Pet ===")

        nome = input("Nome do pet: ")
        especie = input("Espécie: ")
        idade = int(input("Idade: "))
        peso = float(input("Peso: "))
        vacinado = input("Vacinado? (sim/não): ")
        dono = input("Nome do dono: ")

        novo_pet = Pet(
            nome,
            especie,
            idade,
            peso,
            vacinado,
            dono
        )

        pets.append(novo_pet)

        print("\nPet cadastrado com sucesso!")


    # listar pets
    elif opcao == "2":

        if len(pets) == 0:

            print("\nNenhum pet cadastrado ainda.")

        else:

            print("\n=== Lista de Pets ===")

            for pet in pets:
                pet.exibir_dados()


    # check-in
    elif opcao == "3":

        nome = input("\nDigite o nome do pet: ")

        encontrado = False

        for pet in pets:

            if pet.nome.lower() == nome.lower():

                pet.registrar_entrada()
                encontrado = True

        if not encontrado:
            print("\nPet não encontrado.")


    # check-out
    elif opcao == "4":

        nome = input("\nDigite o nome do pet: ")

        encontrado = False

        for pet in pets:

            if pet.nome.lower() == nome.lower():

                pet.registrar_saida()
                encontrado = True

        if not encontrado:
            print("\nPet não encontrado.")


    # atualizar peso
    elif opcao == "5":

        nome = input("\nDigite o nome do pet: ")

        encontrado = False

        for pet in pets:

            if pet.nome.lower() == nome.lower():

                novo_peso = float(input("Novo peso: "))

                pet.atualizar_peso(novo_peso)

                encontrado = True

        if not encontrado:
            print("\nPet não encontrado.")


    # buscar pet
    elif opcao == "6":

        busca = input("\nDigite o nome do pet: ")

        encontrados = []

        for pet in pets:

            if busca.lower() in pet.nome.lower():

                encontrados.append(pet)

        if len(encontrados) == 0:

            print("\nNenhum pet encontrado.")

        else:

            print("\n=== Pets encontrados ===")

            for pet in encontrados:
                pet.exibir_dados()


    # relatório de hospedados
    elif opcao == "7":

        total = 0

        print("\n=== Pets Hospedados ===")

        for pet in pets:

            if pet.hospedado:

                pet.exibir_dados()

                total += pet.calcular_diaria()

        print(f"\nTotal das diárias do dia: R$ {total}")


    # resumo individual
    elif opcao == "8":

        nome = input("\nDigite o nome do pet: ")

        encontrado = False

        for pet in pets:

            if pet.nome.lower() == nome.lower():

                pet.emitir_resumo()

                encontrado = True

        if not encontrado:
            print("\nPet não encontrado.")


    # salvar em txt
    elif opcao == "9":

        salvar_txt(pets)

        print("\nDados salvos em TXT com sucesso!")


    # salvar em binário
    elif opcao == "10":

        salvar_binario(pets)

        print("\nDados salvos em BINÁRIO com sucesso!")


    # sair do sistema
    elif opcao == "0":

        salvar_txt(pets)
        salvar_binario(pets)

        print("\nSistema encerrado com sucesso!")
        print("Até logo!")

        break


    # caso o usuário digite algo inválido
    else:

        print("\nOpção inválida. Tente novamente.")