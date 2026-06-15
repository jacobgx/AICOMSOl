/*
 * bipolar_transistor_thermal.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:54 by COMSOL 6.3.0.290. */
public class bipolar_transistor_thermal {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Transistors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/semi", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T0", "293.15[K]", "Reference temperature");
    model.param().set("w_BJT", "2.5[um]", "BJT width");
    model.param().set("d_BJT", "1[um]", "BJT thickness");
    model.param().set("l_BJT", "3[um]", "BJT length");
    model.param().set("w_E", "1.2[um]", "Emitter width");
    model.param().set("w_EB", "0.35[um]", "Emitter-base spacing");
    model.param().set("w_cE", "w_E/2-d_E", "Emitter contact width");
    model.param().set("w_cB", "w_BJT/2-w_EB-w_E/2", "Base contact width");
    model.param().set("w_cC", "w_BJT/2", "Collector contact width");
    model.param().set("d_E", "0.15[um]", "Emitter junction depth");
    model.param().set("d_B", "0.3[um]", "Base junction depth");
    model.param().set("d_C", "0.3[um]", "Collector junction depth");
    model.param().set("N_epi", "2e16[1/cm^3]", "Epitaxial layer doping");
    model.param().set("N_B", "9e17[1/cm^3]", "Base doping");
    model.param().set("N_E", "2e20[1/cm^3]", "Emitter doping");
    model.param().set("N_C", "8e19[1/cm^3]", "Collector doping");
    model.param().set("V_C", "0.5[V]", "Applied voltage: collector");
    model.param().set("V_B", "0.5[V]", "Applied voltage: base");
    model.param().set("V_E", "0[V]", "Applied voltage: emitter");
    model.param().set("I_B", "1[uA]", "Inward applied current: base");
    model.param().set("I_C", "0[uA]", "Inward applied current: collector");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"w_BJT/2", "d_BJT"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-d_BJT"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "w_cE", 0);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "w_BJT/2-w_cB", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

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
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"131[W/(m*K)]", "0", "0", "0", "131[W/(m*K)]", "0", "0", "0", "131[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
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
         .set("mun0_pl", "1448[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel")
         .set("mup0_pl", "473[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel").set("alphan_pl", "2.33");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel").set("alphap_pl", "2.23");
    model.component("comp1").material("mat1").propertyGroup("PowerLawMobilityModel").set("Tref_pl", "300[K]");
    model.component("comp1").material("mat1").propertyGroup("Auger").set("Cn", "2.8e-31[cm^6/s]");
    model.component("comp1").material("mat1").propertyGroup("Auger").set("Cp", "9.9e-32[cm^6/s]");
    model.component("comp1").material("mat1").propertyGroup("Direct").set("C", "0[cm^3/s]");
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taun", "10[us]");
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taup", "10[us]");
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
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("an", "0.426[1/V]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("ap", "0.243[1/V]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("bn", "4.81E5[V/cm]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("bp", "6.53E5[V/cm]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("cnii", "3.05E-4[1/K]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("cpii", "5.35E-4[1/K]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("dn", "6.86E-4[1/K]");
    model.component("comp1").material("mat1").propertyGroup("ImpactIonization").set("dp", "5.67E-4[1/K]");
    model.component("comp1").material("mat1").propertyGroup("SlotboomModel").set("Eref_sb", "0.00692[V]");
    model.component("comp1").material("mat1").propertyGroup("SlotboomModel").set("Nref_sb", "1.3e17[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("SlotboomModel").set("alpha_sb", "0.5");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("An_jr", "3.5e-8[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Bn_jr", "0[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Cn_jr", "0[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Ap_jr", "3.5e-8[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Bp_jr", "0[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Cp_jr", "0[V]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("Nref_jr", "1[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("JainRoulstonModel").set("alpha_jr", "0.5");
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

    model.component("comp1").physics("semi").prop("d").set("d", "l_BJT");
    model.component("comp1").physics("semi").prop("ShapeProperty").set("Formulation", "FEM1log");
    model.component("comp1").physics("semi").feature("smm1").set("minput_temperature", "T0");
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm1").selection().all();
    model.component("comp1").physics("semi").feature("adm1").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm1").set("NDc", "N_epi");
    model.component("comp1").physics("semi").create("adm2", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm2").selection().all();
    model.component("comp1").physics("semi").feature("adm2").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm2").set("rb", new String[]{"0[um]", "-d_E", "0"});
    model.component("comp1").physics("semi").feature("adm2").set("jwidth", "w_BJT/2");
    model.component("comp1").physics("semi").feature("adm2").set("jdepth", "d_E");
    model.component("comp1").physics("semi").feature("adm2").set("NAc", "N_B+N_epi");
    model.component("comp1").physics("semi").feature("adm2").set("jds", "d_E");
    model.component("comp1").physics("semi").feature("adm2").set("Nb_src", "root.comp1.semi.adm1.NDc");
    model.component("comp1").physics("semi").create("adm3", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm3").selection().all();
    model.component("comp1").physics("semi").feature("adm3").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm3").set("jwidth", "w_E/2-d_E");
    model.component("comp1").physics("semi").feature("adm3").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm3").set("NDc", "N_E+N_B");
    model.component("comp1").physics("semi").feature("adm3").set("jds", "d_E");
    model.component("comp1").physics("semi").feature("adm3").set("Nb", "N_B");
    model.component("comp1").physics("semi").create("adm4", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm4").selection().all();
    model.component("comp1").physics("semi").feature("adm4").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm4").set("rb", new String[]{"0[um]", "-d_BJT-d_C", "0"});
    model.component("comp1").physics("semi").feature("adm4").set("jwidth", "w_BJT/2");
    model.component("comp1").physics("semi").feature("adm4").set("jdepth", "d_C");
    model.component("comp1").physics("semi").feature("adm4").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm4").set("NDc", "N_C");
    model.component("comp1").physics("semi").feature("adm4").set("jds", "1.3*d_C");
    model.component("comp1").physics("semi").feature("adm4").set("Nb_src", "root.comp1.semi.adm1.NDc");
    model.component("comp1").physics("semi").create("tar1", "TrapAssistedRecombination", 2);
    model.component("comp1").physics("semi").feature("tar1").selection().all();
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc1").selection().set(3);
    model.component("comp1").physics("semi").feature("mc1").set("V0", "V_E");
    model.component("comp1").physics("semi").feature("mc1").label("Emitter Voltage");
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc2").selection().set(5);
    model.component("comp1").physics("semi").feature("mc2").set("V0", "V_B");
    model.component("comp1").physics("semi").feature("mc2").label("Base Voltage");
    model.component("comp1").physics("semi").create("mc3", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc3").selection().set(2);
    model.component("comp1").physics("semi").feature("mc3").set("V0", "V_C");
    model.component("comp1").physics("semi").feature("mc3").label("Collector Voltage");
    model.component("comp1").physics("semi").feature().duplicate("mc4", "mc2");
    model.component("comp1").physics("semi").feature("mc4").label("Base Current");
    model.component("comp1").physics("semi").feature("mc4").set("TerminalType", "Current");
    model.component("comp1").physics("semi").feature("mc4").set("I0", "I_B");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature().remove("size1");
    model.component("comp1").mesh("mesh1").feature().remove("ftri1");
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(3, 4, 5);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 40);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("cpe1", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").set(3, 4, 5);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").set(2);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 100);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 100);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("V_C Sweep, V_B=0 V, V_E=0 V");
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
    model.study("std2").label("V_B Sweep, V_C=0.5 V, V_E=0 V");
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
    model.result("pg1").feature("glob1").set("descr", new String[]{"Terminal current"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"A"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"semi.I0_1", "semi.I0_2"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"Terminal current", "Terminal current"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"semi.I0_1", "semi.I0_2", "semi.I0_3"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"Terminal current", "Terminal current", "Terminal current"});
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
    model.result("pg1").label("I_E, I_B and I_C as a function of V_BE");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "abs(semi.I0_3)", 0);
    model.result("pg2").feature("glob1").setIndex("unit", "A", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "Collector Current, I_C", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "abs(semi.I0_2)", 1);
    model.result("pg2").feature("glob1").setIndex("unit", "A", 1);
    model.result("pg2").feature("glob1").setIndex("descr", "Base Current, I_B", 1);
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "Gummel Plot");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "Base Voltage (V)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "Current (A)");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").set("ylog", true);
    model.result("pg2").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg2").setIndex("looplevelindices", "range(17,1,45)", 0);
    model.result("pg2").run();
    model.result("pg2").label("Gummel Plot, I_C and I_B as a function of V_BE");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("xlog", true);
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "abs(semi.I0_3/semi.I0_2)", 0);
    model.result("pg3").feature("glob1").setIndex("unit", 1, 0);
    model.result("pg3").feature("glob1").setIndex("descr", "Gain (I_C/I_B)", 0);
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "semi.I0_3");
    model.result("pg3").run();
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "Collector current (A)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "Current Gain");
    model.result("pg3").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg3").setIndex("looplevelindices", "range(17,1,45)", 0);
    model.result("pg3").run();
    model.result("pg3").label("Current Gain");

    model.component("comp1").physics("semi").feature("mc4").set("V0_init", "0.8[V]");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std3").label("V_C Sweep, V_E=0 V, for I_B=2[uA]");
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
    model.study("std4").label("V_C Sweep, V_E=0 V, for I_B=10[uA]");
    model.study("std4").feature().copy("stat", "std3/stat");
    model.study("std4").feature("stat").setIndex("plistarr", "10[uA]", 0);
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("Common-emitter output characteristics");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("data", "dset3");
    model.result("pg4").feature("glob1").setIndex("expr", "abs(semi.I0_3)", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "uA", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "Collector Current I_B=2uA", 0);
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "I_B=2 uA", 0);
    model.result("pg4").run();
    model.result("pg4").create("glob2", "Global");
    model.result("pg4").feature("glob2").set("markerpos", "datapoints");
    model.result("pg4").feature("glob2").set("linewidth", "preference");
    model.result("pg4").feature("glob2").set("data", "dset4");
    model.result("pg4").feature("glob2").setIndex("expr", "abs(semi.I0_3)", 0);
    model.result("pg4").feature("glob2").setIndex("unit", "uA", 0);
    model.result("pg4").feature("glob2").setIndex("descr", "Collector Current I_B=10 uA", 0);
    model.result("pg4").feature("glob2").set("legendmethod", "manual");
    model.result("pg4").feature("glob2").setIndex("legends", "I_B=10 uA", 0);
    model.result("pg4").run();
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "Collector Current as a function of Collector Voltage");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "Collector Voltage (V)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "Collector Current (uA)");
    model.result("pg4").set("legendpos", "middleright");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("Voltage & Current Density");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "V");
    model.result("pg5").feature("surf1").set("resolution", "norefine");
    model.result("pg5").feature("surf1").set("smooth", "everywhere");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("expr", new String[]{"semi.JnX", "semi.JnY"});
    model.result("pg5").feature("arws1").set("descr", "Electron current density");
    model.result("pg5").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("arws1").set("color", "black");
    model.result("pg5").feature().duplicate("arws2", "arws1");
    model.result("pg5").run();
    model.result("pg5").feature("arws2").set("expr", new String[]{"semi.JpX", "semi.JpY"});
    model.result("pg5").feature("arws2").set("color", "white");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "Voltage and Current Density");
    model.result("pg5").run();

    model.title("\u53cc\u6781\u6676\u4f53\u7ba1");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u8bbe\u7f6e\u7b80\u5355\u53cc\u6781\u6676\u4f53\u7ba1\u7684\u4eff\u771f\u3002\u8ba1\u7b97\u4e86\u5171\u53d1\u5c04\u6781\u914d\u7f6e\u4e2d\u7684\u8f93\u51fa\u7535\u6d41\u7535\u538b\u7279\u6027\uff0c\u5e76\u786e\u5b9a\u4e86\u5171\u53d1\u5c04\u6781\u7535\u6d41\u589e\u76ca\u3002");

    model.label("bipolar_transistor.mph");

    model.result("pg5").run();

    model.param().set("V_C", "3[V]");
    model.param().set("R", "100[K/W]");
    model.param().descr("R", "\u6709\u6548\u70ed\u963b");
    model.param().set("h0", "1/R/((w_cE+w_cB+w_cC)*l_BJT)");
    model.param().descr("h0", "\u89e6\u70b9\u7684\u6709\u6548\u4f20\u70ed\u7cfb\u6570");

    model.component("comp1").physics("semi").feature("smm1").set("Ionization", "incomplete");
    model.component("comp1").physics("semi").feature().duplicate("smm2", "smm1");
    model.component("comp1").physics("semi").feature("mc4").active(false);
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std4").feature("stat").setSolveFor("/physics/ht", true);

    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("dz", "l_BJT");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", 1);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 2);
    model.component("comp1").physics("ht").feature("hs1").selection().set(1);
    model.component("comp1").physics("ht").feature("hs1").set("Q0_src", "root.comp1.semi.Q_tot");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(2, 3, 5, 6);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "h0");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T0");
    model.component("comp1").physics("semi").feature("smm2").set("minput_temperature_src", "root.comp1.T");

    model.study().create("std5");
    model.study("std5").create("stat", "Stationary");
    model.study("std5").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std5").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std5").label("\u4e0d\u5b8c\u5168\u7535\u79bb\uff1aV_C \u626b\u63cf\uff0cV_B=0 V\uff0cV_E=0 V");
    model.study("std5").setGenPlots(false);
    model.study("std5").feature("stat").set("useadvanceddisable", true);
    model.study("std5").feature("stat").set("disabledphysics", new String[]{"semi/smm2"});
    model.study("std5").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std5").feature("stat").set("useinitsol", true);
    model.study("std5").feature("stat").set("initmethod", "sol");
    model.study("std5").feature("stat").set("initstudy", "std1");
    model.study("std5").feature("stat").set("useparam", true);
    model.study("std5").feature("stat").setIndex("pname", "T0", 0);
    model.study("std5").feature("stat").setIndex("plistarr", "", 0);
    model.study("std5").feature("stat").setIndex("punit", "K", 0);
    model.study("std5").feature("stat").setIndex("pname", "T0", 0);
    model.study("std5").feature("stat").setIndex("plistarr", "", 0);
    model.study("std5").feature("stat").setIndex("punit", "K", 0);
    model.study("std5").feature("stat").setIndex("pname", "V_B", 0);
    model.study("std5").feature("stat").setIndex("plistarr", 0, 0);
    model.study("std5").feature("stat").setIndex("pname", "T0", 1);
    model.study("std5").feature("stat").setIndex("plistarr", "", 1);
    model.study("std5").feature("stat").setIndex("punit", "K", 1);
    model.study("std5").feature("stat").setIndex("pname", "T0", 1);
    model.study("std5").feature("stat").setIndex("plistarr", "", 1);
    model.study("std5").feature("stat").setIndex("punit", "K", 1);
    model.study("std5").feature("stat").setIndex("pname", "V_E", 1);
    model.study("std5").feature("stat").setIndex("plistarr", 0, 1);
    model.study("std5").feature("stat").setIndex("pname", "T0", 2);
    model.study("std5").feature("stat").setIndex("plistarr", "", 2);
    model.study("std5").feature("stat").setIndex("punit", "K", 2);
    model.study("std5").feature("stat").setIndex("pname", "T0", 2);
    model.study("std5").feature("stat").setIndex("plistarr", "", 2);
    model.study("std5").feature("stat").setIndex("punit", "K", 2);
    model.study("std5").feature("stat").setIndex("pname", "V_C", 2);
    model.study("std5").feature("stat").setIndex("plistarr", "0.5 1 2 3", 2);
    model.study("std5").feature("stat").set("sweeptype", "filled");
    model.study("std5").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.study().create("std6");
    model.study("std6").create("stat", "Stationary");
    model.study("std6").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std6").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std6").label("\u4e0d\u5b8c\u5168\u7535\u79bb\uff1aV_B \u626b\u63cf\uff0cV_C=3 V\uff0cV_E=0 V");
    model.study("std6").setGenPlots(false);
    model.study("std6").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std6").feature("stat").set("useinitsol", true);
    model.study("std6").feature("stat").set("initmethod", "sol");
    model.study("std6").feature("stat").set("initstudy", "std5");
    model.study("std6").feature("stat").set("useparam", true);
    model.study("std6").feature("stat").setIndex("pname", "T0", 0);
    model.study("std6").feature("stat").setIndex("plistarr", "", 0);
    model.study("std6").feature("stat").setIndex("punit", "K", 0);
    model.study("std6").feature("stat").setIndex("pname", "T0", 0);
    model.study("std6").feature("stat").setIndex("plistarr", "", 0);
    model.study("std6").feature("stat").setIndex("punit", "K", 0);
    model.study("std6").feature("stat").setIndex("pname", "V_B", 0);
    model.study("std6").feature("stat").setIndex("plistarr", "range(0.4,0.1,0.7) range(0.8,0.05,1.1)", 0);
    model.study("std6").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.study().create("std7");
    model.study("std7").label("\u70ed\u5168\u8026\u5408\uff1aV_B \u626b\u63cf\uff0cV_C=3 V\uff0cV_E=0 V");
    model.study("std7").setGenPlots(false);
    model.study("std7").feature().copy("stat", "std6/stat");
    model.study("std7").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std7").createAutoSequences("all");

    model.sol("sol7").runAll();

    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("data", "dset6");
    model.result("pg2").feature().duplicate("glob2", "glob1");
    model.result("pg2").run();
    model.result("pg2").feature("glob2").set("data", "dset7");
    model.result("pg2").feature("glob2").set("xdata", "expr");
    model.result("pg2").feature("glob2").set("xdataexpr", "V_B");
    model.result("pg2").feature("glob2").set("linestyle", "dashed");
    model.result("pg2").run();
    model.result("pg2").set("data", "none");
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("data", "dset6");
    model.result("pg3").feature("glob1").setIndex("descr", "\u589e\u76ca (I_C/I_B) - \u5747\u5300\u6e29\u5ea6", 0);
    model.result("pg3").feature().duplicate("glob2", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").set("data", "dset7");
    model.result("pg3").feature("glob2").setIndex("descr", "\u589e\u76ca (I_C/I_B) - \u70ed\u8026\u5408", 0);
    model.result("pg3").feature("glob2").set("linestyle", "dashed");
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "lowerleft");
    model.result("pg3").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").set("data", "dset7");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u534a\u5bfc\u4f53\u70ed\u6e90");
    model.result("pg6").set("data", "mir1");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "semi.Q_tot");
    model.result("pg6").feature("surf1").set("colortable", "ThermalDark");
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("rangecolormax", "1e17");
    model.result("pg6").feature("surf1").set("rangecolormin", "-1e16");
    model.result("pg6").feature("surf1").set("resolution", "norefine");
    model.result("pg6").feature("surf1").set("smooth", "everywhere");
    model.result("pg6").feature("surf1").set("titletype", "manual");
    model.result("pg6").feature("surf1").set("title", "\u534a\u5bfc\u4f53\u53d1\u70ed");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").run();
    model.result("pg7").label("\u6e29\u5ea6\u548c\u7535\u538b");
    model.result("pg7").set("data", "mir1");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("\u6e29\u5ea6");
    model.result("pg7").feature("surf1").set("colortable", "ThermalDark");
    model.result("pg7").run();
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").label("\u70ed\u901a\u91cf");
    model.result("pg7").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("arws1").set("color", "green");
    model.result("pg7").run();
    model.result("pg7").create("surf2", "Surface");
    model.result("pg7").feature("surf2").label("\u7535\u538b");
    model.result("pg7").feature("surf2").set("expr", "V");
    model.result("pg7").feature("surf2").set("resolution", "norefine");
    model.result("pg7").feature("surf2").set("smooth", "everywhere");
    model.result("pg7").feature("surf2").create("def1", "Deform");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature("def1").set("expr", new String[]{"0", "1.1"});
    model.result("pg7").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg7").feature("surf2").feature("def1").set("scale", 1);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").create("arws2", "ArrowSurface");
    model.result("pg7").feature("arws2").label("\u7535\u5b50\u6d41");
    model.result("pg7").feature("arws2").set("expr", new String[]{"semi.JnX", "semi.JnY"});
    model.result("pg7").feature("arws2").set("arrowlength", "logarithmic");
    model.result("pg7").feature("arws2").set("color", "black");
    model.result("pg7").feature("arws2").create("def1", "Deform");
    model.result("pg7").run();
    model.result("pg7").feature("arws2").feature("def1").set("expr", new String[]{"0", "1.1"});
    model.result("pg7").feature("arws2").feature("def1").set("scaleactive", true);
    model.result("pg7").feature("arws2").feature("def1").set("scale", 1);
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("arws3", "arws2");
    model.result("pg7").run();
    model.result("pg7").feature("arws3").label("\u7a7a\u7a74\u6d41");
    model.result("pg7").feature("arws3").set("expr", new String[]{"semi.JpX", "semi.JpY"});
    model.result("pg7").feature("arws3").set("color", "white");
    model.result("pg7").run();
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u6e29\u5ea6\u548c\u7535\u538b\u5206\u5e03");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").set("legendpos", "bottom");
    model.result("pg7").run();

    model.title("\u53cc\u6781\u6676\u4f53\u7ba1\u7684\u70ed\u5206\u6790");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5c06\u534a\u5bfc\u4f53 \u63a5\u53e3\u8026\u5408\u5230\u56fa\u4f53\u4f20\u70ed \u63a5\u53e3\u3002\u5f53\u5668\u4ef6\u4ee5\u6b63\u5411\u6709\u6e90\u914d\u7f6e\u5de5\u4f5c\u65f6\uff0c\u5bf9\u73b0\u6709\u53cc\u6781\u6676\u4f53\u7ba1\u6a21\u578b\u8fdb\u884c\u70ed\u5206\u6790\u3002\n\n\u534a\u5bfc\u4f53 \u63a5\u53e3\u8ba1\u7b97\u5668\u4ef6\u5185\u7684\u8f7d\u6d41\u5b50\u52a8\u529b\u5b66\u548c\u7535\u6d41\uff0c\u5e76\u8f93\u51fa\u7535\u8fc7\u7a0b\u4ea7\u751f\u7684\u52a0\u70ed\u9879\u3002\u8be5\u52a0\u70ed\u9879\u5728\u56fa\u4f53\u4f20\u70ed \u63a5\u53e3\u4e2d\u7528\u4f5c\u70ed\u6e90\uff0c\u6765\u8ba1\u7b97\u6574\u4e2a\u5668\u4ef6\u5185\u7684\u6e29\u5ea6\u5206\u5e03\u3002\n\n\u56fa\u4f53\u4f20\u70ed \u63a5\u53e3\u4e2d\u7684\u6e29\u5ea6\u5206\u5e03\u7528\u4e8e\u6307\u5b9a\u534a\u5bfc\u4f53 \u63a5\u53e3\u4e2d\u7684\u6676\u683c\u6e29\u5ea6\uff0c\u4ece\u800c\u6539\u53d8\u7535\u5c5e\u6027\u5e76\u5f15\u8d77\u52a0\u70ed\u9879\u53d8\u5316\uff0c\u5f97\u5230\u4e00\u4e2a\u5168\u8026\u5408\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("bipolar_transistor_thermal.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
