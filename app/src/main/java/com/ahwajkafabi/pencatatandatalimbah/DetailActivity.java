package com.ahwajkafabi.pencatatandatalimbah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ahwajkafabi.pencatatandatalimbah.Data.Cekhlist;
import com.ahwajkafabi.pencatatandatalimbah.Helpers.Utils;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView nameTV,descriptionTV,lokasiTV,hariTv,dobTV,diedTV;
    private Cekhlist receivedCekhlist;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private FloatingActionButton editFAB;

    private void initializeWidgets(){
        nameTV= findViewById(R.id.nameTV);
        descriptionTV= findViewById(R.id.descriptionTV);
        lokasiTV= findViewById(R.id.lokasiTV);
        hariTv= findViewById(R.id.starTV);
        dobTV= findViewById(R.id.dobTV);
        diedTV= findViewById(R.id.dodTV);
        editFAB=findViewById(R.id.editFAB);
        editFAB.setOnClickListener(this);
        mCollapsingToolbarLayout=findViewById(R.id.mCollapsingToolbarLayout);
        mCollapsingToolbarLayout.setExpandedTitleColor(getResources().
                getColor(R.color.white));
    }

    /**
     * Let's receive our data and show them in their respective widgets
     */
    private void receiveAndShowData(){
        receivedCekhlist= Utils.receiveCekhlist(getIntent(),DetailActivity.this);

        if(receivedCekhlist != null){
            nameTV.setText(receivedCekhlist.getName());
            descriptionTV.setText(receivedCekhlist.getDescription());
            lokasiTV.setText(receivedCekhlist.getLokasi());
            hariTv.setText(receivedCekhlist.getHari());
            dobTV.setText(receivedCekhlist.getDob());
            diedTV.setText(receivedCekhlist.getDod());

            mCollapsingToolbarLayout.setTitle(receivedCekhlist.getName());
        }

    }

    /**
     * If user clicks the FAB button, we want to send the current scientist
     * for editing
     * @param v
     */
    @Override
    public void onClick(View v) {
        int id =v.getId();
        if(id == R.id.editFAB){
            Cekhlist receivedCekhlist = new Cekhlist();
            Utils.sendCekhlistToActivity(this,receivedCekhlist,CRUDActivity.class);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            case R.id.action_edit:
                Utils.sendCekhlistToActivity(this,receivedCekhlist,CRUDActivity.class);
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Inject custom font to this activity as well
     * @param newBase
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initializeWidgets();
        receiveAndShowData();
    }
}
