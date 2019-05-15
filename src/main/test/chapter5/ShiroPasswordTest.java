package chapter5;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.junit.Test;

import chapter3.AbstractRole;

public class ShiroPasswordTest extends AbstractRole {

  @Test
  public void passwordServiceTest() {// 
    login("classpath:part5/shiro-passwordservice.ini","wu","123");
  }
  
  @Test
  public void jdbcpasswordServiceTest() {// 从数据查找
    login("classpath:part5/shiro-jdbc-passwordservice.ini","wu","123");
  }
  
  @Test
  public void generaterPassword() {// 密码加密(明文密码+盐),盐值生成
    
    String username = "liu";
    String plainpwd = "123";
    //算法名称
    String algorithm = "md5";//大小写无关
    //随机数
    String randomnum = new SecureRandomNumberGenerator().nextBytes().toHex();//使用默认的16bite
    //盐=用户名+随机数
    String salt = username + randomnum;
    //hash迭代次数
    int hashiterations = 2;
    
    //加密
    SimpleHash simpleHash = new SimpleHash(algorithm,plainpwd,salt,hashiterations);
    
    String encrypwd = simpleHash.toHex();//xxx.toHex()方法就是变为16进制编码,Hex.encodeToString(str.getBytes())
    System.out.println("加密的密码:" + encrypwd);//0c613e5023fcbaa9b3fef7805008dffe
    System.out.println("随机数:" + randomnum);
  }
  
  @Test
  public void hashedCredentialsMatcherTest() {
    // 1.先用generaterPassword去生成密码与随机数
    // 2.将密码与随机数分别赋值给Realm2的密码变量,随机数量中
    login("classpath:part5/shiro-hashedCredentialsMatcher.ini","liu","123");
  }
  
  @Test
  public void jdbchashedCredentialsMatcherTest() {//数据库查询 salt=24520ee264eab73ec09451d0e9ea6aac
   //Shiro默认使用了apache commons BeanUtils，默认是不进行Enum类型转型的，此时需要自己注册一个Enum转换器
    BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);
    
    login("classpath:part5/shiro-jdbc-hashedCredentialsMatcher.ini","liu","123");
  }
  
  
  
  
  private class EnumConverter extends AbstractConverter {

    @SuppressWarnings("rawtypes")
    @Override
    protected String convertToString(Object value) throws Throwable {
      // TODO Auto-generated method stub
      return ((Enum)value).name();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected Object convertToType(final Class type, final Object value) throws Throwable {
     
      return Enum.valueOf(type, value.toString());
    }
    @Override
    protected Class<?> getDefaultType() {
      // TODO Auto-generated method stub
      return null;
    }
    
    
    
    
    
    
  }

  
  

  
  
}
