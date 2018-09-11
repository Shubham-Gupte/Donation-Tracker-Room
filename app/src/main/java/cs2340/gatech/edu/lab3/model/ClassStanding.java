package cs2340.gatech.edu.lab3.model;

public enum ClassStanding {
    FRESHMAN ("FR"),
    SOPHOMORE ("SO"),
    JUNIOR ("JU"),
    SENIOR ("SE");

    private String label;

    private ClassStanding(String label) {
        this.label = label;
    }
}
