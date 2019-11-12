# Alpaca_Emblem 2.5

El proyecto se encuentra en su versión 2.5 acorde a los requisitos de la entrega n°2.

En esta entrega se diseñaron las clases **GameController** y **Tactician**, junto con otras clases creadas para los patrones de diseño, como factories y handlers/listeners. Se repetirán secciones de la entrega anterior para mostrar las updates de cada clase.

## Tactician

Representa aun jugador, guardando los datos de este y siendo el intermediario entre el **Game Controller** y el resto del modelo (**model**). El Tactician tiene los siguientes atributos privados:

- **Nombre**: _name_ es un _final String_ que corresponde al nombre del Tactician del jugador.
- **Unidades**: _units_ es un _ArrayList_ que guarda las unidades del Tactican del jugador.
- **Unidades desplazadas**: _movedUnits_ es un _ArrayList_ que guarda las unidades del Tactican desplazadas en ese turno por el jugador.
- **Unidad seleccionada**: _selectedUnit_ es un _IUnit_ con la Unidad seleccionada del Tactician del jugador
- **Item seleccionado**:_selectedItem_ es un _IEquipableItem_ con el item seleccionado del Tactician del jugador
- **Support**: El _PropertyChangeSupport_ que será utilizado cuando el Tactician pierda el juego.
 
El tactician puede realizar lo siguiente: agregarse unidades, seleccionar unidades y guardarles o equiparles items, puede ver sus unidades, sus atributos y los de sus items, tambien intercambiar objetos entre ellas así como usar el item equipado en otras unidades. Puede mover sus unidades una vez por turno, por lo que se aplicó como estrategía que cada vez que el Tactician mueve una unidad, este lo guarda en _movedUnits_. Tambien puede remover totalmente sus unidades si el _HP_ de estas llega a 0 y notificar que ha perdido al controler si alguno de sus _Hero_'s muere o se queda sin unidades (hará uso del _Support_ para avisar al _GameController_).

## Game Controller

Maneja todo los inputs dados por el jugador. Y tiene los siguientes atributos:

- **Tacticians**: es un _ArrayList_ con los _tacticians_ en el juego (depende de cuantos jugadores se quieran en el juego), luego de crearse cada tactician se le suscribirá a un _support_.
- **Secuencia**:  es un _ArrayList_ con los _tacticians_ en el juego, pero reordenados según la seed y otras condiciones.
- **Mapa**: es un _Field_, el mapa donde se desenvolverá el juego (random según la seed), depende del tamaño que se quiera.
- **Seed**: es un _long_ random, que se usará com semilla (para crear otra semilla) para la creación del Mapa y la Secuencia.
- **Ronda**: es un _int_ que corresponde al número de la ronda actual.
- **Rondas máximas**: es un _int_ que corresponde al número máximo de rondas en el juego actual, una vez la ronda llegue a este número el juego acaba.
- **Unidades máximas**: Restricción ideada de forma personal, depende de la cantidad de jugadores y el tamaño del mapa.

El Game Controller puede inicializar un juego (con limite o sin limite), inicializar un juego generará una **Secuencia**, setea el _roundNumber_ en 1 y asigna como _turnOwner_ al primero en la **Secuencia**, tambien puede terminar un turno, con esto el siguiente _turnOwner_ será el siguiente en la **Secuencia** y de haberse acabado esta se generará otra (imposibilitando que el que recién jugo juegue en el primer turno de la nueva ronda) y se sumará 1 a _roundNumber_. 

El Game Controller puede remover _tactician_'s del juego si estos llegan a declarar que perdieron, si se encuentran en su turno se pasará al siguiente jugador. Además puede declarar los ganadores del juego.

Como se dijo antes, el GameController recibe todos los inputs, por lo que tiene muchos métodos que adaptan el input para que sean entendibles para el **Tactician** (como los que tienen que ver con el mapa, el GameController recibe las coordenadas y le da al Tactician el _Location_) y otros métodos que simplemente deliveran la tarea al Tactician.

## Unidades:

Ahora también tienen una referencia al **Tactician** que les posee.

