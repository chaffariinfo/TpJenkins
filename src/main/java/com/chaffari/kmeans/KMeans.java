package com.chaffari.kmeans;

import com.chaffari.kmeans.math.distances.IDistance;
import com.chaffari.kmeans.math.vecteurs.NuageVectoriel;
import com.chaffari.kmeans.math.vecteurs.Vecteur;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;


/**
 * Classe permettant de définir l'algoritme de classement K-means
 * @author Chaffari
 */
public class KMeans {

    private static final int NB_ITERATION_ARRET = 20;
    private static final double DISTANCE_ARRET = 0.1;
    private static final Logger LOG = Logger.getLogger("KMeans");
    private NuageVectoriel nuage;
    private List<PartitionKMeans> partitions;
    private IDistance distance;

    /**
     * Constructeur de {@link KMeans}
     * @param nuage
     *            Le nuage de points
     * @param nbCentres
     *            Le nombre de centres souhaités
     * @param distance
     *            La distance utilisée
     */
    public KMeans(NuageVectoriel nuage, int nbCentres, IDistance distance) {
        this.nuage = nuage;
        this.partitions = new ArrayList<>();
        this.distance = distance;
        initialiser(nbCentres);
    }

    private void initialiser(int nbCentres) {
        int k = 0;
        List<Vecteur> centres = new ArrayList<>();
        while (centres.size() < nbCentres) {
            if (!centres.contains(nuage.get(k))) {
                centres.add(nuage.get(k));
                partitions.add(new PartitionKMeans(centres.size(), nuage.get(k)));
            }
            k++;
        }
    }

    /**
     * Méthode permettant de lancer le K-means
     */
    public void calculer() {
        boolean finish = false;
        int iteration = 0;
        afficherPartitions(iteration);
        while (!finish) {
            razGroupes();
            List<Vecteur> ancienBarycentres = getBarycentres();
            assignerPointsPartitions();
            calculerBarycentres();
            iteration++;
            List<Vecteur> nouveauxBarycentres = getBarycentres();
            double dist = 0;
            for (int i = 0; i < ancienBarycentres.size(); i++) {
                dist += distance.calculer(ancienBarycentres.get(i), nouveauxBarycentres.get(i));
            }
            LOG.info("| Distance par rapport aux centres précédents : " + dist);
            afficherPartitions(iteration);
            if (dist < DISTANCE_ARRET && iteration < NB_ITERATION_ARRET) {
                LOG.info("*** Critère d'arret atteint ***");
                LOG.info("Résultat : ");
                for (PartitionKMeans p : partitions) {
                    LOG.info(p.toString());
                }
                finish = true;
            }
        }
    }

    private void afficherPartitions(int iteration) {
        LOG.info("Itération : " + iteration);
        for (PartitionKMeans p : partitions) {
            LOG.info("| " + p.toString());
        }
    }

    private void razGroupes() {
        for (PartitionKMeans partition : partitions) {
            partition.vider();
        }
    }

    private List<Vecteur> getBarycentres() {
        List<Vecteur> barycentres = new ArrayList<>();
        for (PartitionKMeans groupe : partitions) {
            barycentres.add(new Vecteur(groupe.getBarycentre().getCoordonnees()));
        }
        return barycentres;
    }

    private void assignerPointsPartitions() {
        double max = Double.MAX_VALUE;
        double min;
        int numPartition = 0;
        double dist;
        for (Vecteur vecteur : nuage) {
            min = max;
            for (int i = 0; i < partitions.size(); i++) {
                PartitionKMeans c = partitions.get(i);
                dist = distance.calculer(vecteur, c.getBarycentre());
                if (dist < min) {
                    min = dist;
                    numPartition = i;
                }
            }
            partitions.get(numPartition).addVecteur(vecteur);
        }
    }

    private void calculerBarycentres() {
        for (PartitionKMeans partition : partitions) {
            partition.getBarycentre().setCoordonnees(partition.getVecteurs().getMoyenne().getCoordonnees());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PartitionKMeans p : partitions) {
            for (Vecteur v : p.getNuage()) {
                sb.append(p.getId()).append("|");
                for (BigDecimal b : v.getCoordonnees()) {
                    sb.append(b.setScale(3, RoundingMode.HALF_UP)).append("|");
                }
                sb.replace(sb.length() - 1, sb.length(), "").append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Méthode d'accès aux partitions créées par le k-means. Appeler cette méthode une fois que {@link KMeans#calculer()} a été exécutée
     * @return les partitions créées par le k-means
     */
    public List<PartitionKMeans> getPartitions() {
        return partitions;
    }
}
