### **Welcome to...**
![grafik](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/e2e47afa-c4e6-4d0d-8a18-74298c9bd254)
-------------------------------------------------------------------------------------------------------------

### Gliederung der Entwickler-Dokumentation:
1. Modulbeschreibungen
   1. Belegte URLs
   2. Entities
   3. Repositories
   4. Controller und Services
2. Aufbau des Systems
   1. Architektur
   2. Datenbank
3. Inbetriebnahme


## 1. Modulbeschreibungen
### i. Belegte URLs

| URL                                           | Beschreibung des Screens                                                                                                                                                                                                                                                                                                                                                      |
|-----------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| http://localhost:8080/participant-view        | Dies ist die dynamische Fragebogen-Seite für die Umfrageteilnehmer. Über diese können sie sich durch den ganzen Umfragebogen klicken.                                                                                                                                                                                                                                         |
| http://localhost:8080/login                   | Dies ist die Login-Page. Die aktuelle **Startseite des MVPs**. Über diesen Screen loggt sich ein bestehender Nutzer ein                                                                                                                                                                                                                                                       |
| http://localhost:8080/register                | Dies ist die Registrieren-Page. Dort legt ein Nutzer einen neuen User an                                                                                                                                                                                                                                                                                                      |
| http://localhost:8080/logout                  | Dies ist die URL für das Ausloggen des Users. Diese wird aufgerufen, wenn der User auf den Logout-Button in der Umfrage-Ansicht klickt                                                                                                                                                                                                                                        |
| http://localhost:8080/survey-admin            | Dies ist die Umfrage-Ansicht eines eingeloggten Users                                                                                                                                                                                                                                                                                                                         |
| http://localhost:8080/questions-view          | Dies ist die Fragen-Ansicht                                                                                                                                                                                                                                                                                                                                                   |
| http://localhost:8080/add-survey              | Dies ist die Umfrage-hinzufügen-Ansicht                                                                                                                                                                                                                                                                                                                                       |
| http://localhost:8080/add-question            | Dies ist die Frage-hinzufügen-Ansicht                                                                                                                                                                                                                                                                                                                                         |
| http://localhost:8080/h2-console              | **VORSICHT, das ist eine alte URL**: Dies ist die grafische Oberfläche für die H2-Datenbank                                                                                                                                                                                                                                                                                   |
| http://localhost:8080/question-save           | Über diese URL speichern wir die Daten einer bestehenden Frage oder erzeugen eine neue Frage                                                                                                                                                                                                                                                                                  |
| http://localhost:8080/survey-save             | Über diese URL speichern wir die Daten einer bestehenden Umfrage oder erzeugen eine neue Umfrage                                                                                                                                                                                                                                                                              |
| http://localhost:8080/save-registration       | Über diese URL werden die Daten aus dem RegisterForm in die Datenbank geschrieben. Darüber hinaus passiert hier Error Handling                                                                                                                                                                                                                                                |
| http://localhost:8080/save-user-settings      | Über diese URL können User ihre Konto-Einstellungen bearbeiten. Die Daten werden in der Datenbank aktualisiert. Darüber hinaus wird hier auch für Error Handling gesorgt, wenn eine neu eingetippte E-Mail bereits registriert ist, wenn das alte Passwort nicht mit dem in der Datenbank übereinstimmt und wenn die beiden neu eingetippten Passwörter nicht übereinstimmen. |
| http://localhost:8080/button-question-handler | Über diese URL behandeln wir das Klicken verschiedener Buttons in der Fragen-Ansicht                                                                                                                                                                                                                                                                                          |
| http://localhost:8080/button-survey-handler   | Über diese URL behandeln wir das Klicken verschiedener Buttons in der Umfrage-Ansicht                                                                                                                                                                                                                                                                                         |
| http://localhost:8080/user-actions            | Über diese URL behandeln wir das Klicken verschiedener Buttons im Header der Umfrage-Ansicht (Logout oder Konto-Einstellungen)                                                                                                                                                                                                                                                |
| http://localhost:8080/error                   | Dies ist die individualisierte Error-Page                                                                                                                                                                                                                                                                                                                                     |

