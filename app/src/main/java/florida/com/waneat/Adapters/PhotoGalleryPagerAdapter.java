package florida.com.waneat.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.koushikdutta.ion.Ion;

import florida.com.waneat.R;

/**
 * Created by sergiomoreno on 13/2/18.
 */

public class PhotoGalleryPagerAdapter extends PagerAdapter {

    private static final String TAG = "ImageViewPage";
    Context mContext;
    LayoutInflater mLayoutInflater;

    private String[] mResources;

    public PhotoGalleryPagerAdapter(Context context, String[] resources) {
        mContext = context;
        mResources = resources;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.image_item, container, false);

        final ImageView iv_photo = (ImageView) itemView.findViewById(R.id.iv_photo);

        if (!mResources[position].equals("")){
            Ion.with(mContext)
                    .load(mResources[position].trim())
                    .intoImageView(iv_photo);
        }
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

}