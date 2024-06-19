create_ordinal_scale_charts <- function(data, output_dir) {
  print("Erstelle Ordinalskala-Diagramme:")
  # Beispiel-Daten
  umwelt_data <- data.frame(
    Item1 = c(4, 3, 5),
    Item2 = c(2, 4, 3),
    Item3 = c(3, 5, 4)
  )
  
  # Berechnung des Mittelwerts für jede Zeile (Teilnehmer)
  umwelt_data$Umwelt_skala <- rowMeans(umwelt_data, na.rm = TRUE)
  
  # Umwandlung in einen ordinalen Faktor
  umwelt_data$Umwelt_skala <- factor(
    umwelt_data$Umwelt_skala,
    levels = c("Gar nicht wichtig", "Weniger wichtig", "Neutral", "Wichtig", "Sehr wichtig")
  )
  
  # Balkendiagramm mit Mittelwertslinie
  umwelt_plot <- ggplot(umwelt_data, aes(x = Umwelt_skala)) +
    geom_bar() +
    geom_hline(yintercept = mean(as.numeric(as.character(umwelt_data$Umwelt_skala))), color = "red", linetype = "dashed", linewidth = 1) +
    labs(x = "Umweltbewusstsein", y = "Anzahl der Teilnehmer", title = "Umweltbewusstseinsskala mit Mittelwertslinie")
  
  ggsave(file.path(output_dir, "umweltbewusstsein_chart.png"), plot = umwelt_plot)
  print(paste("Umweltbewusstsein-Diagramm gespeichert in:", file.path(output_dir, "umweltbewusstsein_chart.png")))
  
  print(umwelt_plot)
}
