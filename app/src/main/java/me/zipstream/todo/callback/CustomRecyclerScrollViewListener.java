package me.zipstream.todo.callback;

import android.support.v7.widget.RecyclerView;

public abstract class CustomRecyclerScrollViewListener extends RecyclerView.OnScrollListener {

    private int scrollDist = 0;
    private boolean isVisible = true;
    private static final float MINIMUM = 20f;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (isVisible && scrollDist > MINIMUM) {
            hide();
            scrollDist = 0;
            isVisible = false;
        } else if (!isVisible && scrollDist < -MINIMUM) {
            show();
            scrollDist = 0;
            isVisible = true;
        }

        if ((isVisible && dy > 0) || (!isVisible && dy < 0)) {
            scrollDist = scrollDist + dy;
        }
    }

    public abstract void show();
    public abstract void hide();
}
