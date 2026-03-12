# ===============================================
# Calculadora de notas
# ===============================================
# Disciplina : Programação de Sistema (PS)
# Aula       : 06 - funcoes parametros retorno e escopo
# Autor      : [Kauê Mendes]
# Data       : [12/03/2026]
# Repisitório: https://github.com/kauwfx/2026-PS
# ===============================================
# --- DEFINIÇÕES DE FUNÇÕES ---

def calcular_media(nota1, nota2):
    """Calcula a média aritmética simples entre duas notas."""
    return (nota1 + nota2) / 2

def verificar_situacao(media):
    """Define a situação do aluno com base nos critérios do IFPR."""
    if media >= 6.0:
        return "Aprovado"
    elif 4.0 <= media < 6.0:
        return "Recuperação"
    else:
        return "Reprovado"

def solicitar_notas(nome_aluno):
    """Solicita e valida duas notas entre 0 e 10 para um aluno."""
    notas = []
    i = 1
    print(f"\n--- Notas do aluno: {nome_aluno} ---")
    while len(notas) < 2:
        try:
            nota = float(input(f"Digite a {i}ª nota: "))
            if 0 <= nota <= 10:
                notas.append(nota)
                i += 1
            else:
                print("Erro: A nota deve estar entre 0 e 10.")
        except ValueError:
            print(" Erro: Digite um valor numérico válido.")
    return notas[0], notas[1]

def gerar_relatorio(nome, media, situacao):
    """Exibe o resultado individual formatado."""
    print(f" Aluno: {nome:<15} | Média: {media:>4.1f} | Situação: {situacao}")

def calcular_media_turma_recursiva(lista_medias):
    """Calcula a média da turma usando recursão (Conceito A)."""
    if not lista_medias:
        return 0
    
    # Função interna para somar recursivamente
    def somar_recursivo(lista):
        if len(lista) == 0:
            return 0
        return lista[0] + somar_recursivo(lista[1:])
    
    soma_total = somar_recursivo(lista_medias)
    return soma_total / len(lista_medias)

def resumo_turma(dados_alunos):
    """
    Analisa a lista de alunos e retorna contagem de situações.
    Retorna: (aprovados, recuperacao, reprovados)
    """
    aprovados = 0
    recuperacao = 0
    reprovados = 0
    
    for aluno in dados_alunos:
        situacao = aluno['situacao']
        if situacao == "Aprovado":
            aprovados += 1
        elif situacao == "Recuperação":
            recuperacao += 1
        else:
            reprovados += 1
            
    return aprovados, recuperacao, reprovados

# --- EXECUÇÃO PRINCIPAL ---

def main():
    turma = []
    medias_lista = []
    total_alunos = 3  # Definido conforme requisito do Nível B

    print("=== SISTEMA DE GESTÃO ACADÊMICA IFPR ===")

    # Processamento dos alunos
    for _ in range(total_alunos):
        nome = input("\nNome do aluno: ")
        n1, n2 = solicitar_notas(nome)
        
        media = calcular_media(n1, n2)
        situacao = verificar_situacao(media)
        
        # Armazenando dados para o resumo final
        turma.append({'nome': nome, 'situacao': situacao})
        medias_lista.append(media)
        
        # Relatório individual imediato
        gerar_relatorio(nome, media, situacao)

    # Cálculos finais da turma
    media_geral = calcular_media_turma_recursiva(medias_lista)
    aprov, rec, repr_ = resumo_turma(turma)

    # Relatório Final
    print("\n" + "="*40)
    print("RESUMO FINAL DA TURMA")
    print("="*40)
    print(f"Média Geral da Turma: {media_geral:.2f}")
    print(f"Total de Aprovados:   {aprov}")
    print(f"Em Recuperação:       {rec}")
    print(f"Total de Reprovados:  {repr_}")
    print("="*40)

if __name__ == "__main__":
    main()