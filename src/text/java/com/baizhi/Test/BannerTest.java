package com.baizhi.Test;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class BannerTest extends BasicTest {
    @Autowired
    private BannerService bannerService;


    @Test
    public void test1(){
        Banner banner=new Banner();
        banner.setTitle("ccc");
        banner.setImgPath("F:\\framework\\ideacode\\cmfz_cxs\\src\\main\\webapp\\img\\1.gif");
        banner.setDate(new Date());
        banner.setStatus("123");
        banner.setDescription("");


        bannerService.add(banner);
    }


    @Test
    public void test2(){
        bannerService.delete("7dd0321c-d093-453f-885b-b32fbbf7cd5c");
    }


    @Test
    public void test3(){
        List<Banner> emps = bannerService.findByPage(1, 2);
        for (Banner emp : emps) {
            System.out.println(emp);
        }
    }
    @Test
    public void test4(){
        Banner banner = bannerService.queryOne("29f52dcc-a65c-4370-aa85-c59b1c0ce147");
        System.out.println(banner);
    }

    @Test
    public void test5(){
        Banner banner =new  Banner();
        banner.setTitle("1231231");
        banner.setStatus("asdadasd");
        banner.setDescription("asdasdasd");
        banner.setDate(new Date());
        banner.setId("29f52dcc-a65c-4370-aa85-c59b1c0ce147");
        bannerService.update(banner);
        System.out.println(banner);
    }
}
