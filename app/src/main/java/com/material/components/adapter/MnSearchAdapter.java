package com.material.components.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.material.components.R;
import com.material.components.adapter.MnAdapter.Item;

import java.util.ArrayList;
import java.util.List;

public class MnSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private List<Item> items = new ArrayList<>();
    private List<Item> filtered_items = new ArrayList<>();

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Item obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public MnSearchAdapter(Context context, List<Item> items) {
        this.items = items;
        this.filtered_items = items;
        ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView badge_new;
        public TextView tv_parent;
        public TextView tv_sub;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            badge_new = v.findViewById(R.id.badge_new);
            tv_parent = v.findViewById(R.id.tv_parent);
            tv_sub = v.findViewById(R.id.tv_sub);
            lyt_parent = v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_search, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            Item it = filtered_items.get(position);
            view.tv_parent.setText(it.Parent);
            view.tv_sub.setText(it.Text);
            if (it.New) {
                view.badge_new.setVisibility(View.VISIBLE);
            } else {
                view.badge_new.setVisibility(View.GONE);
            }
            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, filtered_items.get(position), position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return filtered_items.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString().toLowerCase();
                if (query.isEmpty()) {
                    filtered_items = items;
                } else {
                    List<Item> filteredList = new ArrayList<>();
                    for (Item row : items) {
                        String target = row.Parent + " " + row.Text;
                        if (target.toLowerCase().contains(query)) filteredList.add(row);
                    }
                    filtered_items = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered_items;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filtered_items = (ArrayList<Item>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}