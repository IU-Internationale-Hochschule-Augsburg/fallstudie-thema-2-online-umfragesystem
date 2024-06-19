create_nominal_scale_charts <- function(data, output_dir) {
  print("Erstelle Nominalskala-Diagramme:")
  # Beispiel-Daten (ersetze dies durch deine eigenen Daten)
  ds <- data.frame(
    Geschlecht = factor(c("männlich", "weiblich", "männlich", "weiblich", "männlich")),
    Haarfarbe = factor(c("blond", "brünett", "blond", "schwarz", "braun")),
    Nationalität = factor(c("Deutsch", "Französisch", "Spanisch", "Italienisch", "Englisch"))
  )
  
  # Zeige die ersten paar Zeilen der Daten
  print(head(ds))
  
  # Grafische Darstellung der Daten
  
  # Geschlecht
  geschlecht_plot <- ggplot(ds, aes(x = Geschlecht)) +
    geom_bar(fill = "skyblue") +
    labs(x = "Geschlecht", y = "Anzahl", title = "Verteilung nach Geschlecht")
  
  ggsave(file.path(output_dir, "geschlecht_chart.png"), plot = geschlecht_plot)
  print(paste("Geschlechterdiagramm gespeichert in:", file.path(output_dir, "geschlecht_chart.png")))
  
  # Haarfarbe
  haarfarbe_plot <- ggplot(ds, aes(x = Haarfarbe)) +
    geom_bar(fill = "salmon") +
    labs(x = "Haarfarbe", y = "Anzahl", title = "Verteilung nach Haarfarbe")
  
  ggsave(file.path(output_dir, "haarfarbe_chart.png"), plot = haarfarbe_plot)
  print(paste("Haarfarbendiagramm gespeichert in:", file.path(output_dir, "haarfarbe_chart.png")))
  
  # Nationalität
  nationalitaet_plot <- ggplot(ds, aes(x = Nationalität)) +
    geom_bar(fill = "lightgreen") +
    labs(x = "Nationalität", y = "Anzahl", title = "Verteilung nach Nationalität")
  
  ggsave(file.path(output_dir, "nationalitaet_chart.png"), plot = nationalitaet_plot)
  print(paste("Nationalitäten-Diagramm gespeichert in:", file.path(output_dir, "nationalitaet_chart.png")))
  
  print(geschlecht_plot)
  print(haarfarbe_plot)
  print(nationalitaet_plot)
}
