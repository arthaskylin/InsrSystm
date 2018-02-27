package cpprsn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CreatePrsn
{

	public static void main(String Type)
	{
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("create_prsn.xml");
		Cpprsn user = (Cpprsn) context.getBean(Type);
		int age = user.getAge();
		String sex=user.getSex();
		System.out.println(age);
		System.out.println(sex);
		((AbstractApplicationContext) context).close();
	}

}
