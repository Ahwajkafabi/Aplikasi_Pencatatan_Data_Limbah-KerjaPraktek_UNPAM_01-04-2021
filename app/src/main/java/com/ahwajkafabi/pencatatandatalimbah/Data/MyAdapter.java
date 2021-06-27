package com.ahwajkafabi.pencatatandatalimbah.Data;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahwajkafabi.pencatatandatalimbah.DetailActivity;
import com.ahwajkafabi.pencatatandatalimbah.Helpers.FilterHelper;
import com.ahwajkafabi.pencatatandatalimbah.Helpers.Utils;
import com.ahwajkafabi.pencatatandatalimbah.R;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import static com.ahwajkafabi.pencatatandatalimbah.Helpers.Utils.searchString;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
implements Filterable {

    private final Context c;
    private final int[] mMaterialColors;
    public List<Cekhlist> cekhlists;
    private List<Cekhlist> filterList;
    private FilterHelper filterHelper;

    interface ItemClickListener {
        void onItemClick(int pos);
    }

    /**
     * Our ViewHolder class
     * ROLES
     * 1. Hold inflated widgets in memory so that we can recycle them
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements
     View.OnClickListener {
        private final TextView nameTxt,mDescriptionTxt,lokasiTxt;
        private final MaterialLetterIcon mIcon;
        private ItemClickListener itemClickListener;

        ViewHolder(View itemView) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.mMaterialLetterIcon);
            nameTxt = itemView.findViewById(R.id.mNameTxt);
            mDescriptionTxt = itemView.findViewById(R.id.mDescriptionTxt);
            lokasiTxt = itemView.findViewById(R.id.mlokasiTxt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }

        void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

    /**
     * Our Constructor
     * ROLES:
     * 1. Initialize some stuff for us
     * 2. Receive a context obj we need for inflation
     * 3. Receive the list to rendered in our recyclerview
     * @param mContext
     * @param cekhlists
     */
    public MyAdapter(Context mContext, List<Cekhlist> cekhlists) {
        this.c = mContext;
        this.cekhlists = cekhlists;
        this.filterList = cekhlists;
        mMaterialColors = c.getResources().getIntArray(R.array.colors);
    }

    /**
     * ROLES:
     * 1. Inflate model.xml into a view object that we can work with in code
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.model, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Our onBindViewHolder method
     * ROLES:
     * 1. Bind data to our inflated recyclerview items
     * 2. Highlight search results using Spannable class
     * 3. Attach click listener to recyclerview items so that we open
     * detail activity
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get current cekhlist
        final Cekhlist s = cekhlists.get(position);

        //bind data to widgets
        holder.nameTxt.setText(s.getName());
        holder.mDescriptionTxt.setText(s.getDescription());
        holder.lokasiTxt.setText(s.getLokasi());
        holder.mIcon.setInitials(true);
        holder.mIcon.setInitialsNumber(2);
        holder.mIcon.setLetterSize(25);
        //set random color to our material letter icons
        holder.mIcon.setShapeColor(mMaterialColors[new Random().nextInt(
            mMaterialColors.length)]);
        holder.mIcon.setLetter(s.getName());

        //if you want to set alternating row background colors
        if(position % 2 == 0){
            holder.itemView.setBackgroundColor(Color.parseColor("#efefef"));
        }

        //get name and lokasi
        String name = s.getName().toLowerCase(Locale.getDefault());
        String lokasi = s.getLokasi().toLowerCase(Locale.getDefault());

        //highlight name text while searching
        if (name.contains(searchString) && !(searchString.isEmpty())) {
            int startPos = name.indexOf(searchString);
            int endPos = startPos + searchString.length();

            Spannable spanString = Spannable.Factory.getInstance().
                    newSpannable(holder.nameTxt.getText());
            spanString.setSpan(new ForegroundColorSpan(Color.RED), startPos, endPos,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            holder.nameTxt.setText(spanString);
        } else {
            //Utils.show(ctx, "Search string empty");
        }

        //highlight lokasi text while searching
        if (lokasi.contains(searchString) && !(searchString.isEmpty())) {

            int startPos = lokasi.indexOf(searchString);
            int endPos = startPos + searchString.length();

            Spannable spanString = Spannable.Factory.getInstance().
                    newSpannable(holder.lokasiTxt.getText());
            spanString.setSpan(new ForegroundColorSpan(Color.BLUE), startPos, endPos,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            holder.lokasiTxt.setText(spanString);
        }

        //open detailactivity when clicked
        holder.setItemClickListener(pos -> Utils.sendCekhlistToActivity(c, (Cekhlist) cekhlists,
                DetailActivity.class));
    }

    @Override
    public int getItemCount() {
        return cekhlists.size();
    }

    @Override
    public Filter getFilter() {
        if(filterHelper==null){
            filterHelper=FilterHelper.newInstance(filterList, this);
        }
        return filterHelper;
    }
}
//end