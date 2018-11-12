package com.baizhi.Test;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AlbumTest extends BasicTest {
    @Autowired
    private AlbumService albumService;

@Autowired
    private AlbumDAO albumDAO;
@Test
    public void test1(){
        Album album=new Album();

        albumService.add(album);
    }


    @Test
    public void test2(){
        albumService.delete("7dd0321c-d093-453f-885b-b32fbbf7cd5c");
    }


    @Test
    public void test4(){
        Album album = albumService.queryOne("29f52dcc-a65c-4370-aa85-c59b1c0ce147");
        System.out.println(album);
    }

    @Test
    public void test5(){
        Album album =new  Album();

        albumService.update(album);
        System.out.println(album);
    }

    @Test
    public void test6(){
        List<Album> all = albumService.findAll();
        for (Album album : all) {
            System.out.println(album);
        }


    }
}
