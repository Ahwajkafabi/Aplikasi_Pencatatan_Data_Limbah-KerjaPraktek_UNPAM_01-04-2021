package com.ahwajkafabi.pencatatandatalimbah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Context;
import android.helper.DateTimePickerEditText;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ahwajkafabi.pencatatandatalimbah.Data.Cekhlist;
import com.ahwajkafabi.pencatatandatalimbah.Helpers.FirebaseCRUDHelper;
import com.ahwajkafabi.pencatatandatalimbah.Helpers.Utils;
import com.google.firebase.database.DatabaseReference;

import java.util.Date;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class CRUDActivity extends AppCompatActivity {

    //we'll have several instance fields
    private EditText nameTxt, descriptionTxt, lokasiTxt, hariTxt, phinletmskTxt, phinletoutTxt, phoutletmskTxt,
    phoutletoutTxt, suhuinletmskTxt, suhuinletoutTxt, suhuoutletmskTxt, suhuoutletoutTxt, alumuniumTxt, floaculanTxt,
    hasilwasteTxt, sludgemskTxt, sludgeoutTxt, debitTxt, selisihTxt, baikTxt, rusakTxt;
    private TextView headerTxt;
    private DateTimePickerEditText dobTxt,dodTxt;
    private ProgressBar mProgressBar;

    private final Context c = CRUDActivity.this;
    private Cekhlist receivedCekhlist;
    private FirebaseCRUDHelper crudHelper=new FirebaseCRUDHelper();
    private DatabaseReference db= Utils.getDatabaseRefence();

    private void initializeWidgets() {
        mProgressBar = findViewById(R.id.mProgressBarSave);

        headerTxt = findViewById(R.id.headerTxt);
        nameTxt = findViewById(R.id.nameTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        lokasiTxt = findViewById(R.id.lokasiTxt);
        hariTxt = findViewById(R.id.hariTxt);
        dobTxt = findViewById(R.id.dobTxt);
        dobTxt.setFormat(Utils.DATE_FORMAT);
        dodTxt = findViewById(R.id.dodTxt);
        dodTxt.setFormat(Utils.DATE_FORMAT);
        phinletmskTxt = findViewById(R.id.phinletmskTxt);
        phinletoutTxt = findViewById(R.id.phinletoutTxt);
        phoutletmskTxt = findViewById(R.id.phoutletmskTxt);
        phoutletoutTxt = findViewById(R.id.phoutletoutTxt);
        suhuinletmskTxt = findViewById(R.id.suhuinletmskTxt);
        suhuinletoutTxt = findViewById(R.id.suhuinletoutTxt);
        suhuoutletmskTxt = findViewById(R.id.suhuoutletmskTxt);
        suhuoutletoutTxt = findViewById(R.id.suhuoutletoutTxt);
        alumuniumTxt = findViewById(R.id.alumuniumTxt);
        floaculanTxt = findViewById(R.id.floaculanTxt);
        hasilwasteTxt =findViewById(R.id.hasilwasteTxt);
        sludgemskTxt = findViewById(R.id.sludgemskTxt);
        sludgeoutTxt = findViewById(R.id.sludgeoutTxt);
        debitTxt = findViewById(R.id.debitTxt);
        selisihTxt = findViewById(R.id.selisihTxt);
        baikTxt = findViewById(R.id.baikTxt);
        rusakTxt = findViewById(R.id.rusakTxt);
    }

    private void insertData() {
        String name, description, lokasi, hari, dob, dod, phinletmsk, phinletout, phoutletmsk, phoutletout, suhuinletmsk,
        suhuinletout, suhuoutletmsk, suhuoutletout, alumunium, floaculan, hasilwaste, sludgemsk, sludgeout, debit, selisih, baik, rusak;
        if (Utils.validate(nameTxt, descriptionTxt, hariTxt, phinletmskTxt, phinletoutTxt, phoutletmskTxt, phoutletoutTxt, suhuinletmskTxt,
                suhuinletoutTxt, suhuoutletmskTxt, suhuoutletoutTxt, alumuniumTxt, floaculanTxt, hasilwasteTxt, sludgemskTxt, sludgeoutTxt,
                debitTxt, selisihTxt, baikTxt, rusakTxt)) {
            name = nameTxt.getText().toString();
            description = descriptionTxt.getText().toString();
            lokasi = lokasiTxt.getText().toString();
            hari = hariTxt.getText().toString();
            phinletmsk = phinletmskTxt.getText().toString();
            phinletout = phinletoutTxt.getText().toString();
            phoutletmsk = phoutletmskTxt.getText().toString();
            phoutletout = phoutletoutTxt.getText().toString();
            suhuinletmsk = suhuinletmskTxt.getText().toString();
            suhuinletout = suhuinletoutTxt.getText().toString();
            suhuoutletmsk = suhuoutletmskTxt.getText().toString();
            suhuoutletout = suhuoutletoutTxt.getText().toString();
            alumunium = alumuniumTxt.getText().toString();
            floaculan = floaculanTxt.getText().toString();
            hasilwaste = hasilwasteTxt.getText().toString();
            sludgemsk = sludgemskTxt.getText().toString();
            sludgeout = sludgeoutTxt.getText().toString();
            debit = debitTxt.getText().toString();
            selisih = selisihTxt.getText().toString();
            baik = baikTxt.getText().toString();
            rusak = rusakTxt.getText().toString();

            if (dobTxt.getDate() != null) {
                Date date=dobTxt.getDate();
                dob = dobTxt.getFormat().format(dobTxt.getDate());
            } else {
                dobTxt.setError("Invalid Date");
                dobTxt.requestFocus();
                return;
            }
            if (dodTxt.getDate() != null) {
                dod = dodTxt.getFormat().format(dodTxt.getDate());
            } else {
                dodTxt.setError("Invalid Date");
                dodTxt.requestFocus();
                return;
            }

            Cekhlist newCekhlist=new Cekhlist("",name,description,lokasi,hari,dob,dod,phinletmsk,phinletout,phoutletmsk,phoutletout,suhuinletmsk,suhuinletout,suhuoutletmsk,suhuoutletout,alumunium,floaculan,hasilwaste,sludgemsk,sludgeout,debit,selisih,baik,rusak);
            crudHelper.insert(this,db,mProgressBar,newCekhlist);

        }
    }

    /**
     * Validate then update data
     */
    private void updateData() {
        String name, description, lokasi, hari, dob,dod, phinletmsk, phinletout, phoutletmsk, phoutletout, suhuinletmsk,suhuinletout,suhuoutletmsk,suhuoutletout,alumunium,floaculan,hasilwaste,sludgemsk,sludgeout,debit,selisih,baik,rusak;
        if (Utils.validate(nameTxt, descriptionTxt, hariTxt)) {
            name = nameTxt.getText().toString();
            description = descriptionTxt.getText().toString();
            lokasi = lokasiTxt.getText().toString();
            hari = hariTxt.getText().toString();
            phinletmsk = phinletmskTxt.getText().toString();
            phinletout = phinletoutTxt.getText().toString();
            phoutletmsk = phoutletmskTxt.getText().toString();
            phoutletout = phoutletoutTxt.getText().toString();
            suhuinletmsk = suhuinletmskTxt.getText().toString();
            suhuinletout = suhuinletoutTxt.getText().toString();
            suhuoutletmsk = suhuoutletoutTxt.getText().toString();
            suhuoutletout = suhuoutletoutTxt.getText().toString();
            alumunium = alumuniumTxt.getText().toString();
            floaculan = floaculanTxt.getText().toString();
            hasilwaste = hasilwasteTxt.getText().toString();
            sludgemsk = sludgemskTxt.getText().toString();
            sludgeout = sludgeoutTxt.getText().toString();
            debit = debitTxt.getText().toString();
            selisih = selisihTxt.getText().toString();
            baik = baikTxt.getText().toString();
            rusak = rusakTxt.getText().toString();

            if (dobTxt.getDate() != null) {
                dob = dobTxt.getFormat().format(dobTxt.getDate());
            } else {
                dobTxt.setError("Invalid Date");
                dobTxt.requestFocus();
                return;
            }
            if (dodTxt.getDate() != null) {
                dod = dodTxt.getFormat().format(dodTxt.getDate());
            } else {
                dodTxt.setError("Invalid Date");
                dodTxt.requestFocus();
                return;
            }

            Cekhlist newCekhlist=new Cekhlist(receivedCekhlist.getKey(),name,description,lokasi,hari,dob,dod,phinletmsk,phinletout,phoutletmsk,phoutletout,suhuinletmsk,suhuinletout,suhuoutletmsk,suhuoutletout,alumunium,floaculan,hasilwaste,sludgemsk,sludgeout,debit,selisih,baik,rusak);
            crudHelper.update(this,db,mProgressBar,newCekhlist);

        }
    }

    /**
     * Delete data from firebase
     */
    private void deleteData() {
        crudHelper.delete(this,db,mProgressBar,receivedCekhlist);
    }

    /**
     * Show selected star in edittext
     */
    private void showSelectedStarInEditText() {
        hariTxt.setOnClickListener(v -> Utils.selectStar(c, hariTxt));
    }

    /**
     * Warn user if he clicks the back button
     */
    @Override
    public void onBackPressed() {
        Utils.showInfoDialog(this, "Peringatan", "Apakah kamu yakin ingin keluar?");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (receivedCekhlist == null) {
            getMenuInflater().inflate(R.menu.new_item_menu, menu);
            headerTxt.setText("Tambah Data Baru");
        } else {
            getMenuInflater().inflate(R.menu.edit_item_menu, menu);
            headerTxt.setText("Edit Data");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.insertMenuItem:
                insertData();
                return true;
            case R.id.editMenuItem:
                if (receivedCekhlist != null) {
                    updateData();
                } else {
                    Utils.show(this, "EDIT DATA");
                }
                return true;
            case R.id.deleteMenuItem:
                if (receivedCekhlist != null) {
                    deleteData();
                } else {
                    Utils.show(this, "HAPUS DATA");
                }
                return true;
            case R.id.viewAllMenuItem:
                Utils.openActivity(this, CekhlistActivity.class);
                finish();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Attach Base Context
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    /**
     * When our activity is resumed we will receive our data and set them to their editing
     * widgets.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Cekhlist o = Utils.receiveCekhlist(getIntent(), c);
        if (o != null) {
            receivedCekhlist = o;
            nameTxt.setText(receivedCekhlist.getName());
            descriptionTxt.setText(receivedCekhlist.getDescription());
            lokasiTxt.setText(receivedCekhlist.getLokasi());
            hariTxt.setText(receivedCekhlist.getHari());
            phinletmskTxt.setText(receivedCekhlist.getPhinletmsk());
            phinletoutTxt.setText(receivedCekhlist.getPhinletout());
            phoutletmskTxt.setText(receivedCekhlist.getPhoutletmsk());
            phoutletoutTxt.setText(receivedCekhlist.getPhoutletout());
            suhuinletmskTxt.setText(receivedCekhlist.getSuhuinletmsk());
            suhuinletoutTxt.setText(receivedCekhlist.getSuhuinletout());
            alumuniumTxt.setText(receivedCekhlist.getAlumunium());
            floaculanTxt.setText(receivedCekhlist.getFloaculan());
            hasilwasteTxt.setText(receivedCekhlist.getHasilwaste());
            sludgemskTxt.setText(receivedCekhlist.getSludgemsk());
            sludgeoutTxt.setText(receivedCekhlist.getSludgeout());
            debitTxt.setText(receivedCekhlist.getDebit());
            selisihTxt.setText(receivedCekhlist.getSelisih());
            baikTxt.setText(receivedCekhlist.getBaik());
            rusakTxt.setText(receivedCekhlist.getRusak());

            Object dob = receivedCekhlist.getDob();
            if (dob != null) {
                dobTxt.setDate(Utils.giveMeDate(dob.toString()));
            }
            Object dod = receivedCekhlist.getDod();
            if (dod != null) {
                dodTxt.setDate(Utils.giveMeDate(dod.toString()));
            }
        } else {
            //Utils.show(c,"Received Scientist is Null");
        }
    }

    /**
     * Let's override our onCreate() method
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_r_u_d);

        this.initializeWidgets();
        this.showSelectedStarInEditText();
    }
}