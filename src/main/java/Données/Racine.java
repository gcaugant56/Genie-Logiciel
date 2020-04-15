package Projet;


import java.util.*;


public class Racine {

    List<Utilisateur> Utilisateur= new List<Utilisateur>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Projet.Utilisateur> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Projet.Utilisateur utilisateur) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Projet.Utilisateur> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Projet.Utilisateur> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public Projet.Utilisateur get(int index) {
            return null;
        }

        @Override
        public Projet.Utilisateur set(int index, Projet.Utilisateur element) {
            return null;
        }

        @Override
        public void add(int index, Projet.Utilisateur element) {

        }

        @Override
        public Projet.Utilisateur remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Projet.Utilisateur> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Projet.Utilisateur> listIterator(int index) {
            return null;
        }

        @Override
        public List<Projet.Utilisateur> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    public List<Projet.Utilisateur> getUtilisateur() {
        return Utilisateur;
    }

    public void setUtilisateur(List<Projet.Utilisateur> utilisateur) {
        Utilisateur = utilisateur;
    }
}
