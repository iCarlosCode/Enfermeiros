package br.edu.ufrb.gcet236.enfermeiros.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import br.edu.ufrb.gcet236.enfermeiros.entities.Hospital;
import br.edu.ufrb.gcet236.enfermeiros.entities.Enfermeiro;
import br.edu.ufrb.gcet236.enfermeiros.entities.Pessoa;

@RestController
@RequestMapping("api")
public class EnfermeirosController {
    Hospital colaboradores = new Hospital();
    ArrayList<Enfermeiro> enfermeiros = new ArrayList<Enfermeiro>();

    @GetMapping("/hello-world")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello Enfermeiros!");
    }
    
    @GetMapping("/listar_enfermeiros")
    public ResponseEntity<ArrayList<Pessoa>> listarEnfermeiros() {
        return ResponseEntity.ok(colaboradores.getColaboradores());
    }

    @PostMapping(value = "/cadastrar")
    public String cadastrarEnfermeiro(@RequestBody Enfermeiro entrada) {
        colaboradores.cadastrarColaboradores(entrada);
        
        return colaboradores.getColaboradores().toString();
    }

    @GetMapping(value = "/busca")
    public ResponseEntity<ArrayList<Enfermeiro>> buscarEnfermeiro(@RequestParam String nome, String cpf, String rg, String lotação) {
        ArrayList<Pessoa> resultadosDaBusca = null;
        enfermeiros.clear();
        if (!nome.isEmpty()) {
            resultadosDaBusca = this.colaboradores.buscarPorNome(nome);
        } 
        else if (cpf != null) 
        {
            resultadosDaBusca = this.colaboradores.buscarPorCPF(cpf);
        }
        else if (rg != null) 
        {
            resultadosDaBusca = this.colaboradores.buscarPorRG(rg);
        }
        else if (lotação != null) 
        {
            resultadosDaBusca = this.colaboradores.buscarPorLotação(lotação);
        }
        
        if (resultadosDaBusca == null) 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enfermeiros);
                //"Nenhum enfermeiro encontrado, revise suas informações.");
        }

        for (var resultado : resultadosDaBusca) {
            if (resultado instanceof Enfermeiro)
            {
                enfermeiros.add((Enfermeiro) resultado);
            }
        }

        return ResponseEntity.ok(enfermeiros);
    }

    @DeleteMapping(value = "/remover")
    public ResponseEntity<String> removerFevereiro(@RequestParam String nome, String cpf, String rg, String lotação) {
        var resultadoDaBusca = buscarEnfermeiro(nome, cpf, rg, lotação).getBody();

        if (resultadoDaBusca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum enfermeiro encontrado, revise suas informações.");
        }

        Enfermeiro enfermeiro = resultadoDaBusca.get(0);
        
        colaboradores.removerColaboradores(enfermeiro);
        return ResponseEntity.ok(enfermeiros.toString());
    }
}
