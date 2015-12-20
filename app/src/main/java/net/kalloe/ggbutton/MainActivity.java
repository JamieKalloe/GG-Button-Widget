package net.kalloe.ggbutton;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables
    private MediaPlayer ggSound;
    private ImageButton ggButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView(R.id.ibGG, R.id.banner_ad);
    }

    private void initializeView(int...viewIds) {
        try {
            this.ggButton = (ImageButton) findViewById(viewIds[0]);
            this.ggButton.setOnClickListener(this);
            this.ggSound = MediaPlayer.create(this, R.raw.ggsound);
            AdView adBanner = (AdView) findViewById(viewIds[1]);
//            AdRequest adRequest = new AdRequest.Builder().build();
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                    .addTestDevice("AC98C820A50B4AD8A2106EDE96FB87D4")  // An example device ID
                    .build();
            adBanner.loadAd(adRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    ggSound.start();
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
