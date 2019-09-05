# Alpaca_Emblem

El proyecto se encuentra en su versión 1.1 acorde a los requisitos de la entrega n°1.

En las secciones **Unidades** e **Items** se explicán algunas implementaciones, extensiones que se le hicieron al código dado, la visibilidad de parametros, etc. En **Equipamiento e intercambio de items** y **Combate** se explica en especifico los requerimientos puros y duros de la entrega. Cuando noté lo extenso que fue para redactar esto ya era demasiado tarde, perdón :sweat:. 

## Unidades: 
Las unidades son objetos de una clase que extiende a la clase abstracta _AbstractUnit_ que a su vez implementa a la interfaz _IUnit_.
- Cada unidad tiene los siguientes atributos privados en la clase abstracta:
  1. **Hitpoints:** _currentHitPoints_ es un _double_ y representa la vida actual de una unidad.
  2. **Movement:** _movement_ es un _ final int_ que represena la cantidad maxima de celdas que una unidad se puede mover.
  3. **Location:** _location_ es un objeto de la clase _Location_ y representa
la locación actual de un personaje.
  4. **Items:** los items son objetos de la "clase" _IEquipableItem_ (se explicará mas tarde) _maxItems_ es un _final int_ que representa la cantidad maxima de items que una unidad puede portar, _items_ es una _ArrayList_ de los items que la unidad porta y _equipedItem_ es el item que la unidad tiene equipado.

- El juego cuenta con 7 Unidades:

A excepción de la _Alpaca_ cada unidad pude portar como máximo 3 items y de estos 
y de estos 3 items solo puede equiparse 1.
  1. ***Archer:*** Solo puede equiparse _Bow_'s.
  2. ***Fighter:*** Solo puede equiparse _Axe_'s.
  3. ***Sword Master:*** Solo puede equiparse _Sword_'s.
  4. ***Alpaca:*** Puede portar una cantidad ilimitada de items pero equiparse ninguno.
  5. ***Cleric:*** Solo puede equiparse _Staff_'s y no puede atacar.
  6. ***Hero:*** Solo puede equiparse _Spear_'s.
  7. ***Sorcerer:*** Solo puede equiparse _LightBooks_'s, _DarknessBooks_'s 
  y _AnimaBooks_'s.
  
## Items: 

Los items son objetos de una clase que (dependiendo de si es un item magico o no) extiende a la clase abstracta _AbstractMagicItem_ o _AbstractNonMagicItem_, estas extienden a la clase abstracta _AbstractItem_ que a su vez implementa a la interfaz _IEquipableItem_.

- Cada item tiene los siguientes atributos privados:
  1. **Nombre:** _name_ es un _final String_ y es el nombre del item.
  2. **Poder:** _power_ es un _final int_ y representa el poder del item, pregunta ¿Por qué los puntos de vida de las unidades son _double_ cuanto el poder de los items son _final int_? el efecto que producen las armas se puede ver potenciado o disminuido bajo ciertas condiciones que se verán más adelante en las secciones ***Fortalezas y Debilidades*** y ***Combate***.
  3. **Dueño:** _owner_ es efectivamente la unidad que tiene equipado el item.

- Cada item tiene los siguientes atributos protegidos:
  1. **Rango mínimo:** _minRange_ es un _int_ que representa la cantidad mínima de celdas que tiene que estar un adversario para ser atacado por la unidad que equipa el item. Por defecto es 1, en casos especiales como el _Bow_ este es 2.
  2. **Rango máximo:** _maxRange_ es un _int_ que representa la cantidad máxima de celdas que tiene que estar un adversario para ser atacado por la unidad que equipa el item. tiene que ser estrictamente mayor a _minRange_.
  
  - Existen dos clases de items, mágicos y no mágicos y su comportamiento se diferencia principalmente al momento de un combate, esto se explicará en la sección ***Fortalezas y Debilidades***. 
  
 - Items Mágicos: Son principalmente los libros
    1. **Libros de Luz:** _LightBook_
    2. **Libros de Oscuridad:** _DarknessBook_
    3. **Libros de Ánimas:** _AnimaBook_
    
  - Items no Mágicos:
    1. **Hacha:** _Axe_
    2. **Arco:** _Bow_
    3. **Lanza:** _Spear_
    4. **Bastón:** _Staff_
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

Ahora ¿Cómo se combate? Después de verificar que las condiciones previamente mencionadas se cumplan para _unit1_ se envia un mensaje al item equipado de _unit1_ (_item1_) diciendole que atacará a _unit2_, como ya se sabe que _unit2_ está en el rango _item1_ enviará un mensaje al item de _unit2_ (_item2_) diciendole que está siendo atacado por un arma del tipo que de _item1_, así _item2_ sabrá como reaccionar al ataque y dirá cuanto daño debe recibir _unit2_ ,dependiendo de las fortalezas o debilidades de _item2_, si el daño neto es menor que 0 se actualiza el daño como 0, _item2_ envíará un mensaje a _unit2_ con el daño a asginar y se reducirán los _currentsPoints_ de _unit2_, si los _currentPoints_ quedan negativos se le asigna 0. Luego se verifican las condiciones para que _unit2_ pueda atacar a _unit1_ y devuelve con otro ataque de ser posible finalizando el combate. La implementación funciona y es aparentemente correcta pero a la hora de _testearlo_ se encontró un error, si tenemos una unidad que no puede atacar como _Cleric_ esta puede abrir de todos modos el combate y claramente no atacará al oponente ya que el método asignado para el ataque de _Staff_ está vacío, pero aun así luego el oponente "devolverá" el ataque sin haber sido atacado, lo que incumple los fines dados por el enunciado. Solución parche: hacer _overriding_ para el metodo _combat( )_ en _Cleric_ y dejarlo vacío, así para _Cleric_ no se hará nada si este llega a abrir un combate.

Solución propuesta: una vez se le dice a _item2_ que fue atacado por un item de la clase _item1_, _item2_ sabrá su tipo y el del item atacante y sabrá cuanto daño hará el contrataque y si se cumplen las condiciones de combate _item2_ mandará un mensaje a _unit1_ con el daño que recibirá, así mismo para _unit2_. Ahorrandose hacer _overriding_ y solo dejando el método que ataca de _Staff_ vacío. ¿Porqué no se cambió todo para usar esta implementación? Porque dado que solo hay un tipo de unidad "conflictiva" no afecta tanto el diseño y  también el _coverage_ de _testeo_ disminuiría considerablemente.

## Items que sanan
_Cleric_ cuenta con un método _heal( )_ este le enviará un mensaje a su _Staff_ y este le enviará un mensaje a la unidad que se quiere sanar si esta unidad está en rango de _Staff_ se aumentarán los _currentPoints_.












