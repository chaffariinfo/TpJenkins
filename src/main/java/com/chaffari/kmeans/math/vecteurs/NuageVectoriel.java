package com.chaffari.kmeans.math.vecteurs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Classe représentant un nuage de vecteurs
 * @author Chaffari
 */
public class NuageVectoriel implements Iterable<Vecteur> {

    private List<Vecteur> vecteurs;
    private int dimension;

    /**
     * Constructeur de {@link NuageVectoriel}
     */
    public NuageVectoriel() {
        dimension = 0;
        vecteurs = new ArrayList<>();
    }

    /**
     * Méthode permettant d'accéder à un vecteur du nuage
     * @param k
     *            l'index du vecteurs à récupérer
     * @return le vecteur k
     */
    public Vecteur get(int k) {
        return vecteurs.get(k);
    }

    /**
     * Méthode permettant de mélanger les vecteurs du groupe
     */
    public void melanger() {
        Collections.shuffle(vecteurs);
    }

    /**
     * Méthode permettant d'ajouter un vecteur au nuage
     * @param p
     *            Le vecteur à ajouter
     */
    public void add(Vecteur p) {
        if (dimension == 0) {
            dimension = p.getCoordonnees().size();
        }
        vecteurs.add(p);
    }

    /**
     * <p>
     * Méthode permettant de centrer le nuage sur sa moyenne.
     * </p>
     * <p>
     * Si m(m1, m2, ..., mn) est la moyenne des coordonnées des vecteurs du nuage, centrer revient à soustraire
     * m de tous les points du nuage.
     * </p>
     */
    public void centrer() {
        Vecteur moyenne = getMoyenne();
        for (int i = 0; i < size(); i++) {
            vecteurs.set(i, vecteurs.get(i).soustraire(moyenne));
        }
    }

    /**
     * <p>
     * Méthode permettant de réduire le nuage de vecteurs
     * </p>
     * <p>
     * Si e(e1, e2, ..., en) est l'ecart type du nuage alors, réduire le nuage consiste à déviser toutes ses valeurs par e.
     * </p>
     */
    public void reduire() {
        Vecteur ecartType = getEcartType();
        for (int i = 0; i < size(); i++) {
            for (int k = 0; k < dimension; k++) {
                BigDecimal diviseur = ecartType.getCoordonnees().get(k);
                if (diviseur.compareTo(BigDecimal.ZERO) != 0) {
                    vecteurs.get(i).getCoordonnees().set(k, vecteurs.get(i).getCoordonnees().get(k).divide(diviseur, Vecteur.DIVISION_SCALE, RoundingMode.HALF_UP));
                }
            }
        }
    }

    /**
     * Méthode permettant de compter le nombre de vecteurs du nuage
     * @return le nombre de vecteurs du nuage
     */
    public int size() {
        return vecteurs.size();
    }

    /**
     * Méthode permettant de calculer la moyenne du nuage
     * @return la moyenne du nuage
     */
    public Vecteur getMoyenne() {
        Vecteur moyenne = new Vecteur(dimension);
        for (Vecteur p : this) {
            moyenne = moyenne.ajouter(p);
        }
        return moyenne.diviser(size());
    }

    /**
     * Méthode permettant de calculer la variance du nuage
     * @return la variance du nuage
     */
    public Vecteur getVariance() {
        Vecteur variance = new Vecteur(dimension);
        Vecteur moyenne = getMoyenne();
        for (Vecteur p : vecteurs) {
            Vecteur ecartMoyenne = p.soustraire(moyenne);
            Vecteur ecartMoyenne2 = new Vecteur(dimension);
            for (int i = 0; i < dimension; i++) {
                ecartMoyenne2.getCoordonnees().set(i, ecartMoyenne.getCoordonnees().get(i).multiply(ecartMoyenne.getCoordonnees().get(i)).divide(BigDecimal.valueOf(size()), Vecteur.DIVISION_SCALE, RoundingMode.HALF_UP));
            }
            variance = variance.ajouter(ecartMoyenne2);
        }
        return variance;
    }

    /**
     * Méthode permettant de calculer l'écart type du nuage
     * @return l'écart type du nuage
     */
    public Vecteur getEcartType() {
        Vecteur variance = getVariance();
        Vecteur ecartType = new Vecteur(dimension);
        for (int i = 0; i < dimension; i++) {
            ecartType.getCoordonnees().set(i, BigDecimal.valueOf(Math.sqrt(variance.getCoordonnees().get(i).doubleValue())));
        }
        return ecartType;
    }

    /**
     * Méthode permettant de supprimer tous les ponts du nuage
     */
    public void vider() {
        vecteurs.clear();
    }

    /**
     * Méthode d'accès aux vecteurs du nuage
     * @return les vecteurs du nuage
     */
    public List<Vecteur> getVecteurs() {
        return vecteurs;
    }

    /**
     * Méthode d'accès à la dimension du nuage
     * @return la dimension
     */
    public int getDimension() {
        return dimension;
    }

    @Override
    public Iterator<Vecteur> iterator() {
        return vecteurs.iterator();
    }
}
