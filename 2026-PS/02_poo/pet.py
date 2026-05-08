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
        self.nome = nome
        self.especie = especie
        self.idade = idade
        self.raca = raca
        self.nome_dono = nome_dono
        self.observacoes = observacoes
        self.vacinado = vacinado
        self.peso = peso
        self.hospedado = False
        '''
        # ==========================================================
        # ATIVIDADE 1:
        # Adicione pelo menos 3 novos atributos para o pet.
        #
        # Sugestões:
        # self.raca
        # self.peso
        # self.nome_dono
        # self.telefone_dono
        # self.vacinado
        # self.observacoes
        #
        # Atenção:
        # Se você adicionar novos atributos, também precisará alterar
        # os parâmetros do __init__.
        # ==========================================================
        '''

    def exibir_dados(self):
        '''
        Exibe os dados principais do pet.

        Atualmente, mostra apenas nome, espécie, idade e status de
        hospedagem.

        ATIVIDADE:
        Modifique este método para exibir também os novos atributos
        que você adicionou no __init__.
        '''

        print("\n--- Dados do Pet ---")
        print(f"Nome: {self.nome}")
        print(f"Espécie: {self.especie}")
        print(f"Idade: {self.idade}")
        print(f"raça: {self.raca}")
        print(f"nome_dono: {self.nome_dono}")
        print(f"observacoes: {self.observacoes}")
        print(f"Hospedado: {'Sim' if self.hospedado else 'Não'}")

    def registrar_entrada(self):
        '''
        Registra a entrada do pet no hotel.

        Se o pet ainda não estiver hospedado, muda o atributo hospedado
        para True.

        ATIVIDADE:
        Melhore este método para verificar se o pet já está hospedado.
        Se já estiver, mostre uma mensagem avisando.
        '''
        if self.hospedado:
            print(f"(self.nome)  Já Esta Hospedado")
        else:
            self.hospedado = True
        print(f"{self.nome} entrou no hotel.")


    def registrar_saida(self):
        '''
        Registra a saída do pet do hotel.

        Se o pet estiver hospedado, muda o atributo hospedado para False.

        ATIVIDADE:
        Melhore este método para verificar se o pet realmente está
        hospedado.
        Se não estiver, mostre uma mensagem avisando.
        '''
        if self.hospedado:
            print(f"(self.nome)  Não Esta Hospedado")
        self.hospedado = False
        print(f"{self.nome} saiu do hotel.")
        

    def calcular_diaria(self):
        '''
        Calcula o valor da diária do pet.

        ATIVIDADE:
        Implemente uma regra simples para calcular a diária.

        Sugestão:
        - Pet com idade até 3 anos: R$ 50,00
        - Pet com idade entre 4 e 10 anos: R$ 60,00
        - Pet com mais de 10 anos: R$ 75,00

        Este método deve retornar o valor da diária.
        '''

        if self.idade <= 3:
            return 50.0
        elif 4 <= self.idade <= 10:
            return 60.0
        else:
            return 75
    pass  # pass diz ao interpretador:
          # "Eu sei que deveria haver algo aqui, vou colocar
          # depois, por enquanto apenas ignore e siga em frente."


    def verificar_vacinacao(self):
        '''
        Verifica se o pet está vacinado.

        ATIVIDADE:
        Para este método funcionar, você precisa criar um atributo
        chamado self.vacinado no __init__.

        Se o pet estiver vacinado, exiba:
        "Vacinação em dia."

        Caso contrário, exiba:
        "Atenção: vacinação pendente."
        '''
        if self.vacinado:
            print("Vacinaçao esta em dia")
        else:
            print("Vacinação pendende")
        

        pass
    def atualizar_peso(self, novo_peso):
        '''
        Atualiza o peso do pet.

        ATIVIDADE:
        Para este método funcionar, você precisa criar um atributo
        chamado self.peso no __init__.

        O método deve receber um novo peso e atualizar o valor antigo.

        Exemplo:
        pet1.atualizar_peso(12.5)
        '''

        peso_antigo = self.peso
        self.peso = novo_peso

        print(f"Peso antigo: {peso_antigo} kg")
        print(f"Novo peso: {self.peso} kg")
        pass


    def emitir_resumo(self):
        '''
        Exibe um resumo geral do pet.

        ATIVIDADE:
        Crie uma mensagem organizada contendo:
        - nome do pet
        - espécie
        - idade
        - nome do dono
        - peso
        - status de vacinação
        - status de hospedagem
        - valor da diária

        Este método deve usar informações dos atributos e também pode
        chamar outros métodos, como calcular_diaria().
        '''

        print("===== RESUMO DO PET =====")
        print(f"Nome do Pet: {self.nome}")
        print(f"Espécie: {self.especie}")
        print(f"Idade: {self.idade}")

        print(f"Nome do Dono: {self.nome_dono}")
        print(f"Peso: {self.peso} kg")

        print(f"Status de vacinação: {self.vacinado}")
        print(f"Status de hospedagem: {self.hospedado}")

        print(f"Valor da diária: R${self.calcular_diaria():.2f}")
...
# ==========================================================
# TESTES DA CLASSE
# ==========================================================
# Depois de completar a classe, crie pelo menos 3 objetos Pet.
#
# Exemplo:
# pet1 = Pet("Rex", "Cachorro", 5)
#
# Atenção:
# Se você adicionou novos parâmetros no __init__, será necessário
# informar esses dados na criação do objeto.
# ==========================================================
...

pet1 = Pet("Rex", "Cachorro", 5)
pet2 = Pet("Mimi", "Gato", 3, "Ana")
pet3 = Pet("Thor", "Cachorro", 7, "João",)

pet1.exibir_dados()
pet1.registrar_entrada()
pet1.exibir_dados()

...
# ==========================================================
# ATIVIDADE FINAL:
# Crie mais dois pets e teste todos os métodos implementados.
# ==========================================================
...
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


pet4.exibir_dados()
pet4.registrar_entrada()
pet4.verificar_vacinacao()
pet4.atualizar_peso(7.0)
pet4.emitir_resumo()

print("\n")

#
pet5.exibir_dados()
pet5.registrar_entrada()
pet5.registrar_saida()
pet5.verificar_vacinacao()
pet5.atualizar_peso(6.1)
pet5.emitir_resumo()