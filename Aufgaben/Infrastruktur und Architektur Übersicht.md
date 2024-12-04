### Infrastruktur- und Architektur-Übersicht
Infrastruktur und Abbildung der Model-View-Controller-Architektur:

![Infrastruktur](Infrastruktur%20und%20MVC.jpg)

Mit diesem Markdown möchten wir einen kleinen Überblick über unsere Infrastruktur und das Zusammenspiel der einzelnen 
Komponenten geben.

+ **HTML** entspricht der View-Komponente
+ **Spring Boot** ist ein Framework, mit dem man Controller und Models (uvm.) mittels Annotations einfach implementieren
kann; mit Pfaden wird mitgeteilt, wie man View anspricht
+ Mittels **Java** wird das Model ausgelesen; es werden Objekte/Attribute in das Model hineingesteckt