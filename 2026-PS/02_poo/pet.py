'''
===============================================================================
# ARQUIVO    : pet.py
# Disciplina : Programação de Sistemas (2026-2)
# Aula       : Aula 20 — Por que POO?
# Autor      : [Kauê.M]
# Conceitos  : Classe, objeto, atributos, métodos, encapsulamento
# Atividade  : Classe Pet
===============================================================================
'''

class Pet:
    '''
    Esta classe representa um Pet em um sistema simples de hotel para pets.

    Em vez de guardar os dados do pet em um dicionário solto, como fazíamos
    na programação estruturada, agora agrupamos os dados e comportamentos
    dentro de uma classe.
    '''

    def __init__(self, nome, especie, idade, raca, nome_dono, observacoes, vacinado, peso):
        '''
        Método construtor.

        Ele é executado automaticamente quando criamos um novo objeto Pet.

        Exemplo:
        pet1 = Pet("Rex", "Cachorro", 5)

        Parâmetros:
        - nome: nome do pet
        - especie: espécie do pet
        - idade: idade do pet
        '''

        # Atributos principais do pet
        self.nome = nome
        self.especie = especie
        self.idade = idade

        # ==========================================================
        # ATIVIDADE 1:
        # Adicionando novos atributos ao pet
        # ==========================================================

        self.raca = raca
        self.nome_dono = nome_dono
        self.observacoes = observacoes
        self.vacinado = vacinado
        self.peso = peso

        # Status inicial da hospedagem
        self.hospedado = False


    def exibir_dados(self):
        '''
        Exibe os dados principais do pet.

        Atualmente, mostra apenas nome, espécie, idade e status de
        hospedagem.

        ATIVIDADE:
        Modifique este método para exibir também os novos atributos
        adicionados no __init__.
        '''

        print("\n--- Dados do Pet ---")
        print(f"Nome: {self.nome}")
        print(f"Espécie: {self.especie}")
        print(f"Idade: {self.idade}")

        # Novos atributos adicionados
        print(f"Raça: {self.raca}")
        print(f"Nome do dono: {self.nome_dono}")
        print(f"Observações: {self.observacoes}")
        print(f"Peso: {self.peso} kg")

        print(f"Hospedado: {'Sim' if self.hospedado else 'Não'}")


    def registrar_entrada(self):
        '''
        Registra a entrada do pet no hotel.

        Se o pet ainda não estiver hospedado, muda o atributo hospedado
        para True.

        ATIVIDADE:
        Melhorar o método verificando se o pet já está hospedado.
        '''

        if self.hospedado:
            print(f"{self.nome} já está hospedado.")

        else:
            self.hospedado = True
            print(f"{self.nome} entrou no hotel.")


    def registrar_saida(self):
        '''
        Registra a saída do pet do hotel.

        Se o pet estiver hospedado, muda o atributo hospedado para False.

        ATIVIDADE:
        Verificar se o pet realmente está hospedado antes da saída.
        '''

        if self.hospedado:

            self.hospedado = False
            print(f"{self.nome} saiu do hotel.")

        else:
            print(f"{self.nome} não está hospedado.")


    def calcular_diaria(self):
        '''
        Calcula o valor da diária do pet.

        ATIVIDADE:
        Implementar regras simples de cálculo da diária.

        Regras:
        - Até 3 anos: R$ 50,00
        - Entre 4 e 10 anos: R$ 60,00
        - Acima de 10 anos: R$ 75,00
        '''

        if self.idade <= 3:
            return 50.0

        elif 4 <= self.idade <= 10:
            return 60.0

        else:
            return 75.0


    def verificar_vacinacao(self):
        '''
        Verifica se o pet está vacinado.

        ATIVIDADE:
        Exibir mensagem informando situação da vacinação.
        '''

        if self.vacinado:
            print("Vacinação em dia.")

        else:
            print("Atenção: vacinação pendente.")


    def atualizar_peso(self, novo_peso):
        '''
        Atualiza o peso do pet.

        ATIVIDADE:
        Receber um novo peso e atualizar o valor antigo.
        '''

        peso_antigo = self.peso

        self.peso = novo_peso

        print(f"Peso antigo: {peso_antigo} kg")
        print(f"Novo peso: {self.peso} kg")


    def emitir_resumo(self):
        '''
        Exibe um resumo geral do pet.

        ATIVIDADE:
        Mostrar:
        - nome
        - espécie
        - idade
        - dono
        - peso
        - vacinação
        - hospedagem
        - diária
        '''

        print("\n===== RESUMO DO PET =====")

        print(f"Nome do Pet: {self.nome}")
        print(f"Espécie: {self.especie}")
        print(f"Idade: {self.idade}")
        print(f"Raça: {self.raca}")

        print(f"Nome do Dono: {self.nome_dono}")

        print(f"Peso: {self.peso} kg")

        print(f"Status de vacinação: {'Em dia' if self.vacinado else 'Pendente'}")

        print(f"Status de hospedagem: {'Hospedado' if self.hospedado else 'Não hospedado'}")

        print(f"Valor da diária: R${self.calcular_diaria():.2f}")


# ==========================================================
# TESTES DA CLASSE
        # ==========================================================

        # Criando pet1
        pet1 = Pet(
            "Rex",
            "Cachorro",
            5,
            "Labrador",
            "Carlos",
            "Muito brincalhão",
            True,
            18.5
        )

        # Criando pet2
        pet2 = Pet(
            "Mimi",
            "Gato",
            3,
            "Persa",
            "Ana",
            "Gosta de dormir",
            True,
            4.2
        )

        # Criando pet3
        pet3 = Pet(
            "Thor",
            "Cachorro",
            7,
            "Pastor Alemão",
            "João",
            "Late muito",
            False,
            30.0
        )

        # Testando métodos do pet1
        pet1.exibir_dados()
        pet1.registrar_entrada()
        pet1.verificar_vacinacao()
        pet1.atualizar_peso(20)
        pet1.emitir_resumo()

        print("\n")

        # Testando métodos do pet2
        pet2.exibir_dados()
        pet2.registrar_entrada()
        pet2.registrar_saida()
        pet2.verificar_vacinacao()
        pet2.atualizar_peso(5)
        pet2.emitir_resumo()

        print("\n")

        # Testando métodos do pet3
        pet3.exibir_dados()
        pet3.registrar_entrada()
        pet3.verificar_vacinacao()
        pet3.atualizar_peso(32)
        pet3.emitir_resumo()


# ==========================================================
# ATIVIDADE FINAL:
# Crie mais dois pets e teste todos os métodos implementados.
# ==========================================================

# Criando pet4
pet4 = Pet(
    "Mel",
    "Cachorro",
    2,
    "Poodle",
    "Fernanda",
    "Muito agitada",
    True,
    6.5
)

# Criando pet5
pet5 = Pet(
    "Luna",
    "Gato",
    11,
    "Siamês",
    "Ricardo",
    "Precisa de remédio",
    False,
    5.8
)

# Testando métodos do pet4
pet4.exibir_dados()
pet4.registrar_entrada()
pet4.verificar_vacinacao()
pet4.atualizar_peso(7.0)
pet4.emitir_resumo()

print("\n")

# Testando métodos do pet5
pet5.exibir_dados()
pet5.registrar_entrada()
pet5.registrar_saida()
pet5.verificar_vacinacao()
pet5.atualizar_peso(6.1)
pet5.emitir_resumo()