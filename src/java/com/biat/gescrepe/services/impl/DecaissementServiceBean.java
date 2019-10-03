package com.biat.gescrepe.services.impl;

import com.biat.gescrepe.services.DecaissementServiceBeanLocal;
import com.biat.gescrepe.servicegeneric.impl.BaseServiceBean;
import com.biat.gescrepe.entities.Decaissement;
import com.biat.gescrepe.reports.Remontees;
import com.biat.gescrepe.reports.RemplisseurListe;
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
public class DecaissementServiceBean extends BaseServiceBean<Decaissement,Integer> implements DecaissementServiceBeanLocal {

    public DecaissementServiceBean() {
        super(Decaissement.class);
    }

    @Override
    public List<Object[]> RemonObjects(Date date1, Date date2) {
        String query="select CONCAT(b.nom,b.prenom) \n" +
"as nomprenom,\n" +
"c.libObjet as objet,\n" +
"e.nature as nature,\n" +
"a1.montantCessible as montant,\n" +
"a1.quotiteCessible as taux,\n" +
"sum(f.montantOctroye) as totalencours,\n" +
"ROUND(SUM(f.mensualite)+(g.mt/g.dur)) as totalmensualite,\n" +
"b.salaire as salaire\n" +
"from decaissement a\n" +
"LEFT JOIN demande a1 on a1.idDemande=a1.idDemande\n" +
"LEFT JOIN employe b on b.matricule=a1.matricule\n" +
"LEFT JOIN garantie d on d.idGarantie=a1.idGarantie\n" +
"LEFT JOIN objet c ON c.idObjet=a1.idObjet\n" +
"LEFT JOIN typecredit e on e.idType=c.idType\n" +
"left JOIN encours f on f.matricule=b.matricule\n" +
"LEFT JOIN(select a.idDemande,a.dateDemande,a.montantCessible as mt,c.duree as dur from demande a \n" +
"INNER JOIN  objet b on b.idObjet=a.idObjet\n" +
"INNER JOIN typecredit c on c.idType=b.idType) g ON g.idDemande=a.idDemande\n" +
" WHERE\n" +
"a1.dateDemande BETWEEN ?1 and ?2\n" +
"\n" +
"GROUP BY b.matricule";
        Query q=this.em.createNativeQuery(query);
        q.setParameter(1, date1,TemporalType.TIMESTAMP);
        q.setParameter(2, date2,TemporalType.TIMESTAMP);
        return q.getResultList();
    }

    @Override
    public List<Remontees> Remontelist(Date date1, Date date2) {
         return RemplisseurListe.remplireListeSensSomme(RemonObjects(date1, date2), new Remontees());
    }

    
}