Esta entrega generó un problema respecto al combate, el GameController y el Tactician tienen un metodo que permite usar el item seleccionado en otra unidad, pero estos no tienen restricciones ya que permiten atacar a una unidad propia o curar a una unidad enemiga. por lo que, para arreglar esto, el Tactician no hará uso del metodo de combate directamente, ahora las unidades tienen un método useItemOn que desambiguará si la unidad es enemiga o no y veráa si activa el combate dependiendo de eso.

Además ahora si una unidad muere en combate, está le enviará un mensaje al tactician para ser borrada de entre sus unidades y tambien se borrará del mapa. Existe el caso especial del _Hero_ si un Hero Muere será un Game Over inmediato para el tactician. 

## Mapa

Como el mapa se crea desde el GameController y depende de la seed de este se definieron sus constructores: el que no recibe parametros y el que si, este recibe el tamaño del mapa y una seed y crea un mapa cuadrado a partir de esta. 

## Factories

Hay dos tipos de factories:

1. Unit Factory: Hay 9 de estas (Los sorcerer son un caso especial por lo que se prefirió dejarlos en 3 factories distintos) donde cada factory permite retornar una unidad estandar tanto con un item (correspondiente) equipado o no (1 método para c/ acción).
2. Item Factory: Hay 8 de estos y simplemente retornan un item estandar.

# Alpaca_Emblem 1.5

El proyecto se encuentra en su versión 1.5 acorde a los requisitos de la entrega n°1.

En las secciones **Unidades** e **Items** se explicán algunas implementaciones, extensiones que se le hicieron al código dado, la visibilidad de parametros, etc. En **Equipamiento e intercambio de items** y **Combate** se explica en especifico los requerimientos puros y duros de la entrega.

## Unidades: 
Las unidades son objetos de una clase que extiende a la clase abstracta _AbstractUnit_ que a su vez implementa a la interfaz _IUnit_.
- Cada unidad tiene los siguientes atributos privados en la clase abstracta:
  1. **Hitpoints:** _currentHitPoints_ es un _double_ y representa la vida actual de una unidad y _maxHitpoints_ es un _final int_ y representa la cantidad máxima de vida de una unidad.
  2. **Movement:** _movement_ es un _ final int_ que represena la cantidad maxima de celdas que una unidad se puede mover.
  3. **Location:** _location_ es un objeto de la clase _Location_ y representa
la locación actual de un personaje.
  4. **Items:** los items son objetos de la "clase" _IEquipableItem_ (se explicará mas tarde) _maxItems_ es un _final int_ que representa la cantidad maxima de items que una unidad puede portar, _items_ es una _ArrayList_ de los items que la unidad porta y _equipedItem_ es el item que la unidad tiene equipado.

- El juego cuenta con 7 Unidades:
  1. ***Archer:*** Solo puede equiparse _Bow_'s.
  2. ***Fighter:*** Solo puede equiparse _Axe_'s.
  3. ***Sword Master:*** Solo puede equiparse _Sword_'s.
  4. ***Alpaca:*** Puede portar una cantidad ilimitada de items pero equiparse ninguno.
  5. ***Cleric:*** Solo puede equiparse _Staff_'s y no puede atacar.
  6. ***Hero:*** Solo puede equiparse _Spear_'s.
  7. ***Sorcerer:*** Solo puede equiparse _LightBooks_'s, _DarknessBooks_'s 
  y _AnimaBooks_'s.
  
A excepción de la _Alpaca_ cada unidad pude portar como máximo 3 items y de estos 
y de estos 3 items solo puede equiparse 1.
  
## Items: 

Los items son objetos de una clase que (dependiendo de si es un item magico o no) extiende a la clase abstracta _AbstractMagicItem_ o _AbstractNonMagicItem_, estas extienden a la clase abstracta _AbstractItem_ que a su vez implementa a la interfaz _IEquipableItem_.

- Cada item tiene los siguientes atributos privados:
  1. **Nombre:** _name_ es un _final String_ y es el nombre del item.
  2. **Poder:** _power_ es un _final int_ y representa el poder del item, pregunta ¿Por qué los puntos de vida de las unidades son _double_ cuanto el poder de los items son _final int_? el efecto que producen las armas se puede ver potenciado o disminuido bajo ciertas condiciones que se verán más adelante en las secciones ***Fortalezas y Debilidades*** y ***Combate***.
  3. **Dueño:** _owner_ es efectivamente la unidad que tiene equipado el item.

