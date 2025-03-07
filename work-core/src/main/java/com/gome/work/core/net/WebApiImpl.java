
package com.gome.work.core.net;


import android.text.TextUtils;

import com.gome.work.core.model.AccessTokenInfo;
import com.gome.work.core.model.AdBean;
import com.gome.work.core.model.BannerBean;
import com.gome.work.core.model.BaseRspInfo;
import com.gome.work.core.model.CaptchaItem;
import com.gome.work.core.model.FeedBackBean;
import com.gome.work.core.model.PostSearchPartnerItem;
import com.gome.work.core.model.PostUserInfo;
import com.gome.work.core.model.RegionItem;
import com.gome.work.core.model.RequestDataInfo;
import com.gome.work.core.model.RequestGrantBean;
import com.gome.work.core.model.SearchPartnerItem;
import com.gome.work.core.model.SysCfgData;
import com.gome.work.core.model.UploadFileResultInfo;
import com.gome.work.core.model.UserInfo;
import com.gome.work.core.model.UsersRspInfo;
import com.gome.work.core.model.im.EditGroupInfo;
import com.gome.work.core.model.im.GroupInfo;
import com.gome.work.core.model.im.GroupMemberInfo;
import com.gome.work.core.model.im.GroupNoticeBean;
import com.gome.work.core.model.im.GroupQrcode;
import com.gome.work.core.model.im.GroupSetRequestInfo;
import com.gome.work.core.model.schedule.ScheduleInfo;
import com.gome.work.core.model.schedule.ScheduleRemindInfo;
import com.gome.work.core.net.api.ApiService;
import com.gome.work.core.upload.FileRequestBody;
import com.gome.work.core.upload.IUploadListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class WebApiImpl extends WebApi {

    private ApiService service;

    public WebApiImpl() {
        service = GomeOARetrofitManager.getInstance().getService(ApiService.class);
    }

    @Override
    public void login(String account, String password, String loginType, IResponseListener<AccessTokenInfo> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("username", account);
        params.put("password", password);
        params.put("loginType", loginType);
//        SharedPreferencesHelper.commitString(Constants.PreferKeys.REQUEST_TOKEN, base64Str);
        Call<BaseRspInfo<AccessTokenInfo>> result = service.login(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void register(String account, String password, String captcha, IResponseListener<UserInfo> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("username", account);
        params.put("password", password);
        params.put("code", captcha);
        Call<BaseRspInfo<UserInfo>> result = service.register(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getCityData(IResponseListener<List<RegionItem>> listener) {
        Call<BaseRspInfo<List<RegionItem>>> result = service.getCity(3);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getCaptcha(String phoneNum, IResponseListener<CaptchaItem> listener) {
        Call<BaseRspInfo<CaptchaItem>> result = service.getCaptcha(phoneNum);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getRecommendTeachers(String posCode, double latitude, double longitude, IResponseListener<UsersRspInfo> listener) {

    }

    @Override
    public void getBanner(String pos, IResponseListener<List<BannerBean>> listener) {
        Call<BaseRspInfo<List<BannerBean>>> result = service.getBanner(pos);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getAd(IResponseListener<List<AdBean>> listener) {
        Call<BaseRspInfo<List<AdBean>>> result = service.getAd();
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getLauncherPic(IResponseListener<List<AdBean>> listener) {
        Call<BaseRspInfo<List<AdBean>>> result = service.getLauncherPic();
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void forgetPassword(String username, String newPwd, String captcha, IResponseListener<String> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("identity", username);
        params.put("password", newPwd);
        params.put("code", captcha);
        Call<BaseRspInfo<String>> result = service.forgetPassword(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void modifyPassword(String originalPwd, String newPwd, IResponseListener<String> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("oldPassword", originalPwd);
        params.put("newPassword", newPwd);
        Call<BaseRspInfo<String>> result = service.modifyPassword(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getConfigDataDic(String type, IResponseListener<SysCfgData> listener) {
        Call<BaseRspInfo<SysCfgData>> result = service.getConfigDataDic(type);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void postUserInfo(PostUserInfo userInfo, IResponseListener<String> listener) {
        Call<BaseRspInfo<String>> result = service.postUserInfo(userInfo);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void postSearchPartnerItem(PostSearchPartnerItem dataItem, IResponseListener<String> listener) {
        Call<BaseRspInfo<String>> result = service.postSearchPartnerInfo(dataItem);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getSearchPartnerList(int pageIndex, int pageSize, String courseCode, IResponseListener<SearchPartnerItem.ResponseWrapper> listener) {
        Map<String, Object> params = new HashMap<>();
        params.put("pn", pageIndex);
        params.put("ps", pageSize);
        if (!TextUtils.isEmpty(courseCode)) {
            params.put("discipline", courseCode);
        }
        Call<BaseRspInfo<SearchPartnerItem.ResponseWrapper>> result = service.getSearchPartnerList(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getTeacherList(int pageIndex, int pageSize, String courseCode, IResponseListener<UsersRspInfo> listener) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", pageIndex);
        params.put("pageSize", pageSize);
        if (!TextUtils.isEmpty(courseCode)) {
            params.put("discipline", courseCode);
        }
        Call<BaseRspInfo<UsersRspInfo>> result = service.getTeacherList(params);
        result.enqueue(new MyCallback(listener));
    }


    @Override
    public void getUserInfo(String userId, IResponseListener<UserInfo> listener) {
        Call<BaseRspInfo<UserInfo>> result = service.getUserInfo(userId);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public Response<BaseRspInfo<UserInfo>> getUserInfoSyn(String userId) {
        Call<BaseRspInfo<UserInfo>> result = service.getUserInfo(userId);
        try {
            return result.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void follow(String userId, IResponseListener<String> listener) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", userId);
        Call<BaseRspInfo<String>> result = service.follow(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void followCancel(String userId, IResponseListener<String> listener) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", userId);
        Call<BaseRspInfo<String>> result = service.followCancel(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void partner(String userId, IResponseListener<String> listener) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", userId);
        Call<BaseRspInfo<String>> result = service.partner(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void partnerCancel(String userId, IResponseListener<String> listener) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", userId);
        Call<BaseRspInfo<String>> result = service.partnerCancel(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getPartners(int pageIndex, int pageSize, IResponseListener<UsersRspInfo> listener) {
        Call<BaseRspInfo<UsersRspInfo>> result = service.getPartners(pageIndex,pageSize);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getFriends(int pageIndex, int pageSize, IResponseListener<UsersRspInfo> listener) {
        Call<BaseRspInfo<UsersRspInfo>> result = service.getFriends(pageIndex, pageSize);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getFollowers(int pageIndex, int pageSize, IResponseListener<UsersRspInfo> listener) {
        Call<BaseRspInfo<UsersRspInfo>> result = service.getFollowers(pageIndex, pageSize);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getMySearchPartnerList(int pageIndex, int pageSize, IResponseListener<SearchPartnerItem.ResponseWrapper> listener) {
        Map<String, Object> params = new HashMap<>();
        params.put("pn", pageIndex);
        params.put("ps", pageSize);
        Call<BaseRspInfo<SearchPartnerItem.ResponseWrapper>> result = service.getMyPostSearchPartnerList(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getScheduleList(IResponseListener<String> listener) {
        Call<BaseRspInfo<String>> result = service.getScheduleList();
        result.enqueue(new MyCallback(listener));
    }


    @Override
    public void commitCommentData(String appId, int score, String content, IResponseListener<String> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("appId", appId);
        params.put("score", score + "");
        params.put("content", content);
        Call<BaseRspInfo<String>> result = service.submitCommentData(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getAppFavoriteData(String appId, IResponseListener<String> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("appId", appId);
        Call<BaseRspInfo<String>> result = service.getAppFavoriteData(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void cancelAppFavoriteData(String appId, IResponseListener<String> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("appId", appId);
        Call<BaseRspInfo<String>> result = service.cancelAppFavoriteData(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getAppPraiseData(String appId, IResponseListener<String> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("appId", appId);
        Call<BaseRspInfo<String>> result = service.getAppPraisData(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void cancelAppPraiseData(String appId, IResponseListener<String> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("appId", appId);
        Call<BaseRspInfo<String>> result = service.cancelAppPraisData(params);
        result.enqueue(new MyCallback(listener));
    }


    @Override
    public void uploadFile(File file, IUploadListener<UploadFileResultInfo> listener) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        RequestBody requestProgressFile = new FileRequestBody(requestFile, listener);
        MultipartBody.Part body = MultipartBody.Part.createFormData("ufile", file.getName(), requestProgressFile);
        Call<BaseRspInfo<UploadFileResultInfo>> result = service.uploadFile(body);
        result.enqueue(new MyCallback(listener));

    }

    @Override
    public void getIMChatGroupMemberList(String groupId, long lastPullTimestamp, int status, int page, int pageSize, IResponseListener<GroupMemberInfo> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("groupId", groupId);
        params.put("lastPullTimestamp", lastPullTimestamp + "");
        params.put("status", status + "");
        params.put("page", page + "");
        params.put("pageSize", pageSize + "");
        Call<BaseRspInfo<GroupMemberInfo>> result = service.getIMChatGroupMenberList(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getImGroupInfo(String groupId, IResponseListener<GroupInfo> listener) {
        Call<BaseRspInfo<GroupInfo>> result = service.getImGroupInfo(groupId);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getImGroupInfoByQrCode(String qrcode, IResponseListener<GroupInfo> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("qrCode", qrcode);
        Call<BaseRspInfo<GroupInfo>> result = service.getImGroupInfoByQrcode(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void imCreateGroupChat(String groupName, List<UserInfo> list, IResponseListener<GroupInfo> listener) {
        if (list != null && !list.isEmpty()) {
            List<EditGroupInfo> groupInfoList = new ArrayList<>();
            for (UserInfo info : list) {
                EditGroupInfo editGroupInfo = new EditGroupInfo();
                editGroupInfo.setUserId(info.getId());
                editGroupInfo.setNickName(info.getNickname());
                groupInfoList.add(editGroupInfo);
            }
            GroupSetRequestInfo info = new GroupSetRequestInfo();
            info.setGroupName(groupName);
            info.setMemberInfos(groupInfoList);
            Call<BaseRspInfo<GroupInfo>> result = service.imCreateGroupChat(info);
            result.enqueue(new MyCallback(listener));
        }

    }

    @Override
    public void imUpdateGroupChatName(String groupName, String groupId, IResponseListener<GroupInfo> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("groupName", groupName);
        params.put("groupId", groupId);
        Call<BaseRspInfo<GroupInfo>> result = service.imUpdateGroupChatName(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void imGroupAddMembers(String groupId, List<UserInfo> list, IResponseListener<String> listener) {
        if (list != null && !list.isEmpty()) {
            List<EditGroupInfo> groupInfoList = new ArrayList<>();
            for (UserInfo info : list) {
                EditGroupInfo editGroupInfo = new EditGroupInfo();
                editGroupInfo.setUserId(info.getId());
                editGroupInfo.setNickName(info.getNickname());
                groupInfoList.add(editGroupInfo);
            }
            GroupSetRequestInfo bean = new GroupSetRequestInfo();
            bean.setGroupId(groupId);
            bean.setMemberInfos(groupInfoList);
            Call<BaseRspInfo<String>> result = service.imGroupAddMembers(bean);
            result.enqueue(new MyCallback(listener));
        }
    }

    @Override
    public void imJoinGroup(String groupId, String nickName, IResponseListener<String> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("groupId", groupId);
        params.put("nickName", nickName);
        Call<BaseRspInfo<String>> result = service.imJoinGroup(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void imQuitGroup(String groupId, IResponseListener<String> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("groupId", groupId);
        Call<BaseRspInfo<String>> result = service.imQuitGroup(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void imRemoveMemberByGroup(String groupId, List<UserInfo> list, IResponseListener<String> listener) {
        if (list != null && !list.isEmpty()) {
            List<EditGroupInfo> groupInfoList = new ArrayList<>();
            for (UserInfo info : list) {
                EditGroupInfo editGroupInfo = new EditGroupInfo();
                editGroupInfo.setUserId(info.getId());
                editGroupInfo.setNickName(info.getNickname());
                groupInfoList.add(editGroupInfo);
            }
            GroupSetRequestInfo bean = new GroupSetRequestInfo();
            bean.setGroupId(groupId);
            bean.setMemberInfos(groupInfoList);
            Call<BaseRspInfo<String>> result = service.imRemoveMemberByGroup(bean);
            result.enqueue(new MyCallback(listener));
        }
    }

    @Override
    public void imDissolveGroup(String groupId, String nickName, IResponseListener<String> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("groupId", groupId);
//        params.put("nickName", nickName);
        Call<BaseRspInfo<String>> result = service.imDissolveGroup(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void imCreateGroupQrcode(String groupId, IResponseListener<GroupQrcode> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("groupId", groupId);
        Call<BaseRspInfo<GroupQrcode>> result = service.imCreateGroupQrcode(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void imJoinGroupByQrcode(String qrCode, IResponseListener<GroupInfo> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("qrCode", qrCode);
        Call<BaseRspInfo<GroupInfo>> result = service.imJoinGroupByQrcode(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void imAlterationGroupOwner(String groupId, String imUserId, IResponseListener<String> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("groupId", groupId);
        params.put("imUserId", imUserId);
        Call<BaseRspInfo<String>> result = service.imAlterationGroupOwner(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void imModifyGroupNotice(String groupId, String noticeContent, IResponseListener<String> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("groupId", groupId);
        params.put("noticeContent", noticeContent);
        Call<BaseRspInfo<String>> result = service.imModifyGroupNotice(params);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void imGetGroupNotice(String groupId, IResponseListener<GroupNoticeBean> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("groupId", groupId);
        Call<BaseRspInfo<GroupNoticeBean>> result = service.imGetGroupNotice(params);
        result.enqueue(new MyCallback(listener));
    }


    @Override
    public void getRequestGrant(String requestToken, IResponseListener<RequestGrantBean> listener) {
        Map<String, String> map = new HashMap<>();
        map.put("request_token", requestToken);
        Call<BaseRspInfo<RequestGrantBean>> result = service.getRequestGrant(map);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void grantAccessToken(String requestToken, String captcha, IResponseListener<RequestGrantBean> listener) {
        Map<String, String> map = new HashMap<>();
        map.put("request_token", requestToken);
        map.put("captcha", captcha);
        Call<BaseRspInfo<RequestGrantBean>> result = service.GrantAccessToken(map);
        result.enqueue(new MyCallback(listener));
    }

    @Override
    public void getRequestGeantLogin(String requestToken, String captcha, IResponseListener<AccessTokenInfo> listener) {
        Map<String, String> map = new HashMap<>();
        map.put("request_token", requestToken);
        map.put("captcha", captcha);
        Call<BaseRspInfo<AccessTokenInfo>> result = service.getRequestGeantLogin(map);
        result.enqueue(new MyCallback(listener));
    }


    @Override
    public void saveOrUpdateSchedule(ScheduleInfo data, IResponseListener<String> listener) {
        Map<String, Object> params = new HashMap<>();
        if (data.getId() > 0) {
            params.put("id", data.getId());
        }
        params.put("userId", data.getUserId());
        params.put("title", data.getTitle());
        params.put("type", data.getType());
        params.put("scheduleTime", data.getScheduleTime());
        params.put("remindValue", data.getRemindValue());
        params.put("validType", data.getValidType());
        if (data.getValidType() > 1) {
            params.put("validValue", data.getValidValue());
        }
        Call<BaseRspInfo<String>> call = service.saveOrUpdateSchedule(params);
        call.enqueue(new MyCallback(listener));
    }

    @Override
    public void getScheduleDetail(int id, IResponseListener<ScheduleInfo> listener) {
        Call<BaseRspInfo<ScheduleInfo>> call = service.getScheduleDetail(id);
        call.enqueue(new MyCallback(listener));
    }

    @Override
    public void getScheduleRemindMinute(IResponseListener<List<ScheduleRemindInfo>> listener) {
        Call<BaseRspInfo<List<ScheduleRemindInfo>>> call = service.getScheduleRemindMinute();
        call.enqueue(new MyCallback(listener));
    }

    @Override
    public void deleteScheduleById(int id, IResponseListener<String> listener) {
        Call<BaseRspInfo<String>> call = service.deleteScheduleById(id);
        call.enqueue(new MyCallback(listener));
    }

    @Override
    public void submitFeedback(FeedBackBean feedBackBean, IResponseListener<String> listener) {
        RequestDataInfo<FeedBackBean> requestDataInfo = new RequestDataInfo<>();
        requestDataInfo.setData(feedBackBean);
        Call<BaseRspInfo<String>> call = service.submitFeedBack(requestDataInfo);
        call.enqueue(new MyCallback(listener));
    }


    static class MyCallback<T> implements Callback<BaseRspInfo<T>> {

        public IResponseListener<T> listener;

        public MyCallback(IResponseListener<T> listener) {
            this.listener = listener;
        }

        @Override
        public void onResponse(Call<BaseRspInfo<T>> call, retrofit2.Response<BaseRspInfo<T>> response) {
            if (listener != null) {
                if (response.isSuccessful()) {
                    BaseRspInfo baseRspInfo = response.body();
                    if (baseRspInfo.isSuccess()) {
                        listener.onSuccess(response.body().data);
                    } else {
                        listener.onError(baseRspInfo.code, baseRspInfo.message);
                    }

                } else {
                    listener.onError(String.valueOf(response.code()), response.message());
                }
            }
        }

        @Override
        public void onFailure(Call<BaseRspInfo<T>> call, Throwable t) {
            if (listener != null) {
                listener.onError("-1", t.getMessage());
            }
        }
    }


}
