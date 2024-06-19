calculate_variance <- function(column_data, output_dir) {
  if (is.numeric(column_data) && is.atomic(column_data)) {
    print("Berechne Varianz:")
    # Stichprobenvarianz berechnen
    var_sample <- var(column_data)
    print(var_sample)
    
    # Populationsvarianz berechnen
    n <- length(column_data)
    var_population <- var_sample * (n - 1) / n
    print(var_population)
    
    # Variationskoeffizient berechnen
    cv <- sd(column_data) / mean(column_data) * 100
    print(cv)
    
    # Beispiel-Daten für die Varianz
    variance_plot <- ggplot(data.frame(Werte = column_data), aes(x = Werte)) +
      geom_histogram(binwidth = 1, fill = "skyblue", color = "white") +
      labs(title = "Histogramm mit Varianz", x = "Werte", y = "Häufigkeit") +
      theme_minimal()
    
    ggsave(file.path(output_dir, "variance_chart.png"), plot = variance_plot)
    print(paste("Variancediagramm gespeichert in:", file.path(output_dir, "variance_chart.png")))
    
    print(variance_plot)  # Sicherstellen, dass das Diagramm angezeigt wird
  } else {
    print("Varianz kann nur für numerische und atomare Daten berechnet werden.")
  }
}
