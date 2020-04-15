package Données;


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
        public Iterator<Données.Utilisateur> iterator() {
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
        public boolean add(Données.Utilisateur utilisateur) {
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
        public boolean addAll(Collection<? extends Données.Utilisateur> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Données.Utilisateur> c) {
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
        public Données.Utilisateur get(int index) {
            return null;
        }

        @Override
        public Données.Utilisateur set(int index, Données.Utilisateur element) {
            return null;
        }

        @Override
        public void add(int index, Données.Utilisateur element) {

        }

        @Override
        public Données.Utilisateur remove(int index) {
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
        public ListIterator<Données.Utilisateur> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Données.Utilisateur> listIterator(int index) {
            return null;
        }

        @Override
        public List<Données.Utilisateur> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    public List<Données.Utilisateur> getUtilisateur() {
        return Utilisateur;
    }

    public void setUtilisateur(List<Données.Utilisateur> utilisateur) {
        Utilisateur = utilisateur;
    }
}
