package com.praticaIntegradora.projeto1.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Column(name = "tel_cliente")
    private Long telefoneCliente;

    @Column(name = "dt_nasc_cliente")
    private LocalDate dataNascimentoCliente;

    public Integer getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Long getTelefoneCliente() {
        return telefoneCliente;
    }
    public void setTelefoneCliente(Long telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public LocalDate getDataNascimentoCliente() {
        return dataNascimentoCliente;
    }
    public void setDataNascimentoCliente(LocalDate dataNascimentoCliente) {
        this.dataNascimentoCliente = dataNascimentoCliente;
    }
}