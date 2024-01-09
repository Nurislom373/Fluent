package org.khasanof;

import org.khasanof.executors.determination.DeterminationService;
import org.khasanof.simulate.SimulateDeterminationUpdate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/9/2024 9:37 PM
 */
@Configuration
public class SimulateDeterminationUpdateConfiguration {

    @Bean
    public SimulateDeterminationUpdate simulateDeterminationUpdate(DeterminationService determinationService) {
        return new SimulateDeterminationUpdate(determinationService);
    }

}
