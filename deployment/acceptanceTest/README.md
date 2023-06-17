#  Proyecto Base de Karate para pruebas de integraci?n en AcceptanceTest - REST, GraphQL, SOAP
_Karate es una herramienta de c?digo abierto que combina la automatizaci?n de pruebas de API, simulacros , pruebas de rendimiento e incluso la automatizaci?n de la interfaz de usuario en un marco ?nico y unificado . La sintaxis BDD popularizada por Cucumber es un lenguaje neutro y f?cil incluso para los no programadores. Las afirmaciones y los informes HTML est?n integrados y puede ejecutar pruebas en paralelo para aumentar la velocidad._

_Si est? familiarizado con Cucumber / Gherkin, la gran diferencia aqu? es que no necesita escribir c?digo extra de "pegamento" o "definiciones de pasos" de Java._

**RECOMENDACION !!!**: Visitar la documentaci?n oficial para obtener todas las ventajas de este potencial framework: https://github.com/intuit/karate
## Comenzando

### Instalaci?n ?

**IMPORTANTE**: Este proyecto es una demo, proyecto base, para estructurar las pruebas de integraci?n (AcceptanceTest) que se realizar?n. Este proyecto es funcional yo consume la pet-store  API en su versi?n 3 (https://petstore3.swagger.io/api/v3), sin embargo, a continuaci?n te contamos que debes modificar y configurar para comenzar en tu contexto de aplicaci?n con las pruebas:
- Ir al karate-config.js y modificar la `urlBase` por la url o endpoint de tu aplicaci?n.
- Ir al karate-config.js y modificar la `oasUrl` por el path y nombre de la deficnic?n open Api de la API a testear.
- Ir a los archivos .feature (src>test>resources>co.com.equilibrium) agregar tus escenarios, m?todos de prueba, aserciones, y todo lo necesario para tus pruebas en particular.

Aqu? se detalla la estructura que debe guiar las pruebas con Karate, es un ejemplo:

```
src
??? test
    ??? java
    ?   ??? co.com.equilibrium
    ?       ??? TestParallel.java
    ?       ??? utils
    ?           ??? ValidatorTestUtils.java
    ??? resources
        ??? co.com.equilibrium
        ?   ??? demo
        ?   ?   ??? demo.feature
        ?   ?   ??? addPet.json
        ?   ??? pet-store.yaml
        ??? karate-config.js
        ??? logback-test.xml
```

- TestParallel -> Clase general en java que ejecuta los TESTS de karate en Paralelo y tambien genera el reporte de dichos TESTS en formato json que luego se convierte en reporte cucumber

## Ejecutando las pruebas ??
Este proyecto soporta ejecuci?n por features tageados de manera independiente, como es el caso del feature demo.feature el cual tiene el tag @acceptanceTest.

```gradle
gradlew clean test "-Dkarate.options=--tags @acceptanceTest" -i
```

De esta manera se ejecutaran todos los features con el tag @acceptanceTest.

Por otra parte, si lo que se quiere es ejecutar todos los features almacenados en el proyecto bastar? con ejecura el comando

```gradle
gradlew clean test -i
```