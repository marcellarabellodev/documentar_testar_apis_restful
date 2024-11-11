package com.praticaIntegradora.projeto1.controller;

import com.praticaIntegradora.projeto1.entity.Cliente;
import com.praticaIntegradora.projeto1.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    @Operation(summary="Listar todos os clientes", description  = "Listagem dos Clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A requisição foi executada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse recurso."),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.")})
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }//Aqui atualizei de getAll para findAll

    // Aqui eu ajustei o ID pois na entidade era Integer e no repositorio estava Long, coloquei Integer em tudo.
    // No caso ele ta pedindo um ResponseEntity então temos que retornar um responseEntity
    // E vc estava usando GET para PEGAR o Id e não FIND para buscar o ID,
    // fiz uma lambda para ele buscar o cliente e retornar o cliente buscado
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id) {
        return clienteRepository.findById(id)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    //Aqui nesse salvar, precisa criar um clienteSalvo para receber o cliente que foi cadastro e depois retornar Ok.
    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo); //agora estou retornando o que a classe pede pra retornar.
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Integer id,
                                             @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setIdCliente(id);

        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    //Padronizei o ID para INTEGER, estava LONG (o ideal é ser LONG ou UUID)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }
}