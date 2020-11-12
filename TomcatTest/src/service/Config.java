// 获取基础配置项 类
package service;
import java.util.Properties;
import java.io.InputStream;

public class Config {
  public static String getConfig (String key) {
      // 传入key值 输出相应的配置参数
        String ret = null;
        try {
            InputStream in = Config.class.getResourceAsStream("./config.properties");
            Properties properties = new Properties();
            properties.load(in);
            ret = properties.getProperty(key);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
