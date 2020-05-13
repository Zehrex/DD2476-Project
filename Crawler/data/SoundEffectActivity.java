2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/ui/SoundEffectActivity.java
package com.mediaroom.ui;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mediaroom.R;
import com.mediaroom.adapter.SoundEffectAdapter;
import com.mediaroom.base.BaseActivity;
import com.mediaroom.base.BaseAdapter;
import com.mediaroom.bean.SoundMode;
import com.mediaroom.facade.RoomManager;
import com.mediaroom.utils.Constant;
import com.thunder.livesdk.ThunderRtcConstant;

import java.util.ArrayList;

/**
 * Sound page
 *
 * ZH:
 * 音效页面
 *
 * @author Aslan chenhengfei@yy.com
 * @since 2020/01/07
 */
public class SoundEffectActivity extends BaseActivity {

    private TextView mToolbarTitle;
    private SoundEffectAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_sound_effect;
    }

    @Override
    protected void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        toolbar.setTitle("");
        mToolbarTitle.setText(R.string.sound_effect);
        toolbar.setNavigationOnClickListener(v -> SoundEffectActivity.this.finish());

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new SoundEffectAdapter(new ArrayList<>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration
                .setDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.bg_grey)));
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                adapter.selecte(position);
                Constant.soundEffectPostion = position;

                SoundMode soundMode = adapter.getData().get(position);
                //The sound effect and voice change logic are mutually exclusive logic inside the SDK.
                //音效和变声逻辑，SDK内部为互斥逻辑。
                if (position <= 9) {
                    RoomManager.getInstance(SoundEffectActivity.this)
                            .setSoundEffect(soundMode.getValue());
                } else {
                    RoomManager.getInstance(SoundEffectActivity.this)
                            .setVoiceChanger(soundMode.getValue());
                }
            }
        });
    }

    @Override
    protected void initData() {
        initModeData();
        adapter.selecte(Constant.soundEffectPostion);
    }

    //Demo experience configuration
    //Demo 体验配置
    private void initModeData() {
        //The sound effect and voice change logic are mutually exclusive logic inside the SDK.
        //音效和变声逻辑，SDK内部为互斥逻辑。
        addModeData("关闭模式", ThunderRtcConstant.SoundEffectMode.THUNDER_SOUND_EFFECT_MODE_NONE);
        addModeData("VALLEY模式",
                ThunderRtcConstant.SoundEffectMode.THUNDER_SOUND_EFFECT_MODE_VALLEY);
        addModeData("R&B模式", ThunderRtcConstant.SoundEffectMode.THUNDER_SOUND_EFFECT_MODE_RANDB);
        addModeData("KTV模式", ThunderRtcConstant.SoundEffectMode.THUNDER_SOUND_EFFECT_MODE_KTV);
        addModeData("CHARMING模式",
                ThunderRtcConstant.SoundEffectMode.THUNDER_SOUND_EFFECT_MODE_CHARMING);
        addModeData("流行模式", ThunderRtcConstant.SoundEffectMode.THUNDER_SOUND_EFFECT_MODE_POP);
        addModeData("嘻哈模式", ThunderRtcConstant.SoundEffectMode.THUNDER_SOUND_EFFECT_MODE_HIPHOP);
        addModeData("摇滚模式", ThunderRtcConstant.SoundEffectMode.THUNDER_SOUND_EFFECT_MODE_ROCK);
        addModeData("演唱会模式", ThunderRtcConstant.SoundEffectMode.THUNDER_SOUND_EFFECT_MODE_CONCERT);
        addModeData("录音棚模式", ThunderRtcConstant.SoundEffectMode.THUNDER_SOUND_EFFECT_MODE_STUDIO);

        //The sound effect and voice change logic are mutually exclusive logic inside the SDK.
        //音效和变声逻辑，SDK内部为互斥逻辑。
        addModeData("空灵", ThunderRtcConstant.VoiceChangerMode.THUNDER_VOICE_CHANGER_MODE_ETHEREAL);
        addModeData("惊悚", ThunderRtcConstant.VoiceChangerMode.THUNDER_VOICE_CHANGER_MODE_THRILLER);
        addModeData("鲁班", ThunderRtcConstant.VoiceChangerMode.THUNDER_VOICE_CHANGER_MODE_LUBAN);
        addModeData("萝莉", ThunderRtcConstant.VoiceChangerMode.THUNDER_VOICE_CHANGER_MODE_LORIE);
        addModeData("大叔", ThunderRtcConstant.VoiceChangerMode.THUNDER_VOICE_CHANGER_MODE_UNCLE);
        addModeData("死肥仔", ThunderRtcConstant.VoiceChangerMode.THUNDER_VOICE_CHANGER_MODE_DIEFAT);
        addModeData("熊孩子", ThunderRtcConstant.VoiceChangerMode.THUNDER_VOICE_CHANGER_MODE_BADBOY);
        addModeData("魔兽农民",
                ThunderRtcConstant.VoiceChangerMode.THUNDER_VOICE_CHANGER_MODE_WRACRAFT);
        addModeData("重金属",
                ThunderRtcConstant.VoiceChangerMode.THUNDER_VOICE_CHANGER_MODE_HEAVYMETAL);
        addModeData("感冒", ThunderRtcConstant.VoiceChangerMode.THUNDER_VOICE_CHANGER_MODE_COLD);
        addModeData("重机械",
                ThunderRtcConstant.VoiceChangerMode.THUNDER_VOICE_CHANGER_MODE_HEAVYMECHINERY);
        addModeData("困兽",
                ThunderRtcConstant.VoiceChangerMode.THUNDER_VOICE_CHANGER_MODE_TRAPPEDBEAST);
        addModeData("强电流",
                ThunderRtcConstant.VoiceChangerMode.THUNDER_VOICE_CHANGER_MODE_POWERCURRENT);
    }

    private void addModeData(String name, int value) {
        adapter.addItem(new SoundMode(name, value));
    }
}
