package task3;

class Motor {

    enum ExhaustState {
        ABSENT,
        EMMITED;

        Volume volume = Volume.NONE;

        Location location = null;

        public Location getEmmitedLocation() {
            switch(this) {
                case EMMITED -> { return location; }
                default -> { throw new RuntimeException("Нельзя получить локацию состояния отличного от EMMITING"); }
            }
        }

        public void setEmmitedLocation(Location location) {
            switch(this) {
                case EMMITED -> { this.location = location; location.contain(this); }
                default -> { throw new RuntimeException("Нельзя задать локацию для состояния отличного от EMMITING"); }
            }
        }

        public Volume getVolume() {
            return volume;
        }

        private void setVolume(Volume volume) {
            System.out.println(
                switch (this.volume) {
                    case NONE -> "Появился " + volume.getDescription();
                    default -> this.volume.getDescription() + " превратился в " + volume.getDescription();
                }
            );
    
            this.volume = volume;
        }

        @Override
        public String toString() {
            return switch(this) {
                case ABSENT -> "Отсутствующий выхлоп";
                case EMMITED -> "Вбрасываемый выхлопной газ";
            };
        }
    }

    ExhaustState exhaustState = ExhaustState.ABSENT;
    
    private boolean isRunning;

    private final Context context;

    public Motor(Context context) {
        this.isRunning = false;
        this.context = context;
    }

    public void start() {
        this.isRunning = true;
        System.out.println("Зажужжал мотор.");

        exhaustState = ExhaustState.EMMITED;

        exhaustState.setEmmitedLocation(context.getLocation());

        exhaustState.setVolume(Volume.WISTLE);
    }

    public void setHighRotations() {
        if(!isRunning) {
            throw new RuntimeException("Нельзя увеличить обороты без запущенного двигателя");
        }
        exhaustState.setVolume(Volume.ROAR);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public ExhaustState getExhaustState() {
        return exhaustState;
    }
}