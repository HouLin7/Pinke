package com.gome.work.common.widget.pickerview.wheel;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class WheelScroller {

    public static final int MIN_DELTA_FOR_SCROLLING = 1;
    private static final int SCROLLING_DURATION = 400;
    // Messages
    private static final int MESSAGE_SCROLL = 0;
    private static final int MESSAGE_JUSTIFY = 1;
    private static ScrollingListener listener;
    // Context
    private Context context;
    // Scrolling
    private GestureDetector gestureDetector;
    private static Scroller scroller;
    private static int lastScrollY;
    private float lastTouchedY;
    private static boolean isScrollingPerformed;
    // animation filter
    private static Handler animationHandler = new AnimationHandler();

    private static class AnimationHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            scroller.computeScrollOffset();
            int currY = scroller.getCurrY();
            int delta = lastScrollY - currY;
            lastScrollY = currY;
            if (delta != 0) {
                listener.onScroll(delta);
            }

            // scrolling is not finished when it comes to final Y
            // so, finish it manually
            if (Math.abs(currY - scroller.getFinalY()) < MIN_DELTA_FOR_SCROLLING) {
                currY = scroller.getFinalY();
                scroller.forceFinished(true);
            }
            if (!scroller.isFinished()) {
                animationHandler.sendEmptyMessage(msg.what);
            } else if (msg.what == MESSAGE_SCROLL) {
                justify();
            } else {
                finishScrolling();
            }
        }
    }

    // gesture listener
    private SimpleOnGestureListener gestureListener = new SimpleOnGestureListener() {
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            // Do scrolling in onTouchEvent() since onScroll() are not call immediately
            //  when user touch and move the wheel
            return true;
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            lastScrollY = 0;
            final int maxY = 0x7FFFFFFF;
            final int minY = -maxY;
            scroller.fling(0, lastScrollY, 0, (int) -velocityY, 0, 0, minY, maxY);
            setNextMessage(MESSAGE_SCROLL);
            return true;
        }
    };


    public WheelScroller(Context context, ScrollingListener listener) {
        gestureDetector = new GestureDetector(context, gestureListener);
        gestureDetector.setIsLongpressEnabled(false);

        scroller = new Scroller(context);

        WheelScroller.listener = listener;
        this.context = context;
    }


    public void setInterpolator(Interpolator interpolator) {
        scroller.forceFinished(true);
        scroller = new Scroller(context, interpolator);
    }

    public void scroll(int distance, int time) {
        scroller.forceFinished(true);

        lastScrollY = 0;

        scroller.startScroll(0, 0, 0, distance, time != 0 ? time : SCROLLING_DURATION);
        setNextMessage(MESSAGE_SCROLL);

        startScrolling();
    }

    /**
     * Stops scrolling
     */
    public void stopScrolling() {
        scroller.forceFinished(true);
    }

    /**
     * Handles Touch event
     *
     * @param event the motion event
     * @return
     */
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastTouchedY = event.getY();
                scroller.forceFinished(true);
                clearMessages();
                break;

            case MotionEvent.ACTION_MOVE:
                // perform scrolling
                int distanceY = (int) (event.getY() - lastTouchedY);
                if (distanceY != 0) {
                    startScrolling();
                    listener.onScroll(distanceY);
                    lastTouchedY = event.getY();
                }
                break;
        }

        if (!gestureDetector.onTouchEvent(event) && event.getAction() == MotionEvent.ACTION_UP) {
            justify();
        }

        return true;
    }

    /**
     * Set next message to queue. Clears queue before.
     *
     * @param message the message to set
     */
    private static void setNextMessage(int message) {
        clearMessages();
        animationHandler.sendEmptyMessage(message);
    }

    /**
     * Clears messages from queue
     */
    private static void clearMessages() {
        animationHandler.removeMessages(MESSAGE_SCROLL);
        animationHandler.removeMessages(MESSAGE_JUSTIFY);
    }

    /**
     * Justifies wheel
     */
    private static void justify() {
        listener.onJustify();
        setNextMessage(MESSAGE_JUSTIFY);
    }

    /**
     * Starts scrolling
     */
    private void startScrolling() {
        if (!isScrollingPerformed) {
            isScrollingPerformed = true;
            listener.onStarted();
        }
    }

    /**
     * Finishes scrolling
     */
    static void finishScrolling() {
        if (isScrollingPerformed) {
            listener.onFinished();
            isScrollingPerformed = false;
        }
    }

    public interface ScrollingListener {
        /**
         * Scrolling callback called when scrolling is performed.
         *
         * @param distance the distance to scroll
         */
        void onScroll(int distance);

        /**
         * Starting callback called when scrolling is started
         */
        void onStarted();

        /**
         * Finishing callback called after justifying
         */
        void onFinished();

        /**
         * Justifying callback called to justify a view when scrolling is ended
         */
        void onJustify();
    }
}
