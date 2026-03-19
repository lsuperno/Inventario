package org.ayed.tda.vector;

/**
 * Implementación de un Vector Dinámico.
 * <p>
 * Esta estructura de datos crece automáticamente cuando se llena y libera memoria
 * cuando se vacía significativamente. Permite acceso aleatorio, inserción y
 * eliminación de elementos.
 * <p>
 * Los métodos que modifican la estructura del vector (como {@link #agregar(Object, int)} y
 * {@link #eliminar(int)}) desplazan los elementos según sea necesario.
 *
 * @param <T> Tipo de dato a almacenar en el vector.
 */
public class VectorDinamico<T> {
    // La capacidad inicial NO se puede cambiar.
    private static final int CAPACIDAD_INICIAL = 0;
    private T[] datos;
    private int cantidadElementos;

    /**
     * Constructor de Vector.
     * Inicializa un vector con capacidad inicial 0.
     */
    @SuppressWarnings("unchecked")
    public VectorDinamico() {
        // TODO: Completar.
    }

    /**
     * Constructor de copia de Vector.
     *
     * @param otro Vector a copiar.
     * @throws IllegalArgumentException si el vector a copiar es nulo.
     */
    @SuppressWarnings("unchecked")
    public VectorDinamico(VectorDinamico<T> otro) {
        // TODO: Completar.
    }

    /**
     * Agrega un dato al final del vector.
     * <p>
     * Si el vector está lleno, aumenta su capacidad.
     *
     * @param dato Dato a agregar.
     */
    public void agregar(T dato) {
        // TODO: Completar.
    }

    /**
     * Agrega un dato al vector en el índice indicado, desplazando los elementos posteriores.
     * <p>
     * Ejemplo:
     * <pre>
     * {@code
     * Vector<Integer> v = new Vector<>();
     * v.agregar(1); v.agregar(3); // [1, 3]
     * v.agregar(2, 1);            // [1, 2, 3]
     * }
     * </pre>
     *
     * @param dato   Dato a agregar.
     * @param indice Índice en el que se inserta el dato.
     *               Debe estar entre 0 y el tamaño lógico del vector (inclusive).
     * @throws IndiceFueraDeRangoException si el índice es menor a 0 o mayor que el tamaño lógico.
     */
    public void agregar(T dato, int indice) {
        // TODO: Completar.
    }

    /**
     * Elimina el último dato del vector.
     * <p>
     * Reduce la capacidad del vector si es necesario, dependiendo de la estrategia.
     *
     * @return el dato eliminado.
     * @throws VectorVacioException si el vector está vacío.
     */
    public T eliminar() {
        // TODO: Completar.
        return (T) new Object();
    }

    /**
     * Elimina el dato del vector en el índice indicado, desplazando los elementos posteriores.
     * <p>
     * Ejemplo:
     * <pre>
     * {@code
     * Vector<Integer> v = ...; // [1, 2, 3]
     * v.eliminar(1);           // [1, 3]
     * }
     * </pre>
     *
     * @param indice Índice del dato a eliminar.
     *               Debe estar entre 0 y tamaño lógico - 1.
     * @return el dato eliminado.
     * @throws VectorVacioException        si el vector está vacío.
     * @throws IndiceFueraDeRangoException si el índice es menor a 0 o mayor o igual al tamaño lógico.
     */
    public T eliminar(int indice) {
        // TODO: Completar.
        return (T) new Object();
    }

    /**
     * Obtiene el dato del vector en el índice indicado.
     *
     * @param indice Índice del dato a obtener.
     *               Debe estar entre 0 y tamaño lógico - 1.
     * @return el dato en el índice indicado.
     * @throws IndiceFueraDeRangoException si el índice es menor a 0 o mayor o igual al tamaño lógico.
     */
    public T obtener(int indice) {
        // TODO: Completar.
        return (T) new Object();
    }

    /**
     * Modifica el dato del vector en el índice indicado.
     *
     * @param indice Índice del dato a modificar.
     *               Debe estar entre 0 y tamaño lógico - 1.
     * @param dato   Nuevo dato.
     * @throws IndiceFueraDeRangoException si el índice es menor a 0 o mayor o igual al tamaño lógico.
     */
    public void cambiar(int indice, T dato) {
        // TODO: Completar.
    }

    /**
     * Obtiene el tamaño lógico del vector (cantidad de elementos almacenados).
     *
     * @return el tamaño del vector.
     */
    public int tamanio() {
        // TODO: Completar.
        return 0;
    }

    /**
     * Obtiene el tamaño físico actual del vector (capacidad).
     * <p>
     * NOTA: Este método es únicamente para probar el comportamiento dinámico.
     *
     * @return la capacidad actual del vector.
     */
    public int capacidad() {
        return datos.length;
    }

    /**
     * Evalúa si el vector está vacío.
     *
     * @return true si el vector está vacío.
     */
    public boolean vacio() {
        // TODO: Completar.
        return false;
    }
}
