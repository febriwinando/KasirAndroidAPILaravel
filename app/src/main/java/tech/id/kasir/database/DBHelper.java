package tech.id.kasir.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import tech.id.kasir.response_api.Pengguna;
import tech.id.kasir.response_api.Restoran;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "kasir.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PENGGUNA = "pengguna";
    public static final String TABLE_RESTORAN = "restoran";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_PENGGUNA + " (" +
                "id INTEGER PRIMARY KEY, " +
                "restoran_id INTEGER, " +
                "nama TEXT, " +
                "email TEXT, " +
                "no_hp TEXT, " +
                "alamat TEXT, " +
                "foto TEXT, " +
                "role TEXT, " +
                "tugas TEXT, " +
                "status TEXT)";
        db.execSQL(CREATE_TABLE);

        String CREATE_RESTORAN_TABLE = "CREATE TABLE " + TABLE_RESTORAN + "("
                + "id INTEGER PRIMARY KEY,"
                + "restoran TEXT,"
                + "kontak TEXT,"
                + "email TEXT,"
                + "owner TEXT,"
                + "meja INTEGER,"
                + "alamat TEXT,"
                + "kelurahan_id TEXT,"
                + "kecamatan_id TEXT,"
                + "kabupaten_id TEXT,"
                + "provinsi_id TEXT,"
                + "jam_buka TEXT,"
                + "jam_tutup TEXT,"
                + "logo TEXT,"
                + "status TEXT"
                + ")";
        db.execSQL(CREATE_RESTORAN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PENGGUNA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTORAN);

        onCreate(db);
    }

    public void insertPengguna(Pengguna pengguna) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", pengguna.getId());
        values.put("restoran_id", pengguna.getRestoran_id());
        values.put("nama", pengguna.getNama());
        values.put("email", pengguna.getEmail());
        values.put("no_hp", pengguna.getNo_hp());
        values.put("alamat", pengguna.getAlamat());
        values.put("foto", pengguna.getFoto());
        values.put("role", pengguna.getRole());
        values.put("tugas", pengguna.getTugas());
        values.put("status", pengguna.getStatus());

        db.insertWithOnConflict(TABLE_PENGGUNA, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }


    public boolean isUserLoggedIn() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PENGGUNA + " LIMIT 1", null);
        boolean ada = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return ada;
    }


    public void insertRestoran(Restoran restoran) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", restoran.getId());
        values.put("restoran", restoran.getRestoran());
        values.put("kontak", restoran.getKontak());
        values.put("email", restoran.getEmail());
        values.put("owner", restoran.getOwner());
        values.put("meja", restoran.getMeja());
        values.put("alamat", restoran.getAlamat());
        values.put("kelurahan_id", restoran.getKelurahan_id());
        values.put("kecamatan_id", restoran.getKecamatan_id());
        values.put("kabupaten_id", restoran.getKabupaten_id());
        values.put("provinsi_id", restoran.getProvinsi_id());
        values.put("jam_buka", restoran.getJam_buka());
        values.put("jam_tutup", restoran.getJam_tutup());
        values.put("logo", restoran.getLogo());
        values.put("status", restoran.getStatus());

        // Cek dulu, kalau sudah ada: update
        int updated = db.update(TABLE_RESTORAN, values, "id = ?", new String[]{String.valueOf(restoran.getId())});
        if (updated == 0) {
            db.insert(TABLE_RESTORAN, null, values);
        }

        db.close();
    }

    public Restoran getRestoran() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RESTORAN + " LIMIT 1", null);

        Restoran restoran = null;

        if (cursor.moveToFirst()) {
            restoran = new Restoran();
            restoran.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            restoran.setRestoran(cursor.getString(cursor.getColumnIndexOrThrow("restoran")));
            restoran.setKontak(cursor.getString(cursor.getColumnIndexOrThrow("kontak")));
            restoran.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            restoran.setOwner(cursor.getString(cursor.getColumnIndexOrThrow("owner")));
            restoran.setMeja(cursor.getInt(cursor.getColumnIndexOrThrow("meja")));
            restoran.setAlamat(cursor.getString(cursor.getColumnIndexOrThrow("alamat")));
            restoran.setKelurahan_id(cursor.getString(cursor.getColumnIndexOrThrow("kelurahan_id")));
            restoran.setKecamatan_id(cursor.getString(cursor.getColumnIndexOrThrow("kecamatan_id")));
            restoran.setKabupaten_id(cursor.getString(cursor.getColumnIndexOrThrow("kabupaten_id")));
            restoran.setProvinsi_id(cursor.getString(cursor.getColumnIndexOrThrow("provinsi_id")));
            restoran.setJam_buka(cursor.getString(cursor.getColumnIndexOrThrow("jam_buka")));
            restoran.setJam_tutup(cursor.getString(cursor.getColumnIndexOrThrow("jam_tutup")));
            restoran.setLogo(cursor.getString(cursor.getColumnIndexOrThrow("logo")));
            restoran.setStatus(cursor.getString(cursor.getColumnIndexOrThrow("status")));
        }

        cursor.close();
        db.close();
        return restoran;
    }



}
