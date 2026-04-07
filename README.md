[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/l4EfGUme)
# TPI - 1c2026 - El Inventario

<p align="center">
   <img src="TPI-1c2026.png" alt="TPI-1c2026"><br>
</p>

## Video de demostración:

Usar mail fiuba
https://drive.google.com/drive/folders/1eOThz13Fk2ycrQlvNy6nIRKV0woB8bCb?usp=sharing

## Configuración del proyecto

El proyecto está configurado usando [Maven](https://maven.apache.org/),
y puede ser compilado, empaquetado y probado fácilmente usando los siguientes comandos:

### Compilar

```
mvn compile
```

### Empaquetar

```
mvn package
```

### Pruebas

```
mvn test
```

## Aclaraciones para el corrector

-Se añadieron los atributos xancla y yancla a la clase Item para registrar su coordenada de origen (esquina superior izquierda). Esto permite una eliminación más directa de los casilleros, sin tener que recorrer la matriz entera buscando en qué casilleros se encontraba.

-Se creó la clase Menu para manejar la interfaz de usuario y las entradas/salidas por consola.

-Para el inventario se utilizó la clase Matriz.

-Se usó un VectorDinamico auxiliar para mantener una lista de los ítems que están actualmente en uso. Esto se hizo para que cuando quieras eliminar ítems no tengas que iterar la matriz entera. También ayuda para la hora de guardar la partida por lo mismo.

---

## Licencia y Propiedad Intelectual

Todo el código producido por los estudiantes debe ser de código abierto bajo la **Licencia MIT**.

**Aviso Legal:** Este proyecto es un trabajo práctico educativo, **no comercial**. Todos los recursos visuales (sprites,
iconos, nombres de items) son propiedad intelectual de **Grinding Gear Games** y se utilizan en este repositorio
únicamente con fines educativos y de aprendizaje sobre estructuras de datos y algoritmos.
