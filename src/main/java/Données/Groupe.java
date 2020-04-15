package Projet;


import java.util.*;

public class Groupe {
    List<Utilisateur> Groupe= new List<Utilisateur>() {
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
        public Iterator<Utilisateur> iterator() {
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
        public boolean add(Utilisateur utilisateur) {
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
        public boolean addAll(Collection<? extends Utilisateur> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Utilisateur> c) {
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
        public Utilisateur get(int index) {
            return null;
        }

        @Override
        public Utilisateur set(int index, Utilisateur element) {
            return null;
        }

        @Override
        public void add(int index, Utilisateur element) {

        }

        @Override
        public Utilisateur remove(int index) {
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
        public ListIterator<Utilisateur> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Utilisateur> listIterator(int index) {
            return null;
        }

        @Override
        public List<Utilisateur> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    public List<Utilisateur> getGroupe() {
        return groupe;
    }

    public void setGroupe(List<Utilisateur> groupe) {
        this.groupe = groupe;
    }
}