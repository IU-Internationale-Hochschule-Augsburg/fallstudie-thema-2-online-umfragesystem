create_interval_scale_charts <- function(data, output_dir) {
  print("Erstelle Intervallskala-Diagramme:")
  # Beispiel-Daten für Temperatur in Grad Celsius
  temperature <- c(10, 20, 30, 25, 15)
  
  # Grafische Darstellung der Temperaturdaten
  temperature_plot <- ggplot(data.frame(Tag = 1:5, Temperatur = temperature), aes(x = Tag, y = Temperatur)) +
    geom_line(color = "blue", linewidth = 1) +
    geom_point(color = "red", size = 3) +
    labs(x = "Tag", y = "Temperatur (°C)", title = "Temperaturverlauf") +
    theme_minimal()
  
  # Speichern des Temperaturdiagramms
  ggsave(file.path(output_dir, "temperature_chart.png"), plot = temperature_plot)
  print(paste("Temperaturdiagramm gespeichert in:", file.path(output_dir, "temperature_chart.png")))
  
  print(temperature_plot)  # Sicherstellen, dass das Diagramm angezeigt wird
  
  # Beispiel-Daten für IQ-Testergebnisse
  iq_scores <- c(100, 120, 90, 110, 130)
  
  # Grafische Darstellung der Intervallskala
  iq_plot <- ggplot(data.frame(IQ = iq_scores), aes(x = IQ)) +
    geom_histogram(binwidth = 10, fill = "purple", color = "white") +
    labs(x = "IQ-Score", y = "Anzahl", title = "Verteilung der IQ-Testergebnisse") +
    theme_minimal()
  
  # Speichern des IQ-Diagramms
  ggsave(file.path(output_dir, "iq_chart.png"), plot = iq_plot)
  print(paste("IQ-Diagramm gespeichert in:", file.path(output_dir, "iq_chart.png")))
  
  print(iq_plot)  # Sicherstellen, dass das Diagramm angezeigt wird
  
  # Beispiel-Daten für Einstellungsmessungen
  attitude_scores <- c(5, 4, 3, 2, 4)
  
  # Grafische Darstellung der Intervallskala
  attitude_plot <- ggplot(data.frame(Einstellung = attitude_scores), aes(x = Einstellung)) +
    geom_histogram(binwidth = 1, fill = "skyblue", color = "pink") +
    labs(x = "Bewertung", y = "Anzahl", title = "Verteilung der Einstellungsmessungen") +
    theme_minimal()
  
  # Speichern des Einstellungsdiagramms
  ggsave(file.path(output_dir, "attitude_chart.png"), plot = attitude_plot)
  print(paste("Einstellungsdiagramm gespeichert in:", file.path(output_dir, "attitude_chart.png")))
  
  print(attitude_plot)  # Sicherstellen, dass das Diagramm angezeigt wird
}
