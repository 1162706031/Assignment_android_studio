package com.example.sample.logic.homepage;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.sample.R;
import com.example.sample.databinding.ActivityHomeBinding;
import com.example.sample.mvvm.BaseActivity;
import com.example.sample.viewmodel.HomeViewModel;


public class HomeActivity extends BaseActivity<HomeViewModel, ActivityHomeBinding> {

    @Override
    protected void initData() {
        //监听聊天消息
        mViewModel.setSessionListener();
    }

    @Override
    protected void initView() {
        NavHostFragment navHosFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.id_fragment_container);
        NavController navController = navHosFragment.getNavController();
        NavigationUI.setupWithNavController(mViewBinding.idBottomNavigation, navController);
    }

    @Override
    protected ActivityHomeBinding getViewBinding() {
        return ActivityHomeBinding.inflate(getLayoutInflater());
    }
}
