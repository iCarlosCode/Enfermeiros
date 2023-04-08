package br.edu.ufrb.gcet236.enfermeiros.entities;

import java.util.ArrayList;

public class Hospital {
    private ArrayList<Pessoa> colaboradores = new ArrayList<Pessoa>();

    public ArrayList<Pessoa> getColaboradores() {
        return this.colaboradores;
    }

    public void cadastrarColaboradores(Pessoa colaborador) {
        this.colaboradores.add(colaborador);
    }
    
    public void editarColaboradores(Pessoa enfermeiro){
        for (int i = 0; i < colaboradores.size(); i++ ) {
            Pessoa colaborador = colaboradores.get(i);
            String cpfDoColaborador = colaborador.getCpf();
            if (enfermeiro.getCpf().equalsIgnoreCase(cpfDoColaborador)) {
                colaboradores.set(i, enfermeiro);
            }
        }
    }

    public void removerColaboradores(Pessoa colaborador) {
        this.colaboradores.remove(colaborador);
    }

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
   
    public ArrayList<Pessoa> buscarPorLotação(String lotação) {
        ArrayList<Pessoa> resultados = new ArrayList<Pessoa>();
        for (Pessoa colaborador : this.colaboradores) {
            if (colaborador instanceof Enfermeiro) {
                Enfermeiro enfermeiro = (Enfermeiro) colaborador;
                String lotacaoDoColaborador = enfermeiro.getLotação();
                if (lotação.equalsIgnoreCase(lotacaoDoColaborador)){
                    resultados.add(colaborador);
                }
            }
        }
        return resultados;
    }
}
