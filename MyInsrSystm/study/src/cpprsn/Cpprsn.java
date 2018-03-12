package cpprsn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Cpprsn
{
	private String name;
	private String idntfr;
	private Calendar birthDate;
	private int age;
	private String sex;// m为男，f为女
	private String occ_code;
	// private String type; // 1：投保人，2:被保人。只是在新保创建的时候使用，不存入数据库
	public Cpprsn() {
	}
	public static Cpprsn createPrsn(Map message)
	{
		Cpprsn newPrsn = new Cpprsn();
		newPrsn.processInfo(message);
		return newPrsn;
	}

	private void processInfo(Map message)
	{
		// TODO Auto-generated method stub

	}

	public String getBirthDate()
	{
		java.util.Date date = birthDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birth = sdf.format(date);
		return birth;

	}

	public void setBirthDate(int year, int month, int day)
	{
		Calendar currentDay = Calendar.getInstance();
		currentDay.set(year, month, day);
		this.birthDate = currentDay;
	}

	public void setBirthDate(String birthday)
	{
		// 输入为yy-mm-dd格式
		SimpleDateFormat String2date = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date dateParse = String2date.parse(birthday);
			Calendar current = Calendar.getInstance();
			current.setTime(dateParse);
			this.birthDate = current;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	};

	public int getAge()
	{
		Calendar currentDay = Calendar.getInstance();
		// long
		// subTime=currentDay.getTime().getTime()-this.birthDate.getTime().getTime();
		// age=(int) (subTime/(1000*360*24*365))
		int year = currentDay.get(Calendar.YEAR);
		int biryear = this.birthDate.get(Calendar.YEAR);
		if (this.birthDate.after(currentDay)) {
			age = 0;
		} else {
			age = currentDay.get(Calendar.YEAR) - this.birthDate.get(Calendar.YEAR);
			if (currentDay.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
				age--;
			}
		}
		return age;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		if (sex.equals("f") || sex.equals("m")) {
			this.sex = sex;
		}
	}

	public String getIdntfr()
	{
		return idntfr;
	}

	public void setIdntfr(String idntfr)
	{
		varify_idntfr(idntfr);
		this.idntfr = idntfr;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getOcc_code()
	{
		return occ_code;
	}

	public void setOcc_code(String occ_code)
	{
		this.occ_code = occ_code;
	}

	protected void varify_idntfr(String idntfr)
	{
		/*
		 * if (idntfr.length() != 18) { System.out.println("证件号长度");
		 */
		// tool.pkg.log.log_error("证件号长度不够");
		// }
	}

}
