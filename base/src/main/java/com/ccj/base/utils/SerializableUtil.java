package com.ccj.base.utils;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by ccj on 2015/12/7.
 * 将 对象转为 字符串,易于保存 上传等
 *
 */
public class SerializableUtil {

    public static String objToStr(Object obj) throws IOException {
        if (obj == null) {
            return "";
        }
        //实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //然后将得到的字符数据装载到ObjectOutputStream
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        //writeObject 方法负责写入特定类的对象的状态，以便相应的readObject可以还原它
        oos.writeObject(obj);
        //最后，用Base64.encode将字节文件转换成Base64编码，并以String形式保存
        String listString = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        //关闭oos
        oos.close();
        return listString;

    }


    //将序列化的数据还原成Object

    public static Object strToObj(String str) throws IOException {
        byte[] mByte = Base64.decode(str.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(mByte);
        ObjectInputStream ois = new ObjectInputStream(bais);
        try {

            return ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> String listToString(List<T> list) throws IOException {
        //实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //然后将得到的字符数据装载到ObjectOutputStream
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        //writeObject 方法负责写入特定类的对象的状态，以便相应的readObject可以还原它
        oos.writeObject(list);
        //最后，用Base64.encode将字节文件转换成Base64编码，并以String形式保存
        String listString = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        //关闭oos
        oos.close();
        return listString;
    }


    public static <T> List<T> stringToList(String str) throws IOException {
        byte[] mByte = Base64.decode(str.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(mByte);
        ObjectInputStream ois = new ObjectInputStream(bais);
        List<T> stringList = null;
        try {
            stringList = (List<T>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return stringList;
    }


}
