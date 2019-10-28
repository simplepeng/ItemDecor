package example.simple.itemdecor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import example.simple.itemdecor.grid.GridActivity;
import example.simple.itemdecor.linear.LinearActivity;
import example.simple.itemdecor.multitype.MultiTypeActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.item_linear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LinearActivity.class));
            }
        });

        findViewById(R.id.item_grid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GridActivity.class));
            }
        });

        findViewById(R.id.item_multiType).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MultiTypeActivity.class));
            }
        });
    }
}
