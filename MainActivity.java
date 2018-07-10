package com.example.sankesh.firerockpaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference gameRef = rootRef.child("game");
    //Get UI elements
    TextView textView;
    Button rock, paper, scissor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //rootRef.child("Users").child("01").child("Email").setValue("some@cool.com");
        //rootRef.child("Users").child("01").child("Name").setValue("sam");
        textView = (TextView)findViewById(R.id.textview);
        rock = (Button)findViewById(R.id.rock);
        paper = (Button)findViewById(R.id.paper);
        scissor = (Button)findViewById(R.id.scissor);
        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRef.setValue("rock");

            }
        });
        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRef.setValue("paper");

            }
        });
        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRef.setValue("paper");

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue().toString();
                textView.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("TAG","Something is missing here");
            }
        });
    }
}
