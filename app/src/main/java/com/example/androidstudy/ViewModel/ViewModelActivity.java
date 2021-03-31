package com.example.androidstudy.ViewModel;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.androidstudy.R;
import com.example.androidstudy.StaticFun;

import java.util.List;

/**
 * ***前期遇到的坑***
 *
 * ViewModelProviders.of(FragmentActivity)
 * 因为fragmentActvity是Activity的子类，所以这种方式在Activity中是无法使用的
 *
 * getViewModelStore()
 * 看名字看像是用来储存viewmodel的地方，不生成ViewModelStore对象的话，ViewModel就无法正常使用
 *
 * onRetainNonConfigurationInstance
 * 这个是用来储存ViewModelStore的地方，官方文档只说了ViewModel可以在旋转屏幕的时候保存数据，
 * 可是实际操作发现并非如此，是因为在这里保存了ViewModelStroe，viewmodel也在其中。
 * 实现原理和onRetainNonConfigurationInstance该方法的属性有关
 *
 *
 * {@link androidx.appcompat.app.AppCompatActivity}
 * */
public class ViewModelActivity extends Activity
        implements
        LifecycleOwner,
        ViewModelStoreOwner,
        View.OnClickListener {
    private Lifecycle lifecycle ;
    private ViewModelStore mViewModelStore ;
    private MyViewModel model;

    private TextView textView;

    private StringBuilder stringBuilder;

    private int flag;
    private int mflag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        textView = StaticFun.textviewRegister(R.id.textview,this);
        textView.setOnClickListener(this);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());


        stringBuilder = new StringBuilder();

        stringBuilder.append("点击屏幕观察\n\n\n\n\n");

        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.

        ViewModelProvider viewModelProvider = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory());
        model = viewModelProvider.get(MyViewModel.class);

        model.getFlag().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                stringBuilder.append("viewmodle flag: "+integer+"\n")
                        .append("activity flag: "+flag)
                        .append("\n\n++++++++++++++++++++++++\n");
                mflag = integer;

            }
        });
        model.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                stringBuilder.append("*************** the information of user ***************\n\n\n");
                for(User user : users){
                    stringBuilder.append("name: "+user.getName()+"\n")
                            .append("height: "+user.getHeight()+"\n")
                            .append("weight: "+user.getWeight()+"\n");
                    if(user.isWoman()){
                        stringBuilder.append("sex: woman\n");
                    }else {
                        stringBuilder.append("sex: man\n");
                    }
                    stringBuilder.append("==================================\n");
                }
                stringBuilder.append("\n\n\n************************* end *************************");
                textView.setText(stringBuilder.toString());
            }
        });
        flag = 1;

    }

    @Override
    @Nullable
    public final Object onRetainNonConfigurationInstance() {

        ViewModelStore viewModelStore = mViewModelStore;
        if (viewModelStore == null) {
            // No one called getViewModelStore(), so see if there was an existing
            // ViewModelStore from our last NonConfigurationInstance
            NonConfigurationInstances nc =
                    (NonConfigurationInstances) getLastNonConfigurationInstance();
            if (nc != null) {
                viewModelStore = nc.viewModelStore;
            }
        }

        if (viewModelStore == null ) {
            return null;
        }

        NonConfigurationInstances nci = new NonConfigurationInstances();
        nci.viewModelStore = viewModelStore;
        return nci;
    }

    /**
     * 获取ViewModelStore
     *
     * 实际上就是在activity旋转的时候存储这个内容，viewmodel在其中
     * */
    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
//        if(viewModelStore == null)
//            viewModelStore = new ViewModelStore();
//        return viewModelStore;
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the "
                    + "Application instance. You can't request ViewModel before onCreate call.");
        }
        if (mViewModelStore == null) {
            NonConfigurationInstances nc =
                    (NonConfigurationInstances) getLastNonConfigurationInstance();
            if (nc != null) {
                // Restore the ViewModelStore from NonConfigurationInstances
                mViewModelStore = nc.viewModelStore;
            }
            if (mViewModelStore == null) {
                mViewModelStore = new ViewModelStore();
            }
        }
        return mViewModelStore;
    }

    static final class NonConfigurationInstances {
        ViewModelStore viewModelStore;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        if(lifecycle == null)
            lifecycle = new LifecycleRegistry(this);

        return lifecycle;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.textview){
            flag ++;
            model.addFlag();
            model.changeUser();

            if(mflag%2 == 0){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }

        }

    }

}
