package kz.kakimzhanova.parser.parser.sax;

public enum PaperEnum {
    PAPERS("papers"),
    PAPER("paper"),
    TITLE("title"),
    TYPE("type"),
    MONTHLY("monthly"),
    CHARS("chars"),
    COLOR("color"),
    VOLUME("volume"),
    GLOSSY("glossy"),
    SUBSCRIPTION("subscription");

    private String value;

    PaperEnum(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
