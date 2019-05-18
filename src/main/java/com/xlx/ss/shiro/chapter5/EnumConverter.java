package com.xlx.ss.shiro.chapter5;

import org.apache.commons.beanutils.converters.AbstractConverter;

public class EnumConverter extends AbstractConverter {

  @SuppressWarnings("rawtypes")
  @Override
  protected String convertToString(Object value) throws Throwable {
    
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
