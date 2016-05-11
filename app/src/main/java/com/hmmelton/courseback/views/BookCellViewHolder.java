package com.hmmelton.courseback.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hmmelton.courseback.R;

/**
 * Created by harrison on 7/12/15.
 */
public class BookCellViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView price;
    public TextView edition;

    public BookCellViewHolder(View v) {
        super(v);
        title = (TextView) v.findViewById(R.id.book_cell_title);
        price = (TextView) v.findViewById(R.id.book_cell_price);
        edition = (TextView) v.findViewById(R.id.book_cell_edition);
    }
}
