import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergey Klunniy
 */
public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> myHashMap = new MyHashMap();
        System.out.println(myHashMap.size());
        myHashMap.put("Name", 0);
        myHashMap.put("Name", 1);
        myHashMap.put("Name2", 2);

        System.out.println(myHashMap);

        System.out.println(myHashMap.get("Name2"));
//
        myHashMap.put(null, 2);
        myHashMap.put(null, 10);
//        myHashMap.put("Name3", 2);
//        myHashMap.put(null, 2);
        System.out.println(myHashMap);
        System.out.println(myHashMap.size());

        Map<String, Integer> map = new HashMap<>();
    }
}
