package com.examenVal.examenVal.service;

import com.examenVal.examenVal.enums.Estado;
import com.examenVal.examenVal.models.Jugador;
import com.examenVal.examenVal.models.Partida;
import com.examenVal.examenVal.models.Tiro;
import com.examenVal.examenVal.repository.JugadorRepository;
import com.examenVal.examenVal.repository.PartidaRepository;
import com.examenVal.examenVal.repository.TiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
 class JuegoPartida {
    @Autowired
    private TiroRepository tiroRepository;
    @Autowired
    private PartidaRepository partidaRepository;
    @Autowired
    private JugadorRepository jugadorRepository;

    public Partida principaliniciarPartida(Long jugadorId, double apuesta){

        Optional<Jugador> optionalJugador = jugadorRepository.findById(jugadorId);

        if (!optionalJugador.isPresent()) {
            throw new RuntimeException("Jugador no encontrado");
        }

        Jugador jugador1 = optionalJugador.get();

        if (!jugador1.isActivo()){
            throw  new RuntimeException("Jugador no activo");
        }

        if (jugador1.getSaldo() < apuesta){
            throw  new RuntimeException("Saldo insuficiente");
        }

        jugador1.setSaldo(jugador1.getSaldo()-apuesta);
        jugadorRepository.save(jugador1);

        Partida partida = new Partida();
        partida.setJugador(jugador1);
        partida.setApuesta(apuesta);
        partida.setEstado(Estado.EN_JUEGO);
        partidaRepository.save(partida);
        return partida;
    }

    public Tiro realizarTiro(Long partidaId, double apuesta){
        Optional<Partida> optionalPartida = partidaRepository.findById(partidaId);
        if (!optionalPartida.isPresent()) {
            throw new RuntimeException("Partida no encontrada");
        }
        Partida partida = optionalPartida.get();
        Random random = new Random();
        int dado1 = random.nextInt(6)+1;
        int dado2 = random.nextInt(6)+1;
        int suma = dado1 + dado2;
        Tiro tiro = new Tiro();
        tiro.setPartida(partida);
        tiro.setPartida(partida);
        tiro.setValorDado1(dado1);
        tiro.setValorDado2(dado2);
        tiro.setSuma(suma);

        boolean ganador = false;
        if (suma == 7 || suma == 11){
            ganador = true;
            partida.setEstado(Estado.FINALIZADA);
            Jugador jugador1 = partida.getJugador();
            jugador1.setSaldo(jugador1.getSaldo()+partida.getApuesta()*2);
            jugadorRepository.save(jugador1);
        }else if (suma == 2 || suma == 12){
            partida.setEstado(Estado.FINALIZADA);
        }

        tiro.setEsGanador(ganador);
        tiroRepository.save(tiro);
        return tiro;
    }


}
