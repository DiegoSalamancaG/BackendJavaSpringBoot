# 🛒 Backend de Ecommerce con Spring Boot, JWT y PostgreSQL

Este proyecto es un backend RESTful para una tienda en línea, construido con **Spring Boot**. Implementa autenticación segura con JWT, documentación con Swagger, arquitectura en capas y buenas prácticas modernas de desarrollo backend. Ideal como base sólida para un sistema de ecommerce completamente funcional. No solo se enfoca en las características de un ecommerce, sino también en aplicar buenas prácticas de diseño de software. Representa **mi primer proyecto utilizando una arquitectura hexagonal**, buscando una clara separación de la lógica de negocio del resto de las capas (aplicación e infraestructura).



---

## ✨ Características Principales

### 👤 Gestión de Usuarios
- Registro y autenticación con JWT
- CRUD completo de usuarios (ADMIN)
- Validaciones con anotaciones y manejo de errores

### 🛒 Gestión de Productos
- Consulta pública de productos
- Arquitectura lista para implementar CRUD completo

### 🔐 Seguridad
- JWT con roles (ADMIN y USER)
- Spring Security con filtros personalizados

### 🧰 Utilidades
- Documentación Swagger/OpenAPI
- Uso de DTOs con MapStruct

---

## 🚀 Tecnologías utilizadas

**Lenguaje y Framework:**
- Java 17
- Spring Boot 3
- Spring Security

**Base de Datos y Persistencia:**
- PostgreSQL
- Spring Data JPA

**Arquitectura y Utilidades:**
- MapStruct (DTOs)
- Swagger / OpenAPI

**Pruebas (en desarrollo):**
- JUnit / Mockito

---

## 🧩 Metodologías y Patrones de Diseño

- **Metodología Ágil (Scrum/Kanban):** Enfoque iterativo y de mejora continua.
- **Arquitectura en Capas (Layered Architecture):**
    - **Controller:** Manejo de peticiones HTTP.
    - **Service:** Lógica de negocio.
    - **Repository:** Interacción con la base de datos.
- **Patrones de Diseño:**
    - **Repository Pattern**
    - **Service Layer Pattern**
    - **DTOs (Data Transfer Objects)**
- **Control de Versiones:** Uso de Git para gestión del código fuente.

---

## 📁 Estructura del proyecto

La aplicación está organizada siguiendo principios de arquitectura limpia (Clean Architecture) y DDD.

```
src
└── main
    └── java
        └── com.example.proyectoEcommerce
            ├── application
            │   ├── dto               # Clases de transferencia de datos (DTOs)
            │   ├── mapper            # MapStruct: Mapeos entre entidades y DTOs
            │   └── service           # Implementaciones de lógica de aplicación
            │
            ├── domain
            │   ├── model             # Entidades JPA (modelo de negocio)
            │   ├── repository        # Interfaces para repositorios
            │   └── service           # Interfaces para la lógica de negocio
            │
            ├── infrastructure
            │   ├── controller        # Controladores REST
            │   └── security
            │       ├── dto           # DTOs relacionados a seguridad/autenticación
            │       ├── jwt           # Clases para manejo de JWT
            │       ├── service       # Servicio de autenticación/autorización
            │       └── utils         # Utilidades de seguridad (e.g. generación de tokens)
            │
            └── ProyectoEcommerceApplication.java # Clase principal
```

---

## 🔐 Seguridad

El proyecto utiliza **JWT (JSON Web Token)** para autenticación. La configuración se encuentra en:

- `SecurityConfig.java`
- `JwtAuthFilter.java`
- `JwtUtil.java`

Rutas protegidas:
- Rutas como `/api/v1/users/**` están protegidas y requieren rol `ADMIN`.
- Rutas públicas como `/api/v1/auth/**` y `GET /api/v1/products/**` están permitidas sin autenticación.

---

## 📦 Endpoints principales

| Método | Ruta                    | Descripción                    |
|--------|-------------------------|--------------------------------|
| POST   | `/api/v1/auth/register` | Registro de nuevo usuario      |
| POST   | `/api/v1/auth/login`    | Login y generación de token    |
| GET    | `/api/v1/users`         | Listado de usuarios (ADMIN)    |
| GET    | `/api/v1/products`      | Listado de productos (público) |
| GET    | `/api/v1/collections`   | Listado de colecciones (ADMIN) |
| GET    | `/api/v1/categories`    | Listado de categorías (ADMIN)  |


---
## 🚀 Cómo Ejecutar el Proyecto

1. **Clonar el repositorio:**

```bash
git clone https://github.com/DiegoSalamancaG/BackendJavaSpringBoot.git
cd BackendJavaSpringBoot
```

2. **Configurar la base de datos (PostgreSQL):**

- Crea una base de datos llamada `e-commerce`
- Edita el archivo `src/main/resources/application.properties` con tus credenciales:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/e-commerce
spring.datasource.username=tu_usuario_db
spring.datasource.password=tu_password_db
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.open-in-view=false
# Desactivar seguridad (solo para desarrollo)
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
```

3. **Compilar y ejecutar con Maven:**

```bash
./mvnw spring-boot:run
```

> O bien, abre el proyecto en **IntelliJ IDEA**, haz clic derecho en `ProjectApplication.java` y selecciona “Run”.

4. **Acceder a la aplicación:**

Abre tu navegador y ve a `http://localhost:8080`

---

## 🧾 Documentación Swagger

La API está documentada con Swagger:

- `http://localhost:8080/swagger-ui/index.html`

---

## 📌 Mejoras Futuras

- ✅ CRUD completo para productos
- [ ] Manejo global de excepciones con `@ControllerAdvice`
- [ ] Validación de formularios con `@Valid`
- [ ] Pruebas unitarias y de integración con JUnit/Mockito
- [ ] Auditoría de entidades (fecha y usuario de creación/modificación)
- [ ] CRUD para órdenes de compra y registro de ventas

---

## 🤝 Contribuciones

Este proyecto está abierto a mejoras. Si deseas contribuir:
1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`)
3. Realiza tus cambios y haz commit (`git commit -m 'Agrega nueva funcionalidad'`)
4. Envía un pull request.

¡Toda contribución es bienvenida!

---

## 📬 Contacto

Desarrollado por Diego Salamanca  
(Este README está diseñado para facilitar el mantenimiento y comprensión del proyecto)