package kz.kakimzhanova.parser.entity;

public class Paper {
    private String title;
    private String type;
    private boolean monthly;
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
    public void setChars(boolean color, int volume, boolean glossy, boolean subscriptionIndex){
        Chars chars = new Chars();
        chars.color = color;
        chars.glossy = glossy;
        chars.volume = volume;
        chars.subscriptionIndex = subscriptionIndex;
    }
    public class Chars{
        private boolean color;
        private int volume;
        private boolean glossy;
        private boolean subscriptionIndex;

        public boolean isColor() {
            return color;
        }

        public int getVolume() {
            return volume;
        }

        public boolean isGlossy() {
            return glossy;
        }

        public boolean isSubscriptionIndex() {
            return subscriptionIndex;
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

        public void setSubscriptionIndex(boolean subscriptionIndex) {
            this.subscriptionIndex = subscriptionIndex;
        }
    }
}
