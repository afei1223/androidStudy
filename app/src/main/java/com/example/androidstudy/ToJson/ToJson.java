package com.example.androidstudy.ToJson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 支持转码的类型
 * int Integer
 * boolean Boolean
 * float Float
 * double Double
 * long Long
 * short Short
 * byte Byte
 * char
 *
 * String
 * map hashmap
 * List
 * arrarylist
 *
 * 自定义类
 *
 * 包名为java开头的做了特殊处理{见switchType中的default};
 *
 * 若非上述类型，可能会显示@12345类型
 *
 * 只支持转化public的参数，若为private则不显示（对javabean类型的类有点不友好）
 * 可以利用这一特性把想显示的参数用public显示，其余用private显示。
 *
 * */
public class ToJson{

    /**
     * 枚举，用于区分传入类参数的数据类型，是否是基本数据类型，是否继承Object，是否是List，是否是自定义类
     * 主要用于基本数据类型的数组，转为list存入数据
     * Object    是指继承了Object的数据类型
     * notlist   表示不是数组类型，（是可以直接存入json的）
     * notBase   表示自定义数据类型（除最上所列类型外的类型）需要继续拆分成基础数据类型。
     * */
    private enum ObjectType{
        INT,BOOLEAN,LONG,FLOAT,DOUBLE,BYTE,CHAR,SHORT,OBJECT,NOTLIST,NOTBASE,NOTBASELIST
    }

    public JSONObject toJson(){
        //调用转json方法
        return classToJson(this);
    }

    //将类输出json的方法
    private JSONObject classToJson(Object o) {
        JSONObject jsonObject = new JSONObject();
        //获取反射获取到的参数信息。
        Field[] fields = o.getClass().getDeclaredFields();
        //遍历
        for(Field field : fields){
            Object object = null;
            try {
                //获取参数内容
                object = field.get(o);
                if(object != null){
                    //获取参数类型参数
                    String type = field.getType().getName();
                    //进行参数类型判断
                    ObjectType q = switchType(type);
                    if(q == ObjectType.NOTLIST){
                        //不是数组类型
                        jsonObject.put(field.getName(),object.toString());
                    } else if (q == ObjectType.NOTBASE) {
                        //不是基本类型，需要继续拆分
                        JSONObject jsonObject1 = classToJson(object);
                        jsonObject.put(field.getName(),jsonObject1);
                    } else if(q == ObjectType.NOTBASELIST){
                        //自定义类型数组
                        List a = new ArrayList();
                        for(Object i:(Object[]) object){
                            JSONObject jsonObject1 = classToJson(i);
                            a.add(jsonObject1);
                        }
                        jsonObject.put(field.getName(),a);
                    } else{
                        //数组转为list存入数据
                        jsonObject.put(field.getName(),arrayToList(object,q));
                    }
                }else {
                    jsonObject.put(field.getName(),"null");
                }
            } catch (IllegalAccessException  e) {
                //当遇到私有变量时
//                e.printStackTrace();
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    /**
     * 参数类型判断
     *
     * 如果是基础数据类型，或可直接转为string可显示数据的，且不为数组   返回 notlist
     * 如果是数组，分为继承了object的数据类型和默认的基础数据类型（即小写的那种）
     * 按照这两种分别不同的返回
     * 不属于最上所列的数据类型，视为自定义类，即需要继续拆分成数据类型。返回notbase
     *
     * */
    private ObjectType switchType(String type) {
        switch (type){
            case "[Z": //boolean
                return ObjectType.BOOLEAN;
            case "[B"://byte
                return ObjectType.BYTE;
            case "[C"://char
                return ObjectType.CHAR;
            case "[D"://double
                return ObjectType.DOUBLE;
            case "[F"://float
                return ObjectType.FLOAT;
            case "[J"://long
                return ObjectType.LONG;
            case "[S"://short
                return ObjectType.SHORT;
            case "[I"://int
                return ObjectType.INT;
            case "int":
            case "short":
            case "long":
            case "float":
            case "double":
            case "char":
            case "byte":
            case "boolean":
                return ObjectType.NOTLIST;
            default:
                if(type.startsWith("java")){
                    return ObjectType.NOTLIST;
                }else if(type.startsWith("[L")){
                    //继承了Object的数组
                    if(type.startsWith("[Ljav.util"))
                        return ObjectType.OBJECT;
                    return ObjectType.NOTBASELIST;
                }
                return ObjectType.NOTBASE;
        }
    }

//    private JSONObject intToJson(Object object) {
//        JSONObject jsonObject = new JSONObject();
//        int[] q = ((int[])object);
//        for(int i=0;i<q.length;i++){
//            try {
//                jsonObject.put(""+i,q[i]);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return jsonObject;
//    }

    /**
     * 数组转成list
     * */
    private List arrayToList(Object object, @NotNull ObjectType type){
        List a = new ArrayList();
        switch (type){
            case INT:
                for(int i:(int[]) object){
                    a.add(i);
                }
                break;
            case BOOLEAN:
                for(boolean i:(boolean[]) object){
                    a.add(i);
                }
                break;
            case LONG:
                for(long i:(long[]) object){
                    a.add(i);
                }
                break;
            case FLOAT:
                for(float i:(float[]) object){
                    a.add(i);
                }
                break;
            case DOUBLE:
                for(Double i:(double[]) object){
                    a.add(i);
                }
                break;
            case BYTE:
                for(byte i:(byte[]) object){
                    a.add(i);
                }
                break;
            case CHAR:
                for(char i:(char[]) object){
                    a.add(i);
                }
                break;
            case SHORT:
                for(short i:(short[]) object){
                    a.add(i);
                }
                break;
            case OBJECT:
                for(Object i:(Object[]) object){
                    a.add(i);
                }
                break;
            default:
                a = null;
                break;
        }
        return a;
    }

}
