package io.github.ryanhoo.firFlight.ui.genre;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.ryanhoo.firFlight.R;
import io.github.ryanhoo.firFlight.data.model.Genre;
import io.github.ryanhoo.firFlight.ui.common.adapter.IAdapterView;
import io.github.ryanhoo.firFlight.ui.common.widget.CharacterDrawable;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 8/21/16
 * Time: 10:46 PM
 * Desc: AppItemView
 */

public class GenresItemView extends RelativeLayout implements IAdapterView<Genre> {

    Context mContext;
    @Bind(R.id.image_view_icon)
    ImageView imageView;
    @Bind(R.id.text_view_name)
    TextView textViewName;
    @Bind(R.id.text_view_local_version)
    TextView textViewLocalVersion;
    @Bind(R.id.text_view_version)
    TextView textViewVersion;
    @Bind(R.id.text_view_bundle_id)
    TextView textViewBundleId;
    @Bind(R.id.button_action)
    Button buttonAction;
    @Bind(R.id.layout_ios_badge)
    View layoutIOSBadge;

    public GenresItemView(Context context) {
        super(context);

        mContext = context;
        View.inflate(context, R.layout.item_app, this);
        ButterKnife.bind(this);
    }

    @Override
    public void bind(Genre genre, int position) {

        Glide.with(mContext)
                .load(genre.getLogo())
                .placeholder(CharacterDrawable.create(mContext, genre.getName().charAt(0), false, R.dimen.ff_padding_large))
                .into(imageView);
        textViewName.setText(genre.getName());
        //textViewVersion.setText(courses.getTag());
        textViewBundleId.setText(genre.getId());
    }
}
