/*
 * zn_ago_battery_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:19 by COMSOL 6.3.0.290. */
public class zn_ago_battery_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Batteries,_General");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("batbe", "BatteryBinaryElectrolyte", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/batbe", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_Zn", "0.13[cm]", "\u539a\u5ea6\uff0c\u8d1f\u6781");
    model.param().set("L_sep", "0.05[cm]", "\u539a\u5ea6\uff0c\u9694\u819c");
    model.param().set("L_AgO", "0.11[cm]", "\u539a\u5ea6\uff0c\u6b63\u6781");
    model.param().set("t_plus", "0.22", "\u4f20\u9012\u6570");
    model.param().set("T", "300[K]", "\u5de5\u4f5c\u6e29\u5ea6");
    model.param().set("rho", "1500[kg/m^3]", "\u7535\u89e3\u8d28\u6eb6\u6db2\u5bc6\u5ea6");
    model.param().set("rho_Ag", "10.49[g/cm^3]", "Ag \u7684\u5bc6\u5ea6");
    model.param().set("rho_AgO", "7.48[g/cm^3]", "AgO \u7684\u5bc6\u5ea6");
    model.param().set("rho_Ag2O", "7.14[g/cm^3]", "Ag2O \u7684\u5bc6\u5ea6");
    model.param().set("rho_Zn", "7.14[g/cm^3]", "Zn \u7684\u5bc6\u5ea6");
    model.param().set("rho_ZnO", "5.606[g/cm^3]", "ZnO \u7684\u5bc6\u5ea6");
    model.param().set("M_K", "39.1[g/mol]", "\u94be\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("M_OH", "17[g/mol]", "\u9634\u79bb\u5b50\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("M_H2O", "18[g/mol]", "\u6eb6\u5242\u6469\u5c14\u8d28\u91cf");
    model.param().set("MW_Ag", "107.87[g/mol]", "Ag \u7684\u5206\u5b50\u91cf");
    model.param().set("MW_AgO", "123.87[g/mol]", "AgO \u7684\u5206\u5b50\u91cf");
    model.param().set("MW_Ag2O", "231.74[g/mol]", "Ag2O \u7684\u5206\u5b50\u91cf");
    model.param().set("MW_Zn", "65.38[g/mol]", "Zn \u7684\u5206\u5b50\u91cf");
    model.param().set("MW_ZnO", "81.408[g/mol]", "ZnO \u7684\u5206\u5b50\u91cf");
    model.param().set("D_OH", "5.26e-9[m^2/s]", "OH \u79bb\u5b50\u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("D_K", "1.96e-9[m^2/s]", "Zn \u79bb\u5b50\u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("sigma_Ag", "6.28e7[S/m]", "Ag \u7684\u7535\u5b50\u7535\u5bfc\u7387");
    model.param().set("sigma_AgO", "8.33e4[S/m]", "AgO \u7684\u7535\u5b50\u7535\u5bfc\u7387");
    model.param().set("sigma_Ag2O", "1e-6[S/m]", "Ag2O \u7684\u7535\u5b50\u7535\u5bfc\u7387");
    model.param().set("sigma_Zn", "1.83e7[S/m]", "Zn \u7684\u7535\u5b50\u7535\u5bfc\u7387");
    model.param().set("sigma_ZnO", "1[S/m]", "ZnO \u7684\u7535\u5b50\u7535\u5bfc\u7387");
    model.param().set("cl_init", "8.9[mol/dm^3]", "\u521d\u59cb\u7535\u89e3\u8d28\u6d53\u5ea6");
    model.param().set("Coh_ref", "cl_init*0.40", "\u53c2\u8003\u7535\u89e3\u8d28\u6d53\u5ea6");
    model.param()
         .set("cZn_init", "1.3e3[mol/m^3]*cZn_param", "\u7535\u6781\u4e2d\u7684\u521d\u59cb Zn \u6d53\u5ea6");
    model.param().set("cAg2O_init", "1e-6[mol/m^3]", "\u7535\u6781\u4e2d\u7684\u521d\u59cb Ag2O \u6d53\u5ea6");
    model.param().set("cAgO_init", "7e3[mol/m^3]", "\u7535\u6781\u4e2d\u7684\u521d\u59cb AgO \u6d53\u5ea6");
    model.param().set("Eeq1", "0.342[V]", "\u53cd\u5e94 1 \u7684\u5e73\u8861\u7535\u4f4d");
    model.param().set("Eeq2", "0.604[V]", "\u53cd\u5e94 2 \u7684\u5e73\u8861\u7535\u4f4d");
    model.param().set("Eeq3", "-1.305[V]", "\u53cd\u5e94 3 \u7684\u5e73\u8861\u7535\u4f4d");
    model.param().set("i01", "2e-6[A/cm^2]", "\u53cd\u5e94 1 \u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("i02", "2e-6[A/cm^2]", "\u53cd\u5e94 2 \u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("i03", "1.75e-6[A/cm^2]", "\u53cd\u5e94 3 \u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("alphaa1", "0.5", "\u53cd\u5e94 1 \u7684\u9633\u6781\u4f20\u9012\u7cfb\u6570");
    model.param().set("alphaa2", "0.2", "\u53cd\u5e94 2 \u7684\u9633\u6781\u4f20\u9012\u7cfb\u6570");
    model.param().set("alphaa3", "0.85", "\u53cd\u5e94 3 \u7684\u9633\u6781\u4f20\u9012\u7cfb\u6570");
    model.param().set("n", "2", "\u8f6c\u79fb\u7535\u5b50\u6570");
    model.param().set("a", "10[cm^2/cm^3]", "\u7535\u6781\u7684\u6bd4\u8868\u9762\u79ef");
    model.param().set("eps_e_sep", "0.5", "\u9694\u819c\u5b54\u9699\u7387");
    model.param().set("eps_e_pos", "0.7", "\u6b63\u6781\u7684\u521d\u59cb\u5b54\u9699\u7387");
    model.param().set("eps_e_neg", "0.5", "\u8d1f\u6781\u7684\u521d\u59cb\u5b54\u9699\u7387");
    model.param().set("cZn_param", "1", "\u7528\u4e8e\u53c2\u6570\u5316\u626b\u63cf");

    model.func().create("pw1", "Piecewise");
    model.func("pw1").set("funcname", "I");
    model.func("pw1").set("smooth", "cont");
    model.func("pw1").set("smoothzone", 0.01);
    model.func("pw1")
         .set("pieces", new String[][]{{"0", "120", "0.05"}, 
         {"120", "220", "0.1"}, 
         {"220", "320", "0.15"}, 
         {"320", "420", "0.2"}, 
         {"420", "600", "0.3"}, 
         {"600", "800", "0.4"}});
    model.func("pw1").set("argunit", "s");
    model.func("pw1").set("fununit", "A/cm^2");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("cAg", "batbe.c_pce1_Ag", "Ag \u7684\u6d53\u5ea6");
    model.component("comp1").variable("var1").set("cAgO", "batbe.c_pce1_AgO", "AgO \u7684\u6d53\u5ea6");
    model.component("comp1").variable("var1").set("cAg2O", "batbe.c_pce1_Ag2O", "Ag2O \u7684\u6d53\u5ea6");
    model.component("comp1").variable("var1").set("cZn", "batbe.c_pce2_Zn", "Zn \u7684\u6d53\u5ea6");
    model.component("comp1").variable("var1").set("cZnO", "batbe.c_pce2_ZnO", "ZnO \u7684\u6d53\u5ea6");
    model.component("comp1").variable("var1")
         .set("mAg", "max(cAg*MW_Ag/(cAg*MW_Ag+cAgO*MW_AgO+cAg2O*MW_Ag2O),eps)", "Ag \u7684\u8d28\u91cf\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("mAgO", "max(cAgO*MW_AgO/(cAg*MW_Ag+cAgO*MW_AgO+cAg2O*MW_Ag2O),eps)", "AgO \u7684\u8d28\u91cf\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("mAg2O", "max(cAg2O*MW_Ag2O/(cAg*MW_Ag+cAgO*MW_AgO+cAg2O*MW_Ag2O),eps)", "Ag2O \u7684\u8d28\u91cf\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("mZn", "max(cZn*MW_Zn/(cZn*MW_Zn+cZnO*MW_ZnO),eps)", "Zn \u7684\u8d28\u91cf\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("mZnO", "max(cZnO*MW_ZnO/(cZn*MW_Zn+cZnO*MW_ZnO),eps)", "ZnO \u7684\u8d28\u91cf\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("sigmaleff", "(batbe.epsl*F_const^2/R_const/T)*(D_K+D_OH)*cl", "\u6709\u6548\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.component("comp1").variable("var1")
         .set("sigmaseff_pos", "sigma_Ag*mAg^1.5+sigma_AgO*mAgO^1.5+sigma_Ag2O*mAg2O^1.5", "\u6b63\u6781\u6709\u6548\u7535\u5bfc\u7387");
    model.component("comp1").variable("var1")
         .set("sigmaseff_neg", "sigma_Zn*mZn^1.5+sigma_ZnO*mZnO^1.5", "\u8d1f\u6781\u6709\u6548\u7535\u5bfc\u7387");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_AgO", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_sep", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_Zn", 2);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "EndTerminal");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(1);

    model.component("comp1").physics("batbe").prop("BatBeSpecies").set("ManMinus", "M_OH");
    model.component("comp1").physics("batbe").prop("BatBeSpecies").set("McatPlus", "M_K");
    model.component("comp1").physics("batbe").prop("BatBeSpecies").set("M0", "M_H2O");
    model.component("comp1").physics("batbe").feature("sep1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("batbe").feature("sep1")
         .set("sigmal", new String[]{"sigmaleff", "0", "0", "0", "sigmaleff", "0", "0", "0", "sigmaleff"});
    model.component("comp1").physics("batbe").feature("sep1").set("Dl_mat", "userdef");
    model.component("comp1").physics("batbe").feature("sep1").set("Dl", "D_OH");
    model.component("comp1").physics("batbe").feature("sep1").set("transpNum_mat", "userdef");
    model.component("comp1").physics("batbe").feature("sep1").set("transpNum", "t_plus");
    model.component("comp1").physics("batbe").feature("sep1").set("fcl_mat", "userdef");
    model.component("comp1").physics("batbe").feature("sep1").set("rho_mat", "userdef");
    model.component("comp1").physics("batbe").feature("sep1").set("rho", "rho");
    model.component("comp1").physics("batbe").feature("sep1").set("epsl", "eps_e_sep");
    model.component("comp1").physics("batbe").feature("sep1").set("IonicCorrModel", "NoCorr");
    model.component("comp1").physics("batbe").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("batbe").feature("pce1").selection().set(1);
    model.component("comp1").physics("batbe").feature("pce1")
         .label("\u591a\u5b54\u7535\u6781\uff1aAgO\uff08\u6b63\u6781\uff09");
    model.component("comp1").physics("batbe").feature("pce1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("batbe").feature("pce1")
         .set("sigmal", new String[]{"sigmaleff", "0", "0", "0", "sigmaleff", "0", "0", "0", "sigmaleff"});
    model.component("comp1").physics("batbe").feature("pce1").set("Dl_mat", "userdef");
    model.component("comp1").physics("batbe").feature("pce1").set("Dl", "D_OH");
    model.component("comp1").physics("batbe").feature("pce1").set("transpNum_mat", "userdef");
    model.component("comp1").physics("batbe").feature("pce1").set("transpNum", "t_plus");
    model.component("comp1").physics("batbe").feature("pce1").set("fcl_mat", "userdef");
    model.component("comp1").physics("batbe").feature("pce1").set("rho_mat", "userdef");
    model.component("comp1").physics("batbe").feature("pce1").set("rho", "rho");
    model.component("comp1").physics("batbe").feature("pce1")
         .set("sigma", new String[]{"sigmaseff_pos", "0", "0", "0", "sigmaseff_pos", "0", "0", "0", "sigmaseff_pos"});
    model.component("comp1").physics("batbe").feature("pce1")
         .set("IntercalationOption", "NonIntercalatingParticles");
    model.component("comp1").physics("batbe").feature("pce1").set("epss", "1-batbe.epsl");
    model.component("comp1").physics("batbe").feature("pce1").set("epsl", "eps_e_pos");
    model.component("comp1").physics("batbe").feature("pce1").set("IonicCorrModel", "NoCorr");
    model.component("comp1").physics("batbe").feature("pce1").set("ElectricCorrModel", "NoCorr");
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Species", "Ag2O", 0, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("rhos", "rho_Ag2O", 0, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Ms", "MW_Ag2O", 0, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Species", "s1", 1, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Species", "s1", 1, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Species", "AgO", 1, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("rhos", "rho_AgO", 1, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Ms", "MW_AgO", 1, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Species", "s1", 2, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("rhos", 8960, 2, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Ms", 0.06355, 2, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("rhos", 8960, 2, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Ms", 0.06355, 2, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Species", "s1", 2, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("rhos", 8960, 2, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Ms", 0.06355, 2, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Species", "Ag", 2, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("rhos", "rho_Ag", 2, 0);
    model.component("comp1").physics("batbe").feature("pce1").setIndex("Ms", "MW_Ag", 2, 0);
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("Eeq_mat", "NernstEquation");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("Eeq_ref", "Eeq1");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1")
         .set("CRNernst", "(cl/Coh_ref)^2*(cAg/cAg2O)^2");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("CONernst", "cAg2O/cAg2O_init");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1")
         .set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("i0Type", "FromNernstEquation");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("i0_ref", "i01");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("alphaa", "alphaa1*n");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1")
         .set("ActiveSpecificSurfaceAreaType", "userdef");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("Av", "a");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("nm", "n");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").setIndex("Vib", -1, 0, 0);
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").setIndex("Vib", 2, 2, 0);
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("batbe").feature("pce1").create("per2", "PorousElectrodeReaction", 1);
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("Eeq_mat", "NernstEquation");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("Eeq_ref", "Eeq2");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2")
         .set("CRNernst", "(cl/Coh_ref)^2*(cAg2O/cAgO_init)");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2")
         .set("CONernst", "max(cAgO/cAgO_init,1e-6)^2");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2")
         .set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("i0Type", "FromNernstEquation");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("i0_ref", "i02");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("alphaa", "alphaa2*n");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2")
         .set("ActiveSpecificSurfaceAreaType", "userdef");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("Av", "a");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("nm", "n");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").setIndex("Vib", -2, 1, 0);
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("batbe").feature("pce1")
         .create("ivdds1", "InitialValuesForDissolvingDepositingSpecies", 1);
    model.component("comp1").physics("batbe").feature("pce1").feature("ivdds1").setIndex("c0", "cAg2O_init", 0, 0);
    model.component("comp1").physics("batbe").feature("pce1").feature("ivdds1").setIndex("c0", "cAgO_init", 1, 0);
    model.component("comp1").physics("batbe").create("pce2", "PorousElectrode", 1);
    model.component("comp1").physics("batbe").feature("pce2")
         .label("\u591a\u5b54\u7535\u6781\uff1aZn\uff08\u8d1f\u6781\uff09");
    model.component("comp1").physics("batbe").feature("pce2").selection().set(3);
    model.component("comp1").physics("batbe").feature("pce2").set("sigmal_mat", "userdef");
    model.component("comp1").physics("batbe").feature("pce2")
         .set("sigmal", new String[]{"sigmaleff", "0", "0", "0", "sigmaleff", "0", "0", "0", "sigmaleff"});
    model.component("comp1").physics("batbe").feature("pce2").set("Dl_mat", "userdef");
    model.component("comp1").physics("batbe").feature("pce2").set("Dl", "D_OH");
    model.component("comp1").physics("batbe").feature("pce2").set("transpNum_mat", "userdef");
    model.component("comp1").physics("batbe").feature("pce2").set("transpNum", "t_plus");
    model.component("comp1").physics("batbe").feature("pce2").set("fcl_mat", "userdef");
    model.component("comp1").physics("batbe").feature("pce2").set("rho_mat", "userdef");
    model.component("comp1").physics("batbe").feature("pce2").set("rho", "rho");
    model.component("comp1").physics("batbe").feature("pce2")
         .set("sigma", new String[]{"sigmaseff_neg", "0", "0", "0", "sigmaseff_neg", "0", "0", "0", "sigmaseff_neg"});
    model.component("comp1").physics("batbe").feature("pce2")
         .set("IntercalationOption", "NonIntercalatingParticles");
    model.component("comp1").physics("batbe").feature("pce2").set("epss", "1-batbe.epsl");
    model.component("comp1").physics("batbe").feature("pce2").set("epsl", "eps_e_neg");
    model.component("comp1").physics("batbe").feature("pce2").set("IonicCorrModel", "NoCorr");
    model.component("comp1").physics("batbe").feature("pce2").set("ElectricCorrModel", "NoCorr");
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Species", "Zn", 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("rhos", "rho_Zn", 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Ms", "MW_Zn", 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Species", "s1", 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Species", "s1", 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Species", "ZnO", 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("rhos", "rho_ZnO", 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Ms", "MW_ZnO", 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("Eeq_mat", "NernstEquation");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("Eeq_ref", "Eeq3");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1")
         .set("CRNernst", "(cl/Coh_ref)^2*(cZn/cZn_init)");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("CONernst", "cZnO/cZn_init");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1")
         .set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("i0Type", "FromNernstEquation");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("i0_ref", "i03");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("alphaa", "alphaa3*n");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1")
         .set("ActiveSpecificSurfaceAreaType", "userdef");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("Av", "a");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("nm", "n");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").setIndex("Vib", -1, 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("batbe").feature("pce2")
         .create("ivdds1", "InitialValuesForDissolvingDepositingSpecies", 1);
    model.component("comp1").physics("batbe").feature("pce2").feature("ivdds1").setIndex("c0", "cZn_init", 0, 0);
    model.component("comp1").physics("batbe").create("egnd1", "ElectricGround", 0);
    model.component("comp1").physics("batbe").feature("egnd1").selection().set(4);
    model.component("comp1").physics("batbe").create("ecd1", "ElectrodeNormalCurrentDensity", 0);
    model.component("comp1").physics("batbe").feature("ecd1").selection().set(1);
    model.component("comp1").physics("batbe").feature("ecd1").set("nis", "-I(t)");
    model.component("comp1").physics("batbe").feature("init1").set("phil", "-Eeq3");
    model.component("comp1").physics("batbe").feature("init1").set("cl", "cl_init");
    model.component("comp1").physics("batbe").feature("init1").set("phis", "(Eeq1+Eeq2)/2-Eeq3");
    model.component("comp1").physics("batbe").create("init2", "init", 1);
    model.component("comp1").physics("batbe").feature("init2").selection().set(2, 3);
    model.component("comp1").physics("batbe").feature("init2").set("phil", "-Eeq3");
    model.component("comp1").physics("batbe").feature("init2").set("cl", "cl_init");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "L_Zn", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "L_Zn", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "cZn_param", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1 20", 0);
    model.study("std1").feature("time").set("tlist", "0 1000");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_batbe_pce1_c").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_batbe_pce1_c").set("scaleval", 10000);
    model.sol("sol1").feature("v1").feature("comp1_batbe_pce2_c").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_batbe_pce2_c").set("scaleval", 10000);
    model.sol("sol1").feature("t1").set("tout", "tsteps");
    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.EndTerminal(comp1.phis)<1.25", 0);
    model.sol("sol1").feature("t1").feature("st1").set("storestopcondsol", "stepbefore_stepafter");
    model.sol("sol1").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(1);
    model.result("pg1").feature("ptgr1").set("expr", "I(t)");
    model.result("pg1").feature("ptgr1").set("unit", "A/cm^2");
    model.result("pg1").run();
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u653e\u7535\u7535\u6d41\u5bc6\u5ea6 (A/cm<sup>2</sup>)");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u65bd\u52a0\u7684\u653e\u7535\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6c60\u7535\u538b");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr1").set("expr", "phis");
    model.result("pg2").feature("ptgr1").set("descr", "\u7535\u52bf");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "Zn \u7684\u521d\u59cb\u6d53\u5ea6\u4f4e\u503c", 0);
    model.result("pg2").feature("ptgr1").setIndex("legends", "Zn \u7684\u521d\u59cb\u6d53\u5ea6\u9ad8\u503c", 1);
    model.result("pg2").run();
    model.result("pg2").set("ylabel", "\u7535\u6c60\u7535\u4f4d (V)");
    model.result("pg2").set("title", "\u7535\u6c60\u7535\u4f4d (V)");
    model.result("pg2").set("legendpos", "lowerleft");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7269\u8d28\uff1a\u6b63\u6781 (cZn_param = 1)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevelinput", "first", 1);
    model.result("pg3").setIndex("looplevelinput", "interp", 0);
    model.result("pg3").setIndex("interp", "0 100 200 300", 0);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", "cAg");
    model.result("pg3").feature("lngr1").set("descr", "Ag \u7684\u6d53\u5ea6");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").set("xdataunit", "cm");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg3").feature("lngr1").set("legendpattern", "Ag eval(t,s) s");
    model.result("pg3").feature().duplicate("lngr2", "lngr1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").set("expr", "cAgO");
    model.result("pg3").feature("lngr2").set("descr", "AgO \u7684\u6d53\u5ea6");
    model.result("pg3").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg3").feature("lngr2").set("linestyle", "dashed");
    model.result("pg3").feature("lngr2").set("legendpattern", "AgO eval(t,s) s");
    model.result("pg3").run();
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u6b63\u6781\u539a\u5ea6 (cm)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u6b63\u6781\u7684\u7269\u8d28\u53d8\u5316");
    model.result("pg3").set("legendpos", "middleleft");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7269\u8d28\uff1a\u8d1f\u6781 (cZn_param = 1)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevelinput", "first", 1);
    model.result("pg4").setIndex("looplevelinput", "interp", 0);
    model.result("pg4").setIndex("interp", "0 100 200 300", 0);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(3);
    model.result("pg4").feature("lngr1").set("expr", "cZnO");
    model.result("pg4").feature("lngr1").set("descr", "ZnO \u7684\u6d53\u5ea6");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").set("xdataunit", "cm");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg4").feature("lngr1").set("legendpattern", "ZnO eval(t,s) s");
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").set("expr", "cZn");
    model.result("pg4").feature("lngr2").set("descr", "Zn \u7684\u6d53\u5ea6");
    model.result("pg4").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg4").feature("lngr2").set("linestyle", "dashed");
    model.result("pg4").feature("lngr2").set("legendpattern", "Zn eval(t,s) s");
    model.result("pg4").run();
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u8d1f\u6781\u539a\u5ea6 (cm)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u8d1f\u6781\u7684\u7269\u8d28\u53d8\u5316");
    model.result("pg4").set("legendpos", "middleright");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u7269\u8d28\uff1a\u6b63\u6781 (cZn_param = 20)");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevelinput", "last", 1);
    model.result("pg5").setIndex("looplevelinput", "interp", 0);
    model.result("pg5").setIndex("interp", "0 200 400 600 700", 0);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().set(1);
    model.result("pg5").feature("lngr1").set("expr", "cAg");
    model.result("pg5").feature("lngr1").set("descr", "Ag \u7684\u6d53\u5ea6");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").set("xdataunit", "cm");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg5").feature("lngr1").set("legendpattern", "Ag eval(t,s) s");
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("expr", "cAgO");
    model.result("pg5").feature("lngr2").set("descr", "AgO \u7684\u6d53\u5ea6");
    model.result("pg5").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg5").feature("lngr2").set("linestyle", "dashed");
    model.result("pg5").feature("lngr2").set("legendpattern", "AgO eval(t,s) s");
    model.result("pg5").run();
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u6b63\u6781\u539a\u5ea6 (cm)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u6b63\u6781\u7684\u7269\u8d28\u53d8\u5316");
    model.result("pg5").set("legendpos", "middleleft");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u7269\u8d28\uff1a\u8d1f\u6781 (cZn_param = 20)");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevelinput", "last", 1);
    model.result("pg6").setIndex("looplevelinput", "interp", 0);
    model.result("pg6").setIndex("interp", "0 200 400 600 700", 0);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").selection().set(3);
    model.result("pg6").feature("lngr1").set("expr", "cZnO");
    model.result("pg6").feature("lngr1").set("descr", "ZnO \u7684\u6d53\u5ea6");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").set("xdataunit", "cm");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg6").feature("lngr1").set("legendpattern", "ZnO eval(t,s) s");
    model.result("pg6").feature().duplicate("lngr2", "lngr1");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").set("expr", "cZn");
    model.result("pg6").feature("lngr2").set("descr", "Zn \u7684\u6d53\u5ea6");
    model.result("pg6").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg6").feature("lngr2").set("linestyle", "dashed");
    model.result("pg6").feature("lngr2").set("legendpattern", "Zn eval(t,s) s");
    model.result("pg6").run();
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u8d1f\u6781\u539a\u5ea6 (cm)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u8d1f\u6781\u7684\u7269\u8d28\u53d8\u5316");
    model.result("pg6").set("legendpos", "middleright");

    model.title("\u950c-\u6c27\u5316\u94f6\u7535\u6c60\u7b49\u6e29\u6a21\u578b - \u4e00\u7ef4");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u4e8c\u5143\u7535\u89e3\u8d28\u7535\u6c60\u201d\u63a5\u53e3\u6765\u7814\u7a76\u950c-\u6c27\u5316\u94f6\u7535\u6c60\u7684\u653e\u7535\u8fc7\u7a0b\u3002\u5728\u8fd9\u4e00\u8fc7\u7a0b\u4e2d\uff0c\u6b63\u8d1f\u6781\u7684\u7535\u5316\u5b66\u53cd\u5e94\u4f1a\u5bfc\u81f4\u7535\u6781\u7684\u5b54\u9699\u7387\u548c\u7269\u8d28\u6d53\u5ea6\u53d1\u751f\u53d8\u5316\u3002\u6a21\u578b\u91c7\u7528\u4e00\u7ef4\u51e0\u4f55\u7ed3\u6784\uff0c\u5e76\u5728\u7b49\u6e29\u6761\u4ef6\u4e0b\u8fdb\u884c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("zn_ago_battery_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
