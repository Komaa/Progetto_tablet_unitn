package tablet_unitn.treasurehunt;

import tablet_unitn.treasurehunt.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		final TextView title = (TextView) rootView.findViewById(R.id.home_title);
		title.setText(title.getText()+" User");
		return rootView;
	}
}
