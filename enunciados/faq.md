# Preguntas Frecuentes

## Práctica 1


### Al resetear el juego, ¿el comportamiento aleatorio tiene que ser igual que al principio?

Sí, es necesario reutilizar la semilla (seed), es decir, hay que volver a crear el objeto Random con la misma semilla (`this.random = new Random(this.seed)`).

### La salida por consola muestra caracteres extraños, ¿qué ocurre?

Lo más probable es que la codificación que esté usando Eclipse no sea UTF-8. Para cambiarla:
- Selecciona el proyecto y pulsa el botón derecho seleccionando la opción *Properties*. 
- Elige el menú *Resource* y  comprueba que el valor de *Text File Encoding* es *UTF-8*. 
- En caso contrario, selecciona dicha opción.


### Tengo un método en `UCMLaser` que recibe un `RegularAlien` como parámetro para que el láser ataque al alien. ¿Tengo que añadir otro método para `DestroyerAlien`?

Sí, este es uno de los problemas que se mencionan en el enunciado: en nuestra solución deberemos copiar y pegar mucho código. El láser ataca (de la misma forma) a aliens, destroyer aliens, bombas y ovnis, de modo que necesitaremos
sobrecargar ese método y acabar teniendo cuatro versiones suyas: una que reciba como parámetro un `RegularAlien`, otro que reciba un `DestroyerAlien`, otr que recibea una `Bomb` y una última que reciba un `Ufo`. Las implementaciones de esos
métodos posiblemente sean idénticas entre sí (salvo por el tipo del objeto atacado).

### En el método `run` de `Controller` lo que introduce el usuario es devuelto en minúsculas por `prompt()`. Si el usuario ha escrito *move left*, ¿tengo que pasarlo a mayúsculas para convertir la dirección a enumerado?

Efectivamente, para que la conversión de un string a un enumerado sea sencilla hay que pasar el string a mayúsculas. Dentro del caso para *move* puedes hacer algo parecido a lo siguiente:

```java
    String direction = parameters[1];
    Move move = Move.valueOf(direction.toUpperCase());
```

No hace falta, y de hecho está desaconsejado, hacer una distinción de casos sobre el string `direction` para dar valor al enumerado `move`.

## Práctica 2

### ¿Cómo hago el ShockWave si en la lista no sé el tipo de objeto que tengo?

Como se indica en el enunciado, el ShokWave es el único "power-up" que existe. Como es el único se ha decidido dejarlo en la jerarquía, aunque es el objeto más raro y además queda fatal en esta jerarquía de clases que tenemos, pues según la jerarquía tiene pos, movimiento, etc. que no tienen ningún sentido. Obviamente, al estar en la jerarquía tiene que ser capaz de responder a preguntas del tipo: isInPosition(..), automaticMove(), etc. Sus respuestas a casi todas ellas serán obvias: false para isInPosition(...), no hará nada para automaticMove(), etc.

Volviendo a la pregunta y tratando de explicar un poco su comportamiento, os planteo la pregunta ¿qué hace el ShockWave?

La respuesta es que el ShockWave elimina 1 de vida a todos los aliens y nadie lo ataca. De aquí se deduce que debe implementar el performAttack. Fijaros que ahora es importante que para decidir si atacas revises si el otro es capaza de recibir el ataque. Al ser un UCMWeapon  los demás podrán recibir o no su ataque según implementen el método de recibir el ataque de los UCMWeapon o no.  Por lo que cuando meta en la lista el ShockWave  se trata como un objeto más del contenedor (lista), se le pide que se mueva y se mira, al igual que al resto, si performAttack contra todos los objetos de la lista.

Ahora nos plantamos entonces: ¿quién lo genera?, y ¿cómo sabe que lo puede generar?

La UCMShip es la responsable de generarlo/lanzarlo, por lo que la nave debe saber de alguna manera que tiene ese poder o no lo tiene.

¿Cómo sabe que lo tiene? 

En el enunciado se indica claramente que se gana al matar al Ufo. Por lo que el Ufo es el responsable en su muerte de decirle al juego que se ha ganado el poder. El juego será el responsable de mandar ese mensaje a quien corresponda y él actuará en consecuencia.

De todo esto, espero que quede claro que el ShockWave, al igual que el resto de las armas, no tienen un booleano interno indicando si están activos o no. Si están en la lista es porque están actuando, si no es porque no están jugando actualmente. Obviamente, el ShockWave de la lista una vez que actúa ataca, quedará muerto y al igual que wl reato de objetos muertos será necesario hacerlos desaparece de la lista.
