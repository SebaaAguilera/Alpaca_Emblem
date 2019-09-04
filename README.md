# Alpaca_Emblem

El proyecto se encuentra en su versión 1.1 acorde a los requisitos de la entrega n°1.

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
  2. ***Fighter:*** Soloo puede equiparse _Axe_'s.
  3. ***Sword Master:*** Soloo puede equiparse _Sword_'s.
  4. ***Alpaca:*** Puede portar una cantidad ilimitada de items pero equiparse ninguno.
  5. ***Cleric:*** Soloo puede equiparse _Staff_'s.
  6. ***Hero:*** Soloo puede equiparse _Spear_'s.
  7. ***Sorcerer:*** Soloo puede equiparse _LightBooks_'s, _DarknessBooks_'s 
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
    1. **Libroz de Luz:** _LightBook_
    2. **Libroz de Oscuridad:** _DarknessBook_
    3. **Libroz de Ánimas:** _AnimaBook_
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

## Mapa
No se hicieron cambios en la implementación de mapa.



