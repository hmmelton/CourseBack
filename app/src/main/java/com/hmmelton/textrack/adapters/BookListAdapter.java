package com.hmmelton.textrack.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmmelton.textrack.R;
import com.hmmelton.textrack.views.BookCellViewHolder;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by harrison on 7/12/15.
 */
public class BookListAdapter extends RecyclerView.Adapter<BookCellViewHolder> {

    @SuppressWarnings("unused")
    private final String TAG = "BookListAdapter";

    private List<ParseObject> bookList;

    public BookListAdapter(List<ParseObject> bookList) {
        this.bookList = bookList;
    }

    @Override
    public BookCellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.book_list_item, parent, false);

        return new BookCellViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookCellViewHolder holder, int position) {
        ParseObject book = bookList.get(position);
        holder.title.setText(book.getString("title"));
        holder.course.setText(book.getString("course"));
        holder.price.setText("$" + book.getString("price"));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    /**
     * This method clears the current list of books.
     */
    public void clear() {
        bookList.clear();
    }

    /**
     * This method adds all items in the parameter to the current list of books.
     * @param books books to be added to list
     */
    public void addAll(List<ParseObject> books) {
        bookList.addAll(books);
    }
}
