package task3;

class Character {
    private final String name;

    public Character(String name) {
        this.name = name;
    }

    public void enterSpaceship(Spaceship spaceship) {
        spaceship.addToBoard(this);
        System.out.println(name + " вошел в космический корабль.");
    }

    public String getName() {
        return name;
    }
}
