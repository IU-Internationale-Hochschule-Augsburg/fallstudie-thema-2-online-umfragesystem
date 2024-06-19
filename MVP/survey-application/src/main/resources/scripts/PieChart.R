create_pie_chart <- function(data, output_dir) {
  tryCatch({
    print("Erstelle Kuchendiagramm:")
    # Die Prozentdaten auf Basis der Geschlechtsspalte berechnen
    percent <- data.frame(table(data$Geschlecht))
    
    pie_chart <- ggplot(percent, aes(x="", y=Freq, fill=Var1)) +
      geom_bar(stat="identity", color="black") +
      coord_polar("y") + theme_void() +
      geom_label(aes(label=Freq), position=position_stack(vjust=0.5), label.size=0, size=6, show.legend=FALSE) +
      labs(fill="Geschlecht:") +
      theme(legend.text = element_text(size = 15), legend.title = element_text(size = 15))
    
    print(pie_chart)  # Sicherstellen, dass das Diagramm angezeigt wird
    
    # Speichern des Kuchendiagramms
    ggsave(file.path(output_dir, "pie_chart.png"), plot = pie_chart)
    print(paste("Kuchendiagramm gespeichert in:", file.path(output_dir, "pie_chart.png")))
    
  }, error = function(e) {
    if (grepl("does not exist", e$message)) {
      # Fehlerbehandlungsroutine für nicht gefundenen Pfad
      print("Der angegebene Pfad existiert nicht.")
    } else {
      # Allgemeine Fehlerbehandlungsroutine für andere Fehler
      print("Ein anderer Fehler ist aufgetreten:")
      print(e)
    }
  })
}
