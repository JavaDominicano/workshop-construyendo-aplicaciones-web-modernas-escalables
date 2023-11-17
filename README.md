# Construyendo Aplicaciones Web Modernas y Escalables con Java
![Workshop](https://github.com/fredpena/workshop-construyendo-aplicaciones-web-modernas-escalables/assets/5680906/62b84591-0831-420b-a364-b9e9d1a93327)


## Descargo de Responsabilidad:

* La información proporcionada en esta presentación tiene como objetivo educativo y se basa en experiencia personal y
  conocimientos actuales. Si bien se ha hecho todo lo posible para garantizar la precisión y la actualidad de la
  información presentada, no se puedo garantizar su exactitud completa.
* El uso de las tecnologías mencionadas, está sujeto a los términos y condiciones de cada herramienta. Es
  responsabilidad
  del usuario realizar su propia investigación y cumplir con las directrices y políticas de cada tecnología antes de
  implementarlas en su entorno de producción.
* Además, cabe señalar que las mejores prácticas y las soluciones presentadas en esta presentación pueden variar según
  los
  requisitos y las circunstancias específicas de cada proyecto. Recomiendo encarecidamente realizar pruebas exhaustivas
  y
  consultar con profesionales capacitados antes de implementar cualquier solución en un entorno de producción.
* En resumen, mientras que esta presentación busca proporcionar información útil y práctica, el uso de las tecnologías y
  las decisiones de implementación son responsabilidad del usuario final. No se asume ninguna responsabilidad por los
  resultados derivados de la aplicación de los conceptos discutidos en esta presentación.

## Descripción General:

Únete a nuestra comunidad Java Dominicana en el taller "Construyendo Aplicaciones Web Modernas y Escalables con Java",
donde explorarás tecnologías clave que están transformando el desarrollo de software. Desde la creación del backend con
Spring Boot hasta la construcción de interfaces de usuario atractivas con Vaadin Flow, y la administración de la
infraestructura con Docker, Traefik, OpenTofu y Ansible, este taller te proporcionará las habilidades esenciales para
llevar tus proyectos al siguiente nivel.

¡Te invitamos a participar y descubrir cómo llevar tu aplicación a nuevos horizontes en el entorno
cloud!

## Acerca de los Autores:

### Freddy Peña

* Java Developer, y Profesor por vocación.
* Fundador e Ingeniero de Software en [Alphnology](https://alphnology.com/).
* +10 años de experiencia en desarrollo de software en el ecosistema Java.
* Profesor de la Escuela de Ingeniería en Computación y Telecomunicaciones, PUCMM.
* Miembro de la Comunidad [Java Dominicano](https://site.javadominicano.org/).
* Co-organizador de [JConf Dominicana](https://jconfdominicana.org/).

### Hector Ventura

* Ingeniro en Sistemas.
* +10 años de experiencia en arquitectura y desarrollo de sistemas informaticos.
* Altualmente trabajo para Consensus Cloud Solutions
* Tutor de Java y Linux en [Wyzant](https://www.wyzant.com/match/tutor/88372402).
* Miembro de la Comunidad [Java Dominicano](https://site.javadominicano.org/).

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
* `Para realizar el despliegue en la nube con OpenTofu y Ansible, se requiere contar con una cuenta en
  DigitalOcean. En caso de no poseer una cuenta, podrás observar cómo orquestamos todo el despliegue de la aplicación en
  la nube durante el taller.`

## Tecnologías Clave:

- [Spring Boot](https://spring.io/projects/spring-boot): Framework de desarrollo para crear aplicaciones Java de manera
  rápida y sencilla.
- [Vaadin Flow](https://vaadin.com/flow): Framework para la construcción de interfaces de usuario en Java.
- [Docker](https://www.docker.com/): Plataforma de contenerización para el desarrollo, envío y ejecución de
  aplicaciones.
- [Traefik](https://traefik.io/traefik/): Servidor proxy y balanceador de carga para aplicaciones contenerizadas.
- [OpenTofu](https://opentofu.org/): Herramienta para la creación y administración de infraestructuras como
  código `Infrastructure as Code (IaC)`.
- [Ansible](https://www.ansible.com/): Herramienta de automatización para la configuración y administración de sistemas.
- [DigitalOcean](https://www.digitalocean.com/): Proporciona servicios de infraestructura en la nube.

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

_**No te pierdas esta oportunidad de aprender y crecer en el mundo de la tecnología. Únete a nosotros y prepárate para
llevar tus habilidades de desarrollo al futuro.**_

## Agradecimientos

Queremos expresar nuestro sincero agradecimiento a dos miembros de la comunidad Java
Dominicana: [Freddy Peña](https://twitter.com/fred_pena)
y [Hector Ventura](https://twitter.com/hectorvent). Su dedicación y contribuciones han sido fundamentales para hacer
posible este taller. Agradecemos su
compromiso y pasión por compartir conocimientos, enriqueciendo así nuestra comunidad. ¡Gracias, Freddy y Hector, por ser
parte clave de este evento y por inspirar a otros con su experiencia y habilidades en el mundo del desarrollo Java!
