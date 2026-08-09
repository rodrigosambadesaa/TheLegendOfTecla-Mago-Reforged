package com.legendoftecla.model.characters;

import com.legendoftecla.exceptions.AccionInvalidaException;
import com.legendoftecla.model.items.Arma;
import com.legendoftecla.model.world.Posicion;

import java.util.ArrayList;

/** Combatiente resistente, especialmente eficaz a corta distancia. */
public final class Guerrero extends Jugador {
    public Guerrero(String nombre, Posicion posicion, Mochila mochila, int visionBase) {
        super(nombre, 120, 90, posicion, mochila, visionBase);
    }

    @Override
    protected int aplicarModificadorDanio(int base, Personaje objetivo) {
        int distancia = getPosicion().distanciaManhattan(objetivo.getPosicion());
        if (distancia <= 1) {
            return base * 2;
        }
        if (distancia > 2) {
            return Math.max(1, (int) Math.ceil(base * 0.05));
        }
        return base;
    }

    @Override
    public int estimarCosteMovimiento() {
        int coste = (int) Math.ceil(super.estimarCosteMovimiento() * 1.2);
        long armasDosManos = getArmasEquipadas().stream().filter(Arma::isDosManos).count();
        return armasDosManos >= 2 ? (int) Math.ceil(coste * 1.5) : coste;
    }

    @Override
    protected void equiparArma(Arma arma) throws AccionInvalidaException {
        long dosManos = getArmasEquipadas().stream().filter(Arma::isDosManos).count();
        if (arma.isDosManos() && dosManos >= 2) {
            throw new AccionInvalidaException("El guerrero ya lleva dos armas a dos manos.");
        }
        if (!arma.isDosManos()) {
            super.equiparArma(arma);
            return;
        }
        var nuevas = new ArrayList<>(getArmasEquipadas());
        nuevas.add(arma);
        setArmasEquipadas(nuevas);
    }
}
