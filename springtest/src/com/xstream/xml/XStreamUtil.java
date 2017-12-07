package com.xstream.xml;

import com.thoughtworks.xstream.XStream;  
import com.thoughtworks.xstream.io.xml.DomDriver;  
/** 
 * XStream������ 
 * @author sun.kai 
 * 2016��8��13�� 
 */  
public class XStreamUtil {  
  
    private static XStream xStream;  
      
    //JVM������ʱ��ִ����Щ��̬�Ĵ���飬���static������ж����JVM���������������г��ֵ��Ⱥ�˳������ִ�����ǣ�ÿ�������ֻ�ᱻִ��һ�Ρ�  
    static{  
        xStream = new XStream(new DomDriver());  
        /* 
         * ʹ��xStream.alias(String name, Class Type)Ϊ�κ�һ���Զ����ഴ�����ൽԪ�صı��� 
         * �����ʹ�ñ����������ɵı�ǩ��Ϊ��ȫ�� 
         */  
        xStream.alias("person", Person.class);  
        xStream.alias("address", Address.class);  
        //��ĳһ��������ԣ���Ϊxmlͷ��Ϣ�����ԣ��������ӽڵ�  
        //xStream.useAttributeFor(Address.class, "country");  
        //������ȡ����  
        //xStream.aliasField("ʡ", Address.class,"province");  
    }  
      
    //xmlתjava����  
    public static Object xmlToBean(String xml){  
        return xStream.fromXML(xml);  
    }  
      
    //java����תxml  
    public static String beanToXml(Object obj){  
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xStream.toXML(obj);  
    }  
}  