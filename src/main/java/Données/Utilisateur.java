package Projet;


import java.util.*;


public class Utilisateur {
    private String userName;
    private String pseudo;
    private String password;
    List<Contacts> Contacts = new List<Contacts>() {
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
        public Iterator<Projet.Contacts> iterator() {
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
        public boolean add(Projet.Contacts contacts) {
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
        public boolean addAll(Collection<? extends Projet.Contacts> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Projet.Contacts> c) {
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
        public Projet.Contacts get(int index) {
            return null;
        }

        @Override
        public Projet.Contacts set(int index, Projet.Contacts element) {
            return null;
        }

        @Override
        public void add(int index, Projet.Contacts element) {

        }

        @Override
        public Projet.Contacts remove(int index) {
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
        public ListIterator<Projet.Contacts> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Projet.Contacts> listIterator(int index) {
            return null;
        }

        @Override
        public List<Projet.Contacts> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Projet.Contacts> getContacts() {
        return Contacts;
    }

    public void setContacts(List<Projet.Contacts> contacts) {
        Contacts = contacts;
    }
}
