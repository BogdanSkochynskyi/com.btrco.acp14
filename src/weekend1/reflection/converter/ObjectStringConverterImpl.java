package weekend1.reflection.converter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectStringConverterImpl implements ObjectStringConverter {

    @Override
    public String objectToStringConvert(Object object) {
        Class cl = object.getClass();

        String str = "type=" + cl.getName() + "\n";

        for(Field field : cl.getFields())
        {
            try {
                str+=field.getName() + "=" + field.get(object);
                if (!field.getName().equals("age"))
                {
                    str+="\n";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return str;
    }

    @Override
    public Object stringToObjectConvert(String string) {

        Map<String, String> nameValueMap = parseStringToNameValueMap(string);
        try {
            Class cl = Class.forName(nameValueMap.remove("type"));
            Object object = cl.newInstance();

            for (String name : nameValueMap.keySet()) {
                try {
                    Field field = cl.getField(name);
                    if (field.isAnnotationPresent(ForSave.class)) {
                        field.set(object, converTo(nameValueMap.get(name), field.getType()));
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }

            return object;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return null;
    }

    private Object converTo(String s, Class<?> aClass) {

        if (aClass.equals(int.class))
        {
            return Integer.parseInt(s);
        }
        else if (aClass.equals(double.class))
        {
            return Double.parseDouble(s);
        }
        else {
            return s;
        }

    }

    private Map<String,String> parseStringToNameValueMap(String string) {

        Map<String, String> nameValueMap = new HashMap<>();
        String[] parts = string.split("\n");

        for (String part : parts) {
            String[] nameValue = part.split("=");
            nameValueMap.put(nameValue[0], nameValue[1]);
        }
        return nameValueMap;
    }
}