**Hinweis:** Einige URLs erzeugen eine sichtbare Ansicht, die Handler-URLs sowie auch /user-actions und /survey-save sowie
/question-save und weitere sind nur zum Ausführen verschiedener Aktionen wie das Speichern oder Aktualisieren von Daten in
der Datenbank. /error ist die URL der Error-Page.


### ii. Entities
**Die Klassen im entity-Ordner repräsentieren die Tabellen unserer Datenbank**

| Java-Klasse | Beschreibung                                                                                                                                          |
|-------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| Answer      | Datenbank-Tabelle zum Speichern der Antworten der Umfrage-Teilnehmer. Daher wird auch die User-Id in der Tabelle gespeichert.                         |
| Question    | Datenbank-Tabelle zum Speichern der Fragen und Antwortoptionen. Diese Tabelle benötigen wir zum Erzeugen des Fragebogens.                             |
| Survey      | Datenbank-Tabelle zum Speichern der Umfragen.                                                                                                         |
| User        | Datenbank-Tabelle zum Verwalten der User. Über eine Rollenvergabe lassen sich Teilnehmer und Umfrage-Ersteller in Zukunft voneinander trennen.        |


### iii. Repositories
Die Interfaces im repository-Ordner definieren Operationen zum Abrufen, Speichern, Löschen und Aktualisieren von Daten.
Diese Interfaces erben von CrudRepository. Crud steht für Create-Read-Update-Delete Operationen. Durch die Vererbung werden die
Crud-Operationen automatisch implementiert. Für jede Entity gibt es ein solches Interface/Repository.

| Repository         | Beschreibung                                                                                                                                                                                                                                                                                                        |
|--------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| AnswerRepository   | Das AnswerRepository dient als Schnittstelle für den Datenzugriff auf die Answer-Entity in der PostgreSQL-Datenbank. Es stellt die Methode deleteByQuestionId(Long questionId) zur Verfügung, die es dem Entwickler ermöglicht, effizient und ohne viele Zeilen Code Antworten auf Basis der QuestionId zu löschen. |
| QuestionRepository | Das QuestionRepository dient als Schnittstelle für den Datenzugriff auf die Question-Entity in der PostgreSQL-Datenbank. Es stellt die Methode findBySurveyId(Long surveyId) zur Verfügung, die es ermöglicht, Fragen auf einfache Weise über die SurveyId zu finden.                                               |
| SurveyRepository   | Das SurveyRepository dient als Schnittstelle für den Datenzugriff auf die Survey-Entity in der PostgreSQL-Datenbank. Es stellt die Methode findAllByUserId(Long userId) zur Verfügung, die es dem Entwickler ermöglicht, Umfragen zu einer UserId zu finden.                                                        |
| UserRepository     | Das UserRepository dient als Schnittstelle für den Datenzugriff auf die User-Entity in der PostgreSQL-Datenbank. Es stellt die Methoden findByUsername(String username), findByEmail(String email) und getUserByUsername(String username) zur Verfügung.                                                            |


