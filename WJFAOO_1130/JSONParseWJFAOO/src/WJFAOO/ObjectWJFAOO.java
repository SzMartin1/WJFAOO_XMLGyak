package WJFAOO;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class ObjectWJFAOO {
    public static void main(String[] args) throws Exception
    {
        Object obj = new JSONParser().parse(new FileReader("JSONWjfaoo.json"));

        JSONObject jo = (JSONObject) obj;
        System.out.println(jo);
    }
}
