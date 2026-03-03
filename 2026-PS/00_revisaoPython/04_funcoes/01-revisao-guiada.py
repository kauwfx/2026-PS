# ===============================================
# SISTEMA DE CALCULO DE IMO
# ===============================================
# Disciplina : Programação de Sistema (PS)
# Aula       : 06 - Revisao: Funções
# Autor      : [Kauê Mendes]
# Data       : [03/03/2026]
# Repisitório: https://github.com/kauwfx/2026-PS
# ===============================================
#
# DESCRIÇÃO: 
# calcula e classificar o imc de uma pessoa
#demonstrando definição de funcoes parameetros
# retorno, escopo e recursao
# ===============================================


def exibir_cabecalho():
    """Exibe o cabeçalho do sistema no terminal"""  
    print("=" * 40)
    print("   SISTEMA DE CALCULO DE IMC")
    print("=" * 40)

exibir_cabecalho()


def calcular_imc(peso, altura):
    """Calcula e retorna o IMC. Fórmula: peso / altura²"""
    imc = peso / (altura ** 2) 
    return imc                  



peso   = float(input("Peso (kg): "))
altura = float(input("Altura (m): "))



resultado = calcular_imc(peso, altura)

print(f"Seu IMC é: {resultado:.2f}")


versao = "1.0"  

def demonstrar_escopo():
    mensagem = "Olá do interior da função"  
    print("Dentro da função:")
    print(f"  mensagem = {mensagem}")       
    print(f"  versao   = {versao}")        
demonstrar_escopo()

print("\nFora da função:")
print(f"  versao   = {versao}")              
# ---- VALOR PADRÃO E MÚLTIPLOS RETORNOS ----

def classificar_imc(imc, unidade="kg/m²"):
    """Classifica o IMC e retorna classificação e emoji de status.
    Parâmetro 'unidade' tem valor padrão - não é obrigatório informar."""

    if imc < 18.5:
        classificacao = "Abaixo do peso"
        emoji = "⬇️"
    elif imc < 25.0:
        classificacao = "Peso normal"
        emoji = "✅"
    elif imc < 30.0:
        classificacao = "Sobrepeso"
        emoji = "⚠️"
    else:
        classificacao = "Obesidade"
        emoji = "🔴"

    return classificacao, emoji  # retorna dois valores - Python empacota como tupla


# Chamada sem o parâmetro opcional (usa o padrão "kg/m²")
imc_teste = 22.5
classificacao, emoji = classificar_imc(imc_teste)
print(f"IMC {imc_teste} ({classificacao}) {emoji}")

# Chamada informando o parâmetro opcional
classificacao, emoji = classificar_imc(imc_teste, unidade="lb/in²")
print(f"Mesma chamada com unidade customizada: {classificacao} {emoji}")
# ---- RECURSÃO BÁSICA ----

def contagem_regressiva(n):
    """Exibe contagem regressiva de n até 0 usando recursão."""
    if n < 0:             # CASO BASE: para a recursão
        return
    
    print(n)
    contagem_regressiva(n - 1)  # CHAMADA RECURSIVA: resolve problema menor


print("\n--- Contagem regressiva ---")
contagem_regressiva(5)


# Fatorial: exemplo clássico de recursão com retorno
def fatorial(n):
    """Calcula n! recursivamente. Ex: 5! = 5 x 4 x 3 x 2 x 1 = 120"""
    if n == 0 or n == 1:  # caso base
        return 1
    
    return n * fatorial(n - 1)  # caso recursivo


print("\n--- Fatorial ---")
for i in range(1, 7):
    print(f" {i}! = {fatorial(i)}")
    # ---- FUNÇÃO PRINCIPAL ----

def processar_pessoa():
    """Coleta dados, calcula IMC e exibe resultado completo."""
    nome   = input("\nNome: ")
    peso   = float(input("Peso (kg): "))
    altura = float(input("Altura (m): "))

    
    classificacao, emoji = classificar_imc(imc)

    print("\n--- Resultado ---")
    print(f"Nome          : {nome}")
    print(f"IMC           : {imc:.2f} kg/m²")
    print(f"Classificação : {classificacao} {emoji}")


# ---- EXECUÇÃO PRINCIPAL ----

continuar = "s"
while continuar == "s":
    processar_pessoa()
    continuar = input("\nProcessar outra pessoa? (s/n): ").lower()



# ERRO 1: A função definia 'mensagem', mas não a retornava.
def saudacao(nome, turno="manhã"):
    mensagem = f"Bom {turno}, {nome}!"
    return mensagem  # ADICIONADO RETURN

print(saudacao("Bruno", "tarde"))


# ERRO 2: A função 'dobrar' não retornava o resultado.
def dobrar(x):
    resultado = x * 2
    return resultado # ADICIONADO RETURN

print("Dobro de 5:", dobrar(5))


# ERRO 3: Para alterar uma variável global dentro da função, é necessário 'global'.
total = 0
def incrementar():
    global total     # ADICIONADO GLOBAL
    total = total + 1

incrementar()
print("Total:", total)


# ERRO 4: A função recursiva 'contagem' não tinha um CASO BASE (parada).
def contagem(n):
    if n < 0:        # ADICIONADO CASO BASE
        return
    print(n)
    contagem(n - 1)

contagem(3)