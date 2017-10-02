package khi.fast.log;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class MOMAdapter extends ArrayAdapter<MOMCLASS> {

    ArrayList al = new ArrayList();
    ProgressBar mprogressBar;
    public MOMAdapter(Context context, int resource, List<MOMCLASS> objects) {
        super(context, resource, objects);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_mom, parent, false);
        }
        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageViewMOM);
        mprogressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
        Log.d("position",""+getItem(position));
        MOMCLASS message = getItem(position);

        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
        anim.setDuration(4000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
        boolean isPhoto = message.getPICTURE() != null;
        if(isNetworkAvailable()) {
            if (isPhoto) {
                Picasso.with(getContext()).load(message.getPICTURE()).into(photoImageView);

                if (mprogressBar != null)
                    mprogressBar.setVisibility(convertView.INVISIBLE);

            } else {


            }
        }
        else{
            if (isPhoto) {
                Picasso.with(getContext()).load(message.getPICTURE()).networkPolicy(NetworkPolicy.OFFLINE).into(photoImageView);

                if (mprogressBar != null)
                    mprogressBar.setVisibility(convertView.INVISIBLE);

            } else {


            }

        }



        return convertView;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}