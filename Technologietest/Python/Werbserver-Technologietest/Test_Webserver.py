from http.server import BaseHTTPRequestHandler, HTTPServer
import cgi
import sqlite3

# Verbindung zur Datenbank sqlite3 herstellen
# Source: (Python Software Foundation, 2024a)
connection = sqlite3.connect('technologietest.db')
a = connection.cursor()
a.execute('''CREATE TABLE IF NOT EXISTS Texteingaben (TEXTID INTEGER PRIMARY KEY, Texteingabe TEXT)''')
connection.commit()

class MyTestServer(BaseHTTPRequestHandler):
# Source: (Pythonbasics.org, 2021)
    def do_GET(self):
        # sendet dem Server den Code 200, steht für OK
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()
# Source: (jobtensor, 2024)
        # öffnet HTML-Datei
        file_content = open('html_testdatei.html', 'r')
        # liest HTML-Datei aus
        self.wfile.write(file_content.read().encode('utf-8'))
        file_content.close()

    def do_POST(self):
# Source: (Pal, 2023)
        # sendet dem Server den Code 200, steht für OK
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()

        # POST-Daten auslesen
        formular = cgi.FieldStorage(fp=self.rfile, headers=self.headers, environ={'REQUEST_METHOD':'POST'})
        text_input = formular.getvalue('text_input')
        # Daten einfügen in die Datenbank
        a.execute('''INSERT INTO Texteingaben (Texteingabe) VALUES (?)''', (text_input,))
        connection.commit()

        # Als Test: Daten aus der Datenbak ausgeben
        a.execute('''SELECT * FROM Texteingaben''')
        zeilen = a.fetchall()

        # Schließen der Verbindung
        connection.close()

        for zeile in zeilen:
            print(zeile)

        self.wfile.write(f"<html><body><h1>Folgender Text wurde eingegeben:</h1><p>{text_input}</p></body></html>".encode('utf-8'))



if __name__ == '__main__':
# Source: (Python Software Foundation, 2024b)
    testServer = HTTPServer(("localhost", 8080), MyTestServer)
    print("Server started on port 8080...")
    testServer.serve_forever()