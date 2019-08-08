package com.chaffari.kmeans.math.distances;

import com.chaffari.kmeans.math.vecteurs.Vecteur;

import java.math.BigDecimal;
import java.util.Arrays;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DistanceManhattanTest {

    @BeforeClass
    public static void setUp() {
        BasicConfigurator.configure();
    }

    @Test
    public void testerCalcul() {
        Vecteur v1 = new Vecteur(4);
        Vecteur v2 = new Vecteur(Arrays.asList(new BigDecimal[] { BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(-3), BigDecimal.valueOf(4) }));
        Assert.assertEquals(10d, DistanceManhattan.getInstance().calculer(v1, v2), 0.01d);
    }
}

