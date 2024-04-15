***Diese Beschreibung des MVPs wurde von uns gemeinsam in einem Meeting erarbeitet!***

### Minimum Viable Product (MVP)
#### 1. Einleitung
Das Online-Umfragesystem ist ein einfaches System, das es Nutzern wie Studenten oder Forschende ermöglicht, schnell
und unkompliziert Umfragen zu erstellen und Antworten zu erfassen. Mit dieser Beschreibung wollen wir ein gemeinsames
Verständnis des Minimum Viable Products festhalten.

Unser MVP soll alle Grundfunktionalitäten, die für ein Umfragesystem notwendig sind, zur Verfügung stellen. Dabei lässt es
sich einteilen in eine Probanden- und Forschenden-Ansicht.

#### 2. Zielsetzung
Unser Ziel ist es, zügig unser MVP als ein lauffähiges System zu entwickeln, welches einfach zu testen, anzupassen
und zu erweitern ist. Durch die schnelle Implementierung möchten wir schnell die grundlegende Komponente testbar machen.
Weitere Features aus dem Storyboard möchten wir dann nach und nach hinzufügen und parallel testen.

Darüber hinaus möchten wir ein benutzerfreundliches System zur Verfügung stellen, mit dem Umfragen einfach erstellt werden
können. 

#### 3. Benutzeroberfläche inkl. Funktionalitäten
Forschenden-Ansicht:
![Benutzeroberfläche Forschender](MVP%20Benutzeroberfläche%20Forschender.jpg)

Probanden-Ansicht:
![Benutzeroberfläche Proband](MVP%20Benutzeroberfläche%20Proband.jpg)

#### 4. Technologie
Wir möchten Spring Boot in Kombination mit der JPA als Backend verwenden. Als Datenbank möchten wir vorerst eine 
embedded Datenbank wie bspw. H2 verwenden. Diese hat zum Vorteil, dass sie einfach zu integrieren ist und dass sie eine
grafische Oberfläche auf localhost:8080 zur Verfügung stellt. Für das Frontend möchten wir HTML mit Thymeleaf verwenden.
Für Feinheiten oder spezielle Features kommt evtl. React oder JS in Frage.

#### 5. Nächste Schritte
In den nächsten 1-2 Sprints möchten wir unser MVP implementieren und die Grundfunktionalität schaffen.
Parallel möchten wir dann mit dem Testing anfangen.
