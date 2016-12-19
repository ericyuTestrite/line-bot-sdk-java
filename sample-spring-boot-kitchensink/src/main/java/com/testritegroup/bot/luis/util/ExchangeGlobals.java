package com.testritegroup.bot.luis.util;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExchangeGlobals {
  /**
   * Logger for this class
   */
  private static Log log = LogFactory.getLog(com.testritegroup.bot.luis.util.ExchangeGlobals.class);
  // public constant
  private final static String BUNDLE_NAME = "luistr";
  private static ResourceBundle cResources;
  /**
   *  Returns a string value from a resource key. If the key can't be found,
   *  the key is returned as value (so we known which key is missing)
   */
  public static String getString(String aKey) {
    String str = null;
    try {
      str = cResources.getString(aKey);
    }
    catch (MissingResourceException mre) {
      // warn
      if (log.isWarnEnabled()) {
        log.warn("resource " + aKey + " not found!");
      }
    }
    return str;
  }
  /**
   *  Parameterized message with multiple argument. The second argument is
   *  an array of strings. The order of the strings must match the order of
   *  parameters in the message.
   */
  public static String getString(String aKey, Object[] aParams) {
    String str = null;
    String message = getString(aKey);
    if (message != null) {
      str = MessageFormat.format(message, aParams);
    }
    return str;
  }
  /**
   *  Parameterized message with one argument.
   */
  public static String getString(String aKey, String aParam1) {
    String str = null;
    Object[] args = {
        aParam1};
    String message = getString(aKey);
    if (message != null) {
      str = MessageFormat.format(message, args);
    }
    return str;
  }
  static {
    try {
      cResources = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault());
    }
    catch (MissingResourceException mre) {
      //error
      if (log.isErrorEnabled()) {
        log.error(BUNDLE_NAME + ".properties not found");
      }
    }
  }
}
