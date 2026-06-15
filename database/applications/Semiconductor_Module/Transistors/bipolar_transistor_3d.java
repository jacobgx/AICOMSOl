/*
 * bipolar_transistor_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:54 by COMSOL 6.3.0.290. */
public class bipolar_transistor_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Transistors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/semi", true);

    model.param().set("T0", "293[K]");
    model.param().descr("T0", "\u53c2\u8003\u6e29\u5ea6");
    model.param().set("w_BJT", "2.5[um]");
    model.param().descr("w_BJT", "BJT \u5bbd\u5ea6");
    model.param().set("d_BJT", "1[um]");
    model.param().descr("d_BJT", "BJT \u539a\u5ea6");
    model.param().set("l_BJT", "3[um]");
    model.param().descr("l_BJT", "BJT \u957f\u5ea6");
    model.param().set("w_E", "1.2[um]");
    model.param().descr("w_E", "\u53d1\u5c04\u6781\u5bbd\u5ea6");
    model.param().set("l_E", "3[um]");
    model.param().descr("l_E", "\u53d1\u5c04\u6781\u957f\u5ea6");
    model.param().set("w_EB", "0.35[um]");
    model.param().descr("w_EB", "\u53d1\u5c04\u6781-\u57fa\u6781\u95f4\u8ddd");
    model.param().set("w_cE", "w_E/2-d_E");
    model.param().descr("w_cE", "\u53d1\u5c04\u6781\u63a5\u89e6\u5bbd\u5ea6");
    model.param().set("w_cB", "w_BJT/2-w_EB-w_E/2");
    model.param().descr("w_cB", "\u57fa\u6781\u63a5\u89e6\u5bbd\u5ea6");
    model.param().set("l_cB", "3[um]");
    model.param().descr("l_cB", "\u57fa\u6781\u63a5\u89e6\u957f\u5ea6");
    model.param().set("w_cC", "w_BJT");
    model.param().descr("w_cC", "\u96c6\u7535\u6781\u63a5\u89e6\u5bbd\u5ea6");
    model.param().set("d_E", "0.15[um]");
    model.param().descr("d_E", "\u53d1\u5c04\u6781\u7ed3\u6df1");
    model.param().set("d_B", "0.3[um]");
    model.param().descr("d_B", "\u57fa\u6781\u7ed3\u6df1");
    model.param().set("d_C", "0.3[um]");
    model.param().descr("d_C", "\u96c6\u7535\u6781\u7ed3\u6df1");
    model.param().set("N_epi", "2e16[1/cm^3]");
    model.param().descr("N_epi", "\u5916\u5ef6\u5c42\u63ba\u6742\u6d53\u5ea6");
    model.param().set("N_B", "9e17[1/cm^3]");
    model.param().descr("N_B", "\u57fa\u6781\u63ba\u6742\u6d53\u5ea6");
    model.param().set("N_E", "2e20[1/cm^3]");
    model.param().descr("N_E", "\u53d1\u5c04\u6781\u63ba\u6742\u6d53\u5ea6");
    model.param().set("N_C", "8e19[1/cm^3]");
    model.param().descr("N_C", "\u96c6\u7535\u6781\u63ba\u6742\u6d53\u5ea6");
    model.param().set("V_C", "0.5[V]");
    model.param().descr("V_C", "\u5916\u52a0\u7535\u538b\uff1a\u96c6\u7535\u6781");
    model.param().set("V_B", "0.5[V]");
    model.param().descr("V_B", "\u5916\u52a0\u7535\u538b\uff1a\u57fa\u6781");
    model.param().set("V_E", "0[V]");
    model.param().descr("V_E", "\u5916\u52a0\u7535\u538b\uff1a\u53d1\u5c04\u6781");
    model.param().set("I_B", "-1[uA]");
    model.param().descr("I_B", "\u5411\u5185\u5916\u52a0\u7535\u6d41\uff1a\u57fa\u6781");
    model.param().set("I_C", "0[uA]");
    model.param().descr("I_C", "\u5411\u5185\u5916\u52a0\u7535\u6d41\uff1a\u96c6\u7535\u6781");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"w_BJT/2", "l_BJT/2", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "d_BJT", 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"w_BJT/2", "l_BJT/2", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "1*d_E", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "d_BJT-1.25*d_E"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "d_BJT");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"w_cE", "l_E/2-2*d_E"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"w_BJT/2-w_EB-w_E/2", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("size", "l_cB/2-2*d_E", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"w_BJT/2-w_cB", "0"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("semi").prop("ShapeProperty").set("Formulation", "FEM1log");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("AroraMobilityModel", "AroraMobilityModel", "Arora mobility model");
    model.component("comp1").material("mat1").propertyGroup()
         .create("PowerLawMobilityModel", "PowerLawMobilityModel", "Power law mobility model");
    model.component("comp1").material("mat1").propertyGroup().create("Auger", "Auger", "Auger recombination");
    model.component("comp1").material("mat1").propertyGroup().create("Direct", "Direct", "Direct recombination");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SRH", "SRH", "Shockley\u2013Read\u2013Hall recombination");
    model.component("comp1").material("mat1").propertyGroup()
         .create("FletcherMobilityModel", "FletcherMobilityModel", "Fletcher mobility model");
    model.component("comp1").material("mat1").propertyGroup()
         .create("CaugheyThomasMobilityModel", "CaugheyThomasMobilityModel", "Caughey\u2013Thomas mobility model");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SemicondMaterial", "SemicondMaterial", "Semiconductor material");
    model.component("comp1").material("mat1").propertyGroup()
         .create("LombardiSurfaceMobilityModel", "LombardiSurfaceMobilityModel", "Lombardi surface mobility model");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ImpactIonization", "ImpactIonization", "Impact ionization");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SlotboomModel", "SlotboomModel", "Slotboom model");
    model.component("comp1").material("mat1").propertyGroup()
         .create("JainRoulstonModel", "JainRoulstonModel", "Jain\u2013Roulston model");
    model.component("comp1").material("mat1").propertyGroup()
         .create("KlaassenUnifiedMobilityModel", "KlaassenUnifiedMobilityModel", "Klaassen unified mobility model");
    model.component("comp1").material("mat1").label("Si - Silicon");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"131[W/(m*K)]", "0", "0", "0", "131[W/(m*K)]", "0", "0", "0", "131[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").label("Arora mobility model");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("mun0_ref_arora", "1252[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("mup0_ref_arora", "407[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("mun_min_ref_arora", "88[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("mup_min_ref_arora", "54.3[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("Nn0_ref_arora", "1.26e17[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel")
         .set("Np0_ref_arora", "2.35e17[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("alpha0_arora", "0.88");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("beta1_arora", "-0.57");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("beta2_arora", "-2.33");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("beta3_arora", "-2.33");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("beta4_arora", "-0.146");
    model.component("comp1").material("mat1").propertyGroup("AroraMobilityModel").set("Tref_arora", "300[K]");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel")
         .label("Power law mobility model");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel")
         .set("mun0_pl", "1448[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel")
         .set("mup0_pl", "473[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel").set("alphan_pl", "2.33");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel").set("alphap_pl", "2.23");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel").set("Tref_pl", "300[K]");
    model.component("comp1").material("mat1").propertyGroup("Auger").label("Auger recombination");
    model.component("comp1").material("mat1").propertyGroup("Auger").set("Cn", "2.8e-31[cm^6/s]");
    model.component("comp1").material("mat1").propertyGroup("Auger").set("Cp", "9.9e-32[cm^6/s]");
    model.component("comp1").material("mat1").propertyGroup("Direct").label("Direct recombination");
    model.component("comp1").material("mat1").propertyGroup("Direct").set("C", "0[cm^3/s]");
    model.component("comp1").material("mat1").propertyGroup("SRH")
         .label("Shockley\u2013Read\u2013Hall recombination");
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taun", "10[us]");
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taup", "10[us]");
    model.component("comp1").material("mat1").propertyGroup("FletcherMobilityModel")
         .label("Fletcher mobility model");
    model.component("comp1").material("mat1").propertyGroup("FletcherMobilityModel")
         .set("F1_fl", "1.04e21[1/(cm^1*V*s)]");
    model.component("comp1").material("mat1").propertyGroup("FletcherMobilityModel").set("F2_fl", "7.45e13[1/cm^2]");
    model.component("comp1").material("mat1").propertyGroup("FletcherMobilityModel").set("Tref_fl", "300[K]");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel")
         .label("Caughey\u2013Thomas mobility model");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("alphan0_ct", "1.11");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("alphap0_ct", "1.21");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("vn0_ct", "1e7[cm/s]");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel")
         .set("vp0_ct", "8.37e6[cm/s]");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("betan1_ct", "0.66");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("betap1_ct", "0.17");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("betan2_ct", "-0.87");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("betap2_ct", "-0.52");
    model.component("comp1").material("mat1").propertyGroup("CaugheyThomasMobilityModel").set("Tref_ct", "300[K]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").label("Semiconductor material");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Eg0", "1.12[V]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("chi0", "4.05[V]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nv", "(T/300[K])^(3/2)*1.04e19[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nc", "(T/300[K])^(3/2)*2.8e19[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mun", "1450[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mup", "500[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .label("Lombardi surface mobility model");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("deltan_ls", "5.82e14[V/s]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("deltap_ls", "2.05e14[V/s]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("mun1_ls", "4.75e7[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("mup1_ls", "9.93e7[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("mun2_ls", "1.74e5[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("mup2_ls", "8.84e5[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("alphan_ls", "0.125");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("alphap_ls", "0.0317");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel").set("Tref_ls", "1[K]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("Eref_ls", "1[V/cm]");
    model.component("comp1").material("mat1").propertyGroup("LombardiSurfaceMobilityModel")
         .set("Nref_ls", "1[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").label("Impact ionization");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("an", "0.426[1/V]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("ap", "0.243[1/V]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("bn", "4.81E5[V/cm]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("bp", "6.53E5[V/cm]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("cnii", "3.05E-4[1/K]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("cpii", "5.35E-4[1/K]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("dn", "6.86E-4[1/K]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("dp", "5.67E-4[1/K]");
    model.component("comp1").material("mat1").propertyGroup("SlotboomModel").label("Slotboom model");
    model.component("comp1").material("mat1").propertyGroup("SlotboomModel").set("Eref_sb", "0.00692[V]");
    model.component("comp1").material("mat1").propertyGroup("SlotboomModel").set("Nref_sb", "1.3e17[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("SlotboomModel").set("alpha_sb", "0.5");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").label("Jain\u2013Roulston model");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("An_jr", "3.5e-8[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Bn_jr", "0[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Cn_jr", "0[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Ap_jr", "3.5e-8[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Bp_jr", "0[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Cp_jr", "0[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Nref_jr", "1[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("alpha_jr", "0.5");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .label("Klaassen unified mobility model");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("T_ref_kl", "300[K]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("mu_e_max_kl", "1414.0[cm^2/V/s]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("mu_h_max_kl", "470.5[cm^2/V/s]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("mu_e_min_kl", "68.5[cm^2/V/s]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("mu_h_min_kl", "44.9[cm^2/V/s]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("theta_e_kl", "2.285");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("theta_h_kl", "2.247");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("alpha_e_1_kl", "0.711");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("alpha_h_1_kl", "0.719");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("N_ref_e_1_kl", "9.20e16[cm^-3]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("N_ref_h_1_kl", "2.23e17[cm^-3]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("c_D_kl", "0.21");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("c_A_kl", "0.50");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("N_ref_D_kl", "4.0e20[cm^-3]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("N_ref_A_kl", "7.2e20[cm^-3]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("f_BH_kl", "3.828");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("f_CW_kl", "2.459");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("N_BH_kl", "1.36e20[cm^-3]");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("P_CW_kl", "3.97e13");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("s_1_kl", "0.89233");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("s_2_kl", "0.41372");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("s_3_kl", "0.19778");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("s_4_kl", "0.28227");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("s_5_kl", "0.005978");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("s_6_kl", "1.80618");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("s_7_kl", "0.72169");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("r_1_kl", "0.7643");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("r_2_kl", "2.2999");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("r_3_kl", "6.5502");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("r_4_kl", "2.3670");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("r_5_kl", "-0.01552");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel").set("r_6_kl", "0.6478");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("m_e_kl", "me_const");
    model.component("comp1").material("mat1").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("m_h_kl", "1.258*me_const");

    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 3);
    model.component("comp1").physics("semi").feature("adm1").selection().all();
    model.component("comp1").physics("semi").feature("adm1").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm1").set("NDc", "N_epi");
    model.component("comp1").physics("semi").feature("adm1")
         .label("\u6052\u5b9a\u7684\u672c\u5e95 n \u578b\u63ba\u6742");
    model.component("comp1").physics("semi").create("adm2", "AnalyticDopingModel", 3);
    model.component("comp1").physics("semi").feature("adm2").selection().all();
    model.component("comp1").physics("semi").feature("adm2").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm2").set("NAc", "N_B+N_epi");
    model.component("comp1").physics("semi").feature("adm2").set("rb", new String[]{"0[um]", "0[um]", "d_BJT-d_E"});
    model.component("comp1").physics("semi").feature("adm2").set("jwidth", "w_BJT/2");
    model.component("comp1").physics("semi").feature("adm2").set("jdepth", "l_cB/2");
    model.component("comp1").physics("semi").feature("adm2").set("jheight", "d_E");
    model.component("comp1").physics("semi").feature("adm2").set("jds", "d_E");
    model.component("comp1").physics("semi").feature("adm2").set("Nb_src", "root.comp1.semi.adm1.NDc");
    model.component("comp1").physics("semi").feature("adm2").label("\u57fa\u6781 p \u578b\u63ba\u6742");
    model.component("comp1").physics("semi").create("adm3", "AnalyticDopingModel", 3);
    model.component("comp1").physics("semi").feature("adm3").selection().all();
    model.component("comp1").physics("semi").feature("adm3").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm3").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm3").set("NDc", "N_E+N_B");
    model.component("comp1").physics("semi").feature("adm3").set("rb", new String[]{"0[um]", "0[um]", "d_BJT"});
    model.component("comp1").physics("semi").feature("adm3").set("jwidth", "w_E/2-d_E");
    model.component("comp1").physics("semi").feature("adm3").set("jdepth", "l_E/2-2*d_E");
    model.component("comp1").physics("semi").feature("adm3").set("jds", "d_E");
    model.component("comp1").physics("semi").feature("adm3").set("Nb", "N_B");
    model.component("comp1").physics("semi").feature("adm3").label("\u53d1\u5c04\u6781 n \u578b\u63ba\u6742");
    model.component("comp1").physics("semi").create("adm4", "AnalyticDopingModel", 3);
    model.component("comp1").physics("semi").feature("adm4").selection().all();
    model.component("comp1").physics("semi").feature("adm4").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm4").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm4").set("NDc", "N_C");
    model.component("comp1").physics("semi").feature("adm4").set("jwidth", "w_BJT/2");
    model.component("comp1").physics("semi").feature("adm4").set("jdepth", "l_BJT/2");
    model.component("comp1").physics("semi").feature("adm4").set("jds", "1.3*d_C");
    model.component("comp1").physics("semi").feature("adm4").set("Nb_src", "root.comp1.semi.adm1.NDc");
    model.component("comp1").physics("semi").feature("adm4").label("\u96c6\u7535\u6781 n \u578b\u63ba\u6742");
    model.component("comp1").physics("semi").create("tar1", "TrapAssistedRecombination", 3);
    model.component("comp1").physics("semi").feature("tar1").selection().all();
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 2);
    model.component("comp1").physics("semi").feature("mc1").selection().set(10);
    model.component("comp1").physics("semi").feature("mc1").set("V0", "V_E");
    model.component("comp1").physics("semi").feature("mc1").label("\u53d1\u5c04\u6781\u7535\u538b");
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 2);
    model.component("comp1").physics("semi").feature("mc2").selection().set(15);
    model.component("comp1").physics("semi").feature("mc2").set("V0", "V_B");
    model.component("comp1").physics("semi").feature("mc2").label("\u57fa\u6781\u7535\u538b");
    model.component("comp1").physics("semi").create("mc3", "MetalContact", 2);
    model.component("comp1").physics("semi").feature("mc3").selection().set(3);
    model.component("comp1").physics("semi").feature("mc3").set("V0", "V_C");
    model.component("comp1").physics("semi").feature("mc3").label("\u96c6\u7535\u6781\u7535\u538b");
    model.component("comp1").physics("semi").feature().duplicate("mc4", "mc2");
    model.component("comp1").physics("semi").feature("mc4").set("TerminalType", "Current");
    model.component("comp1").physics("semi").feature("mc4").set("I0", "I_B");
    model.component("comp1").physics("semi").feature("mc4").label("\u57fa\u6781\u7535\u6d41");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(3, 10, 11, 15);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "1e-4");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("V_C \u626b\u63cf\uff0cV_B=0 V\uff0cV_E=0 V");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"semi/mc4"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").set("sweeptype", "filled");
    model.study("std1").feature("stat").setIndex("pname", "T0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "T0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "V_B", 0);
    model.study("std1").feature("stat").setIndex("plistarr", 0, 0);
    model.study("std1").feature("stat").setIndex("pname", "T0", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "K", 1);
    model.study("std1").feature("stat").setIndex("pname", "T0", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "K", 1);
    model.study("std1").feature("stat").setIndex("pname", "V_E", 1);
    model.study("std1").feature("stat").setIndex("plistarr", 0, 1);
    model.study("std1").feature("stat").setIndex("pname", "T0", 2);
    model.study("std1").feature("stat").setIndex("plistarr", "", 2);
    model.study("std1").feature("stat").setIndex("punit", "K", 2);
    model.study("std1").feature("stat").setIndex("pname", "T0", 2);
    model.study("std1").feature("stat").setIndex("plistarr", "", 2);
    model.study("std1").feature("stat").setIndex("punit", "K", 2);
    model.study("std1").feature("stat").setIndex("pname", "V_C", 2);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.1,0.5)", 2);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std2").label("V_B \u626b\u63cf\uff0cV_C=0.5 V\uff0cV_E=0 V");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"semi/mc4"});
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("solnum", "last");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "T0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "K", 0);
    model.study("std2").feature("stat").setIndex("pname", "T0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "K", 0);
    model.study("std2").feature("stat").setIndex("pname", "V_B", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.025,1.1)", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").set("expr", new String[]{"semi.I0_1"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u7ec8\u7aef\u7535\u6d41"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"A"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"semi.I0_1", "semi.I0_2"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u7ec8\u7aef\u7535\u6d41", "\u7ec8\u7aef\u7535\u6d41"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"semi.I0_1", "semi.I0_2", "semi.I0_3"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u7ec8\u7aef\u7535\u6d41", "\u7ec8\u7aef\u7535\u6d41", "\u7ec8\u7aef\u7535\u6d41"});

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").feature("glob1").setIndex("unit", "mA", 0);
    model.result("pg1").feature("glob1").setIndex("descr", "I_E", 0);
    model.result("pg1").feature("glob1").setIndex("unit", "mA", 1);
    model.result("pg1").feature("glob1").setIndex("descr", "I_B", 1);
    model.result("pg1").feature("glob1").setIndex("unit", "mA", 2);
    model.result("pg1").feature("glob1").setIndex("descr", "I_C", 2);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("glob2", "Global");
    model.result("pg1").feature("glob2").set("markerpos", "datapoints");
    model.result("pg1").feature("glob2").set("linewidth", "preference");
    model.result("pg1").feature("glob2").setIndex("expr", "-(semi.I0_1+semi.I0_3)", 0);
    model.result("pg1").feature("glob2").setIndex("unit", "mA", 0);
    model.result("pg1").feature("glob2").setIndex("descr", "I_B calc", 0);
    model.result("pg1").feature("glob2").set("linestyle", "none");
    model.result("pg1").feature("glob2").set("linecolor", "magenta");
    model.result("pg1").feature("glob2").set("linemarker", "circle");
    model.result("pg1").feature("glob2").set("markerpos", "interp");
    model.result("pg1").feature("glob2").set("markers", 30);
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("xlabel", "V_BE (V)");
    model.result("pg1").set("ylabel", "I (mA)");
    model.result("pg1").set("legendpos", "lowerleft");
    model.result("pg1").run();
    model.result("pg1").label("I_E\u3001I_B \u548c I_C \u968f V_BE \u53d8\u5316");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "abs(semi.I0_3)", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "\u96c6\u7535\u6781\u7535\u6d41\uff0cI_C", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "abs(semi.I0_2)", 1);
    model.result("pg2").feature("glob1").setIndex("descr", "\u57fa\u6781\u7535\u6d41\uff0cI_B", 1);
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "Gummel \u56fe");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u57fa\u6781\u7535\u538b (V)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u7535\u6d41 (A)");
    model.result("pg2").set("ylog", true);
    model.result("pg2").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg2").setIndex("looplevelindices", "range(17,1,45)", 0);
    model.result("pg2").run();
    model.result("pg2").label("Gummel \u56fe\uff0cI_C \u548c I_B \u968f V_BE \u53d8\u5316");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("xlog", true);
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "abs(semi.I0_3/semi.I0_2)", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u589e\u76ca (I_C/I_B)", 0);
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "semi.I0_3");
    model.result("pg3").run();
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u96c6\u7535\u6781\u7535\u6d41 (A)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u7535\u6d41\u589e\u76ca");
    model.result("pg3").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg3").setIndex("looplevelindices", "range(17,1,45)", 0);
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u6d41\u589e\u76ca");

    model.component("comp1").physics("semi").feature("mc4").set("V0_init", "0.8[V]");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std3").label("V_C \u626b\u63cf\uff0cV_E=0 V\uff0cI_B=2[uA]");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("stat").set("useinitsol", true);
    model.study("std3").feature("stat").set("initmethod", "sol");
    model.study("std3").feature("stat").set("initstudy", "std2");
    model.study("std3").feature("stat").set("solnum", 35);
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").set("sweeptype", "filled");
    model.study("std3").feature("stat").setIndex("pname", "T0", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "K", 0);
    model.study("std3").feature("stat").setIndex("pname", "T0", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "K", 0);
    model.study("std3").feature("stat").setIndex("pname", "I_B", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "2[uA]", 0);
    model.study("std3").feature("stat").setIndex("pname", "T0", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "", 1);
    model.study("std3").feature("stat").setIndex("punit", "K", 1);
    model.study("std3").feature("stat").setIndex("pname", "T0", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "", 1);
    model.study("std3").feature("stat").setIndex("punit", "K", 1);
    model.study("std3").feature("stat").setIndex("pname", "V_C", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "range(0,0.05,0.4) range(0.5,0.1,1.5)", 1);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.study().create("std4");
    model.study("std4").setGenPlots(false);
    model.study("std4").label("V_C \u626b\u63cf\uff0cV_E=0 V\uff0cI_B=10[uA]");
    model.study("std4").feature().copy("stat", "std3/stat");
    model.study("std4").feature("stat").setIndex("plistarr", "10[uA]", 0);
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5171\u53d1\u5c04\u6781\u8f93\u51fa\u7279\u6027");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("data", "dset3");
    model.result("pg4").feature("glob1").setIndex("expr", "abs(semi.I0_3)", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "uA", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u96c6\u7535\u6781\u7535\u6d41 I_B=2uA", 0);
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "I_B=2 uA", 0);
    model.result("pg4").run();
    model.result("pg4").create("glob2", "Global");
    model.result("pg4").feature("glob2").set("markerpos", "datapoints");
    model.result("pg4").feature("glob2").set("linewidth", "preference");
    model.result("pg4").feature("glob2").set("data", "dset4");
    model.result("pg4").feature("glob2").setIndex("expr", "abs(semi.I0_3)", 0);
    model.result("pg4").feature("glob2").setIndex("unit", "uA", 0);
    model.result("pg4").feature("glob2").setIndex("descr", "\u96c6\u7535\u6781\u7535\u6d41 I_B=10 uA", 0);
    model.result("pg4").feature("glob2").set("legendmethod", "manual");
    model.result("pg4").feature("glob2").setIndex("legends", "I_B=10 uA", 0);
    model.result("pg4").run();
    model.result("pg4").set("titletype", "manual");
    model.result("pg4")
         .set("title", "\u96c6\u7535\u6781\u7535\u6d41\u4e0e\u96c6\u7535\u6781\u7535\u538b\u7684\u51fd\u6570\u5173\u7cfb");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u96c6\u7535\u6781\u7535\u538b (V)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u96c6\u7535\u6781\u7535\u6d41 (uA)");
    model.result("pg4").set("legendpos", "middleright");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").create("arwv1", "ArrowVolume");
    model.result("pg5").feature("arwv1").set("expr", new String[]{"semi.JnX", "semi.JnY", ""});
    model.result("pg5").feature("arwv1").setIndex("expr", "semi.JnZ", 2);
    model.result("pg5").feature("arwv1").set("xnumber", 10);
    model.result("pg5").feature("arwv1").set("ynumber", 10);
    model.result("pg5").feature("arwv1").set("znumber", 10);
    model.result("pg5").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("arwv1").set("color", "black");
    model.result("pg5").feature().duplicate("arwv2", "arwv1");
    model.result("pg5").run();
    model.result("pg5").feature("arwv2").setIndex("expr", "semi.JpX", 0);
    model.result("pg5").feature("arwv2").setIndex("expr", "semi.JpY", 1);
    model.result("pg5").feature("arwv2").setIndex("expr", "semi.JpZ", 2);
    model.result("pg5").feature("arwv2").set("color", "white");
    model.result("pg5").run();
    model.result("pg5").create("slc1", "Slice");
    model.result("pg5").feature("slc1").set("quickplane", "zx");
    model.result("pg5").feature("slc1").set("quickynumber", 1);
    model.result("pg5").feature("slc1").set("expr", "V");
    model.result("pg5").run();

    model.title("\u53cc\u6781\u6676\u4f53\u7ba1\u7684\u4e09\u7ef4\u5206\u6790");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u8fdb\u884c\u53cc\u6781\u6676\u4f53\u7ba1\u7684\u4e09\u7ef4\u5206\u6790\u3002\u7ed3\u679c\u8868\u660e\uff0c\u4e09\u7ef4\u534a\u5bfc\u4f53\u6a21\u578b\u53ef\u4ee5\u901a\u8fc7\u6709\u9650\u5143\u5bf9\u6570\u516c\u5f0f\u9ad8\u6548\u6c42\u89e3\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("bipolar_transistor_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
