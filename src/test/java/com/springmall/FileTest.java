package com.springmall;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;


/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/17 20:50
 */

@SpringBootTest
public class FileTest {

    @Test
    public void test(){
        /*File file = new File("C:/SpringMallImage/" + "29d5854bac9946a6a898d88375e497b8.jpg");
        file.delete();*/
        HashMap<Object, Object> map = new HashMap<>();
        map.put(1,"a");
        map.put(2,"a");
        System.out.println(map.size());

    }
}
