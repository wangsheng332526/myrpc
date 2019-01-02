
package com.data.fly.demo;

import java.io.Serializable;

/**
  * TODO 请在此处添加注释
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年12月29日  上午9:32:31  
  * @since 2.0
  */
public class Person implements Serializable{
	 /**    */
	private static final long serialVersionUID = 7187450104958881416L;
	private String name;
	private int age;
	private Integer sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
}
