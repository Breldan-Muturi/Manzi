package turi.mycompanyapp.manzi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

class ManziRecyclerViewAdapter extends RecyclerView.Adapter<ManziRecyclerViewAdapter.ManziImageViewHolder>{
    private static final String TAG = "ManziRecyclerViewAdapter";
    private List<Photo> mPhotoList;
    private Context mContext;

    public ManziRecyclerViewAdapter( Context context, List<Photo> photoList) {
        mPhotoList = photoList;
        mContext = context;
    }

    @NonNull
    @Override
    public ManziImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//         Called By the layout manager when it needs a new view
        Log.d(TAG, "onCreateViewHolder: new View Requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse,parent,false);
        return new ManziImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManziImageViewHolder holder, int position) {
//Called by the Layout Manager when it wants new data in an existing row
        if(mPhotoList == null || mPhotoList.size() == 0){
            holder.thumbNail.setImageResource(R.drawable.pplaceholder);
            holder.title.setText(R.string.empty_photo);
        } else {
            Photo photoItem = mPhotoList.get(position);
            Log.d(TAG, "onBindViewHolder: " + photoItem.getTitle() + " ---> " + position);
            Picasso.get().load(photoItem.getImage())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.thumbNail);
            holder.title.setText(photoItem.getTitle());
        }
    }

    @Override
    public int getItemCount() {

        return ((mPhotoList != null) && (mPhotoList.size() != 0) ? mPhotoList.size() : 1);
    }

    void loadNewData(List<Photo> newPhotos){
        mPhotoList = newPhotos;
        notifyDataSetChanged();
    }

    public Photo getPhoto(int position){
        return ((mPhotoList != null) && (mPhotoList.size() != 0) ? mPhotoList.get(position) : null);
    }

    static class ManziImageViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = "ManziImageViewHolder";
        ImageView thumbNail = null;
        TextView title = null;

        public ManziImageViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "ManziImageViewHolder starts");
            this.thumbNail = (ImageView) itemView.findViewById(R.id.thumbNail);
            this.title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
