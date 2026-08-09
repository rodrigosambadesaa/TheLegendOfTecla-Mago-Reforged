# The Legend of Tecla — Mago / Guerrero Reforged

Reconstrucción moderna y separada de la línea histórica basada en Mago,
Guerrero, Activo, Pasivo y Amigo. El primer commit conserva como código fuente
la entrega recuperada; los commits posteriores construyen una interpretación
correcta, mantenible y completa de aquella idea.

Las referencias históricas identifican a Miguel Alonso Castro y Rodrigo Sambade
Saá; la ayuda de la fase 2 también menciona a Higinio.

Esta edición conserva como base el alcance clásico de P1–P3 y la parte opcional de
interfaz/editor, y añade la ampliación táctica solicitada de aliados. Ofrece consola y GUI Swing vía noVNC, editor gráfico, escenarios
predeterminados/TXT/JSON, Mago, Guerrero y Alquimista, inventario, equipo,
combate y comandos compuestos. No expone las ampliaciones posteriores del
repositorio principal, salvo esta capa aliada compartida.

Los aliados se activan en la GUI o con `--aliados si`, con condición de victoria
seleccionable mediante `--victoria`. `reagrupar defensiva` y `reagrupar ofensiva`
hacen que acompañen al jugador; el aliado en mejor estado busca suministros dentro
del radio del grupo, los enemigos reaccionan al detectar la formación y cada binocular
se conserva hasta un turno útil y se consume en ese único uso.

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
