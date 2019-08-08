package com.chaffari.kmeans.math.distances;
import com.chaffari.kmeans.math.vecteurs.Vecteur;

import java.math.BigDecimal;

/**
 * Classe permettant de définir la distance de manhattan (basée sur la valeur absolue)
 * @author chaffari
 */
public class DistanceManhattan implements IDistance {

    private static IDistance instance = new DistanceManhattan();

    private DistanceManhattan() {
        super();
    }

    @Override
    public double calculer(Vecteur p1, Vecteur p2) {
        double res = 0;
        for (int i = 0; i < p1.getCoordonnees().size(); i++) {
            BigDecimal d1 = p1.getCoordonnees().get(i).add(p2.getCoordonnees().get(i).negate());
            if (d1.signum() < 0) {
                d1 = d1.negate();
            }
            res += d1.doubleValue();
        }
        return res;
    }

    /**
     * Méthode d'accès à l'instance de la classe {@link DistanceManhattan}
     * @return l'instance de la classe {@link DistanceManhattan}
     */
    public static IDistance getInstance() {
        return instance;
    }
}
