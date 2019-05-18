package com.xlx.ss.shiro.chapter6.tools;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;

public class SubjectTool {

  public static Subject getSubject() {
    Subject subject = ThreadContext.getSubject();
    
    //自定义创建
    //new Subject.Builder().principals(principals).authenticated(true/false).buildSubject();  
    
    if(subject == null) {
      subject = (new Subject.Builder()).buildSubject();
      ThreadContext.bind(subject);
    }
    return subject;
  }
}
