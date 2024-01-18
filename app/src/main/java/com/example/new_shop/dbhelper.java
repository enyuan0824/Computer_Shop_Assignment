package com.example.new_shop;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbhelper extends SQLiteOpenHelper {
    static final String DB_NAME = "COMPUTER_SHOP.DB"; //DATABASE NAME
    static final int DB_VERSION = 15;
    //Customer Table
    public static final String CUSTOMER_TABLE = "customer";
    // Table columns
    public static final String CUSTOMER_ID = "_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";


    public static final String PRODUCT_TABLE = "product";
    // Table columns
    public static final String PRODUCT_ID = "_id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_PRICE = "product_price";
    public static final String PRODUCT_QUANTITY_ENTRY = "quantity_entre";
    public static final String PRODUCT_QUANTITY_SOLD = "quantity_sold";
    public static final String PRODUCT_QUANTITY_REMAINING = "quantity_remaining";
    public static final String PRODUCT_DESCRIPTION = "description";

    //order Table
    public static final String ORDER_TABLE = "Order";
    // Table columns
    public static final String ORDER_ID = "_id";
    public static final String NAME_ORDER = "name";
    public static final String PRODUCT_ORDER_NAME = "product_name";
    public static final String PAYMENT_TYPE = "payment_type";
    public static final String DELIVERY_OPTION = "delivery_option";
    public static final String TOTAL_PRICE = "total_price";
    public static final String PROCESS = "process";

    //order Table
    public static final String ADDRESS_TABLE = "address";
    // Table columns
    public static final String ADDRESS_ID = "_id";
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String FLOOR = "floor";
    public static final String BUILDING = "building";
    public static final String REGION = "region";




    //create table query
    private static final String CREATE_CUSTOMER_TABLE = " create table " + CUSTOMER_TABLE + "(" + CUSTOMER_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + EMAIL + " TEXT , " + PASSWORD + " TEXT);";

    private static final String CREATE_PRODUCT_TABLE = " create table " + PRODUCT_TABLE + "(" + PRODUCT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "  + PRODUCT_NAME + " TEXT NOT NULL ,"
            + PRODUCT_PRICE + " TEXT NOT NULL , " + PRODUCT_DESCRIPTION + " TEXT NOT NULL , " + PRODUCT_QUANTITY_ENTRY + " TEXT NOT NULL, "
            + PRODUCT_QUANTITY_SOLD + " TEXT NOT NULL, "+ PRODUCT_QUANTITY_REMAINING + " TEXT NOT NULL );";

    private static final String CREATE_ORDER_TABLE = " create table \"" + ORDER_TABLE + "\"(" + ORDER_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_ORDER + " TEXT NOT NULL, "
            + PRODUCT_ORDER_NAME + " TEXT NOT NULL ," + PAYMENT_TYPE + " TEXT NOT NULL , "
            + DELIVERY_OPTION + " TEXT NOT NULL," + TOTAL_PRICE + " TEXT NOT NULL, " + PROCESS + " TEXT);";

    private static final String CREATE_ADDRESS_TABLE = " create table " + ADDRESS_TABLE + "(" + ADDRESS_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "+CUSTOMER_NAME+ " TEXT NOT NULL, " + FLOOR + " TEXT NOT NULL, "
            + BUILDING + " TEXT NOT NULL ,"+ REGION + " TEXT NOT NULL);";




    public dbhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public dbhelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CUSTOMER_TABLE);
        db.execSQL(CREATE_PRODUCT_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
        db.execSQL(CREATE_ADDRESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ADDRESS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS \"" + ORDER_TABLE + "\"");
        onCreate(db);
    }

    //method to update the password
    public boolean updatePassword(String email, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD, newPassword);

        try {
            // Update the password for the specified email
            db.update(CUSTOMER_TABLE, values, EMAIL + " = ?", new String[]{email});
            db.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            db.close();
            return false;
        }
    }
}
