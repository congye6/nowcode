package cn.edu.nju.nowcode.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.io.*;

/**
 * Created by cong on 2018-05-23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SensitiveServiceTester {

    @Autowired
    private SensitiveService sensitiveService;

    @Test
    public void loadSensitive(){
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(new File("keywords")));
            while(true){
                String line=bufferedReader.readLine();
                if(StringUtils.isEmpty(line))
                    break;
                sensitiveService.addSensitive(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sensitiveReplace(){
        System.out.println(sensitiveService.replaceSensitive("kbjTMDlbjkfjggjjgkfjgkfjgggggggggggggggggkfkkkkkl;f;k;kfkdlklfgkldfkgldfkglfdklkd;lfkglfdklgkfldkglkfdlkg;dfkglkdflkgldfkglfkdlkglfkbmkgmbkgbmkgbmkmgkmkmbgkmbgkmkgkgmbjuduhguthgurghtughuthh,j;l,l,lm,l,blmnkmbjgnbjngfjnbjgnb,nl,blvfmbkbgnjgnjnbjhdbhbcfygbngjnbiytbhiydmgjbjgkfbfkdbjgdfklkgmblkmfdlfld;vvf,"));
        System.out.println(sensitiveService.replaceSensitive("TMD666"));
        System.out.println(sensitiveService.replaceSensitive("联国555"));
    }


}
