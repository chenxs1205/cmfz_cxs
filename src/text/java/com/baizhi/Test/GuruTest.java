package com.baizhi.Test;

import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GuruTest extends BasicTest {
    @Autowired
    private GuruService guruService;


    @Test
    public void test1(){
        Guru guru=new Guru();
        guru.setName("陈大师");
        guru.setHeadPic("没得头像");
        guru.setSex("男");


        guruService.add(guru);
    }


    @Test
    public void test2(){
        guruService.delete("21826c4b-8906-4402-b13a-faa60de735f0");
    }


    @Test
    public void test3(){
        List<Guru> emps = guruService.findByPage(1, 2);
        for (Guru emp : emps) {
            System.out.println(emp);
        }
    }

}
