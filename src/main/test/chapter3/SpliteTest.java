package chapter3;

import com.alibaba.druid.util.StringUtils;

public class SpliteTest {

  public static void main(String[] args) {
    String str ="+user+10";
    String[] rmpty = new String[3];
    String[] arry = str.split("\\+");
    System.out.println("分割长度=" + arry.length);
    for(String x : arry) {
      if(StringUtils.isEmpty(x)) {
        System.out.print("null");
      }
      System.out.print(x + ",");
    }
  }
}
