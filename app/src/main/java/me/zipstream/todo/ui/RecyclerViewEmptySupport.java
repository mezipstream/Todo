package me.zipstream.todo.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class RecyclerViewEmptySupport extends RecyclerView {

    private View mEmptyView;

    private AdapterDataObserver mObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            showEmptyView();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            showEmptyView();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            showEmptyView();
        }
    };

    public RecyclerViewEmptySupport(Context context) {
        super(context);
    }

    public RecyclerViewEmptySupport(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewEmptySupport(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void showEmptyView() {
        Adapter<?> adapter = getAdapter();
        if (adapter != null & mEmptyView != null) {
            if (adapter.getItemCount() == 0) {
                mEmptyView.setVisibility(VISIBLE);
                RecyclerViewEmptySupport.this.setVisibility(GONE);
            } else {
                mEmptyView.setVisibility(GONE);
                RecyclerViewEmptySupport.this.setVisibility(VISIBLE);
            }
        }
    }

    public void setEmptyView(View view) {
        mEmptyView = view;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(mObserver);
            mObserver.onChanged();
        }
    }
}
