public class CourierGenerator {

    public static Courier getDefault() {
        return new Courier("Kittensu", "1235", "Ivan");
    }

    public static Courier getWithoutLogin() {
        return new Courier("", "test", "");
    }

    public static Courier getWithoutPassword() {
        return new Courier("test", "", "");
    }

    public static Courier getNonexistentUser() {
        return new Courier("Kitten_1", "1235", "Ivan");

    }
}
