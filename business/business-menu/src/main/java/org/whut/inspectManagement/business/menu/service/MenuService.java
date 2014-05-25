package org.whut.inspectManagement.business.menu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.menu.entity.Menu;
import org.whut.inspectManagement.business.menu.mapper.MenuMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunhui
 * Date: 14-5-23
 * Time: 上午1:50
 * To change this template use File | Settings | File Templates.
 */
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;

    public void add(Menu menu){
        menuMapper.add(menu);
    }
    public int delete(Menu menu){
        return menuMapper.delete(menu);
    }
    public int update(Menu menu){
        return menuMapper.update(menu);
    }


}