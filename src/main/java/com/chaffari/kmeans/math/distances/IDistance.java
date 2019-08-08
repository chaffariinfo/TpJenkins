package com.chaffari.kmeans.math.distances;

import com.chaffari.kmeans.math.vecteurs.Vecteur;

/**
 * Interface permettant de définir une distance
 * @author k0b6qu
 */
public interface IDistance {

    /**
     * Méthode permettant de calculer la distance entre deux points
     * @param p1
     *            Le premier point
     * @param p2
     *            Le second point
     * @return la distance entre les deux points
     */
    double calculer(Vecteur p1, Vecteur p2);
}
