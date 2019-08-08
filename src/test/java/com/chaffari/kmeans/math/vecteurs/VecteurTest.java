package com.chaffari.kmeans.math.vecteurs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VecteurTest {

    @BeforeClass
    public static void setUp() {
        BasicConfigurator.configure();
    }

    @Test
    public void testerConstructeurDimension() {
        List<BigDecimal> l = new ArrayList<>();
        l.add(BigDecimal.valueOf(0));
        l.add(BigDecimal.valueOf(0));
        l.add(BigDecimal.valueOf(0));
        Vecteur p = new Vecteur(3);
        Assert.assertEquals(3, p.getCoordonnees().size());
        Assert.assertEquals(l, p.getCoordonnees());
        Assert.assertEquals(new Vecteur(l), p);
    }

    @Test
    public void testerAddition() {
        Vecteur p = new Vecteur(1, 2, 3);
        Vecteur res = p.ajouter(p);
        Assert.assertEquals(new Vecteur(2, 4, 6), res);
    }

    @Test
    public void testerSoustraction() {
        Assert.assertEquals(new Vecteur(1, 1, 1), new Vecteur(2, 3, 4).soustraire(new Vecteur(1, 2, 3)));
    }

    @Test
    public void testerMultiplication() {
        Vecteur p = new Vecteur(2, 3, 4);
        Vecteur res = p.multiplier(2d);
        Assert.assertEquals(new Vecteur(4, 6, 8), res);
    }

    @Test
    public void testerIsOrigine() {
        Assert.assertEquals(false, new Vecteur(4, 6, 8).isOrigine());
        Assert.assertEquals(true, new Vecteur(0, 0, 0).isOrigine());
    }
}
