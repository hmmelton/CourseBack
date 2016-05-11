package com.hmmelton.courseback.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmmelton.courseback.R;
import com.hmmelton.courseback.models.Book;
import com.hmmelton.courseback.views.BookCellViewHolder;

import java.util.List;

/**
 * Created by harrison on 7/12/15.
 */
public class BookListAdapter extends RecyclerView.Adapter<BookCellViewHolder> {

    @SuppressWarnings("unused")
    private final String TAG = "BookListAdapter";
    private List<Book> mBooks;

    public BookListAdapter(List<Book> books) {
        mBooks = books;
        Log.e(TAG, getItemCount() + "");
    }

    @Override
    public BookCellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.book_list_item, parent, false);

        return new BookCellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookCellViewHolder holder, int position) {
        Book book = mBooks.get(position);

        // Bind values to view holder
        holder.title.setText(book.getTitle());
        holder.edition.setText(book.getEdition() + "");
        holder.price.setText(book.getPrice());
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    /**
     * This method clears the current list of books.
     */
    public void clear() {
        mBooks.clear();
    }

    /**
     * This method adds all items in the parameter to the current list of books.
     *
     * @param books books to be added to list
     */
    public void addAll(List<Book> books) {
        mBooks.addAll(books);
    }
}