package org.whut.inspectManagement.business.inspectLocate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.inspectLocate.mapper.InspectLocateMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-7-11
 * Time: 上午10:16
 * To change this template use File | Settings | File Templates.
 */
public class InspectLocateService {
    @Autowired
    private InspectLocateMapper mapper;
    public List<Map<String,String>> getInspectLocateInfoByAppId(long appId){
        return mapper.getInspectLocateInfoByAppId(appId);
    }
}