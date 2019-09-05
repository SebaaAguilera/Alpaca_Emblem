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
  -**Items Mágicos:** Son principalmente los libros.
    1. **Libros de Luz:** _LightBook_
    2. **Libros de Oscuridad:** _DarknessBook_
    3. **Libros de Ánimas:** _AnimaBook_
  -Items no Mágicos:
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

Para poder equipar un item a una unidad se tienen que cumplir como condición: la unidad porta el item en su lista de items y el item debe ser del tipo apto para la unidad. Para implemantar esto se utilizó la estrategia de _Double Dispatch_, la unidad no sabe que items puede equiparse, el item lo decidirá por él. Por ejemplo para _Hero_ si hacemos _equipItem(item)_ se enviará un mensaje al item llamando a _item.equipToAHero()_, si el item que recibe el mensaje es un _spear_ equipará el item y actualizará a su dueño y de no ser un _spear_ el item no se equipará. A grandes rasgos la unidad cuando quiere equiparse un item enviará un mensaje al item diciendo "Soy un _algo_" y el item entenderá el mensaje como hacer nada o equiparse.

Para el intercambio basta una estrategia simple. Tenemos dos unidades _unit1_ y _unit2_ si _unit1_ quiere darle un item a _unit2_ se tienen que cumplir tres condiciones: la distacia entre _unit1_ y _unit2_ debe ser igual a 1, _unit1_ debe poseer el item y _unit2_ debe tener como máximo _maxItems_-1 items. Como caso extra si _unit1_ tiene equipado el item este cambiara su _owner_ a _null_ y tambien se desequipará de _unit2_ para luego seguir la vía usual del intercambio, que sería eliminar el item de la lista de items de _unit1_ y agregarlo en la lista de _unit2_.

## Combate

Para el combate también se utilizó la estrategia de _Dual Dispatch_. La unidad abstracta cuenta con un método _combat(IUnit unit)_ que consta de dos partes, primero la unidad que llama a _combat(unit2)_ verifica si cumple ciertas condiciones para que su item ataque y ataca de ser posible, luego _unit2_ataca a la unidad que llama al método de cumplir las mismas condiciones.
Las condiciones son: la unidad que ataca debe tener sus _currentHitpoints_ distintos de 0 (es imposible tener menos que cero) y la unidad debe tener un item equipado.

Ahora ¿Cómo se combate? tenems dos unidades, _unit1_ y _unit2_, después de verificar que las condiciones previamente mencionadas se cumplan para _unit1_ se envia un mensaje al item equipado de _unit1_ llamando a _unit1.getEquippedItem.attackTo(unit2)_ diciendole al arma que atacará a _unit2_ sea el item de _unit1_: _item1_ , este verificará que _unit2_ esté en rango y de estarlo enviará un mensaje al item de _unit2_ (_item2_) llamando a _item2.attackedWithItem1(item1)_ que le dirá a _item2_ que está siendo atacado por un arma del tipo que es _item1_, así _item2_ sabrá como reaccionar al ataque y dirá cuanto daño debe recibir _unit2_ ,dependiendo de las fortalezas o debilidades, mandandole un mensaje a _unit2_ llamando a _unit2.attacked(damage)_ que reducirá los _currentsPoints_ de _unit2_, si los _currentPoints_ pudiesen quedar negativos se le asigna 0. Luego se verifican las condiciones para que _unit2_ pueda atacar a _unit1_ y devuelve con otro ataque de ser posible finalizando el combate. La implementación funciona y es aparentemente correcta pero a la hora de _testearlo_ se encontró un error, si tenemos una unidad que no puede atacar como _Cleric_ esta puede abrir de todos modos el combate está no atacará al oponente ya que el método _attackTo( )_ de _Staff_ está vacío pero aun así luego el oponente devolverá el ataque sin haber sido atacado, lo que incumple los fines dados por el enunciado. Solución parche: hacer _overriding_ para el metodo _combat( )_ en _Cleric_ y dejarlo vacío, así para _Cleric_ no se hará nada si este llega a abrir un combate.

Solución propuesta: una vez se le dice a _item2_ que fue atacado por un item de la clase _item1_ este sabrá su tipo y el del item atacante y sabrá cuanto daño hará el ataque y si cumple con las condiciones y está en el rango mandará un mensaje a _unit1_ con el daño que recibirá, así mismo para _unit2_. Ahorrandose hacer _overriding_. ¿Porqué no se implementó esto? Porque dado que al ser solo un tipo de unidad "conflictiva" no afecta tanto el diseño.

## Items que sanan
_Cleric_ cuenta con un método _heal( )_ este le enviará un mensaje a su _Staff_ y este le enviará un mensaje a la unidad que se quiere sanar llamando a _healed(healHP)_ aumentandole los _currentPoints_


### Quizás no habpia que ser tan explicito con todo, perdón por el _wall of text_ :worried:














