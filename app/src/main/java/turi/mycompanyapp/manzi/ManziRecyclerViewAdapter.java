package turi.mycompanyapp.manzi;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class ManziRecyclerViewAdapter extends RecyclerView.Adapter<ManziRecyclerViewAdapter.ManziImageViewHolder>{
    private static final String TAG = "ManziRecyclerViewAdapter";
    private List<Photo> mPhotoList;
    private Context mContext;

    public ManziRecyclerViewAdapter(List<Photo> photoList, Context context) {
        mPhotoList = photoList;
        mContext = context;
    }

    static class ManziImageViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = "ManziImageViewHolder";
        ImageView thumbNail = null;
        TextView title = null;

        public ManziImageViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "ManziImageViewHolder starts");
            this.thumbNail = (ImageView) itemView.findViewById(R.id.thumbNail);
            this.title = (TextView) itemView.findViewById(R.id.photo_title);
        }
    }
}
