package io.github.ryanhoo.firFlight.ui.play;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.ryanhoo.firFlight.R;
import io.github.ryanhoo.firFlight.data.model.Classes;
import io.github.ryanhoo.firFlight.ui.common.adapter.IAdapterView;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 8/21/16
 * Time: 10:46 PM
 * Desc: AppItemView
 */

public class ClassesItemView extends RelativeLayout implements IAdapterView<Classes> {

    Context mContext;
    @Bind(R.id.chapter)
    TextView chapter;
    @Bind(R.id.section_layout)
    LinearLayout sectionLayout;
    @Bind(R.id.v_dot)
    View dot;
    @Bind(R.id.section)
    TextView setcion;

    public ClassesItemView(Context context) {
        super(context);

        mContext = context;
        View.inflate(context, R.layout.item_class, this);
        ButterKnife.bind(this);
    }

    @Override
    public void bind(Classes classes, int position) {
        if (classes.isChapter()) {
            chapter.setVisibility(View.VISIBLE);
            sectionLayout.setVisibility(View.GONE);
            chapter.setText(mContext.getString(R.string.ff_courses_chapter, classes.getChapterNum(), classes.getName()));
        } else {
            chapter.setVisibility(View.GONE);
            sectionLayout.setVisibility(View.VISIBLE);
            setcion.setText(classes.getName());
            setSelect(classes.isSelect());
        }
    }

    private void setSelect(boolean isSelect) {
        if (isSelect) {
            dot.setBackgroundResource(R.drawable.momo_dot);
            setcion.setTextColor(mContext.getResources().getColor(R.color.momo));
        } else {
            dot.setBackgroundResource(R.drawable.black_dot);
            setcion.setTextColor(mContext.getResources().getColor(R.color.ff_gray));
        }
    }
}
