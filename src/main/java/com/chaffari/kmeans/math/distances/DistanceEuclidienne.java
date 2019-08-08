package com.chaffari.kmeans.math.distances;
import com.chaffari.kmeans.math.vecteurs.Vecteur;

public class DistanceEuclidienne implements IDistance {

    private static IDistance instance = new DistanceEuclidienne();

    private DistanceEuclidienne() {
        super();
    }

    @Override
    public double calculer(Vecteur p1, Vecteur p2) {
        double res = 0;
        for (int i = 0; i < p1.getCoordonnees().size(); i++) {
            double ecart = p1.getCoordonnees().get(i).add(p2.getCoordonnees().get(i).negate()).doubleValue();
            res += ecart * ecart;
        }
        return Math.sqrt(res);
    }

    /**
     * Méthode d'accès à l'instance de la classe {@link DistanceEuclidienne}
     * @return l'instance de la classe {@link DistanceEuclidienne}
     */
    public static IDistance getInstance() {
        return instance;
    }
}
