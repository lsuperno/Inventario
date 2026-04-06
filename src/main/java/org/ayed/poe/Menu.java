package org.ayed.poe;

import org.ayed.tda.vector.VectorDinamico;
import java.util.Scanner;

/**
 * Clase que representa el menu principal del juego.
 */
public class Menu {
    private Inventario inventario;
    private Scanner lector;
    private Item[] catalogo;

    public Menu() {
        this.inventario = new Inventario(10, 10);
        this.lector = new Scanner(System.in);
        this.catalogo = new Item[10];
        inicializarCatalogo();
    }


    /**
     * Inicializa el catalogo con algunos items de ejemplo.
     */
    private void inicializarCatalogo() {
        this.catalogo[0] = new Item(1, 1, 1);
        this.catalogo[1] = new Item(2, 1, 2);
        this.catalogo[2] = new Item(3, 1, 3);
        this.catalogo[3] = new Item(4, 2, 2);
        this.catalogo[4] = new Item(5, 2, 3);
        this.catalogo[5] = new Item(6, 4, 4);
        this.catalogo[6] = new Item(7, 2, 1);
        this.catalogo[7] = new Item(8, 1, 1);
        this.catalogo[8] = new Item(9, 3, 2);
        this.catalogo[9] = new Item(10, 2, 2);
    }

    public void iniciar() {
        boolean salir = false;
        int opcion;
        while (!salir) {
            mostrarOpciones();
            System.out.print("Elige una opción: ");
            String entrada = lector.nextLine();

            try {
                opcion = Integer.parseInt(entrada);
                switch (opcion) {
                    case 1:
                        System.out.println("\n" + this.inventario.toString());
                        break;
                    case 2:
                        imprimirCatalogo();
                        break;
                    case 3:
                        colocarItemAuto();
                        break;
                    case 4:
                        colocarItemManual();
                        break;
                    case 5:
                        eliminarItemPorId();
                        break;
                    case 6:
                        eliminarItemPorCasillero();
                        break;
                    case 7:
                        transferenciaMasiva();
                        break;
                    case 8:
                        guardarPartida();
                        break;
                    case 9:
                        cargarPartida();
                        break;
                    case 10:
                        salir = true;
                        System.out.println("Cerrando el inventario...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingresa un número válido.");
            } catch (Exception e) {
                System.out.println("Error--> " + e.getMessage());
            }
        }
    }


    /**
     * Imprime las opciones del menu
     */
    private void mostrarOpciones() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Ver mi Inventario");
        System.out.println("2. Ver Catálogo de Ítems");
        System.out.println("3. Colocar un Ítem (Automático)");
        System.out.println("4. Colocar un Ítem (Elegir Coordenadas)");
        System.out.println("5. Eliminar un Ítem (Por ID)");
        System.out.println("6. Eliminar un Ítem (Por Casillero)");
        System.out.println("7. Probar Transferencia Masiva (Lote de 4 ítems)");
        System.out.println("8. Guardar Partida");
        System.out.println("9. Cargar Partida");
        System.out.println("10. Salir");
    }


    /**
     * Imprime el catalogo de items con la informacion de cada uno
     */
    private void imprimirCatalogo() {
        System.out.println("\n--- Catálogo de Objetos ---");
        for (int i = 0; i < this.catalogo.length; i++) {
            Item it = this.catalogo[i];
            System.out.println("ID: " + it.getId() + " | Tamaño: " + it.getAncho() + "x" + it.getAlto());
        }
    }


    /**
     * Coloca un item automaticamente en el inventario. En el primer espacio disponible.
     * Si no hay espacio, se informa y no se coloca nada
     */
    private void colocarItemAuto() {
        System.out.print("Ingresa el ID del ítem que quieres colocar: ");
        int idElegido = Integer.parseInt(lector.nextLine());
        Item itemOriginal = encontrarIdEnCatalogo(idElegido);
        if (itemOriginal == null) {
            System.out.println("El ID ingresado no existe en el catálogo.");
        } else {
            Item itemParaColocar = new Item(itemOriginal.getId(), itemOriginal.getAncho(), itemOriginal.getAlto());
            this.inventario.colocarItem(itemParaColocar);
            System.out.println("Item " + idElegido + " colocado automáticamente con exito!");
        }
    }


