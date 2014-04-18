package com.xuyoubo.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

public class Main extends Activity {
    GameView gameView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new HuarongdaoView(this);
        setContentView(gameView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_level){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("选关")
                    .setItems(gameView.getLevelNames(), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            gameView.selectLevel(which);
                        }
                    });
            // Create the AlertDialog object and return it
            Dialog dlg = builder.create();
            dlg.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
