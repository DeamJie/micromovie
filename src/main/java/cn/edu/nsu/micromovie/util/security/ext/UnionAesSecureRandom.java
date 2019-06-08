package cn.edu.nsu.micromovie.util.security.ext;

import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;

import cn.edu.nsu.micromovie.util.security.ext.provider.crypto.CryptoProvider;
import cn.edu.nsu.micromovie.util.security.ext.provider.crypto.SHA1PRNG_SecureRandomImpl;
/**
 * 通用AES随机算法；
 * 使用java自带的SecureRandom算法加密，在unix/linix/windows环境上结果完全不一样；
 * UnionAesSecureRandom.java 可以解决这个一问题
 * @author jiangln
 *
 */
public class UnionAesSecureRandom extends SecureRandom
{
  private static final long serialVersionUID = 1L;

  public UnionAesSecureRandom(SecureRandomSpi secureRandomSpi, Provider provider)
  {
    super(secureRandomSpi, provider);
  }

  public static SecureRandom getInstance()
  {
    return new UnionAesSecureRandom(new SHA1PRNG_SecureRandomImpl(), 
      new CryptoProvider());
  }
}