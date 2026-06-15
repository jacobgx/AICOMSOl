/*
 * nanowire_density_gradient_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:56 by COMSOL 6.3.0.290. */
public class nanowire_density_gradient_3d {

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
    model.param().set("Lgate", "4[nm]");
    model.param().descr("Lgate", "\u6805\u6781\u957f\u5ea6");
    model.param().set("Wchannel", "3.2[nm]");
    model.param().descr("Wchannel", "\u901a\u9053\u5bbd\u5ea6");
    model.param().set("dOx", "0.8[nm]");
    model.param().descr("dOx", "\u6c27\u5316\u5c42\u539a\u5ea6");
    model.param().set("Lsource", "15[nm]");
    model.param().descr("Lsource", "\u6e90\u6781\u957f\u5ea6");
    model.param().set("Ldrain", "15[nm]");
    model.param().descr("Ldrain", "\u6f0f\u6781\u957f\u5ea6");
    model.param().set("Ltot", "Lsource+Lgate+Ldrain");
    model.param().descr("Ltot", "\u603b\u957f\u5ea6");
    model.param().set("Wtot", "Wchannel+dOx*2");
    model.param().descr("Wtot", "\u603b\u5bbd\u5ea6");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").set("quickx", "-Lsource-Lgate/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "Wtot");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").setIndex("layer", "Wtot/2", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq2", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("size", "Wchannel");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("base", "center");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").label("\u62c9\u4f38 1\uff1a\u6e90\u6781");
    model.component("comp1").geom("geom1").feature("ext1").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("wp1", 3, 4, 6, 7);
    model.component("comp1").geom("geom1").feature("ext1").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "Lsource", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext1").set("selresultshow", "all");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Si");
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").feature().duplicate("ext2", "ext1");
    model.component("comp1").geom("geom1").feature("ext2").label("\u62c9\u4f38 2\uff1a\u901a\u9053");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").clear("wp1");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("ext1", 17, 18, 19, 20);
    model.component("comp1").geom("geom1").feature("ext2").set("distance", new int[]{});
    model.component("comp1").geom("geom1").feature("ext2").set("twist", new String[]{});
    model.component("comp1").geom("geom1").feature("ext2").set("scale", new String[][]{});
    model.component("comp1").geom("geom1").feature("ext2").set("displ", new String[][]{});
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "Lgate/2", 0);
    model.component("comp1").geom("geom1").feature("ext2").set("displ", new String[][]{{"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext2").set("scale", new String[][]{{"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext2").set("twist", new String[]{"0"});
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "Lgate", 1);
    model.component("comp1").geom("geom1").feature("ext2").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext2").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext2").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext2").set("selresultshow", "dom");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").feature().duplicate("ext3", "ext2");
    model.component("comp1").geom("geom1").feature("ext3").label("\u62c9\u4f38 3\uff1a\u6f0f\u6781");
    model.component("comp1").geom("geom1").feature("ext3").selection("inputface").clear("ext1");
    model.component("comp1").geom("geom1").feature("ext3").selection("inputface").set("ext2", 33, 34, 35, 36);
    model.component("comp1").geom("geom1").feature("ext3").set("distance", new int[]{});
    model.component("comp1").geom("geom1").feature("ext3").set("twist", new String[]{});
    model.component("comp1").geom("geom1").feature("ext3").set("scale", new String[][]{});
    model.component("comp1").geom("geom1").feature("ext3").set("displ", new String[][]{});
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "Ldrain", 0);
    model.component("comp1").geom("geom1").feature("ext3").set("displ", new String[][]{{"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3").set("scale", new String[][]{{"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3").set("twist", new String[]{"0"});
    model.component("comp1").geom("geom1").feature("ext3").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").feature().duplicate("ext4", "ext1");
    model.component("comp1").geom("geom1").feature("ext4")
         .label("\u62c9\u4f38 4\uff1a\u6e90\u6781\u6c27\u5316\u7269");
    model.component("comp1").geom("geom1").feature("ext4").selection("inputface").clear("wp1");
    model.component("comp1").geom("geom1").feature("ext4").selection("inputface").set("wp1", 8, 1, 2, 5);
    model.component("comp1").geom("geom1").feature("ext4").set("selresultshow", false);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u6c27\u5316\u7269");
    model.component("comp1").geom("geom1").feature("ext4").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").feature().duplicate("ext5", "ext2");
    model.component("comp1").geom("geom1").feature("ext5")
         .label("\u62c9\u4f38 5\uff1a\u6805\u6781\u6c27\u5316\u7269");
    model.component("comp1").geom("geom1").feature("ext5").selection("inputface").clear("ext1");
    model.component("comp1").geom("geom1").feature("ext5").selection("inputface").set("ext4", 25, 26, 27, 28);
    model.component("comp1").geom("geom1").feature("ext5").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").feature().duplicate("ext6", "ext3");
    model.component("comp1").geom("geom1").feature("ext6")
         .label("\u62c9\u4f38 6\uff1a\u6f0f\u6781\u6c27\u5316\u7269");
    model.component("comp1").geom("geom1").feature("ext6").selection("inputface").clear("ext2");
    model.component("comp1").geom("geom1").feature("ext6").selection("inputface").set("ext5", 49, 50, 51, 52);
    model.component("comp1").geom("geom1").feature("ext6").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("ext6").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("adj1", "Adjacent");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("adj1").set("input", new String[]{"geom1_ext5_dom"});
    model.component("comp1").selection().duplicate("adj2", "adj1");
    model.component("comp1").selection("adj2").set("input", new String[]{"geom1_csel2_dom"});
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"adj1", "adj2"});
    model.component("comp1").selection("int1").label("\u6805\u6781");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u6e90\u6781+\u6f0f\u6781");
    model.component("comp1").selection("uni1").set("input", new String[]{"geom1_ext1_dom", "geom1_ext3_dom"});
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u6846 1\uff1ax \u8f74");
    model.component("comp1").selection("box1").set("entitydim", 1);
    model.component("comp1").selection("box1").set("ymin", -0.1);
    model.component("comp1").selection("box1").set("ymax", 0.1);
    model.component("comp1").selection("box1").set("zmin", -0.1);
    model.component("comp1").selection("box1").set("zmax", 0.1);
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection().duplicate("box2", "box1");
    model.component("comp1").selection("box2").label("\u6846 2\uff1az \u8f74");
    model.component("comp1").selection("box2").set("xmin", -0.1);
    model.component("comp1").selection("box2").set("xmax", 0.1);
    model.component("comp1").selection("box2").set("zmin", "-inf");
    model.component("comp1").selection("box2").set("zmax", "inf");

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
    model.component("comp1").material("mat1").selection().named("geom1_csel1_dom");

    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2\uff1a\u7269\u7406\u573a");
    model.param("par2").set("T0", "300[K]");
    model.param("par2").descr("T0", "\u6e29\u5ea6");
    model.param("par2").set("fmx", "0.8");
    model.param("par2").descr("fmx", "\u7eb5\u5411 DG \u6709\u6548\u8d28\u91cf");
    model.param("par2").set("fmy", "0.12");
    model.param("par2").descr("fmy", "\u6a2a\u5411 DG \u6709\u6548\u8d28\u91cf");
    model.param("par2").set("mx", "fmx*me_const");
    model.param("par2").descr("mx", "\u7eb5\u5411 DG \u6709\u6548\u8d28\u91cf");
    model.param("par2").set("my", "fmy*me_const");
    model.param("par2").descr("my", "\u6a2a\u5411 DG \u6709\u6548\u8d28\u91cf");
    model.param("par2").set("mz", "my");
    model.param("par2").descr("mz", "\u6a2a\u5411 DG \u6709\u6548\u8d28\u91cf");
    model.param("par2").set("epsrOx", "3.9");
    model.param("par2").descr("epsrOx", "\u6c27\u5316\u7269\u4ecb\u7535\u5e38\u6570");
    model.param("par2").set("mox", "0.5*me_const");
    model.param("par2").descr("mox", "\u6c27\u5316\u7269 DG \u6709\u6548\u8d28\u91cf");
    model.param("par2").set("moxstar", "0.22*me_const");
    model.param("par2").descr("moxstar", "\u6c27\u5316\u7269 DG \u6709\u6548\u8d28\u91cf");
    model.param("par2").set("PhiBox", "3.15[V]");
    model.param("par2").descr("PhiBox", "\u6c27\u5316\u7269\u52bf\u5792");
    model.param("par2").set("Nd", "1e20[cm^-3]");
    model.param("par2").descr("Nd", "\u63ba\u6742\u6d53\u5ea6");
    model.param("par2").set("cp", "1");
    model.param("par2").descr("cp", "\u63ba\u6742\u7684\u8fde\u7eed\u53c2\u6570");
    model.param("par2").set("Vd", "0.05[V]");
    model.param("par2").descr("Vd", "\u6f0f\u6781\u7535\u538b");
    model.param("par2").set("Phig", "4.5[V]");
    model.param("par2").descr("Phig", "\u6805\u6781\u91d1\u5c5e\u529f\u51fd\u6570");
    model.param("par2").set("Vg", "0.8[V]");
    model.param("par2").descr("Vg", "\u6805\u6781\u7535\u538b");

    model.component("comp1").physics("semi").prop("ModelProperties").set("Compute", "MajorityPsi");
    model.component("comp1").physics("semi").prop("ShapeProperty").set("Formulation", "FEM2DG");
    model.component("comp1").physics("semi").feature("smm1").set("minput_temperature", "T0");
    model.component("comp1").physics("semi").feature("smm1")
         .set("meDG", new String[]{"mx", "0", "0", "0", "my", "0", "0", "0", "mz"});
    model.component("comp1").physics("semi").create("ccn1", "ChargeConservation", 3);
    model.component("comp1").physics("semi").feature("ccn1").selection().named("geom1_csel2_dom");
    model.component("comp1").physics("semi").feature("ccn1").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("semi").feature("ccn1")
         .set("epsilonr", new String[]{"epsrOx", "0", "0", "0", "epsrOx", "0", "0", "0", "epsrOx"});
    model.component("comp1").physics("semi").feature("ii1").set("DGexteriorBC", "barrier");
    model.component("comp1").physics("semi").feature("ii1")
         .set("meOx", new String[]{"mox", "0", "0", "0", "mox", "0", "0", "0", "mox"});
    model.component("comp1").physics("semi").feature("ii1")
         .set("meOxStar", new String[]{"moxstar", "0", "0", "0", "moxstar", "0", "0", "0", "moxstar"});
    model.component("comp1").physics("semi").feature("ii1").set("Phi_nOx", "PhiBox");
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 3);
    model.component("comp1").physics("semi").feature("adm1").selection().named("uni1");
    model.component("comp1").physics("semi").feature("adm1").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm1").set("NDc", "Nd*cp");
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 2);
    model.component("comp1").physics("semi").feature("mc1").label("\u91d1\u5c5e\u63a5\u89e6 1\uff1a\u6e90\u6781");
    model.component("comp1").physics("semi").feature("mc1").selection().named("geom1_ext1_bnd");
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 2);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("semi").feature("mc2").label("\u91d1\u5c5e\u63a5\u89e6 2\uff1a\u6f0f\u6781");
    model.component("comp1").physics("semi").feature("mc2").selection().named("geom1_ext3_bnd");
    model.component("comp1").physics("semi").feature("mc2").set("V0", "Vd");
    model.component("comp1").physics("semi").create("term1", "Terminal", 2);
    model.component("comp1").physics("semi").feature("term1").label("\u7ec8\u7aef 1\uff1a\u6805\u6781");
    model.component("comp1").physics("semi").feature("term1").selection().named("int1");
    model.component("comp1").physics("semi").feature("term1").set("Phic", "Phig");
    model.component("comp1").physics("semi").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("semi").feature("term1").set("V0", "Vg");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 4, 8, 11, 15, 18, 21, 24);
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(9, 20, 21, 24, 27, 31);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature().duplicate("dis2", "dis1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(10, 12, 13, 15, 23, 33);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(5, 17, 26, 34);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", 1);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().named("geom1_ext2_dom");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().named("uni1");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("semie").set("useparam", true);
    model.study("std1").feature("semie").setIndex("pname", "cp", 0);
    model.study("std1").feature("semie").setIndex("plistarr", "", 0);
    model.study("std1").feature("semie").setIndex("punit", "", 0);
    model.study("std1").feature("semie").setIndex("pname", "cp", 0);
    model.study("std1").feature("semie").setIndex("plistarr", "", 0);
    model.study("std1").feature("semie").setIndex("punit", "", 0);
    model.study("std1").feature("semie").setIndex("plistarr", "10^range(-12,4,0)", 0);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").set("sweeptype", "filled");
    model.study("std1").feature("stat").setIndex("pname", "cp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "cp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "fmx", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0.8 0.5 0.3 0.2 0.1", 0);
    model.study("std1").feature("stat").setIndex("pname", "cp", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "", 1);
    model.study("std1").feature("stat").setIndex("pname", "cp", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "", 1);
    model.study("std1").feature("stat").setIndex("pname", "Vg", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0.8,-0.1,0.4)  0.295 range(0.2,-0.1,-0.01)", 1);
    model.study("std1").feature("stat").set("preusesol", "auto");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u5b50\u6d53\u5ea6 (semi)");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("expr", "semi.N");
    model.result("pg1").feature("vol1").set("unit", "1/cm^3");
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").set("colorscalemode", "logarithmic");
    model.result("pg1").feature("vol1").set("resolution", "norefine");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7a7a\u7a74\u6d53\u5ea6 (semi)");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("expr", "semi.P");
    model.result("pg2").feature("vol1").set("unit", "1/cm^3");
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("vol1").set("resolution", "norefine");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u52bf (semi)");
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("expr", "V");
    model.result("pg3").feature("vol1").set("resolution", "norefine");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u91cf\u5b50\u52bf\uff0c\u7535\u5b50 (semi)");
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("expr", "semi.VnDG");
    model.result("pg4").feature("vol1").set("smooth", "internal");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u91cf\u5b50\u52bf\uff0c\u7a7a\u7a74 (semi)");
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("expr", "semi.VpDG");
    model.result("pg5").feature("vol1").set("smooth", "internal");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").create("vol2", "Volume");
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol2").set("expr", "semi.Nnetdop");
    model.result("pg6").feature("vol2").set("unit", "1/cm^3");
    model.result("pg6").feature("vol2").set("coloring", "gradient");
    model.result("pg6").feature("vol2").set("colorscalemode", "logarithmic");
    model.result("pg6").feature("vol2").set("topcolor", "red");
    model.result("pg6").feature("vol2").set("bottomcolor", "custom");
    model.result("pg6").feature("vol2").set("custombottomcolor", new double[]{1, 0.8, 0.8});
    model.result("pg6").feature("vol2").set("smooth", "internal");
    model.result("pg6").feature("vol2").set("showsolutionparams", "on");
    model.result("pg6").feature("vol2").set("data", "parent");
    model.result("pg6").feature("vol2").set("titletype", "none");
    model.result("pg6").feature("vol2").feature().create("filt1", "Filter");
    model.result("pg6").feature("vol2").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg6").feature("vol2").feature("filt1").set("useder", true);
    model.result("pg6").feature("vol1").set("expr", "semi.Nnetdop");
    model.result("pg6").feature("vol1").set("unit", "1/cm^3");
    model.result("pg6").feature("vol1").set("coloring", "gradient");
    model.result("pg6").feature("vol1").set("colorscalemode", "logarithmic");
    model.result("pg6").feature("vol1").set("topcolor", "blue");
    model.result("pg6").feature("vol1").set("bottomcolor", "custom");
    model.result("pg6").feature("vol1").set("custombottomcolor", new double[]{0.8, 0.8, 1});
    model.result("pg6").feature("vol1").set("smooth", "internal");
    model.result("pg6").feature("vol1").set("showsolutionparams", "on");
    model.result("pg6").feature("vol1").set("data", "parent");
    model.result("pg6").feature("vol1").set("titletype", "none");
    model.result("pg6").feature("vol1").feature().create("filt1", "Filter");
    model.result("pg6").feature("vol1").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg6").feature("vol1").feature("filt1").set("useder", true);
    model.result("pg6").set("titletype", "manual");
    model.result("pg6")
         .set("title", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert: P \u578b (\u7ea2\u8272), N \u578b (\u84dd\u8272)");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").set("legendpos", "alternating");
    model.result("pg6").feature("vol2").label("P \u578b");
    model.result("pg6").feature("vol1").label("N \u578b");
    model.result("pg6").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi)");
    model.result("pg1").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("Id-Vg");
    model.result("pg7").setIndex("looplevelinput", "manualindices", 1);
    model.result("pg7").setIndex("looplevelindices", "range(1,4)", 1);
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", 0);
    model.result("pg7").set("xmax", 0.8);
    model.result("pg7").set("ymin", "1e-10");
    model.result("pg7").set("ymax", "1e-4");
    model.result("pg7").set("ylog", true);
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "semi.I0_2", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "Id", 0);
    model.result("pg7").feature("glob1").set("linewidth", 2);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("n(x)");
    model.result("pg8").setIndex("looplevelinput", "manual", 0);
    model.result("pg8").setIndex("looplevel", new int[]{6}, 0);
    model.result("pg8").set("axislimits", true);
    model.result("pg8").set("xmin", -10);
    model.result("pg8").set("xmax", 10);
    model.result("pg8").set("ymin", "1e17");
    model.result("pg8").set("ymax", "1e21");
    model.result("pg8").set("ylog", true);
    model.result("pg8").set("legendpos", "lowerright");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").selection().named("box1");
    model.result("pg8").feature("lngr1").set("expr", "semi.N");
    model.result("pg8").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "x");
    model.result("pg8").feature("lngr1").set("linewidth", 2);
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("n(z)");
    model.result("pg9").set("xmin", -1.6);
    model.result("pg9").set("xmax", 1.6);
    model.result("pg9").set("ymin", "4e15");
    model.result("pg9").set("ymax", "4e18");
    model.result("pg9").set("legendpos", "lowermiddle");
    model.result("pg9").run();
    model.result("pg9").feature("lngr1").selection().set();
    model.result("pg9").feature("lngr1").selection().named("box2");
    model.result("pg9").feature("lngr1").set("xdataexpr", "z");
    model.result("pg9").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 1);
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("vol1").active(false);
    model.result("pg1").run();
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("expr", "semi.log10N");
    model.result("pg1").feature("slc1").set("quickplane", "zx");
    model.result("pg1").feature("slc1").set("quickynumber", 1);
    model.result("pg1").feature().duplicate("slc2", "slc1");
    model.result("pg1").run();
    model.result("pg1").feature("slc2").set("quickplane", "yz");
    model.result("pg1").feature("slc2").set("quickxnumber", 7);
    model.result("pg1").feature("slc2").set("inheritplot", "slc1");
    model.result("pg1").run();
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"semi.JX", "semi.JY", "semi.JZ"});
    model.result("pg1").feature("arwv1").set("descr", "\u603b\u7535\u6d41\u5bc6\u5ea6\uff0c\u8282\u70b9\u503c");
    model.result("pg1").feature("arwv1").set("ynumber", 5);
    model.result("pg1").feature("arwv1").set("znumber", 19);
    model.result("pg1").feature("arwv1").set("color", "black");
    model.result("pg1").run();
    model.result("pg1").create("iso1", "Isosurface");
    model.result("pg1").feature("iso1").set("expr", "V");
    model.result("pg1").feature("iso1").set("number", 10);
    model.result("pg1").feature("iso1").set("colortable", "GrayScale");
    model.result("pg1").feature("iso1").set("colorlegend", false);
    model.result("pg1").feature("iso1").create("filt1", "Filter");
    model.result("pg1").run();
    model.result("pg1").feature("iso1").feature("filt1").set("expr", "-5e-11<y");
    model.result("pg1").run();

    model.title("\u7eb3\u7c73\u7ebf MOSFET \u7684\u5bc6\u5ea6\u68af\u5ea6\u4e09\u7ef4\u4eff\u771f");

    model
         .description("\u8fd9\u4e2a\u7eb3\u7c73\u7ebf MOSFET \u4e09\u7ef4\u6a21\u578b\u57fa\u4e8e\u5bc6\u5ea6\u68af\u5ea6\u7406\u8bba\uff0c\u5c06\u91cf\u5b50\u9650\u5236\u6548\u5e94\u5f15\u5165\u4f20\u7edf\u7684\u6f02\u79fb-\u6269\u6563\u516c\u5f0f\u4e2d\uff0c\u800c\u65e0\u9700\u8fc7\u9ad8\u7684\u8ba1\u7b97\u6210\u672c\u3002\u5176\u4e2d\u901a\u8fc7\u51e0\u4f55\u57df\u6765\u663e\u5f0f\u6a21\u62df\u6c27\u5316\u5c42\uff0c\u5e76\u4f7f\u7528\u4e13\u95e8\u7684\u8fb9\u754c\u6761\u4ef6\u6765\u8003\u8651\u7845-\u6c27\u5316\u5c42\u754c\u9762\u7684\u91cf\u5b50\u9650\u5236\u3002\u5bc6\u5ea6\u68af\u5ea6\u6709\u6548\u8d28\u91cf\u5177\u6709\u5404\u5411\u5f02\u6027\u3002\u672c\u4f8b\u4f7f\u7528\u5404\u79cd\u9009\u62e9\u5b9e\u7528\u5de5\u5177\u6765\u7b80\u5316\u7269\u7406\u573a\u8bbe\u7f6e\u548c\u7ed8\u56fe\u9009\u62e9\u7684\u6307\u6d3e\u3002\u4eff\u771f\u7ed3\u679c\u4e0e\u53c2\u8003\u6587\u732e\u4e2d\u53d1\u8868\u7684 Id-Vg \u66f2\u7ebf\u548c\u7535\u5b50\u5bc6\u5ea6\u5206\u5e03\u56fe\u9ad8\u5ea6\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("nanowire_density_gradient_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
