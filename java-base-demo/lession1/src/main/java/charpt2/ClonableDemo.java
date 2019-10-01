package charpt2;

import java.io.*;

/**
 * Created by zwz on 2019/8/20.
 */
public class ClonableDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        String desc = "Hello,World";
        Data data = new Data();
        data.setValue(1);
        data.setDesc(desc);
        //Data copy = data.clone();
        Data copy = data.deepClone();
        System.out.println("data == copy:"+(data == copy));
        System.out.println("data == copy:"+(data.getValue() == copy.getValue()));
        System.out.println("data == copy:"+(data.getDesc() == copy.getDesc()));
    }
}


class Data implements Cloneable,Serializable{
    private int value;
    private String desc;

    public Data() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 通常将protected 访问性提升为public
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Data clone() throws CloneNotSupportedException {
        //默认为浅拷贝，原生类型没有深浅关系
        Data data = (Data) super.clone();
        //深拷贝实现，一般使用系列化实现
        data.desc = new String(this.desc);
        return data;
    }

    public Data deepClone(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream data = null;
        Data res = null;
        try {
            data = new ObjectOutputStream(byteArrayOutputStream);
            data.writeObject(this);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            res = (Data) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;

    }
}
