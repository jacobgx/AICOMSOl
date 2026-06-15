/*
 * lithium_sulfur.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:18 by COMSOL 6.3.0.290. */
public class lithium_sulfur {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Batteries,_General");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("S8");
    model.component("comp1").physics("tcd").field("concentration")
         .component(new String[]{"S8", "S8_2m", "S6_2m", "S4_2m", "S2_2m", "S_2m", "Li_1p", "A_1m"});

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/tcd", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/tcd", true);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tcd").prop("SpeciesProperties").set("FromElectroneutrality", 8);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("D_Li_1p", "0.88e-12[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cLi+");
    model.param().set("D_S8", "0.88e-11[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cS_8");
    model.param().set("D_S8_2m", "3.5e-12[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cS_8^(2-)");
    model.param().set("D_S6_2m", "3.5e-12[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cS_6^(2-)");
    model.param().set("D_S4_2m", "1.75e-12[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cS_4^(2-)");
    model.param().set("D_S2_2m", "0.88e-12[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cS_2^(2-)");
    model.param().set("D_S_2m", "0.88e-12[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cS^(2-)");
    model.param().set("D_A_1m", "3.5e-12[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0c\u76d0\u9634\u79bb\u5b50");
    model.param().set("c_Li_1p_ref", "1001[mol/m^3]", "\u53c2\u8003\u6d53\u5ea6\uff0cLi+");
    model.param().set("c_S8_ref", "19[mol/m^3]", "\u53c2\u8003\u6d53\u5ea6\uff0cS_8");
    model.param().set("c_S8_2m_ref", "0.18[mol/m^3]", "\u53c2\u8003\u6d53\u5ea6\uff0cS_8^(2-)");
    model.param().set("c_S6_2m_ref", "0.32[mol/m^3]", "\u53c2\u8003\u6d53\u5ea6\uff0cS_6^(2-)");
    model.param().set("c_S4_2m_ref", "0.02[mol/m^3]", "\u53c2\u8003\u6d53\u5ea6\uff0cS_4^(2-)");
    model.param().set("c_S2_2m_ref", "5.23e-7[mol/m^3]", "\u53c2\u8003\u6d53\u5ea6\uff0cS_2^(2-)");
    model.param().set("c_S_2m_ref", "8.27e-10[mol/m^3]", "\u53c2\u8003\u6d53\u5ea6\uff0cS^(2-)");
    model.param().set("c_A_1m_ref", "1000[mol/m^3]", "\u53c2\u8003\u6d53\u5ea6\uff0c\u76d0\u9634\u79bb\u5b50");
    model.param().set("L_sep", "25e-6[m]", "\u9694\u819c\u539a\u5ea6");
    model.param().set("L_pos", "20e-6[m]", "\u7535\u6781\u539a\u5ea6");
    model.param().set("A_cell", "0.28[m^2]", "\u7535\u6c60\u9762\u79ef");
    model.param().set("Av_0", "132762[m^2/m^3]", "\u6bd4\u8868\u9762\u79ef");
    model.param()
         .set("epsl_sep_0", "0.5", "\u65e0\u6c89\u79ef\u7269\u7684\u9694\u819c\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param()
         .set("epsl_pos_0", "0.7", "\u65e0\u6c89\u79ef\u7269\u7684\u6b63\u6781\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param().set("eps_S8_s_sep_0", "1e-12", "S8(s) \u9694\u819c\u7684\u521d\u59cb\u4f53\u79ef\u5206\u6570");
    model.param().set("eps_S8_s_pos_0", "0.166", "S8(s) \u7535\u6781\u7684\u521d\u59cb\u4f53\u79ef\u5206\u6570");
    model.param().set("eps_Li2S_s_sep_0", "1e-7", "Li2S(s) \u9694\u819c\u7684\u521d\u59cb\u4f53\u79ef\u5206\u6570");
    model.param().set("eps_Li2S_s_pos_0", "1e-7", "Li2S(s) \u7535\u6781\u7684\u521d\u59cb\u4f53\u79ef\u5206\u6570");
    model.param().set("sigma_s", "1[S/m]", "\u7535\u5bfc\u7387\uff0c\u7535\u6781");
    model.param().set("k_S8_s", "5[1/s]", "\u901f\u7387\u5e38\u6570\uff0cS8(s) \u6c89\u79ef-\u6eb6\u89e3");
    model.param().set("Ksp_S8_s", "19[mol/m^3]", "\u6eb6\u5ea6\u79ef");
    model.param().set("Vm_S8_s", "1.24e-4[m^3/mol]", "\u6469\u5c14\u4f53\u79ef\uff0cS8(s)");
    model.param()
         .set("k_Li2S_s", "3.45e-5[m^6*mol^-2/s]", "\u901f\u7387\u5e38\u6570\uff0cLi2S(s) \u6c89\u79ef-\u6eb6\u89e3");
    model.param().set("Ksp_Li2S_s", "1e2[mol^3*m^-9]", "\u6eb6\u5ea6\u79ef");
    model.param().set("Vm_Li2S_s", "2.4e-5[m^3/mol]", "\u6469\u5c14\u4f53\u79ef\uff0cLi2S(s)");
    model.param().set("M_Li2S_s", "(32.07+2*6.941)[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cLi2S(s)");
    model.param().set("M_S8_s", "8*32.07[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cS8(s)");
    model.param().set("rho_Li2S_s", "M_Li2S_s/Vm_Li2S_s", "\u5bc6\u5ea6\uff0cLi2S(s)");
    model.param().set("rho_S8_s", "M_S8_s/Vm_S8_s", "\u5bc6\u5ea6\uff0cS8(s)");
    model.param()
         .set("Eeq_Li_ref", "0[V]-R_const*T/F_const*log((c_Li_1p_ref/1[M])^(-1))", "\u53c2\u8003\u6d53\u5ea6\u7684\u5e73\u8861\u7535\u4f4d\uff0cLi/Li+ \u53cd\u5e94");
    model.param()
         .set("Eeq_1_ref", "2.41[V]-R_const*T/F_const*log((c_S8_ref       /1[M])^(-1/2) *(c_S8_2m_ref/1[M])^(1/2))", "\u53c2\u8003\u6d53\u5ea6\u7684\u5e73\u8861\u7535\u4f4d\uff0c\u53cd\u5e94 1");
    model.param()
         .set("Eeq_2_ref", "2.35[V]-R_const*T/F_const*log((c_S8_2m_ref/1[M])^(-3/2)*(c_S6_2m_ref/1[M])^2)", "\u53c2\u8003\u6d53\u5ea6\u7684\u5e73\u8861\u7535\u4f4d\uff0c\u53cd\u5e94 2");
    model.param()
         .set("Eeq_3_ref", "2.23[V]-R_const*T/F_const*log((c_S6_2m_ref/1[M])^(-1)    *(c_S4_2m_ref/1[M])^(3/2))", "\u53c2\u8003\u6d53\u5ea6\u7684\u5e73\u8861\u7535\u4f4d\uff0c\u53cd\u5e94 3");
    model.param()
         .set("Eeq_4_ref", "2.03[V]-R_const*T/F_const*log((c_S4_2m_ref/1[M])^(-1/2) *(c_S2_2m_ref/1[M])^(1))", "\u53c2\u8003\u6d53\u5ea6\u7684\u5e73\u8861\u7535\u4f4d\uff0c\u53cd\u5e94 4");
    model.param()
         .set("Eeq_5_ref", "2.01[V]-R_const*T/F_const*log((c_S2_2m_ref/1[M])^(-1/2) *(c_S_2m_ref  /1[M])^(1))", "\u53c2\u8003\u6d53\u5ea6\u7684\u5e73\u8861\u7535\u4f4d\uff0c\u53cd\u5e94 5");
    model.param()
         .set("i0_Li_ref", "0.5[A/m^2]", "\u53c2\u8003\u6d53\u5ea6\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0cLi/Li+ \u53cd\u5e94");
    model.param()
         .set("i0_1_ref", "1.9[A/m^2]", "\u53c2\u8003\u6d53\u5ea6\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u53cd\u5e94 1");
    model.param()
         .set("i0_2_ref", "0.02[A/m^2]", "\u53c2\u8003\u6d53\u5ea6\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u53cd\u5e94 2");
    model.param()
         .set("i0_3_ref", "0.02[A/m^2]", "\u53c2\u8003\u6d53\u5ea6\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u53cd\u5e94 3");
    model.param()
         .set("i0_4_ref", "2e-4[A/m^2]", "\u53c2\u8003\u6d53\u5ea6\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u53cd\u5e94 4");
    model.param()
         .set("i0_5_ref", "2e-9[A/m^2]*0+2e-7[A/m^2]", "\u53c2\u8003\u6d53\u5ea6\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u53cd\u5e94 5");
    model.param()
         .set("Cap", "16*(c_S8_ref+eps_S8_s_pos_0/Vm_S8_s)*L_pos*F_const", "\u7535\u6c60\u5bb9\u91cf\uff08\u7406\u8bba\uff09");
    model.param().set("i_1C", "Cap_nom/1[h]", "1C \u7535\u6d41\u5bc6\u5ea6");
    model.param().set("Cap_nom", "3.4[A*h]/A_cell", "\u7535\u6c60\u5bb9\u91cf\uff08\u6807\u79f0\uff09");
    model.param().set("C", "0.2", "\u500d\u7387");
    model.param().set("T", "30[degC]", "\u6e29\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_sep", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_pos", 1);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -2, 1);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -2, 2);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -2, 3);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -2, 4);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -2, 5);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 1, 6);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -1, 7);
    model.component("comp1").physics("tcd").create("sep1", "Separator", 1);
    model.component("comp1").physics("tcd").feature("sep1").selection().set(1);
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_S8", new String[]{"D_S8", "0", "0", "0", "D_S8", "0", "0", "0", "D_S8"});
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_S8_2m", new String[]{"D_S8_2m", "0", "0", "0", "D_S8_2m", "0", "0", "0", "D_S8_2m"});
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_S6_2m", new String[]{"D_S6_2m", "0", "0", "0", "D_S6_2m", "0", "0", "0", "D_S6_2m"});
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_S4_2m", new String[]{"D_S4_2m", "0", "0", "0", "D_S4_2m", "0", "0", "0", "D_S4_2m"});
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_S2_2m", new String[]{"D_S2_2m", "0", "0", "0", "D_S2_2m", "0", "0", "0", "D_S2_2m"});
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_S_2m", new String[]{"D_S_2m", "0", "0", "0", "D_S_2m", "0", "0", "0", "D_S_2m"});
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_Li_1p", new String[]{"D_Li_1p", "0", "0", "0", "D_Li_1p", "0", "0", "0", "D_Li_1p"});
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_A_1m", new String[]{"D_A_1m", "0", "0", "0", "D_A_1m", "0", "0", "0", "D_A_1m"});
    model.component("comp1").physics("tcd").feature("sep1").set("epsl", "epsl_sep_0");
    model.component("comp1").physics("tcd").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("tcd").feature("pce1").selection().set(2);
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_S8", new String[]{"D_S8", "0", "0", "0", "D_S8", "0", "0", "0", "D_S8"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_S8_2m", new String[]{"D_S8_2m", "0", "0", "0", "D_S8_2m", "0", "0", "0", "D_S8_2m"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_S6_2m", new String[]{"D_S6_2m", "0", "0", "0", "D_S6_2m", "0", "0", "0", "D_S6_2m"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_S4_2m", new String[]{"D_S4_2m", "0", "0", "0", "D_S4_2m", "0", "0", "0", "D_S4_2m"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_S2_2m", new String[]{"D_S2_2m", "0", "0", "0", "D_S2_2m", "0", "0", "0", "D_S2_2m"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_S_2m", new String[]{"D_S_2m", "0", "0", "0", "D_S_2m", "0", "0", "0", "D_S_2m"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_Li_1p", new String[]{"D_Li_1p", "0", "0", "0", "D_Li_1p", "0", "0", "0", "D_Li_1p"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_A_1m", new String[]{"D_A_1m", "0", "0", "0", "D_A_1m", "0", "0", "0", "D_A_1m"});
    model.component("comp1").physics("tcd").feature("pce1").set("sigma_mat", "userdef");
    model.component("comp1").physics("tcd").feature("pce1")
         .set("sigma", new String[]{"sigma_s", "0", "0", "0", "sigma_s", "0", "0", "0", "sigma_s"});
    model.component("comp1").physics("tcd").feature("pce1").set("epss", "1-epsl_pos_0");
    model.component("comp1").physics("tcd").feature("pce1").set("epsl", "epsl_pos_0");
    model.component("comp1").physics("tcd").feature("pce1").set("ElectricCorrModel", "NoCorr");
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").setIndex("Vi0", "-1/2", 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").setIndex("Vi0", "1/2", 1);
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("Eeq_ref", "Eeq_1_ref");
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").setIndex("cref", "c_S8_ref", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").setIndex("cref", "c_S8_2m_ref", 1, 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("i0_ref", "i0_1_ref");
    model.component("comp1").physics("tcd").feature("pce1").create("per2", "PorousElectrodeReaction", 1);
    model.component("comp1").physics("tcd").feature("pce1").feature("per2").setIndex("Vi0", "-3/2", 1);
    model.component("comp1").physics("tcd").feature("pce1").feature("per2").setIndex("Vi0", 2, 2);
    model.component("comp1").physics("tcd").feature("pce1").feature("per2").set("Eeq_ref", "Eeq_2_ref");
    model.component("comp1").physics("tcd").feature("pce1").feature("per2").setIndex("cref", "c_S8_2m_ref", 1, 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("per2").setIndex("cref", "c_S6_2m_ref", 2, 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("per2").set("i0_ref", "i0_2_ref");
    model.component("comp1").physics("tcd").feature("pce1").create("per3", "PorousElectrodeReaction", 1);
    model.component("comp1").physics("tcd").feature("pce1").feature("per3").setIndex("Vi0", -1, 2);
    model.component("comp1").physics("tcd").feature("pce1").feature("per3").setIndex("Vi0", "3/2", 3);
    model.component("comp1").physics("tcd").feature("pce1").feature("per3").set("Eeq_ref", "Eeq_3_ref");
    model.component("comp1").physics("tcd").feature("pce1").feature("per3").setIndex("cref", "c_S6_2m_ref", 2, 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("per3").setIndex("cref", "c_S4_2m_ref", 3, 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("per3").set("i0_ref", "i0_3_ref");
    model.component("comp1").physics("tcd").feature("pce1").create("per4", "PorousElectrodeReaction", 1);
    model.component("comp1").physics("tcd").feature("pce1").feature("per4").setIndex("Vi0", "-1/2", 3);
    model.component("comp1").physics("tcd").feature("pce1").feature("per4").setIndex("Vi0", 1, 4);
    model.component("comp1").physics("tcd").feature("pce1").feature("per4").set("Eeq_ref", "Eeq_4_ref");
    model.component("comp1").physics("tcd").feature("pce1").feature("per4").setIndex("cref", "c_S4_2m_ref", 3, 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("per4").setIndex("cref", "c_S2_2m_ref", 4, 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("per4").set("i0_ref", "i0_4_ref");
    model.component("comp1").physics("tcd").feature("pce1").create("per5", "PorousElectrodeReaction", 1);
    model.component("comp1").physics("tcd").feature("pce1").feature("per5").setIndex("Vi0", "-1/2", 4);
    model.component("comp1").physics("tcd").feature("pce1").feature("per5").setIndex("Vi0", 1, 5);
    model.component("comp1").physics("tcd").feature("pce1").feature("per5").set("Eeq_ref", "Eeq_5_ref");
    model.component("comp1").physics("tcd").feature("pce1").feature("per5").setIndex("cref", "c_S2_2m_ref", 4, 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("per5").setIndex("cref", "c_S_2m_ref", 5, 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("per5").set("i0_ref", "i0_5_ref");
    model.component("comp1").physics("tcd").feature("sep1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("Species", "Li2S_s", 0, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("rhos", "rho_Li2S_s", 0, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("Ms", "M_Li2S_s", 0, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("Species", "s1", 1, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("Species", "s1", 1, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("Species", "S8_s", 1, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("rhos", "rho_S8_s", 1, 0);
    model.component("comp1").physics("tcd").feature("sep1").setIndex("Ms", "M_S8_s", 1, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Species", "Li2S_s", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("rhos", "rho_Li2S_s", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Ms", "M_Li2S_s", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Species", "s1", 1, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Species", "s1", 1, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Species", "S8_s", 1, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("rhos", "rho_S8_s", 1, 0);
    model.component("comp1").physics("tcd").feature("pce1").setIndex("Ms", "M_S8_s", 1, 0);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf - \u9694\u819c");
    model.component("comp1").variable("var1").selection().geom("geom1", 1);
    model.component("comp1").variable("var1").selection().set(1);

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("eps_Li2S_s", "tcd.c_sep1_Li2S_s*Vm_Li2S_s", "\u4f53\u79ef\u5206\u6570\uff0cLi2S(s)");
    model.component("comp1").variable("var1")
         .set("eps_S8_s", "tcd.c_sep1_S8_s*Vm_S8_s", "\u4f53\u79ef\u5206\u6570\uff0cS8(s)");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u53d8\u91cf - \u6b63\u6781");
    model.component("comp1").variable("var2").selection().geom("geom1", 1);
    model.component("comp1").variable("var2").selection().set(2);

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("Av_pos", "Av_0*(tcd.epsl/epsl_pos_0)^1.5", "\u6bd4\u8868\u9762\u79ef");
    model.component("comp1").variable("var2")
         .set("eps_Li2S_s", "tcd.c_pce1_Li2S_s*Vm_Li2S_s", "\u4f53\u79ef\u5206\u6570\uff0cLi2S(s)");
    model.component("comp1").variable("var2")
         .set("eps_S8_s", "tcd.c_pce1_S8_s*Vm_S8_s", "\u4f53\u79ef\u5206\u6570\uff0cS8(s)");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u53d8\u91cf - \u6240\u6709\u57df");

//    To import content from file, use:
//    model.component("comp1").variable("var3").loadFile("FILENAME");
    model.component("comp1").variable("var3")
         .set("R_Li2S_s", "k_Li2S_s*max(eps_Li2S_s,eps)*(max(Li_1p,eps)^2*max(S_2m,eps)-Ksp_Li2S_s)", "\u53cd\u5e94\u901f\u7387\uff0cLi2S(s) \u6c89\u6dc0");
    model.component("comp1").variable("var3")
         .set("R_S8_s", "k_S8_s*max(eps_S8_s,eps)*(S8-Ksp_S8_s)", "\u53cd\u5e94\u901f\u7387\uff0cS8(s) \u6c89\u6dc0");

    model.component("comp1").physics("tcd").feature("sep1").feature("nfr1")
         .label("\u975e\u6cd5\u62c9\u7b2c\u53cd\u5e94 - Li2S(s)");
    model.component("comp1").physics("tcd").feature("sep1").feature("nfr1")
         .setIndex("R_S_2m", "-R_Li2S_s/tcd.epsl", 0);
    model.component("comp1").physics("tcd").feature("sep1").feature("nfr1")
         .setIndex("R_Li_1p", "-2*R_Li2S_s/tcd.epsl", 0);
    model.component("comp1").physics("tcd").feature("sep1").feature("nfr1").setIndex("R", "R_Li2S_s", 0, 0);
    model.component("comp1").physics("tcd").feature("sep1").create("nfr2", "NonFaradaicReactions", 1);
    model.component("comp1").physics("tcd").feature("sep1").feature("nfr2")
         .label("\u975e\u6cd5\u62c9\u7b2c\u53cd\u5e94 - S8(s)");
    model.component("comp1").physics("tcd").feature("sep1").feature("nfr2").setIndex("R_S8", "-R_S8_s/tcd.epsl", 0);
    model.component("comp1").physics("tcd").feature("sep1").feature("nfr2").setIndex("R", "R_S8_s", 1, 0);
    model.component("comp1").physics("tcd").feature("sep1")
         .create("ivdds1", "InitialValuesForDissolvingDepositingSpecies", 1);
    model.component("comp1").physics("tcd").feature("sep1").feature("ivdds1")
         .setIndex("c0", "eps_Li2S_s_sep_0/Vm_Li2S_s", 0, 0);
    model.component("comp1").physics("tcd").feature("sep1").feature("ivdds1")
         .setIndex("c0", "eps_S8_s_sep_0/Vm_S8_s", 1, 0);
    model.component("comp1").physics("tcd").feature("pce1").create("nfr1", "NonFaradaicReactions", 1);
    model.component("comp1").physics("tcd").feature("pce1").feature("nfr1")
         .label("\u975e\u6cd5\u62c9\u7b2c\u53cd\u5e94 - Li2S(s)");
    model.component("comp1").physics("tcd").feature("pce1").feature("nfr1")
         .setIndex("R_S_2m", "-R_Li2S_s/tcd.epsl", 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("nfr1")
         .setIndex("R_Li_1p", "-2*R_Li2S_s/tcd.epsl", 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("nfr1").setIndex("R", "R_Li2S_s", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").create("nfr2", "NonFaradaicReactions", 1);
    model.component("comp1").physics("tcd").feature("pce1").feature("nfr2")
         .label("\u975e\u6cd5\u62c9\u7b2c\u53cd\u5e94 - S8(s)");
    model.component("comp1").physics("tcd").feature("pce1").feature("nfr2").setIndex("R_S8", "-R_S8_s/tcd.epsl", 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("nfr2").setIndex("R", "R_S8_s", 1, 0);
    model.component("comp1").physics("tcd").feature("pce1")
         .create("ivdds1", "InitialValuesForDissolvingDepositingSpecies", 1);
    model.component("comp1").physics("tcd").feature("pce1").feature("ivdds1")
         .setIndex("c0", "eps_Li2S_s_pos_0/Vm_Li2S_s", 0, 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("ivdds1")
         .setIndex("c0", "eps_S8_s_pos_0/Vm_S8_s", 1, 0);
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("Av", "Av_pos");
    model.component("comp1").physics("tcd").feature("pce1").feature("per2").set("Av", "Av_pos");
    model.component("comp1").physics("tcd").feature("pce1").feature("per3").set("Av", "Av_pos");
    model.component("comp1").physics("tcd").feature("pce1").feature("per4").set("Av", "Av_pos");
    model.component("comp1").physics("tcd").feature("pce1").feature("per5").set("Av", "Av_pos");
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("tcd").feature("es1").selection().set(1);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 6);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq_ref", "Eeq_Li_ref");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0_Li_ref");
    model.component("comp1").physics("tcd").create("ecd1", "ElectrodeNormalCurrentDensity", 0);
    model.component("comp1").physics("tcd").feature("ecd1").selection().set(3);
    model.component("comp1").physics("tcd").feature("ecd1").set("nis", "-i_1C*C");
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_S8_ref", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_S8_2m_ref", 1);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_S6_2m_ref", 2);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_S4_2m_ref", 3);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_S2_2m_ref", 4);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_S_2m_ref", 5);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_Li_1p_ref", 6);
    model.component("comp1").physics("tcd").feature("init1").set("initphis", "Eeq_1_ref");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", "1e-7");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "D_Li_1p", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m^2/s", 0);
    model.study("std1").feature("param").setIndex("pname", "D_Li_1p", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m^2/s", 0);
    model.study("std1").feature("param").setIndex("pname", "C", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.2 1", 0);
    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,0.01/C,1/C)");

    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe("pdom1").setIndex("coords1", "L_sep+L_pos", 0);
    model.component("comp1").probe("pdom1").set("bndsnap1", true);
    model.component("comp1").probe("pdom1").feature("ppb1").set("expr", "phis");
    model.component("comp1").probe("pdom1").feature("ppb1").set("descr", "\u7535\u52bf");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_pos_cc");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(3);

    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.intop_pos_cc(comp1.phis)<1.7", 0);
    model.sol("sol1").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");

    model.component("comp1").probe("pdom1").genResult("none");

    model.batch("p1").run("compute");

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").selection().all();
    model.result("pg2").feature("ptgr1").set("expr", new String[]{"phis"});
    model.result("pg2").feature("ptgr1").selection().set(3);
    model.result("pg2").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (tcd)");
    model.result("pg2").feature("ptgr1").set("xdatasolnumtype", "level1");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u4f4d (tcd)");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1, 2);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"phil"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (tcd)");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1, 2);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").feature("lngr1").set("expr", new String[]{"phis"});
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28");
    model.result("pg5").label("\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28 (tcd)");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1, 2);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"S8"});
    model.result("pg5").feature("lngr1").label("\u7269\u8d28 S8");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("autosolution", false);
    model.result("pg5").feature("lngr1").set("autoexpr", false);
    model.result("pg5").feature("lngr1").set("autodescr", false);
    model.result("pg5").feature("lngr1").set("legendprefix", "S8 ");
    model.result("pg5").feature("lngr1").set("descractive", true);
    model.result("pg5").feature("lngr1").set("descr", "\u6d53\u5ea6");
    model.result("pg5").create("lngr2", "LineGraph");
    model.result("pg5").feature("lngr2").set("xdata", "expr");
    model.result("pg5").feature("lngr2").set("xdataexpr", "x");
    model.result("pg5").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr2").selection().set(1, 2);
    model.result("pg5").feature("lngr2").set("expr", new String[]{"S8_2m"});
    model.result("pg5").feature("lngr2").label("\u7269\u8d28 S8_2m");
    model.result("pg5").feature("lngr2").set("legend", true);
    model.result("pg5").feature("lngr2").set("autosolution", false);
    model.result("pg5").feature("lngr2").set("autoexpr", false);
    model.result("pg5").feature("lngr2").set("autodescr", false);
    model.result("pg5").feature("lngr2").set("legendprefix", "S8_2m ");
    model.result("pg5").feature("lngr2").set("descractive", true);
    model.result("pg5").feature("lngr2").set("descr", "\u6d53\u5ea6");
    model.result("pg5").create("lngr3", "LineGraph");
    model.result("pg5").feature("lngr3").set("xdata", "expr");
    model.result("pg5").feature("lngr3").set("xdataexpr", "x");
    model.result("pg5").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr3").selection().set(1, 2);
    model.result("pg5").feature("lngr3").set("expr", new String[]{"S6_2m"});
    model.result("pg5").feature("lngr3").label("\u7269\u8d28 S6_2m");
    model.result("pg5").feature("lngr3").set("legend", true);
    model.result("pg5").feature("lngr3").set("autosolution", false);
    model.result("pg5").feature("lngr3").set("autoexpr", false);
    model.result("pg5").feature("lngr3").set("autodescr", false);
    model.result("pg5").feature("lngr3").set("legendprefix", "S6_2m ");
    model.result("pg5").feature("lngr3").set("descractive", true);
    model.result("pg5").feature("lngr3").set("descr", "\u6d53\u5ea6");
    model.result("pg5").create("lngr4", "LineGraph");
    model.result("pg5").feature("lngr4").set("xdata", "expr");
    model.result("pg5").feature("lngr4").set("xdataexpr", "x");
    model.result("pg5").feature("lngr4").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr4").selection().set(1, 2);
    model.result("pg5").feature("lngr4").set("expr", new String[]{"S4_2m"});
    model.result("pg5").feature("lngr4").label("\u7269\u8d28 S4_2m");
    model.result("pg5").feature("lngr4").set("legend", true);
    model.result("pg5").feature("lngr4").set("autosolution", false);
    model.result("pg5").feature("lngr4").set("autoexpr", false);
    model.result("pg5").feature("lngr4").set("autodescr", false);
    model.result("pg5").feature("lngr4").set("legendprefix", "S4_2m ");
    model.result("pg5").feature("lngr4").set("descractive", true);
    model.result("pg5").feature("lngr4").set("descr", "\u6d53\u5ea6");
    model.result("pg5").create("lngr5", "LineGraph");
    model.result("pg5").feature("lngr5").set("xdata", "expr");
    model.result("pg5").feature("lngr5").set("xdataexpr", "x");
    model.result("pg5").feature("lngr5").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr5").selection().set(1, 2);
    model.result("pg5").feature("lngr5").set("expr", new String[]{"S2_2m"});
    model.result("pg5").feature("lngr5").label("\u7269\u8d28 S2_2m");
    model.result("pg5").feature("lngr5").set("legend", true);
    model.result("pg5").feature("lngr5").set("autosolution", false);
    model.result("pg5").feature("lngr5").set("autoexpr", false);
    model.result("pg5").feature("lngr5").set("autodescr", false);
    model.result("pg5").feature("lngr5").set("legendprefix", "S2_2m ");
    model.result("pg5").feature("lngr5").set("descractive", true);
    model.result("pg5").feature("lngr5").set("descr", "\u6d53\u5ea6");
    model.result("pg5").create("lngr6", "LineGraph");
    model.result("pg5").feature("lngr6").set("xdata", "expr");
    model.result("pg5").feature("lngr6").set("xdataexpr", "x");
    model.result("pg5").feature("lngr6").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr6").selection().set(1, 2);
    model.result("pg5").feature("lngr6").set("expr", new String[]{"S_2m"});
    model.result("pg5").feature("lngr6").label("\u7269\u8d28 S_2m");
    model.result("pg5").feature("lngr6").set("legend", true);
    model.result("pg5").feature("lngr6").set("autosolution", false);
    model.result("pg5").feature("lngr6").set("autoexpr", false);
    model.result("pg5").feature("lngr6").set("autodescr", false);
    model.result("pg5").feature("lngr6").set("legendprefix", "S_2m ");
    model.result("pg5").feature("lngr6").set("descractive", true);
    model.result("pg5").feature("lngr6").set("descr", "\u6d53\u5ea6");
    model.result("pg5").create("lngr7", "LineGraph");
    model.result("pg5").feature("lngr7").set("xdata", "expr");
    model.result("pg5").feature("lngr7").set("xdataexpr", "x");
    model.result("pg5").feature("lngr7").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr7").selection().set(1, 2);
    model.result("pg5").feature("lngr7").set("expr", new String[]{"Li_1p"});
    model.result("pg5").feature("lngr7").label("\u7269\u8d28 Li_1p");
    model.result("pg5").feature("lngr7").set("legend", true);
    model.result("pg5").feature("lngr7").set("autosolution", false);
    model.result("pg5").feature("lngr7").set("autoexpr", false);
    model.result("pg5").feature("lngr7").set("autodescr", false);
    model.result("pg5").feature("lngr7").set("legendprefix", "Li_1p ");
    model.result("pg5").feature("lngr7").set("descractive", true);
    model.result("pg5").feature("lngr7").set("descr", "\u6d53\u5ea6");
    model.result("pg5").create("lngr8", "LineGraph");
    model.result("pg5").feature("lngr8").set("xdata", "expr");
    model.result("pg5").feature("lngr8").set("xdataexpr", "x");
    model.result("pg5").feature("lngr8").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr8").selection().set(1, 2);
    model.result("pg5").feature("lngr8").set("expr", new String[]{"A_1m"});
    model.result("pg5").feature("lngr8").label("\u7269\u8d28 A_1m");
    model.result("pg5").feature("lngr8").set("legend", true);
    model.result("pg5").feature("lngr8").set("autosolution", false);
    model.result("pg5").feature("lngr8").set("autoexpr", false);
    model.result("pg5").feature("lngr8").set("autodescr", false);
    model.result("pg5").feature("lngr8").set("legendprefix", "A_1m ");
    model.result("pg5").feature("lngr8").set("descractive", true);
    model.result("pg5").feature("lngr8").set("descr", "\u6d53\u5ea6");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").label("\u6d53\u5ea6, S8 (tcd)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1, 2);
    model.result("pg6").feature("lngr1").set("expr", new String[]{"S8"});
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").label("\u6d53\u5ea6, S8_2m (tcd)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("prefixintitle", "");
    model.result("pg7").set("expressionintitle", false);
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "x");
    model.result("pg7").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg7").feature("lngr1").selection().set(1, 2);
    model.result("pg7").feature("lngr1").set("expr", new String[]{"S8_2m"});
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").label("\u6d53\u5ea6, S6_2m (tcd)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "x");
    model.result("pg8").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg8").feature("lngr1").selection().set(1, 2);
    model.result("pg8").feature("lngr1").set("expr", new String[]{"S6_2m"});
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").label("\u6d53\u5ea6, S4_2m (tcd)");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("prefixintitle", "");
    model.result("pg9").set("expressionintitle", false);
    model.result("pg9").set("typeintitle", false);
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").set("xdataexpr", "x");
    model.result("pg9").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg9").feature("lngr1").selection().set(1, 2);
    model.result("pg9").feature("lngr1").set("expr", new String[]{"S4_2m"});
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").label("\u6d53\u5ea6, S2_2m (tcd)");
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("prefixintitle", "");
    model.result("pg10").set("expressionintitle", false);
    model.result("pg10").set("typeintitle", false);
    model.result("pg10").create("lngr1", "LineGraph");
    model.result("pg10").feature("lngr1").set("xdata", "expr");
    model.result("pg10").feature("lngr1").set("xdataexpr", "x");
    model.result("pg10").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg10").feature("lngr1").selection().set(1, 2);
    model.result("pg10").feature("lngr1").set("expr", new String[]{"S2_2m"});
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").label("\u6d53\u5ea6, S_2m (tcd)");
    model.result("pg11").set("titletype", "custom");
    model.result("pg11").set("prefixintitle", "");
    model.result("pg11").set("expressionintitle", false);
    model.result("pg11").set("typeintitle", false);
    model.result("pg11").create("lngr1", "LineGraph");
    model.result("pg11").feature("lngr1").set("xdata", "expr");
    model.result("pg11").feature("lngr1").set("xdataexpr", "x");
    model.result("pg11").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg11").feature("lngr1").selection().set(1, 2);
    model.result("pg11").feature("lngr1").set("expr", new String[]{"S_2m"});
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").set("data", "dset3");
    model.result("pg12").label("\u6d53\u5ea6, Li_1p (tcd)");
    model.result("pg12").set("titletype", "custom");
    model.result("pg12").set("prefixintitle", "");
    model.result("pg12").set("expressionintitle", false);
    model.result("pg12").set("typeintitle", false);
    model.result("pg12").create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").set("xdata", "expr");
    model.result("pg12").feature("lngr1").set("xdataexpr", "x");
    model.result("pg12").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg12").feature("lngr1").selection().set(1, 2);
    model.result("pg12").feature("lngr1").set("expr", new String[]{"Li_1p"});
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").set("data", "dset3");
    model.result("pg13").label("\u6d53\u5ea6, A_1m (tcd)");
    model.result("pg13").set("titletype", "custom");
    model.result("pg13").set("prefixintitle", "");
    model.result("pg13").set("expressionintitle", false);
    model.result("pg13").set("typeintitle", false);
    model.result("pg13").create("lngr1", "LineGraph");
    model.result("pg13").feature("lngr1").set("xdata", "expr");
    model.result("pg13").feature("lngr1").set("xdataexpr", "x");
    model.result("pg13").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg13").feature("lngr1").selection().set(1, 2);
    model.result("pg13").feature("lngr1").set("expr", new String[]{"A_1m"});
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6c60\u7535\u538b");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr1").set("xdata", "expr");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "t*C*i_1C*A_cell/1[A*h]");
    model.result("pg2").feature("ptgr1").set("xdatadescractive", true);
    model.result("pg2").feature("ptgr1").set("xdatadescr", "\u5bb9\u91cf (Ah)");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("autopoint", false);
    model.result("pg2").run();
    model.result("pg2").set("titletype", "label");
    model.result("pg2").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevelinput", "last", 0);
    model.result("pg5").setIndex("looplevelinput", "manual", 1);
    model.result("pg5").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("legendprefix", "S<sub>8 </sub>");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("legendprefix", "S<sup>2-</sup><sub>8</sub>");
    model.result("pg5").run();
    model.result("pg5").feature("lngr3").set("legendprefix", "S<sup>2-</sup><sub>6</sub>");
    model.result("pg5").run();
    model.result("pg5").feature("lngr4").set("legendprefix", "S<sup>2-</sup><sub>4</sub>");
    model.result("pg5").run();
    model.result("pg5").feature("lngr5").set("legendprefix", "S<sup>2-</sup><sub>2</sub>");
    model.result("pg5").run();
    model.result("pg5").feature("lngr6").set("legendprefix", "S<sup>2-</sup>");
    model.result("pg5").run();
    model.result("pg5").feature("lngr7").set("legendprefix", "Li<sup>+</sup>");
    model.result("pg5").run();
    model.result("pg5").feature("lngr8").set("legendprefix", "A<sup>-</sup>");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg5").run();
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").run();
    model.result("pg14").label("\u6c89\u79ef\u7684 Li2S(s)");
    model.result("pg14").set("titletype", "label");
    model.result("pg14").create("lngr1", "LineGraph");
    model.result("pg14").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg14").feature("lngr1").set("linewidth", "preference");
    model.result("pg14").feature("lngr1").selection().all();
    model.result("pg14").feature("lngr1").set("expr", "eps_Li2S_s");
    model.result("pg14").feature("lngr1").set("descr", "\u4f53\u79ef\u5206\u6570\uff0cLi2S(s)");
    model.result("pg14").feature("lngr1").set("xdata", "expr");
    model.result("pg14").feature("lngr1").set("xdataexpr", "x");
    model.result("pg14").feature("lngr1").set("legendprefix", "Li<sub>2</sub>S(s)");
    model.result("pg14").run();
    model.result("pg14").set("data", "dset3");
    model.result("pg14").setIndex("looplevelinput", "manual", 1);
    model.result("pg14").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg14").run();
    model.result("pg14").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg14").run();

    model.title("\u9502-\u786b\u7535\u6c60");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u9502-\u786b\u7535\u6c60\u5355\u5143\u5728\u4e24\u79cd\u4e0d\u540c\u653e\u7535\u7387\u4e0b\u7684\u653e\u7535\u8fc7\u7a0b\uff0c\u6db5\u76d6\u4e86\u9502\u76d0\u548c\u516d\u79cd\u591a\u786b\u5316\u7269\u7684\u7535\u89e3\u8d28\u7535\u8377\u548c\u8d28\u91cf\u4f20\u9012\uff0c\u4ee5\u53ca\u9694\u819c\u548c\u6b63\u6781\u4e2d\u56fa\u4f53\u516b\u786b (S8) \u548c\u786b\u5316\u9502 (Li2S) \u7684\u6c89\u6dc0-\u6eb6\u89e3\u8fc7\u7a0b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("lithium_sulfur.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
