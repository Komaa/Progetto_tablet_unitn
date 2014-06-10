package tablet_unitn.treasurehunt;


import java.util.List;
import java.util.concurrent.ExecutionException;

import tablet_unitn.adapter.TabsPagerAdapter;
import tablet_unitn.dbmanager.GetUserInfo_db;
import tablet_unitn.dbmanager.UserDAO_DB_impl;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {
	public static User user = null;
	List<User> users;
    UserDAO_DB_impl dao;
    
	private static Context context;
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "Home", "Continue", "New Game" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MainActivity.context = getApplicationContext();
		
		// Get user informations from andoird DB
		String usrName = (String) this.getIntent().getExtras().get(".usrName");
		dao = new UserDAO_DB_impl(); 
        dao.open(); 
        users  = dao.getAllUser(); 
        int q=0;
        for (int i = 0; i < users.size(); i++) {
        	if(users.get(i).getName().equals(usrName)){
        		MainActivity.user=users.get(i);
        		q++;
        	}
    	}

//        user = dao.getInfo(ID);
        
//      get user information from server
	    GetUserInfo_db use = new GetUserInfo_db();
	    try {
			user=use.execute(user).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}	
	    
//	    aggiorno android DB
	    user = dao.updateUser(user);
	    		
		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		//actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
	
	public static Context getAppContext(){
 		return MainActivity.context;
 	} 
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	 @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
         
        switch (item.getItemId())
        {
        case R.id.action_logout:
        	dao.deleteUser(user);
        	logout();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }  
	
	public void logout() {
		dao.deleteUser(user);	
		Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    startActivity(intent);
	    finish();
	}
	 
	@Override 
	protected void onResume() { 
		dao.open(); 
		super.onResume(); 
	} 
	 
	@Override 
	protected void onPause() { 
		dao.close(); 
		super.onPause(); 
	} 
}
