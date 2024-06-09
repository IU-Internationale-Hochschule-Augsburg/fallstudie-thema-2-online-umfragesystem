### Entwicklung bzw. Verlauf des Datenbankschemas
***Issue #15, #46, #64 und #66***

**Wir haben uns vorerst für alinas Variante entschieden, da wir nach dem Motto "Keep it simple" gehen. Timons ist also nicht falsch, nur
komplizierter.**

Erste Version:
![Erste Version](Datenbankschema%20erste%20Version.png)

Timons Version: (siehe Excel-tabelle in Teams für Beispiele)

![Erste Version](Datenbankschema%20Timon.png)

Alinas Version:

![Erste Version](Datenbankschema%20Umfragesystem_Alina.png)

Überlegung:

Ich denke, wir können uns die Tabellen Ergebnis, die Auflösungstabelle und den Freitext aus Timons Version sparen. Das, was die
Auflösungstabelle macht, machen doch schon die einzelnen Schlüsselpaare, über die die Tabellen verknüpft sind.
Wenn ich da an ein SELECT-Statement denke, dann schreibe ich eine WHERE-Bedingung und komme an alle Daten, die ich
brauche.

Team 2 kann sich ebenfalls über ein SELECT-Statement über unsere Tabellen alle Daten holen, die sie brauchen.
Selbstverständlich müssen wir uns da nochmal absprechen.

Wenn wir der Tabelle "Antwort" bereits für jeden Fragetyp Spalten mitgeben, dann können wir den Freitext auch in
dieselbe Tabelle speichern. Die Fragen inkl. Antwortoptionen für den Umfragebogen setzen sich aus den gleichen Spalten
in der Tabelle "Frage" zusammen, nur dass es dort für Radiobutton mehrere Spalten gibt, denn bei Radiobutton gibt es nur
eine valide Antwort.

Was passiert mit den Spalten der anderen Fragetypen? Die bleiben einfach leer, wir füllen nur die Spalten des
entsprechenden Fragetyps. Das sollte auch performant sein, weil Spalten/Tabellen, in denen nichts eingetragen wurde,
werden einfach nicht beachtet.
Antwort-ID würde ich zur User-ID abändern, da wir diese vermutlich gar nicht brauchen. Die Frage-ID reicht als Verknüpfung eigentlich aus.

Darüber hinaus würde ich die User-Tabelle, wenn ich so an das MVP denke, vorerst abändern zu einer Tabelle, in der wir
die Probanden speichern. Grund dafür ist, dass es im MVP eh erst einmal nur einen "Admin"-User gibt, da es nur eine
Ansicht gibt. Später, wenn es einen Login gäbe/gibt, brauchen wir sowieso zwei Tabellen für die User: einmal für die
Forschenden und einmal für die Probanden. Oder wir verwalten die User weiterhin in einer Tabelle, aber einfach mit
unterschiedlichen Rollen/Rechten.

Mein Vorschlag: Ich persönlich würde erst einmal mit den vier Tabellen anfangen. Wenn wir beim Implementieren merken,
dass die nicht ausreichen oder wir gewisse Daten nicht bekommen, kann man immer noch Tabellen hinzufügen. Wir können
nicht alles ins kleinste Detail planen, lieber fangen wir einfach Mal an und probieren es aus :-).

**Erklärung:**
+ Ein User kann an mehreren Umfragen teilnehmen (1:n)
+ Eine Umfrage kann mehrere Fragen haben (1:n)
+ Eine Frage kann mehrere Antworten haben (1:n)
+ Ein User kann mehrere Fragen beantworten (1:n)
+ Eine Antwort wird von einem User gegeben (n:1) => wichtig hierfür ist die User-ID in der Antwort-Tabelle

Die Idee für die Frage und Antwort-Tabelle ist, dass wir in der Fragetabelle alle Antwortoptionen und in der Antworttabelle alle
tatsächlichen Antworten durch den User speichern.

Wir limitieren die Anzahl der Checkboxen und die der Radiobutton. Wahrscheinlich hat der Admin-User, der die Umfragen erstellt, 10-12
Checkboxen und Radiobuttons bei der Erstellung einer Frage zur Verfügung. Somit verhindern wir, dass Antwortoptionen gespammt werden. Für
den Fragetyp gibt es 10-12 Spalten in der Fragen-Tabelle und nur eine Spalte in der Antwort-Tabelle, da es bei Radiobutton (Single-Choice)
nur eine Antwortmöglichkeit gibt.

Je nach Fragetyp belegen wir dann entsprechende Tabellen. Die Spalten der andern Fragetypen bleiben einfach frei.

So könnte dann eine befüllte Antwort-Tabelle aussehen:
![grafik](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/10c3b781-4587-4031-ab71-e4b946956034)

Zukunftsaussicht: Wenn es einen Login gibt, dann könnte man die User-Tabelle umbauen und eine zusätzliche Spalte "Rolle" hinzufügen. Dort
unterscheiden wir dann beispielsweise zwischen "Admin" (= Umfrageersteller/Forschender) und "Teilnehmer" (= Umfrageteilnehmer/Proband).
