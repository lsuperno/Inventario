package org.ayed.poe;

import java.util.Objects;

public class Item {
    private final int id;
    private final int ancho;
    private final int alto;

    /**
     * Constructor de un Item.
     *
     * @param id    El identificador único del item.
     * @param ancho El ancho del item (en celdas).
     * @param alto  La altura del item (en celdas).
     */
    public Item(int id, int ancho, int alto) {
        this.id = id;
        this.ancho = ancho;
        this.alto = alto;
    }

    /**
     * Constructor sin parámetros.
     * NOTA: No usar.
     */
    public Item() {
        this.id = 0;
        this.ancho = 0;
        this.alto = 0;
    }

    /**
     * Verifica la igualdad basándose en todos los atributos.
     * <p>
     * NOTA PARA LOS ESTUDIANTES: Si agregan nuevos atributos a esta clase (ej: nombre, rareza),
     * DEBEN actualizar este método y hashCode() para incluirlos, de lo contrario los tests
     * no verificarán sus datos correctamente.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                ancho == item.ancho &&
                alto == item.alto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ancho, alto);
    }
}
