package br.edu.ufrb.gcet236.enfermeiros.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    Hospital hospital = new Hospital();
    ArrayList<Enfermeiro> enfermeiros = new ArrayList<Enfermeiro>();

    // Endpoint para testar a API
    @GetMapping("/hello-world")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello Enfermeiros!");
    }
    
    // Endpoint para listar todos os enfermeiros cadastrados
    @GetMapping("/listar_enfermeiros")
    public ResponseEntity<ArrayList<Pessoa>> listarEnfermeiros() {
        return ResponseEntity.ok(hospital.getColaboradores());
    }

    // Endpoint para cadastrar um novo enfermeiro
    @PostMapping(value = "/cadastrar")
    public String cadastrarEnfermeiro(@RequestBody Enfermeiro entrada) {
        hospital.cadastrarColaboradores(entrada);
        
        return hospital.getColaboradores().toString();
    }

    // Endpoint para editar as informações de um enfermeiro
    @PatchMapping(value = "/editar")
    public String editarEnfermeiro(@RequestParam String nome, String cpf, String rg, String telefone, String lotação, String cpfAntigo) {
        Enfermeiro enfermeiro = new Enfermeiro(nome, cpf, rg, telefone, lotação);
        hospital.editarColaboradores(enfermeiro, cpfAntigo);
        
        return hospital.getColaboradores().toString();
    }

    // Endpoint para buscar enfermeiros com base em critérios de busca
    @GetMapping(value = "/buscar")
    public ResponseEntity<ArrayList<Enfermeiro>> buscarEnfermeiro(@RequestParam String nome, String cpf, String rg, String lotação) {
        ArrayList<Pessoa> resultadosDaBusca = null;
        enfermeiros.clear();
        if (!nome.isEmpty()) {
            resultadosDaBusca = this.hospital.buscarPorNome(nome);
        } 
        else if (cpf != null) 
        {
            resultadosDaBusca = this.hospital.buscarPorCPF(cpf);
        }
        else if (rg != null) 
        {
            resultadosDaBusca = this.hospital.buscarPorRG(rg);
        }
        else if (lotação != null) 
        {
            resultadosDaBusca = this.hospital.buscarPorLotação(lotação);
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

    // Endpoint para remover um enfermeiro cadastrado
   
