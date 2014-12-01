package com.dellnaresh.threads.executors;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nareshm on 12/1/14.
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();
        map.put(null,null);
        map.put(null,null);

        System.out.println(map.size());
    }
}
