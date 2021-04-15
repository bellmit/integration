package com.qunjie.crm.huikuan.event;

import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.huikuan.args.HuikuanDeleteDataArg;
import com.qunjie.crm.huikuan.args.HuikuanInvalidArg;
import com.qunjie.crm.manager.impl.HuikuanManagerImpl;
import org.springframework.context.ApplicationEvent;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.huikuan.event.CrmHuikuanEvent
 *
 * @author whs
 * Date:   2021/3/1  16:44
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */

public class CrmHuikuanEvent extends ApplicationEvent {

    public List<String> AddSuccessIds;

    public CrmHuikuanEvent(Object source) {
        super(source);
    }

    public CrmHuikuanEvent(Object source, List<String> addSuccessIds) {
        super(source);
        AddSuccessIds = addSuccessIds;
    }

    public void deleteHuikuan() throws AccessTokenException {
        if (!CollectionUtils.isEmpty(AddSuccessIds)){
            HuikuanManagerImpl bean = SpringBeanUtils.getBean(HuikuanManagerImpl.class);
            AddSuccessIds.forEach(id->{
                try {
                    this.huikuanInvalid(id,bean);
                } catch (AccessTokenException ex) {
                    ex.printStackTrace();
                }
            });
            this.huikuanDelete(AddSuccessIds,bean);
        }
    }

    public BaseResult huikuanDelete(List<String> list,HuikuanManagerImpl huikuanManagerImpl) throws AccessTokenException {
        HuikuanDeleteDataArg.HuikuanDeleteModel data = new HuikuanDeleteDataArg.HuikuanDeleteModel();
        data.setIdList(list);
        return huikuanManagerImpl.deletePayment(data);
    }

    public BaseResult huikuanInvalid(String dataId,HuikuanManagerImpl huikuanManagerImpl) throws AccessTokenException{
        HuikuanInvalidArg.HuikuanInvalidData data = new HuikuanInvalidArg.HuikuanInvalidData();
        data.setObject_data_id(dataId);
        return huikuanManagerImpl.invalidPayment(data);
    }
}
