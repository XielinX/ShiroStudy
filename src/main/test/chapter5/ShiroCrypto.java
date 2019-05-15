package chapter5;

import java.security.Key;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.BlowfishCipherService;
import org.apache.shiro.crypto.DefaultBlockCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;

/**
 * chapter4:编码/解码
 * @author G0009263
 * @date 05/14/2019
 * @tool Eclipse
 */
public class ShiroCrypto {

  @Test
  public void base64Test() {// Base64编码/解码
    //base64
    String str = "hello";
    //base64编码
    String base64Encode = Base64.encodeToString(str.getBytes());//使用shiro的jar包
    //base64解码
    String str2 = Base64.decodeToString(base64Encode);
    System.out.println("base64Encode:" + base64Encode);
    System.out.println("str2:" + str2);
    Assert.assertEquals(str,str2);
    
    //CodecSupport类用于再byte[]和String之间转换
    
  }

  @Test
  public void codecSupportTest() { // 字符串与字节数组的互转
    String str = "admin";
    //byte[]
    byte[] bytes = CodecSupport.toBytes(str, "utf-8");
    //String
    String str2 = CodecSupport.toString(bytes, "utf-8");
    System.out.println("str:" + str);
    System.out.println("str2:" + str2);
  }
  
  @Test
  public void hexEncodeTest() {  // Hex十六进制编码解码
    //16进制
    String str = "hello";
    //hex编码
    String hexEncode = Hex.encodeToString(str.getBytes());
    //hex解码
    String str2 = new String(Hex.decode(hexEncode.getBytes()));
    System.out.println("str:" + hexEncode);
    System.out.println("str2:" + str2);
  }
  
  @Test
  public void MD5Test() { // MD5加密
    
    String str = "admin";
    String salt = "123";
    //MD5加密
    String md5 = new Md5Hash(str,salt).toString();
    //迭代2次
    String md5_twice = new Md5Hash(str,salt,2).toString();
    System.out.println("无盐值:" + new Md5Hash(str).toString());
    System.out.println("加盐:" + md5);
    System.out.println("2次MD5:" + md5_twice);
  }
  
  @Test
  public void sha256Test() { //SHA256加密
    //SHA
    String str2 = "admin";
    String salt2 = "123";
    String sha256 = new Sha256Hash(str2,salt2).toString();
    System.out.println("SHA256:" + sha256);
  }
  
  @Test
  public void sha1Test() { //SHA1加密
    //SHA
    String str2 = "admin";
    String salt2 = "123";
    String sha1 = new Sha1Hash(str2,salt2).toString();//SHA1,SHA512
    System.out.println("SHA1:" + sha1);
  }
  
  @Test
  public void sha384Test() { //SHA384加密
    //SHA
    String str2 = "admin";
    String salt2 = "123";
    String sha384 = new Sha1Hash(str2,salt2).toString();//SHA1,SHA512
    System.out.println("SHA384:" + sha384);
  }
  
  @Test
  public void sha512Test() { //SHA512加密
    //SHA
    String str2 = "admin";
    String salt2 = "123";
    String sha512 = new Sha512Hash(str2,salt2).toString();//SHA1,SHA512
    System.out.println("SHA512:" + sha512);
  }
  
  @Test
  public void simpleHashTest() { //simpleHash加密
    String str2 = "admin";
    String salt2 = "123";
    //内部实现使用java的MessaDigest
    String simpleHash = new SimpleHash("SHA-1",str2,salt2).toString();//可以指定散列算法
    System.out.println("simpleHash:" + simpleHash);
  }
  
  @Test
  public void HashServiceTest() { // shiro的HashService
    
    //默认算法SHA-512
    DefaultHashService hashServie = new DefaultHashService();
    
    //可以修改算法
    hashServie.setHashAlgorithmName("SHA-512");
    
    //私盐,默认无,在散列时自动与用户传入的公盐混合产生一个新盐
    hashServie.setPrivateSalt(new SimpleByteSource("123"));
    
    //是否生成公盐,默认false
    hashServie.setGeneratePublicSalt(true);
    
    //生成公盐(SecureRandomNumberGenerator用于生成一个随机数：)
    hashServie.setRandomNumberGenerator(new SecureRandomNumberGenerator());
    
    //设置hash值迭代次数
    hashServie.setHashIterations(1);
    
    //构建一个HashRequest，传入算法、数据、公盐、迭代次数
    HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5")
        .setSource(ByteSource.Util.bytes("hello")).setSalt(ByteSource.Util.bytes("123"))
        .setIterations(2).build();
    
    String hex = hashServie.computeHash(request).toHex();
    
    System.out.println("hex:" + hex);
    
  }
  
  @Test
  public void randomNumTest() { // 生成一个随机数
    SecureRandomNumberGenerator randomNumberGenerator =  
        new SecureRandomNumberGenerator();  
    //
   //randomNumberGenerator.setSeed("123".getBytes()); // 23ae809ddacaf96af0fd78ed04b6a265  
   String hex = randomNumberGenerator.nextBytes().toHex(); 
   System.out.println("随机数:" + hex);
   System.out.println("随机数长度:" + hex.length());//32
  }
  
  @Test
  public void aesTest() { //对称的AES加密
    AesCipherService  aesCipherService = new AesCipherService();
    //设置key长度
    aesCipherService.setKeySize(128);
    //生成key
    Key key = aesCipherService.generateNewKey();
    
    String text = "123";
    //加密
    String ciphertext = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();// Key接口只有 Byte[] getEncoded()方法
    //解密,ciphertext字符经过了toHex()编码,所以要先解码
    String text2 = new String(aesCipherService.decrypt(Hex.decode(ciphertext),key.getEncoded()).getBytes());
    
    System.out.println("加密:" + ciphertext);
    System.out.println("解密:" + text2);
  }
  
  @Test
  public void blowFishTest() {
    BlowfishCipherService blowFishCipherService = new BlowfishCipherService();
    //
    blowFishCipherService.setKeySize(128);
    
    //
    Key key = blowFishCipherService.generateNewKey();
    
    String text = "123";
    String encrytext = blowFishCipherService.encrypt(text.getBytes(),key.getEncoded()).toHex();
    String text2 = new String(blowFishCipherService.decrypt(Hex.decode(encrytext), key.getEncoded()).getBytes());
    System.out.println("加密:" + encrytext);
    System.out.println("解密:" + text2);
  }
  
  @Test
  public void defaultBlockCipperService() throws Exception{
    
    //对称加密，使用Java的JCA（javax.crypto.Cipher）加密API，常见的如 'AES', 'Blowfish'
    DefaultBlockCipherService blockCipperService = new DefaultBlockCipherService("AES");
    blockCipperService.setBlockSize(128);
    
    Key key = blockCipperService.generateNewKey();
    String text = "123";
    String encrytext = blockCipperService.encrypt(text.getBytes(), key.getEncoded()).toHex();
    String text2 = new String(blockCipperService.decrypt(Hex.decode(encrytext), key.getEncoded()).getBytes());
    
    System.out.println("加密:" + encrytext);
    System.out.println("解密:" + text2);
    // 此段测试会抛异常,百度下说是安装的jdk问题不支持64bit,支持X86bit的,未验证
    //Unable to acquire a Java JCA Cipher instance using javax.crypto.Cipher.getInstance
    //
  }
}
