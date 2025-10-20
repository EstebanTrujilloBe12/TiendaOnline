nn Cómo correr el proyecto con H2 por defecto
n Configuración por defecto (H2 en memoria)
El proyecto está configurado para usar la base de datos H2 en memoria, sin necesidad de instalar
ningún sistema externo como MySQL o PostgreSQL. Al ejecutar la aplicación, la base se crea
automáticamente con todas las tablas.
Archivo: src/main/resources/application.properties
# Puerto del servidor
server.port=8080
# Configuración de la base de datos H2
spring.datasource.url=jdbc:h2:mem:tienda
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
# Crear la base en memoria al iniciar
spring.jpa.hibernate.ddl-auto=create-drop
# Mostrar SQL en consola
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
# Activar la consola H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
n Pasos para ejecutar el proyecto
1. Abrir el proyecto en IntelliJ IDEA o VS Code. 2. Ejecutar la clase principal (por ejemplo
TiendaOnlineApplication.java). 3. Esperar a que aparezca el mensaje: 'H2 console available at
/h2-console'. 4. Abrir el navegador y entrar a: http://localhost:8080/h2-console 5. Conectarse a la
base de datos con los siguientes datos: - JDBC URL: jdbc:h2:mem:tienda - User Name: sa -
Password: (vacío) - Clic en Connect
n Verificación de las tablas
Una vez conectado, se pueden ver las tablas creadas automáticamente: CLIENTES,
DIRECCIONES, CATEGORIAS, PRODUCTOS, PEDIDOS, ITEMS_PEDIDO, etc. Desde la
consola H2 puedes ejecutar consultas como:
SELECT * FROM CLIENTES;
SELECT * FROM PRODUCTOS;
SELECT * FROM PEDIDOS;
SELECT * FROM ITEMS_PEDIDO;
