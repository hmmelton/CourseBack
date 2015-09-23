package com.hmmelton.textrack.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hmmelton.textrack.R;

/**
 * Created by harrison on 7/12/15.
 */
public class BookCellViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView price;
    public TextView course;
    public View image;

    public BookCellViewHolder(View v) {
        super(v);
        title = (TextView) v.findViewById(R.id.book_cell_title);
        price = (TextView) v.findViewById(R.id.book_cell_price);
        course = (TextView) v.findViewById(R.id.book_cell_edition);
        image = v.findViewById(R.id.bg_image);
    }
}
