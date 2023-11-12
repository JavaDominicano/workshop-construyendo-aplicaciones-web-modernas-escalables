# Taller Construyendo Aplicaciones Web Modernas y Escalables con Java

### Taller: Construyendo Aplicaciones Web Modernas y Escalables con Java

### Fecha: 25 de noviembre de 2023

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
    * `Open JDK` 17 o superior.
        * Si utiliza _SDKMAN_, puede utilizar el comando `sdk install java 17-tem`
    * `Maven` 3.9.4 o superior (https://maven.apache.org/)
    * `IDE` de preferencia
    * `Docker` (https://www.docker.com/get-started/)
    * `Traefik` (https://doc.traefik.io/traefik/)
    * `OpenTofu` (https://opentofu.org/docs/intro/)
    * `Ansible` (https://docs.ansible.com/)

**Nota**: _Para realizar el despliegue en la nube con Ansible y OpenTofu, se requiere contar con una cuenta en
DigitalOcean._

_En caso de no poseer una cuenta, podrás observar cómo orquestamos todo el despliegue de la aplicación en la nube
durante el taller._

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