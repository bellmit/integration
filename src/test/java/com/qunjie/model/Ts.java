package com.qunjie.model;/**
 * Created by whs on 2020/12/25.
 */

import com.alibaba.fastjson.JSONArray;
import com.qunjie.crm.attendance.args.AttendanceModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.model.Ts
 *
 * @author whs
 *         Date:   2020/12/25  14:09
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
public class Ts {

    public static void main(String[] args) throws IOException {
        String s = "[\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_C594085CA8AD4ABED51355DE5F42143B\",\n" +
                "\t\t\"userName\": \"姚武俊\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615188125197,\n" +
                "\t\t\"checkinsAddressDesc\": \"中国安徽省合肥市肥西县彩虹路\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_EADB2F07480FD48D98DEF1054CCEC550\",\n" +
                "\t\t\"userName\": \"戈崇坤\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615175029652,\n" +
                "\t\t\"checkinsAddressDesc\": \"浙江省杭州市江干区新塘路\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_23988C9F8FC621310C3391A22C29D451\",\n" +
                "\t\t\"userName\": \"李阳\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615193852434,\n" +
                "\t\t\"checkinsAddressDesc\": \"云南省昆明市五华区茭菱路389号附近云南省高创园\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_23988C9F8FC621310C3391A22C29D451\",\n" +
                "\t\t\"userName\": \"李阳\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615185225691,\n" +
                "\t\t\"checkinsAddressDesc\": \"云南省昆明市西山区丹霞路198号昆明市新闻中心\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_23988C9F8FC621310C3391A22C29D451\",\n" +
                "\t\t\"userName\": \"李阳\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615166537795,\n" +
                "\t\t\"checkinsAddressDesc\": \"云南省昆明市官渡区吴井路183号百富琪商业广场B座\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_350B34F65BFA575CD49820AF05A884B7\",\n" +
                "\t\t\"userName\": \"刘飞\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615186806938,\n" +
                "\t\t\"checkinsAddressDesc\": \"中国北京市西城区广莲路1号建工大厦\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_66C3452BCDC8FE9055AF0253B7C1E123\",\n" +
                "\t\t\"userName\": \"杨文涛\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615161199693,\n" +
                "\t\t\"checkinsAddressDesc\": \"中国广东省佛山市顺德区工业大道43号腾越建筑工程有限公司\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_5E5FC01F16460869335281281B05ED79\",\n" +
                "\t\t\"userName\": \"范高星\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615166106348,\n" +
                "\t\t\"checkinsAddressDesc\": \"中国河南省郑州市中原区泾河路紫牛大厦\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_DCC32C93B1C356A1C36318C911047D40\",\n" +
                "\t\t\"userName\": \"何树祯\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615183128300,\n" +
                "\t\t\"checkinsAddressDesc\": \"中国山东省济南市历城区经十东路33399号水发集团\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_DCC32C93B1C356A1C36318C911047D40\",\n" +
                "\t\t\"userName\": \"何树祯\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615169337347,\n" +
                "\t\t\"checkinsAddressDesc\": \"中国山东省济南市历下区泉城路320号\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_79813623B7635AE1748EACA45C2C3A85\",\n" +
                "\t\t\"userName\": \"刘欢\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615188498635,\n" +
                "\t\t\"checkinsAddressDesc\": \"中国广西壮族自治区南宁市西乡塘区高新大道62号南宁高新区生物医药产业园\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_79813623B7635AE1748EACA45C2C3A85\",\n" +
                "\t\t\"userName\": \"刘欢\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615176006121,\n" +
                "\t\t\"checkinsAddressDesc\": \"中国云南省文山壮族苗族自治州富宁县G323富宁站\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_4C97EBD05B7AFABE4F65B78EADEC5292\",\n" +
                "\t\t\"userName\": \"朱余清\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615193280863,\n" +
                "\t\t\"checkinsAddressDesc\": \"中国广东省中山市翠澜道(中)奥渤雅家具\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_4C97EBD05B7AFABE4F65B78EADEC5292\",\n" +
                "\t\t\"userName\": \"朱余清\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615190002438,\n" +
                "\t\t\"checkinsAddressDesc\": \"中国广东省中山市和清路(中)戴思乐科技集团有限公司\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_4C97EBD05B7AFABE4F65B78EADEC5292\",\n" +
                "\t\t\"userName\": \"朱余清\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615182733543,\n" +
                "\t\t\"checkinsAddressDesc\": \"中国广东省中山市东镇东一路港义路25号创意港火炬高技术产业开发区\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_2F62F4973709D29409D80C0CF1F6EDEE\",\n" +
                "\t\t\"userName\": \"何伟乐\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615214683532,\n" +
                "\t\t\"checkinsAddressDesc\": \"中国湖北省武汉市洪山区雄楚大道746附4关西小区\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_2F62F4973709D29409D80C0CF1F6EDEE\",\n" +
                "\t\t\"userName\": \"何伟乐\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615162238985,\n" +
                "\t\t\"checkinsAddressDesc\": \"中国湖北省武汉市洪山区雄楚大道746附4关西小区\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_3CF407BA9BF1C47334BAE36F11AA0880\",\n" +
                "\t\t\"userName\": \"刘强\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615186837308,\n" +
                "\t\t\"checkinsAddressDesc\": \"中国北京市丰台区广安路15号\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_0986BA619630F47FC17C1132324EC904\",\n" +
                "\t\t\"userName\": \"张世辉\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615184754788,\n" +
                "\t\t\"checkinsAddressDesc\": \"四川省成都市双流区双流区机场路土桥段80号双流区公共资源交易服务中心\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_D6EC93289671F12E76433356D15B9007\",\n" +
                "\t\t\"userName\": \"王沛\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615169761210,\n" +
                "\t\t\"checkinsAddressDesc\": \"湖北省宜昌市西陵区兰台路19号兰台科技园\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_BAAAFBDA842CB42181E532120FC009C3\",\n" +
                "\t\t\"userName\": \"罗时飞\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615204615778,\n" +
                "\t\t\"checkinsAddressDesc\": \"湖北省武汉市江夏区大桥现代产业园新华街8号附26大桥新区人力资源和社会保障服务中心\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"openUserId\": \"FSUID_BAAAFBDA842CB42181E532120FC009C3\",\n" +
                "\t\t\"userName\": \"罗时飞\",\n" +
                "\t\t\"createDateStr\": null,\n" +
                "\t\t\"checkTime\": null,\n" +
                "\t\t\"checkType\": null,\n" +
                "\t\t\"locationType\": null,\n" +
                "\t\t\"locationException\": null,\n" +
                "\t\t\"deviceId\": null,\n" +
                "\t\t\"checkAddress\": null,\n" +
                "\t\t\"deviceException\": null,\n" +
                "\t\t\"systemException\": null,\n" +
                "\t\t\"checkinsTimeStamp\": 1615186602502,\n" +
                "\t\t\"checkinsAddressDesc\": \"湖北省武汉市洪山区珞喻路\"\n" +
                "\t}\n" +
                "]";

        List<AttendanceModel> attendanceModels = JSONArray.parseArray(s, AttendanceModel.class);
        List<AttendanceModel> listAll = new ArrayList<>();
        System.out.println(attendanceModels.size());
        Map<String, List<AttendanceModel>> collect = attendanceModels.stream().collect(Collectors.groupingBy(AttendanceModel::getUserName));
        collect.forEach((k,v)->{
            if (v != null && v.size()>2){
                AttendanceModel attendanceModelMax = v.stream().max(Comparator.comparing(AttendanceModel::getCheckinsTimeStamp)).get();
                AttendanceModel attendanceModelMin = v.stream().min(Comparator.comparing(AttendanceModel::getCheckinsTimeStamp)).get();
                listAll.add(attendanceModelMax);
                listAll.add(attendanceModelMin);
            }else {
                listAll.addAll(v);
            }
        });
        System.out.println(listAll.size());
        attendanceModels.removeAll(listAll);
        System.out.println(attendanceModels);
    }
}
