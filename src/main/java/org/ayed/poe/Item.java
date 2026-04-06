package org.ayed.poe;

import java.util.Objects;

public class Item {
    private final int id;
    private final int ancho;
    private final int alto;
    private int xancla;
    private int yancla;

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
                alto == item.alto &&
                xancla == item.xancla &&
                yancla == item.yancla;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ancho, alto, xancla, yancla);
    }


    /**
     * devuelve el id del item
     * @return id del item
     */
    public int getId() {
        return id;
    }


    /**
     * devuelve el ancho del item
     * @return ancho del item
     */
    public int getAncho() {
        return ancho;
    }


    /**
     * devuelve la altura del item
     * @return altura del item
     */
    public int getAlto() {
        return alto;
    }


    /**
     * devuelve la xancla del item (superior izquierda)
     * @return xancla del item
     */
    public int getXancla() {
        return xancla;
    }


    /**
     * Le asigna un valor a la yancla del item (superior izquierda)
     * @param xancla xancla del item
     */
    public void setXancla(int xancla) {
        this.xancla = xancla;
    }


    /**
     * devuelve la yancla del item (superior izquierda)
     * @return yancla del item
     */
    public int getYancla() {
        return yancla;
    }


    /**
     * Le asigna un valor a la yancla del item (superior izquierda)
     * @param yancla yancla del item
     */
    public void setYancla(int yancla) {
        this.yancla = yancla;
    }


    /**
     * Convierte el objeto en una cadena de texto.
     * @return el id del item en formato String
     */
    public String toString() {
        return String.valueOf(this.id);
    }
}