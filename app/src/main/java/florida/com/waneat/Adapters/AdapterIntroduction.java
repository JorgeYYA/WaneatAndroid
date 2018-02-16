package florida.com.waneat.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import florida.com.waneat.Activities.SplashActivity;
import florida.com.waneat.R;

/**
 * Created by David on 14/02/2018.
 */

public class AdapterIntroduction extends PagerAdapter {

    Context context;
    int images[];
    LayoutInflater layoutInflater;


    public AdapterIntroduction(Context context, int images[]) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.image_item, container, false);

        final ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_photo);
        imageView.setImageResource(images[position]);

        container.addView(itemView);

        //listening to image click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(position == 1) {
                    context.startActivity(new Intent(context, SplashActivity.class));
                }
                //Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
