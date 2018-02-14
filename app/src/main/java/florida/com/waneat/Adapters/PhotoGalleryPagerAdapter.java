package florida.com.waneat.Adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.koushikdutta.ion.Ion;

import java.util.List;
import florida.com.waneat.Models.ImageProduct;
import florida.com.waneat.R;

/**
 * Created by sergiomoreno on 13/2/18.
 */

public class PhotoGalleryPagerAdapter extends PagerAdapter {

    private static final String TAG = "ImageViewPage";
    Context mContext;
    LayoutInflater mLayoutInflater;
    private List<ImageProduct> images;

    public PhotoGalleryPagerAdapter(Context context, List<ImageProduct> images) {
        mContext = context;
        this.images = images;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return images.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.image_item, container, false);

        final ImageView iv_photo = (ImageView) itemView.findViewById(R.id.iv_photo);

        if (!images.get(position).getImageUrl().equals("")){
            Ion.with(mContext)
                    .load(images.get(position).getImageUrl().trim())
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