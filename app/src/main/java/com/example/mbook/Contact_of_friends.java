package com.example.mbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.URLEncoder;

public class Contact_of_friends extends AppCompatActivity {
    ListView lv;
    String[] data;
    int pos;
    LinearLayout lin,mn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_of_friends);
        mn=(LinearLayout)findViewById(R.id.main);
        lin=(LinearLayout)findViewById(R.id.flot);
        lv = (ListView) findViewById(R.id.myfriendcntct);

        mn.removeView(lin);

        /// think about constructor of MyDBFunctions getting context parameter
        MyDBFunctions mf = new MyDBFunctions(getApplicationContext());

        /// receiving database's all datas from method of my_data()
        data = mf.my_data();

        /// lv is list view in myfriendcntct
        /// lview is an another xml containing only text
        /// make an ArrayAdapter of only lview containing data (our string array of database's datas)
        /// Then add this to listview as an Adapter

        lv.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.lview, R.id.mytext, data));
        final MyDBFunctions obj = new MyDBFunctions(getApplicationContext());

        /// By pressing long click on per item in list view
        /// we will go edit and delete activity
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pos=i;
                mn.addView(lin);
                final String rec_fb=obj.fetch_f(pos+1);
                final String rec_gm=obj.fetch_g(pos+1);
                final String rec_cl=obj.fetch_c(pos+1);
                final String rec_wp=obj.fetch_wp(pos+1);
                final String rec_in=obj.fetch_ins(pos+1);

                FloatingActionButton fb,wp,gm,cl,in;

                fb=(FloatingActionButton)findViewById(R.id.fb);
                gm=(FloatingActionButton)findViewById(R.id.gmail);
                wp=(FloatingActionButton)findViewById(R.id.whatsapp);
                cl=(FloatingActionButton)findViewById(R.id.call);
                in=(FloatingActionButton)findViewById(R.id.instragam);

                cl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:"+rec_cl));
                        startActivity(intent);
                    }
                });

                in.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://instagram.com/_u/" + "username"));
                            intent.setPackage("com.instagram.android");
                            startActivity(intent);
                        }
                        catch (android.content.ActivityNotFoundException anfe)
                        {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.instagram.com/" + rec_in)));
                        }
                    }
                });

               wp.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                           String smsNumber = rec_wp;
                           boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
                           if (isWhatsappInstalled) {
                               Intent sendIntent = new Intent("android.intent.action.MAIN");
                               sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                               sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");//phone number without "+" prefix
                               startActivity(sendIntent);
                           } else {
                               Uri uri = Uri.parse("market://details?id=com.whatsapp");
                               Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                               Toast.makeText(Contact_of_friends.this, "WhatsApp not Installed",
                                       Toast.LENGTH_SHORT).show();
                               startActivity(goToMarket);
                           }

                   }
               });

                fb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent ii=new Intent(Contact_of_friends.this,Facebook.class);
                        ii.putExtra("message_key",rec_fb);
                        startActivity(ii);
                        finish();
                    }
                });

                gm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent email = new Intent(Intent.ACTION_SEND);
                        email.putExtra(Intent.EXTRA_EMAIL, new String[]{rec_gm});
                        email.putExtra(Intent.EXTRA_SUBJECT, "this is subject");
                        email.putExtra(Intent.EXTRA_TEXT, "this is message");
//need this to prompts email client only
                        email.setType("message/rfc822");
                        startActivity(Intent.createChooser(email, "Choose an Email client :"));
                    }
                });
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), Edit_delete.class);
                ///By using another Extra string we will send position of current item to another activity
                i.putExtra("MyKEY", position);
                startActivity(i);
                finish();
                return true;
            }
        });

}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

}
