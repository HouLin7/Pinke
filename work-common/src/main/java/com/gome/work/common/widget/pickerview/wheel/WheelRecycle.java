package com.gome.work.common.widget.pickerview.wheel;

import android.view.View;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.List;

/**
 * Recycle stores wheel items to reuse.
 */
public class WheelRecycle {
    // Cached items
    private List<View> items;

    // Cached empty items
    private List<View> emptyItems;

    // Wheel view
    private WheelView wheel;


    public WheelRecycle(WheelView wheel) {
        this.wheel = wheel;
    }

    public int recycleItems(LinearLayout layout, int firstItem, ItemsRange range, int currentItem) {
        int index = firstItem;
        for (int i = 0; i < layout.getChildCount(); ) {
            if (!range.contains(index)) {
                recycleView(layout.getChildAt(i), index, currentItem);
                layout.removeViewAt(i);
                if (i == 0) { // first item
                    firstItem++;
                }
            } else {
                i++; // go to next item
            }
            index++;
        }
        return firstItem;
    }

    /**
     * Gets item view
     *
     * @return the cached view
     */
    public View getItem() {
        return getCachedView(items);
    }

    /**
     * Gets empty item view
     *
     * @return the cached empty view
     */
    public View getEmptyItem() {
        return getCachedView(emptyItems);
    }

    /**
     * Clears all views
     */
    public void clearAll() {
        if (items != null) {
            items.clear();
        }
        if (emptyItems != null) {
            emptyItems.clear();
        }
    }

    /**
     * Adds view to specified cache. Creates a cache list if it is null.
     *
     * @param view  the view to be cached
     * @param cache the cache list
     * @return the cache list
     */
    private List<View> addView(View view, List<View> cache) {
        if (cache == null) {
            cache = new LinkedList<View>();
        }

        cache.add(view);
        return cache;
    }

    /**
     * Adds view to cache. Determines view type (item view or empty one) by index.
     *
     * @param view  the view to be cached
     * @param index the index of view
     */
    private void recycleView(View view, int index, int current) {
        int count = wheel.getViewAdapter().getItemsCount();


        if ((index < 0 || index >= count) && !wheel.isCyclic()) {
            // empty view
            emptyItems = addView(view, emptyItems);
        } else {
            while (index < 0) {
                index = count + index;
            }
            index %= count;
            items = addView(view, items);
        }
    }

    /**
     * Gets view from specified cache.
     *
     * @param cache the cache
     * @return the first view from cache.
     */
    private View getCachedView(List<View> cache) {
        if (cache != null && cache.size() > 0) {
            View view = cache.get(0);
            cache.remove(0);
            return view;
        }
        return null;
    }

}
