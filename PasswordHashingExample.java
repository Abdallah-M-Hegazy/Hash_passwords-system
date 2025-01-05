import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Sec3 {

    public static void hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            String hash = sb.toString();
            System.out.println("Hashed password: "+hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public static void mapping(String h){
        String jsonStringWithoutBraces = h.replace("{", "").replace("}", "");
         String[] keyValuePairs = jsonStringWithoutBraces.split(",");
        Map<String, String> jsonMap = new HashMap<>();
        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split(":");
            String key = keyValue[0].replace("\"", "").trim(); 
            String value = keyValue[1].replace("\"", "").trim();
            jsonMap.put(key, value);
        }
        String name = jsonMap.get("name");
        int age = Integer.parseInt(jsonMap.get("age"));
        System.out.println("Name: " + name +"\n Age: " + age);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter your password: ");
        String pass = sc.nextLine();
        String jsonString = "{\"name\":\""+name+"\",\"age\":"+age+"}";
        mapping(jsonString);
        hashPassword(pass);
        sc.close();
    }
}
