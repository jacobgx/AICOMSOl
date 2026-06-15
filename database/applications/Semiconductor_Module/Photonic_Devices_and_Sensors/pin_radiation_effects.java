/*
 * pin_radiation_effects.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:52 by COMSOL 6.3.0.290. */
public class pin_radiation_effects {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Photonic_Devices_and_Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");

    model.study().create("std1");
    model.study("std1").create("semie", "SemiconductorEquilibrium");
    model.study("std1").feature("semie").set("ftplistmethod", "manual");
    model.study("std1").feature("semie").set("solnum", "auto");
    model.study("std1").feature("semie").set("notsolnum", "auto");
    model.study("std1").feature("semie").set("outputmap", new String[]{});
    model.study("std1").feature("semie").set("ngenAUX", "1");
    model.study("std1").feature("semie").set("goalngenAUX", "1");
    model.study("std1").feature("semie").set("ngenAUX", "1");
    model.study("std1").feature("semie").set("goalngenAUX", "1");
    model.study("std1").feature("semie").setSolveFor("/physics/semi", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.param().set("L", "300[um]");
    model.param().descr("L", "\u5668\u4ef6\u957f\u5ea6");
    model.param().set("V0", "1000[V]");
    model.param().descr("V0", "\u5916\u52a0\u7535\u538b");
    model.param().set("Nds", "1e20[cm^-3]");
    model.param().descr("Nds", "N+ \u548c P+ \u63ba\u6742\u7684\u8868\u9762\u6d53\u5ea6");
    model.param().set("dj", "25[um]");
    model.param().descr("dj", "N+ \u548c P+ \u63ba\u6742\u7684\u7ed3\u6df1");
    model.param().set("Npi", "2e12[cm^-3]");
    model.param().descr("Npi", "\u672c\u5f81\u63ba\u6742\u6d53\u5ea6");
    model.param().set("tau", "100[us]");
    model.param().descr("tau", "\u8f7d\u6d41\u5b50\u5bff\u547d");
    model.param().set("mup0", "480[cm^2/V/s]");
    model.param().descr("mup0", "\u4f4e\u573a\uff0c\u4f4e\u63ba\u6742\uff0c\u7a7a\u7a74\u8fc1\u79fb\u7387");
    model.param().set("mun0", "1350[cm^2/V/s]");
    model.param().descr("mun0", "\u4f4e\u573a\uff0c\u4f4e\u63ba\u6742\uff0c\u7535\u5b50\u8fc1\u79fb\u7387");
    model.param().set("cp", "1");
    model.param().descr("cp", "\u8fc1\u79fb\u7387\u6a21\u578b\u7684\u8fde\u7eed\u53c2\u6570");
    model.param().set("RadSi", "0");
    model.param().descr("RadSi", "\u8f90\u5c04\u5242\u91cf\u7387 (Rad(Si)/s)");
    model.param().set("t", "0[s]");
    model.param().descr("t", "\u65f6\u95f4\u53c2\u6570");
    model.param().set("tp", "0.5[ns]");
    model.param().descr("tp", "\u8f90\u5c04\u8109\u51b2\u6301\u7eed\u65f6\u95f4");
    model.param().set("gR", "4.03e13*RadSi[1/cm^3/s]*pw1(t/tp)");
    model.param().descr("gR", "\u8f90\u5c04\u5242\u91cf\u4ea7\u751f\u7387");
    model.param().set("index", "1");
    model.param().descr("index", "\u89e3\u6807\u5ea6\u53c2\u6570");
    model.param().set("area", "1[mm^2]");
    model.param().descr("area", "\u4e00\u7ef4\u6a21\u578b\u7684\u6a2a\u622a\u9762\u79ef");

    model.func().create("pw1", "Piecewise");
    model.func("pw1").setIndex("pieces", 0, 0, 0);
    model.func("pw1").setIndex("pieces", 1, 0, 1);
    model.func("pw1").setIndex("pieces", "x", 0, 2);
    model.func("pw1").setIndex("pieces", 1, 1, 0);
    model.func("pw1").setIndex("pieces", 2, 1, 1);
    model.func("pw1").setIndex("pieces", 0, 1, 2);
    model.func("pw1").set("argunit", "1");
    model.func("pw1").set("fununit", "1");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L", 1);

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("E", "semi.normE/1[V/cm]");
    model.component("comp1").variable("var1").descr("E", "\u7535\u573a\u5f3a\u5ea6 (V/cm)");
    model.component("comp1").variable("var1").set("alphap", "1.8e7[cm^-1]*exp(-3.2e6/E)");
    model.component("comp1").variable("var1").descr("alphap", "\u7535\u79bb\u7cfb\u6570\uff0c\u7a7a\u7a74");
    model.component("comp1").variable("var1").set("alphan", "2.4e7[cm^-1]*exp(-1.6e6/E)");
    model.component("comp1").variable("var1").descr("alphan", "\u7535\u79bb\u7cfb\u6570\uff0c\u7535\u5b50");
    model.component("comp1").variable("var1").set("NDt", "(semi.Ndplus+semi.Naminus)/1[cm^-3]");
    model.component("comp1").variable("var1")
         .descr("NDt", "\u603b\u7535\u79bb\u63ba\u6742\u5242\u6d53\u5ea6 (1/cm^3)");
    model.component("comp1").variable("var1")
         .set("mun", "mun0/(1+81*NDt/(NDt+3.24e18))^0.5/(1+cp*(E/8e3)^4.9*(E+1.64e5)/(E+1))^(1/4.9)");
    model.component("comp1").variable("var1").descr("mun", "\u7535\u5b50\u8fc1\u79fb\u7387");
    model.component("comp1").variable("var1")
         .set("mup", "mup0/(1+350*NDt/(NDt+1.05e19))^0.5/(1+cp*(E/8.72e4)^1.15*(E+8.51e5)/(E+8.12e4))^(1/1.15)");
    model.component("comp1").variable("var1").descr("mup", "\u7a7a\u7a74\u8fc1\u79fb\u7387");

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

    model.component("comp1").physics("semi").prop("d").set("A", "area");
    model.component("comp1").physics("semi").feature("smm1").set("mun_mat", "userdef");
    model.component("comp1").physics("semi").feature("smm1").set("mun", "mun");
    model.component("comp1").physics("semi").feature("smm1").set("mup_mat", "userdef");
    model.component("comp1").physics("semi").feature("smm1").set("mup", "mup");
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 1);
    model.component("comp1").physics("semi").feature("adm1")
         .label("\u89e3\u6790\u63ba\u6742\u6a21\u578b 1\uff1a\u672c\u5f81");
    model.component("comp1").physics("semi").feature("adm1").selection().all();
    model.component("comp1").physics("semi").feature("adm1").set("NAc", "Npi");
    model.component("comp1").physics("semi").create("gdm1", "GeometricDopingModel", 1);
    model.component("comp1").physics("semi").feature("gdm1").label("\u51e0\u4f55\u63ba\u6742\u6a21\u578b 1\uff1aP+");
    model.component("comp1").physics("semi").feature("gdm1").selection().all();
    model.component("comp1").physics("semi").feature("gdm1").set("impurityProfile", "erf_func");
    model.component("comp1").physics("semi").feature("gdm1").set("NAgen", "Nds");
    model.component("comp1").physics("semi").feature("gdm1").set("jd_gen", "dj");
    model.component("comp1").physics("semi").feature("gdm1").set("Nb_gen_src", "root.comp1.semi.adm1.NAc");
    model.component("comp1").physics("semi").feature().duplicate("gdm2", "gdm1");
    model.component("comp1").physics("semi").feature("gdm2").label("\u51e0\u4f55\u63ba\u6742\u6a21\u578b 2\uff1aN+");
    model.component("comp1").physics("semi").feature("gdm2").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("gdm2").set("NDgen", "Nds");
    model.component("comp1").physics("semi").feature("gdm1").feature("gdmbs1").selection().set(2);
    model.component("comp1").physics("semi").feature("gdm2").feature("gdmbs1").selection().set(1);
    model.component("comp1").physics("semi").create("tar1", "TrapAssistedRecombination", 1);
    model.component("comp1").physics("semi").feature("tar1")
         .label("\u9677\u9631\u8f85\u52a9\u590d\u5408 1\uff1aSRH");
    model.component("comp1").physics("semi").feature("tar1").selection().all();
    model.component("comp1").physics("semi").feature("tar1").set("taun_mat", "userdef");
    model.component("comp1").physics("semi").feature("tar1").set("taun", "tau");
    model.component("comp1").physics("semi").feature("tar1").set("taup_mat", "userdef");
    model.component("comp1").physics("semi").feature("tar1").set("taup", "tau");
    model.component("comp1").physics("semi").create("iig1", "IIGeneration", 1);
    model.component("comp1").physics("semi").feature("iig1").selection().all();
    model.component("comp1").physics("semi").feature("iig1").set("iiG_model", "UserDefined");
    model.component("comp1").physics("semi").feature("iig1").set("alpha_n", "alphan");
    model.component("comp1").physics("semi").feature("iig1").set("alpha_p", "alphap");
    model.component("comp1").physics("semi").create("udg1", "UDGeneration", 1);
    model.component("comp1").physics("semi").feature("udg1")
         .label("\u7528\u6237\u5b9a\u4e49\u4ea7\u751f 1\uff1a\u8f90\u5c04\u6548\u5e94");
    model.component("comp1").physics("semi").feature("udg1").selection().all();
    model.component("comp1").physics("semi").feature("udg1").set("Gn", "gR");
    model.component("comp1").physics("semi").feature("udg1").set("Gp", "gR");
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 0);
    model.component("comp1").physics("semi").feature("mc1").label("\u91d1\u5c5e\u63a5\u89e6 1\uff1a\u63a5\u5730");
    model.component("comp1").physics("semi").feature("mc1").selection().set(2);
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 0);
    model.component("comp1").physics("semi").feature("mc2").label("\u91d1\u5c5e\u63a5\u89e6 2\uff1aV0");
    model.component("comp1").physics("semi").feature("mc2").selection().set(1);
    model.component("comp1").physics("semi").feature("mc2").set("V0", "V0");

    model.component("comp1").mesh("mesh1").autoMeshSize(2);

    model.study("std1")
         .label("\u7814\u7a76 1a\uff1a\u4f7f\u7528\u4e0e\u573a\u65e0\u5173\u8fc1\u79fb\u7387\u7684\u6e10\u53d8 V0");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").set("sweeptype", "filled");
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "cp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", 0, 0);
    model.study("std1").feature("stat").setIndex("pname", "L", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "m", 1);
    model.study("std1").feature("stat").setIndex("pname", "L", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "m", 1);
    model.study("std1").feature("stat").setIndex("pname", "V0", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "0 0.5 10 50 100 250 1000", 1);
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s2").feature("p1").set("porder", "linear");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("solnum", "manual");
    model.study("std2").feature("stat").set("manualsolnum", "index");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "L", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "L", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "V0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "0 0.5 10 50 100 250 1000", 0);
    model.study("std2").feature("param").setIndex("pname", "L", 1);
    model.study("std2").feature("param").setIndex("plistarr", "", 1);
    model.study("std2").feature("param").setIndex("punit", "m", 1);
    model.study("std2").feature("param").setIndex("pname", "L", 1);
    model.study("std2").feature("param").setIndex("plistarr", "", 1);
    model.study("std2").feature("param").setIndex("punit", "m", 1);
    model.study("std2").feature("param").setIndex("pname", "index", 1);
    model.study("std2").feature("param").setIndex("plistarr", "range(1,7)", 1);
    model.study("std2")
         .label("\u7814\u7a76 1b\uff1a\u4f7f\u7528\u5b8c\u6574\u8fc1\u79fb\u7387\u6a21\u578b\u7684\u6e10\u53d8 V0");
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std2");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u80fd\u7ea7 (semi)");
    model.result("pg1").set("data", "dset4");
    model.result("pg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "Energy Diagram");
    model.result("pg1").set("ylabel", "Energy (eV)");
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").label("\u5bfc\u5e26\u80fd\u7ea7");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("expr", "semi.Ec_e");
    model.result("pg1").feature("lngr1").set("unit", "eV");
    model.result("pg1").feature("lngr1").set("linecolor", "blue");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").set("legends", new String[]{"Ec"});
    model.result("pg1").feature("lngr1").set("resolution", "norefine");
    model.result("pg1").feature("lngr1").set("smooth", "everywhere");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature().create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").label("\u7535\u5b50\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg1").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr2").set("expr", "semi.Efn_e");
    model.result("pg1").feature("lngr2").set("unit", "eV");
    model.result("pg1").feature("lngr2").set("linestyle", "dashed");
    model.result("pg1").feature("lngr2").set("linecolor", "black");
    model.result("pg1").feature("lngr2").set("legend", true);
    model.result("pg1").feature("lngr2").set("legendmethod", "manual");
    model.result("pg1").feature("lngr2").set("legends", new String[]{"Efn"});
    model.result("pg1").feature("lngr2").set("resolution", "norefine");
    model.result("pg1").feature("lngr2").set("smooth", "everywhere");
    model.result("pg1").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr2").set("data", "parent");
    model.result("pg1").feature("lngr2").selection().geom("geom1", 1);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").feature("lngr2").selection().set(1);
    model.result("pg1").feature().create("lngr3", "LineGraph");
    model.result("pg1").feature("lngr3").label("\u7a7a\u7a74\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg1").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr3").set("expr", "semi.Efp_e");
    model.result("pg1").feature("lngr3").set("unit", "eV");
    model.result("pg1").feature("lngr3").set("linestyle", "dotted");
    model.result("pg1").feature("lngr3").set("linecolor", "black");
    model.result("pg1").feature("lngr3").set("legend", true);
    model.result("pg1").feature("lngr3").set("legendmethod", "manual");
    model.result("pg1").feature("lngr3").set("legends", new String[]{"Efp"});
    model.result("pg1").feature("lngr3").set("resolution", "norefine");
    model.result("pg1").feature("lngr3").set("smooth", "everywhere");
    model.result("pg1").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr3").set("data", "parent");
    model.result("pg1").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr3").selection().set(1);
    model.result("pg1").feature().create("lngr4", "LineGraph");
    model.result("pg1").feature("lngr4").label("\u4ef7\u5e26\u80fd\u7ea7");
    model.result("pg1").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr4").set("expr", "semi.Ev_e");
    model.result("pg1").feature("lngr4").set("unit", "eV");
    model.result("pg1").feature("lngr4").set("linecolor", "green");
    model.result("pg1").feature("lngr4").set("legend", true);
    model.result("pg1").feature("lngr4").set("legendmethod", "manual");
    model.result("pg1").feature("lngr4").set("legends", new String[]{"Ev"});
    model.result("pg1").feature("lngr4").set("resolution", "norefine");
    model.result("pg1").feature("lngr4").set("smooth", "everywhere");
    model.result("pg1").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr4").set("data", "parent");
    model.result("pg1").feature("lngr4").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr4").selection().set(1);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u8f7d\u6d41\u5b50\u6d53\u5ea6 (semi)");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg2").set("ylabel", "Carrier concentration (1/cm^3)");
    model.result("pg2").set("ylog", true);
    model.result("pg2").feature().create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").label("\u7535\u5b50\u6d53\u5ea6");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("expr", "semi.N");
    model.result("pg2").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").set("legends", new String[]{"electrons"});
    model.result("pg2").feature("lngr1").set("resolution", "norefine");
    model.result("pg2").feature("lngr1").set("smooth", "everywhere");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("data", "parent");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature().create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").label("\u7a7a\u7a74\u6d53\u5ea6");
    model.result("pg2").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr2").set("expr", "semi.P");
    model.result("pg2").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("legendmethod", "manual");
    model.result("pg2").feature("lngr2").set("legends", new String[]{"holes"});
    model.result("pg2").feature("lngr2").set("resolution", "norefine");
    model.result("pg2").feature("lngr2").set("smooth", "everywhere");
    model.result("pg2").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr2").set("data", "parent");
    model.result("pg2").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr2").selection().set(1);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u7535\u52bf (semi)");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg3").set("ylabel", "Electric Potential (V)");
    model.result("pg3").feature().create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg3").feature("lngr1").set("expr", "V");
    model.result("pg3").feature("lngr1").set("resolution", "norefine");
    model.result("pg3").feature("lngr1").set("smooth", "everywhere");
    model.result("pg3").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg3").feature("lngr1").set("data", "parent");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr1").selection().all();
    model.result("pg4").feature("lngr1").set("xdataexpr", "X");
    model.result("pg4").feature("lngr1").set("expr", "semi.Nnetdop");
    model.result("pg4").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg4").feature("lngr1").set("linecolor", "red");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").set("legends", new String[]{"P \u578b (\u7ea2\u8272)"});
    model.result("pg4").feature("lngr1").feature().create("filt1", "LineGraphFilter");
    model.result("pg4").feature("lngr1").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg4").feature("lngr2").selection().all();
    model.result("pg4").feature("lngr2").set("xdataexpr", "X");
    model.result("pg4").feature("lngr2").set("expr", "semi.Nnetdop");
    model.result("pg4").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg4").feature("lngr2").set("linecolor", "blue");
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("legendmethod", "manual");
    model.result("pg4").feature("lngr2").set("legends", new String[]{"N \u578b (\u84dd\u8272)"});
    model.result("pg4").feature("lngr2").feature().create("filt1", "LineGraphFilter");
    model.result("pg4").feature("lngr2").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("legendpos", "uppermiddle");
    model.result("pg4")
         .set("ylabel", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert, 1/cm<sup>3</sup>");
    model.result("pg4").set("ylog", true);
    model.result("pg4").feature("lngr1").label("P \u578b");
    model.result("pg4").feature("lngr2").label("N \u578b");
    model.result("pg4").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi)");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result("pg3").run();
    model.result("pg3").label("\u56fe 3 \u63ba\u6742\u66f2\u7ebf");
    model.result("pg3").setIndex("looplevelinput", "first", 0);
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("expr", "abs(semi.Ndoping)");
    model.result("pg3").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg3").feature("lngr1").set("descractive", true);
    model.result("pg3").feature("lngr1").set("descr", "\u63ba\u6742\u5bc6\u5ea6");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").run();
    model.result("pg3").set("ylabelactive", false);
    model.result("pg3").set("ylog", true);
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmin", 0);
    model.result("pg3").set("xmax", 300);
    model.result("pg3").set("ymin", "1e10");
    model.result("pg3").set("ymax", "1e20");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u56fe 4A \u7a7a\u7a74\u5206\u5e03");
    model.result("pg4").setIndex("looplevelinput", "all", 0);
    model.result("pg4").set("xmax", 1);
    model.result("pg4").set("ymax", "1e19");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").set("expr", "semi.P");
    model.result("pg4").feature("lngr1").set("descractive", false);
    model.result("pg4").feature("lngr1").set("xdataexpr", "x/L");
    model.result("pg4").feature("lngr1").set("xdatadescractive", true);
    model.result("pg4").feature("lngr1").set("xdatadescr", "\u5206\u6570\u8ddd\u79bb");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u56fe 4B \u7535\u573a\u5206\u5e03");
    model.result("pg5").set("ymin", "1e2");
    model.result("pg5").set("ymax", "1e5");
    model.result("pg5").set("legendpos", "uppermiddle");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("expr", "semi.normE");
    model.result("pg5").feature("lngr1").set("unit", "V/cm");
    model.result("pg5").run();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std3").label("\u7814\u7a76 2\uff1a\u7a33\u6001\u8f90\u5c04\u6548\u5e94");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("stat").set("useinitsol", true);
    model.study("std3").feature("stat").set("initmethod", "sol");
    model.study("std3").feature("stat").set("initstudy", "std2");
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").set("sweeptype", "filled");
    model.study("std3").feature("stat").setIndex("pname", "L", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "L", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "t", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "tp", 0);
    model.study("std3").feature("stat").setIndex("pname", "L", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "", 1);
    model.study("std3").feature("stat").setIndex("punit", "m", 1);
    model.study("std3").feature("stat").setIndex("pname", "L", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "", 1);
    model.study("std3").feature("stat").setIndex("punit", "m", 1);
    model.study("std3").feature("stat").setIndex("pname", "RadSi", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "{0.25,2.5,5.0,5.7}*1e8", 1);
    model.study("std3").showAutoSequences("all");

    model.sol("sol12").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol12").feature("s1").feature("fc1").set("damp", "0.6");
    model.sol("sol12").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol12").feature("s1").feature("p1").set("pinitstep", "5e7");
    model.sol("sol12").feature("s1").feature("p1").set("pmaxstep", "1e8");
    model.sol("sol12").feature("s1").feature("p1").set("porder", "linear");
    model.sol("sol12").feature("s1").feature("fc1").set("maxiter", 20);
    model.sol("sol12").feature("s1").feature("fc1").set("stabacc", "aacc");
    model.sol("sol12").feature("s1").feature("fc1").set("aaccdim", 5);

    model.study("std3").createAutoSequences("all");

    model.sol("sol12").runAll();

    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u56fe 5A \u7a33\u6001\u7535\u573a\u5206\u5e03 vs. \u5242\u91cf\u7387");
    model.result("pg6").set("data", "dset5");
    model.result("pg6").set("ymax", "1e6");
    model.result("pg6").set("legendpos", "lowermiddle");
    model.result("pg6").run();
    model.result("pg4").run();
    model.result().duplicate("pg7", "pg4");
    model.result("pg7").run();
    model.result("pg7").label("\u56fe 5B \u7a33\u6001\u7a7a\u7a74\u5206\u5e03 vs. \u5242\u91cf\u7387");
    model.result("pg7").set("data", "dset5");
    model.result("pg7").set("ymin", "1e11");
    model.result("pg7").set("ymax", "1e15");
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u56fe 2A \u8fc1\u79fb\u7387 vs. \u63ba\u6742");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").set("titletype", "none");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "\u63ba\u6742 (cm<sup>-3</sup>)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u8fc1\u79fb\u7387 (cm<sup>2</sup>/V-s)");
    model.result("pg8").set("xlog", true);
    model.result("pg8").set("ylog", false);
    model.result("pg8").set("xmin", "1e13");
    model.result("pg8").set("xmax", "1e20");
    model.result("pg8").set("ymin", 0);
    model.result("pg8").set("ymax", 1400);
    model.result("pg8").set("legendpos", "upperright");
    model.result("pg8").run();
    model.result("pg8").feature("lngr1").set("expr", "semi.mun");
    model.result("pg8").feature("lngr1").set("unit", "cm^2/(V*s)");
    model.result("pg8").feature("lngr1").set("xdataexpr", "NDt");
    model.result("pg8").feature("lngr1").set("xdatadescr", "\u7535\u79bb\u6742\u8d28\u6d53\u5ea6");
    model.result("pg8").feature("lngr1").set("linestyle", "none");
    model.result("pg8").feature("lngr1").set("linemarker", "cycle");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("legend", false);
    model.result("pg8").feature().duplicate("lngr2", "lngr1");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").set("expr", "semi.mup");
    model.result("pg8").feature("lngr2").set("linemarker", "cyclereset");
    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").label("\u4e00\u7ef4\u6805\u683c 1\uff1alogNDt");
    model.result().dataset("grid1").set("source", "function");
    model.result().dataset("grid1").set("function", "pw1");
    model.result().dataset("grid1").set("par1", "logNDt");
    model.result().dataset("grid1").set("parmin1", 13);
    model.result().dataset("grid1").set("parmax1", 20);
    model.result("pg8").run();
    model.result("pg8").create("lngr3", "LineGraph");
    model.result("pg8").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr3").set("linewidth", "preference");
    model.result("pg8").feature("lngr3").set("data", "grid1");
    model.result("pg8").feature("lngr3").set("expr", "mun0[V*s/cm^2]/(1+81*10^logNDt/(10^logNDt+3.24e18))^0.5");
    model.result("pg8").feature("lngr3").set("descractive", true);
    model.result("pg8").feature("lngr3").set("descr", "\u7535\u5b50\u8fc1\u79fb\u7387");
    model.result("pg8").feature("lngr3").set("xdata", "expr");
    model.result("pg8").feature("lngr3").set("xdataexpr", "10^logNDt");
    model.result("pg8").feature("lngr3").set("linecolor", "cyan");
    model.result("pg8").feature("lngr3").set("linewidth", 2);
    model.result("pg8").feature("lngr3").set("legend", true);
    model.result("pg8").feature("lngr3").set("autosolution", false);
    model.result("pg8").feature("lngr3").set("autodescr", true);
    model.result("pg8").feature().duplicate("lngr4", "lngr3");
    model.result("pg8").run();
    model.result("pg8").feature("lngr4").set("expr", "mup0[V*s/cm^2]/(1+350*10^logNDt/(10^logNDt+1.05e19))^0.5");
    model.result("pg8").feature("lngr4").set("descr", "\u7a7a\u7a74\u8fc1\u79fb\u7387");
    model.result("pg8").feature("lngr4").set("linecolor", "green");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u56fe 2B \u901f\u5ea6 vs. \u7535\u573a");
    model.result("pg9").set("data", "dset5");
    model.result("pg9").set("xlabel", "\u7535\u573a (V/cm)");
    model.result("pg9").set("ylabel", "\u6f02\u79fb\u901f\u5ea6 (cm/s)");
    model.result("pg9").set("xlog", false);
    model.result("pg9").set("xmin", 0);
    model.result("pg9").set("xmax", "1e5");
    model.result("pg9").set("ymax", "9e6");
    model.result("pg9").set("legendpos", "lowerright");
    model.result("pg9").run();
    model.result("pg9").feature("lngr1").set("expr", "semi.mun*semi.normE");
    model.result("pg9").feature("lngr1").set("unit", "cm/s");
    model.result("pg9").feature("lngr1").set("xdataexpr", "semi.normE[cm/V]");
    model.result("pg9").feature("lngr1").set("xdatadescractive", false);
    model.result("pg9").run();
    model.result("pg9").feature("lngr2").set("expr", "semi.mup*semi.normE");
    model.result("pg9").feature("lngr2").set("unit", "cm/s");
    model.result("pg9").feature("lngr2").set("xdataexpr", "semi.normE[cm/V]");
    model.result("pg9").feature("lngr2").set("xdatadescractive", false);
    model.result().dataset().duplicate("grid2", "grid1");
    model.result().dataset("grid2").label("\u4e00\u7ef4\u6805\u683c 1\uff1aE1");
    model.result().dataset("grid2").set("par1", "E1");
    model.result().dataset("grid2").set("parmin1", 0);
    model.result().dataset("grid2").set("parmax1", "1e5");
    model.result("pg9").run();
    model.result("pg9").feature("lngr3").set("data", "grid2");
    model.result("pg9").feature("lngr3")
         .set("expr", "E1*mun0[V*s/cm^2]/(1+(E1/8e3)^4.9*(E1+1.64e5)/(E1+1))^(1/4.9)");
    model.result("pg9").feature("lngr3").set("descr", "\u7535\u5b50\u901f\u5ea6");
    model.result("pg9").feature("lngr3").set("xdataexpr", "E1");
    model.result("pg9").feature("lngr3").set("xdataunit", "m");
    model.result("pg9").feature("lngr3").set("linecolor", "red");
    model.result("pg9").run();
    model.result("pg9").feature("lngr4").set("data", "grid2");
    model.result("pg9").feature("lngr4")
         .set("expr", "E1*mup0[V*s/cm^2]/(1+(E1/8.72e4)^1.15*(E1+8.51e5)/(E1+8.12e4))^(1/1.15)");
    model.result("pg9").feature("lngr4").set("descr", "\u7a7a\u7a74\u901f\u5ea6");
    model.result("pg9").feature("lngr4").set("xdataexpr", "E1");
    model.result("pg9").feature("lngr4").set("xdataunit", "m");
    model.result("pg9").feature("lngr4").set("linecolor", "magenta");
    model.result("pg9").run();

    model.study().create("std4");
    model.study("std4").create("time", "Transient");
    model.study("std4").feature("time").setSolveFor("/physics/semi", true);
    model.study("std4").feature("time").set("tunit", "ns");
    model.study("std4").feature("time")
         .set("tlist", "0 0.1 0.2 0.3 0.5 0.6 0.8 1 1.3 1.7 2 2.4 3 3.5 4 4.3 4.7 5 6 7 8 10");
    model.study("std4").feature("time").set("usertol", true);
    model.study("std4").feature("time").set("rtol", 1.0E-8);
    model.study("std4").feature("time").set("useinitsol", true);
    model.study("std4").feature("time").set("initmethod", "sol");
    model.study("std4").feature("time").set("initstudy", "std2");
    model.study("std4").feature("time").set("useparam", true);
    model.study("std4").feature("time").setIndex("pname", "L", 0);
    model.study("std4").feature("time").setIndex("plistarr", "", 0);
    model.study("std4").feature("time").setIndex("punit", "m", 0);
    model.study("std4").feature("time").setIndex("pname", "L", 0);
    model.study("std4").feature("time").setIndex("plistarr", "", 0);
    model.study("std4").feature("time").setIndex("punit", "m", 0);
    model.study("std4").feature("time").setIndex("pname", "RadSi", 0);
    model.study("std4").feature("time").setIndex("plistarr", "2.5e8 2.5e10", 0);
    model.study("std4").feature("time").setIndex("punit", "s", 0);
    model.study("std4").label("\u7814\u7a76 3\uff1a\u8109\u51b2\u8f90\u5c04\u6548\u5e94");
    model.study("std4").setGenPlots(false);
    model.study("std4").showAutoSequences("all");

    model.sol("sol13").feature("v1").set("scalemethod", "init");

    model.study("std4").createAutoSequences("all");

    model.sol("sol13").runAll();

    model.result("pg7").run();
    model.result().duplicate("pg10", "pg7");
    model.result("pg10").run();
    model.result("pg10").label("\u56fe 7A \u591a\u4e2a\u65f6\u95f4\u7684\u7a7a\u7a74\u5206\u5e03");
    model.result("pg10").set("data", "dset6");
    model.result("pg10").setIndex("looplevelinput", "last", 1);
    model.result("pg10").set("ymax", "1e16");
    model.result("pg10").setIndex("looplevelinput", "manual", 0);
    model.result("pg10").setIndex("looplevel", new int[]{6, 8, 11, 18}, 0);
    model.result("pg10").run();
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").label("\u56fe 7B \u591a\u4e2a\u65f6\u95f4\u7684\u7535\u5b50\u5206\u5e03");
    model.result("pg11").set("legendpos", "lowerleft");
    model.result("pg11").run();
    model.result("pg11").feature("lngr1").set("expr", "semi.N");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result().duplicate("pg12", "pg11");
    model.result("pg12").run();
    model.result("pg12").label("\u56fe 7C \u591a\u4e2a\u65f6\u95f4\u7684\u7535\u573a\u5206\u5e03");
    model.result("pg12").setIndex("looplevel", new int[]{1, 6, 8, 11, 18}, 0);
    model.result("pg12").set("ymin", "1e2");
    model.result("pg12").set("ymax", "1e6");
    model.result("pg12").set("legendpos", "uppermiddle");
    model.result("pg12").run();
    model.result("pg12").feature("lngr1").set("expr", "semi.normE");
    model.result("pg12").feature("lngr1").set("unit", "V/cm");
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").label("\u56fe 8 \u77ac\u6001\u5149\u7535\u6d41\u54cd\u5e94");
    model.result("pg13").set("data", "none");
    model.result("pg13").set("twoyaxes", true);
    model.result("pg13").create("glob1", "Global");
    model.result("pg13").feature("glob1").set("markerpos", "datapoints");
    model.result("pg13").feature("glob1").set("linewidth", "preference");
    model.result("pg13").feature("glob1").set("data", "dset6");
    model.result("pg13").feature("glob1").setIndex("looplevelinput", "first", 1);
    model.result("pg13").feature("glob1").setIndex("expr", "semi.I0_2/area", 0);
    model.result("pg13").feature("glob1").setIndex("unit", "A/cm^2", 0);
    model.result("pg13").feature("glob1").setIndex("descr", "\u7535\u6d41\u5bc6\u5ea6", 0);
    model.result("pg13").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg13").feature().duplicate("glob2", "glob1");
    model.result("pg13").run();
    model.result("pg13").feature("glob2").setIndex("looplevelinput", "last", 1);
    model.result("pg13").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg13").run();
    model.result().dataset().create("par1", "Parametric1D");
    model.result().dataset("par1").set("data", "dset6");
    model.result().dataset("par1").setIndex("looplevelinput", "last", 1);
    model.result().create("pg14", "PlotGroup2D");
    model.result("pg14").run();
    model.result("pg14")
         .label("\u7a7a\u7a74\u5bc6\u5ea6\u5206\u5e03\u548c\u6f02\u79fb\u901f\u5ea6\u7684\u6f14\u53d8");
    model.result("pg14").set("titletype", "manual");
    model.result("pg14")
         .set("title", "x\uff1a\u8ddd\u79bb y\uff1a\u65f6\u95f4 z\uff1alog<sub>10</sub>\uff08\u7a7a\u7a74\u5bc6\u5ea6 (cm<sup>-3</sup>)\uff09 \u989c\u8272\uff1a\u6f02\u79fb\u901f\u5ea6 (cm/s)");
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", "semi.normE*semi.mup");
    model.result("pg14").feature("surf1").set("unit", "cm/s");
    model.result("pg14").feature("surf1").create("hght1", "Height");
    model.result("pg14").run();
    model.result("pg14").feature("surf1").feature("hght1").set("heightdata", "expr");
    model.result("pg14").feature("surf1").feature("hght1").set("expr", "semi.log10P");
    model.result("pg14").run();
    model.result("pg14").feature("surf1").create("filt1", "Filter");
    model.result("pg14").run();
    model.result("pg14").feature("surf1").feature("filt1").set("expr", "semi.log10P>11 && semi.log10P<16");
    model.result("pg14").run();
    model.result("pg14").create("con1", "Contour");
    model.result("pg14").feature("con1").set("expr", "x");
    model.result("pg14").feature("con1").set("number", 10);
    model.result("pg14").feature("con1").set("coloring", "uniform");
    model.result("pg14").feature("con1").set("color", "gray");
    model.result("pg14").feature("con1").set("colorlegend", false);
    model.result("pg14").run();
    model.result("pg14").feature("surf1").feature("hght1").set("scaleactive", true);
    model.result("pg14").run();
    model.result("pg14").feature("con1").feature().copy("hght1", "pg14/surf1/hght1");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result("pg14").run();
    model.result("pg14").feature("con1").feature().copy("filt1", "pg14/surf1/filt1");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result("pg14").feature().duplicate("con2", "con1");
    model.result("pg14").run();
    model.result("pg14").feature("con2").set("expr", "t");
    model.result("pg14").feature("con2").set("unit", "ns");
    model.result("pg14").feature("con2").set("levelmethod", "levels");
    model.result("pg14").feature("con2").set("levels", "0.1 0.2 0.5 1 2 3 4 5 8");
    model.result("pg14").feature("con2").set("coloring", "gradient");
    model.result("pg14").feature("con2").set("topcolor", "cyan");
    model.result("pg14").feature("con2").set("bottomcolor", "magenta");
    model.result("pg14").feature("con2").set("colorlegend", true);
    model.result("pg14").run();

    model.title("PIN \u4e8c\u6781\u7ba1\u4e2d\u7684\u8f90\u5c04\u6548\u5e94");

    model
         .description("\u672c\u6559\u7a0b\u5bf9 PIN \u4e8c\u6781\u7ba1\u5728\u6052\u5b9a\u8f90\u5c04\u548c\u8109\u51b2\u8f90\u5c04\u4e0b\u7684\u7a33\u6001\u548c\u77ac\u6001\u54cd\u5e94\u8fdb\u884c\u5206\u6790\u3002\u5176\u4e2d\u5c06\u8f90\u5c04\u6548\u5e94\u5efa\u6a21\u4e3a\u5728\u5668\u4ef6\u5185\u90e8\u7a7a\u95f4\u5747\u5300\u5730\u4ea7\u751f\u7535\u5b50-\u7a7a\u7a74\u5bf9\u3002\u5728\u9ad8\u5242\u91cf\u7387\u4e0b\uff0c\u6240\u4ea7\u751f\u7535\u8377\u7684\u5206\u79bb\u4f1a\u5bfc\u81f4\u5185\u90e8\u7535\u573a\u51cf\u5f31\uff0c\u5e76\u5ef6\u957f\u8fc7\u5269\u8f7d\u6d41\u5b50\u7684\u5b58\u50a8\u65f6\u95f4\u3002\u7531\u4e8e\u65e0\u6cd5\u83b7\u5f97\u89e3\u6790\u89e3\uff0c\u53ea\u80fd\u901a\u8fc7\u6570\u503c\u4eff\u771f\u5bf9\u8fd9\u79cd\u73b0\u8c61\u8fdb\u884c\u5b9a\u91cf\u9884\u6d4b\u3002\u672c\u4f8b\u6f14\u793a\u4e86\u5728\u9ad8\u53cd\u5411\u504f\u7f6e\u3001\u573a\u76f8\u5173\u8fc1\u79fb\u7387\u4ee5\u53ca\u77ac\u6001\u7814\u7a76\u60c5\u51b5\u4e0b\u5b9e\u73b0\u6536\u655b\u7684\u51e0\u79cd\u6280\u672f\u3002\u8ba1\u7b97\u5f97\u5230\u7684\u8f7d\u6d41\u5b50\u6d53\u5ea6\u548c\u7535\u573a\u5206\u5e03\u4e0e\u53c2\u8003\u6587\u732e\u4e2d\u7684\u7ed3\u679c\u9ad8\u5ea6\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();

    model.label("pin_radiation_effects.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
