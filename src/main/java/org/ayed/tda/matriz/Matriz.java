package org.ayed.tda.matriz;

import org.ayed.poe.Item;

/**
 * Implementación de una matriz genérica de tamaño fijo.
 *
 * @param <T> Tipo de dato a almacenar en la matriz.
 */
public class Matriz<T> {
    private Object[][] casilleros;
    private int filas;
    private int columnas;

    /**
     * Constructor para matriz cuadrada inicializada con un valor.
     *
     * @param tamanio Dimensiones de la matriz (tamanio * tamanio). No puede ser menor que 1.
     * @param valor   Valor inicial para todas las celdas.
     * @throws IllegalArgumentException Si el tamaño es menor que 1.
     */
    @SuppressWarnings("unchecked")
    public Matriz(int tamanio, T valor) {
        this(tamanio, tamanio, valor);
    }

    /**
     * Constructor para matriz cuadrada vacía (inicializada en null).
     *
     * @param tamanio Dimensiones de la matriz (tamanio * tamanio). No puede ser menor que 1.
     * @throws IllegalArgumentException Si el tamaño es menor que 1.
     */
    @SuppressWarnings("unchecked")
    public Matriz(int tamanio) {
        this(tamanio, tamanio);
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
        this(filas, columnas);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                asignar(i, j, valor);
            }
        }
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
        if (filas < 1 || columnas <1) {
            throw new IllegalArgumentException("Filas o columnas tiene que ser mayor que 1");
        }
        this.filas = filas;
        this.columnas = columnas;
        this.casilleros = new Object[filas][columnas];
    }

    /**
     * Constructor por copia.
     *
     * @param otra Matriz a copiar.
     * @throws IllegalArgumentException Si la matriz a copiar es nula.
     */
    @SuppressWarnings("unchecked")
    public Matriz(Matriz<T> otra) {
        if (otra == null) {
            throw new IllegalArgumentException("Matriz a copiar no debe ser nula");
        }
        this.filas = otra.filas();
        this.columnas = otra.columnas();
        this.casilleros = new Object[this.filas][this.columnas];
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                this.casilleros[i][j] = otra.elemento(i, j);
            }
        }
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
        validarIndices(i, j);
        return (T) this.casilleros[i][j];
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
       validarIndices(i, j);
       this.casilleros[i][j] = valor;
    }

    /**
     * Devuelve la cantidad de filas de la matriz.
     *
     * @return Cantidad de filas.
     */
    public int filas() {
        return this.filas;
    }

    /**
     * Devuelve la cantidad de columnas de la matriz.
     *
     * @return Cantidad de columnas.
     */
    public int columnas() {
        return this.columnas;
    }

    /**
     * Verifica si los indices recividos son validos (0 <= i filas, 0 <= j < columnas de la matriz)
     *
     * @param i fila a verificar
     * @param j columna a verificar
     * @throws IndiceNoValidoException Si el índice está fuera de rango.
     */
    public void validarIndices(int i, int j) {
        if (i < 0 || i >= this.filas || j < 0 || j >= this.columnas) {
            throw new IndiceNoValidoException("Indice fuera de rango");
        }
    }
}
