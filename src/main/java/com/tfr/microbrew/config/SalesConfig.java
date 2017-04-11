package com.tfr.microbrew.config;

/**
 * Created by Erik on 4/11/2017.
 */
public interface SalesConfig {

    interface ProductVolumes {
        Double PINT = 0.125;
        Double FLIGHT = 0.25;
        Double HALF_GROWLER = 0.50;
        Double GROWLER = 1.0;
    }

    interface VolumeProbability {
        Double PINT = 0.70;
        Double FLIGHT = 0.15;
        Double HALF_GROWLER = 0.10;
        Double GROWLER = 0.05;
    }

    interface ProductProbability {
        Double STAPLE = 0.80;
        Double SEASONAL = 0.15;
        Double LIMITED = 0.05;
    }
}
