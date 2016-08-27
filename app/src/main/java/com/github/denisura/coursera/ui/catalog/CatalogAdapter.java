package com.github.denisura.coursera.ui.catalog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.denisura.coursera.R;
import com.github.denisura.coursera.data.model.CoursesV1;
import com.github.denisura.coursera.data.model.Item;
import com.github.denisura.coursera.data.model.OnDemandSpecializationsV1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.github.denisura.coursera.R.id.entity_image;
import static com.github.denisura.coursera.R.id.numCourses;
import static com.github.denisura.coursera.R.id.partners_list;

/**
 * @author e.matsyuk
 */
public class CatalogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int UNKNOWN_VIEW = -1;
    private static final int COURSE_VIEW = 0;
    private static final int SPECIALIZATION_VIEW = 1;

    private List<Item> listElements = new ArrayList<>();
    // after reorientation test this member
    // or one extra request will be sent after each reorientation
    private boolean allItemsLoaded;

    private final Context mContext;

    public CatalogAdapter(Context context) {
        mContext = context;
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        public TextView mName;

        @BindView(entity_image)
        ImageView mLogo;

        @BindView(partners_list)
        TextView mPartners;

        public CourseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    static class SpecializationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView mName;

        @BindView(entity_image)
        ImageView mLogo;

        @BindView(partners_list)
        TextView mPartners;

        @BindView(numCourses)
        TextView mNumCourses;

        public SpecializationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void addNewItems(List<Item> items) {
        if (items.size() == 0) {
            allItemsLoaded = true;
            return;
        }
        listElements.addAll(items);
    }

    public boolean isAllItemsLoaded() {
        return allItemsLoaded;
    }

    @Override
    public long getItemId(int position) {

        return getItem(position).getId();
    }

    public Item getItem(int position) {
        return listElements.get(position);
    }

    @Override
    public int getItemCount() {
        return listElements.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case COURSE_VIEW:
                View courseView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_catalog_course_entity, parent, false);
                return new CourseViewHolder(courseView);
            case SPECIALIZATION_VIEW:
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_catalog_entity, parent, false);
                return new SpecializationViewHolder(v);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        if (getItem(position).getEntity() instanceof CoursesV1) {
            return COURSE_VIEW;
        }

        if (getItem(position).getEntity() instanceof OnDemandSpecializationsV1) {
            return SPECIALIZATION_VIEW;
        }
        return UNKNOWN_VIEW;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case COURSE_VIEW:
                onBindCourseHolder((CourseViewHolder) holder, (CoursesV1) getItem(position).getEntity());
                break;
            case SPECIALIZATION_VIEW:
                onBindSpecializationHolder((SpecializationViewHolder) holder, (OnDemandSpecializationsV1) getItem(position).getEntity());
                break;
        }
    }

    private void onBindCourseHolder(CourseViewHolder holder, CoursesV1 course) {
        Glide.with(mContext).load(course.photoUrl)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.mLogo);
        holder.mName.setText(course.name);
        holder.mLogo.setContentDescription(course.name);
        holder.mPartners.setText(TextUtils.join("\n", course.partnerNames));
    }

    private void onBindSpecializationHolder(SpecializationViewHolder holder, OnDemandSpecializationsV1 specialization) {

        Glide.with(mContext).load(specialization.logo)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.mLogo);

        holder.mName.setText(specialization.name);
        holder.mLogo.setContentDescription(specialization.name);
        holder.mPartners.setText(TextUtils.join("\n", specialization.partnerNames));
        int numCourses = specialization.courseIds.size();
        holder.mNumCourses.setText(
                mContext
                        .getResources()
                        .getQuantityString(R.plurals.format_num_courses, numCourses, numCourses)
        );

    }
}