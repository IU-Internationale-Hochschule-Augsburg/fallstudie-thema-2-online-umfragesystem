calculate_mode <- function(column_data, output_dir) {
  if (is.numeric(column_data) && is.atomic(column_data)) {
    print("Berechne Modus:")
    # Funktion zum Berechnen des Modus
    find_mode <- function(x) {
      u <- unique(x)
      tab <- tabulate(match(x, u))
      u[tab == max(tab)]
    }
    
    # Berechnung des Modus für einen numerischen Vektor
    mode_numeric <- find_mode(column_data)
    cat("Der Modus des numerischen Vektors ist:", mode_numeric, "\n")
    
    # Histogramm zur Visualisierung der Verteilung
    mode_plot <- ggplot(data.frame(Werte = column_data), aes(x = Werte)) +
      geom_histogram(binwidth = 1, fill = "skyblue", color = "white") +
      geom_vline(aes(xintercept = mode_numeric), color = "red", linetype = "dashed", linewidth = 1) +
      labs(title = "Histogramm mit Modus", x = "Werte", y = "Häufigkeit") +
      theme_minimal() +
      annotate("text", x = mode_numeric, y = 1, label = "Modus", color = "red")
    
    # Speichern des Modusdiagramms
    ggsave(file.path(output_dir, "mode_chart.png"), plot = mode_plot)
    print(paste("Modusdiagramm gespeichert in:", file.path(output_dir, "mode_chart.png")))
    
    print(mode_plot)  # Sicherstellen, dass das Diagramm angezeigt wird
  } else {
    print("Modus kann nur für numerische und atomare Daten berechnet werden.")
  }
}
