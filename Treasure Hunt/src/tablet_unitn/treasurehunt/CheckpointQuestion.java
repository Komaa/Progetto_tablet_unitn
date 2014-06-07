package tablet_unitn.treasurehunt;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CheckpointQuestion extends Activity{
	
	Typeface robotoThin, robotoCond, roboto, robotoReg;
	int indexRightAnswer = 0;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.checkpointquestion);
        
        robotoReg = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        roboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Black.ttf");
        robotoThin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        robotoCond = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Condensed.ttf");
        
        TextView question = (TextView) findViewById(R.id.question);
        final Button answer1 = (Button) findViewById(R.id.answer1);
        final Button answer2 = (Button) findViewById(R.id.answer2);
        final Button answer3 = (Button) findViewById(R.id.answer3);
        final Button answer4 = (Button) findViewById(R.id.answer4);
        
        question.setTypeface(roboto);
        answer1.setTypeface(robotoReg); answer2.setTypeface(robotoReg);
        answer3.setTypeface(robotoReg); answer4.setTypeface(robotoReg);
        
        //e' solo un getExtras di prova, bisogna ancora sistemare tutto
        question.setText((String) this.getIntent().getExtras().get(".question"));
        answer1.setText((String) this.getIntent().getExtras().get(".answer1"));
        answer2.setText((String) this.getIntent().getExtras().get(".answer2"));
        answer3.setText((String) this.getIntent().getExtras().get(".answer3"));
        answer4.setText((String) this.getIntent().getExtras().get(".answer4"));
        indexRightAnswer = (Integer) this.getIntent().getExtras().get(".rightAnswer");
        
        answer1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(indexRightAnswer == 1){
					answer1.setBackgroundResource(R.drawable.rightanswerbutton);
					//ritorna alla mappa con i dati aggiornati (AP)
				} else {
					if(indexRightAnswer == 2) answer2.setBackgroundResource(R.drawable.rightanswerbutton);
					else if(indexRightAnswer == 3) answer3.setBackgroundResource(R.drawable.rightanswerbutton);
					else if(indexRightAnswer == 4) answer4.setBackgroundResource(R.drawable.rightanswerbutton);
				}
			}
		});
        
        answer2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(indexRightAnswer == 2){
					answer2.setBackgroundResource(R.drawable.rightanswerbutton);
					//ritorna alla mappa con i dati aggiornati (AP)
				} else {
					if(indexRightAnswer == 1) answer1.setBackgroundResource(R.drawable.rightanswerbutton);
					else if(indexRightAnswer == 3) answer3.setBackgroundResource(R.drawable.rightanswerbutton);
					else if(indexRightAnswer == 4) answer4.setBackgroundResource(R.drawable.rightanswerbutton);
				}
			}
		});
        
        answer3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(indexRightAnswer == 3){
					answer3.setBackgroundResource(R.drawable.rightanswerbutton);
					//ritorna alla mappa con i dati aggiornati (AP)
				} else {
					if(indexRightAnswer == 1) answer1.setBackgroundResource(R.drawable.rightanswerbutton);
					else if(indexRightAnswer == 2) answer2.setBackgroundResource(R.drawable.rightanswerbutton);
					else if(indexRightAnswer == 4) answer4.setBackgroundResource(R.drawable.rightanswerbutton);
				}
			}
		});
        
        answer4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(indexRightAnswer == 4){
					answer4.setBackgroundResource(R.drawable.rightanswerbutton);
					//ritorna alla mappa con i dati aggiornati (AP)
				} else {
					if(indexRightAnswer == 1) answer1.setBackgroundResource(R.drawable.rightanswerbutton);
					else if(indexRightAnswer == 2) answer2.setBackgroundResource(R.drawable.rightanswerbutton);
					else if(indexRightAnswer == 3) answer3.setBackgroundResource(R.drawable.rightanswerbutton);
				}
			}
		});
	}

}
