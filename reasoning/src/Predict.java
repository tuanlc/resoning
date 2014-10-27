
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tuanlc
 */
public class Predict {
    private Rules.SEASON season;
    private Rules.HUMIDITY humidity;
    private Rules.TEMPERATURE temperature;
    private Rules.WIND wind;
    private Rules.RESULT result;
    public void readRules(String line){
        String[] splitLine = line.split(",");
        if(splitLine[0].endsWith("SP"))
            this.season = Rules.SEASON.SPRING;
        else if(splitLine[0].equals("SU"))
            this.season = Rules.SEASON.SUMMER;
        else if(splitLine[0].equals("AU"))
            this.season = Rules.SEASON.AUTUMN;
        else if(splitLine[0].equals("WI"))
            this.season = Rules.SEASON.WINTER;
        
        if(splitLine[1].equals("L"))
            this.humidity = Rules.HUMIDITY.LOW;
        else if(splitLine[1].equals("M"))
            this.humidity = Rules.HUMIDITY.MEDIUM;
        else if(splitLine[1].equals("H"))
            this.humidity = Rules.HUMIDITY.HIGHT;
        
        if(splitLine[2].equals("VL"))
            this.temperature = Rules.TEMPERATURE.VL;
        else if(splitLine[2].equals("L"))
            this.temperature = Rules.TEMPERATURE.L;
        else if(splitLine[2].equals("N"))
            this.temperature = Rules.TEMPERATURE.N;
        else if(splitLine[2].equals("H"))
            this.temperature = Rules.TEMPERATURE.H;
        else if(splitLine[2].equals("VH"))
            this.temperature = Rules.TEMPERATURE.VH;
        
        if(splitLine[3].equals("L"))
            this.wind = Rules.WIND.LOW;
        else if(splitLine[3].equals("M"))
            this.wind = Rules.WIND.MEDIUM;
        else if(splitLine[3].equals("H"))
            this.wind = Rules.WIND.HIGHT;
        
        if(splitLine[4].equals("0"))
            this.result = Rules.RESULT.STAY_HOME_COLD;
        else if(splitLine[4].equals("1"))
            this.result = Rules.RESULT.AC_OUT_SIDE;
        else if(splitLine[4].equals("2"))
            this.result = Rules.RESULT.NEED_WATER_HOT;
        else if(splitLine[4].equals("3"))
            this.result = Rules.RESULT.STAY_HOME_HOT;
        else if(splitLine[4].equals("4"))
            this.result = Rules.RESULT.HOT;
        else if(splitLine[4].equals("5"))
            this.result = Rules.RESULT.NEED_WATER_COLD;
        else if(splitLine[4].equals("6"))
            this.result = Rules.RESULT.COLD;
        else if(splitLine[4].equals("7"))
            this.result = Rules.RESULT.RAIN;
    }
    
    public  Rules.RESULT compareRules(Rules.SEASON sea, Rules.HUMIDITY humi, Rules.TEMPERATURE temper, Rules.WIND wi){
        String file = "/home/tuanlc/developerTool/eclipse/workspace/reasoning/src/setOfRules.txt";
    try{
            InputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            System.out.println(""+ sea + humi + temper + wi);
            while((line = br.readLine()) != null){
                System.out.println(line);
                if(line.contains("#"))
                    continue;
                
                readRules(line);
                System.out.println(""+this.season + this.humidity + this.temperature + this.wind);
                
                
                if(this.season == sea && this.humidity == humi && this.temperature == temper 
                        && this.wind == wi)
                    return this.result;
            }
            br.close();
    }catch(Exception e){
        e.printStackTrace();
        return Rules.RESULT.NON_DETERMINE;
    }
    
    return Rules.RESULT.NON_DETERMINE;
    }
    
    public String convertResult(Rules.RESULT result){
        if(result == Rules.RESULT.AC_OUT_SIDE)
            return "Thời tiết này rất phù hợp cho các hoạt động ngoài trời";
        else if(result == Rules.RESULT.HOT)
            return "Nhiệt độ khá nóng, bạn lưu ý cần giữ cơ thể mát mẻ bằng cách hạn chế ra ngoài";
        else if(result == Rules.RESULT.NEED_WATER_COLD)
            return "Bạn nhớ uống nhiều nước và giữ ấm cho cơ thể";
        else if(result == Rules.RESULT.NEED_WATER_HOT)
            return "Bạn nhớ uống nhiều nước và giữ cho cơ thể mát mẻ";
        else if(result == Rules.RESULT.NON_DETERMINE)
            return "Không có gợi ý nào từ hệ thống";
        else if(result == Rules.RESULT.STAY_HOME_COLD)
            return "Nhiệt độ rất lạnh, tốt nhất là nên ở trong nhà và giữ ấm cho cơ thể";
        else if(result == Rules.RESULT.STAY_HOME_HOT)
            return "Nhiệt độ rất nóng, tốt nhất là nên ở trong nhà \\n và có một số biện pháp giảm nhiệt như: điều hòa, hơi nước,..";
        else if(result == Rules.RESULT.COLD)
            return "Nhiệt độ khá lạnh, Bạn nên giữ ấm cơ thể khi đi ra ngoài đường";
         else if(result == Rules.RESULT.RAIN)
            return "Khả năng có mưa là rất cao, bạn nên mang ô dù, áo mưa khi đi ra ngoài."; 
        
        return "Không có gợi ý nào từ hệ thống";
    }
    
}
