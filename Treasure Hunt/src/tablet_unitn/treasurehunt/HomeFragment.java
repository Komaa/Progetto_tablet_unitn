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
		Typeface robotoThin = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Thin.ttf");
		Typeface roboto = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Black.ttf");
		
		int numGames = 3, numNewPubGames = 1, numNewPvtGames = 2;
		
		final TextView welcome = (TextView) rootView.findViewById(R.id.t_wellcome);
		welcome.setTypeface(robotoThin);
		welcome.setText(welcome.getText() + " Mario27");
		
		final TextView infoContinue = (TextView) rootView.findViewById(R.id.t_stats);
		infoContinue.setTypeface(robotoThin);;
		infoContinue.setText("You're playing " + numGames + " games at the moment.\n\n"
				+ "You have complited 23 games!");
		
		final TextView infoNewPub = (TextView) rootView.findViewById(R.id.t_history);
		infoNewPub.setTypeface(robotoThin);
		infoNewPub.setText("Your score is: \n 203 points");
		
		/*infoNewPub.setText("There " + ((numNewPubGames > 1) ? "are " : "is ") + numNewPubGames 
				+ " public " + ((numNewPubGames > 1) ? "games" : "game") + " near your locations\n\n"
						+ "You're invited to " + numNewPvtGames + " private " 
				+ ((numNewPvtGames > 1) ? "games" : "game"));*/
		
		return rootView;
	}
}
