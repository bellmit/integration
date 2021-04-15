package com.qunjie.sync.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.sync.service.SyncService
 *
 * @author whs
 * Date:   2020/12/22  11:20
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Slf4j
@Component
public class SyncService {

    @Autowired
    SyncDept syncDept;

    @Autowired
    SyncUser syncUser;

    @Autowired
    private UpdMapHandle updMapHandle;

    public static final String LocalUrl = "D:\\oa_crm\\fanweidepts.properties";

    /**
     * 1.从泛微获取分部信息，
     * 2.遍历分部信息， 将遍历到的分部id放在List<Integer>中，
     * 从mysql查询该分部是否有映射：A.有映射: 查询映射数据，
     * 	a.如果无数据，做无映射处理，修改该映射消息;
     * 	b.直接修改；修改时，先挂在总公司下，更新其他数据，然后放入待更新list ； 调用修改接口
     * B.无映射：父级分部信息为空的挂在总公司下，不加入待更新list，父级分部不为空的暂挂在总公司下，同时放入待更新List(因为部分分部 父级分部尚未同步，可能在部门映射库里找不到父级部门)，调用添加接口 同时把id映射更新到mysql数据库
     * 3.更新待更新list(即父级部门映射)，分部数据同步完成;
     *
     *
     * 4.遍历List<Integer>.根据分部id查询泛微该分部下的部门list，将部门id放入deptList中
     * 查询mysql中该部门是否有映射:A.无映射: a.父级部门为空的则将本分部映射后的crm部门id作为父级部门，存入数据库，并将本部门的id映射添加到mysql数据库
     * 			           b.父级部门不为空，则将本部暂挂在当前映射分部下，添加映射到mysql数据库，同时把该条数据放在待更新List
     * 		            B有映射: 查询映射数据：a.有数据：直接更改，先将该部门挂在当前分部下，更新其他字段，放入待更新List
     * 					b.无数据:  删除该映射，做无映射处理
     * 更新待更新list，部门同步完成；
     *
     *
     * 5.遍历部门deptList，根据部门id查询该部门下的人，UserList;
     * 遍历UserList，映射用户部门，
     * 通过用户id在mysql表中查该用户的映射：
     * 	A.无映射:用户做添加操作(上级领导给个默认值 FSUID_62CD79E40D9AD1DF1D1E02F28986E039)，并将该用户映射添加进数据库中；并将用户添加到待更新List
     * 	B.有映射：	a.通过映射查询该用户，如果为空，删除该映射，做无映射处理
     * 		b.不为空，判断需不需要修改，需要修改，则修改(上级领导给默认值FSUID_62CD79E40D9AD1DF1D1E02F28986E039)，并将这条单据添加到待更新List
     * 更新待更新List，修改上级领导;人员同步结束；
     *
     * @return
     */

    public int syncDeptAndUser(String userids) throws IOException {
        System.out.println("========================用户同步开始=================================");
        List<Integer> list = splitUserid(userids);
        List<String> depts = splitDeptnm(getDeptnms());
        syncDept.syncDept();
        syncUser.syncUser(list,depts);
        updMapHandle.handleUpdMap();
        return 1;
    }

    public int syncDept(){
        System.out.println("========================部门同步开始=======================");
        syncDept.syncDept();
        System.out.println("===========================部门同步结束，更新父级部门==========================");
        updMapHandle.handleUpdMap();
        System.out.println("================================同步部门结束！=====================================");
        return 1;
    }

    public int syncUser(String userids) throws IOException {
        System.out.println("========================用户同步开始=================================");
        List<Integer> list = splitUserid(userids);
        List<String> depts = splitDeptnm(getDeptnms());
        syncUser.syncUser(list,depts);
        System.out.println("========================用户同步结束！处理后续==========================");
        updMapHandle.handleUpdMap();
        System.out.println("=================后续处理完成!=======================");
        return 1;
    }

    private List<Integer> splitUserid(String userids){
        List<Integer> list = null;
        if (StringUtils.isNotEmpty(userids)){
            list = Arrays.asList(userids.split("[， 、 ,]")).stream().map(Integer::new).collect(Collectors.toList());
        }else {
            list = new ArrayList<>();
        }
        return list;
    }

    private List<String> splitDeptnm(String deptnms){
        List<String> depts = null;
        if (StringUtils.isNotEmpty(deptnms)){
            depts = Arrays.asList(deptnms.split("[， 、 ,]"));
        }else {
            depts = new ArrayList<>();
        }
        return depts;
    }

//    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void scheduledSyncUser() throws IOException {
        System.out.println("========================用户同步开始=================================");
        List<String> depts;
        String deptnms = getDeptnms();
        if (StringUtils.isNotEmpty(deptnms)) {
            depts = splitDeptnm(deptnms);
        }else {
            depts = new ArrayList<>();
        }
        syncUser.syncUser(new ArrayList<>(),depts);
        System.out.println("========================用户同步结束！处理后续==========================");
        updMapHandle.handleUpdMap();
        System.out.println("=================后续处理完成!=======================");
    }

    public String getDeptnms() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream= new FileInputStream(LocalUrl);
        prop.load(inputStream);
        String gbk = getProperty(prop, "fanwei.deptnms", "UTF-8");
        return gbk;
    }

    private String getProperty(Properties properties, String key, String encoding) throws UnsupportedEncodingException {
        //param check
        if (properties == null)
            return null;

        // 如果此时value是中文，则应该是乱码
        String value = properties.getProperty(key);
        if (value == null)
            return null;

        // 编码转换，从ISO8859-1转向指定编码
        value = new String(value.getBytes("ISO8859-1"), encoding);
        return value;
    }
}
