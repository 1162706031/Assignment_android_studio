package com.example.sample.logic.homepage;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.sample.R;
import com.example.sample.mvvm.BaseActivity;
import com.example.sample.viewmodel.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends BaseActivity<HomeViewModel> {

    @Override
    protected void initData() {
        //监听聊天消息
        mViewModel.setSessionListener();
    }

    @Override
    protected void initView() {
        NavHostFragment navHosFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.id_fragment_container);
        NavController navController = navHosFragment.getNavController();
        BottomNavigationView bottomView = (BottomNavigationView) findViewById(R.id.id_bottom_navigation);
        NavigationUI.setupWithNavController(bottomView, navController);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_home;
    }
}
