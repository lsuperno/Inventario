package org.ayed.poe;

import org.ayed.tda.matriz.Matriz;
import org.ayed.tda.vector.VectorDinamico;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Inventario {

    private Matriz<Item> grilla;
    private VectorDinamico<Item> itemsGuardados;
    private int ancho;
    private int alto;

    /**
     * Constructor del Inventario.
     * <p>
     * Crea una grilla de inventario vacía con las dimensiones especificadas.
     *
     * @param ancho El ancho de la grilla del inventario.
     * @param alto  La altura de la grilla del inventario.
     * @throws IllegalArgumentException si el ancho o la altura son menores a 1.
     */
    public Inventario(int ancho, int alto) {
        if (ancho < 1 || alto < 1) {
            throw new IllegalArgumentException("Las dimensiones deben ser mayores a 0.");
        }
        this.ancho = ancho;
        this.alto = alto;
        this.grilla = new Matriz<>(alto, ancho);
        this.itemsGuardados = new VectorDinamico<>();
    }

    /**
     * Obtiene el item en una coordenada específica.
     *
     * @param x La coordenada x.
     * @param y La coordenada y.
     * @return El Item en la coordenada especificada, o null si la celda está vacía.
     * @throws InventarioFueraDeLimitesException Si x o y están fuera de las dimensiones de la grilla.
     */
    public Item obtenerItem(int x, int y) {
        if (x < 0 || x >= this.ancho || y < 0 || y >= this.alto) {
            throw new InventarioFueraDeLimitesException("Coordenada fuera de los límites de la grilla.");
        }
        return this.grilla.elemento(y, x);
    }

    /**
     * Coloca un item en una coordenada específica (x, y).
     * <p>
     * <b>Éxito:</b> El item es colocado. {@link #obtenerItem(int, int)} para cada coordenada dentro del
     * área del item devuelve el item colocado.
     *
     * @param x    La coordenada x de la esquina superior izquierda.
     * @param y    La coordenada y de la esquina superior izquierda.
     * @param item El item a colocar.
     * @throws InventarioFueraDeLimitesException Si el área del item cae parcial o totalmente fuera de la grilla.
     * @throws EspacioOcupadoException           Si una o más coordenadas requeridas por el área ya están ocupadas.
     * @throws ItemDuplicadoException            Si un item con el mismo ID ya existe en el inventario.
     */
    public void colocarItem(int x, int y, Item item) {
        if (existeItem(item.getId())) {
            throw new ItemDuplicadoException("Ya existe un ítem con el ID: " + item.getId());
        }

        if (x < 0 || y < 0 || x + item.getAncho() > this.ancho || y + item.getAlto() > this.alto) {
            throw new InventarioFueraDeLimitesException("El ítem excede los límites del inventario.");
        }

        if (!espacioLibre(x, y, item.getAncho(), item.getAlto())) {
            throw new EspacioOcupadoException("Hay otro ítem ocupando el espacio.");
        }
        item.setXancla(x);
        item.setYancla(y);
        for (int i = 0; i < item.getAlto(); i++) {
            for (int j = 0; j < item.getAncho(); j++) {
                this.grilla.asignar(y + i, x + j, item);
            }
        }
        this.itemsGuardados.agregar(item);
    }

    /**
     * Coloca automáticamente un item en el primer espacio disponible.
     * <p>
     * Escanea el inventario de izquierda a derecha y de arriba hacia abajo para encontrar el primer
     * bloque contiguo de celdas vacías para el item.
     * <p>
     * <b>Éxito:</b> El item es colocado en el primer espacio lógicamente disponible.
     *
     * @param item El item a colocar automáticamente.
     * @throws InventarioLlenoException Si el inventario no tiene un bloque contiguo de celdas vacías lo suficientemente grande.
     * @throws ItemDuplicadoException   Si un item con el mismo ID ya existe en el inventario.
     */
    public void colocarItem(Item item) {
        if (existeItem(item.getId())) {
            throw new ItemDuplicadoException("Ya existe un ítem con el ID: " + item.getId());
        }
        boolean colocado = false;
        for (int x = 0; x <= this.ancho - item.getAncho() && !colocado; x++) {
            for (int y = 0; y <= this.alto - item.getAlto() && !colocado; y++) {
                if (espacioLibre(x, y, item.getAncho(), item.getAlto())) {
                    colocarItem(x, y, item);
                    colocado = true;
                }
            }
        }
        if (!colocado) {
            throw new InventarioLlenoException("No hay espacio para colocar el item.");
        }
    }


    /**
     * Elimina un item por su ID.
     * <p>
     * <b>Éxito:</b> Elimina el item con el ID correspondiente. {@link #obtenerItem(int, int)} para todas las coordenadas que
     * el item ocupaba ahora devuelve null.
     *
     * @param id El ID del item a eliminar.
     * @return El Item eliminado.
     * @throws ItemNoEncontradoException Si no existe ningún item con el ID dado.
     */
    public Item eliminarItem(int id) {
        int indice = -1;
        boolean encontrado = false;
        for (int i = 0; i < this.itemsGuardados.tamanio() && !encontrado; i++) {
            if (this.itemsGuardados.obtener(i).getId() == id) {
                indice = i;
                encontrado = true;
            }
        }
        if (indice == -1) {
            throw new ItemNoEncontradoException("No se encontró un ítem con el ID: " + id);
        }
        Item item = this.itemsGuardados.eliminar(indice);
        for (int i = 0; i < item.getAlto(); i++) {
            for (int j = 0; j < item.getAncho(); j++) {
                this.grilla.asignar(item.getYancla() + i, item.getXancla() + j, null);
            }
        }

        item.setXancla(-1);
        item.setYancla(-1);
        return item;
    }

    /**
     * Elimina el item que ocupa una coordenada específica.
     * <p>
     * <b>Éxito:</b> Elimina el item que ocupa (x, y). {@link #obtenerItem(int, int)} para todas las coordenadas que
     * el item ocupaba ahora devuelve null.
     *
     * @param x La coordenada x.
     * @param y La coordenada y.
     * @return El Item eliminado.
     * @throws ItemNoEncontradoException         Si la coordenada (x, y) está actualmente vacía (null).
     * @throws InventarioFueraDeLimitesException Si la coordenada (x, y) consultada está fuera de la grilla.
     */
    public Item eliminarItem(int x, int y) {
        Item item = obtenerItem(x, y);
        if (item == null) {
            throw new ItemNoEncontradoException("El casillero está vacío.");
        }
        return eliminarItem(item.getId());
    }

    /**
     * Transfiere una lista de items al inventario como una única transacción atómica.
     * <p>
     * <b>Éxito:</b> El inventario tiene espacio suficiente para colocar secuencialmente cada item de la lista.
     * <p>
     * <b>Fallo:</b> El inventario debe revertirse totalmente a como estaba antes de llamar a este método.
     *
     * @param items La lista de items a transferir.
     * @throws InventarioLlenoException Si el inventario se queda sin espacio antes de que se procese toda la lista.
     * @throws ItemDuplicadoException   Si el ID de un item entrante ya existe en el inventario, o si la lista
     *                                  entrante contiene duplicados.
     */
    public void transferirItems(VectorDinamico<Item> items) {
        VectorDinamico<Integer> idsColocados = new VectorDinamico<>();
        try {
            for (int i = 0; i < items.tamanio(); i++) {
                Item item = items.obtener(i);
                colocarItem(item);
                idsColocados.agregar(item.getId());
            }
        } catch (RuntimeException e) {
            for (int i = 0; i < idsColocados.tamanio(); i++) {
                eliminarItem(idsColocados.obtener(i));
            }
            throw e;
        }
    }

    /**
     * Guarda el estado actual del inventario en un archivo.
     * <p>
     * El formato es específico de la implementación, pero debe soportar la reconstrucción completa del estado.
     * El formato es CSV ID, Ancho, Alto, Xancla, Yancla.
     *
     * @param ruta La ruta al archivo donde se guardará el inventario.
     */
    public void guardar(String ruta) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(ruta))) {
            for (int i = 0; i < this.itemsGuardados.tamanio(); i++) {
                Item item = this.itemsGuardados.obtener(i);
                // CSV: ID, Ancho, Alto, X, Y
                escritor.println(item.getId() + "," + item.getAncho() + "," +
                        item.getAlto() + "," + item.getXancla() + "," + item.getYancla());
            }
        } catch (Exception e) {
            System.out.println("Error al guardar la partida");
        }
    }

    /**
     * Carga el estado del inventario desde un archivo.
     * <p>
     * <b>Éxito:</b> El inventario se reconstruye por completo.
     * <p>
     * <b>Fallo:</b> El inventario permanece vacío.
     *
     * @param filepath La ruta al archivo a cargar.
     * @throws ArchivoNoExistenteException Si la ruta del archivo no existe.
     */
    public void cargar(String filepath) {
        File archivo = new File(filepath);
        if (!archivo.exists()) {
            throw new ArchivoNoExistenteException("El archivo no existe.");
        }
        this.grilla = new Matriz<>(this.alto, this.ancho);
        this.itemsGuardados = new VectorDinamico<>();

        try (Scanner lector = new Scanner(archivo)) {
            while (lector.hasNextLine()) {
                String linea = lector.nextLine().trim();
                if (!linea.isEmpty()) {
                    String[] datos = linea.split(",");
                    int id = Integer.parseInt(datos[0]);
                    int ancho = Integer.parseInt(datos[1]);
                    int alto = Integer.parseInt(datos[2]);
                    int x = Integer.parseInt(datos[3]);
                    int y = Integer.parseInt(datos[4]);
                    Item item = new Item(id, ancho, alto);
                    colocarItem(x, y, item);
                }
            }
        } catch (Exception e) {
            this.grilla = new Matriz<>(this.alto, this.ancho);
            this.itemsGuardados = new VectorDinamico<>();
            System.out.println("Error al cargar la partida");
        }
    }


    /**
     * Verifica si existe un item con el ID dado.
     * @param id ID del item a buscar
     * @return true si existe, false si no
     */
    private boolean existeItem(int id) {
        boolean existe = false;
        for (int i = 0; i < this.itemsGuardados.tamanio() && !existe; i++) {
            if (this.itemsGuardados.obtener(i).getId() == id) {
                existe = true;
            }
        }
        return existe;
    }


    /**
     * Verifica si hay espacio libre para un item
     * @param x coordenada x del item
     * @param y coordenada y del item
     * @param ancho ancho del item
     * @param alto alto del item
     * @return true si hay espacio libre, false si no
     */
    private boolean espacioLibre(int x, int y, int ancho, int alto) {
        boolean libre = true;
        for (int i = y; i < y + alto && libre; i++) {
            for (int j = x; j < x + ancho && libre; j++) {
                if (this.grilla.elemento(i, j) != null) {
                    libre = false;
                }
            }
        }
        return libre;
    }

    public String toString() {
        return this.grilla.toString();
    }
}