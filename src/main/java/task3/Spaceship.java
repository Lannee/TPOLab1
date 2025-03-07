package task3;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Spaceship {
    private Motor motor;
    private boolean isFlying;

    private Set<Character> onBoard = new HashSet<>();

    public Spaceship(Motor motor) {
        this.motor = motor;
        this.isFlying = false;
    }

    public Spaceship(Motor motor, Set<Character> characters) {
        this(motor);
        onBoard = characters;
    }

    public void takeOff() {
        if (!motor.isRunning()) {
            throw new RuntimeException("Нельзя взлетель с выключенным мотором");
        }

        if (onBoard.isEmpty()) {
            throw new RuntimeException("Нельзя взлетель без людей на борту");
        }
        isFlying = true;

        motor.setHighRotations();
    }

    public void flyInSpace(Space space) {
        if (!isFlying) {
            throw new RuntimeException("Нельзя полететь в космос не взлетев");
        }

        System.out.println(onBoard.stream().map((e) -> e.getName()).collect(Collectors.joining(", ")) + " полетели в " + space.getDescription() + ".");
        space.contain(this);
    }

    public boolean isFlying() {
        return isFlying;
    }

    public void addToBoard(Character character) {
        onBoard.add(character);
    }

    public Set<Character> getOnBoard() {
        return onBoard;
    }

    public boolean isOnBoard(Character character) {
        return onBoard.contains(character);
    }

    @Override
    public String toString() {
        return "Космический корабль";
    }
}