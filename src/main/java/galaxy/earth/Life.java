package galaxy.earth;

import java.time.LocalDateTime;
import java.util.Random;

class Human {
    private static Life humansLife;

    public static void born(String[] args) throws InterruptedException {
        humansLife = new Life("Name", LocalDateTime.now());
        do {
            humansLife.affairs();
        }
        while (humansLife.isAlive());
        System.out.println("Life over");
    }
}

public class Life {
    private String humansName;
    private LocalDateTime dateOfBorn;

    public String getHumansName() {
        return humansName;
    }

    public LocalDateTime getDateOfBorn() {
        return dateOfBorn;
    }

    public Life(String humansName, LocalDateTime dateOfBorn) {
        this.humansName = humansName;
        this.dateOfBorn = dateOfBorn;
    }

    public boolean isAlive() {
        return new Random().nextBoolean();
    }

    public void affairs() throws InterruptedException {
        System.out.println(" smth doing ...");
        Thread.sleep(8);//8 hours
    }
}
