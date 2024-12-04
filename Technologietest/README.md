### Fazit des Technologietests und Ausführung der Programmcodes

Wir haben bisher einen Technologietest mit Python und Java Spring Boot durchgeführt.
Beide Sprachen haben wir mit einfachem HTML-Code sowie einer embedded Datenbank wie SQLite3 oder H2 kombiniert.

Bei Python ließ sich der HTTP-Webserver ohne weitere Configs einfach instanziieren und auch die Datenbank (SQLite3)
ließ sich in wenigen Schritten anbinden und ansprechen. Es war lediglich ein Import und dann der Aufbau einer Connection
zur Datenbank notwendig. Daraufhin können Tabellen mit kleinen "CREATE TABLE"-Queries erzeugt und Daten mit "INSERT INTO" hinzugefügt werden.
Der HTML-Code ließ sich ebenfalls einfach hinzufügen (entweder als String oder wie im committeten
Beispiel als extra .html-Datei) und ausführen. Für das Frontend des tatsächlichen Projekts könnte man auch Angular oder Flutter verwenden.

Ein Vorteil der SQLite3 Datenbank ist, dass man für die Datenbank keinen eigenen Server aufsetzen/instanziieren muss,
denn diese lässt sich einfach in den Programmcode einbetten. Ebenso ist Python gut geeignet für einen Webserver,
da der Server in dieser Programmiersprache wenig Konfiguration benötigt.

Der Python-Code lässt sich über eine IDE wie beispielsweise PyCharm ausführen, indem man auf Run klickt und dann im Browser http://localhost:8080/ eintippt.
Daraufhin sollte ein kleines HTML-Konstrukt erscheinen und man kann eine Texteingabe tätigen, welche dann in der Datenbank korrekt gespeichert wird.

Spring Boot hingegen war zu Beginn hinsichtlich der Configs etwas aufwendiger, aber die eigentliche Integration
von dem HTML Code und der Datenbank (H2) war einfach. Durch die JPA (Java/Jakarta Persistence API) haben wir die Möglichkeit, dass wir
keine SQL-Queries schreiben müssen, denn das Erzeugen von Tabellen und das Einfügen von Daten übernimmt die API für
uns. Spring Boot unterstützt uns mit entsprechenden Annotations, die JPA einfach verwenden zu können. Darüber hinaus war es in wenigen Schritten möglich, eine MVC-Architektur umzusetzen, was den Code später einfacher adaptierbar und erweiterbar macht.

Ein Vorteil von der H2 Datenbank ist die grafische Oberfläche, bei der man sich über http://localhost:8080/h2-console mit den 
Anmeldedaten aus der .yaml-Datei anmelden kann. Dort kann man auch eigene kleine Datenbank-Abfragen machen und sich die 
Struktur der Datenbank selbst anschauen.

Der Java-Code lässt sich über eine IDE wie beispielsweise IntelliJ ausführen, indem man das Programm mit Gradle baut, dann auf Run klickt und dann
http://localhost:8080/umfrage im Browser aufruft. Dort kann man dann ebenfalls wie in Python eine Eingabe tätigen. Zum Login der H2-Datenbank kommt man über folgenden Link: http://localhost:8080/h2-console.
Wichtig ist, dass man folgende URL angibt: jdbc:h2:mem:testdb und sich die Anmeldedaten aus der .yaml-Datei holt.

Viel Spaß beim Ausprobieren ☺!
