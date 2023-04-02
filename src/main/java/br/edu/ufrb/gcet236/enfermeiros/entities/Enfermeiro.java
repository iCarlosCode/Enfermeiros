package br.edu.ufrb.gcet236.enfermeiros.entities;

public class Enfermeiro extends Pessoa {
    private String lotação;

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
