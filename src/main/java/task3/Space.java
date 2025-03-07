package task3;

class Space implements Location {
    private final String description;
    private int objectCount;

    public Space(String description) {
        this.description = description;
        this.objectCount = 0;
    }

    public int getObjectCount() {
        return objectCount;
    }

    public String getDescription() {
        return description;
    } 

    @Override
    public void contain(Object obj) {
        objectCount++;
        System.out.println(obj.toString() + " попадает в " + description);
    }
}