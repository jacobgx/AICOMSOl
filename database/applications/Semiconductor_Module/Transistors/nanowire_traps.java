/*
 * nanowire_traps.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:57 by COMSOL 6.3.0.290. */
public class nanowire_traps {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Transistors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/semi", true);

    model.component("comp1").geom("geom1").lengthUnit("nm");

    model.param().set("Rw", "10[nm]");
    model.param().descr("Rw", "\u7eb3\u7c73\u7ebf\u534a\u5f84");
    model.param().set("L", "100[nm]");
    model.param().descr("L", "\u6805\u6781\u957f\u5ea6");
    model.param().set("t_ox", "2[nm]");
    model.param().descr("t_ox", "\u6c27\u5316\u5c42\u539a\u5ea6");
    model.param().set("t_np", "Rw");
    model.param().descr("t_np", "\u989d\u5916\u957f\u5ea6");
    model.param().set("Na_0", "1e17[1/cm^3]");
    model.param().descr("Na_0", "\u672c\u5e95\u53d7\u4e3b\u6d53\u5ea6");
    model.param().set("Nd_0", "1e20[1/cm^3]");
    model.param().descr("Nd_0", "\u6700\u5927\u65bd\u4e3b\u6d53\u5ea6");
    model.param().set("Nt_0", "1e10[1/cm^2]");
    model.param().descr("Nt_0", "\u9677\u9631\u5bc6\u5ea6");
    model.param().set("Vd", "500[mV]");
    model.param().descr("Vd", "\u6f0f\u6781\u7535\u538b");
    model.param().set("Vg", "-500[mV]");
    model.param().descr("Vg", "\u6805\u6781\u7535\u538b");
    model.param().set("ramp", "1");
    model.param().descr("ramp", "RAMP \u53c2\u6570");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Rw", "L+2*t_np"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"Rw", "L/2+t_np"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"Rw", "L"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "t_np"});
    model.component("comp1").geom("geom1").run("r3");
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
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("mun", new String[]{"500[cm^2/(V*s)]"});

    model.component("comp1").physics("semi").prop("ModelProperties").set("CarrierStatistics", "FermiDirac");
    model.component("comp1").physics("semi").prop("Continuation")
         .set("DopingTrapDensityContinuation", "UserDefined");
    model.component("comp1").physics("semi").prop("Continuation").set("cp_dtd_input", "ramp");
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm1").selection().all();
    model.component("comp1").physics("semi").feature("adm1").set("NAc", "Na_0");
    model.component("comp1").physics("semi").create("adm2", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm2").selection().all();
    model.component("comp1").physics("semi").feature("adm2").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm2").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm2").set("NDc", "Nd_0");
    model.component("comp1").physics("semi").feature("adm2").set("jwidth", "Rw");
    model.component("comp1").physics("semi").feature("adm2").set("jheight", "t_np");
    model.component("comp1").physics("semi").feature("adm2").set("jds", "t_np");
    model.component("comp1").physics("semi").feature("adm2").set("Nb_src", "root.comp1.semi.adm1.NAc");
    model.component("comp1").physics("semi").create("adm3", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm3").selection().all();
    model.component("comp1").physics("semi").feature("adm3").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm3").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm3").set("NDc", "Nd_0");
    model.component("comp1").physics("semi").feature("adm3").set("rb", new String[]{"0[um]", "0", "L+t_np"});
    model.component("comp1").physics("semi").feature("adm3").set("jwidth", "Rw");
    model.component("comp1").physics("semi").feature("adm3").set("jheight", "t_np");
    model.component("comp1").physics("semi").feature("adm3").set("jds", "t_np");
    model.component("comp1").physics("semi").feature("adm3").set("Nb_src", "root.comp1.semi.adm1.NAc");
    model.component("comp1").physics("semi").create("tar1", "TrapAssistedRecombination", 2);
    model.component("comp1").physics("semi").feature("tar1").selection().all();
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc1").selection().set(2);
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc2").selection().set(9);
    model.component("comp1").physics("semi").feature("mc2").set("V0", "Vd");
    model.component("comp1").physics("semi").create("gc1", "GateContact", 1);
    model.component("comp1").physics("semi").feature("gc1").selection().set(11, 12);
    model.component("comp1").physics("semi").feature("gc1").set("V0", "Vg");
    model.component("comp1").physics("semi").feature("gc1").set("epsilon_ins", 3.9);
    model.component("comp1").physics("semi").feature("gc1").set("d_ins", "t_ox");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").label("\u6805\u6781");
    model.component("comp1").selection("sel1").set(11, 12);

