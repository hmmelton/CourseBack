package com.hmmelton.courseback.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmmelton.courseback.R;
import com.hmmelton.courseback.views.BookCellViewHolder;

import java.util.List;

/**
 * Created by harrison on 7/12/15.
 */
public class BookListAdapter extends RecyclerView.Adapter<BookCellViewHolder> {

    @SuppressWarnings("unused")
    private final String TAG = "BookListAdapter";

    @Override
    public BookCellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.book_list_item, parent, false);

        return new BookCellViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookCellViewHolder holder, int position) {
        // TODO: bind items to view holder
    }

    @Override
    public int getItemCount() {
        // TODO: return actual size of list
        return 0;
    }

    /**
     * This method clears the current list of books.
     */
    public void clear() {
        // TODO: clear list
    }

    /**
     * This method adds all items in the parameter to the current list of books.
     *
     * @param books books to be added to list
     */
    public void addAll(List<?> books) {
        // TODO: add all books in passed list.  Also, change that question mark
    }
}