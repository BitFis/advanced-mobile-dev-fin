package ch.amk.exercise1;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ch.amk.exercise1.ItemFragment.OnListFragmentInteractionListener;
import ch.amk.exercise1.models.Post;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    private final List<Post> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<Post> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == this.TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_header, parent, false);
            return new VHHeader(view);
        }
        if(viewType == this.TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_footer, parent, false);
            return new VFHeader(view);
        }

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(holder.getViewType() == TYPE_ITEM) {
            // coorect position, since header is added
            position = position-1;

            holder.mItem = mValues.get(position);
            holder.mIdView.setText(String.valueOf(mValues.get(position).ID));
            holder.mContentView.setText(mValues.get(position).Title);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.onListFragmentInteraction(holder.mItem);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TYPE_HEADER;
        if(position == this.getItemCount() - 1)
            return TYPE_FOOTER;
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return mValues.size()+2;
    }

    class VHHeader extends ViewHolder {
        public VHHeader(View itemView) {
            super(itemView);
        }

        @Override
        public int getViewType() {
            return TYPE_HEADER;
        }
    }

    class VFHeader extends ViewHolder {
        public VFHeader(View itemView) {
            super(itemView);
        }

        @Override
        public int getViewType() {
            return TYPE_FOOTER;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Post mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.entry_content);
        }

        public int getViewType() {
            return TYPE_ITEM;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
