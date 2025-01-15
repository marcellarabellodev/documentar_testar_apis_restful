package com.praticaIntegradora.projeto1.controller;

import com.praticaIntegradora.projeto1.entity.Cliente;
import com.praticaIntegradora.projeto1.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cliente")

public class ClienteController {
    @Autowired
    ClienteService clienteService;
    // Foi inserido o Service para encapsular a lógica da aplicação

    @GetMapping
    @Operation(summary="Listar todos os clientes", description  = "Listagem dos Clientes") // Essa informação irá aparecer ao lado de Get /cliente
    @ApiResponses(value = {
            // aparecerão os seguintes códigos de acordo com o retorno da ação
            @ApiResponse(responseCode = "200", description = "A requisição foi executada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse recurso."),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.")})

    //Inserindo os métodos necessários para realizar o controle das ações: Buscar todos os clientes, buscar cliente por Id, incluir cliente, atualizar cliente e deletar cliente
    public ResponseEntity<List<Cliente>> getAll(){
        List<Cliente> clientes = clienteService.getAll();
        if(!clientes.isEmpty())
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        Cliente cliente = clienteService.getById(id);
        if(cliente != null)
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.saveCliente(cliente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id,
                                                 @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.updateCliente(id, cliente);
        if(clienteAtualizado != null)
            return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCliente(@PathVariable Long id) {
        if(clienteService.deleteCliente(id))
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(false, HttpStatus.OK);
    }
}




