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
        mGestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.d(TAG, "onSingleTapUp: starts");
                View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(childView != null && mListener != null){
                    Log.d(TAG, "onSingleTapUp: call listener.onItemClicked");
                    mListener.onItemClicked(childView,recyclerView.getChildAdapterPosition(childView));
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.d(TAG, "onLongPress: starts");
                View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(childView != null && mListener != null){
                    Log.d(TAG, "onLongPress: calling listener.onItemLongClicked");
                    mListener.onItemLongClicked(childView, recyclerView.getChildAdapterPosition(childView));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        Log.d(TAG, "onInterceptTouchEvent starts");
        if(mGestureDetector != null){
            boolean result = mGestureDetector.onTouchEvent(e);
            Log.d(TAG, "onInterceptTouchEvent(): returned "+result);
            return  result;
        } else {
            Log.d(TAG, "onInterceptTouchEvent(): returned: false ");
            return false;
        }
    }
}
