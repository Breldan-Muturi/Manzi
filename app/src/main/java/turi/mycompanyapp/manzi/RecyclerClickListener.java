package turi.mycompanyapp.manzi;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

class RecyclerClickListener extends RecyclerView.SimpleOnItemTouchListener {
    private static final String TAG = "RecyclerClickListener";

    interface OnRecyclerClickListener{
        void onItemClicked(View view, int position);
        void onItemLongClicked(View view, int position);
    }

    private final OnRecyclerClickListener mListener;
    private final GestureDetectorCompat mGestureDetector;

    public RecyclerClickListener(Context context,final RecyclerView recyclerView, OnRecyclerClickListener listener) {
        mListener = listener;
        mGestureDetector = null;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        Log.d(TAG, "onInterceptTouchEvent starts");
        return super.onInterceptTouchEvent(rv, e);
    }
}
