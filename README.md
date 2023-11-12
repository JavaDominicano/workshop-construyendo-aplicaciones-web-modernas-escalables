# Construyendo Aplicaciones Web Modernas y Escalables con Java

### Taller: Construyendo Aplicaciones Web Modernas y Escalables con Java

#### Fecha: 25 de noviembre de 2023

#### Hora: 10:00 - 14:00 (GMT-4)

#### Lugar:

## Descripción General:

Únete a nuestra comunidad Java Dominicana en el taller "Construyendo Aplicaciones Web Modernas y Escalables con Java",
donde explorarás tecnologías clave que están transformando el desarrollo de software. Desde la creación del backend con
Spring Boot hasta la construcción de interfaces de usuario atractivas con Vaadin Flow, y la administración de la
infraestructura con Docker, Traefik, OpenTofu y Ansible, este taller te proporcionará las habilidades esenciales para
llevar tus proyectos al siguiente nivel.

¡Te invitamos a participar y descubrir cómo llevar tu aplicación a nuevos horizontes en el entorno
cloud!

## Requisitos:

* Conocimientos básicos de programación en Java.
* Familiaridad con el desarrollo de aplicaciones web.
* Laptop con capacidad para ejecutar entornos de desarrollo y máquinas virtuales.
* Software instalado:
    * [Open JDK](https://adoptium.net/temurin/releases/?package=jdk&version=17) 17 o superior.
        * Si utiliza [SDKMAN](https://sdkman.io/install), puede utilizar el comando `sdk install java 17-tem`
    * [Maven](https://maven.apache.org/) 3.9.4 o superior
    * `IDE` de
      preferencia ([IntelliJ IDEA](https://www.jetbrains.com/idea/download/other.html), [Eclipse IDE](https://www.eclipse.org/downloads/packages/installer), [Apache NetBeans](https://netbeans.apache.org/front/main/download/)
      o [Visual Studio Code](https://code.visualstudio.com/Download))
    * [Docker](https://www.docker.com/get-started/)
    * [Traefik](https://doc.traefik.io/traefik/)
    * [OpenTofu](https://opentofu.org/docs/intro/)
    * [Ansible](https://docs.ansible.com/)

**Nota**:

* Para garantizar una experiencia fluida durante el taller, agradecemos que todos los participantes aseguren tener
  previamente instalados los requisitos de software en sus laptops.
* Esta preparación previa garantizará que puedas participar activamente en todos los laboratorios del taller,
  maximizando tu experiencia de aprendizaje
* `Para realizar el despliegue en la nube con Ansible y OpenTofu, se requiere contar con una cuenta en
  DigitalOcean. En caso de no poseer una cuenta, podrás observar cómo orquestamos todo el despliegue de la aplicación en
  la nube durante el taller.`

## Tecnologías Clave:

- **Spring Boot:** Framework de desarrollo para crear aplicaciones Java de manera rápida y sencilla.
- **Vaadin Flow:** Framework para la construcción de interfaces de usuario en Java.
- **Docker:** Plataforma de contenerización para el desarrollo, envío y ejecución de aplicaciones.
- **Traefik:** Servidor proxy y balanceador de carga para aplicaciones contenerizadas.
- **OpenTofu:** Herramienta para la creación y administración de infraestructuras como código.
- **Ansible:** Herramienta de automatización para la configuración y administración de sistemas.

## Estructura del proyecto

- `resources` package in `src/main`
- `views` package in `src/main/java`
- `security` package in `src/main/java`
- `data` package in `src/main/java`
- `themes` folder in `frontend/` contiene los estilos CSS personalizados.

## Laboratorios:

### Laboratorio 1: Construyendo un proyecto con Vaadin Flow

Crear un proyecto con Open Vaadin Start. Conocer la arquitectura del proyecto y realizar despliegue local.

Explorar temas importantes de Vaadin Flow, como construir una interfaz de usuario, manejar eventos, crear vistas
principales, enrutamiento y navegación, ciclo de vida de navegación, disposición de enrutadores y objetivos enrutadores
anidados, manejo de excepciones de enrutador, plantillas de ruta, creación de UI en aplicaciones Vaadin, creación de
componentes, visión general de estilos, enlace de datos, seguridad y despliegue en producción.

### Laboratorio 2: Despliegue Local con Docker y Traefik

Utilizar Docker para contenerizar la aplicación.
Implementar Traefik como servidor proxy y balanceador de carga.
Realizar un despliegue local de la aplicación y gestionar su funcionamiento.

### Laboratorio 3: Despliegue Seguro y Escalable con Docker, Traefik, OpenTofu y Ansible

Integrar Docker, Traefik, OpenTofu y Ansible para un despliegue seguro y escalable.
Configurar OpenTofu para la creación de recursos de infraestructura.
Utilizar Ansible para la configuración y administración de sistemas.
Implementar una solución completa de despliegue seguro y escalable de aplicaciones web.

No te pierdas esta oportunidad de aprender y crecer en el mundo de la tecnología. Únete a nosotros y prepárate para
llevar tus habilidades de desarrollo al futuro.

## Agradecimientos

Queremos expresar nuestro sincero agradecimiento a dos miembros de la comunidad Java
Dominicana: [Freddy Peña](https://twitter.com/fred_pena)
y [Hector Ventura](https://twitter.com/hectorvent). Su dedicación y contribuciones han sido fundamentales para hacer
posible este taller. Agradecemos su
compromiso y pasión por compartir conocimientos, enriqueciendo así nuestra comunidad. ¡Gracias, Freddy y Hector, por ser
parte clave de este evento y por inspirar a otros con su experiencia y habilidades en el mundo del desarrollo Java!