# WorkShop2023

### Ejecutando la aplicación

El proyecto sigue la estructura estándar de Maven. Para ejecutarlo desde la línea de comando,
escriba `make start` (Mac & Linux) o `.\make.exe start` (Windows), luego abra http://localhost:50380 en su navegador.

Es necesario tener instalado [Docker](https://www.docker.com/get-started/). Además, para
ejecutar el comando `make start`, es necesario contar con la herramienta [make](https://www.gnu.org/software/make/).
Estos son los requisitos para poder ejecutar este proyecto.

El comando `make start` representa una forma eficiente de automatizar el proceso de configuración y ejecución de la
aplicación en un entorno Docker. Esta abstracción simplifica enormemente el despliegue, ya que permite poner en marcha
la aplicación sin tener que preocuparse por los detalles específicos de la configuración y otros procesos de inicio.

En este comando, están integrados los pasos necesarios, como `docker-compose down -v` para detener y limpiar los
contenedores existentes, `docker-compose up -d` para iniciar los servicios en segundo plano, y `mvn` para compilar y
ejecutar la aplicación en un ambiente de prueba. Esto facilita significativamente el despliegue de la aplicación,
proporcionando una única y simple entrada para llevar a cabo el proceso completo de manera eficiente. En caso de no contar con make peude ejecutar los comando uno a uno.

También puede importar el proyecto a su IDE de elección como lo haría con cualquier
Proyecto Maven. Leer más
en [cómo importar proyectos de Vaadin a diferentes IDE](https://vaadin.com/docs/latest/guide/step-by-step/importing) (
Eclipse, IntelliJ IDEA, NetBeans, y VS Code).

#### Credenciales:

| **Usuario** | **Contraseña** |
|-------------|----------------|
| root        | 1234f          | 
| f.pena      | 1234f          | 
| h.ventura   | 1234f          |

## Implementación en producción

Para crear una compilación de producción, ejecute el comando `mvnw clean package -Pproduction` (Windows),
o `./mvnw clean package -Pproduction` (Mac y Linux).
Esto creará un archivo JAR con todas las dependencias y recursos de front-end,
listo para ser implementado. El ejecutable se puede encontrar en la carpeta `target` una vez que se completa la
compilación.

Una vez creado el archivo JAR, puede ejecutarlo usando
`java -jar target/workshop.jar`

_Es importante destacar que, para la ejecución exitosa de este proyecto en este escenario, es necesario proporcionar al
servidor de la base
de datos con las configuraciones correspondientes en el archivo `application.yml`. Asegúrese de que estas
configuraciones
estén correctamente definidas para garantizar la conexión adecuada con el motor de la base de datos durante la ejecución
de la aplicación._

```yaml
  #MariaDB configuration.
  datasource:
    username: root
    password: p@ssw0rd
    url: jdbc:mariadb://localhost:3306/workshop
    driver-class-name: org.mariadb.jdbc.Driver
```

## Implementación en producción usando Docker

Para realizar el despliegue en un entorno de producción en Docker, se tienen dos escenarios posibles. Pero antes de
proceder,
es imperativo ejecutar el comando `mvnw clean package -Pproduction` en entornos Windows, o `./mvnw clean package
-Pproduction` en Mac y Linux.

- El primer escenario implica ejecutar el comando `docker-compose -f docker-compose-prod up -d`, donde se pondrá en
  marcha los servicios de mariadb y workshopapp.

- En el segundo escenario, al ejecutar el comando `docker-compose -f docker-compose-prod-traefik up -d`, se incorpora
  Traefik para llevar a cabo una prueba de concepto de un proxy inverso. En caso de optar por esta opción, es crucial
  recordar modificar el archivo hosts para apuntar `127.0.0.1` a `workshop.local`, o al nombre que haya definido en el
  label `
  traefik.http.routers.workshop.rule=Host(...)`. Este ajuste es esencial para permitir la ejecución de la aplicación en
  el dominio local y habilitar la funcionalidad del proxy inverso proporcionada por Traefik.

Una vez que la imagenes de Docker estén correctamente creada, puede probarla localmente accediendo.

[http://workshop.local](http://workshop.local)

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