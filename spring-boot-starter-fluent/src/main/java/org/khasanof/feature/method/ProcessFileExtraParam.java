package org.khasanof.feature.method;

import org.khasanof.FluentBotSingletonBean;
import org.khasanof.context.FluentContextHolder;
import org.khasanof.context.FluentUpdate;
import org.khasanof.enums.DefaultMethodType;
import org.khasanof.feature.HandleMethodExtraParam;
import org.khasanof.models.invoker.InvokerParam;
import org.khasanof.models.invoker.SimpleInvoker;
import org.khasanof.utils.UpdateUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author Nurislom
 * @see org.khasanof.feature.method
 * @since 2/1/2024 10:28 PM
 */
@Component
public class ProcessFileExtraParam implements HandleMethodExtraParam {

    private final FluentBotSingletonBean fluentBotSingletonBean;

    public ProcessFileExtraParam(FluentBotSingletonBean fluentBotSingletonBean) {
        this.fluentBotSingletonBean = fluentBotSingletonBean;
    }

    @Override
    public void execute(SimpleInvoker simpleInvoker) {
        FluentUpdate currentUpdate = FluentContextHolder.getCurrentFluentUpdate();
        simpleInvoker.getParams().put(InvokerParam.ADDITIONAL_PARAM, getFileInputStream(currentUpdate));
    }

    private InputStream getFileInputStream(FluentUpdate currentUpdate) {
        return UpdateUtils.getInputStreamWithFileId(UpdateUtils.getFileId(currentUpdate.getUpdate()), fluentBotSingletonBean.getInstance());
    }

    @Override
    public MethodType methodType() {
        return DefaultMethodType.PROCESS_FILE;
    }
}
