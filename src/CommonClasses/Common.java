package CommonClasses;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Common
{
	public static String getVerificationCode()
	{
		return "" + new BigInteger(35, new Random()).toString(32);
	}
	
	public static List<String> getFloorList()
	{
		List<String> list=new ArrayList<>();
		list.add("GF");
		for(int i=1; i<=30;i++)
			list.add(""+i);
		
		return list;
	}

	public static List<String> getMonthList()
	{
		List<String> list=new ArrayList<>();
		list.add("01");
		list.add("02");
		list.add("03");
		list.add("04");
		list.add("05");
		list.add("06");
		list.add("07");
		list.add("08");
		list.add("09");
		list.add("10");
		list.add("11");
		list.add("12");
		
		return list;
	}

	public static List<String> getYearList()
	{
		List<String> list=new ArrayList<>();
		list.add("2017");
		list.add("2018");
		list.add("2019");
		list.add("2020");
		list.add("2021");
		list.add("2022");
		
		return list;
	}



}
