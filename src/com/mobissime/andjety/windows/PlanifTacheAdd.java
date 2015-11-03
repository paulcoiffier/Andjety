/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package com.mobissime.andjety.windows;

import com.mobissime.andjety.utils.DBUtils;
import com.mobissime.andjety.MainFen;
import com.mobissime.andjety.entities.Scheduletasks;
import com.mobissime.andjety.entities.Tasks;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import com.mobissime.andjety.jobs.TaskJob;
import com.mobissime.andjety.objects.ScheduleTaskObj;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.*;

/**
 *
 * @author Paul Coiffier
 */
public class PlanifTacheAdd extends javax.swing.JDialog {

    /**
     * Creates new form PlanifTacheAdd
     */
    private ScheduleTaskObj sctl;
    private static Scheduletasks scTache;
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String typeFen;
    private static MainFen fenPrincipale;

    public static Date stringToDate(String sDate) throws ParseException {
        return formatter.parse(sDate);
    }

    public PlanifTacheAdd(MainFen f, boolean modal, ScheduleTaskObj sctl, Scheduletasks sct, String typeF) {
        super(f, modal);
        fenPrincipale = f;
        initComponents();
        this.sctl = sctl;
        scTache = sct;
        typeFen = typeF;

       /* new Thread(new Runnable() {

            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        // ComboBox des tâches
                        jComboBoxTaches.removeAllItems();
                       // Query query = em.createQuery("SELECT t FROM Tasks t");
                       // List<Tasks> resultList = query.getResultList();

                        for (int i = 0; i < resultList.size(); i++) {
                            jComboBoxTaches.addItem(resultList.get(i).getTaskLibelle().toString());
                        }

                        if (typeFen.equals("UPDATE")) {

                            DBUtils dbu = new DBUtils();
                            // Si on est en mode update on met un item en selection par défaut
                            // Get du libellé de la tache
                            TaskObj laTache = scTache;


                            if (scTache.getAffichexec().equals("true")) {
                                jCheckBoxAffichDuringExec.setSelected(true);
                            } else {
                                jCheckBoxAffichDuringExec.setSelected(false);
                            }

                            // Type de tâche
                            for (int i = 0; i < jComboBoxTaches.getItemCount(); i++) {
                                if (jComboBoxTaches.getItemAt(i).toString().equals(laTache.getTaskLibelle())) {
                                    // Selection par défaut
                                    jComboBoxTaches.setSelectedIndex(i);
                                }
                            }
                            // Affiche des heures
                            String HeureVal = String.valueOf(scTache.getHeures());
                            String MinutesVal = String.valueOf(scTache.getMinutes());

                            for (int i = 0; i < jComboBoxHeure.getItemCount(); i++) {
                                if (jComboBoxHeure.getItemAt(i).toString().equals(HeureVal)) {
                                    jComboBoxHeure.setSelectedIndex(i);
                                }
                            }

                            // Affiche des minutes
                            String minutesVal = String.valueOf(MinutesVal);

                            for (int i = 0; i < jComboBoxMinutes.getItemCount(); i++) {
                                if (jComboBoxMinutes.getItemAt(i).toString().equals(minutesVal)) {
                                    jComboBoxMinutes.setSelectedIndex(i);
                                }
                            }

                            // ComboBoxes
                            if (scTache.getIfalldays().equals("oui")) {

                                jCheckBox1.setSelected(true);
                                jCheckBoxLundi.setEnabled(false);
                                jCheckBoxMardi.setEnabled(false);
                                jCheckBoxMercredi.setEnabled(false);
                                jCheckBoxJeudi.setEnabled(false);
                                jCheckBoxVendredi.setEnabled(false);
                                jCheckBoxSamedi.setEnabled(false);
                                jCheckBoxDimanche.setEnabled(false);

                            } else {

                                jCheckBoxLundi.setEnabled(true);
                                jCheckBoxMardi.setEnabled(true);
                                jCheckBoxMercredi.setEnabled(true);
                                jCheckBoxJeudi.setEnabled(true);
                                jCheckBoxVendredi.setEnabled(true);
                                jCheckBoxSamedi.setEnabled(true);
                                jCheckBoxDimanche.setEnabled(true);

                            }

                            if (scTache.getIflundi() == 1) {
                                jCheckBoxLundi.setSelected(true);
                            }
                            if (scTache.getIfmardi() == 1) {
                                jCheckBoxMardi.setSelected(true);
                            }
                            if (scTache.getIfmercredi() == 1) {
                                jCheckBoxMercredi.setSelected(true);
                            }
                            if (scTache.getIfjeudi() == 1) {
                                jCheckBoxJeudi.setSelected(true);
                            }
                            if (scTache.getIfvendredi() == 1) {
                                jCheckBoxVendredi.setSelected(true);
                            }
                            if (scTache.getIfsamedi() == 1) {
                                jCheckBoxSamedi.setSelected(true);
                            }
                            if (scTache.getIfdimanche() == 1) {
                                jCheckBoxDimanche.setSelected(true);
                            }
                        }
                    }
                });
            }
        }).start();*/

        /*
         * DBUtils dbu = new DBUtils(); Tasks laTache =
         * dbu.getTask(jComboBoxTaches.getSelectedItem().toString());
         * if(laTache.getTaskAffichexec().equals(1)){
         * jCheckBoxAffichDuringExec.setSelected(true); } else {
         * jCheckBoxAffichDuringExec.setSelected(false); }
         */



        jComboBoxHeure.addItem("0");
        jComboBoxHeure.addItem("1");
        jComboBoxHeure.addItem("2");
        jComboBoxHeure.addItem("3");
        jComboBoxHeure.addItem("4");
        jComboBoxHeure.addItem("5");
        jComboBoxHeure.addItem("6");
        jComboBoxHeure.addItem("7");
        jComboBoxHeure.addItem("8");
        jComboBoxHeure.addItem("9");
        jComboBoxHeure.addItem("10");
        jComboBoxHeure.addItem("11");
        jComboBoxHeure.addItem("12");
        jComboBoxHeure.addItem("13");
        jComboBoxHeure.addItem("14");
        jComboBoxHeure.addItem("15");
        jComboBoxHeure.addItem("16");
        jComboBoxHeure.addItem("17");
        jComboBoxHeure.addItem("18");
        jComboBoxHeure.addItem("19");
        jComboBoxHeure.addItem("20");
        jComboBoxHeure.addItem("21");
        jComboBoxHeure.addItem("22");
        jComboBoxHeure.addItem("23");

        for (int i = 0; i < 60; i++) {
            jComboBoxMinutes.addItem(i);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jComboBoxTaches = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jCheckBoxLundi = new javax.swing.JCheckBox();
        jCheckBoxMardi = new javax.swing.JCheckBox();
        jCheckBoxMercredi = new javax.swing.JCheckBox();
        jCheckBoxJeudi = new javax.swing.JCheckBox();
        jCheckBoxVendredi = new javax.swing.JCheckBox();
        jCheckBoxSamedi = new javax.swing.JCheckBox();
        jCheckBoxDimanche = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxHeure = new javax.swing.JComboBox();
        jButtonOk = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBoxMinutes = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCheckBoxAffichDuringExec = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel5.setBackground(new java.awt.Color(204, 204, 255));
        jLabel5.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome_document_open_recent.png"))); // NOI18N
        jLabel5.setText("Tâche planifiée");
        jLabel5.setOpaque(true);

        jLabel1.setText("Tâche : ");

        jCheckBox1.setText("Répéter tous les jours");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(2, 3));

        jCheckBoxLundi.setText("Lundi");
        jPanel1.add(jCheckBoxLundi);

        jCheckBoxMardi.setText("Mardi");
        jPanel1.add(jCheckBoxMardi);

        jCheckBoxMercredi.setText("Mercredi");
        jPanel1.add(jCheckBoxMercredi);

        jCheckBoxJeudi.setText("Jeudi");
        jPanel1.add(jCheckBoxJeudi);

        jCheckBoxVendredi.setText("Vendredi");
        jPanel1.add(jCheckBoxVendredi);

        jCheckBoxSamedi.setText("Samedi");
        jPanel1.add(jCheckBoxSamedi);

        jCheckBoxDimanche.setText("Dimanche");
        jPanel1.add(jCheckBoxDimanche);

        jLabel2.setText("Heure : ");

        jButtonOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tick.png"))); // NOI18N
        jButtonOk.setText("Terminé");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("assistant/Bundle"); // NOI18N
        jButton2.setText(bundle.getString("Assistant2.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("h");

        jLabel4.setText("mn");

        jCheckBoxAffichDuringExec.setSelected(true);
        jCheckBoxAffichDuringExec.setText("Afficher durant l'execution");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxAffichDuringExec)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxTaches, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxHeure, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)))
                        .addGap(0, 72, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTaches, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxHeure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxAffichDuringExec)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButtonOk))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed

        String tacheNom, heure, minute, ifrepetitif;
        int ifMardi = 0, ifMercredi = 0, ifJeudi = 0, ifVendredi = 0, ifSamedi = 0, ifDimanche = 0, ifLundi = 0;

        // Il faut au moins qu'un jour soit selectionné
        if ((jCheckBox1.isSelected() == false) && (jCheckBoxLundi.isSelected() == false) && (jCheckBoxMardi.isSelected() == false) && (jCheckBoxMercredi.isSelected() == false) && (jCheckBoxJeudi.isSelected() == false) && (jCheckBoxSamedi.isSelected() == false) && (jCheckBoxDimanche.isSelected() == false)) {
            JOptionPane.showMessageDialog(this, "Veuillez selectionner au moins un jour.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {

            if (typeFen.equals("UPDATE")) {

                heure = jComboBoxHeure.getSelectedItem().toString();
                minute = jComboBoxMinutes.getSelectedItem().toString();
                String daysParts = "";

                scTache.setHeures(Integer.valueOf(heure));
                scTache.setMinutes(Integer.valueOf(minute));

                if (jCheckBox1.isSelected()) {
                    scTache.setIfalldays("oui");
                } else {
                    scTache.setIfalldays("non");
                }

                if (jCheckBoxLundi.isSelected()) {
                    if (daysParts.length() > 1) {
                        daysParts = daysParts + ",MON";
                    } else {
                        daysParts = daysParts + "MON";
                    }
                    scTache.setIflundi(1);
                } else {
                    scTache.setIflundi(0);
                }
                if (jCheckBoxMardi.isSelected()) {
                    if (daysParts.length() > 1) {
                        daysParts = daysParts + ",TUE";
                    } else {
                        daysParts = daysParts + "TUE";
                    }
                    scTache.setIfmardi(1);
                } else {
                    scTache.setIfmardi(0);
                }
                if (jCheckBoxMercredi.isSelected()) {
                    if (daysParts.length() > 1) {
                        daysParts = daysParts + ",WED";
                    } else {
                        daysParts = daysParts + "WED";
                    }
                    scTache.setIfmercredi(1);
                } else {
                    scTache.setIfmercredi(0);
                }
                if (jCheckBoxJeudi.isSelected()) {
                    if (daysParts.length() > 1) {
                        daysParts = daysParts + ",THU";
                    } else {
                        daysParts = daysParts + "THU";
                    }
                    scTache.setIfjeudi(1);
                } else {
                    scTache.setIfjeudi(0);
                }
                if (jCheckBoxVendredi.isSelected()) {
                    if (daysParts.length() > 1) {
                        daysParts = daysParts + ",FRI";
                    } else {
                        daysParts = daysParts + "FRI";
                    }
                    scTache.setIfvendredi(1);
                } else {
                    scTache.setIfvendredi(0);
                }
                if (jCheckBoxSamedi.isSelected()) {
                    if (daysParts.length() > 1) {
                        daysParts = daysParts + ",SAT";
                    } else {
                        daysParts = daysParts + "SAT";
                    }
                    scTache.setIfsamedi(1);
                } else {
                    scTache.setIfsamedi(0);
                }
                if (jCheckBoxDimanche.isSelected()) {
                    if (daysParts.length() > 1) {
                        daysParts = daysParts + ",SUN";
                    } else {
                        daysParts = daysParts + "SUN";
                    }
                    scTache.setIfdimanche(1);
                } else {
                    scTache.setIfdimanche(0);
                }

                if (jCheckBoxAffichDuringExec.isSelected()) {
                    scTache.setAffichexec("true");
                } else {
                    scTache.setAffichexec("false");
                }

                merge(scTache);

                if (jCheckBox1.isSelected()) {
                    daysParts = "MON,TUE,WED,THU,FRI,SAT,SUN";
                }

                // Génération de l'expression CRON
                String cronString = "0 " + minute + "  " + heure + "-" + heure + " ? * " + daysParts;

                // Création de la nouvelle tâche
                JobKey theJkey = new JobKey(String.valueOf(scTache.getId()));
                TriggerKey tkey = new TriggerKey(String.valueOf(scTache.getId()));
                JobDetail job = newJob(TaskJob.class).withIdentity(theJkey).build();
                Trigger trigger = newTrigger().withIdentity(tkey).withSchedule(cronSchedule(cronString)).forJob(theJkey).build();

                try {
                    // On supprime l'ancienne tache
                    fenPrincipale.scheduler.unscheduleJob(tkey);
                    // On schedule le nouveau
                    job.getJobDataMap().put("TaskLibelle", jComboBoxTaches.getSelectedItem().toString() + "/" + scTache.getId());
                    //job.getJobDataMap().put("ScheduleTaskId", scTache.getId());
                    System.out.println("Schdule Task ID : " + scTache.getId());
                    fenPrincipale.scheduler.scheduleJob(job, trigger);
                } catch (SchedulerException ex) {
                    Logger.getLogger(PlanifTacheAdd.class.getName()).log(Level.SEVERE, null, ex);
                }

                dispatchEvent(new WindowEvent(
                        this, WindowEvent.WINDOW_CLOSING));


            } else {
                // Ajout de la tâche planifiée

                tacheNom = jComboBoxTaches.getSelectedItem().toString();
                heure = jComboBoxHeure.getSelectedItem().toString();
                minute = jComboBoxMinutes.getSelectedItem().toString();

                if (jCheckBox1.isSelected()) {
                    ifrepetitif = "oui";
                } else {
                    ifrepetitif = "non";
                }

                String daysParts = "";

                if (jCheckBoxLundi.isSelected()) {
                    ifLundi = 1;
                    if (daysParts.length() > 1) {
                        daysParts = daysParts + ",MON";
                    } else {
                        daysParts = daysParts + "MON";
                    }
                }
                if (jCheckBoxMardi.isSelected()) {
                    ifMardi = 1;
                    if (daysParts.length() > 1) {
                        daysParts = daysParts + ",TUE";
                    } else {
                        daysParts = daysParts + "TUE";
                    }
                }
                if (jCheckBoxMercredi.isSelected()) {
                    ifMercredi = 1;
                    if (daysParts.length() > 1) {
                        daysParts = daysParts + ",WED";
                    } else {
                        daysParts = daysParts + "WED";
                    }
                }
                if (jCheckBoxJeudi.isSelected()) {
                    ifJeudi = 1;
                    if (daysParts.length() > 1) {
                        daysParts = daysParts + ",THU";
                    } else {
                        daysParts = daysParts + "THU";
                    }
                }
                if (jCheckBoxVendredi.isSelected()) {
                    ifVendredi = 1;
                    if (daysParts.length() > 1) {
                        daysParts = daysParts + ",FRI";
                    } else {
                        daysParts = daysParts + "FRI";
                    }
                }
                if (jCheckBoxSamedi.isSelected()) {
                    ifSamedi = 1;
                    if (daysParts.length() > 1) {
                        daysParts = daysParts + ",SAT";
                    } else {
                        daysParts = daysParts + "SAT";
                    }
                }
                if (jCheckBoxDimanche.isSelected()) {
                    ifDimanche = 1;
                    if (daysParts.length() > 1) {
                        daysParts = daysParts + ",SUN";
                    } else {
                        daysParts = daysParts + "SUN";
                    }
                }

                DBUtils dbu = new DBUtils();
                Tasks tmpTask = dbu.getTask(jComboBoxTaches.getSelectedItem().toString());

                Scheduletasks newTask = null;
                if (jCheckBoxAffichDuringExec.isSelected()) {
                    // Insertion en base
                    newTask = new Scheduletasks(null, tmpTask.getId(), ifrepetitif, ifLundi, ifMardi, ifMercredi, ifJeudi, ifVendredi, ifSamedi, ifDimanche, Integer.valueOf(minute), Integer.valueOf(heure), "true");
                    persist(newTask);
                } else {
                    // Insertion en base
                    newTask = new Scheduletasks(null, tmpTask.getId(), ifrepetitif, ifLundi, ifMardi, ifMercredi, ifJeudi, ifVendredi, ifSamedi, ifDimanche, Integer.valueOf(minute), Integer.valueOf(heure), "false");
                    persist(newTask);
                }



                if (ifrepetitif.equals("oui")) {
                    daysParts = "MON,TUE,WED,THU,FRI,SAT,SUN";
                }
                // Génération de l'expression CRON
                String cronString = "0 " + minute + "  " + heure + "-" + heure + " ? * " + daysParts;

                // Création de la tâche CRON
                JobKey theJkey = new JobKey(String.valueOf(newTask.getId()));
                TriggerKey tkey = new TriggerKey(String.valueOf(newTask.getId()));
                JobDetail job = newJob(TaskJob.class).withIdentity(theJkey).build();
                Trigger trigger = newTrigger().withIdentity(tkey).withSchedule(cronSchedule(cronString)).forJob(theJkey).build();

                try {
                    job.getJobDataMap().put("TaskLibelle", jComboBoxTaches.getSelectedItem().toString() + "/" + newTask.getId());
                    //job.getJobDataMap().put("ScheduleTaskId", newTask.getId());
                    System.out.println("Schdule Task ID : " + newTask.getId());
                    fenPrincipale.scheduler.scheduleJob(job, trigger);
                } catch (SchedulerException ex) {
                    Logger.getLogger(PlanifTacheAdd.class.getName()).log(Level.SEVERE, null, ex);
                }


                dispatchEvent(new WindowEvent(
                        this, WindowEvent.WINDOW_CLOSING));

            }
        }
    }//GEN-LAST:event_jButtonOkActionPerformed

    static void merge(Object object) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
        EntityManager mm = emf.createEntityManager();
        mm.getTransaction().begin();
        try {
            mm.merge(object);
            mm.getTransaction().commit();
            //System.out.println("Commit");
        } catch (Exception e) {
            mm.getTransaction().rollback();
        } finally {
            mm.close();
        }
    }

    static void persist(Object object) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
        EntityManager eml = emf.createEntityManager();
        eml.getTransaction().begin();
        try {
            eml.persist(object);
            eml.getTransaction().commit();
            // System.out.println("Commit");
        } catch (Exception e) {
            eml.getTransaction().rollback();
        } finally {
            eml.close();
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.hide();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        if (jCheckBox1.isSelected()) {
            jCheckBoxLundi.setEnabled(false);
            jCheckBoxMardi.setEnabled(false);
            jCheckBoxMercredi.setEnabled(false);
            jCheckBoxJeudi.setEnabled(false);
            jCheckBoxVendredi.setEnabled(false);
            jCheckBoxSamedi.setEnabled(false);
            jCheckBoxDimanche.setEnabled(false);
        } else {
            jCheckBoxLundi.setEnabled(true);
            jCheckBoxMardi.setEnabled(true);
            jCheckBoxMercredi.setEnabled(true);
            jCheckBoxJeudi.setEnabled(true);
            jCheckBoxVendredi.setEnabled(true);
            jCheckBoxSamedi.setEnabled(true);
            jCheckBoxDimanche.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBox1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PlanifTacheAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlanifTacheAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlanifTacheAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlanifTacheAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                PlanifTacheAdd dialog = new PlanifTacheAdd(fenPrincipale, true, null, scTache, typeFen);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBoxAffichDuringExec;
    private javax.swing.JCheckBox jCheckBoxDimanche;
    private javax.swing.JCheckBox jCheckBoxJeudi;
    private javax.swing.JCheckBox jCheckBoxLundi;
    private javax.swing.JCheckBox jCheckBoxMardi;
    private javax.swing.JCheckBox jCheckBoxMercredi;
    private javax.swing.JCheckBox jCheckBoxSamedi;
    private javax.swing.JCheckBox jCheckBoxVendredi;
    private javax.swing.JComboBox jComboBoxHeure;
    private javax.swing.JComboBox jComboBoxMinutes;
    private javax.swing.JComboBox jComboBoxTaches;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
