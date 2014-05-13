package tablet_unitn.adapter;

import tablet_unitn.treasurehunt.ContinueGameFragment;
import tablet_unitn.treasurehunt.NewGameFragment;
import tablet_unitn.treasurehunt.HomeFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Home fragment activity
			return new HomeFragment();
		case 1:
			// Games fragment activity
			return new ContinueGameFragment();
		case 2:
			// NewGame fragment activity
			return new NewGameFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