    /**
     * Se le pide al usuario que ingrese el id del item y las coordenadas x e y en las que quiere colocar el item.
     * Si el item no existe, se le informa. Si el item existe, se coloca y se informa.
     */
    private void colocarItemManual() {
        System.out.print("Ingresa el ID del ítem del catálogo (1 al 10): ");
        int idElegido = Integer.parseInt(lector.nextLine());
        System.out.print("Ingresa la coordenada X (Columna): ");
        int x = Integer.parseInt(lector.nextLine());
        System.out.print("Ingresa la coordenada Y (Fila): ");
        int y = Integer.parseInt(lector.nextLine());
        Item itemOriginal = encontrarIdEnCatalogo(idElegido);
        if (itemOriginal == null) {
            System.out.println("El ID ingresado no existe en el catálogo.");
        } else {
            Item itemParaColocar = new Item(itemOriginal.getId(), itemOriginal.getAncho(), itemOriginal.getAlto());
            this.inventario.colocarItem(x, y, itemParaColocar);
            System.out.println("Item " + idElegido + " colocado con éxito en la posición (" + x + "," + y + ")!");
        }
    }


    /**
     * Se le pide al usuario que ingrese el ID del item que quiere eliminar. Si el item existe
     * se elimina y se informa. En el caso de que algo salga mal, se informa.
     */
    private void eliminarItemPorId() {
        System.out.print("Ingresa el ID del item que quieres eliminar: ");
        int id = Integer.parseInt(lector.nextLine());
        Item itemEliminado = this.inventario.eliminarItem(id);
        System.out.println("El item " + itemEliminado.getId() + " ha sido eliminado correctamente!");
    }


    /**
     * Se le pide al usuario que ingrese las coordenadas x e y del item que quiere eliminar.
     * Si el item no existe, se le informa. Si el item existe, se elimina y se informa.
     */
    private void eliminarItemPorCasillero() {
        System.out.print("Ingresa la coordenada X a vaciar: ");
        int x = Integer.parseInt(lector.nextLine());

        System.out.print("Ingresa la coordenada Y a vaciar: ");
        int y = Integer.parseInt(lector.nextLine());

        Item itemEliminado = this.inventario.eliminarItem(x, y);
        System.out.println("El item " + itemEliminado.getId() + " que ocupaba esa posición fue eliminado!");
    }


    /**
     * Transfiere 4 items todos juntos de manera automatica, si el inventario tiene espacio suficiente.
     * En el caso de que falle algo, ya sea un item duplicado o que no hay espacio suficiente no se transfiere nada.
     */
    private void transferenciaMasiva() {
        System.out.println("\nTransfiriendo 4 items a la vez...");
        VectorDinamico<Item> lote = new VectorDinamico<>();
        lote.agregar(new Item(11, 1, 1));
        lote.agregar(new Item(12, 1, 2));
        lote.agregar(new Item(13, 2, 2));
        lote.agregar(new Item(14, 3, 1));
        this.inventario.transferirItems(lote);
        System.out.println("Transferencia masiva completada con exito!");
    }


    /**
     * Se le pide al usuario un nombre de archivo y se guarda el estado actual del inventario en ese archivo.
     * como csv de la forma ID, Ancho, Alto, X, Y
     */
    private void guardarPartida() {
        System.out.print("Ingresa el nombre del archivo para guardar la partida: ");
        String ruta = lector.nextLine();
        this.inventario.guardar(ruta);
        System.out.println("Inventario guardado correctamente!");
    }


    /**
     * Se le pide al usuario una ruta de archivo y si existe se carga la partida, sino se crea una nueva.
     */
    private void cargarPartida() {
        System.out.print("Ingresa la ruta del archivo a cargar: ");
        String ruta = lector.nextLine();
        this.inventario.cargar(ruta);
        System.out.println("Partida cargada!");
    }


    /**
     * Busca un item en el catalogo por su ID y si existe lo devuelve, sino devuelve null.
     * @param idElegido ID del item a buscar
     * @return Item con el ID dado, o null si no existe
     */
    private Item encontrarIdEnCatalogo(int idElegido) {
        boolean encontrado = false;
        Item itemOriginal = null;
        for (int i = 0; i < this.catalogo.length && !encontrado; i++) {
            if (this.catalogo[i].getId() == idElegido) {
                itemOriginal = this.catalogo[i];
                encontrado = true;
            }
        }
        return itemOriginal;
    }
}