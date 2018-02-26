package tool.pkg;

public class log
{

	 public static void log_error(String errorCode)
	{
		System.out.println(errorCode);
		System.out.println("³ÌÐòÍË³ö");
		System.exit(0);
	}

	public static void log_message(String messageCode)
	{
		System.out.println(messageCode);
	}
}
