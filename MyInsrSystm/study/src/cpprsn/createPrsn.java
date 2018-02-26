package cpprsn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class createPrsn
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("create_prsn.xml");
		cpprsn user = (cpprsn) context.getBean("NewPrsn");
		int age = user.getAge();
		String sex=user.getSex();
		System.out.println(age);
		System.out.println(sex);
		((AbstractApplicationContext) context).close();
	}

}
