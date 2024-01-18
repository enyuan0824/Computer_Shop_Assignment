package com.example.new_shop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;


public class dbmanager {
    private dbhelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager(Context c) {
        context = c;
    }

    public dbmanager open() throws SQLException {
        dbHelper = new dbhelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    //insert Customer data
    public void insert(String name, String email, String password) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.NAME, name);
        contentValue.put(dbhelper.EMAIL, email);
        contentValue.put(dbhelper.PASSWORD, password);
        database.insert(dbhelper.CUSTOMER_TABLE, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { dbhelper.CUSTOMER_ID, dbhelper.NAME, dbhelper.EMAIL, dbhelper.PASSWORD };
        Cursor cursor = database.query(dbhelper.CUSTOMER_TABLE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.NAME, name);
        contentValues.put(dbhelper.EMAIL, email);
        contentValues.put(dbhelper.PASSWORD, password);
        int i = database.update(dbhelper.CUSTOMER_TABLE, contentValues, dbhelper.CUSTOMER_ID + " = " + _id, null);
        return i;
    }

    public boolean checkUserCredentials(String username, String password){
        String[] columns = {dbhelper.CUSTOMER_ID};
        String selection = dbhelper.NAME + "=? AND " + dbhelper.PASSWORD + "=?";
        String[] selectionArgs = {username, password};

        Cursor cursor = database.query(dbhelper.CUSTOMER_TABLE, columns, selection, selectionArgs, null, null, null);

        boolean isValid = cursor.moveToFirst();

        cursor.close();
        return isValid;
    }

