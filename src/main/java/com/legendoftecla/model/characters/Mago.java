package com.legendoftecla.model.characters;

import com.legendoftecla.model.world.Posicion;

/** Especialista de largo alcance con visión mejorada. */
public final class Mago extends Jugador {
    public Mago(String nombre, Posicion posicion, Mochila mochila, int visionBase) {
        super(nombre, 100, 100, posicion, mochila, visionBase + 1);
    }

    @Override
    protected int aplicarModificadorDanio(int base, Personaje objetivo) {
        int distancia = Math.max(1, getPosicion().distanciaManhattan(objetivo.getPosicion()));
        return (int) Math.ceil(base + Math.pow(distancia, 1.2));
    }

    @Override
    public int estimarCosteMovimiento() {
        return Math.max(1, super.estimarCosteMovimiento() - 1);
    }
}
