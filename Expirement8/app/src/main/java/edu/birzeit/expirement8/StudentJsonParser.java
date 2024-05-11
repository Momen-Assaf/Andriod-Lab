package edu.birzeit.expirement8;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class StudentJsonParser {
    public static List<Student> getObjectFromJson(String json) {
        List<Student> students;
        try {
            JSONArray jsonArray = new JSONArray(json);
            students = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                Student student = new Student();
                student.setID(jsonObject.getInt("id"));
                student.setName(jsonObject.getString("name"));
                student.setAge(jsonObject.getDouble("age"));
                student.setCity(jsonObject.getString("city"));
                students.add(student);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return students;
    }
}
