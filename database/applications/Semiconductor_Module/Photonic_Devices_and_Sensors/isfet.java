/*
 * isfet.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:52 by COMSOL 6.3.0.290. */
public class isfet {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Photonic_Devices_and_Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Vd", "10[mV]", "\u6f0f\u6781\u7535\u538b");
    model.param().set("Vga", "0[V]", "\u6805\u6781\u7535\u538b\uff08\u5916\u52a0\uff09");
    model.param().set("pHb", "3", "\u672c\u4f53\u7535\u89e3\u8d28\u7684 pH \u503c");
    model.param().set("T0", "25[degC]", "\u6e29\u5ea6");
    model.param().set("V_therm", "R_const*T0/F_const", "\u70ed\u7535\u538b");
    model.param().set("WAg", "4.6[V]", "Ag \u7684\u529f\u51fd\u6570");
    model.param().set("Eeq", "0.2[V]", "\u53c2\u6bd4\u7535\u6781\u7684\u5e73\u8861\u7535\u4f4d");
    model.param().set("DC", "1e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0c\u9633\u79bb\u5b50");
    model.param().set("DA", "DC", "\u6269\u6563\u7cfb\u6570\uff0c\u9634\u79bb\u5b50");
    model.param().set("DH", "36.3e-4[cm^2/V/s]*V_therm", "\u6269\u6563\u7cfb\u6570\uff0cH+");
    model.param().set("DOH", "20.5e-4[cm^2/V/s]*V_therm", "\u6269\u6563\u7cfb\u6570\uff0cOH-");
    model.param().set("c0", "0.1[M]", "\u6d53\u5ea6\u53c2\u6570");
    model.param().set("cH_bulk", "10^-pHb[M]", "\u672c\u4f53 H+ \u6d53\u5ea6");
    model.param().set("cOH_bulk", "10^-14[M^2]/cH_bulk", "\u672c\u4f53 OH- \u6d53\u5ea6");
    model.param().set("cC_bulk", "c0+cOH_bulk", "\u9633\u79bb\u5b50\u672c\u4f53\u6d53\u5ea6");
    model.param().set("cA_bulk", "c0+cH_bulk", "\u9634\u79bb\u5b50\u672c\u4f53\u6d53\u5ea6");
    model.param().set("zC", "+1", "\u9633\u79bb\u5b50\u7535\u8377");
    model.param().set("zA", "-1", "\u9634\u79bb\u5b50\u7535\u8377");
    model.param()
         .set("Istr_bulk", "0.5*(zC^2*cC_bulk+zA^2*cA_bulk+cOH_bulk+cH_bulk)", "\u672c\u4f53\u79bb\u5b50\u5f3a\u5ea6");
    model.param().set("eps_H2O", "78.5", "\u6c34\u7684\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param()
         .set("xD", "sqrt(epsilon0_const*eps_H2O*V_therm/(2*F_const*Istr_bulk))", "\u5fb7\u62dc\u957f\u5ea6");
    model.param().set("xS", "0.5[nm]", "Stern \u5c42\u539a\u5ea6");
    model.param().set("epsr_st", "11", "Stern \u5c42\u7684\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param().set("Ci_st", "epsilon0_const*epsr_st/xS", "Stern \u5c42\u7535\u5bb9");
    model.param()
         .set("h_max", "0.1[um]", "\u6700\u5927\u7f51\u683c\u5355\u5143\u5927\u5c0f\uff08\u7535\u89e3\u8d28\u57df\uff09");
    model.param()
         .set("h_max_surf", "9.572087e-12[m]", "\u6700\u5927\u7f51\u683c\u5355\u5143\u5927\u5c0f\uff08\u6c27\u5316\u7269\u8868\u9762\uff09~xD/100");
    model.param().set("Ka", "10^-6[M]", "\u5e73\u8861\u5e38\u6570");
    model.param().set("Kb", "100[M]", "\u5e73\u8861\u5e38\u6570");
    model.param().set("Ns", "5e14[1/cm^2]", "\u6c27\u5316\u7269\u8868\u9762\u7ed3\u5408\u4f4d\u70b9\u5bc6\u5ea6");
    model.param()
         .set("phi2_aprx", "-0.01[V]", "\u5916\u90e8\u4ea5\u59c6\u970d\u5179\u5e73\u9762\u7684\u7535\u52bf\uff08\u4e00\u7ef4\u8fd1\u4f3c\u7684\u63a7\u5236\u53c2\u6570\uff09");
    model.param()
         .set("rhos0_aprx", "sqrt(8*R_const*T0*epsilon0_const*eps_H2O*c0)*sinh(phi2_aprx/V_therm/2)", "\u8868\u9762\u7535\u8377\u5bc6\u5ea6\uff08\u4e00\u7ef4\u8fd1\u4f3c\uff09");
    model.param()
         .set("Psi0_aprx", "phi2_aprx+rhos0_aprx/Ci_st", "\u6c27\u5316\u7269\u8868\u9762\u7535\u52bf\uff08\u4e00\u7ef4\u8fd1\u4f3c\uff09");
    model.param().set("eNs", "e_const*Ns", "\u52a9\u53d8\u91cf");
    model.param()
         .set("aHs_aprx", "if(eNs^2>rhos0_aprx^2,(Kb*rhos0_aprx+sqrt((Kb*rhos0_aprx)^2+4*Ka*Kb*(eNs^2-rhos0_aprx^2)))/(2*(eNs-rhos0_aprx)),NaN[M])", "\u6c27\u5316\u7269\u8868\u9762\u5438\u9644\u7684 H+ \u7684\u6d3b\u6027\uff08\u4e00\u7ef4\u8fd1\u4f3c\uff09");
    model.param()
         .set("pHb_aprx", "-log10(aHs_aprx/1[M]*exp(Psi0_aprx/V_therm))", "\u672c\u4f53\u7535\u89e3\u8d28\u7684 pH \u503c\uff08\u4e00\u7ef4\u8fd1\u4f3c\uff09");
    model.param().set("ramp", "1", "\u8fde\u7eed\u53c2\u6570");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{3, 0.7});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{0, -0.7});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "closed");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.03, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.5, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 2.5, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 3, 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 4, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 3, 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.03, 5, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 1, 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0.7, 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 2.3, 2, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 3, 3, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 1, 3, 1);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol3").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 1.5, 0, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 1.5, 1, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 1, 1, 1);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 4);

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1")
         .label("\u7528\u4e8e\u65bd\u52a0\u6307\u5b9a\u6805\u6781\u7535\u538b (Vg) \u7684\u53d8\u91cf");
    model.component("comp1").variable("var1").set("phil_bulk_g", "Vga-WAg-Eeq");
    model.component("comp1").variable("var1").descr("phil_bulk_g", "\u672c\u4f53\u7535\u89e3\u8d28\u7535\u4f4d");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2")
         .label("\u7528\u4e8e\u8c03\u6574 Vg \u4ee5\u83b7\u5f97\u6307\u5b9a\u6f0f\u6781\u7535\u6d41\u7684\u53d8\u91cf");
    model.component("comp1").variable("var2").rename("phil_bulk_g", "phil_bulk_d");
    model.component("comp1").variable("var2").set("phil_bulk_d", "Vg-WAg-Eeq");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u6c27\u5316\u7269\u8868\u9762\u53d8\u91cf");
    model.component("comp1").variable("var3").selection().geom("geom1", 1);
    model.component("comp1").variable("var3").selection().set(7, 9);
    model.component("comp1").variable("var3").set("aHs_g", "cH_bulk*exp(-(phiM-phil_bulk_g)/V_therm)");
    model.component("comp1").variable("var3").descr("aHs_g", "H+ \u7684\u6d3b\u6027");
    model.component("comp1").variable("var3").set("rhos0_g", "e_const*Ns*(aHs_g^2-Ka*Kb)/(Ka*Kb+Kb*aHs_g+aHs_g^2)");
    model.component("comp1").variable("var3").descr("rhos0_g", "\u8868\u9762\u7535\u8377\u5bc6\u5ea6");
    model.component("comp1").variable("var3").set("aHs_d", "cH_bulk*exp(-(phiM-phil_bulk_d)/V_therm)");
    model.component("comp1").variable("var3").descr("aHs_d", "H+ \u7684\u6d3b\u6027");
    model.component("comp1").variable("var3").set("rhos0_d", "e_const*Ns*(aHs_d^2-Ka*Kb)/(Ka*Kb+Kb*aHs_d+aHs_d^2)");
    model.component("comp1").variable("var3").descr("rhos0_d", "\u8868\u9762\u7535\u8377\u5bc6\u5ea6");

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
    model.component("comp1").material("mat2").label("\u7535\u89e3\u8d28");
    model.component("comp1").material("mat2").selection().set(2, 3);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u7535\u89e3\u8d28\u57df");
    model.component("comp1").selection("sel1").set(2, 3);

    model.component("comp1").material("mat2").selection().named("sel1");
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"eps_H2O"});

    model.component("comp1").physics("semi").selection().set(1);
    model.component("comp1").physics("semi").prop("ModelProperties").set("CarrierStatistics", "FermiDirac");
    model.component("comp1").physics("semi").feature("smm1").set("minput_temperature", "T0");
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm1").label("\u5747\u5300\u672c\u5e95\u63ba\u6742");
    model.component("comp1").physics("semi").feature("adm1").selection().set(1);
    model.component("comp1").physics("semi").feature("adm1").set("NAc", "1e17[1/cm^3]");
    model.component("comp1").physics("semi").create("adm2", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm2").label("\u6e90\u6781\u63ba\u6742");
    model.component("comp1").physics("semi").feature("adm2").selection().set(1);
    model.component("comp1").physics("semi").feature("adm2").set("impurityDistribution", "box");
    model.component("comp1").physics("semi").feature("adm2").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm2").set("NDc", "1e20[1/cm^3]");
    model.component("comp1").physics("semi").feature("adm2").set("rb", new String[]{"0[um]", "-0.1[um]", "0"});
    model.component("comp1").physics("semi").feature("adm2").set("jwidth", "0.6[um]");
    model.component("comp1").physics("semi").feature("adm2").set("jdepth", "0.1[um]");
    model.component("comp1").physics("semi").feature("adm2").set("AsymmetricJunctionDepth", true);
    model.component("comp1").physics("semi").feature("adm2").set("jda", new String[]{"0.2[um]", "0.25[um]", "0"});
    model.component("comp1").physics("semi").feature("adm2").set("Nb_src", "root.comp1.semi.adm1.NAc");
    model.component("comp1").physics("semi").feature().duplicate("adm3", "adm2");
    model.component("comp1").physics("semi").feature("adm3").label("\u6f0f\u6781\u63ba\u6742");
    model.component("comp1").physics("semi").feature("adm3").set("rb", new String[]{"2.4[um]", "-0.1[um]", "0"});
    model.component("comp1").physics("semi").create("tar1", "TrapAssistedRecombination", 2);
    model.component("comp1").physics("semi").feature("tar1").selection().set(1);
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc1").label("\u6e90\u6781");
    model.component("comp1").physics("semi").feature("mc1").selection().set(3);
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc2").label("\u6f0f\u6781");
    model.component("comp1").physics("semi").feature("mc2").selection().set(13);
    model.component("comp1").physics("semi").feature("mc2").set("V0", "Vd");
    model.component("comp1").physics("semi").create("mc3", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc3").label("\u4e3b\u4f53");
    model.component("comp1").physics("semi").feature("mc3").selection().set(2);
    model.component("comp1").physics("semi").create("gc1", "GateContact", 1);
    model.component("comp1").physics("semi").feature("gc1").selection().set(7, 9);
    model.component("comp1").physics("semi").feature("gc1").set("V0", "phiM");
    model.component("comp1").physics("semi").feature("gc1").set("epsilon_ins", 4.5);
    model.component("comp1").physics("semi").feature("gc1").set("d_ins", "30[nm]");
    model.component("comp1").physics("semi").feature("gc1").set("Phi", "0[V]");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").field("electricpotential").field("phil");
    model.component("comp1").physics("es").selection().named("sel1");
    model.component("comp1").physics().create("tcc", "TransportOfChargeCarriers", "geom1");
    model.component("comp1").physics("tcc").label("\u8f7d\u6d41\u5b50\u4f20\u9012\uff08\u7535\u89e3\u8d28\uff09");
    model.component("comp1").physics("tcc").selection().set(2, 3);
    model.component("comp1").physics("tcc").selection().named("sel1");
    model.component("comp1").physics("tcc").prop("PhysicalModel").set("dz", "1[m]");
    model.component("comp1").physics("tcc").field("numberdensity").component(new String[]{"n", "n2", "n3", "n4"});
    model.component("comp1").physics("tcc").field("numberdensity").component(1, "C");
    model.component("comp1").physics("tcc").field("numberdensity").component(2, "A");
    model.component("comp1").physics("tcc").field("numberdensity").component(3, "H");
    model.component("comp1").physics("tcc").field("numberdensity").component(4, "OH");
    model.component("comp1").physics().create("bode", "BoundaryODE", "geom1");
    model.component("comp1").physics("bode").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("bode").field("dimensionless").component(new String[]{"phiM"});
    model.component("comp1").physics("bode").selection().set(7, 9);
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics().create("ge2", "GlobalEquations", "geom1");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("ge2").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("es").label("\u9759\u7535\uff08\u7535\u89e3\u8d28\uff09");
    model.component("comp1").physics("es").prop("d").set("d", "1e-6[m]");
    model.component("comp1").physics("es").create("ccnf1", "ChargeConservationFluid", 2);
    model.component("comp1").physics("es").feature("ccnf1").selection().named("sel1");
    model.component("comp1").physics("bode")
         .label("\u6c27\u5316\u7269\u8868\u9762\u7535\u52bf phiM \u7684\u8fb9\u754c\u5e38\u5fae\u5206\u65b9\u7a0b");
    model.component("comp1").physics("bode").prop("Units").set("DependentVariableQuantity", "electricpotential");
    model.component("comp1").physics("bode").prop("Units").set("SourceTermQuantity", "electricpotential");
    model.component("comp1").physics("ge")
         .label("\u7528\u4e8e\u590d\u5236\u6307\u5b9a Vg \u503c\u7684\u5168\u5c40\u5e38\u5fae\u5206\u65b9\u7a0b");
    model.component("comp1").physics("ge2")
         .label("\u7528\u4e8e\u8c03\u6574\u6307\u5b9a\u6f0f\u6781\u7535\u6d41\u7684 Vg \u7684\u5168\u5c40\u5e38\u5fae\u5206\u65b9\u7a0b");
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot1").selection().set(5, 10);
    model.component("comp1").physics("es").feature("pot1").set("V0", "phil_bulk_g");
    model.component("comp1").physics("es").create("pot2", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot2").selection().set(5, 10);
    model.component("comp1").physics("es").feature("pot2").set("V0", "phil_bulk_d");
    model.component("comp1").physics("es").create("df1", "DisplacementField", 1);
    model.component("comp1").physics("es").feature("df1")
         .label("\u534a\u5bfc\u4f53\u4fa7\u7684\u7535\u4f4d\u79fb\u573a");
    model.component("comp1").physics("es").feature("df1").selection().set(7, 9);
    model.component("comp1").physics("es").feature("df1")
         .set("D0", new String[]{"semi.nD_ins*semi.nX", "semi.nD_ins*semi.nY", "0"});
    model.component("comp1").physics("es").create("sfcd1", "SurfaceChargeDensity", 1);
    model.component("comp1").physics("es").feature("sfcd1").selection().set(7, 9);
    model.component("comp1").physics("es").feature("sfcd1").set("rhoqs", "rhos0_g");
    model.component("comp1").physics("es").create("sfcd2", "SurfaceChargeDensity", 1);
    model.component("comp1").physics("es").feature("sfcd2").selection().set(7, 9);
    model.component("comp1").physics("es").feature("sfcd2").set("rhoqs", "rhos0_d");
    model.component("comp1").physics("es").create("scd1", "SpaceChargeDensity", 2);
    model.component("comp1").physics("es").feature("scd1").selection().all();
    model.component("comp1").physics("es").feature("scd1").set("rhoq_src", "root.comp1.tcc.rho");
    model.component("comp1").physics("tcc").feature("tp1").set("E_src", "root.comp1.es.Ex");
    model.component("comp1").physics("tcc").feature("tp1").setIndex("z_C", "zC", 0);
    model.component("comp1").physics("tcc").feature("tp1").setIndex("z_A", "zA", 0);
    model.component("comp1").physics("tcc").feature("tp1").setIndex("z_H", 1, 0);
    model.component("comp1").physics("tcc").feature("tp1").setIndex("z_OH", -1, 0);
    model.component("comp1").physics("tcc").feature("tp1")
         .setIndex("mu", new String[]{"DC*F_const/(R_const*T0)", "0", "0", "0", "DC*F_const/(R_const*T0)", "0", "0", "0", "DC*F_const/(R_const*T0)"}, 0);
    model.component("comp1").physics("tcc").feature("tp1")
         .setIndex("mu", new String[]{"DA*F_const/(R_const*T0)", "0", "0", "0", "DA*F_const/(R_const*T0)", "0", "0", "0", "DA*F_const/(R_const*T0)"}, 1);
    model.component("comp1").physics("tcc").feature("tp1")
         .setIndex("mu", new String[]{"DH*F_const/(R_const*T0)", "0", "0", "0", "DH*F_const/(R_const*T0)", "0", "0", "0", "DH*F_const/(R_const*T0)"}, 2);
    model.component("comp1").physics("tcc").feature("tp1")
         .setIndex("mu", new String[]{"DOH*F_const/(R_const*T0)", "0", "0", "0", "DOH*F_const/(R_const*T0)", "0", "0", "0", "DOH*F_const/(R_const*T0)"}, 3);
    model.component("comp1").physics("tcc").feature("tp1")
         .setIndex("D", new String[]{"DC", "0", "0", "0", "DC", "0", "0", "0", "DC"}, 0);
    model.component("comp1").physics("tcc").feature("tp1")
         .setIndex("D", new String[]{"DA", "0", "0", "0", "DA", "0", "0", "0", "DA"}, 1);
    model.component("comp1").physics("tcc").feature("tp1")
         .setIndex("D", new String[]{"DH", "0", "0", "0", "DH", "0", "0", "0", "DH"}, 2);
    model.component("comp1").physics("tcc").feature("tp1")
         .setIndex("D", new String[]{"DOH", "0", "0", "0", "DOH", "0", "0", "0", "DOH"}, 3);
    model.component("comp1").physics("tcc").feature("init1").setIndex("n0", "cC_bulk*N_A_const", 0);
    model.component("comp1").physics("tcc").feature("init1").setIndex("n0", "cA_bulk*N_A_const", 1);
    model.component("comp1").physics("tcc").feature("init1").setIndex("n0", "cH_bulk*N_A_const", 2);
    model.component("comp1").physics("tcc").feature("init1").setIndex("n0", "cOH_bulk*N_A_const", 3);
    model.component("comp1").physics("tcc").create("nflx1", "NoFlux", 1);
    model.component("comp1").physics("tcc").feature("nflx1").selection().all();
    model.component("comp1").physics("tcc").create("nd1", "NumberDensity", 1);
    model.component("comp1").physics("tcc").feature("nd1").selection().set(5, 10);
    model.component("comp1").physics("tcc").feature("nd1").setIndex("carrier", true, 0);
    model.component("comp1").physics("tcc").feature("nd1").setIndex("n0", "cC_bulk*N_A_const", 0);
    model.component("comp1").physics("tcc").feature("nd1").setIndex("carrier", true, 1);
    model.component("comp1").physics("tcc").feature("nd1").setIndex("n0", "cA_bulk*N_A_const", 1);
    model.component("comp1").physics("tcc").feature("nd1").setIndex("carrier", true, 2);
    model.component("comp1").physics("tcc").feature("nd1").setIndex("n0", "cH_bulk*N_A_const", 2);
    model.component("comp1").physics("tcc").feature("nd1").setIndex("carrier", true, 3);
    model.component("comp1").physics("tcc").feature("nd1").setIndex("n0", "cOH_bulk*N_A_const", 3);
    model.component("comp1").physics("bode").feature("dode1").setIndex("f", "phil+es.nD/Ci_st-phiM", 0);
    model.component("comp1").physics("bode").feature("dode1").setIndex("da", 0, 0);
    model.component("comp1").physics("bode").feature("init1").set("phiM", "phil_bulk_g-0.01[V]");
    model.component("comp1").physics("bode").feature().duplicate("init2", "init1");
    model.component("comp1").physics("bode").feature("init2").set("phiM", "phil_bulk_d-0.01[V]");
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "Vg", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "(Vg-Vga)/1[V]", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "electricpotential");
    model.component("comp1").physics("ge2").feature("ge1").set("DependentVariableQuantity", "electricpotential");
    model.component("comp1").physics("ge2").feature("ge1").setIndex("name", "Vg", 0, 0);
    model.component("comp1").physics("ge2").feature("ge1").setIndex("equation", "semi.I0_2/18[uA]-1", 0, 0);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "h_max");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hgrad", 1.05);
    model.component("comp1").mesh("mesh1").feature().remove("size2");
    model.component("comp1").mesh("mesh1").feature().remove("size3");
    model.component("comp1").mesh("mesh1").feature().remove("ftri1");
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(3, 6, 7, 9, 11, 13);
    model.component("comp1").mesh("mesh1").feature("edg1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("table", "semi");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", 0.03);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(15);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 9);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(7, 9);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 20);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "h_max_surf");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/semi", false);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tcc", true);
    model.study("std1").feature("stat").setSolveFor("/physics/bode", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ge", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ge2", false);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledvariables", new String[]{"var2"});
    model.study("std1").feature("stat")
         .set("disabledphysics", new String[]{"es/pot2", "es/df1", "es/sfcd2", "bode/init2", "ge2"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Vd", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("pname", "Vd", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("pname", "pHb", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(3,1,11)", 0);
    model.study("std1").label("\u4ec5\u7535\u89e3\u8d28");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").selection().set(6);
    model.result().numerical("pev1").setIndex("expr", "phil-phil_bulk_g", 0);
    model.result().numerical("pev1")
         .setIndex("descr", "\u8981\u4ee3\u5165\u4e00\u7ef4\u8fd1\u4f3c\u516c\u5f0f\u7684\u7535\u89e3\u8d28\u7535\u4f4d", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/semi", false);
    model.study("std2").feature("stat").setSolveFor("/physics/es", false);
    model.study("std2").feature("stat").setSolveFor("/physics/tcc", false);
    model.study("std2").feature("stat").setSolveFor("/physics/bode", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ge", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ge2", false);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledvariables", new String[]{"var2", "var3"});
    model.study("std2").feature("stat")
         .set("disabledphysics", new String[]{"es/pot2", "es/sfcd2", "bode/init2", "ge2"});
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "Vd", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("pname", "Vd", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("pname", "phi2_aprx", 0);
    model.study("std2").feature("stat")
         .setIndex("plistarr", "-9.084249078E-4 -0.004637217778 -0.01188430850 -0.02083132004 -0.03018603909 -0.03930650544 -0.04789552279 -0.05583282929 -0.06297207279", 0);
    model.study("std2").label("\u4ec5\u7528\u4e8e\u7535\u89e3\u8d28\u7684\u4e00\u7ef4\u8fd1\u4f3c");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("phil\uff1a\u4e8c\u7ef4\u6a21\u578b vs. \u4e00\u7ef4\u8fd1\u4f3c");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1")
         .set("title", "\u7535\u89e3\u8d28\u7535\u4f4d (V) - \u5b9e\u66f2\u7ebf\uff1a\u4e8c\u7ef4\u6a21\u578b\uff0c\u70b9\u865a\u7ebf\uff1a\u4e00\u7ef4\u8fd1\u4f3c");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").label("\u7ebf\u7ed3\u679c\u56fe\uff1a\u4e8c\u7ef4\u6a21\u578b");
    model.result("pg1").feature("lngr1").selection().set(8);
    model.result("pg1").feature("lngr1").set("expr", "phil-phil_bulk_g");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "y");
    model.result("pg1").feature().duplicate("lngr2", "lngr1");
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").label("\u7ebf\u7ed3\u679c\u56fe\uff1a\u4e00\u7ef4\u8fd1\u4f3c");
    model.result("pg1").feature("lngr2").set("data", "dset2");
    model.result("pg1").feature("lngr2").set("expr", "phi2_aprx*exp(-y/xD)");
    model.result("pg1").feature("lngr2").set("linestyle", "dotted");
    model.result("pg1").feature("lngr2").set("linewidth", 3);
    model.result("pg1").set("xlog", true);
    model.result("pg1").run();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std3").feature("stat").setSolveFor("/physics/es", false);
    model.study("std3").feature("stat").setSolveFor("/physics/tcc", false);
    model.study("std3").feature("stat").setSolveFor("/physics/bode", false);
    model.study("std3").feature("stat").setSolveFor("/physics/ge", false);
    model.study("std3").feature("stat").setSolveFor("/physics/ge2", false);
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disabledvariables", new String[]{"var2"});
    model.study("std3").feature("stat")
         .set("disabledphysics", new String[]{"es/pot2", "es/sfcd2", "bode/init2", "ge2"});
    model.study("std3").feature("stat").set("usesol", true);
    model.study("std3").feature("stat").set("notsolmethod", "sol");
    model.study("std3").feature("stat").set("notstudy", "std1");
    model.study("std3").feature("stat").set("notsolnum", "first");
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "Vd", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "V", 0);
    model.study("std3").feature("stat").setIndex("pname", "Vd", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "V", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "0 10", 0);
    model.study("std3").feature("stat").setIndex("punit", "mV", 0);
    model.study("std3").label("\u4ec5\u534a\u5bfc\u4f53");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std4").feature("stat").setSolveFor("/physics/es", true);
    model.study("std4").feature("stat").setSolveFor("/physics/tcc", true);
    model.study("std4").feature("stat").setSolveFor("/physics/bode", true);
    model.study("std4").feature("stat").setSolveFor("/physics/ge", false);
    model.study("std4").feature("stat").setSolveFor("/physics/ge2", false);
    model.study("std4").feature("stat").set("useadvanceddisable", true);
    model.study("std4").feature("stat").set("disabledvariables", new String[]{"var2"});
    model.study("std4").feature("stat")
         .set("disabledphysics", new String[]{"es/pot2", "es/sfcd2", "bode/init2", "ge2"});
    model.study("std4").feature("stat").set("useinitsol", true);
    model.study("std4").feature("stat").set("initmethod", "sol");
    model.study("std4").feature("stat").set("initstudy", "std3");
    model.study("std4").feature("stat").set("solnum", "last");
    model.study("std4").feature("stat").set("useparam", true);
    model.study("std4").feature("stat").setIndex("pname", "Vd", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "V", 0);
    model.study("std4").feature("stat").setIndex("pname", "Vd", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "V", 0);
    model.study("std4").feature("stat").setIndex("pname", "Vga", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "range(0,0.2,3)", 0);
    model.study("std4").label("\u5728 pH \u6052\u5b9a\u7684\u60c5\u51b5\u4e0b\u626b\u63cf Vg");
    model.study("std4").setGenPlots(false);
    model.study("std4").showAutoSequences("all");

    model.sol("sol4").feature("s1").feature("d1").set("nliniterrefine", true);

    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("Id vs. Vg");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{"semi.I0_2"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u7ec8\u7aef\u7535\u6d41"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"A"});
    model.result("pg2").feature("glob1").setIndex("unit", "uA", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "\u6f0f\u6781\u7535\u6d41", 0);
    model.result("pg2").feature("glob1").set("legend", false);
    model.result("pg2").run();

    model.study().create("std5");
    model.study("std5").label("\u4f7f\u7528\u56fa\u5b9a Vg \u626b\u63cf pH \u548c Vd");
    model.study("std5").setGenPlots(false);
    model.study("std5").feature().copy("stat", "std4/stat");
    model.study("std5").feature("stat").setSolveFor("/physics/ge", true);
    model.study("std5").feature("stat").set("initstudy", "std4");
    model.study("std5").feature("stat").set("solnum", 14);
    model.study("std5").feature("stat").set("sweeptype", "filled");
    model.study("std5").feature("stat").setIndex("plistarr", 2.6, 0);
    model.study("std5").feature("stat").setIndex("pname", "Vd", 1);
    model.study("std5").feature("stat").setIndex("plistarr", "", 1);
    model.study("std5").feature("stat").setIndex("punit", "V", 1);
    model.study("std5").feature("stat").setIndex("pname", "Vd", 1);
    model.study("std5").feature("stat").setIndex("plistarr", "", 1);
    model.study("std5").feature("stat").setIndex("punit", "V", 1);
    model.study("std5").feature("stat").setIndex("pname", "pHb", 1);
    model.study("std5").feature("stat").setIndex("plistarr", "3 7 11", 1);
    model.study("std5").feature("stat").setIndex("pname", "Vd", 2);
    model.study("std5").feature("stat").setIndex("plistarr", "", 2);
    model.study("std5").feature("stat").setIndex("punit", "V", 2);
    model.study("std5").feature("stat").setIndex("pname", "Vd", 2);
    model.study("std5").feature("stat").setIndex("plistarr", "", 2);
    model.study("std5").feature("stat").setIndex("punit", "V", 2);
    model.study("std5").feature("stat").setIndex("plistarr", "range(0,0.2,1.2)^2", 2);
    model.study("std5").feature("stat").set("preusesol", "auto");
    model.study("std5").showAutoSequences("all");

    model.sol("sol5").feature("s1").feature("d1").set("nliniterrefine", true);

    model.study("std5").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("3 \u79cd\u4e0d\u540c pH \u503c\u7684 Id-Vd \u66f2\u7ebf");
    model.result("pg3").set("data", "dset5");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"semi.I0_2"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u7ec8\u7aef\u7535\u6d41"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"A"});
    model.result("pg3").feature("glob1").setIndex("unit", "uA", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u6f0f\u6781\u7535\u6d41", 0);
    model.result("pg3").feature("glob1").set("autodescr", false);
    model.result("pg3").run();

    model.study().create("std6");
    model.study("std6").label("\u4f7f\u7528\u56fa\u5b9a Id \u626b\u63cf pH");
    model.study("std6").setGenPlots(false);
    model.study("std6").feature().copy("stat", "std5/stat");
    model.study("std6").feature("stat").set("disabledvariables", new String[]{"var1"});
    model.study("std6").feature("stat").set("disabledphysics", new String[]{"ge2", "es/pot1", "es/sfcd1"});
    model.study("std6").feature("stat").setSolveFor("/physics/ge", false);
    model.study("std6").feature("stat").set("disabledphysics", new String[]{"ge2", "es/pot1", "es/sfcd1", "ge"});
    model.study("std6").feature("stat").setSolveFor("/physics/ge2", true);
    model.study("std6").feature("stat").set("initstudy", "std5");
    model.study("std6").feature("stat").set("solnum", 6);
    model.study("std6").feature("stat").remove("pname", 0);
    model.study("std6").feature("stat").remove("punit", 0);
    model.study("std6").feature("stat").remove("plistarr", new int[]{0});
    model.study("std6").feature("stat").setIndex("plistarr", "3 5 7 9 11", 0);
    model.study("std6").feature("stat").move("pname", new int[]{0}, 1);
    model.study("std6").feature("stat").move("plistarr", new int[]{0}, 1);
    model.study("std6").feature("stat").move("punit", new int[]{0}, 1);
    model.study("std6").feature("stat").setIndex("plistarr", 1, 0);
    model.study("std6").feature("stat").set("pcontinuationmode", "no");
    model.study("std6").feature("stat").set("preusesol", "yes");
    model.study("std6").showAutoSequences("all");

    model.sol("sol6").feature("s1").feature("d1").set("nliniterrefine", true);

    model.study("std6").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("Vg vs. pH - ISFET \u7684\u7075\u654f\u5ea6\u66f2\u7ebf");
    model.result("pg4").set("data", "dset6");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("descr", "pH \u4f20\u611f\u5668\u7684\u8f93\u51fa V", 0);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "pHb");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset6");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature().duplicate("surf2", "surf1");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("expr", "V");
    model.result("pg5").feature("surf2").set("resolution", "norefine");
    model.result("pg5").feature("surf2").set("inheritplot", "surf1");
    model.result("pg5").run();

    model.title("\u79bb\u5b50\u654f\u573a\u6548\u5e94\u6676\u4f53\u7ba1 (ISFET) \u4eff\u771f");

    model
         .description("\u79bb\u5b50\u654f\u573a\u6548\u5e94\u6676\u4f53\u7ba1 (ISFET) \u662f\u7528\u9002\u5f53\u7684\u7535\u89e3\u8d28\u53d6\u4ee3 MOSFET \u7684\u6805\u6781\u89e6\u70b9\u6765\u6784\u5efa\u7684\u3002\u901a\u8fc7\u6d4b\u91cf\u7531\u4e8e\u79bb\u5b50\u4e0e\u6805\u4ecb\u8d28\u76f8\u4e92\u4f5c\u7528\u800c\u5f15\u8d77\u7684\u6805\u538b\u53d8\u5316\uff0c\u53ef\u4ee5\u786e\u5b9a\u7535\u89e3\u8d28\u4e2d\u7279\u5b9a\u79bb\u5b50\u7269\u8d28\u7684\u6d53\u5ea6\u3002\n\n\u672c\u6559\u7a0b\u4ee5 ISFET pH \u4f20\u611f\u5668\u4e3a\u4f8b\uff0c\u9610\u8ff0\u4e86\u5728\u534a\u5bfc\u4f53\u6a21\u578b\u4e0e\u7535\u89e3\u8d28\u6a21\u578b\u4e4b\u95f4\u5efa\u7acb\u8026\u5408\u7684\u8fc7\u7a0b\u3002\u6b64\u5916\uff0c\u8fd8\u6f14\u793a\u4e86\u4e00\u79cd\u4f7f\u7528\u7b80\u5355\u7684\u5168\u5c40\u65b9\u7a0b\u6765\u63d0\u53d6\u64cd\u4f5c\u53c2\u6570\u7684\u6280\u672f\uff0c\u800c\u65e0\u9700\u5bf9\u5b9e\u9645\u7684\u53cd\u9988\u7535\u8def\u8fdb\u884c\u663e\u5f0f\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("isfet.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
