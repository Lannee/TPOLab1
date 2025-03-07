package task3;

public enum Volume {
    WISTLE("свист"),
    ROAR("рёв"),
    NONE;

    String description;

    Volume(String desc) {
        description = desc;
    }

    Volume() {
        this("");
    }

    public String getDescription() {
        return description;
    }
}
