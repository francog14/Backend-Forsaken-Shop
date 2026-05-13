# Resumen de Implementación - Backend-Forsaken-Shop Microservicios

## ✅ Microservicios Completados

### 1. **Usuario-Microservicio** (Puerto 7076)
- Entity: `Usuario` (id_usuario, run, nombre, email, id_rol)
- Controller: `UsuarioController` (GET, POST, PUT, DELETE)
- Service: `UsuarioService` - Valida rol mediante WebClient
- Se conecta a: **Rol-Microservicio** (7075)
- Base de datos: `usuario_db`

### 2. **Venta-Microservicio** (Puerto 7077)
- Entity: `Venta` (id_venta, fecha, total, id_usuario)
- Controller: `VentaController` (GET, POST, PUT, DELETE)
- Service: `VentaService` - Valida usuario mediante WebClient
- Se conecta a: **Usuario-Microservicio** (7076)
- Base de datos: `venta_db`

### 3. **Detalle-Venta-Microservicio** (Puerto 7078)
- Entity: `DetalleVenta` (id_detalle_venta, id_venta, id_prenda, cantidad, precio_unitario)
- Controller: `DetalleVentaController` (GET, POST, PUT, DELETE)
- Service: `DetalleVentaService` - Valida venta y prenda mediante WebClient
- Se conecta a: **Venta-Microservicio** (7077) y **Prenda-Microservicio** (7580)
- Base de datos: `detalle_venta_db`

### 4. **Mensajeria-Microservicio** (Puerto 7079)
- Entity: `Mensaje` (id_mensaje, id_usuario, asunto, contenido, fecha_envio)
- Controller: `MensajeController` (GET, POST, PUT, DELETE)
- Service: `MensajeService` - Valida usuario mediante WebClient
- Se conecta a: **Usuario-Microservicio** (7076)
- Base de datos: `mensajeria_db`

## 🔗 Mapa de Comunicación entre Microservicios

```
Rol-Microservicio (7075)
    ↑
    └─── Usuario-Microservicio (7076) ──┬─── Venta-Microservicio (7077)
                                         │        ↑
                                         │        └─── Detalle-Venta (7078)
                                         │
                                         └─── Mensajeria-Microservicio (7079)

Categoría-Microservicio (7070)
    ↑
    └─── Prenda-Microservicio (7580)
            ↑
            └─── Detalle-Venta (7078)
```

## 📋 Pasos Siguientes

### 1. Crear las Bases de Datos
Ejecuta el script `setup_databases.sql` en tu servidor MySQL:
```bash
mysql -u root -p < setup_databases.sql
```

### 2. Compilar Todos los Microservicios
En cada carpeta de microservicio, ejecuta:
```bash
mvn clean install
```

### 3. Iniciar los Microservicios (en orden)
1. **Categoría-Microservicio**
   ```bash
   mvn spring-boot:run -Dspring-boot.run.folder=Categoria-Microservicio/Microservicio-Categoria
   ```

2. **Rol-Microservicio**
   ```bash
   mvn spring-boot:run -Dspring-boot.run.folder=Rol-Microservicio/Microservicio-Rol
   ```

3. **Prenda-Microservicio**
   ```bash
   mvn spring-boot:run -Dspring-boot.run.folder=Prenda-Microservicio/Microservicio-Prenda
   ```

4. **Usuario-Microservicio**
   ```bash
   mvn spring-boot:run -Dspring-boot.run.folder=Usuario-Microservicio/Microservicio-Usuario
   ```

5. **Venta-Microservicio**
   ```bash
   mvn spring-boot:run -Dspring-boot.run.folder=Venta-Microservicio/Microservicio-Venta
   ```

6. **Detalle-Venta-Microservicio**
   ```bash
   mvn spring-boot:run -Dspring-boot.run.folder=Detalles-Ventas-Microservicio/Microservicio-Detalle-Venta
   ```

7. **Mensajeria-Microservicio**
   ```bash
   mvn spring-boot:run -Dspring-boot.run.folder=Mensajeria-Microservicio/Microservicio-Mensajeria
   ```

### 4. Verificar Endpoints

#### Usuario Service (http://localhost:7076)
- GET `/usuarios` - Obtener todos los usuarios
- GET `/usuarios/{id}` - Obtener usuario por ID
- POST `/usuarios` - Crear nuevo usuario
- PUT `/usuarios` - Actualizar usuario
- DELETE `/usuarios/{id}` - Eliminar usuario

