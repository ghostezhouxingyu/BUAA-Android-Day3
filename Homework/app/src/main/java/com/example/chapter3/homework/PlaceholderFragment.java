package com.example.chapter3.homework;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TO;DO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        ListView listView = view.findViewById(R.id.listview);

        String data[] = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.listview, data);
        listView.setAdapter(arrayAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                LottieAnimationView lv = getView().findViewById(R.id.loading);
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(
                        lv,
                        "alpha",
                        1.0f, 0f
                );
                alphaAnimator.setInterpolator(new LinearInterpolator());
                alphaAnimator.setDuration(500);

                ListView listView = getView().findViewById(R.id.listview);
                ObjectAnimator alphaAnimator2 = ObjectAnimator.ofFloat(
                        listView,
                        "alpha",
                        0f, 1.0f
                );
                alphaAnimator2.setInterpolator(new LinearInterpolator());
                alphaAnimator2.setDuration(500);

                AnimatorSet as = new AnimatorSet();
                as.playTogether(alphaAnimator, alphaAnimator2);
                as.start();
            }
        }, 5000);
    }
}


