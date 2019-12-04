package com.aisino.guava;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.List;

public class CommonsTest {
    public static void main(String[] args) {
        String str = ",1,2,3,4,5,";
        String strip = StringUtils.stripEnd(str,",");
        System.err.println(strip);

        String[] split = str.split(",");

        List<String> list = Splitter.on(",").splitToList(str);
        System.err.println(list);

        Date date = DateUtils.addDays(new Date(), 30);

    }
}
