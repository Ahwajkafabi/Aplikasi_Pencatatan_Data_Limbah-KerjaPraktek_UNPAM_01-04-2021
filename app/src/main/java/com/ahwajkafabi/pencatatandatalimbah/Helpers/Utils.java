package com.ahwajkafabi.pencatatandatalimbah.Helpers;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ahwajkafabi.pencatatandatalimbah.DasboardActivity;
import com.ahwajkafabi.pencatatandatalimbah.Data.Cekhlist;
import com.ahwajkafabi.pencatatandatalimbah.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yarolegovich.lovelydialog.LovelyChoiceDialog;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static List<Cekhlist> DataCache =new ArrayList<>();

    public static String searchString = "";

    /**
     * This method allows you easily show a toast message from any activity
     * @param c
     * @param message
     */
    public static void show(Context c, String message){
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * You can pass an arbitrary number of edittexts into this method, then
     * we pick the first three and validate them
     * @param editTexts
     * @return
     */
    public static boolean validate(EditText... editTexts){
        EditText nameTxt = editTexts[0];
        EditText descriptionTxt = editTexts[1];
        EditText lokasiTxt = editTexts[2];

        if(nameTxt.getText() == null || nameTxt.getText().toString().isEmpty()){
            nameTxt.setError("Nama wajib di isi ya");
            return false;
        }
        if(descriptionTxt.getText() == null || descriptionTxt.getText().toString().isEmpty()){
            descriptionTxt.setError("Keterangan wajib di isi juga");
            return false;
        }
        if(lokasiTxt.getText() == null || lokasiTxt.getText().toString().isEmpty()){
            lokasiTxt.setError("jangan lupa lokasi wajib di isi");
            return false;
        }
        return true;
    }

    /**
     * This method will allow easily open any activity
     * @param c
     * @param clazz
     */
    public static void openActivity(Context c, Class clazz){
        Intent intent = new Intent(c, clazz);
        c.startActivity(intent);
    }

    /**
     * This method will allow us show an Info dialog anywhere in our app.
     */
    public static void showInfoDialog(final AppCompatActivity activity, String title,
                                      String message) {
        new LovelyStandardDialog(activity, LovelyStandardDialog.ButtonLayout.HORIZONTAL)
                .setTopColorRes(R.color.indigo)
                .setButtonsColorRes(R.color.darkDeepOrange)
                .setIcon(R.drawable.m_info)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Lanjut", v -> {})
                .setNeutralButton("Menu", v -> openActivity(activity, DasboardActivity.class))
                .setNegativeButton("kembali", v -> activity.finish())
                .show();
    }

    /**
     * This method will allow us show a single select dialog where we can select and return a
     * star to an edittext.
     */
    public static void selectStar(Context c, final EditText starTxt){
        String[] stars ={"SENIN", "SELASA", "RABU", "KAMIS", "JUM'AT", "SABTU", "MINGGU"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(c,
                android.R.layout.simple_list_item_1,
                stars);
        new LovelyChoiceDialog(c)
                .setTopColorRes(R.color.darkGreen)
                .setTitle("Hari Pengecekan Data")
                .setTitleGravity(Gravity.CENTER_HORIZONTAL)
                .setIcon(R.drawable.m_star)
                .setMessage("Silahkan pilih hari sesuai jadwal")
                .setMessageGravity(Gravity.CENTER_HORIZONTAL)
                .setItems(adapter, (position, item) -> starTxt.setText(item))
                .show();
    }

    /**
     * This method will allow us convert a string into a java.util.Date object and
     *  return it.
     */
    public static Date giveMeDate(String stringDate){
        try {
            SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT);
            return sdf.parse(stringDate);
        }catch (ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * This method will allow us send a serialized scientist objec  to a specified
     *  activity
     */
    public static void sendCekhlistToActivity(Context c, Cekhlist cekhlist,
                                               Class clazz){
        Intent i=new Intent(c,clazz);
        i.putExtra("CEKHLIST_KEY",cekhlist);
        c.startActivity(i);
    }

    /**
     * This method will allow us receive a serialized scientist, deserialize it and return it,.
     */
    public  static Cekhlist receiveCekhlist(Intent intent, Context c){
        try {
            return (Cekhlist) intent.getSerializableExtra("CEKHLIST_KEY");
        }catch (Exception e){
            e.printStackTrace();
            //show(c,"RECEIVING-CEKHLIST ERROR: "+e.getMessage());
        }
        return null;
    }
    public static void showProgressBar(ProgressBar pb){
        pb.setVisibility(View.VISIBLE);
    }
    public static void hideProgressBar(ProgressBar pb){
        pb.setVisibility(View.GONE);
    }

    public static DatabaseReference getDatabaseRefence() {
        return FirebaseDatabase.getInstance().getReference();
    }

}
//end
