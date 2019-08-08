package com.chaffari.kmeans;

import java.math.BigDecimal;
import java.util.Arrays;

import com.chaffari.kmeans.math.distances.DistanceEuclidienne;
import com.chaffari.kmeans.math.vecteurs.NuageVectoriel;
import com.chaffari.kmeans.math.vecteurs.Vecteur;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;




public class KMeansTest {

    @BeforeClass
    public static void setUp() {
        BasicConfigurator.configure();
    }

    @Test
    public void testerKMeans() {
        NuageVectoriel points = new NuageVectoriel();
        Vecteur p1;
        Vecteur p2;
        for (int i = 1; i < 5; i++) {
            p1 = new Vecteur(Arrays.asList(new BigDecimal[] { BigDecimal.valueOf(1d), BigDecimal.valueOf(1d), BigDecimal.valueOf(1d) }));
            points.add(p1);
        }
        for (int i = 1; i < 5; i++) {
            p2 = new Vecteur(Arrays.asList(new BigDecimal[] { BigDecimal.valueOf(2d), BigDecimal.valueOf(2d), BigDecimal.valueOf(2d) }));
            points.add(p2);
        }
        for (int i = 1; i < 5; i++) {
            p2 = new Vecteur(Arrays.asList(new BigDecimal[] { BigDecimal.valueOf(1.2d), BigDecimal.valueOf(1.2d), BigDecimal.valueOf(1.2d) }));
            points.add(p2);
        }
        for (int i = 1; i < 5; i++) {
            p2 = new Vecteur(Arrays.asList(new BigDecimal[] { BigDecimal.valueOf(1.9d), BigDecimal.valueOf(1.9d), BigDecimal.valueOf(1.9d) }));
            points.add(p2);
        }
        points.melanger();
        try {
            KMeans kmeans = new KMeans(points, 2, DistanceEuclidienne.getInstance());
            kmeans.calculer();
            Assert.assertEquals(2, kmeans.getPartitions().size());
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
