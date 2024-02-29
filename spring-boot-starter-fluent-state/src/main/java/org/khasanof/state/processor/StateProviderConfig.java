package org.khasanof.state.processor;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.config.Config;
import org.khasanof.enums.ProcessType;
import org.khasanof.StateConfigurerAdapter;
import org.khasanof.state.collector.StateConfigCollector;
import org.khasanof.state.configurer.StateConfigReader;
import org.khasanof.state.configurer.StateConfigurerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.state.processor
 * @since 23.07.2023 23:20
 */
@Slf4j
@Component
@SuppressWarnings({"unchecked", "rawtypes"})
public class StateProviderConfig implements Config {

    private final ApplicationContext applicationContext;
    private final StateConfigCollector configCollector;

    public StateProviderConfig(ApplicationContext applicationContext, StateConfigCollector configCollector) {
        this.applicationContext = applicationContext;
        this.configCollector = configCollector;
    }

    @Override
    public void runnable() {
        readStateConfig();
    }

    private void readStateConfig() {
        try {
            tryReadStateConfig();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void tryReadStateConfig() throws Exception {
        StateConfigurerAdapter adapter = applicationContext.getBean(StateConfigurerAdapter.class);
        StateConfigurerImpl configurer = new StateConfigurerImpl();
        adapter.configure(configurer);
        setStateCollector(configurer);
    }

    private void setStateCollector(StateConfigReader configReader) {
        configCollector.addInitial(configReader.getInitial());
        configCollector.addStates(configReader.getStates());
    }

    @Override
    public ProcessType processType() {
        return ProcessType.STATE;
    }
}
