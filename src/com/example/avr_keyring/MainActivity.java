package com.example.avr_keyring;

import java.util.Locale;

import classes.MainController;
import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	ActionBar actionBar = null;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding tab. 
		// We can also use ActionBar.Tab#select() to do this if we have a reference to the Tab.
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_add_password_enabled).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_show_passwords_disabled).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_settings_disabled).setTabListener(this));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in the ViewPager.
		int position = tab.getPosition();
		switch (position) {
		case 0:
			tab.setIcon(R.drawable.ic_add_password_enabled); break;
		case 1:
			tab.setIcon(R.drawable.ic_show_passwords_enabled); break;
		case 2:
			tab.setIcon(R.drawable.ic_settings_enabled); break;
		}
		
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		int position = tab.getPosition();
		switch (position) {
		case 0:
			tab.setIcon(R.drawable.ic_add_password_disabled); break;
		case 1:
			tab.setIcon(R.drawable.ic_show_passwords_disabled); break;
		case 2:
			tab.setIcon(R.drawable.ic_settings_disabled); break;
		}
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.

			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = new FragmentAddPassword(); break;
			case 1:
				fragment = new FragmentShowPasswords(); break;
			case 2:
				fragment = new FragmentSettings(); break;
			}
			
			Log.i("TEST", position + "pozitie");
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}
	
	//Menu functions
	public void closeApplication(MenuItem mi) {	
		System.exit(0);
	}
	
	public void showSettings(MenuItem mi) {
		actionBar.selectTab(actionBar.getTabAt(2));
	}
	
	//Save to file
	public void onPause()
    {
		MainController.getInstance().savePasswords();
       
		super.onPause();
    }

	
	public void onStop()
    {
		MainController.getInstance().savePasswords();
       
		super.onStop();
    }

	
	@Override
	public void onDestroy() {
		MainController.getInstance().savePasswords();
		
		super.onDestroy();  
	}
}
