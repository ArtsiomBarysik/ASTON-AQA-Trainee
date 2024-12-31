/* Класс кота
 */
    public class Cat extends Animal {
    private String color;
    private boolean satiety = false;
    private static int catClassCount = 0;
    // Конструктор кота (Сытость не указывается, она по умолчанию отрицательная)
    public Cat(String name, String color) {
        super(name);
        this.color = color;
        catClassCount++;
    }

    // Геттеры класса Cat
    public String getColor() {
        return color;
    }
    public boolean getSatiety() {
        return satiety;
    }
    public static int getCatClassCount() {
        return catClassCount;
    }
    // Сеттеры класса Cat
    public void setColor(String color) {
        this.color = color;
    }
    public void setSatiety(boolean satiety) {
        this.satiety = satiety;
    }

    // Метод бега кота
    @Override
    public void run(int distance) {
        if (distance <= 0) {
            System.out.println("Котики конечно лентяи, но не до такой степени");
        } else if (distance <= 200) {
            System.out.println(getName() + " пробежал " + distance + " метров.");
        } else {
            System.out.println("Полегче, это же не гепард");
        }
    }
    // Метод плавания кота
    @Override
    public void swim(int distance) {
        System.out.println("Тебе надо ты и плавай");
    }
    // Метод проверки кота на сытость
    public void checkSatiety () {
        if (satiety == true) {
            System.out.println(getName() + "наевся и спит");
        } else {
            System.out.println(getName() + " голодный и злой");
        }
    }
}
