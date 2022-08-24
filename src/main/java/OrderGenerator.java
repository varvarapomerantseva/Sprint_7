public class OrderGenerator {

    public static Order orderWithBlack() {
        return new Order("Naruto", "Uchiha", "Konoha, 142 apt", "4", "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", Color.BLACK);
    }

    public static Order orderWithGrey() {
        return new Order("Naruto", "Uchiha", "Konoha, 142 apt", "4", "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", Color.GREY);
    }

    public static Order orderWithoutColor() {
        return new Order("Naruto", "Uchiha", "Konoha, 142 apt", "4", "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", null);
    }

    public static Order orderWithBothColor() {
        return new Order("Naruto", "Uchiha", "Konoha, 142 apt", "4", "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", Color.BOTH);
    }
}