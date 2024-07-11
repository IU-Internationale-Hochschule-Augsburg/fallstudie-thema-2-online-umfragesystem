# Wir benötigen damit wir die Datenbank einbinden können die Packages deswegen muss zu beginn
# install.packages(c("DBI", "RODBC", "odbc", "dplyr", "dbplyr", "RPostgres"))
# ausgeführt werde.

library(DBI)
library(RODBC)
library(odbc)
library(dplyr)
library(dbplyr)
library(RPostgreSQL)

# Falls es Anpassungen gibt lasse ich die Erklärung was ausgeraut dahinter geschrieben ist stehen!
# So fällt es mir leichter nachzuvollziehen warum ich die Daten da eingefügt habe und wann ich Anführungszeichen brauche und wann nicht!

dsn_database <- "defaultdb" # Spezifischer Name der Database
# Spezifischer hostname ist z.b:"Gar-keine-Lust-1-portal.4.aivencloud.com"
dsn_hostname <- "survey-master-pg-survey-master.e.aivencloud.com"
dsn_port <- 25901 # port nummer von Team 1 z.b 98939
dsn_uid <- "survey-report" # Persönlicher benutzername z.b "admin"
dsn_pwd <- "g2X%DcO9!=*D4gWmQ" # Wichtig Verschlüsseltes Passwort beim Hochladen "xxx"

# connect to Database
database_connection <- function() {
    tryCatch(
        {
            drv <- dbDriver("PostgreSQL")
            print("Connecting to Database…")
            connec <- dbConnect(RPostgres::Postgres(),
                dbname = dsn_database,
                host = dsn_hostname,
                port = dsn_port,
                user = dsn_uid,
                password = dsn_pwd
            )
            print("Database Connected!")
            return(connec)
        },
        error = function(cond) {
            print("Unable to connect to Database.")
        }
    )
}

# load all questions from database
data_questions <- function(connec) {
    return(dbGetQuery(connec, "SELECT * FROM question"))
}

# load all answers from database
data_answers <- function(connec) {
    return(dbGetQuery(connec, "SELECT * FROM answer"))
}

# !WICHTIG! disconnect from Database
database_disconnection <- function(connec) {
    dbDisconnect(connec) # disconnect from database
    print("Database Disconnected!")
}

###################################################################################
# Testabfragen von der Datenbank
# dbListTables(connec)
# questions <- dbGetQuery(connec, "SELECT * FROM question WHERE survey_id = 20001")
# answers <- dbGetQuery(connec, "SELECT * FROM answer")
# answer <- dbGetQuery(connec, "SELECT * FROM answer WHERE question_id = 20001")
###################################################################################
