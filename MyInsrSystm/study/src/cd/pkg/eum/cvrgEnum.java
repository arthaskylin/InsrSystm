package cd.pkg.eum;

public class cvrgEnum
{

	public enum CvrgStatus {
		pending, inforce, issued, notissued
	}

	public enum CvrgStatusReason {
		notissuer, active, surrender, payment, claim
	}

	public enum Suspend {
		none, all
	}

	public enum SuspendReason {
		na, payment, claim, chang
	}

	public enum bllngFreq {
		year1, // 1Äê
		year3, //
		month1, //
		w,// õ»½»
	}
	public enum timeUnit{
		year,month,day,
	}
}