### iv. Controller und Services
| Java-Klassen                | Beschreibung                                                                                                                                                                                                                                                                                                                                                                                                                                 |
|-----------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| SurveyApplication           | Die Application-Klasse dient als zentrale Einstiegsklasse für eine Spring Boot-Anwendung. Dabei ist die main-Methode die auszuführende Methode.                                                                                                                                                                                                                                                                                              |
| LoginController             | Ist eine Klasse, die login-spezifische HTTP-Anfragen entgegennimmt, sie verarbeitet und eine entsprechende HTTP-Antwort zurückgibt. Über Annotationen wie @GetMapping und @PostMapping findet das Routing von HTTP-Requests statt.                                                                                                                                                                                                           |
| SurveyController            | Ist eine Klasse, die umfragen-spezifische HTTP-Anfragen entgegennimmt, sie verarbeitet und eine entsprechende HTTP-Antwort zurückgibt. Über Annotationen wie @GetMapping und @PostMapping findet das Routing von HTTP-Requests statt.                                                                                                                                                                                                        |
| QuestionController          | Ist eine Klasse, die fragen-spezifische HTTP-Anfragen entgegennimmt, sie verarbeitet und eine entsprechende HTTP-Antwort zurückgibt. Über Annotationen wie @GetMapping und @PostMapping findet das Routing von HTTP-Requests statt.                                                                                                                                                                                                          |
| ParticipantSurveyController | Ist eine Klasse, die HTTP-Anfragen über den Probanden/Teilnehmer-Screen entgegennimmt, sie verarbeitet und eine entsprechende HTTP-Antwort zurückgibt. Über Annotationen wie @GetMapping und @PostMapping findet das Routing von HTTP-Requests statt.                                                                                                                                                                                        |
| AnswerMapper                | Answer-Mapper ist eine Mapper-Klasse, welche die Eingaben aus der Benutzeroberfläche auf die Datenbank-Entität "Answer" mappt. Der Grund für das Erstellen der Mapper-Klasse war, dass die Antwortoptionen von den questionTypes "Radiobutton" und "Checkbox" in den gleichen Datenbankfeldern gespeichert werden. Aus technischen Gründen können diese nicht in denselben UI-Feldern erfasst werden. Daher ist kein 1-zu-1-Mapping möglich. |
| SurveyErrorController       | Über die Klasse wird bei einem auftretenden Fehler eine individualisierte Error-Page geladen.                                                                                                                                                                                                                                                                                                                                                |
| QuestionService             | Dies ist eine Service-Klasse, die das Löschen von Fragen und das Laden der aktuellen Fragen in der Fragen-Ansicht übernimmt.                                                                                                                                                                                                                                                                                                                 |
| SurveyService               | Dies ist eine Service-Klasse, die das Löschen von Umfragen übernimmt.                                                                                                                                                                                                                                                                                                                                                                        |
| UserService                 | Diese Klasse implementiert das Interface UserDetailsService. Die Funktion loadUserByUsername(String username) wird verwendet, um Benutzerinfos basierenf auf dem Benutzernamen abzurufen.                                                                                                                                                                                                                                                    |
| JasyptConfig                | Dies ist eine Konfigurationsklasse für die Encryption mit Jasypt.                                                                                                                                                                                                                                                                                                                                                                            |
| WebSecurityConfig           | Diese Klasse definiert die Sicherheitskonfiguration der Anwendung. Die Methode securityFilterChain() konfiguriert dabei Sicherheitsregeln und Filterketten. Es wird festgelegt, welche URLs öffentlich zugänglich sind, welche eine Authentification erfordern und wie Login- und Logout-Prozess aussehen                                                                                                                                    |

SurveyView, QuestionView, SingleQuestionView, QuestionForm, ParticipantSurveyView, LoginForm, RegisterForm, UserSettingsForm
und SurveyForm sind, ebenfalls wie der AnswerMapper, Mapper zum Konvertieren von Daten zwischen verschiedenen Objektstrukturen.
Die @Data-Annotation generiert automatisch Getter, Setter, hashCode, equals und toString-Methoden.

## 2. Aufbau des Systems
### i. Architektur
Wir haben uns bewusst für Spring Boot und dessen Model-View-Controller-Architektur entschieden.

#### Infrastruktur und Abbildung der Model-View-Controller-Architektur:

![Infrastruktur](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/7dc36199-4d94-4a4e-b007-dfb503b8930a)

+ **HTML** entspricht, gemeinsam mit JS, der View-Komponente (darüber hinaus verwenden wir auch CSS und Thymeleaf)
+ **Thymeleaf** ist eine serverseitige Java-Template-Engine und wird für die Erstellung dynamischer Webseiten (= Views) verwendet
+ **Spring Boot** ist ein Framework, mit dem man Controller und Models (uvm.) mittels Annotations einfach implementieren
  kann; mit Pfaden wird mitgeteilt, wie man die View-Komponente anspricht
+ Mittels **Java** wird das Model ausgelesen; es werden Objekte/Attribute in das Model hineingesteckt und an die View-Komponente übergeben

### ii. Datenbank
#### Anmeldedaten für die PostgreSQL-Datenbank:

![grafik](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/79426bec-4d3d-4e44-b459-885127541027)

