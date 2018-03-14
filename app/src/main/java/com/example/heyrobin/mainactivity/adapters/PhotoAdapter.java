package com.example.heyrobin.mainactivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyrobin.mainactivity.R;
import com.example.heyrobin.mainactivity.presentation.PhotoDetailedActivity;
import com.example.heyrobin.mainactivity.domain.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    //Variables
    private static final String TAG = "PhotoAdapter called";
    private ArrayList<Photo> photos;
    private Context context;

    //Constructor
    public PhotoAdapter(ArrayList<Photo> photos, Context context)  {
        Log.d(TAG, "PhotoAdapter constructor called. Amount of photos received: " + photos.size());

        this.photos = photos;
        this.context = context;
    }


    //Inner Class: create 1 row of data for the RecycleView
        public class ViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener  {


        //Variables
        public View view;
        public ImageView mImageView;
        public TextView mTextView;
        private static final String TAG = "PhotoAdapter.ViewHolder";

        //Constructor
        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;

            //Log
            Log.d(TAG, "PhotoAdapter.ViewHolder called");

            //Setting onClickListener (this class)
            view.setOnClickListener(this);

            //Linking the variables from XML to this class
            mImageView = (ImageView) view.findViewById(R.id.list_row_image);
            mTextView = (TextView) view.findViewById(R.id.list_row_image_id);
        }

        //What needs to happen when clicked
        @Override
        public void onClick(View view) {
            //Log
            Log.d(TAG, "onClick called");

            //Get the position of the adapter
            int position = getAdapterPosition();
            Log.d(TAG, "Adapter position = " + position);

            Photo photo = photos.get(position);
            Log.d(TAG, "Photo id retrieved: " + photo.getID());

            //Making a new intent
            Intent photoIntent = new Intent(
                    view.getContext().getApplicationContext(),
                    PhotoDetailedActivity.class);

            //Adding the photo in the intent
            photoIntent.putExtra("PHOTO", photo);

            //Starting the new Activity on next click
            view.getContext().startActivity(photoIntent);
            Log.d(TAG, "PhotoDetailedActivity started");
        }
    }

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder called");

        //Create a new view
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        Log.d(TAG, "Inflater made");

        //Inflating the view with the content
        View view = layoutInflater.inflate(R.layout.list_row, parent, false);
        Log.d(TAG, "View made");

        //Creating a new ViewHolder (that defines what happens onclick) and returning it
        Log.d(TAG, "Returned ViewHolder");
        return new ViewHolder(view);
    }

    //Replacing the content of a view once it is no longer used
    @Override
    public void onBindViewHolder(PhotoAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder called");

        Photo photo = photos.get(position);
        Log.d(TAG, "Photo retrieved: " + photo.getID());

        holder.mTextView.setText(photo.getID());

        Picasso.get().load(photo.getImgURL()).fit().centerInside().into(holder.mImageView);
        Log.d(TAG, "Variables set");
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}
