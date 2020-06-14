========================
BUILD OUTPUT DESCRIPTION
========================

When you build an Java application project that has a main class, the IDE
automatically copies all of the JAR
files on the projects classpath to your projects dist/lib folder. The IDE
also adds each of the JAR files to the Class-Path element in the application
JAR files manifest file (MANIFEST.MF).

To run the project from the command line, go to the dist folder and
type the following:

java -jar "ProyectoSO.jar" 

To distribute this project, zip up the dist folder (including the lib folder)
and distribute the ZIP file.

Notes:

* If two JAR files on the project classpath have the same name, only the first
JAR file is copied to the lib folder.
* Only JAR files are copied to the lib folder.
If the classpath contains other types of files or folders, these files (folders)
are not copied.
* If a library on the projects classpath also has a Class-Path element
specified in the manifest,the content of the Class-Path element has to be on
the projects runtime path.
* To set a main class in a standard Java project, right-click the project node
in the Projects window and choose Properties. Then click Run and enter the
class name in the Main Class field. Alternatively, you can manually type the
class name in the manifest Main-Class element.

-----------------------------------------------
COPIA SETTINGS.txt POR SI FALLA
-----------------------------------------------

Configuracion del programa
No modificar nada a excepcion de los numeros
Y en caso de modificar los numeros porfavor asegurarse
de que sean mayores a 0

-Tiempo en segundos,que dura una hora en el programa
tiempo=60*

-Numero de carritos iniciales
carritosIni=10*

-Numero de estantes iniciales
estantesIni=2*

-Numero de cajas registradoras operando
cajas=4*

-Numero maximo de carritos
maxCarritos=20*

-Numero maximo de estantes
maxEstantes=3*

-Numero maximo de cajas registradoras permitidas
maxCajas=7*

-Capacidad maxima de los estantes(No puede ser menor a 3)
capEstantes=10*

fin