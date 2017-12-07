package com.xstream.xml;

import java.util.ArrayList;  
import java.util.List;  
     
public class TestXStream {  
  
    public static void main(String[] args) {  
        long time1  = System.currentTimeMillis();  
        
        //����java����תxml��java�����а������϶���  
        Person person = new Person();  
        person.setName("admin����Ա");  
        person.setSex("��");  
        person.setAge(25);  
        List<Address> adds = new ArrayList<Address>();  
        Address address1 = new Address();  
        address1.setCountry("�й�");  
        address1.setProvince("����");  
        address1.setCity("����");  
        address1.setCounty("����");  
        Address address2 = new Address();  
        address2.setCountry("�й�");  
        address2.setProvince("�Ϻ�");  
        address2.setCity(null);  
        address2.setCounty("");  
        adds.add(address1);  
        adds.add(address2);  
        person.setAddress(adds);  
        
        String xml = XStreamUtil.beanToXml(person);  
        System.out.println(xml);  
          
        //����xmlתjava����  
        Person person2 = (Person) XStreamUtil.xmlToBean(xml);  
        System.out.println(person2.getAddress().get(1).getProvince());  
          
        long time2 = System.currentTimeMillis();  
        System.out.println((time2-time1)+"ms");  
    }  
}  
