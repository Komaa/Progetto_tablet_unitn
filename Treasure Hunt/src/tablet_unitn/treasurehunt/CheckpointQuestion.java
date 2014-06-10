package tablet_unitn.treasurehunt;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class CheckpointQuestion extends Activity{
	
	Typeface robotoThin, robotoCond, roboto, robotoReg;
	Boolean isCorrect1, isCorrect2, isCorrect3, isCorrect4; 
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
        isCorrect1 = (Boolean) this.getIntent().getExtras().get(".isCorrect1");
        
        answer2.setText((String) this.getIntent().getExtras().get(".answer2"));
        isCorrect2 = (Boolean) this.getIntent().getExtras().get(".isCorrect2");
        
        answer3.setText((String) this.getIntent().getExtras().get(".answer3"));
        isCorrect3 = (Boolean) this.getIntent().getExtras().get(".isCorrect3");
        
        answer4.setText((String) this.getIntent().getExtras().get(".answer4"));
        isCorrect4 = (Boolean) this.getIntent().getExtras().get(".isCorrect4");
        
        if(!isCorrect1&&!isCorrect2&&!isCorrect3&&!isCorrect4) //se tutti sono settati a false
        	isCorrect1=true; //serve per far procedere comunque l'utente anche se la risposta può essere sbagliata
        
        answer1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isCorrect1){
					answer1.setBackgroundResource(R.drawable.rightanswerbutton);
					Intent returnIntent = new Intent();
					returnIntent.putExtra("result",1);
					setResult(RESULT_OK,returnIntent);
					finish();
				} else {
					answer1.setBackgroundResource(R.drawable.wronganswerbutton);
					}
			}
		});
        
        answer2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isCorrect2){
					answer2.setBackgroundResource(R.drawable.rightanswerbutton);
					Intent returnIntent = new Intent();
					returnIntent.putExtra("result",1);
					setResult(RESULT_OK,returnIntent);
					finish();
				} else {
					answer2.setBackgroundResource(R.drawable.wronganswerbutton);
						}
			}
		});
        
        answer3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isCorrect3){
					answer3.setBackgroundResource(R.drawable.rightanswerbutton);
					Intent returnIntent = new Intent();
					returnIntent.putExtra("result",1);
					setResult(RESULT_OK,returnIntent);
					finish();
				} else {
					answer3.setBackgroundResource(R.drawable.wronganswerbutton);
					}
			}
		});
        
        answer4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isCorrect4){
					answer4.setBackgroundResource(R.drawable.rightanswerbutton);
					Intent returnIntent = new Intent();
					returnIntent.putExtra("result",1);
					setResult(RESULT_OK,returnIntent);
					finish();
				} else {
					answer4.setBackgroundResource(R.drawable.wronganswerbutton);
					}
			}
		});
	}
}