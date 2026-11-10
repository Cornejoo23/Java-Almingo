🎱 ALMINGO S.L. – Bingo Multijugador de Sostenibilidad

Proyecto desarrollado por ALMINGO S.L. como primer encargo de la nueva etapa de la empresa. La aplicación consiste en un bingo multijugador basado en conceptos de sostenibilidad, permitiendo la participación simultánea de varios jugadores mediante cartones personalizados y extracción aleatoria de números.

📖 Contexto del proyecto

Tras la renovación tecnológica y organizativa de ALMINGO S.L., el primer proyecto interno consiste en desarrollar una aplicación de bingo educativo centrada en la sostenibilidad.

Los objetivos principales son:

Aplicar programación orientada a objetos en Java.
Gestionar múltiples jugadores en una misma partida.
Simular el funcionamiento real de un bingo.
Trabajar en equipo utilizando Git y GitHub.
Gestionar datos mediante ficheros externos.

🎯 Objetivos del programa
Permitir la participación de varios jugadores.
Generar cartones de bingo de forma aleatoria.
Simular un bombo que extraiga números sin repetir.
Controlar turnos y estado de la partida.
Registrar el historial de números extraídos.
Utilizar términos relacionados con la sostenibilidad para la dinámica del juego.

🧩 Funcionalidades principales
Clase	Descripción
bingo.java	Clase principal que inicia y controla la aplicación
bombo.java	Genera números aleatorios sin repetir
carton.java	Gestiona la creación y comprobación de cartones
jugador.java	Almacena los datos y estado de cada jugador
Partida.java	Controla el flujo de la partida y los turnos

📁 Estructura del proyecto
Plain Text
retoAlmingo/
│
├── src/
│ └── retoAlmingo/
│ ├── bingo.java
│ ├── bombo.java
│ ├── carton.java
│ ├── jugador.java
│ └── Partida.java
│
├── historial.txt
├── numeros.txt
├── respuestas.txt
├── sostenibilidad.txt
├── Turno.txt
│
└── README.md

📄 Ficheros auxiliares
Archivo	Función
historial.txt	Guarda el histórico de números generados
numeros.txt	Almacena los números disponibles del bombo
respuestas.txt	Registra respuestas y resultados
sostenibilidad.txt	Contiene conceptos relacionados con sostenibilidad
Turno.txt	Controla el turno actual de los jugadores

☕ Tecnologías utilizadas
Java 21
Eclipse IDE
Programación Orientada a Objetos
Git
GitHub
Ficheros de texto (.txt)

🚀 Instalación y ejecución
1. Clonar el repositorio
Shell
git clone <URL_DEL_REPOSITORIO>
2. Abrir Eclipse
Plain Text
File → Import → Existing Projects into Workspace
 
3. Seleccionar el proyecto
Plain Text
retoAlmingo
4. Ejecutar

Ejecutar la clase principal:

Plain Text
bingo.java

mediante:

Plain Text
Run As → Java Application

🔀 Control de versiones y trabajo en equipo

Todo el desarrollo se realiza mediante Git y GitHub.

Ramas del proyecto
Plain Text
main
Cornejo
Ayoub
Aitor
Develop
Flujo de trabajo
Plain Text
1. Pull de la rama personal
2. Desarrollo de la funcionalidad
3. Commit
4. Push
5. Pull Request hacia Develop
6. Revisión
7. Merge en main

📝 Convención de commits
Plain Text
feat: añade sistema de turnos
feat: genera cartones aleatorios
fix: corrige error en comprobación de bingo
refactor: reorganiza la clase Partida
docs: actualiza README

👥 Equipo de desarrollo
Urko Cornejo
Ayoub Tahsaint
Aitor Vela

📬 Contacto

ALMINGO S.L.

📧 almingo@gmail.com

🌐 https://www.almingo.com

📄 Licencia

© ALMINGO S.L. Todos los derechos reservados.