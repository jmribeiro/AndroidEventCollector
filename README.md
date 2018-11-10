# Android Event Collector [pt-PT]

##############################################################################################################

DISCLAIMER:
	
	> Para correr a ferramenta é necessário:

	 - ADB 	(Android Debug Bridge)	(Android SKD)
	 - SQLITE3 						(Android SKD)
	 - Um telemóvel android rooted* ligado por usb, uma imagem de um filesystem de um telemóvel android ou uma máquina virtual emulada a correr no computador;
	 - Java 1.8

* Quanto à extração de dados gostaríamos de ter conseguido fazer uma procura dos ficheiros consoante a versão do telefone, 
por exemplo possuir um mapa de localizações e ir procurando até encontrar o ficheiro pretendido em cada versão diferente da Android.
Como não tivemos oportunidade para tal, a ferramenta está testada para a versão Android 4.4.2.

** Qualquer sistema operativo com Java, ADB e SQLITE3 deve correr a ferramenta, contudo esta apenas foi testada em Windows (10)

##############################################################################################################

1) Compilar a ferramenta:

	$ mvn clean package

2) Correr

	$ mvn exec:java

Alternativamente - Deploy do Jar:

	A) Compilar a ferramenta:

		$ mvn clean package
		> target/AndroidEventCollector-1.0-SNAPSHOT-jar-with-dependencies.jar

	B) Correr a ferramenta:

		$ java -jar AndroidEventCollector-1.0-SNAPSHOT-jar-with-dependencies.jar

##############################################################################################################

PASTAS:

resources:
	extractions - Pasta onde as extrações são guardadas (podendo serem carregadas noutra execução)
	images		- Pasta onde se podem colocar imagens de um sistema de ficheiros android
	logs		- Pasta onde é guardado o Log de execução, onde são registados os passos que o utilizador seguiu
	tmp 		- Pasta onde são colocados ficheiros temporários em tempo de execução (eliminados ao terminar)
