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
        this.datos = (T[]) new Object[CAPACIDAD_INICIAL];
        this.cantidadElementos = 0;
    }


    /**
     * Constructor de copia de Vector.
     *
     * @param otro Vector a copiar.
     * @throws IllegalArgumentException si el vector a copiar es nulo.
     */
    @SuppressWarnings("unchecked")
    public VectorDinamico(VectorDinamico<T> otro) {
        if (otro == null) {
            throw new IllegalArgumentException("El vector a copiar no puede ser nulo");
        }
        this.cantidadElementos = otro.cantidadElementos;
        this.datos = (T[]) new Object[otro.capacidad()];
        System.arraycopy(otro.datos, 0, this.datos, 0, this.cantidadElementos);
    }


    /**
     * Agrega un dato al final del vector.
     * <p>
     * Si el vector está lleno, aumenta su capacidad.
     *
     * @param dato Dato a agregar.
     */
    public void agregar(T dato) {
        agrandarSiNecesario();
        this.datos[this.cantidadElementos] = dato;
        this.cantidadElementos++;
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
        validarIndiceInsercion(indice);
        agrandarSiNecesario();
        moverValoresAdelante(indice);
        this.datos[indice] = dato;
        this.cantidadElementos++;
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
        if (this.vacio()) {
            throw new VectorVacioException("El vector esta vacio, no se puede eliminar ningun dato.");
        }
        T datoEliminado = this.datos[this.cantidadElementos - 1];
        this.datos[this.cantidadElementos - 1] = null;
        this.cantidadElementos--;
        achicarSiEsNecesario();
        return datoEliminado;
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
        validarIndiceAcceso(indice);
        T datoEliminado = this.datos[indice];
        moverValoresAtras(indice);
        this.cantidadElementos--;
        this.datos[this.cantidadElementos] = null;
        achicarSiEsNecesario();
        return datoEliminado;
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
        validarIndiceAcceso(indice);
        return this.datos[indice];
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
        validarIndiceAcceso(indice);
        this.datos[indice] = dato;
    }


    /**
     * Obtiene el tamaño lógico del vector (cantidad de elementos almacenados).
     *
     * @return el tamaño del vector.
     */
    public int tamanio() {
        return this.cantidadElementos;
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
        return this.cantidadElementos == 0;
    }


    /**
     * Valida si el indice es valido (indice < 0 y indice >= cantidadElementos), en el caso de que no lo sea
     * llama una excepcion
     * @param indice indice a validar
     * @throws IndiceFueraDeRangoException si el indice esta fuera de rango
     */
    private void validarIndiceAcceso(int indice) {
        if (indice < 0 || indice >= this.cantidadElementos) {
            throw new IndiceFueraDeRangoException("Indice fuera de rango");
        }
    }


    /**
     * Valida si el indice es valido (indice < 0 y indice > cantidadElementos), en el caso de que no lo sea
     * llama una excepcion
     * @param indice indice a validar
     * @throws IndiceFueraDeRangoException si el indice esta fuera de rango
     */
    private void validarIndiceInsercion(int indice) {
        if (indice < 0 || indice > this.cantidadElementos) {
            throw new IndiceFueraDeRangoException("Indice fuera de rango");
        }
    }


    /**
     * Aumenta la capacidad del vector si es necesario, copiando los datos a un nuevo vector con una capacidad doble.
     * Esto se hace cuando el vector se llena
     */
    private void agrandarSiNecesario() {
        if (this.cantidadElementos == this.datos.length) {
            if (this.datos.length == 0) {
                this.datos = (T[]) new Object[4];
            } else {
                T[] nuevoArreglo = (T[]) new Object[this.cantidadElementos * 2];
                System.arraycopy(this.datos, 0, nuevoArreglo, 0, this.cantidadElementos);
                this.datos = nuevoArreglo;
            }
        }
    }


    /**
     * Reduce la capacidad del vector si es necesario, copiando los datos a un nuevo vector con la mitad de capacidad.
     * Esto se hace cuando la cantidad de elementos del vector es la mitad de la capacidad
     */
    private void achicarSiEsNecesario() {
        if (this.cantidadElementos * 2 <= this.datos.length) {
            T[] nuevoArreglo = (T[]) new Object[this.cantidadElementos];
            System.arraycopy(this.datos, 0, nuevoArreglo, 0, this.cantidadElementos);
            this.datos = nuevoArreglo;
        }
    }


    /**
     * Mueve los valores de un vector hacia adelante partiendo del indice recibido
     * @param desde donde empieza a mover los valores
     */
    private void moverValoresAdelante(int desde) {
        for (int i = this.cantidadElementos - 1; i >= desde; i--) {
            this.datos[i + 1] = this.datos[i];
        }
    }


    /**
     * Mueve los valores de un vector hacia atras partiendo del indice recibido
     * @param desde donde empieza a mover los valores
     */
    private void moverValoresAtras(int desde) {
        for (int i = desde; i < this.cantidadElementos - 1; i++) {
            this.datos[i] = this.datos[i + 1];
        }
    }
}
