package core;

final public class Helper {
    // public static void main(String[] args) {
    //     String[] array = {"valor1", "valor2"};
    //     String[] keys = {"chave1", "chave2"};
    //     String res = Helper.simpleJson(keys, array);
    //     System.out.println(res);
    // }

    public static String simpleJson(String[] keys, String[] values) {
        if (keys.length != values.length) {
            System.out.println("both array must be the same size");
            return "Something went wrong!";
        }
        String string = "{";
        for (int i = 0; i <= keys.length - 1; i++) {
            string += String.format("\"%s\": \"%s\"", keys[i], values[i]);
            if (i < keys.length - 1) {
                string += ", ";
            }
        }
        return string + "}";
    }

    public static String simpleJson(String key, String value) {
        return "{\" " + key + "\": \"" + value + "\"}";
    }
}
