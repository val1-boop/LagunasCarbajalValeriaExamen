package com.examenVal.examenVal.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
public class Tiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int valorDado1;
    private int valorDado2;
    private int suma;
    private boolean esGanador;
    @ManyToOne
    @JoinColumn(name = "partida_id")
    private Partida partida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValorDado1() {
        return valorDado1;
    }

    public void setValorDado1(int valorDado1) {
        this.valorDado1 = valorDado1;
    }

    public int getValorDado2() {
        return valorDado2;
    }

    public void setValorDado2(int valorDado2) {
        this.valorDado2 = valorDado2;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public boolean isEsGanador() {
        return esGanador;
    }

    public void setEsGanador(boolean esGanador) {
        this.esGanador = esGanador;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
}
