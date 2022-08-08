/**
 * @author Sergey Klunniy
 */
public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> myHashMap = new MyHashMap();
        myHashMap.put("Name", 0);
        myHashMap.put("Name", 1);
        myHashMap.put("Name2", 2);

        System.out.println(myHashMap);

        System.out.println(myHashMap.get("Name2"));

    }
}
