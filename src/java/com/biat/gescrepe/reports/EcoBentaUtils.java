/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biat.gescrepe.reports;

//import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;


/**
 *
 * @author MANU
 */
public class EcoBentaUtils {

  
    private static final Properties langue=null;
    private static final Date dateDujour = new Date();
    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final String HOUR_PATTERN = "HH : mm : ss";
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);
    private static final SimpleDateFormat hourFormatter = new SimpleDateFormat(HOUR_PATTERN);

    public static int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public EcoBentaUtils() {
    }

 

    public static Date dateDujour() {
        return dateDujour;
    }

    public static String pattern(int pos) {
        String pa = new String();
        for (int i = 0; i < pos; i++) {
            pa = pa + "0";
        }
        return pa;
    }

    /**
     * Convertit un Boolean en String
     *
     * @param status boolean
     * @return string Oui/Non
     */
    public String convertStatusPiece(Boolean status) {
        return status ? "Oui" : "Non";
    }

    public static UserTransaction getUserTransaction() {
        UserTransaction utx;
        try {
            InitialContext context = new InitialContext();
            utx = (UserTransaction) context.lookup("java:comp/UserTransaction");
            return utx;
        } catch (NamingException e) {
            return null;
        }
    }

 
    public static String formateDate(String pattern, Date d) {
        if (d != null) {
            SimpleDateFormat formate = new SimpleDateFormat(pattern);
            return formate.format(d);
        } else {
            return null;
        }
    }

    /*
     * Recuperer le nom de la machine locale
     * @return hostName= nom de la machine local
     */
    public static String getLocalHostName() {
        String hostName = null;
        try {
            hostName = InetAddress.getLocalHost().getHostName();
            return hostName;
        } catch (java.net.UnknownHostException ex) {
            Logger.getLogger(EcoBentaUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hostName;
    }

    @SuppressWarnings("deprecation")
    public static String getDateFormate(Date date) {
        String dateFormate = null;
        if (date != null) {
            dateFormate = ((date.getDate() < 10) ? ("0" + date.getDate()) : (String.valueOf(date.getDate())));
            dateFormate += "/";
            dateFormate += ((date.getMonth() < 9) ? ("0" + (date.getMonth() + 1)) : (date.getMonth() + 1));
            dateFormate += "/";
            dateFormate += date.getYear() + 1900;
        }
        return dateFormate;
    }

 

    /*
     * Formatter un objet Date en String(heure seulement)
     * @param date = date à formatter
     * @return String = date formattée, null (si date à formatter est nulle)
     */
    public static String formatHour(Date date) {
        return (date != null) ? hourFormatter.format(date) : null;
    }

    public static Integer getYear(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);

            return new Integer(year);
        }
        return null;
    }

   // Conversion en Lettre
    public static String conversionLettre(BigDecimal montant) {
        com.ibm.icu.text.NumberFormat formatter = new RuleBasedNumberFormat(Locale.FRANCE,
                RuleBasedNumberFormat.SPELLOUT);
        return formatter.format(montant.intValue());
    }

    /**
     * Convertit un montant en String 1000000 -> 1000 000
     *
     * @param somme montant à convertir
     * @return montant converti
     */
    public static String numBerFormat(BigDecimal somme) {

        NumberFormat numberFormat = NumberFormat.getInstance();

        try {
            return numberFormat.format(somme.doubleValue());

        } catch (Exception e) {

            return "";
        }
    }

    public static String numBerFormatInteger(Integer valeur) {

        NumberFormat numberFormat = NumberFormat.getInstance();
        try {
            return numberFormat.format(valeur.doubleValue());

        } catch (Exception e) {
            return "0";
        }
    }

    public static String formatDate(Date date) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if (date != null) {
            return format.format(date);
        }
        return "";
    }

    public static String sansAccent(String s) {
        String strTemp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(strTemp).replaceAll("");
    }

}
