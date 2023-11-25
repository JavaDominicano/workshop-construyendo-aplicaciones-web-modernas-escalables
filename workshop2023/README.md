# WorkShop2023

### Ejecutando la aplicación

El proyecto es un proyecto estándar de Maven. Para ejecutarlo desde la línea de comando,
escriba `make start` (Mac & Linux), luego abra http://localhost:50380 en su navegador.

El comando `make start`, es una forma eficiente de automatización del proceso de configuración y ejecución
de la aplicación en un entorno Docker. Esta abstracción simplifica enormemente, ya que
permite poner en marcha la aplicación sin tener que preocuparse por los detalles específicos de la configuración de
la aplicación y otros procesos de inicio.

#### Credenciales:

| **Usuario** | **Contraseña** |
|-------------|----------------|
| root        | 1234f          | 
| f.pena      | 1234f          | 
| h.ventura   | 1234f          |

También puede importar el proyecto a su IDE de elección como lo haría con cualquier
Proyecto Maven. Leer más
en [cómo importar proyectos de Vaadin a diferentes IDE](https://vaadin.com/docs/latest/guide/step-by-step/importing) (
Eclipse, IntelliJ IDEA, NetBeans, y VS Code).

## Implementación en producción

Para crear una compilación de producción, llame a `mvnw clean package -Pproduction` (Windows),
o `./mvnw clean package -Pproduction` (Mac y Linux).
Esto creará un archivo JAR con todas las dependencias y recursos de front-end,
listo para ser implementado. El archivo se puede encontrar en la carpeta `target` una vez que se completa la
compilación.

Una vez creado el archivo JAR, puede ejecutarlo usando
`java -jar target/workshop.jar`

## Implementación usando Docker

Para construir la versión Dockerizada del proyecto, ejecute

```
mvn clean package -Pproduction
docker-compose -f docker-compose-full.yml up -d 
```

Una vez que la imagenes de Docker estén correctamente creada, puede probarla localmente accediendo

```
http://workshop.local
```

## Enlaces útiles

- Read the documentation at [vaadin.com/docs](https://vaadin.com/docs).
- Follow the tutorial at [vaadin.com/docs/latest/tutorial/overview](https://vaadin.com/docs/latest/tutorial/overview).
- Create new projects at [start.vaadin.com](https://start.vaadin.com/).
- Search UI components and their usage examples
  at [vaadin.com/docs/latest/components](https://vaadin.com/docs/latest/components).
- View use case applications that demonstrate Vaadin capabilities
  at [vaadin.com/examples-and-demos](https://vaadin.com/examples-and-demos).
- Build any UI without custom CSS by discovering Vaadin's set
  of [CSS utility classes](https://vaadin.com/docs/styling/lumo/utility-classes).
- Find a collection of solutions to common use cases at [cookbook.vaadin.com](https://cookbook.vaadin.com/).
- Find add-ons at [vaadin.com/directory](https://vaadin.com/directory).
- Ask questions on [Stack Overflow](https://stackoverflow.com/questions/tagged/vaadin) or join
  our [Discord channel](https://discord.gg/MYFq5RTbBn).
- Report issues, create pull requests in [GitHub](https://github.com/vaadin).