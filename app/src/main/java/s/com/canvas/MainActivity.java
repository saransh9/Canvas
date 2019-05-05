package s.com.canvas;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.agsw.FabricView.FabricView;
import com.github.clans.fab.FloatingActionButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton changeColur,undo,redo,colour,inc_size,select,selection_mode;
    FabricView fabricView;
    int color;
    int size=5;
    int selectionMode=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fabricView = findViewById(R.id.faricView);
        undo=findViewById(R.id.undo);
        select=findViewById(R.id.select);
        redo=findViewById(R.id.redo);
        colour=findViewById(R.id.color);
        inc_size=findViewById(R.id.inc_size);
        changeColur=findViewById(R.id.changeColur);
        selection_mode=findViewById(R.id.selection_mode);
        fabricView.setSelectionColor(R.color.colorPrimary);
         final Random rnd = new Random();
          changeColur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabricView.cleanPage();
            }
        });

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabricView.undo();
            }
        });
        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabricView.redo();
            }
        });

        colour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

                fabricView.setColor(color);
            }
        });

        inc_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabricView.setSize(size++);
            }
        });
        inc_size.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                size=5;
                fabricView.setSize(size);
                return false;
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabricView.selectLastDrawn();
            }
        });

        selection_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectionMode=(selectionMode+1)%3;
                fabricView.setInteractionMode(selectionMode);
                String mode;
                if(selectionMode==0){
                    mode="DRAW_MODE";
                }else if(selectionMode==1){
                    mode="SELECT_MODE";
                }else if(selectionMode==2){
                    mode="ROTATE_MODE";
                }else{
                    mode="LOCKED_MODE";
                }
                Toast.makeText(MainActivity.this,mode,Toast.LENGTH_LONG).show();
            }
        });
        fabricView.setScaleRotateListener(new FabricView.ScaleRotateListener() {
            @Override
            public boolean startRotate() {
                return true;
            }

            @Override
            public void endRotate() {

            }
        });

    }
}
