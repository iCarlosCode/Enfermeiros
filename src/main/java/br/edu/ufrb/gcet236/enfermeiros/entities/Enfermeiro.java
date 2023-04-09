package br.edu.ufrb.gcet236.enfermeiros.entities;

public class Enfermeiro extends Pessoa {
    private String lotação;

    public Enfermeiro(String nome, String cpf, String rg, String telefone, String lotação) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setRg(rg);
        this.setTelefone(telefone);
        this.setLotação(lotação);
    }
    public String getLotação() {
        return this.lotação;
    }

    public void setLotação(String lotação) {
        this.lotação = lotação;
    }

    @Override
    public String toString() {

        // Return Json of parameters lotação, rg, cpf, nome and telefone
        return "{" + " lotação='" + this.getLotação() + "'" + ", rg='" + this.getRg() + "'" + ", cpf='" + this.getCpf() + "'"
                + ", nome='" + this.getNome() + "'" + ", telefone='" + this.getTelefone() + "'" + "}";
    }
}
