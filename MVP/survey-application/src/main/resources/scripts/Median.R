calculate_median <- function(column_data, output_dir) {
  if (is.numeric(column_data) && is.atomic(column_data)) {
    print("Berechne Median:")
    # Basis Median
    median_value <- median(column_data)
    print(median_value)
    
    # Median eines Vektors mit NA-Werten
    median_value_na <- median(column_data, na.rm = TRUE)
    print(median_value_na)
    
    # Beispiel-Daten für den Median
    x3 <- c(1, 2, 3, 4, 5)
    
    # Visualisierung des Medians
    median_plot <- ggplot(data.frame(Werte = x3), aes(x = Werte)) +
      geom_histogram(binwidth = 1, fill = "skyblue", color = "white") +
      geom_vline(aes(xintercept = median(x3)), color = "red", linetype = "dashed", linewidth = 1) +
      labs(title = "Histogramm mit Median", x = "Werte", y = "Häufigkeit") +
      theme_minimal() +
      annotate("text", x = 4, y = 1, label = "Median", color = "red")
    
    # Speichern des Mediansdiagramms
    ggsave(file.path(output_dir, "median_chart.png"), plot = median_plot)
    print(paste("Mediandiagramm gespeichert in:", file.path(output_dir, "median_chart.png")))
    
    print(median_plot)  # Sicherstellen, dass das Diagramm angezeigt wird
  } else {
    print("Median kann nur für numerische und atomare Daten berechnet werden.")
  }
}
