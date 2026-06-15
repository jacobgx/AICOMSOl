/*
 * trench_gate_igbt_2d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:57 by COMSOL 6.3.0.290. */
public class trench_gate_igbt_2d {

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

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.param().set("W", "16[um]");
    model.param().descr("W", "\u5668\u4ef6\u5bbd\u5ea6");
    model.param().set("S", "1[um]");
    model.param().descr("S", "\u53f0\u9762\u5bbd\u5ea6");
    model.param().set("DT", "2[um]");
    model.param().descr("DT", "\u6c9f\u69fd\u6df1\u5ea6");
    model.param().set("WT", "0.33[um]");
    model.param().descr("WT", "\u6c9f\u69fd\u5bbd\u5ea6");
    model.param().set("Wewin", "0.16[um]");
    model.param().descr("Wewin", "\u53d1\u5c04\u6781\u63a5\u89e6\u7a97\u53e3");
    model.param().set("Dp", "1.4[um]");
    model.param().descr("Dp", "p \u57fa\u5e95\u6df1\u5ea6");
    model.param().set("Dn", "0.13[um]");
    model.param().descr("Dn", "n+ \u53d1\u5c04\u6781\u6df1\u5ea6");
    model.param().set("tOX", "33[nm]");
    model.param().descr("tOX", "\u6c27\u5316\u7269\u539a\u5ea6");
    model.param().set("Dnb", "120[um]");
    model.param().descr("Dnb", "n \u57fa\u5e95\u6df1\u5ea6");
    model.param().set("Ln", "1.5[um]");
    model.param().descr("Ln", "n+ \u53d1\u5c04\u6781\u957f\u5ea6");
    model.param().set("Lp", "1.5[um]");
    model.param().descr("Lp", "p+ \u53d1\u5c04\u6781\u957f\u5ea6");
    model.param().set("Nab", "3.8e17[cm^-3]");
    model.param().descr("Nab", "p \u57fa\u5e95\u5cf0\u503c\u63ba\u6742");
    model.param().set("Ndb", "8.5e13[cm^-3]");
    model.param().descr("Ndb", "n \u57fa\u5e95\u63ba\u6742");
    model.param().set("Ndbf", "9e15[cm^-3]");
    model.param().descr("Ndbf", "n \u7f13\u51b2\u533a\u63ba\u6742");
    model.param().set("Nac", "3.7e18[cm^-3]");
    model.param().descr("Nac", "p+ \u96c6\u7535\u6781\u63ba\u6742");
    model.param().set("Nae", "1e20[cm^-3]");
    model.param().descr("Nae", "p+ \u53d1\u5c04\u6781\u63ba\u6742");
    model.param().set("Nde", "1e20[cm^-3]");
    model.param().descr("Nde", "n+ \u53d1\u5c04\u6781\u63ba\u6742");
    model.param().set("tau0", "10[us]");
    model.param().descr("tau0", "\u8f7d\u6d41\u5b50\u5bff\u547d");
    model.param().set("tnbf", "5[um]");
    model.param().descr("tnbf", "n \u7f13\u51b2\u533a\u539a\u5ea6");
    model.param().set("Dsub", "10[um]");
    model.param().descr("Dsub", "p+ \u96c6\u7535\u6781\u539a\u5ea6");
    model.param().set("t0", "Dp+Dnb+tnbf+Dsub");
    model.param().descr("t0", "\u5668\u4ef6\u539a\u5ea6");
    model.param().set("d0", "(Ln+Lp)/2");
    model.param().descr("d0", "\u9762\u5916\u539a\u5ea6");
    model.param().set("rhoCC", "1.6e-4[ohm*cm^2]");
    model.param().descr("rhoCC", "p+ \u57fa\u5e95\u7b49\u6548\u7535\u963b\u7387");
    model.param().set("rhoCE", "4.7e-6[ohm*cm^2]");
    model.param().descr("rhoCE", "\u53d1\u5c04\u6781\u63a5\u89e6\u7535\u963b\u7387");
    model.param().set("T0", "300[K]");
    model.param().descr("T0", "\u6676\u683c\u6e29\u5ea6");
    model.param().set("Eg0", "1.1743[V]");
    model.param().descr("Eg0", "\u5e26\u9699 Shigyo");
    model.param().set("Nv_", "(T0/300[K])^(3/2)*1.04e19[1/cm^3]");
    model.param().descr("Nv_", "\u4ef7\u5e26 DOS COMSOL");
    model.param().set("Nc_", "(T0/300[K])^(3/2)*2.8e19[1/cm^3]");
    model.param().descr("Nc_", "\u5bfc\u5e26 DOS COMSOL");
    model.param().set("ni0", "1e10[cm^-3]");
    model.param().descr("ni0", "\u672c\u5f81\u8f7d\u6d41\u5b50\u6d53\u5ea6");
    model.param().set("Nvcfac", "sqrt(ni0^2/Nv_/Nc_/exp(-Eg0*e_const/T0/k_B_const))");
    model.param().descr("Nvcfac", "DOS \u56e0\u5b50\u4e0e ni \u5339\u914d");
    model.param().set("Nv0", "Nvcfac*Nv_");
    model.param().descr("Nv0", "\u4ef7\u5e26 DOS Shigyo");
    model.param().set("Nc0", "Nvcfac*Nc_");
    model.param().descr("Nc0", "\u5bfc\u5e26 DOS Shigyo");
    model.param().set("Nref0", "6.5e16[cm^-3]");
    model.param().descr("Nref0", "\u5e26\u9699\u53d8\u7a84\u53c2\u8003\u6d53\u5ea6 Shigyo");
    model.param("default").setShowInParamSel(false);
    model.param().create("par2");
    model.param("par2").set("Vc", "0[V]");
    model.param("par2").descr("Vc", "\u96c6\u7535\u6781\u7535\u538b");
    model.param("par2").set("Vg", "5[V]");
    model.param("par2").descr("Vg", "\u6805\u6781\u7535\u538b");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1")
         .label("\u77e9\u5f62 1 - \u5668\u4ef6\u8f6e\u5ed3\uff08\u4e00\u534a\u5355\u5143\uff09");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W/2", "t0"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-t0"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "Dsub", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "tnbf", 1);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").label("\u77e9\u5f62 2 - \u6c9f\u69fd");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"WT", "DT-WT/2"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"S/2", "-(DT-WT/2)"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").label("\u5706 1 - \u6c9f\u69fd");
    model.component("comp1").geom("geom1").feature("c1").set("r", "WT/2");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"S/2+WT/2", "-(DT-WT/2)"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1")
         .label("\u5dee\u96c6 1 - \u5668\u4ef6\u8f6e\u5ed3\u51cf\u53bb\u6c9f\u69fd");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1", "r2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1")
         .label("\u70b9 1 - \u53d1\u5c04\u6781\u63a5\u89e6 & \u63ba\u6742\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "S/4-Wewin/1.5 S/4 S/4+Wewin/1.5", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "0 0 0", 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").label("\u77e9\u5f62 3 - \u7f51\u683c\u8f85\u52a9\u7ebf");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"S/2", "Dn"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "-Dn"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").label("\u77e9\u5f62 4 - \u7f51\u683c\u8f85\u52a9\u7ebf");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"30[nm]", "DT-WT/2"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"S/2-30[nm]", "-(DT-WT/2)"});
    model.component("comp1").geom("geom1").feature().duplicate("r5", "r4");
    model.component("comp1").geom("geom1").feature("r5").label("\u77e9\u5f62 5 - \u7f51\u683c\u8f85\u52a9\u7ebf");
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"S/2+WT", "-(DT-WT/2)"});
    model.component("comp1").geom("geom1").feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("c2").label("\u5706 2 - \u7f51\u683c\u8f85\u52a9\u7ebf");
    model.component("comp1").geom("geom1").feature("c2").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c2").set("r", "WT/2+30[nm]");
    model.component("comp1").geom("geom1").feature("c2").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c2").set("rot", 180);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c2", 3, 4);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").label("\u7ebf\u6bb5 1 - \u7f51\u683c\u8f85\u52a9\u7ebf");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"0", "-DT*8"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"W/2", "-DT*8"});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input")
         .set("fin", 8, 10, 15, 16, 17, 18, 23, 25, 31, 33);
    model.component("comp1").geom("geom1").run("mce1");

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

    model.component("comp1").physics("semi").prop("d").set("d", "d0");
    model.component("comp1").physics("semi").prop("ModelProperties").set("CarrierStatistics", "FermiDirac");
    model.component("comp1").physics("semi").feature("smm1").create("mmkl1", "KlaassenUnifiedMobilityModel", 2);
    model.component("comp1").physics("semi").feature("smm1").create("mmct1", "CaugheyThomasMobilityModel", 2);
    model.component("comp1").physics("semi").feature("smm1").feature("mmct1")
         .set("mun_in_src", "root.comp1.semi.mun_kl");
    model.component("comp1").physics("semi").feature("smm1").feature("mmct1")
         .set("mup_in_src", "root.comp1.semi.mup_kl");
    model.component("comp1").physics("semi").feature("smm1").set("minput_temperature", "T0");
    model.component("comp1").physics("semi").feature("smm1").set("Eg0_mat", "userdef");
    model.component("comp1").physics("semi").feature("smm1").set("Eg0", "Eg0");
    model.component("comp1").physics("semi").feature("smm1").set("Nv_mat", "userdef");
    model.component("comp1").physics("semi").feature("smm1").set("Nv", "Nv0");
    model.component("comp1").physics("semi").feature("smm1").set("Nc_mat", "userdef");
    model.component("comp1").physics("semi").feature("smm1").set("Nc", "Nc0");
    model.component("comp1").physics("semi").feature("smm1").set("mun_mat", "root.comp1.semi.mun_ct");
    model.component("comp1").physics("semi").feature("smm1").set("mup_mat", "root.comp1.semi.mup_ct");
    model.component("comp1").physics("semi").feature("smm1").set("BandGapNarrowing", "slotboom");
    model.component("comp1").physics("semi").feature("smm1").set("Nref_sb_mat", "userdef");
    model.component("comp1").physics("semi").feature("smm1").set("Nref_sb", "Nref0");
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm1")
         .label("\u89e3\u6790\u63ba\u6742\u6a21\u578b - n \u57fa\u5e95");
    model.component("comp1").physics("semi").feature("adm1").selection().set(3);
    model.component("comp1").physics("semi").feature("adm1").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm1").set("NDc", "Ndb");
    model.component("comp1").physics("semi").create("adm2", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm2")
         .label("\u89e3\u6790\u63ba\u6742\u6a21\u578b - n \u7f13\u51b2\u533a");
    model.component("comp1").physics("semi").feature("adm2").selection().set(2);
    model.component("comp1").physics("semi").feature("adm2").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm2").set("NDc", "Ndbf");
    model.component("comp1").physics("semi").create("adm3", "AnalyticDopingModel", 2);
    model.component("comp1").physics("semi").feature("adm3")
         .label("\u89e3\u6790\u63ba\u6742\u6a21\u578b - p+ \u96c6\u7535\u6781");
    model.component("comp1").physics("semi").feature("adm3").selection().set(1);
    model.component("comp1").physics("semi").feature("adm3").set("NAc", "Nac");
    model.component("comp1").physics("semi").create("gdm1", "GeometricDopingModel", 2);
    model.component("comp1").physics("semi").feature("gdm1")
         .label("\u51e0\u4f55\u63ba\u6742\u6a21\u578b - p \u57fa\u5e95");
    model.component("comp1").physics("semi").feature("gdm1").selection().set(3);
    model.component("comp1").physics("semi").feature("gdm1").set("NAgen", "Nab");
    model.component("comp1").physics("semi").feature("gdm1").set("jd_gen", "Dp");
    model.component("comp1").physics("semi").feature("gdm1").set("Nb_gen", "Ndb");
    model.component("comp1").physics("semi").feature("gdm1").feature("gdmbs1").selection().set(7, 8, 9, 10, 13);
    model.component("comp1").physics("semi").create("gdm2", "GeometricDopingModel", 2);
    model.component("comp1").physics("semi").feature("gdm2")
         .label("\u51e0\u4f55\u63ba\u6742\u6a21\u578b - p+ \u53d1\u5c04\u6781");
    model.component("comp1").physics("semi").feature("gdm2").selection().set(3);
    model.component("comp1").physics("semi").feature("gdm2").set("NAgen", "Nae");
    model.component("comp1").physics("semi").feature("gdm2").set("jd_gen", "Dn");
    model.component("comp1").physics("semi").feature("gdm2").set("Nb_gen", "Ndb");
    model.component("comp1").physics("semi").feature("gdm2").feature("gdmbs1").selection().set(7, 8);
    model.component("comp1").physics("semi").create("gdm3", "GeometricDopingModel", 2);
    model.component("comp1").physics("semi").feature("gdm3")
         .label("\u51e0\u4f55\u63ba\u6742\u6a21\u578b - n+ \u53d1\u5c04\u6781");
    model.component("comp1").physics("semi").feature("gdm3").selection().set(3);
    model.component("comp1").physics("semi").feature("gdm3").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("gdm3").set("NDgen", "Nde");
    model.component("comp1").physics("semi").feature("gdm3").set("jd_gen", "Dn");
    model.component("comp1").physics("semi").feature("gdm3").set("Nb_gen", "Ndb");
    model.component("comp1").physics("semi").feature("gdm3").feature("gdmbs1").selection().set(9, 10);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("semi").create("tar1", "TrapAssistedRecombination", 2);
    model.component("comp1").physics("semi").feature("tar1").selection().all();
    model.component("comp1").physics("semi").feature("tar1").set("taun_mat", "userdef");
    model.component("comp1").physics("semi").feature("tar1").set("taun", "tau0");
    model.component("comp1").physics("semi").feature("tar1").set("taup_mat", "userdef");
    model.component("comp1").physics("semi").feature("tar1").set("taup", "tau0");
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc1").label("\u91d1\u5c5e\u63a5\u89e6 - \u53d1\u5c04\u6781");
    model.component("comp1").physics("semi").feature("mc1").selection().set(8, 9);
    model.component("comp1").physics("semi").feature("mc1").set("TerminalName", "E");
    model.component("comp1").physics("semi").feature("mc1").set("ContactResistance", true);
    model.component("comp1").physics("semi").feature("mc1").set("rho_c", "rhoCE");
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 1);
    model.component("comp1").physics("semi").feature("mc2").label("\u91d1\u5c5e\u63a5\u89e6 - \u96c6\u7535\u6781");
    model.component("comp1").physics("semi").feature("mc2").selection().set(2);
    model.component("comp1").physics("semi").feature("mc2").set("TerminalName", "C");
    model.component("comp1").physics("semi").feature("mc2").set("V0", "Vc");
    model.component("comp1").physics("semi").feature("mc2").set("ContactResistance", true);
    model.component("comp1").physics("semi").feature("mc2").set("rho_c", "rhoCC");
    model.component("comp1").physics("semi").create("gc1", "GateContact", 1);
    model.component("comp1").physics("semi").feature("gc1").selection().set(11, 12);
    model.component("comp1").physics("semi").feature("gc1").set("TerminalName", "G");
    model.component("comp1").physics("semi").feature("gc1").set("V0", "Vg");
    model.component("comp1").physics("semi").feature("gc1").set("epsilon_ins", 3.9);
    model.component("comp1").physics("semi").feature("gc1").set("d_ins", "tOX");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").label("\u8fb9 1 - \u91d1\u5c5e\u63a5\u89e6");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(8, 9);
    model.component("comp1").mesh("mesh1").feature("edg1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 4);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 9.1);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.04);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.25);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.35);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 1.1);
    model.component("comp1").mesh("mesh1").create("edg2", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg2").label("\u8fb9 2 - \u53d1\u5c04\u6781\u8868\u9762");
    model.component("comp1").mesh("mesh1").feature("edg2").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("edg2").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("edg2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("elemcount", 3);
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("elemratio", 6);
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("edg3", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg3").label("\u8fb9 3 - \u53d1\u5c04\u6781\u8868\u9762");
    model.component("comp1").mesh("mesh1").feature("edg3").selection().set(10);
    model.component("comp1").mesh("mesh1").feature("edg3").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("edg3").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg3").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg3").feature("dis1").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("edg3").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("cpe1", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").set(7, 8, 9, 10);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").set(26);
    model.component("comp1").mesh("mesh1").feature("cpe1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").label("\u6620\u5c04 1 - \u53d1\u5c04\u5668\u6df1\u5ea6");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(9);
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(18, 29);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").label("\u6620\u5c04 2 - \u6805\u6781\u6df1\u5ea6");
    model.component("comp1").mesh("mesh1").feature("map2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(5, 6, 7, 8);
    model.component("comp1").mesh("mesh1").feature("map2").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map2").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1")
         .label("\u5206\u5e03 1 - \u5de6\u4fa7\u6df1\u5ea6");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(19, 28, 30);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2")
         .label("\u5206\u5e03 2 - \u53f3\u4fa7\u6df1\u5ea6");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").selection().set(13, 31);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis3")
         .label("\u5206\u5e03 3 - \u5de6\u4fa7\u8868\u9762");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis3").selection().set(20, 27);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis3").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis3").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis3").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis4")
         .label("\u5206\u5e03 4 - \u53f3\u4fa7\u8868\u9762");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis4").selection().set(22, 32);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis4").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis4").set("elemcount", 6);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis4").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis5", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis5").label("\u5206\u5e03 5 - \u5e95\u9762");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis5").selection().set(12, 21, 33, 34);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis5").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis5").set("elemcount", 8);
    model.component("comp1").mesh("mesh1").run("map2");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("ftri1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("cpe2", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("source").set(25);
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("destination").set(2, 4, 6);
    model.component("comp1").mesh("mesh1").feature("cpe2").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").create("map3", "Map");
    model.component("comp1").mesh("mesh1").feature("map3").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map3").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map3").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis1").label("\u5206\u5e03 1 - n \u57fa\u5e95");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis1").selection().set(5, 24);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis1").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map3").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis2")
         .label("\u5206\u5e03 2 - n \u7f13\u51b2\u533a");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis2").selection().set(3, 15);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis2").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("map3").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis3")
         .label("\u5206\u5e03 3 - p+ \u96c6\u7535\u6781");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis3").selection().set(1, 14);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis3").set("elemcount", 3);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis3").set("elemratio", 30);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis3").set("reverse", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Vc", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("pname", "Vc", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.1,1.2) range(1.5,0.5,5)", 0);
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u5b50\u6d53\u5ea6 (semi)");
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
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "V");
    model.result("pg3").feature("surf1").set("resolution", "norefine");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf2").set("expr", "semi.Nnetdop");
    model.result("pg4").feature("surf2").set("unit", "1/cm^3");
    model.result("pg4").feature("surf2").set("coloring", "gradient");
    model.result("pg4").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf2").set("topcolor", "red");
    model.result("pg4").feature("surf2").set("bottomcolor", "custom");
    model.result("pg4").feature("surf2").set("custombottomcolor", new double[]{1, 0.8, 0.8});
    model.result("pg4").feature("surf2").set("smooth", "internal");
    model.result("pg4").feature("surf2").set("showsolutionparams", "on");
    model.result("pg4").feature("surf2").set("data", "parent");
    model.result("pg4").feature("surf2").set("titletype", "none");
    model.result("pg4").feature("surf2").feature().create("filt1", "Filter");
    model.result("pg4").feature("surf2").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg4").feature("surf2").feature("filt1").set("useder", true);
    model.result("pg4").feature("surf1").set("expr", "semi.Nnetdop");
    model.result("pg4").feature("surf1").set("unit", "1/cm^3");
    model.result("pg4").feature("surf1").set("coloring", "gradient");
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").set("topcolor", "blue");
    model.result("pg4").feature("surf1").set("bottomcolor", "custom");
    model.result("pg4").feature("surf1").set("custombottomcolor", new double[]{0.8, 0.8, 1});
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").set("titletype", "none");
    model.result("pg4").feature("surf1").feature().create("filt1", "Filter");
    model.result("pg4").feature("surf1").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg4").feature("surf1").feature("filt1").set("useder", true);
    model.result("pg4").set("titletype", "manual");
    model.result("pg4")
         .set("title", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert: P \u578b (\u7ea2\u8272), N \u578b (\u84dd\u8272)");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").set("legendpos", "alternating");
    model.result("pg4").feature("surf2").label("P \u578b");
    model.result("pg4").feature("surf1").label("N \u578b");
    model.result("pg4").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi)");
    model.result("pg1").run();

    model.sol("sol1").feature("v2").set("scalemethod", "init");
    model.sol("sol1").feature("v2").feature("comp1_semi_V_dae").set("scalemethod", "manual");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("J-V (log) - \u56fe 4(a)");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("xmin", 0);
    model.result("pg5").set("xmax", 5);
    model.result("pg5").set("ymin", 10);
    model.result("pg5").set("ymax", 3000);
    model.result("pg5").set("ylog", true);
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").label("\u5168\u5c40 1 - \u4e8c\u7ef4");
    model.result("pg5").feature("glob1").setIndex("expr", "semi.J0_C", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "A/cm^2", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "2D", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("J-V\uff08\u7ebf\u6027\uff09 - \u56fe 4(b)");
    model.result("pg6").set("xmax", 3);
    model.result("pg6").set("ymin", -20);
    model.result("pg6").set("ymax", 700);
    model.result("pg6").set("ylog", false);
    model.result("pg6").run();
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u5b50\u6d53\u5ea6 & \u7535\u6d41\u6d41\u7ebf");

    model.component("comp1").view("view1").axis().set("xmin", -0.1);
    model.component("comp1").view("view1").axis().set("xmax", 0.6);
    model.component("comp1").view("view1").axis().set("ymin", -0.8);
    model.component("comp1").view("view1").axis().set("ymax", 0.05);

    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").label("\u6d41\u7ebf 1 - \u7535\u5b50\u6d41");
    model.result("pg1").feature("str1").set("expr", new String[]{"semi.JnX", "semi.JnY"});
    model.result("pg1").feature("str1").set("descr", "\u7535\u5b50\u6d41\u5bc6\u5ea6");
    model.result("pg1").feature("str1").set("selnumber", 10);
    model.result("pg1").feature("str1").selection().set(9);
    model.result("pg1").run();
    model.result("pg1").create("str2", "Streamline");
    model.result("pg1").feature("str2").label("\u6d41\u7ebf 2 - \u7a7a\u7a74\u6d41");
    model.result("pg1").feature("str2").set("expr", new String[]{"semi.JpX", "semi.JpY"});
    model.result("pg1").feature("str2").set("descr", "\u7a7a\u7a74\u6d41\u5bc6\u5ea6");
    model.result("pg1").feature("str2").set("selnumber", 10);
    model.result("pg1").feature("str2").selection().set(8);
    model.result("pg1").feature("str2").set("color", "white");
    model.result("pg1").run();

    model.title("\u6c9f\u69fd\u6805 IGBT \u4e8c\u7ef4\u6a21\u578b");

    model
         .description("\u5728\u8fd9\u4e2a\u7531\u4e24\u90e8\u5206\u7ec4\u6210\u7684\u793a\u4f8b\u7684\u524d\u534a\u90e8\u5206\uff0c\u6211\u4eec\u6784\u5efa\u4e00\u4e2a\u6c9f\u69fd\u6805 IGBT \u4e8c\u7ef4\u6a21\u578b\uff0c\u7136\u540e\u5728\u540e\u534a\u90e8\u5206\u5c06\u5176\u6269\u5c55\u6210\u4e09\u7ef4\u6a21\u578b\u3002\u901a\u5e38\uff0c\u6700\u6709\u6548\u7684\u65b9\u6cd5\u662f\u4ece\u4e8c\u7ef4\u6a21\u578b\u5f00\u59cb\uff0c\u4ee5\u786e\u4fdd\u4e00\u5207\u6309\u9884\u671f\u5de5\u4f5c\uff0c\u7136\u540e\u518d\u5c06\u5176\u6269\u5c55\u5230\u4e09\u7ef4\u3002\u672c\u4f8b\u5c06 Caughey-Thomas \u8fc1\u79fb\u7387\u6a21\u578b\u4e0e Klaassen \u7edf\u4e00\u8fc1\u79fb\u7387\u6a21\u578b\u76f8\u7ed3\u5408\uff0c\u4ee5\u89e3\u91ca\u901f\u5ea6\u9971\u548c\u4e0e\u58f0\u5b50\u3001\u6742\u8d28\u4ee5\u53ca\u8f7d\u6d41\u5b50-\u8f7d\u6d41\u5b50\u6563\u5c04\uff1b\u5e76\u91c7\u7528\u201c\u91d1\u5c5e\u63a5\u89e6\u201d\u8fb9\u754c\u6761\u4ef6\u7684\u201c\u63a5\u89e6\u7535\u963b\u201d\u9009\u9879\uff0c\u4ee5\u5b9e\u73b0\u53c2\u8003\u6587\u732e\u4e2d\u63d0\u5230\u7684\u96c6\u7535\u6781\u548c\u53d1\u5c04\u6781\u5bc4\u751f\u7535\u963b\u7684\u6df7\u5408\u6a21\u5f0f\u4eff\u771f\u3002\u8ba1\u7b97\u51fa\u7684\u96c6\u7535\u6781\u7535\u6d41\u5bc6\u5ea6\u968f\u96c6\u7535\u6781\u7535\u538b\u7684\u53d8\u5316\u60c5\u51b5\u4e0e\u516c\u5e03\u7684\u7ed3\u679c\u543b\u5408\u826f\u597d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("trench_gate_igbt_2d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
