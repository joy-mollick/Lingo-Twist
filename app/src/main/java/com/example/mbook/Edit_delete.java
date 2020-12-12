package com.example.mbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit_delete extends AppCompatActivity
{
    EditText e,e11,e12,e13,e14,e15;
    Button b, d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);

        e = (EditText) findViewById(R.id.contact_text);
        e11=(EditText) findViewById(R.id.fb);
        e12=(EditText) findViewById(R.id.gmail);
        e13=(EditText)findViewById(R.id.identity);
        e14=(EditText)findViewById(R.id.whatsapp);
        e15=(EditText)findViewById(R.id.instragam);

        b = (Button) findViewById(R.id.updatedatabase);
        d = (Button) findViewById(R.id.delete_data);

        /// this will take the position of the item which has been clicked for long time
        /// Otherwise default value of position of the item is 999
        final int rec_pos = getIntent().getIntExtra("MyKEY", 999);

        /// Constructor of MyDBFunctions
        final MyDBFunctions obj = new MyDBFunctions(getApplicationContext());

        /// By fetching data of the current item's position from the MyDBFunctions class, we will set that data on our edit text.
        e.setText(obj.fetch_c(rec_pos+1));
        /// Cursor will on the end of the string.
        e.setSelection(e.getText().length());

        e11.setText(obj.fetch_f(rec_pos+1));
        e11.setSelection(e11.getText().length());

        e12.setText(obj.fetch_g(rec_pos+1));
        e12.setSelection(e12.getText().length());

        e13.setText(obj.fetch_i(rec_pos+1));
        e13.setSelection(e13.getText().length());

        e14.setText(obj.fetch_wp(rec_pos+1));
        e14.setSelection(e14.getText().length());

        e15.setText(obj.fetch_ins(rec_pos+1));
        e15.setSelection(e15.getText().length());



        /// databse starting position starts from 1
        /// So, all time pass parameter by adding extra one
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj.update_birthday((rec_pos+1), e.getText().toString(),e11.getText().toString(),e12.getText().toString(),e13.getText().toString(),e14.getText().toString(),e15.getText().toString());
                Toast.makeText(Edit_delete.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        });


        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj.delete_bday(obj.fetch_c(rec_pos+1),rec_pos+1);
                Toast.makeText(getApplicationContext(), "Deleted Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(), Contact_of_friends.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}