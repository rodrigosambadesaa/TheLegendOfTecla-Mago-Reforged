package com.legendoftecla.model.characters;

import com.legendoftecla.model.world.Posicion;

/** Especialista en pociones y explosivos. */
public final class Alquimista extends Jugador {
    public Alquimista(String nombre, Posicion posicion, Mochila mochila, int visionBase) {
        super(nombre, 105, 95, posicion, mochila, visionBase);
    }

    @Override
    protected int aplicarModificadorDanio(int base, Personaje objetivo) {
        int distancia = getPosicion().distanciaManhattan(objetivo.getPosicion());
        return distancia > 2 ? Math.max(1, (int) Math.ceil(base * 0.05)) : base;
    }
}
