package com.me.ParameterTest;

import java.util.HashMap;
import java.util.Map;

public class ParameterUtilsTest {
    public static void main(String[] args) {
//        String parameterString =
//                "${user} is userName, '$[1]' '$[add_months(yyyyMMdd,12*2)]' '$[add_months(yyyyMMdd,-12*2)]' '$[add_months(yyyyMMdd,3)]' '$[add_months(yyyyMMdd,-4)]' "
//                        + "'$[yyyyMMdd+7*2]' '$[yyyyMMdd-7*2]'  '$[yyyyMMdd+3]'  '$[0]' '$[yyyyMMdd-3]' '$[HHmmss+2/24]' '$[HHmmss-1/24]' '$[HHmmss+3/24/60]' '$[HHmmss-2/24/60]'  '$[3]'";
        String parameterString = "'$[yyyy-MM-dd]'";
        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("user", "Kris");
//        parameterMap.put(DateConstants.PARAMETER_DATETIME, "20201201123000");
        parameterString = ParameterUtils.convertParameterPlaceholders(parameterString, parameterMap);
        System.out.println(parameterString);

        assert "Kris is userName, '$[1]' '20221201' '20181201' '20210301' '20200801' '20201215' '20201117'  '20201204'  '$[0]' '20201128' '143000' '113000' '123300' '122800'  '$[3]'" == parameterString;
//        Assertions.assertEquals(
//                "Kris is userName, '$[1]' '20221201' '20181201' '20210301' '20200801' '20201215' '20201117'  '20201204'  '$[0]' '20201128' '143000' '113000' '123300' '122800'  '$[3]'",
//                parameterString);
    }
}
