package com.example.sample.logic.coursedetail.ui.main;

import static com.example.sample.Constant.COURSE_ID;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.sample.R;
import com.example.sample.logic.coursedetail.fragment.ClassMateFragment;
import com.example.sample.logic.coursedetail.fragment.CourseDetailFragment;
import com.example.sample.logic.coursedetail.fragment.CourseFilesFragment;
import com.example.sample.logic.coursedetail.fragment.UpdateRecordListFragment;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4};
    private final Context mContext;
    private String mCourseId;

    public SectionsPagerAdapter(Context context, FragmentManager fm, String courseId) {
        super(fm);
        mContext = context;
        mCourseId = courseId;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Bundle bundle = new Bundle();
        bundle.putString(COURSE_ID, mCourseId);
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new CourseDetailFragment();
                break;
            case 1:
                fragment = new CourseFilesFragment();
                break;
            case 2:
                fragment = new ClassMateFragment();
                break;
            default:
                fragment = new UpdateRecordListFragment();
                break;
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }
}