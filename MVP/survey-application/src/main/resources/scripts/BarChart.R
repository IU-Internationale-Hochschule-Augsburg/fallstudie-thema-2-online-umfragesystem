create_bar_chart <- function(data, output_dir) {
  # Beispiel-Daten: Alter von Personen
  alter <- c(25, 30, 22, 40, 28, 35, 29)
  
  # Berechne den Durchschnitt des Alters
  durchschnitt_alter <- mean(alter)
  
  # Variablen für Achsenbeschriftungen
  x_labels <- c("P1", "P2", "P3", "P4", "P5", "P6", "P7")
  title <- "Alter der Personen"
  x_label <- "Personen"
  y_label <- "Alter"
  
  # Hier der Text für die Caption
  caption <- "Die rote Linie kennzeichnet den Durchschnittwert"
  
  # Erstelle ein Balkendiagramm mit dem Durchschnitt als Linie
  bar_plot <- ggplot(data.frame(Person = x_labels, Alter = alter), aes(x = Person, y = Alter)) +
    geom_bar(stat = "identity", fill = "skyblue") +
    geom_hline(yintercept = durchschnitt_alter, color = "red", linetype = "dashed", linewidth = 1) +
    labs(x = x_label, y = y_label, title = title, caption = caption) +
    theme_minimal()
  
  # Speichern des Balkendiagramms
  ggsave(file.path(output_dir, "bar_chart.png"), plot = bar_plot)
  print(paste("Balkendiagramm gespeichert in:", file.path(output_dir, "bar_chart.png")))
  
  print(bar_plot)  # Sicherstellen, dass das Diagramm angezeigt wird
}