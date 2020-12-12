package com.example.mbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDBFunctions extends SQLiteOpenHelper
{
    ///naming my databse store's name DATABSE_NAME
    ///naming my database's table name

    /// naming primary (identity) key by which we can map that value. TAB_ID
    /// naming another column say TAB_NAME
    /// naming second column say TAB_DAYS

    private static final String DATABSE_NAME = "mydb";
    private static final String TABLE_NAME = "mytab";

    private static final String TAB_ID = "id";
    private static final String TAB_NAME = "name";
    private static final String TAB_DAYS = "days";

    private static final String TAB_FB = "fb";
    private static final String TAB_GM = "Gmail";
    private static final String TAB_Identity = "identity";
    private static final String TAB_WP="whatsapp";
    private static final String TAB_IN="Instragam";



    /// constructor of MyDBFunctions
    MyDBFunctions(Context c)
    {
        /// it is invoking
        super(c, DATABSE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        /// SQL -> structured Query Language
        String s = "CREATE TABLE "+TABLE_NAME+" ("+TAB_ID+" INTEGER PRIMARY KEY, "+TAB_NAME+" TEXT, "+TAB_DAYS+" TEXT, "+TAB_FB+" TEXT, "+TAB_GM+" TEXT, "+TAB_Identity+" TEXT,"+TAB_IN+" TEXT, "+TAB_WP+" TEXT)";
        /// making a structure of containing a table where TAB_ID is primary key , TAB_NAME and TAB_DAYS are columns
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    // ---- ---- adding data to database --- -----

    void addingDataToTable(DataTemp dt){

        /// it takes writable access
        SQLiteDatabase sqd  = this.getWritableDatabase();

        /// sqd instance  can take content values so make content of column (TAB_NAME and TAB_DAYS)
        ContentValues cv = new ContentValues();
        cv.put(TAB_NAME, dt.getName());
        cv.put(TAB_DAYS, dt.getDay());
        cv.put(TAB_FB,dt.getFb());
        cv.put(TAB_GM,dt.getGm());
        cv.put(TAB_Identity,dt.getIdentity());
        cv.put(TAB_IN,dt.getInstragam());
        cv.put(TAB_WP,dt.getwhatsapp());
        /// insert content values in that table
        sqd.insert(TABLE_NAME, null, cv);
        sqd.close();
    }


    // --- ---- showing data ------ ----

    String[] my_data() {

        /// Readable access from database
        SQLiteDatabase sq = this.getReadableDatabase();

        /// select all from our table
        String q = "SELECT * FROM "+TABLE_NAME;

        /// cursor is one kind of iterator by this q sql.
        Cursor c = sq.rawQuery(q, null);

        /// we will fill up recvied_data array with data from
        String[] recvied_data = new String[c.getCount()];

        /// starting from first
        c.moveToFirst();

        if(c.moveToFirst()){
            int counter = 0 ;
            do {
                recvied_data[counter] = c.getString(c.getColumnIndex(TAB_NAME+"")) +"\nContact: "+
                        c.getString(c.getColumnIndex(TAB_DAYS+""))+"\nFb: "+c.getString(c.getColumnIndex(TAB_FB+""))+"\nGmail: "+c.getString(c.getColumnIndex(TAB_GM+""))+"\nDistrict: "+c.getString(c.getColumnIndex(TAB_Identity+""))+"\nWhatsapp: "+c.getString(c.getColumnIndex(TAB_WP+""))+"\nInstragam: "+c.getString(c.getColumnIndex(TAB_IN+""));
                counter = counter+1;

            } while(c.moveToNext());

        }
        /// return array of data
        return recvied_data;
    }

    String fetch_nm(int id)
    {
        SQLiteDatabase sq = this.getReadableDatabase();
        /// SQL-(Structured Query Language)
        String q = "SELECT "+TAB_NAME+" FROM "+TABLE_NAME+" WHERE "+TAB_ID+" = "+id;
        ///Taking only cursor of the row of the TAB_ID which reflex the position of on click item.
        Cursor c = sq.rawQuery(q, null);
        String s = "";
        c.moveToFirst();
        if(c.moveToFirst()) {
            s = c.getString(c.getColumnIndex(TAB_NAME+""));
        }
        return s;
    }

    String fetch_ins(int id)
    {
        SQLiteDatabase sq = this.getReadableDatabase();
        /// SQL-(Structured Query Language)
        String q = "SELECT "+TAB_IN+" FROM "+TABLE_NAME+" WHERE "+TAB_ID+" = "+id;
        ///Taking only cursor of the row of the TAB_ID which reflex the position of on click item.
        Cursor c = sq.rawQuery(q, null);
        String s = "";
        c.moveToFirst();
        if(c.moveToFirst()) {
            s = c.getString(c.getColumnIndex(TAB_IN+""));
        }
        return s;
    }

    String fetch_wp(int id)
    {
        SQLiteDatabase sq = this.getReadableDatabase();
        /// SQL-(Structured Query Language)
        String q = "SELECT "+TAB_WP+" FROM "+TABLE_NAME+" WHERE "+TAB_ID+" = "+id;
        ///Taking only cursor of the row of the TAB_ID which reflex the position of on click item.
        Cursor c = sq.rawQuery(q, null);
        String s = "";
        c.moveToFirst();
        if(c.moveToFirst()) {
            s = c.getString(c.getColumnIndex(TAB_WP+""));
        }
        return s;
    }

    String fetch_c(int id)
    {
        SQLiteDatabase sq = this.getReadableDatabase();
        /// SQL-(Structured Query Language)
        String q = "SELECT "+TAB_DAYS+" FROM "+TABLE_NAME+" WHERE "+TAB_ID+" = "+id;
        ///Taking only cursor of the row of the TAB_ID which reflex the position of on click item.
        Cursor c = sq.rawQuery(q, null);
        String s = "";
        c.moveToFirst();
        if(c.moveToFirst()) {
            s = c.getString(c.getColumnIndex(TAB_DAYS+""));
        }
        return s;
    }

    String fetch_f(int id)
    {
        SQLiteDatabase sq = this.getReadableDatabase();
        /// SQL-(Structured Query Language)
        String q = "SELECT "+TAB_FB+" FROM "+TABLE_NAME+" WHERE "+TAB_ID+" = "+id;
        ///Taking only cursor of the row of the TAB_ID which reflex the position of on click item.
        Cursor c = sq.rawQuery(q, null);
        String s = "";
        c.moveToFirst();
        if(c.moveToFirst()) {
            s = c.getString(c.getColumnIndex(TAB_FB+""));
        }
        return s;
    }

    String fetch_g(int id)
    {
        SQLiteDatabase sq = this.getReadableDatabase();
        /// SQL-(Structured Query Language)
        String q = "SELECT "+TAB_GM+" FROM "+TABLE_NAME+" WHERE "+TAB_ID+" = "+id;
        ///Taking only cursor of the row of the TAB_ID which reflex the position of on click item.
        Cursor c = sq.rawQuery(q, null);
        String s = "";
        c.moveToFirst();
        if(c.moveToFirst()) {
            s = c.getString(c.getColumnIndex(TAB_GM+""));
        }
        return s;
    }

    String fetch_i(int id)
    {
        SQLiteDatabase sq = this.getReadableDatabase();
        /// SQL-(Structured Query Language)
        String q = "SELECT "+TAB_Identity+" FROM "+TABLE_NAME+" WHERE "+TAB_ID+" = "+id;
        ///Taking only cursor of the row of the TAB_ID which reflex the position of on click item.
        Cursor c = sq.rawQuery(q, null);
        String s = "";
        c.moveToFirst();
        if(c.moveToFirst()) {
            s = c.getString(c.getColumnIndex(TAB_Identity+""));
        }
        return s;
    }

    int update_birthday(int id, String bday,String F,String G,String I,String wp,String ins)
    {
        SQLiteDatabase sq = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TAB_DAYS, bday);
        cv.put(TAB_FB,F);
        cv.put(TAB_GM,G);
        cv.put(TAB_Identity,I);
        cv.put(TAB_WP,wp);
        cv.put(TAB_IN,ins);
        /// finding TAB_ID of position(id) and updated by cv
        return sq.update(TABLE_NAME, cv, TAB_ID+" = ? ", new String[]{id+""});
    }

    int delete_bday(String bday,int id)
    {
        SQLiteDatabase s = this.getWritableDatabase();
        return s.delete(TABLE_NAME, TAB_DAYS+" = ?", new String[] {bday});
        ///return s.delete(TABLE_NAME, TAB_ID+" = ?", new String[] {id+""});
    }

    boolean n(String nm)
    {
        /// Readable access from database
        SQLiteDatabase sq = this.getReadableDatabase();
        String s;
        /// select all from our table
        String q = "SELECT * FROM "+TABLE_NAME;

        /// cursor is one kind of iterator by this q sql.
        Cursor c = sq.rawQuery(q, null);
        /// starting from first
        c.moveToFirst();
        if(c.moveToFirst())
        {
            s=c.getString(c.getColumnIndex(TAB_NAME+""));
            if(s.equals(nm)==true)
            {
                return true;
            }
            c.moveToNext();
        }
        return false;
    }

    boolean c(String cntact)
    {
        /// Readable access from database
        SQLiteDatabase sq = this.getReadableDatabase();
        String s;
        /// select all from our table
        String q = "SELECT * FROM "+TABLE_NAME;

        /// cursor is one kind of iterator by this q sql.
        Cursor c = sq.rawQuery(q, null);
        /// starting from first
        c.moveToFirst();
        if(c.moveToFirst())
        {
            s=c.getString(c.getColumnIndex(TAB_DAYS+""));
            if(s.equals(cntact)==true)
            {
                return true;
            }
            c.moveToNext();
        }
        return false;
    }

    boolean f(String fb)
    {
        /// Readable access from database
        SQLiteDatabase sq = this.getReadableDatabase();
        String s;
        /// select all from our table
        String q = "SELECT * FROM "+TABLE_NAME;

        /// cursor is one kind of iterator by this q sql.
        Cursor c = sq.rawQuery(q, null);
        /// starting from first
        c.moveToFirst();
        if(c.moveToFirst())
        {
            s=c.getString(c.getColumnIndex(TAB_FB+""));
            if(s.equals(fb)==true)
            {
                return true;
            }
            c.moveToNext();
        }
        return false;
    }

    boolean g(String gm)
    {
        /// Readable access from database
        SQLiteDatabase sq = this.getReadableDatabase();
        String s;
        /// select all from our table
        String q = "SELECT * FROM "+TABLE_NAME;

        /// cursor is one kind of iterator by this q sql.
        Cursor c = sq.rawQuery(q, null);
        /// starting from first
        c.moveToFirst();
        if(c.moveToFirst())
        {
            s=c.getString(c.getColumnIndex(TAB_GM+""));
            if(s.equals(gm)==true)
            {
                return true;
            }
            c.moveToNext();
        }
        return false;
    }

    boolean i(String iden)
    {
        /// Readable access from database
        SQLiteDatabase sq = this.getReadableDatabase();
        String s;
        /// select all from our table
        String q = "SELECT * FROM "+TABLE_NAME;

        /// cursor is one kind of iterator by this q sql.
        Cursor c = sq.rawQuery(q, null);
        /// starting from first
        c.moveToFirst();
        if(c.moveToFirst())
        {
            s=c.getString(c.getColumnIndex(TAB_Identity+""));
            if(s.equals(iden)==true)
            {
                return true;
            }
            c.moveToNext();
        }
        return false;
    }
}
