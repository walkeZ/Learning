package com.gdcaihui.luckycoin.android.event;

import com.gdcaihui.luckycoin.android.entity.vo.MemberInfo;

/**
 * 吾日三省吾身：看脸，看秤，看余额。
 * Created by lanso on 2017/1/16.
 */
public interface ChangeHeaderEvent {
    void onChangeHeaderImageSuccess(MemberInfo memberInfo);
}
