package com.biat.gescrepe.services.impl;

import com.biat.gescrepe.services.DemandeServiceBeanLocal;
import com.biat.gescrepe.servicegeneric.impl.BaseServiceBean;
import com.biat.gescrepe.entities.Demande;
import com.biat.gescrepe.reports.RemplisseurListe;
import com.biat.gescrepe.reports.Suividemande;
import com.biat.gescrepe.reports.aprobSystematiq;
import com.biat.gescrepe.reports.demandeComite;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author MENSAH Y.O.D
 */
@Stateless
public class DemandeServiceBean extends BaseServiceBean<Demande,Integer> implements DemandeServiceBeanLocal {

    public DemandeServiceBean() {
        super(Demande.class);
    }

    @Override
    public List<Demande> demandenondecaisse() {
      Query q=this.em.createQuery("SELECT d FROM Demande d WHERE d.idDemande NOT IN(SELECT de.idDemande.idDemande FROM Decaissement de)");
      return q.getResultList();
       // Query q=this.em.createQuery("WHERE d.idDemande NOT IN()")
    }

    @Override
    public List<Object[]> suividemandeobj(Date date1,Date date2) {
       String string="select a.matricule,CONCAT(b.nom,b.prenom) \n" +
"as nomprenom,b.salaire,a.dateDemande as datedemande,\n" +
"c.libObjet as objetcredit,\n" +
"d.libGarantie as garantie,\n" +
"a.montantCessible as valeur,\n" +
"a.quotiteCessible as quotitecredit,\n" +
"a.quotiteAtteinte as quotitetotal,\n" +
"e.nature as categorie\n" +
" from demande 	a\n" +
"LEFT JOIN employe b on b.matricule=a.matricule\n" +
"LEFT JOIN garantie d on d.idGarantie=a.idGarantie\n" +
"LEFT JOIN objet c ON c.idObjet=a.idObjet\n" +
"LEFT JOIN typecredit e on e.idType=c.idType\n" +
" WHERE\n" +
"dateDemande BETWEEN ?1 and ?2 ";
        Query q=this.em.createNativeQuery(string);
        q.setParameter(1,date1, TemporalType.TIMESTAMP);
        q.setParameter(2, date2,TemporalType.TIMESTAMP);
        return q.getResultList();
    }

    @Override
    public List<Suividemande> suividemandelist(Date date1, Date date2) {
        return RemplisseurListe.remplireListeSensSomme(suividemandeobj(date1, date2), new Suividemande());
    }

    @Override
    public List<Object[]> demandeComitelistobj(Date date1, Date date2) {
        String req="select a.matricule,CONCAT(b.nom,b.prenom) \n" +
"as nomprenom,b.salaire,a.dateDemande as datedemande,\n" +
"c.libObjet as objetcredit,\n" +
"d.libGarantie as garantie,\n" +
"a.montantCessible as valeur,\n" +
"a.quotiteCessible as quotitecredit,\n" +
"a.quotiteAtteinte as quotitetotal,\n" +
"e.nature as categorie\n" +
" from demande 	a\n" +
"LEFT JOIN employe b on b.matricule=a.matricule\n" +
"LEFT JOIN garantie d on d.idGarantie=a.idGarantie\n" +
"LEFT JOIN objet c ON c.idObjet=a.idObjet\n" +
"LEFT JOIN typecredit e on e.idType=c.idType\n" +
" WHERE\n" +
"a.dateDemande BETWEEN ?1 and ?2\n" +
"and a.decision is not NULL";
       Query q=this.em.createNativeQuery(req);
        q.setParameter(1,date1, TemporalType.TIMESTAMP);
        q.setParameter(2, date2,TemporalType.TIMESTAMP);
        return q.getResultList();
    }

    @Override
    public List<demandeComite> demandeComitelist(Date date1, Date date2) {
        return RemplisseurListe.remplireListeSensSomme(demandeComitelistobj(date1, date2), new demandeComite());
    }

    @Override
    public List<Object[]> aproSystematiquelistobj(Date date1, Date date2) {
        String req="select a.matricule,CONCAT(b.nom,b.prenom) \n" +
"as nomprenom,b.salaire,a.dateDemande as datedemande,\n" +
"c.libObjet as objetcredit,\n" +
"d.libGarantie as garantie,\n" +
"a.montantCessible as valeur,\n" +
"a.quotiteCessible as quotitecredit,\n" +
"a.quotiteAtteinte as quotitetotal,\n" +
"e.nature as categorie\n" +
" from demande 	a\n" +
"LEFT JOIN employe b on b.matricule=a.matricule\n" +
"LEFT JOIN garantie d on d.idGarantie=a.idGarantie\n" +
"LEFT JOIN objet c ON c.idObjet=a.idObjet\n" +
"LEFT JOIN typecredit e on e.idType=c.idType\n" +
" WHERE\n" +
"a.dateDemande BETWEEN ?1 and ?2\n" +
"and a.decision is  NULL";
       Query q=this.em.createNativeQuery(req);
        q.setParameter(1,date1, TemporalType.TIMESTAMP);
        q.setParameter(2, date2,TemporalType.TIMESTAMP);
        return q.getResultList();
    }

    @Override
    public List<aprobSystematiq> aproSystematiquelist(Date date1, Date date2) {
        return RemplisseurListe.remplireListeSensSomme(aproSystematiquelistobj(date1, date2), new aprobSystematiq());
    }

   
    
}