- Cada item tiene los siguientes atributos protegidos:
  1. **Rango mínimo:** _minRange_ es un _int_ que representa la cantidad mínima de celdas que tiene que estar un adversario para ser atacado por la unidad que equipa el item. Por defecto es 1, en casos especiales como el _Bow_ este es 2.-  
  2. **Rango máximo:** _maxRange_ es un _int_ que representa la cantidad máxima de celdas que tiene que estar un adversario para ser atacado por la unidad que equipa el item. tiene que ser estrictamente mayor a _minRange_.
  
- Existen dos clases de items, mágicos y no mágicos y su comportamiento se diferencia principalmente al momento de un combate, esto se explicará en la sección ***Fortalezas y Debilidades***. 
  - Items Mágicos: Son principalmente los libros
   1. **Libros de Luz:** _LightBook_
   2. **Libros de Oscuridad:** _DarknessBook_
   3. **Libros de Ánimas:** _AnimaBook_
  
  - Items no Mágicos:
   1. **Hacha:** _Axe_
   2. **Arco:** _Bow_ (tiene rango minimo 2)
   3. **Lanza:** _Spear_
   4. **Bastón:** _Staff_ (no ataca, cura)
   5. **Espada:** _Sword_
    
## Fortalezas y Debilidades

Algunos items son fuertes y/o debiles contra otros, que sean fuertes implica que cada vez que hacen daño a una unidad esta se potencia por un factor de 1.5 y si son débiles el daño disminuye en 20 o en su defecto se reduce a 0. Los items mágicos son fuertes contra los items no mágicos y a su vez, los items no mágicos son fuertes contra los items mágicos, por lo que cada vez que se ataquen una a la otra el da{o se verá potenciadoen un factor de 1.5.

Entre items de la misma clase tambien hay fortalezas y débilidades y se verán en la siguientes tablas:

|Item|Débil contra|Fuerte contra|
|:---|   :---:    |    :---:    |
|Hacha|Espada|Lanza|
|Espada|Lanza|Hacha|
|Lanza|Hacha|Espada|

|Item|Débil contra|Fuerte contra|
|:---|   :---:    |    :---:    |
|Ánima|Oscuridad|Luz|
|Oscuridad|Luz|Ánima|
|Luz|Ánima|Oscuridad|

## Mapa
No se hicieron cambios en la implementación de mapa.

## Equipamiento e intercambio de items

Para poder equipar un item a una unidad se tienen que cumplir como condición: la unidad porta el item en su lista de items y el item debe ser del tipo apto para la unidad. Para implemantar esto se utilizó la estrategia de _Double Dispatch_, la unidad no sabe que items puede equiparse, el item lo decidirá por él. Por ejemplo tenemos una unidad  _unit_ si hacemos _equipItem(item)_ se enviará un mensaje al _item_, este item sabrá que hacer para cada tipo de unidad, si _unit_ e _item_ son compatibles _item_ se asignará como _owner_ a _unit_ y _unit_ asignará a _item_ como _equippedItem_ por el contrario, si _item_ no tiene instrucciones para equiparse a _unit_ no será equipado.

Para el intercambio basta una estrategia simple. Tenemos dos unidades _unit1_ y _unit2_ si _unit1_ quiere darle un item a _unit2_ se tienen que cumplir tres condiciones: la distacia entre _unit1_ y _unit2_ debe ser igual a 1, _unit1_ debe poseer el item y _unit2_ debe tener como máximo _maxItems_-1 items. Como caso extra si _unit1_ tiene equipado el item este cambiara su _owner_ a _null_ y tambien se desequipará de _unit2_ para luego seguir la vía usual del intercambio, que sería eliminar el item de la lista de items de _unit1_ y agregarlo en la lista de _unit2_.

## Combate
Para el combate también se utilizó la estrategia de _Dual Dispatch_. La unidad abstracta cuenta con un método _combat(IUnit unit)_. Sea _unit1_ y _unit2_ dos tipos de unidades. Primero se verifican para _unit1_ ciertas condiciones, ataca si puede, luego _unit2_ verifica si tambien se cumple las condiciones y atacará de vuelta. 

Las condiciones son: la unidad que ataca debe tener sus _currentHitpoints_ distintos de 0 (es imposible tener menos que cero), la unidad debe tener un item equipado y la unidad adversaria debe estar en el rango del item de la unidad que ataca. Si en alguna parte del combate no se cumplen las condiciones, el combate termina.

Ahora ¿Cómo se combate? Después de verificar que las condiciones previamente mencionadas se cumplan para _unit1_ se envia un mensaje al item equipado de _unit1_ (_item1_) diciendole que atacará a _unit2_, como ya se sabe que _unit2_ está en el rango _item1_ enviará un mensaje al item de _unit2_ (_item2_) diciendole que está siendo atacado por un arma del tipo que de _item1_, así _item2_ sabrá como reaccionar al ataque y dirá cuanto daño debe recibir _unit2_ ,dependiendo de las fortalezas o debilidades de _item2_, si el daño neto es menor que 0 se actualiza el daño como 0, _item2_ envíará un mensaje a _unit2_ con el daño a asginar y se reducirán los _currentsPoints_ de _unit2_, si los _currentPoints_ quedan negativos se le asigna 0. Luego se verifican las condiciones para que _unit2_ pueda atacar a _unit1_ y devuelve con otro ataque de ser posible finalizando el combate. La implementación funciona pero es algo redundante, hay implementaciones de contrataque muchos más optimos, por ejemplo: una vez se le dice a _item2_ que fue atacado por un item de la clase _item1_, _item2_ sabrá su tipo y el del item atacante y sabrá cuanto daño hará el contrataque y si se cumplen las condiciones de combate _item2_ mandará un mensaje a _unit1_ con el daño que recibirá, así mismo para _unit2_. ¿Porqué no se cambió todo para usar esta implementación? Porque la implementación funciona y no afecta tanto el diseño y aparte el _coverage_ de _testeo_ disminuiría considerablemente ya que el combate implementado funciona de forma simetrica.

Faltó mencionar que pasa con _Cleric_ ya que este no ataca y según se explica en la implementación este debería recibir un contraatque de todos modos, bueno el metodo de _combat( )_ de _Cleric_ es distinto, si _Cleric_ tiene equipado un item (el _Staff_), la otra unidad está en rango del _Staff_ y además los _currentHitPoints_ de la unidad son mayores a 0, _Cleric_ le envará un mensaje a su _Staff_ y este a su vez le enviará un mensaje a la unidad que se quiere sanar y se aumentarán sus _currentPoints_, si los nuevos _currentsHitpoints_ superan a _maxHitpoints_ se asignará como _currentHitpoints_ _maxHitpoints_ para evitar el overHealing.


## Ejecución
Como el programa sigue en una versión "primitiva" la ejecución se basa en los test qu se encuentran en la capeta test:

Para el testeo de las unidades se setearon un set de unidades "_target_" y dos sets de items, uno para equiparlos a las unidades que se querian testear y el otro para equipar a las "_target_". Entre los test se incluyen el correcto funconamiento de los constructores, la correcta equipación de los items, el funcionamiento del intercambio, la cantidad maxima de items, el movimiento de las unidades, el combate y el correcto funconamiento a la hora de sanar una unidad (_testCleric_ verifica los resultados al sanar a otras unidades).

El testeo del combate es el más "complicado" dado que cada unidad evalua ambitos distintos del comportamiento entre las unidades. _testSorcerer_ evalua como se comportan las unidades al combatir libros con libros o libros contra items no mágicos, _testHero_, _testSwordMaster_ y _testFighter_ evaluan los resultados al combatir entre ellos. _testArcher_ verifica los resultados de combatir con items magicos o no magicos de corto alcance (_spear_,_axe_, _sword_) y que pasa si el poder del arma es mayor que la vida de la unidad atacada o si la unidad atacada está fuera del rango de su arma, _testAlpaca_ ve que pasa cuando la unidad atacada no tiene item equipado (en este caso la unidad atacada es la ALpaca).

Entre los testeos de los items se encontraba el correcto funcionamiento del constructor, el equipamiento a unidades y tambien como para las unidades el correcto funcionamiento de la curación y los ataques a unidades.









