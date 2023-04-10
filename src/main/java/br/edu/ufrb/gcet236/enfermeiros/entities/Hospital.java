package br.edu.ufrb.gcet236.enfermeiros.entities;

import java.util.ArrayList;

public class Hospital {
    private ArrayList<Pessoa> colaboradores = new ArrayList<Pessoa>();

    // Método para obter a lista de colaboradores do hospital
    public ArrayList<Pessoa> getColaboradores() {
        return this.colaboradores;
    }

    // Método para cadastrar um novo colaborador no hospital
    public void cadastrarColaboradores(Pessoa colaborador) {
        this.colaboradores.add(colaborador);
    }
    
    // Método para editar um colaborador existente no hospital
    public void editarColaboradores(Pessoa enfermeiro, String cpfAntigo){
        for (int i = 0; i < colaboradores.size(); i++ ) {
            Pessoa colaborador = colaboradores.get(i);
            String cpfDoColaborador = colaborador.getCpf();
            if (cpfAntigo.equalsIgnoreCase(cpfDoColaborador)) {
                colaboradores.set(i, enfermeiro);
            }
        }
    }

    // Método para remover um colaborador do hospital
    public void removerColaboradores(Pessoa colaborador) {
        this.colaboradores.remove(colaborador);
    }

    // Método para buscar colaboradores por nome
    public ArrayList<Pessoa> buscarPorNome(String nome) {
        // Todo Baseado nos Exercícios 5.3 de 14 Mar no ClassRoom
        ArrayList<Pessoa> resultados = new ArrayList<Pessoa>();
        for (Pessoa colaborador : this.colaboradores) {
            if (colaborador.getNome().contains(nome)) {
                resultados.add(colaborador);
            }
        }
        return resultados;
    }
    
    // Método para buscar colaboradores por CPF
    public ArrayList<Pessoa> buscarPorCPF(String cpf) {
        ArrayList<Pessoa> resultados = new ArrayList<Pessoa>();
        for (Pessoa colaborador : this.colaboradores) {
            String cpfDoColaborador = colaborador.getCpf();
            if (cpf.equalsIgnoreCase(cpfDoColaborador)) {
                resultados.add(colaborador);
            }
        }
        return resultados;
    }
    
    // Método para buscar colaboradores por RG
    public ArrayList<Pessoa> buscarPorRG(String rg) {
        // Todo Baseado nos Exercícios 5.3 de 14 Mar no ClassRoom
        ArrayList<Pessoa> resultados = new ArrayList<Pessoa>();
        for (Pessoa colaborador : this.colaboradores) {
            if (rg.equalsIgnoreCase(colaborador.getRg())) {
                resultados.add(colaborador);
            }
        }
        return resultados;
    }
   
    // Método para buscar colaboradores por lotação
    public ArrayList<Pessoa> buscarPorLotação(String lotação) {
        ArrayList<Pessoa> resultados = new ArrayList<Pessoa>();
        for (Pessoa colaborador : this.colaboradores) {
            if (colaborador instanceof Enfermeiro) { // Verifica se o colaborador é um enfermeiro
                Enfermeiro enfermeiro = (Enfermeiro) colaborador; // Realiza o cast do objeto para Enfermeiro
                String lotacaoDoColaborador = enfermeiro.getLotação(); // Obtém a lotação do enfermeiro
                if (lotação.equalsIgnoreCase(lotacaoDoColaborador)){ // Compara a lotação do enfermeiro com a lotação informada
                    resultados.add(colaborador); // Adiciona o enfermeiro na lista de resultados
                }
            }
        }
        return resultados;
    }
}
``