    model.component("comp1").physics("semi").feature("gc1").selection().named("sel1");
    model.component("comp1").physics("semi").create("tasr1", "TrapAssistedSurfaceRecombination", 1);
    model.component("comp1").physics("semi").feature("tasr1").selection().named("sel1");
    model.component("comp1").physics("semi").feature("tasr1").set("IncludeTraps", "ExplicitTraps");
    model.component("comp1").physics("semi").feature("tasr1").create("dtb1", "DiscreteEnergyLevelBoundary", 1);
    model.component("comp1").physics("semi").feature("tasr1").feature("dtb1").set("Nt_b", "Nt_0");
    model.component("comp1").physics("semi").feature("init1")
         .set("SpecifyInitialValues", "IntrinsicConcentrationsSetVoltage");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 4, 6, 8, 9);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 50);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 40);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(3, 11);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 50);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(5, 12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", 50);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemcount", 50);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(1, 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis5", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").selection().set(7, 13);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"semi/tasr1"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Rw", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "Rw", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "Nt_0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", 0, 0);
    model.study("std1").feature("stat").setIndex("pname", "Rw", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "m", 1);
    model.study("std1").feature("stat").setIndex("pname", "Rw", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "m", 1);
    model.study("std1").feature("stat").setIndex("pname", "ramp", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "1e-6 1e-4 1e-3 1e-2 1", 1);
    model.study("std1").feature("stat").set("sweeptype", "filled");
    model.study("std1").feature("stat2").set("useparam", true);
    model.study("std1").feature("stat2").set("sweeptype", "filled");
    model.study("std1").feature("stat2").setIndex("pname", "Rw", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "m", 0);
    model.study("std1").feature("stat2").setIndex("pname", "Rw", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "m", 0);
    model.study("std1").feature("stat2").setIndex("pname", "Vg", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", -0.49, 0);
    model.study("std1").feature("stat2").setIndex("pname", "Rw", 1);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat2").setIndex("punit", "m", 1);
    model.study("std1").feature("stat2").setIndex("pname", "Rw", 1);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat2").setIndex("punit", "m", 1);
    model.study("std1").feature("stat2").setIndex("pname", "Nt_0", 1);
    model.study("std1").feature("stat2").setIndex("plistarr", "0 100e14 500e14", 1);
    model.study("std1").setGenPlots(false);
    model.study("std1").label("\u7814\u7a76 1\uff1a\u9010\u6e10\u63d0\u5347\u63ba\u6742\u548c\u9677\u9631");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "semi.Ndoping");
    model.result("pg1").feature("surf1")
         .set("descr", "\u5e26\u7b26\u53f7\u7684\u79bb\u5b50\u5316\u63ba\u6742\u5242\u6d53\u5ea6");
    model.result("pg1").feature("surf1").set("unit", "1/cm^3");
    model.result("pg1").feature("surf1").set("resolution", "norefine");
    model.result("pg1").feature("surf1").set("smooth", "everywhere");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").label("\u63ba\u6742\u6d53\u5ea6");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/semi", true);
    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/semi", true);
    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("solnum", 1);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").set("sweeptype", "filled");
    model.study("std2").feature("stat").setIndex("pname", "Rw", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "Rw", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "Nt_0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", 0, 0);
    model.study("std2").feature("stat").setIndex("pname", "Rw", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "m", 1);
    model.study("std2").feature("stat").setIndex("pname", "Rw", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "m", 1);
    model.study("std2").feature("stat").setIndex("pname", "Vg", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "range(-0.5,0.1,1.5)", 1);
    model.study("std2").setGenPlots(false);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").label("\u7814\u7a76 2\uff1aVg \u626b\u63cf\u65e0\u9677\u9631");
    model.study("std3").feature("stat").set("useinitsol", true);
    model.study("std3").feature("stat").set("initmethod", "sol");
    model.study("std3").feature("stat").set("initstudy", "std1");
    model.study("std3").feature("stat").set("solnum", 2);
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").set("sweeptype", "filled");
    model.study("std3").feature("stat").setIndex("pname", "Rw", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "Rw", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "Nt_0", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "1e16", 0);
    model.study("std3").feature("stat").setIndex("pname", "Rw", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "", 1);
    model.study("std3").feature("stat").setIndex("punit", "m", 1);
    model.study("std3").feature("stat").setIndex("pname", "Rw", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "", 1);
    model.study("std3").feature("stat").setIndex("punit", "m", 1);
    model.study("std3").feature("stat").setIndex("pname", "Vg", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "range(-0.5,0.1,1.5)", 1);
    model.study("std3").setGenPlots(false);
    model.study("std3").label("\u7814\u7a76 3\uff1aVg \u626b\u63cf Nt=1e12[1/cm^2]");
    model.study("std4").feature("stat").set("useinitsol", true);
    model.study("std4").feature("stat").set("initmethod", "sol");
    model.study("std4").feature("stat").set("initstudy", "std1");
    model.study("std4").feature("stat").set("solnum", 3);
    model.study("std4").feature("stat").set("useparam", true);
    model.study("std4").feature("stat").set("sweeptype", "filled");
    model.study("std4").feature("stat").setIndex("pname", "Rw", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "m", 0);
    model.study("std4").feature("stat").setIndex("pname", "Rw", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "m", 0);
    model.study("std4").feature("stat").setIndex("pname", "Nt_0", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "5e16", 0);
    model.study("std4").feature("stat").setIndex("pname", "Rw", 1);
    model.study("std4").feature("stat").setIndex("plistarr", "", 1);
    model.study("std4").feature("stat").setIndex("punit", "m", 1);
    model.study("std4").feature("stat").setIndex("pname", "Rw", 1);
    model.study("std4").feature("stat").setIndex("plistarr", "", 1);
    model.study("std4").feature("stat").setIndex("punit", "m", 1);
    model.study("std4").feature("stat").setIndex("pname", "Vg", 1);
    model.study("std4").feature("stat").setIndex("plistarr", "range(-0.5,0.1,1.5)", 1);
    model.study("std4").setGenPlots(false);
    model.study("std4").label("\u7814\u7a76 4\uff1aVg \u626b\u63cf Nt=5e12[1/cm^3]");
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.study("std4").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u6805\u6781\u7535\u538b\uff0cVg (V)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u7535\u6d41 (A)");
    model.result("pg2").set("ylog", true);
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("data", "dset3");
    model.result("pg2").feature("glob1").setIndex("expr", "abs(semi.I0_1)", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "Nt_0 = 0 1/cm^2", 0);
    model.result("pg2").feature("glob1").set("autodescr", false);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("glob2", "glob1");
    model.result("pg2").run();
    model.result("pg2").feature("glob2").set("data", "dset4");
    model.result("pg2").feature("glob2").setIndex("descr", "Nt_0 = 1e-12 1/cm^2", 0);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("glob3", "glob2");
    model.result("pg2").run();
    model.result("pg2").feature("glob3").set("data", "dset5");
    model.result("pg2").feature("glob3").setIndex("descr", "Nt_0 = 5e-12 1/cm^2", 0);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6d41 vs. \u6805\u6781\u7535\u538b");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset5");
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").set("edges", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "V");
    model.result("pg3").feature("surf1").set("descr", "\u7535\u52bf");
    model.result("pg3").feature("surf1").set("resolution", "norefine");
    model.result("pg3").feature("surf1").set("smooth", "everywhere");
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("colortable", "Wave");
    model.result("pg3").feature("surf2").set("expr", "semi.log10N");
    model.result("pg3").feature("surf2").set("descr", "\u7535\u5b50\u6d53\u5ea6\u5bf9\u6570");
    model.result("pg3").feature("surf2").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("def1").set("expr", new String[]{"-20", ""});
    model.result("pg3").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("surf2").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf3", "surf2");
    model.result("pg3").run();
    model.result("pg3").feature("surf3").set("expr", "semi.log10P");
    model.result("pg3").feature("surf3").set("descr", "\u7a7a\u7a74\u6d53\u5ea6\u5bf9\u6570");
    model.result("pg3").run();
    model.result("pg3").feature("surf3").feature("def1").set("expr", new String[]{"-40", ""});
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf4", "surf3");
    model.result("pg3").run();
    model.result("pg3").feature("surf4").set("expr", "semi.log10normJ");
    model.result("pg3").feature("surf4").set("descr", "\u7535\u6d41\u5bc6\u5ea6\u6a21\u5bf9\u6570");
    model.result("pg3").feature("surf4").set("colortable", "ThermalDark");
    model.result("pg3").run();
    model.result("pg3").feature("surf4").feature("def1").set("expr", new String[]{"-60", ""});
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").label("J\u3001P\u3001N\u3001V");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset4");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(6);
    model.result("pg4").feature("lngr1").set("expr", "semi.normJ");
    model.result("pg4").feature("lngr1")
         .set("descr", "\u603b\u7535\u6d41\u5bc6\u5ea6\u6a21\uff0c\u8282\u70b9\u503c");
    model.result("pg4").feature("lngr1").set("resolution", "norefine");
    model.result("pg4").feature("lngr1").set("smooth", "everywhere");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").label("\u6cbf\u4e2d\u7ebf\u7684\u7535\u6d41");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevelinput", "last", 0);
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().set(6);
    model.result("pg5").feature("lngr1").set("expr", "V");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").setIndex("legends", "Nt_0 = 0.0 1/cm^2", 0);
    model.result("pg5").feature("lngr1").set("resolution", "norefine");
    model.result("pg5").feature("lngr1").set("smooth", "everywhere");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("data", "dset4");
    model.result("pg5").feature("lngr2").setIndex("looplevelinput", "last", 0);
    model.result("pg5").feature("lngr2").setIndex("legends", "Nt_0 = 1.0E12 1/cm^2", 0);
    model.result("pg5").feature().duplicate("lngr3", "lngr2");
    model.result("pg5").run();
    model.result("pg5").feature("lngr3").set("data", "dset5");
    model.result("pg5").feature("lngr3").setIndex("legends", "Nt_0 = 5.0E12 1/cm^2", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg5").label("\u6cbf\u4e2d\u7ebf\u7684\u7535\u538b");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset5");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("expr", "semi.Ec_e");
    model.result("pg6").feature("lngr1").set("descr", "\u5bfc\u5e26\u80fd\u7ea7");
    model.result("pg6").feature("lngr1").set("unit", "eV");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "r");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").setIndex("legends", "\u5bfc\u5e26", 0);
    model.result("pg6").feature("lngr1").set("resolution", "norefine");
    model.result("pg6").feature("lngr1").set("smooth", "everywhere");
    model.result("pg6").feature("lngr1").selection().set(6);
    model.result("pg6").feature().duplicate("lngr2", "lngr1");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").set("expr", "semi.Ev_e");
    model.result("pg6").feature("lngr2").set("descr", "\u4ef7\u5e26\u80fd\u7ea7");
    model.result("pg6").feature("lngr2").setIndex("legends", "\u4ef7\u5e26", 0);
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("lngr3", "lngr2");
    model.result("pg6").run();
    model.result("pg6").feature("lngr3").set("expr", "semi.Efn_e");
    model.result("pg6").feature("lngr3").set("descr", "\u7535\u5b50\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg6").feature("lngr3").set("linestyle", "dashed");
    model.result("pg6").feature("lngr3").setIndex("legends", "\u7535\u5b50\u51c6\u8d39\u7c73\u80fd\u7ea7", 0);
    model.result("pg6").feature().duplicate("lngr4", "lngr3");
    model.result("pg6").run();
    model.result("pg6").feature("lngr4").set("expr", "semi.Efp_e");
    model.result("pg6").feature("lngr4").set("descr", "\u7a7a\u7a74\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg6").feature("lngr4").set("linestyle", "dotted");
    model.result("pg6").feature("lngr4").setIndex("legends", "\u7a7a\u7a74\u51c6\u8d39\u7c73\u80fd\u7ea7", 0);
    model.result("pg6").run();
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u80fd\u7ea7\u56fe");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "r (nm)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u80fd\u91cf");
    model.result("pg6").set("legendpos", "middleleft");
    model.result("pg6").run();
    model.result("pg6").label("\u6cbf\u4e2d\u7ebf\u7684\u80fd\u5e26\u56fe");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("data", "dset5");
    model.result("pg7").setIndex("looplevelinput", "manual", 0);
    model.result("pg7").setIndex("looplevel", new int[]{1, 6, 16}, 0);
    model.result("pg7").set("legendpos", "middleleft");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").selection().named("sel1");
    model.result("pg7").feature("lngr1").set("expr", "semi.tasr1.dtb1.ft");
    model.result("pg7").feature("lngr1").set("descr", "\u9677\u9631\u5360\u6709\u7387");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "z");
    model.result("pg7").feature("lngr1").set("resolution", "norefine");
    model.result("pg7").feature("lngr1").set("smooth", "everywhere");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").label("\u9677\u9631\u5360\u6709\u7387");

    model.title("\u7845\u7eb3\u7c73\u7ebf\u73af\u6805\u5143\u4ef6\u7684\u8868\u9762\u9677\u9631");

    model
         .description("\u672c\u4f8b\u5206\u6790\u4e00\u4e2a\u5728\u6805\u6781\u5904\u5177\u6709\u4e0d\u540c\u9677\u9631\u5bc6\u5ea6\u7684\u7845\u7eb3\u7c73\u7ebf\u73af\u6805\u5143\u4ef6\u3002\u9677\u9631\u7684\u4f5c\u7528\u662f\u589e\u52a0\u5143\u4ef6\u7684\u9608\u503c\u7535\u538b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("nanowire_traps.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
