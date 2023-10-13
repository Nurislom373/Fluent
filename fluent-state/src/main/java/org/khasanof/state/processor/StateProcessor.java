package org.khasanof.state.processor;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.config.Config;
import org.khasanof.enums.ProcessType;
import org.khasanof.state.StateConfigurerAdapter;
import org.khasanof.state.collector.StateConfigCollector;
import org.khasanof.state.configurer.StateConfigReader;
import org.khasanof.state.configurer.StateConfigurerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.state.processor
 * @since 23.07.2023 23:20
 */
@Slf4j
@Component
public class StateProcessor implements Config {

    private final ApplicationContext applicationContext;
    private final StateConfigCollector configCollector;

    public StateProcessor(ApplicationContext applicationContext, StateConfigCollector configCollector) {
        this.applicationContext = applicationContext;
        this.configCollector = configCollector;
    }

    @Override
    public void runnable() {
        /*log.info("state processor start! ###");
        Map<String, StateConfigurerAdapter> configurerMap = applicationContext.getBeansOfType(StateConfigurerAdapter.class);
        if (configurerMap.size() != 1) {
            throw new RuntimeException("StateConfigurer must be one implement!");
        }
        StateConfigurerAdapter configurer = configurerMap.values().iterator().next();
        if (Objects.isNull(configurer.stateEnums()) || configurer.stateEnums().isEmpty()) {
            throw new RuntimeException("states is null or empty!");
        }
        StateEnumsCollector collector = applicationContext.getBean(StateEnumsCollector.NAME, StateEnumsCollector.class);
        collector.addAll(configurer.stateEnums());*/
        stateConfigReader();
    }

    @SuppressWarnings("unchecked")
    private void stateConfigReader() {
        try {
            StateConfigurerAdapter adapter = applicationContext.getBean(StateConfigurerAdapter.class);
            StateConfigurerImpl configurer = new StateConfigurerImpl();
            adapter.configure(configurer);
            setStateCollector(configurer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
