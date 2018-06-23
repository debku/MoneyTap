package com.example.aa.moneytap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by aa on 22-06-2018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context mcontext;
    private List<Item> itemDetails;

    public ItemAdapter(Context mcontext, List<Item> itemDetails) {

        this.mcontext = mcontext;
        this.itemDetails = itemDetails;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detail_list, parent, false);
        ItemAdapter.ViewHolder viewHolder = new ItemAdapter.ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(itemDetails.get(position));
        Item item = itemDetails.get(position);
        holder.edi_title.setText(item.getTitle());
        holder.edi_description.setText(item.getDescription());
         Picasso.with(mcontext).load(item.getImage())
                .resize(1000, 1050).error(R.drawable.prof_image).into(holder.pImage1);

    }

    @Override
    public int getItemCount() {
        return itemDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public EditText edi_title,edi_description;
        public ImageView pImage1;
        public ViewHolder(View itemView) {
            super(itemView);

            edi_title = (EditText)itemView.findViewById(R.id.edi_title);
            edi_description = (EditText)itemView.findViewById(R.id.edi_description);
            pImage1 = (ImageView)itemView.findViewById(R.id.pImage1);
        }
    }
}
