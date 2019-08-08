package com.chaffari.kmeans;


import com.chaffari.kmeans.math.vecteurs.NuageVectoriel;
import com.chaffari.kmeans.math.vecteurs.Vecteur;

/**
 * Classe permettant de réprésenter une partition de vecteurs pour la méthode {@link KMeans}
 * @author Chaffari
 */
public class PartitionKMeans {

    private int id;
    private Vecteur barycentre;
    private NuageVectoriel nuage;

    /**
     * Constructeur de {@link PartitionKMeans}
     * @param id
     *            Identifiant de la partition
     * @param barycentre
     *            Barycentre de la partition
     */
    public PartitionKMeans(int id, Vecteur barycentre) {
        this.id = id;
        this.nuage = new NuageVectoriel();
        this.barycentre = barycentre;
    }

    /**
     * Méthode d'accès aux vecteurs de la partition
     * @return les vecteurs de la partition
     */
    public NuageVectoriel getVecteurs() {
        return nuage;
    }

    /**
     * Méthode permettant d'assigner un vecteur a la partition
     * @param vecteur
     *            le vecteur à assigner
     */
    public void addVecteur(Vecteur vecteur) {
        nuage.add(vecteur);
    }

    /**
     * Méthode permettant d'accéder au barycentre de la partition
     * @return le barycentre de la partition
     */
    public Vecteur getBarycentre() {
        return barycentre;
    }

    /**
     * Méthode permettant de définir le barycentre de la partition
     * @param barycentre
     *            le barycentre de la partition
     */
    public void setBarycentre(Vecteur barycentre) {
        this.barycentre = barycentre;
    }

    /**
     * Méthode permettant de supprimer les vecteurs assignés a la partition
     */
    public void vider() {
        nuage.vider();
    }

    @Override
    public String toString() {
        return "Partition " + id + " : barycentre " + barycentre.getCoordonnees();
    }

    /**
     * Méthode d'accès à l'id de la partition
     * @return l'identifiant de la partition
     */
    public int getId() {
        return id;
    }

    /**
     * Méthode d'accès au nuage de points associé à la partition
     * @return le nuage de points associé à la partition
     */
    public NuageVectoriel getNuage() {
        return nuage;
    }
}
