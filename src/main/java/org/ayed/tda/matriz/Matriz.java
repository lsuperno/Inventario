package org.ayed.tda.matriz;

import org.ayed.poe.Item;

/**
 * Implementación de una matriz genérica de tamaño fijo.
 *
 * @param <T> Tipo de dato a almacenar en la matriz.
 */
public class Matriz<T> {
    /**
     * Constructor para matriz cuadrada inicializada con un valor.
     *
     * @param tamanio Dimensiones de la matriz (tamanio * tamanio). No puede ser menor que 1.
     * @param valor   Valor inicial para todas las celdas.
     * @throws IllegalArgumentException Si el tamaño es menor que 1.
     */
    @SuppressWarnings("unchecked")
    public Matriz(int tamanio, T valor) {
        // TODO: Completar.
    }

    /**
     * Constructor para matriz cuadrada vacía (inicializada en null).
     *
     * @param tamanio Dimensiones de la matriz (tamanio * tamanio). No puede ser menor que 1.
     * @throws IllegalArgumentException Si el tamaño es menor que 1.
     */
    @SuppressWarnings("unchecked")
    public Matriz(int tamanio) {
        // TODO: Completar.
    }

    /**
     * Constructor para matriz rectangular inicializada con un valor.
     *
     * @param filas    Cantidad de filas. No puede ser menor que 1.
     * @param columnas Cantidad de columnas. No puede ser menor que 1.
     * @param valor    Valor inicial para todas las celdas.
     * @throws IllegalArgumentException Si filas o columnas son menores que 1.
     */
    @SuppressWarnings("unchecked")
    public Matriz(int filas, int columnas, T valor) {
        // TODO: Completar.
    }

    /**
     * Constructor para matriz rectangular vacía (inicializada en null).
     *
     * @param filas    Cantidad de filas. No puede ser menor que 1.
     * @param columnas Cantidad de columnas. No puede ser menor que 1.
     * @throws IllegalArgumentException Si filas o columnas son menores que 1.
     */
    @SuppressWarnings("unchecked")
    public Matriz(int filas, int columnas) {
        // TODO: Completar.
    }

    /**
     * Constructor por copia.
     *
     * @param otra Matriz a copiar.
     * @throws IllegalArgumentException Si la matriz a copiar es nula.
     */
    @SuppressWarnings("unchecked")
    public Matriz(Matriz<T> otra) {
        // TODO: Completar.
    }

    /**
     * Obtiene el elemento en la posición indicada.
     * <p>
     * El índice debe ser válido (0 <= i < filas, 0 <= j < columnas).
     *
     * @param i Índice de la fila.
     * @param j Índice de la columna.
     * @return El elemento en (i, j).
     * @throws IndiceNoValidoException Si el índice está fuera de rango.
     */
    public T elemento(int i, int j) {
        // TODO: Completar.
        return (T) new Object();
    }

    /**
     * Asigna un valor en la posición indicada.
     * <p>
     * El índice debe ser válido (0 <= i < filas, 0 <= j < columnas).
     *
     * @param i     Índice de la fila.
     * @param j     Índice de la columna.
     * @param valor Valor a asignar.
     * @throws IndiceNoValidoException Si el índice está fuera de rango.
     */
    public void asignar(int i, int j, T valor) {
        // TODO: Completar.
    }

    /**
     * Devuelve la cantidad de filas de la matriz.
     *
     * @return Cantidad de filas.
     */
    public int filas() {
        // TODO: Completar.
        return 0;
    }

    /**
     * Devuelve la cantidad de columnas de la matriz.
     *
     * @return Cantidad de columnas.
     */
    public int columnas() {
        // TODO: Completar.
        return 0;
    }
}
