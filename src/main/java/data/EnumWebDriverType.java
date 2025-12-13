package main.java.data;

public enum EnumWebDriverType {

    CHROME("chrome"),
    EDGE("edge");

    private String browser_name = null;

    EnumWebDriverType(String browser_name) {
        this.browser_name = browser_name;
    }

    public String getName() {
        return this.browser_name;
    }

}