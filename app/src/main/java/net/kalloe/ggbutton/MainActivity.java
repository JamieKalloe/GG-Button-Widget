package net.kalloe.ggbutton;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables
    private MediaPlayer ggSound;
    private ImageButton ggButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView(R.id.ibGG);
    }

    private void initializeView(int...viewIds) {
        this.ggButton = (ImageButton) findViewById(viewIds[0]);
        this.ggButton.setOnClickListener(this);
        this.ggSound = MediaPlayer.create(this, R.raw.ggsound);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibGG:
                try {
                    ggButton.setEnabled(false);
                    ggSound.start();
                    while(ggSound.isPlaying())
                        ggButton.setImageDrawable(getResources().getDrawable(R.drawable.ggbuttonpressed));
                    ggButton.setImageDrawable(getResources().getDrawable(R.drawable.ggbuttons));
                    ggButton.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to claim victory", Toast.LENGTH_LONG).show();
                }
                break;

            default:
                break;
        }
    }
}
