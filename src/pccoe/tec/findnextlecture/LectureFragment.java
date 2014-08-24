package pccoe.tec.findnextlecture;

import java.util.Calendar;
import java.util.Date;

import com.actionbarsherlock.app.SherlockFragment;

import android.R.bool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
 
public class LectureFragment extends SherlockFragment implements OnClickListener
{
	
	//Variables
		String sMonday[] = {"1. ESDL 207\n3. ESDL 207","1. ESDL 207\n3. ESDL 207","OSD 204","TOC 204","1.OSD 205\n2.DMSA 205\n3.DACN 205\n4.FACA 212","1.OSD 205\n2.DMSA 205\n3.DACN 205\n4.FACA 212"};
		String sTuesday[] = {"FACA 204 ","TOC 204","OSD 203 ","DCWSN 203","1.DMSA 205\n2.DACN 205\n3.FACA 212\n4.OSD 205","1.DMSA 205\n2.DACN 205\n3.FACA 212\n4.OSD 205"};
		String sWednesday[] = {"DCWSN 204","DMSA 204 ","OSD 203","TOC 203","1.DACN 205\n 2.FACA 212\n 3.OSD 205\n 4.DMSA 205","1.DACN 205\n 2.FACA 212\n 3.OSD 205\n 4.DMSA 205"};
		String sThursday[] = {"2. ESDL 207\n4. ESDL 207","2. ESDL 207\n4. ESDL 207","DMSA 203","DCWSN 203","TOC 203","FACA 203"};
		String sFriday[] = {"1.FACA\n2.OSD\n3.DMSA\n4.DACN","1.FACA\n2.OSD\n3.DMSA\n4.DACN","DMSA 203","DCWSN 203","FACA 203","OSD 203"};		
		
		String sWeekdays[] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		String sTime[] = {"11:10 - 12:10", "12:10 - 1:10", "1:50 - 2:50", "2:50 - 3:50", "3:50 - 4:50", "4:50 - 5:50"};
		
		Calendar cal = Calendar.getInstance();
		Date dtDate = cal.getTime();
		
		//Two Index integers
		int iCurrentDay = -99; //0 to 6 represents Sunday to Monday
		int iCurrentLecture = -99;//0 to 5 represents lectures 1 to 6
		
		//Custom Day
		public static int iCustomDay;
		public static boolean bCustomDay;
		
		//View Components
		TextView tvLecture,tvTime,tvDayDate;
		Button bPrev,bNext,bReset;
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
    	View rootView = inflater.inflate(R.layout.fragment1, container, false);
    	
		//Set day and date
		tvDayDate = (TextView) rootView.findViewById(R.id.vTVDayDate);
		tvDayDate.setText(getWeekday(iCustomDay));
		
		//Set current lecture
		tvLecture = (TextView) rootView.findViewById(R.id.vTVLecture);
		tvLecture.setText(sGetLecture());
		
		//Set time range
		tvTime = (TextView) rootView.findViewById(R.id.vTVTime);
		tvTime.setText(sGetTime());
		
		//Initialize the buttons
		bPrev = (Button) rootView.findViewById(R.id.vBTPrev);
		bNext = (Button) rootView.findViewById(R.id.vBTNext);
		bReset = (Button) rootView.findViewById(R.id.vBTReset);
		bPrev.setOnClickListener(this);
		bNext.setOnClickListener(this);
		bReset.setOnClickListener(this);
		
