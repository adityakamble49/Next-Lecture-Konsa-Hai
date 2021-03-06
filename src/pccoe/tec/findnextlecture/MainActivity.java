package pccoe.tec.findnextlecture;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.view.GravityCompat;

public class MainActivity extends SherlockFragmentActivity
{

	// Declare Variables
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	MenuListAdapter mMenuAdapter;
	String[] title;
	String[] subtitle;
	int[] icon;
	Fragment ftLectureFragment = null;
	Fragment ftAboutFragment = new AboutFragment();
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// Get the view from drawer_main.xml
		setContentView(R.layout.drawer_main);
		
		// Get the Title
		mTitle = mDrawerTitle = getTitle();

		// Generate title
		title = new String[] {"Monday","Tuesday","Wednesday","Thursday","Friday","About App"};

		// Generate subtitle
		subtitle = new String[] {"Monday Lectures","Tuesday Lectures","Wednesday Lectures","Thursday Lectures","Friday Lectures","About Developers"};

		// Generate icon
		icon = new int[] { R.drawable.ic_action_forward,R.drawable.ic_action_forward,R.drawable.ic_action_forward,R.drawable.ic_action_forward,R.drawable.ic_action_forward, R.drawable.ic_action_about,};

		// Locate DrawerLayout in drawer_main.xml
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Locate ListView in drawer_main.xml
		mDrawerList = (ListView) findViewById(R.id.listview_drawer);

		// Set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// Pass string arrays to MenuListAdapter
		mMenuAdapter = new MenuListAdapter(MainActivity.this, title, subtitle,
				icon);

		// Set the MenuListAdapter to the ListView
		mDrawerList.setAdapter(mMenuAdapter);

		// Capture listview menu item click
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// Enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close)
		{

			public void onDrawerClosed(View view)
			{
				// TODO Auto-generated method stub
				super.onDrawerClosed(view);
			}

			public void onDrawerOpened(View drawerView)
			{
				// TODO Auto-generated method stub
				// Set the title on the action when drawer open
				super.onDrawerOpened(drawerView);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null)
		{			
			ftLectureFragment = null;
			LectureFragment.iCustomDay = 0;
			ftLectureFragment =  new LectureFragment();
			LectureFragment.bCustomDay = false;
			FragmentTransaction nft = getSupportFragmentManager().beginTransaction();
			nft.replace(R.id.content_frame, ftLectureFragment);
			nft.commit();
			//selectItem(0);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		if (item.getItemId() == android.R.id.home)
		{

			if (mDrawerLayout.isDrawerOpen(mDrawerList))
			{
				mDrawerLayout.closeDrawer(mDrawerList);
			} else
			{
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}

		return super.onOptionsItemSelected(item);
	}

	// ListView click listener in the navigation drawer
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id)
		{
			selectItem(position);
		}
	}

	private void selectItem(int position)
	{

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		// Locate Position
		switch (position)
		{
		case 0:
				ftLectureFragment = null;
				ftLectureFragment =  new LectureFragment();
				LectureFragment.iCustomDay = 1;
				LectureFragment.bCustomDay = true;
				ft.replace(R.id.content_frame, ftLectureFragment);
		break;
		
		case 1:
				ftLectureFragment = null;
				ftLectureFragment =  new LectureFragment();
				LectureFragment.iCustomDay = 2;
				LectureFragment.bCustomDay = true;
				ft.replace(R.id.content_frame, ftLectureFragment);
		break;
		
		case 2:
				ftLectureFragment = null;
				ftLectureFragment =  new LectureFragment();
				LectureFragment.iCustomDay = 3;
				LectureFragment.bCustomDay = true;
				ft.replace(R.id.content_frame, ftLectureFragment);
		break;
		
		case 3:
				ftLectureFragment = null;
				ftLectureFragment =  new LectureFragment();
				LectureFragment.iCustomDay = 4;
				LectureFragment.bCustomDay = true;
				ft.replace(R.id.content_frame, ftLectureFragment);
		break;
		
		case 4:
				ftLectureFragment = null;
				ftLectureFragment =  new LectureFragment();
				LectureFragment.iCustomDay = 5;
				LectureFragment.bCustomDay = true;
				ft.replace(R.id.content_frame, ftLectureFragment);
		break;
		
		case 5:
				ft.replace(R.id.content_frame, ftAboutFragment);
		break;
		}
		ft.commit();
		mDrawerList.setItemChecked(position, true);

		// Get the title followed by the position
		if(position==5)
		setTitle(title[position]);
		else
			setTitle(R.string.drawer_close);	
		// Close drawer
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void setTitle(CharSequence title)
	{
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}
}