#### Venta Service (http://localhost:7077)
- GET `/ventas` - Obtener todas las ventas
- GET `/ventas/{id}` - Obtener venta por ID
- POST `/ventas` - Crear nueva venta
- PUT `/ventas` - Actualizar venta
- DELETE `/ventas/{id}` - Eliminar venta

#### Detalle-Venta Service (http://localhost:7078)
- GET `/detalles-ventas` - Obtener todos los detalles
- GET `/detalles-ventas/{id}` - Obtener detalle por ID
- POST `/detalles-ventas` - Crear nuevo detalle
- PUT `/detalles-ventas` - Actualizar detalle
- DELETE `/detalles-ventas/{id}` - Eliminar detalle

#### Mensajeria Service (http://localhost:7079)
- GET `/mensajes` - Obtener todos los mensajes
- GET `/mensajes/{id}` - Obtener mensaje por ID
- POST `/mensajes` - Crear nuevo mensaje
- PUT `/mensajes` - Actualizar mensaje
- DELETE `/mensajes/{id}` - Eliminar mensaje

## 🧪 Pruebas de Integración Ejemplo

### 1. Crear un Usuario (requiere un Rol existente)
```json
POST http://localhost:7076/usuarios
{
  "run": "12345678-9",
  "nombre": "Juan Pérez",
  "email": "juan@example.com",
  "id_rol": 1
}
```

### 2. Crear una Venta (requiere un Usuario existente)
```json
POST http://localhost:7077/ventas
{
  "fecha": "2024-05-09T10:30:00",
  "total": 95000,
  "id_usuario": 1
}
```

### 3. Crear un Detalle-Venta (requiere una Venta y Prenda existentes)
```json
POST http://localhost:7078/detalles-ventas
{
  "id_venta": 1,
  "id_prenda": 1,
  "cantidad": 2,
  "precio_unitario": 15000
}
```

### 4. Crear un Mensaje (requiere un Usuario existente)
```json
POST http://localhost:7079/mensajes
{
  "id_usuario": 1,
  "asunto": "Bienvenida",
  "contenido": "Bienvenido a nuestra tienda",
  "fecha_envio": "2024-05-09T10:30:00"
}
```

## 📁 Estructura de Carpetas Completada

Cada microservicio ahora tiene:
```
Microservicio-XXX/
├── pom.xml (actualizado con WebClient)
├── src/main/
│   ├── java/com/xxx/Microservicio_XXX/
│   │   ├── controller/ (XxxController.java)
│   │   ├── service/ (XxxService.java)
│   │   ├── models/
│   │   │   ├── entities/ (Entity.java)
│   │   │   ├── dto/ (DTOs para inter-comunicación)
│   │   │   └── request/ (Request DTOs)
│   │   ├── repositories/ (XxxRepository.java)
│   │   └── config/ (WebClientConfig.java)
│   └── resources/
│       └── application.properties (actualizado con puerto y BD)
└── target/ (compilación)
```

## ⚙️ Configuración Importante

- **Base de datos**: MySQL 5.7+
- **Usuario BD**: root
- **Contraseña BD**: system
- **Host BD**: localhost:3306
- **Java**: 21+
- **Spring Boot**: 4.0.6
- **Hibernate**: Auto-create tables (ddl-auto=update)

## 🔍 Validaciones Implementadas

Cada microservicio valida sus dependencias:
- Usuario valida que el rol exista en Rol-Microservicio
- Venta valida que el usuario exista en Usuario-Microservicio
- Detalle-Venta valida que la venta y prenda existan
- Mensajeria valida que el usuario exista en Usuario-Microservicio
- Prenda valida que la categoría exista en Categoría-Microservicio

Si la validación falla, se retorna un error 503 (Service Unavailable) o el error específico del microservicio de dependencia.

## 📝 Notas Importantes

1. Los puertos deben estar disponibles (no tener otros procesos escuchando)
2. MySQL debe estar corriendo en localhost:3306
3. Se recomienda iniciar los microservicios independientes primero (Categoría, Rol)
4. Luego iniciar los que dependen de otros
5. El patrón de WebClient realiza llamadas síncronas (block()), se puede optimizar con Mono/Flux para llamadas asíncronas

## ✨ Próximos Pasos Sugeridos

1. Implementar API Gateway para enrutar las llamadas
2. Agregar autenticación/autorización (JWT)
3. Implementar circuit breaker para resiliencia
4. Agregar logging y monitoring
5. Containerizar con Docker
6. Implementar cache (Redis)
7. Agregar tests unitarios e integración
