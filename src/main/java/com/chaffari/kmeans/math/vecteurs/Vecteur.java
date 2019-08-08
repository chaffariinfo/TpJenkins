package com.chaffari.kmeans.math.vecteurs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe permettant de réprésenter un point de dimension variable
 * @author Chaffari
 */
public class Vecteur implements Comparable<Vecteur> {

    public static final int DIVISION_SCALE = 20;
    private List<BigDecimal> coordonnees;

    /**
     * Constructeur de {@link Vecteur}
     * @param valeurs
     *            Les coordonnées du point
     */
    public Vecteur(List<BigDecimal> valeurs) {
        this.coordonnees = valeurs;
    }

    /**
     * Constructeur de {@link Vecteur}
     * @param valeurs
     *            Les coordonnées du point
     */
    public Vecteur(int... vals) {
        coordonnees = new ArrayList<>();
        for (int d : vals) {
            coordonnees.add(BigDecimal.valueOf(d));
        }
    }

    /**
     * Constructeur de {@link Vecteur}
     * @param valeurs
     *            Les coordonnées du point
     */
    public Vecteur(int dimension) {
        this.coordonnees = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            coordonnees.add(BigDecimal.ZERO);
        }
    }

    /**
     * <p>
     * Méthode permettant d'ajouter un vecteur au vecteur courant.
     * </p>
     *
     * <pre>
     * Soit (x1, x2, ..., xn) le vecteur courant et (y1, y2, ..., yn) un autre vecteur
     * (x1, x2, ..., xn) + (y1, y2, ..., yn) = (x1 + y1, x2 + y2, ..., xn + yn)
     * </pre>
     *
     * @param d
     *            Le vecteur à ajouter au vecteur courant
     * @return le nouveau vecteur
     */
    public Vecteur ajouter(Vecteur p) {
        Vecteur res = new Vecteur(coordonnees.size());
        for (int i = 0; i < coordonnees.size(); i++) {
            res.getCoordonnees().set(i, coordonnees.get(i).add(p.getCoordonnees().get(i)));
        }
        return res;
    }

    /**
     * <p>
     * Méthode permettant de soustraire un vecteur au vecteur courant.
     * </p>
     *
     * <pre>
     * Soit (x1, x2, ..., xn) le vecteur courant et (y1, y2, ..., yn) un autre vecteur
     * (x1, x2, ..., xn) - (y1, y2, ..., yn) = (x1 - y1, x2 - y2, ..., xn - yn)
     * </pre>
     *
     * @param p
     *            Le vecteur à soustraire au vecteur courant
     * @return le nouveau vecteur
     */
    public Vecteur soustraire(final Vecteur p) {
        Vecteur res = new Vecteur(coordonnees.size());
        for (int i = 0; i < coordonnees.size(); i++) {
            res.getCoordonnees().set(i, coordonnees.get(i).add(p.getCoordonnees().get(i).negate()));
        }
        return res;
    }

    /**
     * <p>
     * Méthode permettant de multiplier le vecteur par un nombre.
     * </p>
     *
     * <pre>
     * Soit (x1, x2, ..., xn) un vecteur et d un réel,
     * d * (x1, x2, ..., xn) = (d*x1, d*x2, ..., d*xn)
     * </pre>
     *
     * @param d
     *            Le nombre servant à multiplier le vecteur
     * @return le nouveau vecteur
     */
    public Vecteur multiplier(double d) {
        Vecteur res = new Vecteur(coordonnees.size());
        for (int i = 0; i < coordonnees.size(); i++) {
            res.getCoordonnees().set(i, coordonnees.get(i).multiply(BigDecimal.valueOf(d)));
        }
        return res;
    }

    /**
     * <p>
     * Méthode permettant de diviser le vecteur par un nombre.
     * </p>
     *
     * <pre>
     * Soit (x1, x2, ..., xn) un vecteur et d un réel,
     * d * (x1, x2, ..., xn) = (x1/d, x2/d, ..., xn/d)
     * </pre>
     *
     * @param d
     *            Le nombre servant à diviser le vecteur
     * @return le nouveau vecteur
     */
    public Vecteur diviser(double d) {
        Vecteur res = new Vecteur(coordonnees.size());
        for (int i = 0; i < coordonnees.size(); i++) {
            res.getCoordonnees().set(i, coordonnees.get(i).divide(BigDecimal.valueOf(d), DIVISION_SCALE, RoundingMode.HALF_UP));
        }
        return res;
    }

    /**
     * Méthode d'accès à la liste des valeurs du point
     * @return les coordonnees du point
     */
    public List<BigDecimal> getCoordonnees() {
        return coordonnees;
    }

    /**
     * Méthode permetant de définir les coordonnees du point
     * @param valeurs
     *            les coordonnees du point
     */
    public void setCoordonnees(List<BigDecimal> coordonnees) {
        this.coordonnees = coordonnees;
    }

    @Override
    public String toString() {
        return Arrays.toString(coordonnees.toArray());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((coordonnees == null) ? 0 : coordonnees.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Vecteur)) {
            return false;
        }
        Vecteur other = (Vecteur) obj;
        if (coordonnees == null) {
            if (other.coordonnees != null) {
                return false;
            }
        }
        else {
            if (other.coordonnees != null) {
                return compareTo(other) == 0;
            }
        }
        return true;
    }

    @Override
    public int compareTo(Vecteur o) {
        if (coordonnees.size() != o.coordonnees.size()) {
            return 1000;
        }
        int res = 0;
        for (int i = 0; i < coordonnees.size(); i++) {
            int c = coordonnees.get(i).compareTo(o.coordonnees.get(i));
            res += Math.abs(c);
        }
        return res;
    }

    /**
     * Méthode permettant de savoir si un vecteur est le vecteur (0, 0, ..., 0)
     * @return vrai si le vecteur est le vecteur nul (0, 0, ..., 0)
     */
    public boolean isOrigine() {
        boolean res = true;
        for (BigDecimal b : coordonnees) {
            if (BigDecimal.ZERO.compareTo(b) != 0) {
                res = false;
            }
        }
        return res;
    }
}
