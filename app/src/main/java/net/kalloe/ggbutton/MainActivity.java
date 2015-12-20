package net.kalloe.ggbutton;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
            AdRequest adRequest = new AdRequest.Builder().build();
            adBanner.loadAd(adRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
