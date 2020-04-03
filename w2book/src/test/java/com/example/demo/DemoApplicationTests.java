package com.example.demo;

import com.ying.background.Application;
import com.ying.background.dao.model.CsSbZszm;
import com.ying.background.mapper.BookInfoMapper;
import com.ying.background.mapper.CsSbZszmMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class DemoApplicationTests {

//    @Autowired
//    private CustomerMapper customerMapper;
//
//    @Autowired
//    private ICustomerService customerService;
//
    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Autowired
    private CsSbZszmMapper csSbZszmMapper;

//
//    @Test
//    public void test2(){
////        System.out.println(customerMapper.toLogin("2018","zqx2018") == null);
//
//        System.out.println(customerService.login("2018", "zqx2018", null, null).getToken());
//    }
//
    @Test
    public void test3(){
        System.out.println(bookInfoMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void test4() throws Exception{
        List<CsSbZszm> csSbZszms = csSbZszmMapper.selectAll();
        System.out.println(csSbZszms.size());
        Map<String, List<CsSbZszm>> map = new HashMap<>();
        //按UUID+SJLY分组
        for(CsSbZszm csSbZszm : csSbZszms){
            String flag = csSbZszm.getUuid()+csSbZszm.getSjlySwjgdm();
            List<CsSbZszm> csSbZszms1 = map.get(flag);
            if(CollectionUtils.isEmpty(csSbZszms1)){
                csSbZszms1 = new ArrayList<>();
                map.put(flag, csSbZszms1);
            }
            csSbZszms1.add(csSbZszm);
        }
        List<CsSbZszm> delete = new ArrayList<>();
        if(MapUtils.isNotEmpty(map)){
            Set<Map.Entry<String, List<CsSbZszm>>> entries = map.entrySet();
            Iterator<Map.Entry<String, List<CsSbZszm>>> iterator = entries.iterator();
            while (iterator.hasNext()){
                Map.Entry<String, List<CsSbZszm>> next = iterator.next();
                List<CsSbZszm> value = next.getValue();
                //按修改日期正序
                Collections.sort(value, (o1, o2) -> o1.getXgrq().compareTo(o2.getXgrq()));
                //移除最后一条数据
                value.remove(value.size()-1);
                delete.addAll(value);
            }
        }
        StringBuilder str = new StringBuilder();
        if(CollectionUtils.isNotEmpty(delete)){
            for(CsSbZszm csSbZszm : delete){
                str.append("DELETE FROM CS_SB_ZSZM WHERE PZXH=");
                str.append(csSbZszm.getPzxh());
                str.append(" AND SJLY_SWJGDM='");
                str.append(csSbZszm.getSjlySwjgdm());
                str.append("';\r\n");
            }
        }
        str.append("COMMIT;");
        System.out.println(str.length());
        FileOutputStream outputStream = new FileOutputStream("D:/data/ZszmCleanUp_sc.sql");
        byte[] bytes = str.toString().getBytes();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

}
