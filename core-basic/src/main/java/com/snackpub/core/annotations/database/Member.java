//: annotations/database/Member.java
package com.snackpub.core.annotations.database;

import java.util.Date;

@DBTable(name = "MEMBER")
public class Member {
  // 注解中定义了一个名为value的元素，且它在使用时是唯一需要赋值的元素
  // 那么就可以省略 key-value这种写法，直接填写值
  @SQLString(30) String firstName;
  @SQLString(50) String lastName;
  @SQLInteger Integer age;
  @SQLString(value = 30, constraints = @Constraints(primaryKey = true))
  String handle;

  @SQLDate(name = "create_time",constraints = @Constraints(allowNull = false),exist = false)
  Date createTime;

  static int memberCount;
  public String getHandle() { return handle; }
  public String getFirstName() { return firstName; }
  public String getLastName() { return lastName; }
  public String toString() { return handle; }
  public Integer getAge() { return age; }
  public Date getCreateTime() { return createTime; }
} ///:~
