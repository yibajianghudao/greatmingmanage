package com.manage.greatming.greatmingmanage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.manage.greatming.greatmingmanage.Utils.LogRead;

@SpringBootTest
public class LogReadTest {

    @Test
    void FindTKTest(){
        String name = LogRead.findTK(" 20:07:25 - GreatMing_WJ_TestName_BH误 杀 7PUSL_IV_RN_Sierz_BT。  ");
        assert (name.equals("TestName"));
    }
    @Test
    void FindTKHorseTest(){
        String name = LogRead.findTK(" 20:07:25 - GreatMing_WJ_TestName_BH误 杀 7PUSL_IV_RN_Sierz_BT的 马 。    ");
        assert (name.equals(""));
    }
    @Test
    void ReadLogTest() throws FileNotFoundException, IOException{
        String path = "./src/test/java/com/manage/greatming/greatmingmanage/test.txt";  
        String fileAsString = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
      
            while (line != null) {
              sb.append(line).append("\n");
              line = br.readLine();
            }
      
            fileAsString = sb.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        String result = LogRead.ReadLog(fileAsString);
        assert(result.equals(""));
    }
    
}
