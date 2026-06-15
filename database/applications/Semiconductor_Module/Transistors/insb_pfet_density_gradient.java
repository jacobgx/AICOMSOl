/*
 * insb_pfet_density_gradient.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:55 by COMSOL 6.3.0.290. */
public class insb_pfet_density_gradient {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Transistors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

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

    model.component("comp1").geom("geom1").lengthUnit("nm");

    model.param().label("\u53c2\u6570 1\uff1a\u51e0\u4f55");
    model.param().set("Lgate", "40[nm]");
    model.param().descr("Lgate", "\u6805\u6781\u957f\u5ea6");
    model.param().set("Lgate2cap", "135[nm]");
    model.param().descr("Lgate2cap", "\u6805\u6781\u5230\u5e3d\u7684\u957f\u5ea6");
    model.param().set("Lcap", "100[nm]");
    model.param().descr("Lcap", "\u5e3d\u957f\u5ea6");
    model.param().set("Hcap", "16[nm]");
    model.param().descr("Hcap", "\u5e3d\u9ad8\u5ea6");
    model.param().set("Htopbar", "10[nm]");
    model.param().descr("Htopbar", "\u9876\u90e8\u52bf\u5792\u9ad8\u5ea6");
    model.param().set("Hwell", "5[nm]");
    model.param().descr("Hwell", "\u9631\u9ad8\u5ea6");
    model.param().set("Hbotbar", "150[nm]");
    model.param().descr("Hbotbar", "\u5e95\u90e8\u52bf\u5792\u9ad8\u5ea6");
    model.param().set("Hstack", "Htopbar+Hwell+Hbotbar");
    model.param().descr("Hstack", "\u603b\u9ad8\u5ea6");
    model.param().set("Ltot", "Lgate+2*(Lgate2cap+Lcap)");
    model.param().descr("Ltot", "\u603b\u957f\u5ea6");
    model.param().set("d0", "1[um]");
    model.param().descr("d0", "\u9762\u5916\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Ltot", "Hstack"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-Ltot/2", "-Hstack"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "Hbotbar", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "Hwell", 1);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"Ltot", "Hcap"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-Ltot/2", "0"});
    model.component("comp1").geom("geom1").feature("r2").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r2").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r2").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "Lcap", 0);
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "Lgate2cap", 1);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("r2", 2, 4);

    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2\uff1a\u6750\u6599");
    model.param("par2").set("cp", "1");
    model.param("par2").descr("cp", "\u5f02\u8d28\u7ed3\u548c\u63ba\u6742\u7684\u8fde\u7eed\u53c2\u6570");
    model.param("par2").set("EgW0", "0.17[V]");
    model.param("par2").descr("EgW0", "\u5e26\u9699\uff0c\u9631");
    model.param("par2").set("EgB", "0.78[V]");
    model.param("par2").descr("EgB", "\u5e26\u9699\uff0c\u52bf\u5792");
    model.param("par2").set("deltaEv", "0.21[V]");
    model.param("par2").descr("deltaEv", "\u4ef7\u5e26\u504f\u79fb\u91cf");
    model.param("par2").set("chiW0", "4.59[V]");
    model.param("par2").descr("chiW0", "\u7535\u5b50\u4eb2\u548c\u80fd\uff0c\u9631");
    model.param("par2").set("chiB", "chiW0+EgW0+deltaEv-EgB");
    model.param("par2").descr("chiB", "\u7535\u5b50\u4eb2\u548c\u80fd\uff0c\u52bf\u5792");
    model.param("par2").set("EgW", "EgW0*cp+EgB*(1-cp)");
    model.param("par2").descr("EgW", "\u5e26\u9699\uff0c\u9631");
    model.param("par2").set("chiW", "chiW0*cp+chiB*(1-cp)");
    model.param("par2").descr("chiW", "\u7535\u5b50\u4eb2\u548c\u80fd\uff0c\u9631");
    model.param("par2").set("epsrW", "17.7");
    model.param("par2").descr("epsrW", "\u4ecb\u7535\u5e38\u6570\uff0c\u9631");
    model.param("par2").set("epsrB", "15.7");
    model.param("par2").descr("epsrB", "\u4ecb\u7535\u5e38\u6570\uff0c\u52bf\u5792");
    model.param("par2").set("PhiB", "0.4[V]");
    model.param("par2").descr("PhiB", "\u8096\u7279\u57fa\u52bf\u5792");
    model.param("par2").set("Nv0", "7.3e18[cm^-3]");
    model.param("par2").descr("Nv0", "\u6709\u6548\u4ef7\u5e26\u6001\u5bc6\u5ea6");
    model.param("par2").set("Nc0", "4.2e16[cm^-3]");
    model.param("par2").descr("Nc0", "\u6709\u6548\u5bfc\u5e26\u6001\u5bc6\u5ea6");
    model.param("par2").set("muLFW", "1230[cm^2/V/s]");
    model.param("par2").descr("muLFW", "LF \u7a7a\u7a74\u8fc1\u79fb\u7387\uff0c\u9631");
    model.param("par2").set("muLFB", "50[cm^2/V/s]");
    model.param("par2").descr("muLFB", "LF \u7a7a\u7a74\u8fc1\u79fb\u7387\uff0c\u52bf\u5792");
    model.param("par2").set("vSat", "8e6[cm/s]");
    model.param("par2").descr("vSat", "\u9971\u548c\u901f\u5ea6");
    model.param("par2").set("muSS", "100[cm^2/V/s]");
    model.param("par2").descr("muSS", "SS \u7a7a\u7a74\u8fc1\u79fb\u7387\uff0c\u6240\u6709\u6750\u6599");
    model.param("par2").set("ESS", "3e5[V/cm]");
    model.param("par2").descr("ESS", "SS \u7535\u573a\uff0c\u6240\u6709\u6750\u6599");
    model.param("par2").set("mun0", "7.7e4[cm^2/V/s]");
    model.param("par2").descr("mun0", "\u7535\u5b50\u8fc1\u79fb\u7387");
    model.param("par2").set("mpDGperp", "0.04*me_const");
    model.param("par2").descr("mpDGperp", "DG \u6709\u6548\u8d28\u91cf\uff0c\u5782\u76f4");
    model.param("par2").set("mpDGpara", "0.053*me_const");
    model.param("par2").descr("mpDGpara", "DG \u6709\u6548\u8d28\u91cf\uff0c\u5e73\u884c");

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

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material("mat1").label("InSb \u9631");
    model.component("comp1").material("mat1").selection().set(2);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u9631\u57df");
    model.component("comp1").selection("sel1").set(2);

    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"epsrW"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Eg0", new String[]{"EgW"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("chi0", new String[]{"chiW"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Nv", new String[]{"Nv0"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Nc", new String[]{"Nc0"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mun", new String[]{"mun0"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mup", new String[]{"muLFW"});
    model.component("comp1").material().duplicate("mat2", "mat1");
    model.component("comp1").material("mat2").label("AlInSb \u52bf\u5792");
    model.component("comp1").material("mat2").selection().set(1, 3, 4, 6);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u52bf\u5792\u57df");
    model.component("comp1").selection("sel2").set(1, 3, 4, 6);

    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"epsrB"});
    model.component("comp1").material("mat2").propertyGroup("SemicondMaterial").set("Eg0", new String[]{"EgB"});
    model.component("comp1").material("mat2").propertyGroup("SemicondMaterial").set("chi0", new String[]{"chiB"});
    model.component("comp1").material("mat2").propertyGroup("SemicondMaterial").set("mup", new String[]{"muLFB"});

    model.component("comp1").physics("semi").selection().set(1, 2, 3, 4, 6);

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").label("\u534a\u5bfc\u4f53\u57df");
    model.component("comp1").selection("sel3").set(1, 2, 3, 4, 6);

    model.component("comp1").physics("semi").selection().named("sel3");
    model.component("comp1").physics("semi").prop("d").set("d", "d0");
    model.component("comp1").physics("semi").prop("ModelProperties").set("CarrierStatistics", "FermiDirac");
    model.component("comp1").physics("semi").prop("ModelProperties").set("Compute", "MajorityPsi");
    model.component("comp1").physics("semi").prop("ModelProperties").set("MajorityCarriers", "Holes");
    model.component("comp1").physics("semi").prop("ShapeProperty").set("Formulation", "FEM2DG");
    model.component("comp1").physics("semi").feature("smm1").set("minput_temperature", "300[K]");
    model.component("comp1").physics("semi").feature("smm1")
         .set("mhDG", new String[]{"mpDGpara", "0", "0", "0", "mpDGperp", "0", "0", "0", "me_const"});
    model.component("comp1").physics("semi").feature("smm1").set("mup_mat", "userdef");
    model.component("comp1").physics("semi").feature("smm1").set("mup", "muptot");
    model.component("comp1").physics("semi").feature("smm1").create("mmct1", "CaugheyThomasMobilityModel", 2);

    model.param().create("par3");
    model.param("par3").label("\u53c2\u6570 3\uff1a\u7269\u7406\u573a");
    model.param("par3").set("NaCap", "5e20[cm^-3]");
    model.param("par3").descr("NaCap", "p+ \u5e3d\u63ba\u6742\u6d53\u5ea6");
    model.param("par3").set("NaDelta", "1e19[cm^-3]");
    model.param("par3").descr("NaDelta", "Delta \u63ba\u6742\u6d53\u5ea6");
    model.param("par3").set("Vd", "0[V]");
    model.param("par3").descr("Vd", "\u6f0f\u6781\u7535\u538b");
    model.param("par3").set("Vg", "0[V]");
    model.param("par3").descr("Vg", "\u6805\u6781\u7535\u538b");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .label("\u53d8\u91cf 1\uff1a\u4f4e\u573a\u8fc1\u79fb\u7387\uff0c\u9631");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("sel1");
    model.component("comp1").variable("var1").set("mupLF", "muLFW");
    model.component("comp1").variable("var1").descr("mupLF", "\u4f4e\u573a\u8fc1\u79fb\u7387");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2")
         .label("\u53d8\u91cf 2\uff1a\u4f4e\u573a\u8fc1\u79fb\u7387\uff0c\u52bf\u5792");
    model.component("comp1").variable("var2").selection().named("sel2");
    model.component("comp1").variable("var2").set("mupLF", "muLFB");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u53d8\u91cf 3\uff1a\u8fc1\u79fb\u7387\u6a21\u578b");
    model.component("comp1").variable("var3").set("mupFDinv", "sqrt(1+(mupLF*semi.smm1.mmct1.Epp/vSat)^2)/mupLF");
    model.component("comp1").variable("var3")
         .descr("mupFDinv", "\u573a\u76f8\u5173\u8fc1\u79fb\u7387\u7684\u5012\u6570");
    model.component("comp1").variable("var3").set("mupSSinv", "(semi.EY/ESS)^4/muSS");
    model.component("comp1").variable("var3")
         .descr("mupSSinv", "\u8868\u9762\u6563\u5c04\u8fc1\u79fb\u7387\u7684\u5012\u6570");
    model.component("comp1").variable("var3").set("muptot", "1/(mupFDinv+mupSSinv)");
    model.component("comp1").variable("var3").descr("muptot", "\u603b\u8fc1\u79fb\u7387");

    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm1")
         .label("\u89e3\u6790\u63ba\u6742\u6a21\u578b 1\uff1ap+ \u5e3d");
    model.component("comp1").physics("semi").feature("adm1").selection().set(4, 6);
    model.component("comp1").physics("semi").feature("adm1").set("NAc", "NaCap*cp");
    model.component("comp1").physics("semi").create("adm2", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm2")
         .label("\u89e3\u6790\u63ba\u6742\u6a21\u578b 2\uff1aDelta \u63ba\u6742");
    model.component("comp1").physics("semi").feature("adm2").selection().set(3);
    model.component("comp1").physics("semi").feature("adm2").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm2").set("NAc", "NaDelta*cp");
    model.component("comp1").physics("semi").feature("adm2").set("rb", new String[]{"-Ltot/2", "-0.4*Htopbar", "0"});
    model.component("comp1").physics("semi").feature("adm2").set("jwidth", "Ltot");
    model.component("comp1").physics("semi").feature("adm2").set("jdepth", "0.2*Htopbar");
    model.component("comp1").physics("semi").feature("adm2").set("JunctionOrLength", "decay_length");
    model.component("comp1").physics("semi").feature("adm2").set("ls", "0.02*Htopbar");
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc1").label("\u91d1\u5c5e\u63a5\u89e6 1\uff1a\u6e90\u6781");
    model.component("comp1").physics("semi").feature("mc1").selection().set(9);
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc2").label("\u91d1\u5c5e\u63a5\u89e6 2\uff1a\u6f0f\u6781");
    model.component("comp1").physics("semi").feature("mc2").selection().set(19);
    model.component("comp1").physics("semi").feature("mc2").set("V0", "Vd");
    model.component("comp1").physics("semi").create("mc3", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc3").label("\u91d1\u5c5e\u63a5\u89e6 3\uff1a\u6805\u6781");
    model.component("comp1").physics("semi").feature("mc3").selection().set(13);
    model.component("comp1").physics("semi").feature("mc3").set("V0", "Vg");
    model.component("comp1").physics("semi").feature("mc3").set("ContactType", "Schottky");
    model.component("comp1").physics("semi").feature("mc3").set("SpecifyBarrierHeight", "userdef");
    model.component("comp1").physics("semi").feature("mc3").set("Phi_B", "PhiB");
    model.component("comp1").physics("semi").feature("mc3").set("Astar_n", "0[A/(K*cm)^2]");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("semi").create("ins2", "Insulation", 1);
    model.component("comp1").physics("semi").feature("ins2").selection().set(11, 16);
    model.component("comp1").physics("semi").feature("ins2").set("DGexteriorBC", "barrier");
    model.component("comp1").physics("semi").feature("ins2").set("Phi_pOx", "1000[V]");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(11, 16);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature().duplicate("edg2", "edg1");
    model.component("comp1").mesh("mesh1").feature("edg2").selection().set(13);
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("elemratio", 2.5);
    model.component("comp1").mesh("mesh1").feature().duplicate("edg3", "edg2");
    model.component("comp1").mesh("mesh1").feature("edg3").selection().set(8);
    model.component("comp1").mesh("mesh1").feature("edg3").feature("dis1").set("elemcount", 12);
    model.component("comp1").mesh("mesh1").feature("edg3").feature("dis1").set("elemratio", 6);
    model.component("comp1").mesh("mesh1").feature("edg3").feature("dis1").set("symmetric", false);
    model.component("comp1").mesh("mesh1").feature().duplicate("edg4", "edg3");
    model.component("comp1").mesh("mesh1").feature("edg4").selection().set(18);
    model.component("comp1").mesh("mesh1").create("cpe1", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").set(8, 11, 13, 16, 18);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").set(2, 4, 6);
    model.component("comp1").mesh("mesh1").create("cpe2", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("source").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("destination").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("source").set(8, 18);
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("destination").set(9, 19);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(5, 22);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature().duplicate("dis2", "dis1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(3, 21);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature().duplicate("dis3", "dis2");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(7, 10, 17, 23);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("symmetric", false);
    model.component("comp1").mesh("mesh1").feature("map1").feature().duplicate("dis4", "dis3");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(1, 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("elemcount", 25);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("elemratio", 50);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("edg5", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg5").selection().set(12, 14, 15);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u659c\u5761\u63ba\u6742\u548c\u80fd\u5e26\u504f\u79fb");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("semie").set("useadvanceddisable", true);
    model.study("std1").feature("semie").set("disabledphysics", new String[]{"semi/smm1/mmct1"});
    model.study("std1").feature("semie").set("useparam", true);
    model.study("std1").feature("semie").setIndex("pname", "chiB", 0);
    model.study("std1").feature("semie").setIndex("plistarr", "", 0);
    model.study("std1").feature("semie").setIndex("punit", "V", 0);
    model.study("std1").feature("semie").setIndex("pname", "chiB", 0);
    model.study("std1").feature("semie").setIndex("plistarr", "", 0);
    model.study("std1").feature("semie").setIndex("punit", "V", 0);
    model.study("std1").feature("semie").setIndex("pname", "cp", 0);
    model.study("std1").feature("semie").setIndex("plistarr", "10^range(-12,4,0)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("solnum", "last");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "chiB", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("pname", "chiB", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("pname", "Vd", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "0 -0.1 -0.3 -0.5", 0);
    model.study("std2").feature("stat").setIndex("pname", "chiB", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "V", 1);
    model.study("std2").feature("stat").setIndex("pname", "chiB", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "V", 1);
    model.study("std2").feature("stat").setIndex("pname", "Vg", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "0 -0.1 -0.4 -0.6", 1);
    model.study("std2").feature("stat").set("preusesol", "yes");
    model.study("std2")
         .label("\u7814\u7a76 2\uff1a\u659c\u5761 Vd \u548c Vg\uff08\u4ec5\u4f5c\u4e3a\u4e0b\u4e00\u4e2a\u7814\u7a76\u7684\u521d\u59cb\u5316\u6761\u4ef6\uff09");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std3").feature("stat").set("useinitsol", true);
    model.study("std3").feature("stat").set("initmethod", "sol");
    model.study("std3").feature("stat").set("initstudy", "std2");
    model.study("std3").feature("stat").set("solnum", "last");
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").set("sweeptype", "filled");
    model.study("std3").feature("stat").setIndex("pname", "chiB", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "V", 0);
    model.study("std3").feature("stat").setIndex("pname", "chiB", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "V", 0);
    model.study("std3").feature("stat").setIndex("pname", "Vd", 0);
    model.study("std3").feature("stat").setIndex("plistarr", -0.5, 0);
    model.study("std3").feature("stat").setIndex("pname", "chiB", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "", 1);
    model.study("std3").feature("stat").setIndex("punit", "V", 1);
    model.study("std3").feature("stat").setIndex("pname", "chiB", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "", 1);
    model.study("std3").feature("stat").setIndex("punit", "V", 1);
    model.study("std3").feature("stat").setIndex("pname", "Vg", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "range(-0.6,0.1,0.6)", 1);
    model.study("std3").label("\u7814\u7a76 3\uff1aId-Vg \u66f2\u7ebf\u7684 Vg \u626b\u63cf");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u5b50\u6d53\u5ea6 (semi)");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 13, 0);
    model.result("pg1").setIndex("looplevel", 1, 1);
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "semi.N");
    model.result("pg1").feature("surf1").set("unit", "1/cm^3");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg1").feature("surf1").set("resolution", "norefine");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7a7a\u7a74\u6d53\u5ea6 (semi)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 13, 0);
    model.result("pg2").setIndex("looplevel", 1, 1);
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "semi.P");
    model.result("pg2").feature("surf1").set("unit", "1/cm^3");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf1").set("resolution", "norefine");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u52bf (semi)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 13, 0);
    model.result("pg3").setIndex("looplevel", 1, 1);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "V");
    model.result("pg3").feature("surf1").set("resolution", "norefine");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u91cf\u5b50\u52bf\uff0c\u7535\u5b50 (semi)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 13, 0);
    model.result("pg4").setIndex("looplevel", 1, 1);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "semi.VnDG");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u91cf\u5b50\u52bf\uff0c\u7a7a\u7a74 (semi)");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 13, 0);
    model.result("pg5").setIndex("looplevel", 1, 1);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "semi.VpDG");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf2").set("expr", "semi.Nnetdop");
    model.result("pg6").feature("surf2").set("unit", "1/cm^3");
    model.result("pg6").feature("surf2").set("coloring", "gradient");
    model.result("pg6").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg6").feature("surf2").set("topcolor", "red");
    model.result("pg6").feature("surf2").set("bottomcolor", "custom");
    model.result("pg6").feature("surf2").set("custombottomcolor", new double[]{1, 0.8, 0.8});
    model.result("pg6").feature("surf2").set("smooth", "internal");
    model.result("pg6").feature("surf2").set("showsolutionparams", "on");
    model.result("pg6").feature("surf2").set("data", "parent");
    model.result("pg6").feature("surf2").set("titletype", "none");
    model.result("pg6").feature("surf2").feature().create("filt1", "Filter");
    model.result("pg6").feature("surf2").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg6").feature("surf2").feature("filt1").set("useder", true);
    model.result("pg6").feature("surf1").set("expr", "semi.Nnetdop");
    model.result("pg6").feature("surf1").set("unit", "1/cm^3");
    model.result("pg6").feature("surf1").set("coloring", "gradient");
    model.result("pg6").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg6").feature("surf1").set("topcolor", "blue");
    model.result("pg6").feature("surf1").set("bottomcolor", "custom");
    model.result("pg6").feature("surf1").set("custombottomcolor", new double[]{0.8, 0.8, 1});
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").set("titletype", "none");
    model.result("pg6").feature("surf1").feature().create("filt1", "Filter");
    model.result("pg6").feature("surf1").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg6").feature("surf1").feature("filt1").set("useder", true);
    model.result("pg6").set("titletype", "manual");
    model.result("pg6")
         .set("title", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert: P \u578b (\u7ea2\u8272), N \u578b (\u84dd\u8272)");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").set("legendpos", "alternating");
    model.result("pg6").feature("surf2").label("P \u578b");
    model.result("pg6").feature("surf1").label("N \u578b");
    model.result("pg6").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi)");
    model.result("pg1").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature().remove("surf1");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("Id-Vg");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", -0.7);
    model.result("pg7").set("xmax", 0.6);
    model.result("pg7").set("ymin", 0.1);
    model.result("pg7").set("ymax", 500);
    model.result("pg7").set("ylog", true);
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "-semi.I0_2/d0", 0);
    model.result("pg7").feature("glob1").setIndex("unit", "mA/mm", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "\u7535\u6d41", 0);
    model.result("pg7").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 10, 0);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("rangecoloractive", true);
    model.result("pg2").feature("surf1").set("rangecolormin", "10^10");
    model.result("pg2").feature("surf1").set("rangecolormax", "10^21");
    model.result("pg2").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "dset3");
    model.result().dataset("cln1").setIndex("genpoints", "-100[nm]", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "-100[nm]", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "-30[nm]", 1, 1);
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u7a7a\u7a74\u6d53\u5ea6\u622a\u7ebf");
    model.result("pg8").set("data", "cln1");
    model.result("pg8").setIndex("looplevelinput", "manual", 0);
    model.result("pg8").setIndex("looplevel", new int[]{10}, 0);
    model.result("pg8").set("titletype", "manual");
    model.result("pg8")
         .set("title", "\u7a7a\u7a74\u6d53\u5ea6\uff1a\u5bc6\u5ea6\u68af\u5ea6 (DG) \u4e0e\u6f02\u79fb\u6269\u6563 (DD)\u3001\u4ef7\u5e26\u8fb9\u7f18 (Ev) \u548c\u7a7a\u7a74\u51c6\u8d39\u7c73\u80fd\u7ea7 (Efp) \u7684\u5173\u7cfb");
    model.result("pg8").set("twoyaxes", true);
    model.result("pg8").set("yseclabelactive", true);
    model.result("pg8").set("yseclabel", "\u80fd\u7ea7 (eV)");
    model.result("pg8").set("legendpos", "middleleft");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").set("expr", "semi.P");
    model.result("pg8").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "y");
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").feature("lngr1").set("legendmethod", "manual");
    model.result("pg8").feature("lngr1").setIndex("legends", "P (DG)", 0);
    model.result("pg8").feature().duplicate("lngr2", "lngr1");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").set("expr", "semi.Nv*semi.FD_half((semi.Ev-semi.Efp)/semi.Vth)");
    model.result("pg8").feature("lngr2").set("descractive", true);
    model.result("pg8").feature("lngr2").set("descr", "\u7a7a\u7a74\u6d53\u5ea6");
    model.result("pg8").feature("lngr2").set("linestyle", "dotted");
    model.result("pg8").feature("lngr2").set("linecolor", "red");
    model.result("pg8").feature("lngr2").setIndex("legends", "P (DD)", 0);
    model.result("pg8").feature().duplicate("lngr3", "lngr2");
    model.result("pg8").run();
    model.result("pg8").feature("lngr3").set("plotonsecyaxis", true);
    model.result("pg8").feature("lngr3").set("expr", "semi.Ev");
    model.result("pg8").feature("lngr3").setIndex("legends", "Ev", 0);
    model.result("pg8").feature("lngr3").set("descractive", false);
    model.result("pg8").feature("lngr3").set("linestyle", "solid");
    model.result("pg8").feature("lngr3").set("linecolor", "cycle");
    model.result("pg8").feature().duplicate("lngr4", "lngr3");
    model.result("pg8").run();
    model.result("pg8").feature("lngr4").set("expr", "semi.Efp");
    model.result("pg8").feature("lngr4").set("linestyle", "dashed");
    model.result("pg8").feature("lngr4").set("linecolor", "black");
    model.result("pg8").feature("lngr4").setIndex("legends", "Efp", 0);
    model.result("pg8").set("ylog", true);
    model.result("pg8").run();

    model.title("InSb p \u6c9f\u9053\u573a\u6548\u5e94\u6676\u4f53\u7ba1\u7684\u5bc6\u5ea6\u68af\u5ea6\u5206\u6790");

    model
         .description("\u672c\u6559\u7a0b\u5206\u6790 InSb p \u6c9f\u9053\u573a\u6548\u5e94\u6676\u4f53\u7ba1\u7684\u76f4\u6d41\u7279\u6027\uff0c\u5176\u4e2d\u4f7f\u7528\u5bc6\u5ea6\u68af\u5ea6\u7406\u8bba\u5c06\u91cf\u5b50\u9650\u5236\u6548\u5e94\u5f15\u5165\u4f20\u7edf\u7684\u6f02\u79fb-\u6269\u6563\u516c\u5f0f\u4e2d\uff0c\u800c\u65e0\u9700\u5927\u5e45\u589e\u52a0\u8ba1\u7b97\u8d44\u6e90\u3002\u672c\u4f8b\u5bf9\u91cf\u5b50\u9631\u6c9f\u9053\u53ca\u76f8\u90bb\u7684\u9876\u90e8\u7edd\u7f18\u4f53\u754c\u9762\u90fd\u5e94\u7528\u4e86\u8fd9\u79cd\u9650\u5236\u6548\u5e94\uff0c\u6f14\u793a\u4e86\u5982\u4f55\u4f7f\u7528\u5404\u5411\u5f02\u6027\u5bc6\u5ea6\u68af\u5ea6\u6709\u6548\u8d28\u91cf\u77e9\u9635\uff0c\u4ee5\u53ca\u914d\u7f6e\u4e00\u822c\u7684\u573a\u76f8\u5173\u8fc1\u79fb\u7387\u6a21\u578b\u7684\u6280\u5de7\u3002\u901a\u8fc7\u5c06\u57fa\u4e8e\u4e8c\u7ef4\u6a21\u578b\u5f97\u5230\u7684\u7a7a\u7a74\u5bc6\u5ea6\u5206\u5e03\u548c Id-Vg \u66f2\u7ebf\u4e0e\u53c2\u8003\u6587\u732e\u4e2d\u53d1\u8868\u7684\u6570\u636e\u8fdb\u884c\u6bd4\u8f83\uff0c\u7ed3\u679c\u8868\u73b0\u51fa\u9ad8\u5ea6\u7684\u4e00\u81f4\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("insb_pfet_density_gradient.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
