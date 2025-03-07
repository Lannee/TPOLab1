package task3;

public class Void implements Location {
    private final String description;

    Void(String description) {
        this.description = description;
    }

    @Override
    public void contain(Object obj) {
        System.out.println(obj.toString() + " попадает в пустоту (" + description + ").");
    }
}
