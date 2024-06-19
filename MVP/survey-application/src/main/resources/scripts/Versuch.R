create_bar_chart <- function(df, output_dir) {
  # Beispiel-Daten (ersetze dies durch deine eigenen Daten)
  df <- data.frame(
    Alter = c(30, 35, 40, 45, 50),  # Altersgruppen
    Anzahl = c(5, 8, 12, 7, 10)     # Häufigkeiten
  )
  
  # Balkendiagramm erstellen
  bar_plot <- ggplot(df, aes(x = factor(Alter), y = Anzahl)) +
    geom_bar(stat = "identity", fill = "skyblue") +
    labs(title = "Häufigkeit der Altersgruppen", x = "Alter", y = "Anzahl") +
    theme_minimal()
  
  # Speichern des Balkendiagramms
  ggsave(file.path(output_dir, "versuch_bar_chart.png"), plot = bar_plot)
  print(paste("Versuchs-Balkendiagramm gespeichert in:", file.path(output_dir, "versuch_bar_chart.png")))
  
  print(bar_plot)  # Sicherstellen, dass das Diagramm angezeigt wird
}
