package pankaj.com.circularview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private CircularProgressBar circularSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void renderCircularBar(View view) {
        EditText total = (EditText) findViewById(R.id.total);
        EditText progress = (EditText) findViewById(R.id.progress);

        int totalValue = Integer.parseInt(total.getText().toString().isEmpty() ? "0" : total.getText().toString());
        int progressValue = Integer.parseInt(progress.getText().toString().isEmpty() ? "0" : progress.getText().toString());
        circularSeekBar = findViewById(R.id.circularBar);
        circularSeekBar.setAngle(360 * progressValue / totalValue);
        circularSeekBar.invalidate();
    }
}
