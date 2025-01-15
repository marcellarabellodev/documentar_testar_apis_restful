package com.praticaIntegradora.projeto1.service;

import java.util.List;

import org.hibernate.dialect.LobMergeStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praticaIntegradora.projeto1.repository.ClienteRepository;
import com.praticaIntegradora.projeto1.entity.Cliente;

@Service

public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> getAll(){
        return clienteRepository.findAll();
    }

    public Cliente getById(Long id) {
        return clienteRepository.findById(id).orElse(null) ;
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        Cliente clienteAtualizada = clienteRepository.findById(id).orElse(null);
        if(clienteAtualizada != null) {
            clienteAtualizada.setNomeCliente(cliente.getNomeCliente());
            return clienteRepository.save(clienteAtualizada);
        }else {
            return null;
        }
    }

    public Boolean deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if(cliente != null) {
            clienteRepository.delete(cliente);
            return true;
        }else {
            return false;
        }
    }

}
