# Kudos Automation - Front-End E2E (Screenplay)

Proyecto de automatización E2E para el módulo de Kudos, utilizando **Serenity BDD** con **Cucumber** y el patrón **Screenplay** con **Selenium WebDriver**.

## Tecnologías

- Java 17+
- Gradle
- Serenity BDD 4.2.12
- Serenity Screenplay 4.2.12
- Cucumber 7.20.1
- Selenium WebDriver (Edge)

## Prerrequisitos

- JDK 17 o superior instalado
- Gradle 8.x (o usar el wrapper incluido `gradlew`)
- Navegador Microsoft Edge instalado
- La aplicación bajo prueba corriendo en `http://localhost:5173`

## Estructura del Proyecto

```
src/test/
├── java/
│   ├── config/                        # Configuración del WebDriver
│   │   └── EdgeDriverProvider.java
│   ├── screenplay/
│   │   ├── interactions/              # Interacciones de bajo nivel
│   │   │   ├── DragSlider.java
│   │   │   ├── SelectFromDropdown.java
│   │   │   └── WaitForUrlContaining.java
│   │   ├── questions/                 # Preguntas (validaciones)
│   │   │   └── IsKudoDisplayed.java
│   │   ├── tasks/                     # Tareas (acciones de negocio)
│   │   │   ├── FillKudoForm.java
│   │   │   ├── NavigateToKudos.java
│   │   │   ├── OpenApplication.java
│   │   │   └── SendKudo.java
│   │   └── userinterfaces/            # Localizadores UI (Targets)
│   │       ├── KudosFormUI.java
│   │       ├── KudosTableUI.java
│   │       └── NavBarUI.java
│   ├── stepdefinitions/               # Step Definitions de Cucumber
│   │   └── SendKudoStepDefinitions.java
│   └── runners/                       # Runner de Cucumber
│       └── CucumberTestRunner.java
└── resources/
    ├── features/                      # Escenarios Gherkin
    │   └── send_kudo.feature
    ├── serenity.conf                  # Configuración de Serenity BDD
    └── junit-platform.properties
```

## Patrón Screenplay

Este proyecto reemplaza el patrón Page Object Model (POM) por el patrón **Screenplay**, separando la lógica en:

| Componente | Responsabilidad | Ejemplo |
|------------|----------------|---------|
| **User Interfaces** | Localizadores de elementos UI (Targets) | `KudosFormUI`, `NavBarUI` |
| **Tasks** | Acciones de negocio de alto nivel | `FillKudoForm`, `SendKudo` |
| **Interactions** | Acciones de bajo nivel sobre la UI | `DragSlider`, `SelectFromDropdown` |
| **Questions** | Validaciones del estado de la aplicación | `IsKudoDisplayed` |

### Flujo E2E Automatizado

```
Actor (collaborator)
  │
  ├── OpenApplication          → Abrir http://localhost:5173
  ├── NavigateToKudos          → Click en "Acceder"
  ├── FillKudoForm             → Seleccionar remitente, destinatario, categoría y mensaje
  ├── SendKudo                 → Deslizar slider para enviar
  └── IsKudoDisplayed          → Verificar que el kudo aparece en la tabla
```

## Ejecución de Tests

```bash
./gradlew clean test
```

## Generación de Reportes Serenity

```bash
./gradlew aggregate
```

Los reportes se generan en `target/site/serenity/index.html`.

## Configuración del Driver

El driver de Edge se configura mediante un provider personalizado (`EdgeDriverProvider.java`) y se ubica en la carpeta `drivers/msedgedriver.exe`. La configuración se gestiona en `serenity.conf` con `webdriver.driver = provided`.
