package com.chaffari.kmeans.math.vecteurs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NuageVectorielTest {

    private NuageVectoriel nuage;

    @BeforeClass
    public static void setUpClass() {
        BasicConfigurator.configure();
    }

    @Before
    public void setUp() {
        nuage = new NuageVectoriel();
        nuage.add(new Vecteur(Arrays.asList(new BigDecimal[] { BigDecimal.valueOf(1.4d), BigDecimal.valueOf(2d), BigDecimal.valueOf(0d) })));
        nuage.add(new Vecteur(Arrays.asList(new BigDecimal[] { BigDecimal.valueOf(10d), BigDecimal.valueOf(2d), BigDecimal.valueOf(0d) })));
        nuage.add(new Vecteur(Arrays.asList(new BigDecimal[] { BigDecimal.valueOf(7d), BigDecimal.valueOf(2.8d), BigDecimal.valueOf(0d) })));
        nuage.add(new Vecteur(Arrays.asList(new BigDecimal[] { BigDecimal.valueOf(2d), BigDecimal.valueOf(2d), BigDecimal.valueOf(0d) })));
    }

    @Test
    public void testerMoyenne() {
        Vecteur moyenne = new Vecteur(Arrays.asList(new BigDecimal[] { BigDecimal.valueOf(5.1d), BigDecimal.valueOf(2.2d), BigDecimal.valueOf(0d) }));
        Assert.assertEquals(moyenne, nuage.getMoyenne());
    }

    @Test
    public void testerVariance() {
        Vecteur variance = new Vecteur(Arrays.asList(new BigDecimal[] { BigDecimal.valueOf(12.73d), BigDecimal.valueOf(0.12d), BigDecimal.valueOf(0d) }));
        Assert.assertEquals(variance, nuage.getVariance());
    }

    @Test
    public void testerEcartType() {
        Vecteur ecartType = new Vecteur(Arrays.asList(new BigDecimal[] { BigDecimal.valueOf(Math.sqrt(12.73d)), BigDecimal.valueOf(Math.sqrt(0.12d)), BigDecimal.valueOf(0d) }));
        Assert.assertEquals(ecartType, nuage.getEcartType());
    }

    @Test
    public void testerCentrer() {
        Vecteur m = nuage.getMoyenne();
        List<Vecteur> result = new ArrayList<>();
        for (Vecteur v : nuage) {
            result.add(v.soustraire(m));
        }
        nuage.centrer();
        for (int i = 0; i < nuage.size(); i++) {
            Assert.assertEquals(result.get(i), nuage.get(i));
        }
    }

    @Test
    public void testerReduire() {
        Vecteur e = nuage.getEcartType();
        List<Vecteur> result = new ArrayList<>();
        for (Vecteur v : nuage) {
            Vecteur r = new Vecteur(v.getCoordonnees().size());
            for (int i = 0; i < v.getCoordonnees().size(); i++) {
                if (BigDecimal.ZERO.compareTo(e.getCoordonnees().get(i)) != 0) {
                    r.getCoordonnees().set(i, v.getCoordonnees().get(i).divide(e.getCoordonnees().get(i), Vecteur.DIVISION_SCALE, RoundingMode.HALF_UP));
                }
            }
            result.add(r);
        }
        try {
            nuage.reduire();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail();
        }
        for (int i = 0; i < nuage.size(); i++) {
            Assert.assertEquals(result.get(i), nuage.get(i));
        }
    }
}