+ Datasource Url (.yaml-Datei): jdbc:postgresql://survey-master-pg-survey-master.e.aivencloud.com:25901/defaultdb?currentSchema=public
+ Url: jdbc:postgresql://survey-master-pg-survey-master.e.aivencloud.com:25901/defaultdb
+ Driver-Class-Name: org.postgresql.Driver
+ Host: survey-master-pg-survey-master.e.aivencloud.com
+ Port: 25901
+ Database-Name: defaultdb
+ Schema-Name: public

#### Entity-Relationship-Modell
![2024-05-23 Entity-Relationship-Modell-Onlineumfrage](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/2ce88358-5af1-4bca-864b-bf19efa658d2)

+ Ein User kann an mehreren Umfragen teilnehmen (1:n)
+ Eine Umfrage kann mehrere Fragen haben (1:n)
+ Eine Frage kann mehrere Antworten haben (1:n)
+ Ein User kann mehrere Fragen beantworten (1:n)
+ Eine Antwort wird von einem User gegeben (n:1) => wichtig hierfür ist die User-ID in der Antwort-Tabelle


## 3. Inbetriebnahme
### Gliederung:
1. Klonen des Projekts
2. Projekt auschecken
3. Gradle einrichten
4. Eigene Encryption mit Jasypt durchführen
5. Datenbank in die IDE einbinden

-----------------------------------------------------------------------------------------------------

#### 1. Klonen des Projekts:
+ Zuerst die URL in HTTPS im github-Repository kopieren

![HTTPS Link](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/d4e32d89-a7b8-4fad-9fec-be5271d2ddd5)
+ Daraufhin ggf. noch einen neuen Ordner erstellen und im Terminal mit **cd [Ordner]** in den entsprechenden Ordner gehen, wo das Projekt dann schlussendlich liegen soll
+ Dort dann den Command **"git clone [github-Link]"** ausführen
+ Das Projekt sollte nun erfolgreich geklont sein

--------------------------------------------------------------------------------------------------------------

#### 2. Projekt auschecken:
+ Zuerst die IDE öffnen. Bei mir ist es standardmäßig IntelliJ
+ Nun auf **Open Project** klicken und den Ordner heraussuchen, in den man das Projekt geklont hat
+ Daraufhin das Projekt auf der Ordnerebene **fallstudie-thema-2-online-umfragesystem** öffnen

![Ordner-Ebene](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/7de04868-8031-4488-8043-2d4b98abe25f)

+ In IntelliJ wird man dann gefragt, ob man dem Projekt vertrauen kann; dort dann auf **Trust Project** klicken
+ Nun unten rechts (in der alten Version) und oben links (in der neueren Version) auf das Git-Symbol klicken und den entsprechenden Branch (normalerweise main, bei mir aktuell create-cloud-database) mit einem Klick auf den Branch und dann auf **Checkout** auschecken

![Checkout](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/6807abef-37b2-4753-bd52-a8894f3cc56e)

---------------------------------------------------------------------------------------------------------

#### 3. Gradle einrichten: _ggf. muss man ab und an Gradle neu laden/aktualisieren_
+ Nun das Projekt noch einmal schließen und auf einer tieferen Ebene öffnen: **fallstudie-thema-2-online-umfragesystem => MVP => survey-application** (Oft erscheint da auch noch ein schwarzes Code-Symbol)

![tiefere Ordner-Ebene](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/e7a70849-721d-4a37-960b-d3afce138832)

+ Dann erscheint ein Fenster **Open or Import Project**. Die Auswahlmöglichkeiten sind: Open as Gradle Project oder as Maven Project. Bitte einmal **Gradle Project** auswählen
+ Daraufhin die Settings von IntelliJ öffnen und in der Suchleiste nach **Gradle** suchen. Dort coretto-17 hinzufügen (ggf. vorher downloaden) und die Auswahl speichern.

![Gradle Settings](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/88534330-2afb-4f26-a0e2-59911ae388d7)

+ Nun bitte die Run Configurations öffnen:

![Run Config](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/36a9ce91-072e-420a-a9f8-4e61e363f0ed)

