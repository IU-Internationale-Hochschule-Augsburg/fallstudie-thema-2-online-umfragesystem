create_line_chart <- function(data, output_dir) {
  # Beispiel-Daten (ersetze dies durch deine eigenen Daten)
  line <- data.frame(
    Jahr = c(2000, 2001, 2002, 2003, 2004),  # Jahre
    Schluss = c(0.92, 0.95, 0.98, 1.02, 1.05)  # Euro-Dollar-Wechselkurse
  )
  
  # Liniendiagramm mit ggplot2 erstellen
  line_plot <- ggplot(line, aes(x = Jahr, y = Schluss)) +
    geom_line(color = "blue", linewidth = 1) +  # Verwendung von `linewidth` statt `size`
    geom_point(color = "red", size = 3) +
    labs(x = "Jahr", y = "€/$-Kurs",
         title = "Wechselkurs je Jahr") +
    theme_minimal() +
    theme(
      plot.title = element_text(hjust = 0.5),
      axis.text = element_text(size = 12),
      axis.title = element_text(size = 14)
    )
  
  # Speichern des Liniendiagramms
  ggsave(file.path(output_dir, "line_chart.png"), plot = line_plot)
  print(paste("Liniendiagramm gespeichert in:", file.path(output_dir, "line_chart.png")))
  
  print(line_plot)  # Sicherstellen, dass das Diagramm angezeigt wird
}
