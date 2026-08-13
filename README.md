# The Legend of Tecla — Mago / Guerrero Reforged

Reconstrucción moderna y separada de la línea histórica basada en Mago,
Guerrero, Activo, Pasivo y Amigo. El primer commit conserva como código fuente
la entrega recuperada; los commits posteriores construyen una interpretación
correcta, mantenible y completa de aquella idea.

Las referencias históricas identifican a Miguel Alonso Castro y Rodrigo Sambade
Saá; la ayuda de la fase 2 también menciona a Higinio.

Esta edición conserva las clases propias Mago, Guerrero y Alquimista y las integra
con la implementación moderna completa: GUI web, editor, mapas predeterminados,
grandes, TXT, JSON y procedurales por semilla, misiones, persistencia, progresión,
entorno destructible, trampas, fabricación, munición y combate táctico.

Los aliados parten junto al jugador, pueden generarse automáticamente o en una
cantidad y nivel elegidos, puntúan individualmente y priorizan ayudar y explorar.
Algunos cumplen el rol médico y buscan botiquines y Toritos Rojos. Los enemigos
emplean armas y armaduras de su propia facción, se coordinan cuando hay escuadra y
su número se escala de forma justa con los aliados. Cada celda posee ambientación
detallada y `mirar` refleja fielmente suelo, luz, fuego, agua y estructuras visibles.
La partida puede continuar en modo espectador cuando muere el jugador: `Turbo`
reproduce turnos cada 100 ms y muestra victoria humana o enemiga. Los enemigos se
dispersan fuera de un radio de preparación escalable y todas las familias de objetos
crecen con el total de aliados y enemigos. Mapa, estado, acciones, eventos y
comandos viven en ventanas movibles, redimensionables, minimizables y maximizables.

## Arsenal, cooperación y escala

- El nivel inicial del jugador se elige en consola y GUI, tanto en solitario como con aliados.
- Por defecto los aliados pueden sustituir sus armas y armaduras por equipo objetivamente mejor
  y transfieren al jugador munición compatible cuando la necesita; ambas ayudas son configurables.
- El arsenal diferencia espadas de una y dos manos, cuchillos, arrojadizas, arcos, ballestas,
  granadas, pistolas, subfusiles, escopetas, rifles de asalto y precisión, ametralladoras,
  lanzadores y armas de energía, con cargadores, daño, alcance y penetración propios.
- El catálogo determinista rota variantes para evitar partidas con el mismo lote de armas.
  Jugador, aliados, enemigos normales y jefes eligen y emplean armas compatibles con su rol.
- La precisión depende del tirador, su clase y nivel; el daño final también considera la
  cobertura, el flanqueo y la defensa efectiva de la armadura tras aplicar penetración.
- El despliegue nunca permite más enemigos que combatientes aliados contando al jugador.
  El máximo validado es 5.000 contra 5.000 (4.999 aliados más el jugador).
- La fila numérica y el teclado numérico funcionan en configuración, editor y comando de partida.

CLI: `--nivel-jugador N`, `--sin-mejoras-aliados` y `--sin-municion-aliada`.

## Ejecutar

```bash
mvn verify
java -jar target/the-legend-of-tecla.jar --rapido
java -jar target/the-legend-of-tecla.jar --gui
java -jar target/the-legend-of-tecla.jar --editor
```

Con Docker, `docker compose up --build gui` publica la GUI en
`http://localhost:6080/vnc.html?autoconnect=1&resize=scale`.

La entrega recuperada está en `historico/entrega-original`; los cuatro PDF de
la raíz son los enunciados usados para la reconstrucción.
