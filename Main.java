import java.util.ArrayList;
import java.util.List;

// Classe de base pour tous les utilisateurs
abstract class Utilisateur {
    protected String nom;
    protected String prenom;
    protected String role;

    public Utilisateur(String nom, String prenom, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
    }

    public String getNomComplet() {
        return nom + " " + prenom;
    }

    public String getRole() {
        return role;
    }
}

// Classe spécifique pour le Chef de Projet
class ChefDeProjet extends Utilisateur {
    public ChefDeProjet(String nom, String prenom) {
        super(nom, prenom, "Chef de Projet");
    }

    public void gererProjet(Projet projet) {
        System.out.println("Chef de projet " + getNomComplet() + " gère le projet : " + projet.getNom());
    }
}

// Classe spécifique pour l'Employé
class Employe extends Utilisateur {
    public Employe(String nom, String prenom) {
        super(nom, prenom, "Employé");
    }
}

// Classe spécifique pour le Manager
class Manager extends Utilisateur {
    public Manager(String nom, String prenom) {
        super(nom, prenom, "Manager");
    }
}

// Classe Tâche
class Tache {
    private String intitule;
    private String description;
    private String statut; // Ex: "En cours", "Terminée"
    private Employe responsable;

    public Tache(String intitule, String description) {
        this.intitule = intitule;
        this.description = description;
        this.statut = "Non commencée";
    }

    public void assignerResponsable(Employe employe) {
        this.responsable = employe;
    }

    public void mettreAJourStatut(String statut) {
        this.statut = statut;
    }

    public String getDetails() {
        String resp = (responsable != null) ? responsable.getNomComplet() : "Non assigné";
        return "Tâche: " + intitule + " | Statut: " + statut + " | Responsable: " + resp;
    }
}

// Classe Projet
class Projet {
    private String nom;
    private ChefDeProjet chefDeProjet;
    private List<Tache> taches;

    public Projet(String nom, ChefDeProjet chefDeProjet) {
        this.nom = nom;
        this.chefDeProjet = chefDeProjet;
        this.taches = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void ajouterTache(Tache tache) {
        taches.add(tache);
    }

    public void afficherDetails() {
        System.out.println("Projet: " + nom);
        System.out.println("Chef de Projet: " + chefDeProjet.getNomComplet());
        System.out.println("Tâches:");
        for (Tache tache : taches) {
            System.out.println("  - " + tache.getDetails());
        }
    }
}

// Classe principale
public class Main {
    public static void main(String[] args) {
        // Création d'un Chef de Projet
        ChefDeProjet chef = new ChefDeProjet("Dupont", "Jean");

        // Création d'un projet
        Projet projet = new Projet("Migration Système", chef);

        // Ajout de tâches
        Tache tache1 = new Tache("Analyse des besoins", "Collecter les besoins des parties prenantes.");
        Tache tache2 = new Tache("Développement", "Développer les modules principaux.");

        projet.ajouterTache(tache1);
        projet.ajouterTache(tache2);

        // Création d'employés
        Employe employe1 = new Employe("Martin", "Alice");
        Employe employe2 = new Employe("Durand", "Paul");

        // Assignation des tâches
        tache1.assignerResponsable(employe1);
        tache2.assignerResponsable(employe2);

        // Mise à jour du statut des tâches
        tache1.mettreAJourStatut("En cours");
        tache2.mettreAJourStatut("Non commencée");

        // Affichage des détails du projet
        projet.afficherDetails();
    }
}
