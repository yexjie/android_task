package com.example.AndroidTask.MainFram.TakePhotoFram;

import androidx.lifecycle.ViewModelProviders;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.AndroidTask.EnterFram.RegisterActivity;
import com.example.cq_1014_task.R;

public class TakephotoFragment extends Fragment {
    private TakephotoViewModel mViewModel;
    private ImageButton openCamera;
    private RadioButton rb_safe,rb_clean,rb_order,rb_important,rb_normal;
    private RadioGroup rg_content,rg_important;
    private TextView t_tag_question;
    private TextView t_tag_important;
    private Button open_map;

    private void setListeners(){
        OnClick onClick=new OnClick();
        rb_safe.setOnClickListener(onClick);
        rb_clean.setOnClickListener(onClick);
        rb_normal.setOnClickListener(onClick);
        rb_important.setOnClickListener(onClick);
        rb_order.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rb_safe:
                    t_tag_question.setText("问题类型是：安全隐患");
                    break;
                case R.id.rb_clean:
                    t_tag_question.setText("问题类型是：卫生问题");
                    break;
                case R.id.rb_order:
                    t_tag_question.setText("问题类型是：秩序问题");
                    break;
                case R.id.rb_important:
                    t_tag_important.setText("问题重要性是：重要");
                    break;
                case R.id.rb_normal:
                    t_tag_important.setText("问题重要性是：一般");
                    break;
            }
        }
    }

    public ImageButton getImageButton(){
        return openCamera;
    }

    public static TakephotoFragment newInstance() {
        return new TakephotoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.takephoto_fragment, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    TextView textView=getView().findViewById(R.id.textView);
                    //处理代码在此地
                    String str= bundle.getString("renturnLocation");
                    textView.setText(str);
                }
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TakephotoViewModel.class);// TODO: Use the ViewModel

        rb_safe=(RadioButton) getView().findViewById(R.id.rb_safe);
        openCamera = (ImageButton) getView().findViewById(R.id.img_camera);
        rb_clean=(RadioButton) getView().findViewById(R.id.rb_clean);
        rb_normal=(RadioButton) getView().findViewById(R.id.rb_normal);
        rb_safe=(RadioButton) getView().findViewById(R.id.rb_safe);
        rb_order=(RadioButton) getView().findViewById(R.id.rb_order);
        rb_important=(RadioButton) getView().findViewById(R.id.rb_important);
        rg_content=(RadioGroup) getView().findViewById(R.id.rg_content);
        rg_important=(RadioGroup) getView().findViewById(R.id.rg_important);
        open_map=(Button) getView().findViewById(R.id.openMap);

        t_tag_question=(TextView) getView().findViewById(R.id.t_tag_question);
        t_tag_important=(TextView) getView().findViewById(R.id.t_tag_important);
        openCamera.setImageDrawable(getResources().getDrawable(R.drawable.add));
        //拍照
        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent();
                intent2.setClass(getActivity().getApplicationContext(), CameraActivity.class);
                Bundle bundle = new Bundle();
                intent2.putExtras(bundle);
                startActivityForResult(intent2, 2);
            }
        });
        //地图
        open_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent();
                intent2.setClass(getActivity().getApplicationContext(),OpenMap.class);
                Bundle bundle = new Bundle();
                intent2.putExtras(bundle);
                startActivityForResult(intent2, 1);
            }
        });
        Intent intent=getActivity().getIntent();
        //if(intent==null){
        openCamera.setImageDrawable(getResources().getDrawable(R.drawable.add));
        //}
        if(intent!=null){
            Bitmap bitmap=intent.getParcelableExtra("bitmap");
            /*FragmentManager fm = getActivity().getFragmentManager();
            //注意v4包的配套使用
            Fragment fragment = new 目标fragment();
            fm.beginTransaction().replace(容器控件id,fragment).commit();*/
            openCamera.setImageBitmap(bitmap);
        }
        if(intent!=null){
            Uri uri = intent.getParcelableExtra("uri");
            openCamera.setImageURI(uri);
        }
        setListeners();

    }


}
