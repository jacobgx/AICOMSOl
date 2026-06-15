/*
 * eeprom.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:54 by COMSOL 6.3.0.290. */
public class eeprom {

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

    model.component("comp1").geom("geom1").insertFile("eeprom_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new double[]{0.05, -0.01});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new double[]{0.85, -0.01});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 18, 22);
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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(3, 7);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"4.2"});

    model.param().set("Qgate", "0[C]");
    model.param().descr("Qgate", "\u6805\u6781\u7535\u8377");
    model.param().set("Vcontrol", "0.1[V]");
    model.param().descr("Vcontrol", "\u63a7\u5236\u7535\u538b");
    model.param().set("Vd", "10[mV]");
    model.param().descr("Vd", "\u6f0f\u6781\u7535\u538b");
    model.param().set("Vmax", "23.7[V]");
    model.param().descr("Vmax", "\u77ac\u6001\u7535\u538b\u6700\u5927\u503c");
    model.param().set("A_fn", "1.23e-6[A/V^2]");
    model.param().descr("A_fn", "A \u96a7\u7a7f\u7cfb\u6570");
    model.param().set("B_fn", "237[MV/cm]");
    model.param().descr("B_fn", "B \u96a7\u7a7f\u7cfb\u6570");

    model.func().create("rect1", "Rectangle");
    model.func("rect1").set("lower", "2e-6");
    model.func("rect1").set("upper", "5.6e-6");
    model.func("rect1").set("smooth", "1.6e-6");

    model.variable().create("var1");
    model.variable("var1").set("Vcontrol_t", "Vmax*rect1(t/1[s])");
    model.variable("var1").descr("Vcontrol_t", "\u77ac\u6001\u7535\u538b\u8109\u51b2");
    model.variable("var1").label("\u63a7\u5236\u7535\u538b\u8109\u51b2");

    model.component("comp1").physics("semi").selection().set(1, 2, 3, 6, 7, 8, 9);
    model.component("comp1").physics("semi").create("ccn1", "ChargeConservation", 2);
    model.component("comp1").physics("semi").feature("ccn1").selection().set(3, 7);
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm1").selection().all();
    model.component("comp1").physics("semi").create("adm2", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm2").selection().set(2, 8, 9);
    model.component("comp1").physics("semi").feature("adm2").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm2").set("NDc", "5e17[1/cm^3]");
    model.component("comp1").physics("semi").create("gdm1", "GeometricDopingModel", 2);
    model.component("comp1").physics("semi").feature("gdm1").selection().set(1, 6);
    model.component("comp1").physics("semi").feature("gdm1").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("gdm1").set("NDgen", "5e17[1/cm^3]");
    model.component("comp1").physics("semi").feature("gdm1").set("JunctionOrLength", "decay_length");
    model.component("comp1").physics("semi").feature("gdm1").set("l_gen", "0.02[um]");
    model.component("comp1").physics("semi").feature("gdm1").feature("gdmbs1").selection()
         .set(4, 16, 31, 32, 35, 43, 45);
    model.component("comp1").physics("semi").create("adm3", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm3").selection().set(6, 9);
    model.component("comp1").physics("semi").feature("adm3").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm3").set("NDc", "1e18[1/cm^3]");
    model.component("comp1").physics("semi").create("gdm2", "GeometricDopingModel", 2);
    model.component("comp1").physics("semi").feature("gdm2").selection().set(1, 2, 8);
    model.component("comp1").physics("semi").feature("gdm2").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("gdm2").set("NDgen", "1e18[1/cm^3]");
    model.component("comp1").physics("semi").feature("gdm2").set("JunctionOrLength", "decay_length");
    model.component("comp1").physics("semi").feature("gdm2").set("l_gen", "0.02[um]");
    model.component("comp1").physics("semi").feature("gdm2").feature("gdmbs1").selection().set(18, 24, 33, 44);
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc1").selection().set(39);
    model.component("comp1").physics("semi").feature("mc1").label("\u6e90\u6781");
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc2").selection().set(2);
    model.component("comp1").physics("semi").feature("mc2").label("\u5e95\u5ea7");
    model.component("comp1").physics("semi").create("mc3", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc3").selection().set(5);
    model.component("comp1").physics("semi").feature("mc3").set("V0", "Vd");
    model.component("comp1").physics("semi").feature("mc3").label("\u6f0f\u6781\uff08\u7a33\u6001\uff09");
    model.component("comp1").physics("semi").create("mc4", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc4").selection().set(5);
    model.component("comp1").physics("semi").feature("mc4").set("V0", "Vd");
    model.component("comp1").physics("semi").feature("mc4").set("TerminalName", 3);
    model.component("comp1").physics("semi").feature("mc4").label("\u6f0f\u6781\uff08\u77ac\u6001\uff09");
    model.component("comp1").physics("semi").create("term1", "Terminal", 1);
    model.component("comp1").physics("semi").feature("term1").selection().set(13, 14, 15, 30);
    model.component("comp1").physics("semi").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("semi").feature("term1").set("V0", "Vcontrol");
    model.component("comp1").physics("semi").feature("term1")
         .label("\u63a7\u5236\u6805\u6781\uff08\u7a33\u6001\uff09");
    model.component("comp1").physics("semi").create("term2", "Terminal", 1);
    model.component("comp1").physics("semi").feature("term2").selection().set(13, 14, 15, 30);
    model.component("comp1").physics("semi").feature("term2").set("TerminalName", 4);
    model.component("comp1").physics("semi").feature("term2").set("TerminalType", "Voltage");
    model.component("comp1").physics("semi").feature("term2").set("V0", "Vcontrol_t");
    model.component("comp1").physics("semi").feature("term2")
         .label("\u63a7\u5236\u6805\u6781\uff08\u77ac\u6001\uff09");
    model.component("comp1").physics("semi").create("ii2", "InsulatorInterface", 1);
    model.component("comp1").physics("semi").feature("ii2").selection().set(21);
    model.component("comp1").physics("semi").feature("ii2").set("TunnelingType", "FowlerNordheim");
    model.component("comp1").physics("semi").feature("ii2").set("AfnElectrons", "A_fn");
    model.component("comp1").physics("semi").feature("ii2").set("BfnElectrons", "B_fn");
    model.component("comp1").physics("semi").feature("ii2").label("\u96a7\u9053\u52bf\u5792");
    model.component("comp1").physics("semi").create("fg1", "FloatingGate", 1);
    model.component("comp1").physics("semi").feature("fg1").selection().set(10, 11, 12, 22, 23, 27, 28, 29);
    model.component("comp1").physics("semi").feature("fg1").set("Q_init", "Qgate");
    model.component("comp1").physics("semi").feature("fg1").label("\u6d6e\u6805");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(1, 3);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size2").selection()
         .set(4, 5, 18, 24, 31, 32, 33, 35, 39, 43, 44, 45, 48);
    model.component("comp1").mesh("mesh1").feature("size2").set("table", "semi");
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("size3").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size3").selection().set(10, 11, 12, 13, 14, 15, 28, 29, 30);
    model.component("comp1").mesh("mesh1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmax", 0.01);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(17, 19, 21, 26);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("table", "semi");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", 0.01);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").selection().set(18, 22);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("table", "semi");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hmax", 0.002);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hgrad", 1.05);
    model.component("comp1").mesh("mesh1").create("cpe1", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").set(17);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").set(51);
    model.component("comp1").mesh("mesh1").create("cpe2", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("source").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("destination").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("source").set(19, 21, 26);
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("destination").set(52);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(7, 10, 11);
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(16);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(20);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1, 2, 3, 6, 8, 9);
    model.component("comp1").mesh("mesh1").feature("ftri1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "semi.Ndoping");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").label("\u63ba\u6742");

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"semi/mc4", "semi/term2"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Qgate", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "C", 0);
    model.study("std1").feature("stat").setIndex("pname", "Qgate", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "C", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0 -2e-15", 0);
    model.study("std1").feature("stat").setIndex("pname", "Vcontrol", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "V", 1);
    model.study("std1").feature("stat").setIndex("pname", "Vcontrol", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "V", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.1,3)", 1);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std1").feature("stat").set("sweeptype", "filled");
    model.study("std1").feature("stat").set("preusesol", "auto");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").set("scalemethod", "none");
    model.sol("sol1").feature("v1").feature("comp1_Ne").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_Ne").set("scaleval", "1.0e24");
    model.sol("sol1").feature("v1").feature("comp1_Ph").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_Ph").set("scaleval", "1.0e22");

    model.study("std1").label("\u7a33\u6001\uff0c\u9488\u5bf9\u56fa\u5b9a Qgate \u626b\u63cf Vcontrol");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6d41 vs. \u7535\u538b");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "semi.I0_1", 0);
    model.result("pg2").feature("glob1").setIndex("unit", "uA", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "\u6e90\u6781\u7535\u6d41", 0);
    model.result("pg2").feature("glob1").set("legendmethod", "evaluated");
    model.result("pg2").feature("glob1").set("legendpattern", "Qgate= eval(Qgate, C) C");
    model.result("pg2").run();
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u63a7\u5236\u7535\u538b (V)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2")
         .set("title", "\u6e90\u6781\u7535\u6d41\u4e0e\u63a7\u5236\u7535\u538b\u7684\u51fd\u6570\u5173\u7cfb");
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/semi", true);
    model.study("std2").feature("time").set("tlist", "range(0,0.1e-6,7e-6)");
    model.study("std2").feature("time").set("useinitsol", true);
    model.study("std2").feature("time").set("initmethod", "sol");
    model.study("std2").feature("time").set("initstudy", "std1");
    model.study("std2").feature("time").set("solnum", 32);
    model.study("std2").feature("time").set("useparam", true);
    model.study("std2").feature("time").setIndex("pname", "Qgate", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "C", 0);
    model.study("std2").feature("time").setIndex("pname", "Qgate", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "C", 0);
    model.study("std2").feature("time").setIndex("pname", "Vmax", 0);
    model.study("std2").feature("time").setIndex("plistarr", "16.8[V]", 0);
    model.study("std2").feature("time").setIndex("pname", "Qgate", 1);
    model.study("std2").feature("time").setIndex("plistarr", "", 1);
    model.study("std2").feature("time").setIndex("punit", "C", 1);
    model.study("std2").feature("time").setIndex("pname", "Qgate", 1);
    model.study("std2").feature("time").setIndex("plistarr", "", 1);
    model.study("std2").feature("time").setIndex("punit", "C", 1);
    model.study("std2").feature("time").setIndex("pname", "A_fn", 1);
    model.study("std2").feature("time").setIndex("plistarr", "1.23e-6[A/V^2]", 1);
    model.study("std2").feature("time").setIndex("pname", "Qgate", 2);
    model.study("std2").feature("time").setIndex("plistarr", "", 2);
    model.study("std2").feature("time").setIndex("punit", "C", 2);
    model.study("std2").feature("time").setIndex("pname", "Qgate", 2);
    model.study("std2").feature("time").setIndex("plistarr", "", 2);
    model.study("std2").feature("time").setIndex("punit", "C", 2);
    model.study("std2").feature("time").setIndex("pname", "B_fn", 2);
    model.study("std2").feature("time").setIndex("plistarr", "237[MV/cm]", 2);
    model.study("std2").label("\u77ac\u6001\uff0c\u7f16\u7a0b\u4e8b\u4ef6");
    model.study("std2").setGenPlots(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/semi", true);
    model.study("std3").feature("time").set("tlist", "range(0,0.1e-6,7e-6)");
    model.study("std3").feature("time").set("useinitsol", true);
    model.study("std3").feature("time").set("initmethod", "sol");
    model.study("std3").feature("time").set("initstudy", "std2");
    model.study("std3").feature("time").set("solnum", "last");
    model.study("std3").feature("time").set("useparam", true);
    model.study("std3").feature("time").setIndex("pname", "Qgate", 0);
    model.study("std3").feature("time").setIndex("plistarr", "", 0);
    model.study("std3").feature("time").setIndex("punit", "C", 0);
    model.study("std3").feature("time").setIndex("pname", "Qgate", 0);
    model.study("std3").feature("time").setIndex("plistarr", "", 0);
    model.study("std3").feature("time").setIndex("punit", "C", 0);
    model.study("std3").feature("time").setIndex("pname", "Vmax", 0);
    model.study("std3").feature("time").setIndex("plistarr", -15.34, 0);
    model.study("std3").feature("time").setIndex("pname", "Qgate", 1);
    model.study("std3").feature("time").setIndex("plistarr", "", 1);
    model.study("std3").feature("time").setIndex("punit", "C", 1);
    model.study("std3").feature("time").setIndex("pname", "Qgate", 1);
    model.study("std3").feature("time").setIndex("plistarr", "", 1);
    model.study("std3").feature("time").setIndex("punit", "C", 1);
    model.study("std3").feature("time").setIndex("pname", "A_fn", 1);
    model.study("std3").feature("time").setIndex("plistarr", "1.82e-7[A/V^2]", 1);
    model.study("std3").feature("time").setIndex("pname", "Qgate", 2);
    model.study("std3").feature("time").setIndex("plistarr", "", 2);
    model.study("std3").feature("time").setIndex("punit", "C", 2);
    model.study("std3").feature("time").setIndex("pname", "Qgate", 2);
    model.study("std3").feature("time").setIndex("plistarr", "", 2);
    model.study("std3").feature("time").setIndex("punit", "C", 2);
    model.study("std3").feature("time").setIndex("pname", "B_fn", 2);
    model.study("std3").feature("time").setIndex("plistarr", "188[MV/cm]", 2);
    model.study("std3").label("\u77ac\u6001\uff0c\u64e6\u9664\u4e8b\u4ef6");
    model.study("std3").setGenPlots(false);
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u96a7\u7a7f\u7535\u6d41");
    model.result("pg3").set("data", "none");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u96a7\u7a7f\u7535\u6d41\u968f\u65f6\u95f4\u7684\u53d8\u5316");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u65f6\u95f4 (us)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u96a7\u7a7f\u7535\u6d41 (nA)");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("data", "dset2");
    model.result("pg3").feature("glob1").setIndex("expr", "semi.ii2.I_tun", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "nA", 0);
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg3").feature("glob1").set("xdataparamunit", "\u00b5s");
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "\u7f16\u7a0b\u4e8b\u4ef6", 0);
    model.result("pg3").run();
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").set("markerpos", "datapoints");
    model.result("pg3").feature("glob2").set("linewidth", "preference");
    model.result("pg3").feature("glob2").set("data", "dset3");
    model.result("pg3").feature("glob2").setIndex("expr", "semi.ii2.I_tun", 0);
    model.result("pg3").feature("glob2").setIndex("unit", "nA", 0);
    model.result("pg3").feature("glob2").set("xdatasolnumtype", "level1");
    model.result("pg3").feature("glob2").set("xdataparamunit", "\u00b5s");
    model.result("pg3").feature("glob2").set("legendmethod", "manual");
    model.result("pg3").feature("glob2").setIndex("legends", "\u64e6\u9664\u4e8b\u4ef6", 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "none");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u6d6e\u6805\u7535\u8377\u968f\u65f6\u95f4\u7684\u53d8\u5316");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u65f6\u95f4 (us)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u6d6e\u6805\u4e0a\u7684\u7535\u8377 (pC)");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("data", "dset2");
    model.result("pg4").feature("glob1").setIndex("expr", "semi.fg1.Q0", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "pC", 0);
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg4").feature("glob1").set("xdataparamunit", "\u00b5s");
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "\u7f16\u7a0b\u4e8b\u4ef6", 0);
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").set("data", "dset3");
    model.result("pg4").feature("glob2").setIndex("legends", "\u64e6\u9664\u4e8b\u4ef6", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").label("\u6d6e\u6805\u7535\u8377");

    model.title("\u6d6e\u6805 EEPROM \u5668\u4ef6\u7684\u7f16\u7a0b\u8fc7\u7a0b");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u6d6e\u6805\u201c\u7535\u53ef\u64e6\u9664\u53ef\u7f16\u7a0b\u53ea\u8bfb\u5b58\u50a8\u5668\u201d(EEPROM) \u5668\u4ef6\u4e2d\u7684\u7535\u6d41\u4e0e\u7535\u8377\u7279\u6027\u3002\u901a\u8fc7\u7a33\u6001\u7814\u7a76\u6765\u8ba1\u7b97\u7535\u6d41-\u7535\u538b\u66f2\u7ebf\u968f\u4e24\u79cd\u4e0d\u540c\u6570\u91cf\u5b58\u50a8\u7535\u8377\u7684\u63a7\u5236\u6805\u6781\u7535\u538b\u7684\u53d8\u5316\u60c5\u51b5\uff0c\u4ece\u800c\u6f14\u793a\u6d6e\u6805\u4e0a\u5b58\u50a8\u7684\u7535\u8377\u53d1\u751f\u53d8\u5316\u6240\u4ea7\u751f\u7684\u5f71\u54cd\u3002\u968f\u540e\u8fdb\u884c\u7684\u77ac\u6001\u7814\u7a76\u7528\u6765\u6a21\u62df\u63a7\u5236\u6805\u4e0a\u7684\u77ac\u6001\u7535\u538b\u8109\u51b2\u3002\u8fd9\u4e9b\u8109\u51b2\u4f7f\u7535\u6d41\u5728\u6d6e\u6805\u548c\u534a\u5bfc\u4f53\u6750\u6599\u4e4b\u95f4\u4ea7\u751f\u96a7\u7a7f\uff0c\u4ece\u800c\u53ef\u4ee5\u5bf9 EEPROM \u5668\u4ef6\u8fdb\u884c\u7f16\u7a0b\u548c\u64e6\u9664\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("eeprom.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
