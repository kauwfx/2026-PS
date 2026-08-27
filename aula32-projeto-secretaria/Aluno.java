/**
 * Disciplina:2025-ps
 * Estudante : Kaue
 * data : 27.08
 * Projeto : aula32-projeto-secretaria
 * Arquivo : Aluno.java
 */
/* classe e o molde da ficha
ela n guarda os dados de ninguem: descreve oq toda ficha de aluno
tem (nome, matricula,curso ) */
public class Aluno {
    private String nome;
    private String matricula;
    private String curso;
    private String cidade; // Extra ai

    public Aluno(String nome, String matricula, String curso, String cidade) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.cidade = cidade;
    }

    // Getters e Setters
    public String getNome() { 
        return nome; 
    }
    
    public void setNome(String nome) { 
        this.nome = nome; 
    }

    public String getMatricula() { 
        return matricula; 
    }
    
    public void setMatricula(String matricula) { 
        this.matricula = matricula; 
    }

    public String getCurso() { 
        return curso; 
    }
    
    public void setCurso(String curso) { 
        this.curso = curso; 
    }

    public String getCidade() { 
        return cidade; 
    }
    
    public void setCidade(String cidade) { 
        this.cidade = cidade; 
    }

    // A ficha se apresenta sozinha (Fase 2)
    @Override
    public String toString() {
        return matricula + " | " + nome + " | " + curso + " | " + cidade;
    }
}