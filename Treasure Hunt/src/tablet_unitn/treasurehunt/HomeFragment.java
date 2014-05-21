package tablet_unitn.treasurehunt;

import tablet_unitn.treasurehunt.R;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		Typeface robotoThin = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-ThinItalic.ttf");
		Typeface roboto = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Black.ttf");

		int numGames = 3, numNewPubGames = 1, numNewPvtGames = 2;
		
		final TextView welcome = (TextView) rootView.findViewById(R.id.home_title);
		welcome.setTypeface(robotoThin);
		welcome.setText(welcome.getText() + "\nDiego");
		
		final TextView infoContinue = (TextView) rootView.findViewById(R.id.infos_continue);
		infoContinue.setTypeface(robotoThin);;
		infoContinue.setText("you're playing " + numGames + " games at the moment");
		
		final TextView infoNewPub = (TextView) rootView.findViewById(R.id.infos_newpub);
		infoNewPub.setTypeface(robotoThin);;
		infoNewPub.setText("There " + ((numNewPubGames > 1) ? "are " : "is ") + numNewPubGames 
				+ " public " + ((numNewPubGames > 1) ? "games" : "game") + " near your locations");
		
		final TextView infoNewPvt = (TextView) rootView.findViewById(R.id.infos_newpvt);
		infoNewPvt.setTypeface(robotoThin);;
		infoNewPvt.setText("You're invited to " + numNewPvtGames + " private " 
				+ ((numNewPvtGames > 1) ? "games" : "game"));
		
		return rootView;
	}
}