        return rootView;
    }
    
    // My App Functions
    //################################ SET DAY ON TOP ########################################
    String getWeekday(int iWeekDayNumber)
    {
    	String sD = "Today";
    	switch(iWeekDayNumber)
    	{
    		case 0: int iDate = dtDate.getDay();
    				sD = sWeekdays[iDate];
    									break;
    		case 1: sD = "Monday";		break;
    		case 2: sD = "Tuesday";		break;
    		case 3: sD = "Wednesday";	break;
    		case 4: sD = "Thursday";	break;
    		case 5: sD = "Friday";		break;
    		case 6: sD = "Saturday";	break;
    		case 7: sD = "Sunday";		break;
    	}
    	return sD;
    }
    //############################### SET LECTURE IN MIDDLE ##################################
	@SuppressWarnings("deprecation")
	String sGetLecture()
	{
		String sLecture = "";		
		int iSelectionDay = dtDate.getDay();
		
		if(bCustomDay)
		{
			iSelectionDay = iCustomDay;
		}
		
		switch(iSelectionDay)
		{
			case 0:		//Sunday
				//tvDayDate.setText("SCgotSunday");
					iCurrentDay = 0;
					sLecture = GetCurrentLecture(sMonday);
			break;
			
			case 1: 	//Monday
				//tvDayDate.setText("SCgotMonday");
					iCurrentDay = 1;
					sLecture = GetCurrentLecture(sMonday);
			break;
			
			case 2:		//Tuesday
				//tvDayDate.setText("SCgotTuesday");
					iCurrentDay = 2;
					sLecture = GetCurrentLecture(sTuesday);
			break;
			
			case 3:		//Wednesday
				//tvDayDate.setText("SCgotWednesday");
					iCurrentDay = 3;
					sLecture = GetCurrentLecture(sWednesday);
			break;
			
			case 4:		//Thursday
				//tvDayDate.setText("SCgotThursday");
					iCurrentDay = 4;
					sLecture = GetCurrentLecture(sThursday);
			break;
				
			case 5:		//Friday
				//tvDayDate.setText("SCgotFriday");
					iCurrentDay = 5;
					sLecture = GetCurrentLecture(sFriday);
			break;
			
			case 6:		//Saturday
				//tvDayDate.setText("SCgotSaturday");
					iCurrentDay = 6;
					sLecture = GetCurrentLecture(sMonday);
			break;
		}
		
		return sLecture;
	}
	
	String GetCurrentLecture(String[] sToday)
	{
		String sLecture ="";
		int iHours24 = dtDate.getHours();
		int iMinutes24 = dtDate.getMinutes();
		boolean bLectureNotFound=true;
		int iTime = iHours24*100+iMinutes24;
		
		//If day is Saturday or Sunday
		if(iCurrentDay==0 || iCurrentDay==6){
			sLecture = ":)";
			iCurrentLecture=-1;
			return sLecture;
		}
		
		//Toast.makeText(getApplicationContext(), String.valueOf(iHours24)+":"+String.valueOf(iMinutes24), Toast.LENGTH_SHORT).show();
		if(iTime<=1750 && iTime >1650){ sLecture = sToday[5]; iCurrentLecture = 5; return sLecture;}
		if(iTime<=1650 && iTime >1550){ sLecture = sToday[4]; iCurrentLecture = 4; return sLecture;}
		if(iTime<=1550 && iTime >1450){ sLecture = sToday[3]; iCurrentLecture = 3; return sLecture;}
		if(iTime<=1450 && iTime >1310){ sLecture = sToday[2]; iCurrentLecture = 2; return sLecture;}
		if(iTime<=1310 && iTime >1210){ sLecture = sToday[1]; iCurrentLecture = 1; return sLecture;}
		if(iTime<=1210 && iTime >1110){ sLecture = sToday[0]; iCurrentLecture = 0; return sLecture;}
		
		if(iHours24 < 11)  				//Less than 11:00
		{
			return "Too Early";
		}
		if(iHours24 == 11 && iMinutes24<9)	//Less than 11:10

		{
			return "Few Minutes";
		}
		
		if(iHours24 == 17 && iMinutes24 > 50)	//More than 5:50
		{
			return "School's out";
		}
		if(iHours24 > 18)					//Less than 6:00
		{
			return "Too Late";
		}
		
		return sLecture;
	}
	
	//############################# GET TIME ON BOTTOM #############################################
	String sGetTime()
	{
		String sLecture = "";
		int iHours24 = dtDate.getHours();
		int iMinutes24 = dtDate.getMinutes();
		int iTime = iHours24*100+iMinutes24;
		
		//If day is Saturday or Sunday
		if(iCurrentDay==0 || iCurrentDay==6){
			sLecture = "its weekend";
			iCurrentLecture=-1;
			return sLecture;
		}

		if(iTime<=1750 && iTime >1650){ sLecture = sTime[5]; return sLecture;}
		if(iTime<=1650 && iTime >1550){ sLecture = sTime[4]; return sLecture;}
		if(iTime<=1550 && iTime >1450){ sLecture = sTime[3]; return sLecture;}
		if(iTime<=1450 && iTime >1310){ sLecture = sTime[2]; return sLecture;}
		if(iTime<=1310 && iTime >1210){ sLecture = sTime[1]; return sLecture;}
		if(iTime<=1210 && iTime >1110){ sLecture = sTime[0]; return sLecture;}
		
		if(iHours24 < 11)  				//Less than 11:00
		{	iCurrentLecture=-1;
			return "Lecture not started yet";
		}
		if(iHours24 == 11 && iMinutes24<9)	//Less than 11:10

		{	iCurrentLecture=-1;
			return "to lecture";
		}
		
		if(iHours24 == 17 && iMinutes24 > 50)	//More than 5:50
		{
			iCurrentLecture=-1;
			return "Party time!!!";
		}
		if(iHours24 > 18)					//Less than 6:00
		{
			iCurrentLecture=-1;
			return "Come back later";
		}
		return sLecture;
		
	}
	
    @Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.vBTNext:
			if(iCurrentDay==1){sCustomLect(sMonday,++iCurrentLecture);}
			else if(iCurrentDay==2){sCustomLect(sTuesday,++iCurrentLecture);}
			else if(iCurrentDay==3){sCustomLect(sWednesday,++iCurrentLecture);}
			else if(iCurrentDay==4){sCustomLect(sThursday,++iCurrentLecture);}
			else if(iCurrentDay==5){sCustomLect(sFriday,++iCurrentLecture);}
			else if(iCurrentDay==6){sCustomLect(sMonday,++iCurrentLecture);}
			else if(iCurrentDay==0){sCustomLect(sMonday,++iCurrentLecture);}
		break;
		
		case R.id.vBTPrev:
			if(iCurrentDay==1){sCustomLect(sMonday,--iCurrentLecture);}
			else if(iCurrentDay==2){sCustomLect(sTuesday,--iCurrentLecture);}
			else if(iCurrentDay==3){sCustomLect(sWednesday,--iCurrentLecture);}
			else if(iCurrentDay==4){sCustomLect(sThursday,--iCurrentLecture);}
			else if(iCurrentDay==5){sCustomLect(sFriday,--iCurrentLecture);}
			else if(iCurrentDay==6){sCustomLect(sMonday,--iCurrentLecture);}
			else if(iCurrentDay==0){sCustomLect(sMonday,--iCurrentLecture);}
		break;
		case R.id.vBTReset:
			iCustomDay=0;
			bCustomDay=false;
			tvDayDate.setText(getWeekday(0));
			tvLecture.setText(sGetLecture());
			tvTime.setText(sGetTime());
			break;
		}
	}
	
	private void sCustomLect(String[] sDay, int iLecture) {
	if(iCurrentLecture<=-1){iCurrentLecture=5;}		//Lower limit
		iCurrentLecture%=6;							//Upper limit
		if(iCurrentDay==0 || iCurrentDay==6)
		{tvDayDate.setText("Monday");}
		tvLecture.setText(sDay[iCurrentLecture]);
		tvTime.setText(sTime[iCurrentLecture]);
	}
}