package Données;

import java.util.*;

public class Contacts {
    private String userName;
    private String pseudo;
    private String password;
    private List<Message> Message=new List<Message>() {
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
        public Iterator<Données.Message> iterator() {
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
        public boolean add(Données.Message message) {
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
        public boolean addAll(Collection<? extends Données.Message> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Données.Message> c) {
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
        public Données.Message get(int index) {
            return null;
        }

        @Override
        public Données.Message set(int index, Données.Message element) {
            return null;
        }

        @Override
        public void add(int index, Données.Message element) {

        }

        @Override
        public Données.Message remove(int index) {
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
        public ListIterator<Données.Message> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Données.Message> listIterator(int index) {
            return null;
        }

        @Override
        public List<Données.Message> subList(int fromIndex, int toIndex) {
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

    public List<Données.Message> getMessage() {
        return Message;
    }

    public void setMessage(List<Données.Message> message) {
        Message = message;
    }
}
