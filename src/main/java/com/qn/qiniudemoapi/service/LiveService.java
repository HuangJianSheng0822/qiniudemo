package com.qn.qiniudemoapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qn.qiniudemoapi.pojo.Live;
import com.qn.qiniudemoapi.util.ResponseDataStructure;

public interface LiveService extends IService<Live> {

    ResponseDataStructure getLiveActiveList();

    /**
     * 有新用户加入直播
     * @param liveId 房间号
     * @param ip ip
     * @return r
     */
    ResponseDataStructure hasNewPeople(String liveId,String ip);

    /**
     * 获取在线用户数量
     * @param liveId 用户id
     * @return r
     */
    ResponseDataStructure getPeopleCount(String liveId);

    /**
     * 用户退出直播间
     * @param liveId liveid
     * @param ip ip
     * @return r
     */
    ResponseDataStructure exitLive(String liveId,String ip);


}
