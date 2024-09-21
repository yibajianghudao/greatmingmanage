package com.manage.greatming.greatmingmanage.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LogRead {

    public static String ReadLog(String allline) {
        StringReader alllineReader = new StringReader(allline);
        BufferedReader reader = new BufferedReader(alllineReader);
        Map<String, Integer> result = new HashMap<>();
        String line;
        try{
            while ((line = reader.readLine()) != null) {
                //  18:02:20 - GreatMing_SJ_smnz_YB[III] <img=ico_crossbow> 74thPGC_UEST_LLL
                String[] lineList = line.split(" ");
                if (lineList.length > 3 && lineList[3].startsWith("GreatMing")) {
                    try {
                        String[] nameList = lineList[3].split("_");
                        // String group = nameList[1];
                        String name = getNameFromLineList(nameList);
                        // 是击杀的数据才统计，排除tk数据
                        if (!name.equals("")  && lineList[4].startsWith("<")) {
                            result.putIfAbsent(name, 0);
                            result.put(name, result.get(name) + 1);
                        }
                        String tk = findTK(line);
                        if (!tk.equals("")) {
                            result.put(tk, result.get(tk) - 1);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("处理行出错: "+line);
                    }
                }
            }
        }catch (IOException e){
            System.out.println("读取字符串内容失败");
        }
        return ReadedMapToString(result);
    }

    private static String getNameFromLineList(String[] nameList) {
        String name = "";
        if (nameList.length >= 4) {
            name = nameList.length > 4 ? nameList[2] + "_" + nameList[3] : nameList[2];
        }
        return name;
    }
    //str_ch=" 20:07:25 - 74th_KnWe_dunh[kfive]误 杀 7PUSL_IV_RN_Sierz_BT。  "
    //str_ch_mount=" 20:07:18 - 25thNIR_inf_Rec_FA误 杀 18thRUG_2Lt_genossen的 马 。  "
    public static String findTK(String line){
        String name = "";
        if (line.contains("误 杀") && !line.contains("马")) {
            String[] nameList = line.split(" ")[3].replace("误", "").split("_");
            name = getNameFromLineList(nameList);
        }
        return name;
    }

    public static String ReadedMapToString(Map<String, Integer> map){
        String result = "";
        for (Entry<String, Integer> entry : map.entrySet()) {
            String username = entry.getKey();
            int kills = entry.getValue();
            result+=username + " ";
            result+=kills + "\n";
        }
        return result;

    }

    public static String CountData(String logReadedData) {
        StringReader alllineReader = new StringReader(logReadedData);
        BufferedReader reader = new BufferedReader(alllineReader);
        Map<String, Integer> resultAttendance = new HashMap<>();
        Map<String, Integer> resultKills = new HashMap<>();
        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
                String[] lineList = line.split(" ");
                String username = lineList[0];
                int kills = Integer.parseInt(lineList[1]);
                resultAttendance.putIfAbsent(username, 0);
                resultKills.putIfAbsent(username, 0);
                resultAttendance.put(username, resultAttendance.get(username)+1);
                resultKills.put(username, resultKills.get(username)+kills);
            }
            line = CountedMapToString(resultAttendance, resultKills);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;


    }
    public static String CountedMapToString(Map<String, Integer> mapAttendance, Map<String, Integer> mapKills){
        String result = "";
        for (Entry<String, Integer> entry : mapAttendance.entrySet()) {
            String username = entry.getKey();
            int attendance = entry.getValue();
            int kills = mapKills.get(username);
            result+=username + " ";
            result+=attendance + " ";
            result+=kills + "\n";
        }
        return result;

    }

}
