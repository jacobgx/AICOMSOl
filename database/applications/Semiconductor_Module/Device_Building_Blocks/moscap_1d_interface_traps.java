/*
 * moscap_1d_interface_traps.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:50 by COMSOL 6.3.0.290. */
public class moscap_1d_interface_traps {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Device_Building_Blocks");

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

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("t_epi", "10[um]", "\u5916\u5ef6\u5c42\u539a\u5ea6");
    model.param().set("t_sub", "2[um]", "\u57fa\u677f\u539a\u5ea6");
    model.param().set("d_g", "3.8e-2[cm]", "\u6805\u76f4\u5f84");
    model.param().set("area_g", "pi*(d_g/2)^2", "\u6805\u9762\u79ef");
    model.param().set("r_epi", "0.75[ohm*cm]", "\u5916\u5ef6\u5c42\u7535\u963b\u7387");
    model.param().set("r_sub", "0.005[ohm*cm]", "\u57fa\u677f\u7535\u963b\u7387");
    model.param().set("Nd_epi", "1/(e_const*1450[cm^2/V/s]*r_epi)", "\u5916\u5ef6\u5c42\u63ba\u6742\u6d53\u5ea6");
    model.param().set("Nd_sub", "1/(e_const*1450[cm^2/V/s]*r_sub)", "\u57fa\u677f\u63ba\u6742\u6d53\u5ea6");
    model.param().set("epsr_ox", "3.9", "\u6c27\u5316\u7269\u4ecb\u7535\u5e38\u6570");
    model.param().set("t_ox", "60[nm]", "\u6c27\u5316\u5c42\u539a\u5ea6");
    model.param().set("C_ox", "epsr_ox*epsilon0_const/t_ox*area_g", "\u6c27\u5316\u5c42\u7535\u5bb9");
    model.param().set("rhos_ox", "e_const*9e11[cm^-2]", "\u6c27\u5316\u5c42\u56fa\u5b9a\u7535\u8377");
    model.param().set("Nss", "2e11[cm^-2*eV^-1]", "\u8868\u9762\u72b6\u6001\u5bc6\u5ea6");
    model.param().set("Ew0", "0.2[V]", "\u8fde\u7eed\u9677\u9631\u80fd\u7ea7\u5bbd\u5ea6");
    model.param().set("f0", "50[Hz]", "\u4ea4\u6d41\u9891\u7387");
    model.param().set("phiM", "4.5[V]", "\u91d1\u5c5e\u529f\u51fd\u6570");
    model.param().set("v_th", "1e7[cm/s]", "\u70ed\u901f\u5ea6");
    model.param().set("sigma_n", "1e-15[cm^2]", "\u7535\u5b50\u6355\u83b7\u622a\u9762\u79ef");
    model.param().set("sigma_p", "2.2e-16[cm^2]", "\u7a7a\u7a74\u6355\u83b7\u622a\u9762\u79ef");
    model.param().set("Vg", "0[V]", "\u6805\u6781\u7535\u538b");
    model.param().set("Vac", "1[mV]", "\u4ea4\u6d41\u7535\u538b");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("Ym", "semi.iomega*lindev(semi.Q0_2)/Vac", "\u5bfc\u7eb3\u6d4b\u91cf\u503c");
    model.component("comp1").variable("var1").set("Cm", "real(Ym/semi.iomega)", "\u7535\u5bb9\u6d4b\u91cf\u503c");
    model.component("comp1").variable("var1").set("Gm", "real(Ym)", "\u7535\u5bfc\u7387\u6d4b\u91cf\u503c");
    model.component("comp1").variable("var1")
         .set("Yp", "1/(1/Ym-1/(semi.iomega*C_ox))", "\u7b49\u6548\u5e76\u8054\u5bfc\u7eb3");
    model.component("comp1").variable("var1")
         .set("Cp", "real(Yp/semi.iomega)", "\u7b49\u6548\u5e76\u8054\u7535\u5bb9");
    model.component("comp1").variable("var1").set("Gp", "real(Yp)", "\u7b49\u6548\u5e76\u8054\u7535\u5bfc\u7387");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "t_epi", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "t_epi+t_sub", 2);
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

    model.component("comp1").physics("semi").prop("d").set("A", "area_g");
    model.component("comp1").physics("semi").prop("ShapeProperty").set("Formulation", "FEM2Ef");
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 1);
    model.component("comp1").physics("semi").feature("adm1").selection().set(2);
    model.component("comp1").physics("semi").feature("adm1").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm1").set("NDc", "Nd_sub");
    model.component("comp1").physics("semi").create("adm2", "AnalyticDopingModel", 1);
    model.component("comp1").physics("semi").feature("adm2").selection().set(1);
    model.component("comp1").physics("semi").feature("adm2").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm2").set("NDc", "Nd_epi");
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 0);
    model.component("comp1").physics("semi").feature("mc1").selection().set(3);
    model.component("comp1").physics("semi").create("gc1", "GateContact", 0);
    model.component("comp1").physics("semi").feature("gc1").selection().set(1);
    model.component("comp1").physics("semi").feature("gc1").set("V0", "Vg");
    model.component("comp1").physics("semi").feature("gc1").set("epsilon_ins", "epsr_ox");
    model.component("comp1").physics("semi").feature("gc1").set("d_ins", "t_ox");
    model.component("comp1").physics("semi").feature("gc1").set("Phi", "phiM");
    model.component("comp1").physics("semi").feature("gc1").create("hp1", "HarmonicPerturbation", 0);
    model.component("comp1").physics("semi").feature("gc1").feature("hp1").set("V0", "Vac");
    model.component("comp1").physics("semi").create("sfcd1", "SurfaceChargeDensity", 0);
    model.component("comp1").physics("semi").feature("sfcd1").selection().set(1);
    model.component("comp1").physics("semi").feature("sfcd1").set("rhoqs", "rhos_ox");
    model.component("comp1").physics("semi").create("tasr1", "TrapAssistedSurfaceRecombination", 0);
    model.component("comp1").physics("semi").feature("tasr1").selection().set(1);
    model.component("comp1").physics("semi").feature("tasr1").set("IncludeTraps", "ExplicitTraps");
    model.component("comp1").physics("semi").feature("tasr1")
         .set("SpecifyDiscreteContinuous", "SpecifyContinuousAndOrDiscreteLevels");
    model.component("comp1").physics("semi").feature("tasr1").create("ctb1", "ContinuousEnergyLevelsBoundary", 0);
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1").set("Nt_b", "Nss*Ew0*e_const");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1")
         .set("TrapDensityDistribution", "Rectangle");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1").set("Ewidth", "Ew0");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1").set("Etran", "Ew0/100");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1")
         .set("Vxd_min_in", "semi.tasr1.ctb1.Et0-Ew0/2");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1")
         .set("Vxd_max_in", "semi.tasr1.ctb1.Et0+Ew0/2");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1").set("sigman", "sigma_n");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1").set("vn_th", "v_th");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1").set("vp_th", "v_th");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1").set("sigmap", "sigma_p");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 100);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("numelem", 20);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - Vg \u626b\u63cf\u9891\u7387\u4e3a 50 Hz");
    model.study("std1").feature("semie").set("useparam", true);
    model.study("std1").feature("semie").setIndex("pname", "t_epi", 0);
    model.study("std1").feature("semie").setIndex("plistarr", "", 0);
    model.study("std1").feature("semie").setIndex("punit", "m", 0);
    model.study("std1").feature("semie").setIndex("pname", "t_epi", 0);
    model.study("std1").feature("semie").setIndex("plistarr", "", 0);
    model.study("std1").feature("semie").setIndex("punit", "m", 0);
    model.study("std1").feature("semie").setIndex("pname", "Vg", 0);
    model.study("std1").feature("semie")
         .setIndex("plistarr", "range(1,-0.2, -2) range(-2.1,-0.05,-4) range(-4.2,-0.2,-5)", 0);
    model.study("std1").create("frlin", "Frequencylinearized");
    model.study("std1").feature("frlin").set("plist", "f0");
    model.study("std1").feature("frlin").set("useparam", true);
    model.study("std1").feature("frlin").setIndex("pname_aux", "t_epi", 0);
    model.study("std1").feature("frlin").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("frlin").setIndex("punit_aux", "m", 0);
    model.study("std1").feature("frlin").setIndex("pname_aux", "t_epi", 0);
    model.study("std1").feature("frlin").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("frlin").setIndex("punit_aux", "m", 0);
    model.study("std1").feature("frlin").setIndex("pname_aux", "Vg", 0);
    model.study("std1").feature("frlin")
         .setIndex("plistarr_aux", "range(1,-0.2, -2) range(-2.1,-0.05,-4) range(-4.2,-0.2,-5)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u80fd\u7ea7 (semi)");
    model.result("pg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "Energy Diagram");
    model.result("pg1").set("ylabel", "Energy (eV)");
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").label("\u5bfc\u5e26\u80fd\u7ea7");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("expr", "semi.Ec_e");
    model.result("pg1").feature("lngr1").set("unit", "eV");
    model.result("pg1").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr1").set("linecolor", "blue");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").set("legends", new String[]{"Ec"});
    model.result("pg1").feature("lngr1").set("resolution", "norefine");
    model.result("pg1").feature("lngr1").set("smooth", "everywhere");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1, 2);
    model.result("pg1").feature().create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").label("\u7535\u5b50\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg1").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr2").set("expr", "semi.Efn_e");
    model.result("pg1").feature("lngr2").set("unit", "eV");
    model.result("pg1").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr2").set("linestyle", "dashed");
    model.result("pg1").feature("lngr2").set("linecolor", "black");
    model.result("pg1").feature("lngr2").set("legend", true);
    model.result("pg1").feature("lngr2").set("legendmethod", "manual");
    model.result("pg1").feature("lngr2").set("legends", new String[]{"Efn"});
    model.result("pg1").feature("lngr2").set("resolution", "norefine");
    model.result("pg1").feature("lngr2").set("smooth", "everywhere");
    model.result("pg1").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr2").set("data", "parent");
    model.result("pg1").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr2").selection().set(1, 2);
    model.result("pg1").feature().create("lngr3", "LineGraph");
    model.result("pg1").feature("lngr3").label("\u7a7a\u7a74\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg1").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr3").set("expr", "semi.Efp_e");
    model.result("pg1").feature("lngr3").set("unit", "eV");
    model.result("pg1").feature("lngr3").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr3").set("linestyle", "dotted");
    model.result("pg1").feature("lngr3").set("linecolor", "black");
    model.result("pg1").feature("lngr3").set("legend", true);
    model.result("pg1").feature("lngr3").set("legendmethod", "manual");
    model.result("pg1").feature("lngr3").set("legends", new String[]{"Efp"});
    model.result("pg1").feature("lngr3").set("resolution", "norefine");
    model.result("pg1").feature("lngr3").set("smooth", "everywhere");
    model.result("pg1").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr3").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr3").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr3").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr3").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr3").set("data", "parent");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr3").selection().set(1, 2);
    model.result("pg1").feature().create("lngr4", "LineGraph");
    model.result("pg1").feature("lngr4").label("\u4ef7\u5e26\u80fd\u7ea7");
    model.result("pg1").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr4").set("expr", "semi.Ev_e");
    model.result("pg1").feature("lngr4").set("unit", "eV");
    model.result("pg1").feature("lngr4").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr4").set("linecolor", "green");
    model.result("pg1").feature("lngr4").set("legend", true);
    model.result("pg1").feature("lngr4").set("legendmethod", "manual");
    model.result("pg1").feature("lngr4").set("legends", new String[]{"Ev"});
    model.result("pg1").feature("lngr4").set("resolution", "norefine");
    model.result("pg1").feature("lngr4").set("smooth", "everywhere");
    model.result("pg1").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr4").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr4").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr4").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr4").set("evalmethodactive", "off");
    model.result("pg1").feature("lngr4").set("data", "parent");
    model.result("pg1").feature("lngr4").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr4").selection().set(1, 2);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u8f7d\u6d41\u5b50\u6d53\u5ea6 (semi)");
    model.result("pg2").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg2").set("ylabel", "Carrier concentration (1/cm^3)");
    model.result("pg2").set("ylog", true);
    model.result("pg2").feature().create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").label("\u7535\u5b50\u6d53\u5ea6");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("expr", "semi.N");
    model.result("pg2").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg2").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").set("legends", new String[]{"electrons"});
    model.result("pg2").feature("lngr1").set("resolution", "norefine");
    model.result("pg2").feature("lngr1").set("smooth", "everywhere");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg2").feature("lngr1").set("data", "parent");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1, 2);
    model.result("pg2").feature().create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").label("\u7a7a\u7a74\u6d53\u5ea6");
    model.result("pg2").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr2").set("expr", "semi.P");
    model.result("pg2").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg2").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("legendmethod", "manual");
    model.result("pg2").feature("lngr2").set("legends", new String[]{"holes"});
    model.result("pg2").feature("lngr2").set("resolution", "norefine");
    model.result("pg2").feature("lngr2").set("smooth", "everywhere");
    model.result("pg2").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg2").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg2").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg2").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg2").feature("lngr2").set("data", "parent");
    model.result("pg2").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr2").selection().set(1, 2);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u7535\u52bf (semi)");
    model.result("pg3").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg3").set("ylabel", "Electric Potential (V)");
    model.result("pg3").feature().create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg3").feature("lngr1").set("expr", "V");
    model.result("pg3").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg3").feature("lngr1").set("resolution", "norefine");
    model.result("pg3").feature("lngr1").set("smooth", "everywhere");
    model.result("pg3").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg3").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg3").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg3").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg3").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg3").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg3").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg3").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg3").feature("lngr1").set("data", "parent");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1, 2);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
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
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("Cm \u548c Gp vs. Vg");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "Cm", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "pF", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "Cm", 0);
    model.result("pg4").run();
    model.result("pg4").create("glob2", "Global");
    model.result("pg4").feature("glob2").set("markerpos", "datapoints");
    model.result("pg4").feature("glob2").set("linewidth", "preference");
    model.result("pg4").feature("glob2").setIndex("expr", "Gp", 0);
    model.result("pg4").feature("glob2").setIndex("unit", "nS", 0);
    model.result("pg4").feature("glob2").setIndex("descr", "Gp", 0);
    model.result("pg4").run();
    model.result("pg4").set("twoyaxes", true);
    model.result("pg4").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg4").set("legendpos", "middleright");
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("plotgroup", "Default");
    model.study("std2").feature("stat").set("ftplistmethod", "manual");
    model.study("std2").feature("stat").set("solnum", "auto");
    model.study("std2").feature("stat").set("notsolnum", "auto");
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std2").create("frlin", "Frequencylinearized");
    model.study("std2").feature("frlin").set("plotgroup", "Default");
    model.study("std2").feature("frlin").set("ftplistmethod", "manual");
    model.study("std2").feature("frlin").set("linpsolnum", "auto");
    model.study("std2").feature("frlin").set("solnum", "auto");
    model.study("std2").feature("frlin").set("notsolnum", "auto");
    model.study("std2").feature("frlin").set("outputmap", new String[]{});
    model.study("std2").feature("frlin").set("ngenAUX", "1");
    model.study("std2").feature("frlin").set("goalngenAUX", "1");
    model.study("std2").feature("frlin").set("ngenAUX", "1");
    model.study("std2").feature("frlin").set("goalngenAUX", "1");
    model.study("std2").feature("frlin").setSolveFor("/physics/semi", true);
    model.study("std2").feature().remove("stat");
    model.study("std2").label("\u7814\u7a76 2 - \u626b\u63cf\u9891\u7387\u4e3a -3 V");
    model.study("std2").feature("frlin").set("plist", "f0 * 10^range(-2,0.2,3)");
    model.study("std2").feature("frlin").set("useparam", true);
    model.study("std2").feature("frlin").setIndex("pname_aux", "t_epi", 0);
    model.study("std2").feature("frlin").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("frlin").setIndex("punit_aux", "m", 0);
    model.study("std2").feature("frlin").setIndex("pname_aux", "t_epi", 0);
    model.study("std2").feature("frlin").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("frlin").setIndex("punit_aux", "m", 0);
    model.study("std2").feature("frlin").setIndex("pname_aux", "Vg", 0);
    model.study("std2").feature("frlin").setIndex("plistarr_aux", -3, 0);
    model.study("std2").showAutoSequences("all");
    model.study("std2").feature("frlin").set("uselinpsol", true);
    model.study("std2").feature("frlin").set("linpmethod", "sol");
    model.study("std2").feature("frlin").set("linpstudy", "std1");
    model.study("std2").feature("frlin").set("linpsol", "sol1");
    model.study("std2").feature("frlin").set("linpsoluse", "sol2");
    model.study("std2").feature("frlin").set("linpsolnum", 35);
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").label("\u80fd\u7ea7 (semi) 1");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "Energy Diagram");
    model.result("pg5").set("ylabel", "Energy (eV)");
    model.result("pg5").feature().create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").label("\u5bfc\u5e26\u80fd\u7ea7");
    model.result("pg5").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr1").set("expr", "semi.Ec_e");
    model.result("pg5").feature("lngr1").set("unit", "eV");
    model.result("pg5").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr1").set("linecolor", "blue");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").set("legends", new String[]{"Ec"});
    model.result("pg5").feature("lngr1").set("resolution", "norefine");
    model.result("pg5").feature("lngr1").set("smooth", "everywhere");
    model.result("pg5").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr1").set("data", "parent");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1, 2);
    model.result("pg5").feature().create("lngr2", "LineGraph");
    model.result("pg5").feature("lngr2").label("\u7535\u5b50\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg5").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr2").set("expr", "semi.Efn_e");
    model.result("pg5").feature("lngr2").set("unit", "eV");
    model.result("pg5").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr2").set("linestyle", "dashed");
    model.result("pg5").feature("lngr2").set("linecolor", "black");
    model.result("pg5").feature("lngr2").set("legend", true);
    model.result("pg5").feature("lngr2").set("legendmethod", "manual");
    model.result("pg5").feature("lngr2").set("legends", new String[]{"Efn"});
    model.result("pg5").feature("lngr2").set("resolution", "norefine");
    model.result("pg5").feature("lngr2").set("smooth", "everywhere");
    model.result("pg5").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr2").set("data", "parent");
    model.result("pg5").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr2").selection().set(1, 2);
    model.result("pg5").feature().create("lngr3", "LineGraph");
    model.result("pg5").feature("lngr3").label("\u7a7a\u7a74\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg5").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr3").set("expr", "semi.Efp_e");
    model.result("pg5").feature("lngr3").set("unit", "eV");
    model.result("pg5").feature("lngr3").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr3").set("linestyle", "dotted");
    model.result("pg5").feature("lngr3").set("linecolor", "black");
    model.result("pg5").feature("lngr3").set("legend", true);
    model.result("pg5").feature("lngr3").set("legendmethod", "manual");
    model.result("pg5").feature("lngr3").set("legends", new String[]{"Efp"});
    model.result("pg5").feature("lngr3").set("resolution", "norefine");
    model.result("pg5").feature("lngr3").set("smooth", "everywhere");
    model.result("pg5").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr3").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr3").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr3").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr3").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr3").set("data", "parent");
    model.result("pg5").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr3").selection().set(1, 2);
    model.result("pg5").feature().create("lngr4", "LineGraph");
    model.result("pg5").feature("lngr4").label("\u4ef7\u5e26\u80fd\u7ea7");
    model.result("pg5").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr4").set("expr", "semi.Ev_e");
    model.result("pg5").feature("lngr4").set("unit", "eV");
    model.result("pg5").feature("lngr4").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr4").set("linecolor", "green");
    model.result("pg5").feature("lngr4").set("legend", true);
    model.result("pg5").feature("lngr4").set("legendmethod", "manual");
    model.result("pg5").feature("lngr4").set("legends", new String[]{"Ev"});
    model.result("pg5").feature("lngr4").set("resolution", "norefine");
    model.result("pg5").feature("lngr4").set("smooth", "everywhere");
    model.result("pg5").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr4").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr4").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr4").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr4").set("evalmethodactive", "off");
    model.result("pg5").feature("lngr4").set("data", "parent");
    model.result("pg5").feature("lngr4").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr4").selection().set(1, 2);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").label("\u8f7d\u6d41\u5b50\u6d53\u5ea6 (semi) 1");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg6").set("ylabel", "Carrier concentration (1/cm^3)");
    model.result("pg6").set("ylog", true);
    model.result("pg6").feature().create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").label("\u7535\u5b50\u6d53\u5ea6");
    model.result("pg6").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr1").set("expr", "semi.N");
    model.result("pg6").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg6").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").set("legends", new String[]{"electrons"});
    model.result("pg6").feature("lngr1").set("resolution", "norefine");
    model.result("pg6").feature("lngr1").set("smooth", "everywhere");
    model.result("pg6").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg6").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg6").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg6").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg6").feature("lngr1").set("data", "parent");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1, 2);
    model.result("pg6").feature().create("lngr2", "LineGraph");
    model.result("pg6").feature("lngr2").label("\u7a7a\u7a74\u6d53\u5ea6");
    model.result("pg6").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr2").set("expr", "semi.P");
    model.result("pg6").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg6").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg6").feature("lngr2").set("legend", true);
    model.result("pg6").feature("lngr2").set("legendmethod", "manual");
    model.result("pg6").feature("lngr2").set("legends", new String[]{"holes"});
    model.result("pg6").feature("lngr2").set("resolution", "norefine");
    model.result("pg6").feature("lngr2").set("smooth", "everywhere");
    model.result("pg6").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg6").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg6").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg6").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr2").set("evalmethodactive", "off");
    model.result("pg6").feature("lngr2").set("data", "parent");
    model.result("pg6").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr2").selection().set(1, 2);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").label("\u7535\u52bf (semi) 1");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg7").set("ylabel", "Electric Potential (V)");
    model.result("pg7").feature().create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg7").feature("lngr1").set("expr", "V");
    model.result("pg7").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg7").feature("lngr1").set("resolution", "norefine");
    model.result("pg7").feature("lngr1").set("smooth", "everywhere");
    model.result("pg7").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg7").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg7").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg7").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg7").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg7").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg7").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg7").feature("lngr1").set("evalmethodactive", "off");
    model.result("pg7").feature("lngr1").set("data", "parent");
    model.result("pg7").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg7").feature("lngr1").selection().set(1, 2);
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").create("lngr2", "LineGraph");
    model.result("pg8").feature("lngr1").selection().all();
    model.result("pg8").feature("lngr1").set("xdataexpr", "X");
    model.result("pg8").feature("lngr1").set("expr", "semi.Nnetdop");
    model.result("pg8").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg8").feature("lngr1").set("linecolor", "red");
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").feature("lngr1").set("legendmethod", "manual");
    model.result("pg8").feature("lngr1").set("legends", new String[]{"P \u578b (\u7ea2\u8272)"});
    model.result("pg8").feature("lngr1").feature().create("filt1", "LineGraphFilter");
    model.result("pg8").feature("lngr1").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg8").feature("lngr2").selection().all();
    model.result("pg8").feature("lngr2").set("xdataexpr", "X");
    model.result("pg8").feature("lngr2").set("expr", "semi.Nnetdop");
    model.result("pg8").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg8").feature("lngr2").set("linecolor", "blue");
    model.result("pg8").feature("lngr2").set("legend", true);
    model.result("pg8").feature("lngr2").set("legendmethod", "manual");
    model.result("pg8").feature("lngr2").set("legends", new String[]{"N \u578b (\u84dd\u8272)"});
    model.result("pg8").feature("lngr2").feature().create("filt1", "LineGraphFilter");
    model.result("pg8").feature("lngr2").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg8").set("titletype", "none");
    model.result("pg8").set("legendpos", "uppermiddle");
    model.result("pg8")
         .set("ylabel", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert, 1/cm<sup>3</sup>");
    model.result("pg8").set("ylog", true);
    model.result("pg8").feature("lngr1").label("P \u578b");
    model.result("pg8").feature("lngr2").label("N \u578b");
    model.result("pg8").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi)");
    model.result("pg5").run();
    model.result("pg8").run();
    model.result().remove("pg8");
    model.result("pg4").run();
    model.result().duplicate("pg8", "pg4");
    model.result("pg8").run();
    model.result("pg8").label("Cm \u548c Gp vs. Freq");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").set("legendpos", "upperright");
    model.result("pg8").run();
    model.result("pg8").feature("glob2").setIndex("expr", "Gp/(2*pi*freq)", 0);
    model.result("pg8").feature("glob2").setIndex("unit", "pF", 0);
    model.result("pg8").feature("glob2").setIndex("descr", "Gp/\\omega", 0);
    model.result("pg8").feature("glob2").set("xdatasolnumtype", "level2");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg8").set("xlog", true);
    model.result("pg8").run();
    model.result().dataset().duplicate("dset4", "dset1");
    model.result().dataset("dset4").label("\u7814\u7a76 1 - Vg \u626b\u63cf\u9891\u7387\u4e3a 50 Hz/\u89e3 1 XD");
    model.result().dataset("dset4").set("comp", "semi_tasr1_ctb1_xdim");
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u9677\u9631\u6001\u5bc6\u5ea6 vs. \u80fd\u91cf");
    model.result("pg9").set("data", "dset4");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg9").feature("lngr1").set("linewidth", "preference");
    model.result("pg9").feature("lngr1").selection().all();
    model.result("pg9").feature("lngr1").set("expr", "atxd0(0[um],(semi.tasr1.ctb1.gt/(e_const*Ew0)))");
    model.result("pg9").feature("lngr1").set("unit", "1/(cm^2*eV)");
    model.result("pg9").feature("lngr1").set("descractive", true);
    model.result("pg9").feature("lngr1").set("descr", "\u9677\u9631\u6001\u5bc6\u5ea6");
    model.result("pg9").feature("lngr1").set("evalmethod", "linpoint");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1")
         .set("xdataexpr", "atxd0(0[um],semi.tasr1.ctb1.Vxd-semi.tasr1.ctb1.Et0)*e_const");
    model.result("pg9").feature("lngr1").set("xdataunit", "eV");
    model.result("pg9").feature("lngr1").set("xdatadescractive", true);
    model.result("pg9").feature("lngr1").set("xdatadescr", "\u80fd\u91cf");
    model.result("pg9").feature("lngr1").set("xdataevalmethod", "linpoint");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u9677\u9631\u5360\u6709\u7387\uff0c\u7a33\u6001");
    model.result("pg10").setIndex("looplevelinput", "manual", 0);
    model.result("pg10").setIndex("looplevel", new int[]{1, 35}, 0);
    model.result("pg10").set("legendpos", "lowerleft");
    model.result("pg10").run();
    model.result("pg10").feature("lngr1").set("expr", "atxd0(0[um],(semi.tasr1.ctb1.ft))");
    model.result("pg10").feature("lngr1").set("descr", "\u9677\u9631\u5360\u6709\u7387\uff0c\u7a33\u6001");
    model.result("pg10").feature("lngr1").set("legend", true);
    model.result("pg10").run();
    model.result("pg10").feature("lngr1").set("resolution", "norefine");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").label("\u9677\u9631\u5360\u6709\u7387\uff0c\u5c0f\u4fe1\u53f7\u54cd\u5e94");
    model.result("pg11").set("titletype", "manual");
    model.result("pg11")
         .set("title", "\u9677\u9631\u5360\u6709\u7387\uff0c\u5c0f\u4fe1\u53f7\uff0c\u5b9e\u90e8\u548c\u865a\u90e8");
    model.result("pg11").set("legendpos", "upperleft");
    model.result("pg11").run();

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg11").feature("lngr1")
         .set("descr", "\u9677\u9631\u5360\u6709\u7387\uff0c\u5c0f\u4fe1\u53f7\u54cd\u5e94");
    model.result("pg11").feature("lngr1").set("evalmethod", "harmonic");
    model.result("pg11").feature("lngr1").set("differential", true);
    model.result("pg11").feature().duplicate("lngr2", "lngr1");
    model.result("pg11").run();
    model.result("pg11").feature("lngr2").set("expr", "atxd0(0[um],imag(semi.tasr1.ctb1.ft))");
    model.result("pg11").feature("lngr2").set("linestyle", "dashed");
    model.result("pg11").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg11").run();

    model.title("MOSCAP \u7684\u754c\u9762\u9677\u9631\u6548\u5e94");

    model
         .description("\u672c\u6559\u7a0b\u5c06\u6587\u732e\u4e2d\u7684\u5b9e\u9a8c\u6570\u636e\u4e0e COMSOL \u7684\u5305\u542b\u754c\u9762\u9677\u9631\uff08\u8868\u9762\u72b6\u6001\uff09\u7684 MOSCAP \u6a21\u578b\u5206\u6790\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\uff0c\u5176\u4e2d\u4f7f\u7528\u201c\u9677\u9631\u8f85\u52a9\u8868\u9762\u590d\u5408\u201d\u7279\u5f81\u6a21\u62df\u9677\u9631\u7535\u8377\u7684\u5f71\u54cd\u4ee5\u53ca\u9677\u9631\u4fd8\u83b7\u548c\u53d1\u5c04\u8f7d\u6d41\u5b50\u7684\u8fc7\u7a0b\u3002\u6b64\u5916\uff0c\u8fd8\u5206\u6790\u4e86\u6805\u6c27\u5316\u5c42\u4e2d\u56fa\u5b9a\u7535\u8377\u7684\u5f71\u54cd\u3002\u672c\u4f8b\u5c06\u7535\u5bb9\u548c\u7535\u5bfc\u4f5c\u4e3a\u6805\u6781\u7535\u538b\u548c\u9891\u7387\u7684\u51fd\u6570\u8fdb\u884c\u8ba1\u7b97\uff0c\u5f97\u5230\u7684\u503c\u53ef\u4ee5\u91cd\u73b0\u91c7\u7528\u540c\u7b49\u5927\u5c0f\u7684\u5b9e\u9a8c\u6570\u636e\u7684\u5b9a\u6027\u884c\u4e3a\u3002\u8be5\u6a21\u578b\u4f7f\u7528\u51c6\u8d39\u7c73\u80fd\u7ea7\u516c\u5f0f\uff0c\u6f14\u793a\u5982\u4f55\u7ed8\u5236\u9677\u9631\u5360\u6709\u7387\u7b49\u7269\u7406\u91cf\u968f\u80fd\u91cf\u7684\u53d8\u5316\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("moscap_1d_interface_traps.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
