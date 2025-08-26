package gestionalebackend.gestionalebackend.worklog.model;

public enum DayType {
    LAVORO("Lavoro", "Work"),
    FERIE("Ferie", "Vacation"),
    MALATTIA("Malattia", "Sick Leave"),
    PERMESSO("Permesso", "Permit"),
    FESTIVITA("Festività", "Holiday"),
    SMART_WORKING("Smart Working", "Smart Working"),
    TRASFERTA("Trasferta", "Business Trip"),
    FORMAZIONE("Formazione", "Training"),
    CONGEDO("Congedo", "Leave"),
    ALTRO("Altro", "Other");
    
    private final String italianLabel;
    private final String englishLabel;
    
    DayType(String italianLabel, String englishLabel) {
        this.italianLabel = italianLabel;
        this.englishLabel = englishLabel;
    }
    
    public String getItalianLabel() {
        return italianLabel;
    }
    
    public String getEnglishLabel() {
        return englishLabel;
    }
}