# inetum-inditex-prices-api

---

## Tabla de Contenidos

- [Descripción](#descripción)
- [Características](#características)
- [Tecnologías](#tecnologías)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Ejecución](#ejecución)
- [Uso de la API](#uso-de-la-api)
- [Arquitectura](#arquitectura)
- [Testing](#testing)
- [Modelo de Datos](#modelo-de-datos)
- [Decisiones Técnicas Adoptadas](#decisiones-técnicas-adoptadas)

---

## Descripción

Esta API REST permite consultar el precio aplicable de un producto en una fecha específica, considerando:

- **Fecha de aplicación** (application date)
- **Identificador de producto** (product id)
- **Identificador de cadena** (brand id)

Cuando existen múltiples precios para los mismos criterios, el sistema devuelve el precio con **mayor prioridad**.

---

## Características

- API REST con endpoint GET para consulta de precios
- Arquitectura Hexagonal (Ports & Adapters)
- Clean Code y principios SOLID
- Base de datos H2 en memoria para desarrollo
- Manejo de excepciones global con códigos HTTP apropiados
- Tests completos: Unitarios, Integración y E2E
- Validación de parámetros y formato de fechas

---


## Tecnologías

### Core

- **Java 17** - Lenguaje de programación
- **Spring Boot 3.2.0** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **H2 Database** - Base de datos en memoria

### Testing

- **JUnit 5** - Framework de testing
- **Mockito** - Mocking para tests unitarios
- **RestAssured** - Tests end-to-end
- **Spring Boot Test** - Tests de integración

### Documentación

- **OpenAPI/Swagger** - Documentación de la API generada automáticamente a partir de las anotaciones en los controladores REST. Permite explorar y probar los endpoints desde una interfaz web interactiva (Swagger UI) accediendo a `/swagger-ui.html` o `/swagger-ui/index.html` una vez levantada la aplicación. La especificación OpenAPI se expone en `/v3/api-docs`. Facilita la integración con otras aplicaciones y la comprensión de la API por parte de desarrolladores externos.
---

## Requisitos Previos

Antes de comenzar, es necesario tener instalado:

- **Java 17** o superior
- **Maven 3.8+**
- **Git**
- **IDE recomendado**: IntelliJ IDEA, Eclipse o VS Code
---



## Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/aserranoandres/inetum-inditex-technical-challenge.git
cd inetum-inditex-technical-challenge
```

### 2. Compilar el proyecto

```bash
mvn clean install
```

### 3. Ejecutar tests

```bash
mvn test
```

---

## Ejecución

### Opción 1: Usando Maven

```bash
mvn spring-boot:run
```

### Opción 2: Usando el JAR generado

```bash
java -jar target/inditex-techincal-challenge-0.0.1-SNAPSHOT.jar
```

### Opción 3: Desde el IDE

Ejecuta la clase principal:
```java
com.aserranoandres.inditex_techincal_challenge.InditexTechincalChallengeApplication
```

La aplicación se iniciará en: **http://localhost:8080**

---

## Uso de la API

### Documentación Interactiva con Swagger

Una vez arrancada la aplicación en el puerto 8080, se puede acceder a la documentación interactiva de Swagger UI en:

```
http://localhost:8080/swagger-ui.html
```

O también en:

```
http://localhost:8080/swagger-ui/index.html
```

 Swagger UI  permite:
- Visualizar todos los endpoints disponibles
- Ver la documentación detallada de cada parámetro
- Ejecutar peticiones directamente desde el navegador
- Probar diferentes casos de uso sin necesidad de herramientas externas como curl o Postman

La especificación OpenAPI en formato JSON está disponible en:
```
http://localhost:8080/v3/api-docs
```

### Endpoint Principal

```http
GET /api/product-prices?applicationDate={date}&productId={id}&brandId={id}
```

### Parámetros

| Parámetro | Tipo | Requerido | Descripción | Ejemplo |
|-----------|------|-----------|-------------|---------|
| `applicationDate` | DateTime | Sí | Fecha de aplicación (ISO 8601) | `2020-06-14T10:00:00` |
| `productId` | Long | Sí | Identificador del producto | `35455` |
| `brandId` | Long | Sí | Identificador de la cadena | `1` |

### Ejemplos de Uso

(En el proyecto, en el directorio **http-requests**, se incluyen ejemplos de requests de cada uno de los casos de prueba indicados)

#### Ejemplo 1: Consulta exitosa

**Request:**
```bash
curl -X GET "http://localhost:8080/api/product-prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1"
```

**Response:** `200 OK`
```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "priceValue": 35.50
}
```

#### Ejemplo 2: Producto no encontrado

**Request:**
```bash
curl -X GET "http://localhost:8080/api/product-prices?applicationDate=2020-06-14T10:00:00&productId=99999&brandId=1"
```

**Response:** `404 Not Found`
```json
{
  "timestamp": "2020-06-14T10:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Product price not found for productId=99999, brandId=1"
}
```

#### Ejemplo 3: Parámetro no informado

**Request:**
```bash
curl -X GET "http://localhost:8080/api/product-prices?productId=35455&brandId=1"
```

**Response:** `400 Bad Request`
```json
{
  "timestamp": "2020-06-14T10:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Required parameter 'applicationDate' is missing"
}
```

### Códigos de Estado HTTP

| Código | Descripción |
|--------|-------------|
| `200` | Precio encontrado exitosamente |
| `400` | Parámetros inválidos o faltantes |
| `404` | Precio no encontrado para los criterios especificados |
| `500` | Error interno del servidor |

---

## Arquitectura

El proyecto sigue **Arquitectura Hexagonal (Ports & Adapters)** para garantizar separación de responsabilidades y testeabilidad.

```
┌─────────────────────────────────────────────────────────┐
│                    REST Controller                       │
│                 (Infrastructure Layer)                   │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│              Application Use Cases                       │
│                 (Application Layer)                      │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│                 Domain Models                            │
│                  (Domain Layer)                          │
└─────────────────────────────────────────────────────────┘
```

### Capas

1. **Domain**: Modelos y puertos (interfaces)
2. **Application**: Casos de uso y lógica de negocio
3. **Infrastructure**: Adaptadores (REST, JPA, etc.)

---

# Testing

El proyecto incluye **cobertura completa de tests**:
- **Tests Unitarios**: Para probar componentes individuales (servicios, repositorios) de forma aislada.
- **Tests de Integración**: Para probar el controlador (Controller) y la capa de servicio/repositorio en conjunto.
- **Tests E2E**: Para simular un cliente real que interactúa con el endpoint como lo haría una aplicación externa.

### Herramientas
- **JUnit 5**: Framework principal de testing
- **Mockito**: Para mocking en tests unitarios
- **Spring Boot Test** y **MockMvc**: Para tests de integración
- **RestAssured**: Para tests E2E de la API REST

### Estructura de Tests

```
src/test/java/
├── unit/                    # Tests Unitarios (con mocks)
├── integration/             # Tests de Integración (Spring + H2)
├── e2e/                     # Tests End-to-End (RestAssured)
└── providers/               # Helpers para datos de prueba
```

### Ejecutar Tests

```bash
# Todos los tests
mvn test
```

### Casos de Prueba Implementados

Los tests validan los 5 casos de uso especificados:

| Test | Fecha | Producto | Brand | Resultado Esperado |
|------|-------|----------|-------|--------------------|
| Test 1 | 2020-06-14 10:00 | 35455 | 1 | price=35.50        |
| Test 2 | 2020-06-14 16:00 | 35455 | 1 | price=25.45        |
| Test 3 | 2020-06-14 21:00 | 35455 | 1 | price=35.50        |
| Test 4 | 2020-06-15 10:00 | 35455 | 1 | price=30.50        |
| Test 5 | 2020-06-16 21:00 | 35455 | 1 | price=38.95        |

---

## Decisiones Técnicas Adoptadas

### 1. Arquitectura Hexagonal

**Decisión:** Se ha implementado una arquitectura hexagonal (Ports & Adapters).

**Justificación:**
- Permite separar la lógica de negocio de los detalles de infraestructura
- Facilita el testing mediante inversión de dependencias
- Posibilita cambiar frameworks o bases de datos sin afectar el núcleo del dominio
- Mejora la mantenibilidad y escalabilidad del código

**Implementación:**
- **Domain Layer:** Contiene los modelos de dominio y las interfaces (puertos)
- **Application Layer:** Implementa los casos de uso y la lógica de negocio
- **Infrastructure Layer:** Adaptadores concretos (REST, JPA, etc.)

### 2. Extracción de datos

**Decisión:** Se utiliza una query nativa SQL con LIMIT 1 para obtener el precio con mayor prioridad.

```java
@Query(value = "SELECT * FROM prices " +
               "WHERE product_id = :productId " +
               "AND brand_id = :brandId " +
               "AND :applicationDate BETWEEN start_date AND end_date " +
               "ORDER BY priority DESC " +
               "LIMIT 1", 
       nativeQuery = true);
```

**Justificación:**

Se tomo esta decisión para optimizar la consulta y asegurar que se recupere únicamente el registro con la mayor prioridad directamente desde la base de datos, evitando la necesidad de filtrar resultados en memoria.

- **Rendimiento:** Garantiza que solo se recupere un registro de la base de datos
- **Claridad:** La intención de la query es explícita y fácil de entender
- **Control:** Mayor control sobre la ejecución y el plan de consulta
- **Eficiencia:** Evita cargar múltiples resultados en memoria para luego filtrarlos

**Otras soluciones que fueron consideradas:**
- **JPQL con `Pageable`:** De esta forma habria que generar un objeto Pageable y manejar la paginación, lo que añade complejidad innecesaria para este caso de uso simple, teniendo en cuenta que no tiene mucho sentido crear una paginación de 1 y puede aumentar el coste de base de datos y disminuir la eficiencia.
- **Naming conventions de JPA:**  Es la solución más rápida y la más haitual pero sería la más verbosa generado un nombre de un método excesivamente largo

### 3. Manejo de Excepciones con GlobalExceptionHandler

**Decisión:** Se implementa un manejador global de excepciones con `@RestControllerAdvice`.

**Justificación:**
- **Centralización:** Toda la lógica de manejo de errores en un solo lugar
- **Consistencia:** Respuestas de error uniformes en toda la API
- **Códigos HTTP apropiados:**
  - `404 Not Found`: Cuando no se encuentra el precio (ProductPriceNotFoundException)
  - `400 Bad Request`: Parámetros inválidos o faltantes
  - `500 Internal Server Error`: Errores inesperados del servidor

**Implementación:**
```java
@ExceptionHandler(ProductPriceNotFoundException.class)
public ResponseEntity<Map<String, Object>> handleProductPriceNotFoundException(...)
```
### 4. Tests en Tres Niveles

**Decisión:** Se implementan tests unitarios, de integración y end-to-end.

**Justificación:**

La estrategia de testing en tres niveles proporciona una cobertura completa del código desde diferentes perspectivas, garantizando la calidad y el correcto funcionamiento de la aplicación en todos sus aspectos.

**Tests Unitarios:**
- Prueban componentes individuales de forma aislada
- Usan mocks para las dependencias
- Ejecución rápida (milisegundos)
- Detectan errores en la lógica de negocio

**Tests de Integración:**
- Validan la interacción entre capas
- Usan MockMvc con contexto de Spring
- Base de datos H2 real
- Verifican que las capas funcionen correctamente juntas

**Tests E2E:**
- Simulan peticiones HTTP reales con RestAssured
- Validan el comportamiento desde la perspectiva del cliente
- Comprueban el flujo completo de la aplicación

Se podria haber creado un profile("test") en especifico para los tests de integración y e2e, pero al tratarse de un proyecto sencillo con pocos casos de prueba, se ha optado por mantener la configuración simple y directa.

### 5. Separación de DTOs

**Decisión:** Se utilizan DTOs (Data Transfer Objects) separados para la capa REST.

**Justificación:**
- **Desacoplamiento:** El modelo de dominio no depende de la API REST
- **Evolución independiente:** Los cambios en la API no afectan al dominio
- **Seguridad:** Control sobre qué datos se exponen en la API
- **Validación:** Permite añadir validaciones específicas de la capa REST

### 6. Validación de Parámetros con Spring

**Decisión:** Se delega la validación de parámetros a Spring Boot.

**Justificación:**
- **@RequestParam:** Spring valida automáticamente la presencia de parámetros requeridos
- **@DateTimeFormat:** Conversión y validación automática del formato de fecha
- **Type Conversion:** Conversión automática de String a Long
- **Menos código:** No es necesario escribir validaciones manuales

### 7. Uso de Lombok

**Decisión:** Se utiliza Lombok para reducir código boilerplate.

**Justificación:**
- **@Getter/@Setter:** Elimina getters y setters repetitivos
- **@AllArgsConstructor:** Genera constructores automáticamente
- **@NoArgsConstructor:** Necesario para JPA
- **Legibilidad:** El código se centra en la lógica de negocio

**Consideraciones:**
- Se usa de forma moderada, sin abusar de anotaciones complejas
- Facilita el mantenimiento al reducir código redundante
---

### 8. Uso de Trunk-Based Development como GitFlow

**Decisión:** Se utiliza un flujo de trabajo basado en Trunk-Based Development para el control de versiones con Git.

**Justificación:**

Se ha elegido Trunk-Based Development como práctica de control de versiones debido a que encaja con las caracteristicas del proyecto.

**Ventajas para este proyecto:**
- **Simplicidad:** No hay complejidad de gestionar múltiples ramas de larga duración
- **Menos conflictos:** Al integrar frecuentemente, los conflictos de merge son menores
- **Feedback rápido:** Los tests se ejecutan constantemente sobre la rama principal
- **Deploy continuo:** La rama main está siempre en estado desplegable
- **Ideal para proyectos pequeños:** Perfecto para desarrollos individuales o equipos reducidos

**Implementación:**
- La rama `main` contiene siempre código funcional y probado
- Se crean ramas 'feature' para cada nueva funcionalidad y se mergean a main mediante Pull Request
- Los commits son atómicos y tienen mensajes descriptivos
- No se utilizan ramas de release ni hotfix (adecuado para el alcance del proyecto)
- Cuando se genera una version estable, se etiqueta directamente en la rama main

---