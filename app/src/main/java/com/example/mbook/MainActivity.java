package com.example.mbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7;
    Button b1, b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.name);
        e2 = (EditText) findViewById(R.id.bday);
        e3= (EditText) findViewById(R.id.fb1);
        e4= (EditText) findViewById(R.id.gmail1);
        /// district
        e5=(EditText) findViewById(R.id.identity1);
        e6=(EditText) findViewById(R.id.instra1);
        e7=(EditText) findViewById(R.id.whatsapp1);

        b1 = (Button) findViewById(R.id.save);
        b2 = (Button) findViewById(R.id.show);

        final MyDBFunctions mf = new MyDBFunctions(getApplicationContext());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String _name = e1.getText().toString();
                String _bday = e2.getText().toString();
                String F=e3.getText().toString();
                String G=e4.getText().toString();
                String I=e5.getText().toString();
                String Inst=e6.getText().toString();
                String whp=e7.getText().toString();
                /// By making data as DataTemp class we will add this to addingDataToTable method of our MyDBFunctions class.
                DataTemp dt = new DataTemp(_name,_bday,F,G,I,Inst,whp);

                if(mf.n(_name))
                {
                    e1.setHint("PLease Change Your Name");
                    Toast.makeText(getApplicationContext(),"Same name exists ,please change name",Toast.LENGTH_LONG).show();
                }

                else {
                    exist(_name, _bday, F, G, I);

                    mf.addingDataToTable(dt);

                    Toast.makeText(getApplicationContext(), "Data added successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Contact_of_friends.class));
            }
        });
    }
    public void exist(String n,String c,String f,String g,String i)
    {
         final MyDBFunctions db = new MyDBFunctions(getApplicationContext());
         String s="";
         if(db.n(n))
         {
            s+="This name already exist.";
         }

        if(db.f(f))
        {
            s+="\nThis facebook id already exists.";
        }

        if(db.g(g))
        {
            s+="\nThis gmail id already exits.";
        }

        if(db.c(c))
        {
            s+="\nThis contact already exists.";
        }

        if(db.i(i))
        {
            s+="\nThis institution/district already exists.";
        }
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
    public void onBackPressed()
    {
        finish();
    }
}