    //product tables insert
    public void insertProductData(String productName, String productPrice, String productQuantity, String description){
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.PRODUCT_NAME, productName);
        contentValue.put(dbhelper.PRODUCT_PRICE, productPrice);
        contentValue.put(dbhelper.PRODUCT_QUANTITY_ENTRY, productQuantity);
        contentValue.put(dbhelper.PRODUCT_QUANTITY_REMAINING, productQuantity);
        contentValue.put(dbhelper.PRODUCT_QUANTITY_SOLD,0);
        contentValue.put(dbhelper.PRODUCT_DESCRIPTION, description);
        database.insert(dbhelper.PRODUCT_TABLE, null, contentValue);
    }

    public int update_quantity_entry(String product_name){
        String sqlUpdate = "UPDATE " + dbhelper.PRODUCT_TABLE +
                " SET " + dbhelper.PRODUCT_QUANTITY_REMAINING + " = " + dbhelper.PRODUCT_QUANTITY_REMAINING + " - 1, " +
                dbhelper.PRODUCT_QUANTITY_SOLD + " = " + dbhelper.PRODUCT_QUANTITY_SOLD + " + 1" +
                " WHERE " + dbhelper.PRODUCT_NAME + " = ?";

        // Execute the SQL update statement
        SQLiteStatement statement = database.compileStatement(sqlUpdate);
        statement.bindString(1, product_name);
        int rowsAffected = statement.executeUpdateDelete();
        return rowsAffected;
    }

    public int updateProductData(long _id, String productDescription, String productName, String productPrice){
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.PRODUCT_NAME, productName);
        contentValues.put(dbhelper.PRODUCT_PRICE, productPrice);
        contentValues.put(dbhelper.PRODUCT_DESCRIPTION, productDescription);

        int rowsAffected = database.update(dbhelper.PRODUCT_TABLE, contentValues, dbhelper.PRODUCT_ID + " = " + _id, null);
        return rowsAffected;
    }

    // Retrieve product data
    public Cursor getQuantityProduct(){
        String[] columns = new String[]{dbhelper.PRODUCT_ID,dbhelper.PRODUCT_NAME,dbhelper.PRODUCT_PRICE,
                dbhelper.PRODUCT_DESCRIPTION,dbhelper.PRODUCT_QUANTITY_ENTRY, dbhelper.PRODUCT_QUANTITY_SOLD,
                dbhelper.PRODUCT_QUANTITY_REMAINING};
        return database.query(dbhelper.PRODUCT_TABLE, columns, null, null, null, null, null);
    }

    public boolean deleteProductByName(String productName) {
        return database.delete(dbhelper.PRODUCT_TABLE, dbhelper.PRODUCT_NAME + "=?", new String[]{productName}) > 0;
    }

    public int getTotalSold(){
        int totalsold = 0;
        String query="SELECT SUM(" + dbhelper.PRODUCT_QUANTITY_SOLD + ") " +
                "FROM " + dbhelper.PRODUCT_TABLE ;
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalsold = cursor.getInt(0);
        }
        cursor.close();
        return totalsold;
    }



    public void insertOrderTable(String name, String product_name, String payment_ty, String delivery_op,String total){
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.NAME_ORDER, name);
        contentValue.put(dbhelper.PRODUCT_ORDER_NAME, product_name);
        contentValue.put(dbhelper.PAYMENT_TYPE, payment_ty);
        contentValue.put(dbhelper.DELIVERY_OPTION, delivery_op);
        contentValue.put(dbhelper.TOTAL_PRICE, total);
        contentValue.put(dbhelper.PROCESS, "Panding");
        database.insert("'"+dbhelper.ORDER_TABLE+"'", null, contentValue);
    }

    public boolean deleteOrderbyproductname(String productName) {
        String whereClause = dbhelper.PRODUCT_ORDER_NAME + " = ?";
        String[] whereArgs = {productName};
        return database.delete("'"+dbhelper.ORDER_TABLE+"'",whereClause,whereArgs) > 0;
    }

    public Cursor getOrders(){
        String[] columns = new String[]{dbhelper.ORDER_ID, dbhelper.NAME_ORDER, dbhelper.PRODUCT_ORDER_NAME,dbhelper.TOTAL_PRICE};
        return database.query("'"+dbhelper.ORDER_TABLE+"'", columns, null, null, null, null, null);
    }

    public int updateOrderprocess(String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.PROCESS, "Recieve");

        String whereClause = dbhelper.NAME_ORDER + " = ?";
        String[] whereArgs = {name};

        int rowsAffected = database.update("'"+dbhelper.ORDER_TABLE+"'",contentValues,whereClause,whereArgs);
        return rowsAffected;
    }

    public Cursor getOrdersbyname(String name){
        String[] columns = new String[]{dbhelper.ORDER_ID, dbhelper.NAME_ORDER, dbhelper.PRODUCT_ORDER_NAME,dbhelper.TOTAL_PRICE,dbhelper.PROCESS};
        String selection = dbhelper.NAME_ORDER + "=?";
        String[] selectionArgs = new String[]{name};
        return database.query("'"+dbhelper.ORDER_TABLE+"'", columns, selection, selectionArgs, null, null, null);
    }

    public Cursor getOrderDetails(long orderId){
        String[] columns = new String[]{dbhelper.ORDER_ID, dbhelper.NAME_ORDER, dbhelper.PRODUCT_ORDER_NAME,
                dbhelper.PAYMENT_TYPE, dbhelper.DELIVERY_OPTION};
        String selection = dbhelper.ORDER_ID + "=?";
        String[] selectionArgs = {String.valueOf(orderId)};
        return database.query("'"+dbhelper.ORDER_TABLE+"'", columns, selection, selectionArgs, null, null, null);
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        String query = "SELECT SUM(" + dbhelper.PRODUCT_PRICE + ") " +
                "FROM " + dbhelper.PRODUCT_TABLE + " p " +
                "JOIN \"" + dbhelper.ORDER_TABLE + "\" o ON p." + dbhelper.PRODUCT_ORDER_NAME + " = o." + dbhelper.PRODUCT_ORDER_NAME;
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(0);
        }
        cursor.close();
        return totalPrice;
    }





    public void insertaddresstable(String name,String floor, String building, String region){
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.CUSTOMER_NAME, name);
        contentValue.put(dbhelper.FLOOR, floor);
        contentValue.put(dbhelper.BUILDING, building);
        contentValue.put(dbhelper.REGION, region);
        database.insert(dbhelper.ADDRESS_TABLE, null, contentValue);
    }

    public Cursor getaddress(String name){
        String[] columns = new String[]{dbhelper.ADDRESS_ID, dbhelper.CUSTOMER_NAME,dbhelper.FLOOR, dbhelper.BUILDING, dbhelper.REGION};
        String selection = dbhelper.CUSTOMER_NAME + "=?";
        String[] selectionArgs = new String[]{name};
        return database.query(dbhelper.ADDRESS_TABLE, columns, selection, selectionArgs, null, null, null);
    }

    public int updateaddress(long _id, String floor, String building, String region){
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.FLOOR, floor);
        contentValues.put(dbhelper.BUILDING, building);
        contentValues.put(dbhelper.REGION, region);

        int rowsAffected = database.update(dbhelper.ADDRESS_TABLE, contentValues, dbhelper.ADDRESS_ID + " = " + _id, null);
        return rowsAffected;
    }
}
