/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.services;

import com.admin.dao.BaseDaoBeanLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author  K.M.A.
 */
public abstract class BaseServiceBean<T  extends Serializable, K> implements BaseServiceBeanLocal<T, K> {

    
    protected abstract BaseDaoBeanLocal<T, K> getDao();
    
    @Override
    public List<T> selectionnerTout() {
        return this.getDao().selectionnerTout();
    }

    @Override
    public List<T> selectionnerTout(String proprieteTri, boolean triAscendant) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<T> selectionnerTout(int debut, int total) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<T> selectionnerTout(int debut, int total, String proprieteTri, boolean triAscendant) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T selectionner(K k) {
        return this.getDao().selectionner(k);
    }

    @Override
    public void ajouter(T t) {
        this.getDao().ajouter(t);
    }

    @Override
    public void ajouter(Collection<T> ts) {
        for (T t : ts) {
            this.getDao().ajouter(t);
        }
    }

    @Override
    public void modifier(T t) {
        this.getDao().modifier(t);
    }

    @Override
    public void supprimer(K k) {
        this.getDao().supprimer(this.getDao().selectionner(k));
    }

    @Override
    public void supprimer(T t) {
        this.getDao().supprimer(t);
    }

    @Override
    public void supprimer(Collection<T> ts) {
        for (T t: ts) {
            this.getDao().supprimer(t);
        }
    }

    @Override
    public void supprimerTout() {
        this.getDao().supprimerTout();
    }

    @Override
    public int compter() {
        return this.getDao().compter();
    }

    @Override
    public T selectionner(String propriete, String valeur) {
        return this.getDao().selectionner(propriete, valeur);
    }

 
    @Override
    public List<T> selectionnerTouts(String objet,String propriete, String valeur) {
       return this.getDao().selectionnerTouts(objet,propriete, valeur);
    }
    
     @Override
    public T selectionmigrer(String id,String prop,String val){
       return this.getDao().selectionmigrer(id, prop, val);
    }
    
    @Override
    public T selectionMirgerIt(String id,String prop,Integer val){
       return this.getDao().selectionMirgerIt(id, prop, val);
    }
    
    @Override
    public List<T> selectionnerToutregle(String objet,String propriete,float valeur) {
       return this.getDao().selectionnerToutregle(objet,propriete, valeur);
    }
    
    @Override
    public List<T> selectionnerParLettre(String propriete){
        return this.getDao().selectionnerParLettre(propriete);
    }
    
}
