package kz.kakimzhanova.parser.entity;

public class Paper {
    private String title;
    private String type;
    private boolean monthly;
    private Chars chars = new Chars();
    public String getTitle(){
        return title;
    }

    public String getType() {
        return type;
    }

    public boolean isMonthly() {
        return monthly;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMonthly(boolean monthly) {
        this.monthly = monthly;
    }
    public Chars getChars(){
        return chars;
    }

    public void setChars(Chars chars) {
        this.chars = chars;
    }

    public class Chars{
        private boolean color;
        private int volume;
        private boolean glossy;
        private boolean subscription;

        public boolean isColor() {
            return color;
        }

        public int getVolume() {
            return volume;
        }

        public boolean isGlossy() {
            return glossy;
        }

        public boolean isSubscription() {
            return subscription;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public void setGlossy(boolean glossy) {
            this.glossy = glossy;
        }

        public void setSubscription(boolean subscription) {
            this.subscription = subscription;
        }

        @Override
        public String toString() {
            return "Chars{" +
                    "color=" + color +
                    ", volume=" + volume +
                    ", glossy=" + glossy +
                    ", subscription=" + subscription +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Paper{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", monthly=" + monthly +
                ", chars=" + chars +
                '}' + "\n";
    }
}