+ ...und über das +-Symbol eine neue Run Configuration für Spring Boot hinzufügen:

![Spring Boot Run Config](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/97fa28da-1567-4e53-8194-44a2be830740)

+ Die Configuration sollte dann vorerst wie folgt aussehen:

![grafik](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/6ea6c30a-8152-41dc-8011-937488cd8d37)

+ Also noch einmal Java 17 auswählen, **-cp survey-application.main** auswählen und noch folgenden Pfad für die ausführbare Klasse angeben: **com.surveymaster.SurveyApplication**

+ Dann die Eingaben speichern und die Run Configurations wieder schließen

-------------------------------------------------------------------------------------------------------------

#### 4. Eigene Encryption mit Jasypt durchführen: _Die eckigen Klammern "[ ]" müssen ebenfalls mit gelöscht/ausgetauscht werden_
+ Zuerst muss das jasypt.jar file online unter folgendem Link heruntergeladen werden: https://mvnrepository.com/artifact/org.jasypt/jasypt/1.9.3
+ Ist die Webseite aufgerufen, so klickt an dann unter **Files** auf **jar**:

![jar file](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/b873cd4d-96ad-4d68-a34a-bc9921714d13)

+ Nun geht man mit dem Command **cd [Downloads]** in der Kommandozeile in den Ordner, wo man das .jar-file gespeichert hat (Bei mir ist es Downloads)
+ Dann führt man einen weiteren Command wie folgt aus: **java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI "input=[plain database-password/data to be encrypted]" "password=[secret key used in encrypting data, new, self-devised password]" "algorithm=PBEWITHHMACSHA512ANDAES_256" "ivGeneratorClassName=org.jasypt.iv.RandomIvGenerator"**
+ Das sollte dann der Output sein:

![Output jasypt](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/bc3103c5-3b96-4331-aedb-0065de70466e)

+ Der lange Schlüssel unter **OUTPUT** muss dann kopiert werden und mit einem ENC() in die Datei application.yaml eingefügt werden. Dabei muss der bisherige Schlüssel herausgenommen werden, denn dieser ist nur für mich:

![Pfad](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/b16ee2ca-5116-46f5-8f59-b90cd3612942)

![ENC](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/00203239-ec81-412f-a494-1d322dba11ab)

#### Zwei verschiedene Möglichkeiten, das zweite, festgelegte Passwort ebenfalls nicht zu veröffentlichen:
![grafik](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/0292a2d6-5169-43e5-b1d9-a86234aa9b41)

**Zum Kopieren des Commands: -Djasypt.encryptor.password=[festgelegtes Passwort aus der Kommandozeile]**

#### Sources für die Encryption:
Obidiagha, S. (2020). Data Encryption on Spring Boot application.properties file. https://stanleyobi.medium.com/data-encryption-on-spring-boot-application-properties-file-bf6ecb644bfe.

Techie, J. (2021). Spring Boot Password Encryption using Jasypt. https://medium.com/@javatechie/spring-boot-password-encryption-using-jasypt-e92eed7343ab.

------------------------------------------------------------------------------------------------------

#### 5. Datenbank in die IDE einbinden:
+ Rechts am seitlichen Rand in IntelliJ auf das Database-Symbol klicken
+ Dann über einen Klick auf das **+-Symbol => Data Source => PostgreSQL** eine neue Data Source anlegen

![Data Source](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/292eda08-97d0-4ee7-815e-a0057c82c076)

+ Daraufhin bitte einige Angaben zur Datenbank und den entsprechenden Anmeldedaten tätigen. Diese sind auch hier zu finden: https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/wiki

![Data Source Eingaben](https://github.com/IU-Internationale-Hochschule-Augsburg/fallstudie-thema-2-online-umfragesystem/assets/166111939/55492191-1f4e-4ff9-b082-3526b2f021f7)

+ Nun ggf. noch fehlende Driver installieren, wenn IntelliJ das mit einem **Download missing driver files** anbietet
+ Dann auf **Test Connection** klicken. Wenn dies erfolgreich war, dann auf **Apply** oder **OK** klicken und die Datenbank ist fertig eingebunden