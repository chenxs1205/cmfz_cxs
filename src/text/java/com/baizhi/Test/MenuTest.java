package com.baizhi.Test;

import com.baizhi.service.MenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuTest extends  BasicTest{
    @Autowired
    private MenuService menuService;



    @Test
    public void test1(){
        List list=menuService.queryAllMenu();
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
