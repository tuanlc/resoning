/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tuanlc
 */
public class Rules {
    public  enum SEASON{
        SPRING,
        SUMMER,
        AUTUMN,
        WINTER
    }
    
    public enum HUMIDITY{
        LOW,
        MEDIUM,
        HIGHT
    }
    
    public enum TEMPERATURE{
        VL,
        L,
        N,
        H,
        VH
    }
    
    public enum WIND{
        LOW,
        MEDIUM,
        HIGHT
    }
    
    public enum RESULT{
        AC_OUT_SIDE,
        STAY_HOME_COLD,
        STAY_HOME_HOT,
        NEED_WATER_COLD,
        NEED_WATER_HOT,
        HOT,
        COLD,
        RAIN,
        NON_DETERMINE
    }
}
