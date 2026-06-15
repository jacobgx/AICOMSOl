/*
 * pipe_fitting_llsw.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:49 by COMSOL 6.3.0.290. */
public class pipe_fitting_llsw {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\LiveLink_for_SOLIDWORKS\\Tutorials,_LiveLink_Interface");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("cad1", "LiveLinkSOLIDWORKS");
    model.component("comp1").geom("geom1").feature("cad1").updateCadParamTable(false, false);
    model.component("comp1").geom("geom1").feature("cad1").importData();

    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").geom("geom1").run("cad1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zx");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);
    model.component("comp2").geom("geom2").axisymmetric(true);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").lengthUnit("mm");
    model.component("comp2").geom("geom2").create("cro1", "CrossSection");
    model.component("comp2").geom("geom2").feature("cro1").set("selfrom3d", true);
    model.component("comp2").geom("geom2").run("cro1");
    model.component("comp2").geom("geom2").run("cro1");
    model.component("comp2").geom("geom2").create("uni1", "Union");
    model.component("comp2").geom("geom2").feature("uni1").selection("input").named("cro1_cad1_Male_fitting");
    model.component("comp2").geom("geom2").feature("fin").set("action", "assembly");
    model.component("comp2").geom("geom2").feature("fin").set("createpairs", false);
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").material().create("mat1", "Common");
    model.component("comp2").material("mat1").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan");
    model.component("comp2").material("mat1").propertyGroup().create("Lame", "Lam\u00e9 parameters");
    model.component("comp2").material("mat1").label("Structural steel");
    model.component("comp2").material("mat1").set("family", "custom");
    model.component("comp2").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp2").material("mat1").set("diffuse", "custom");
    model.component("comp2").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat1").set("noise", true);
    model.component("comp2").material("mat1").set("fresnel", 0.9);
    model.component("comp2").material("mat1").set("roughness", 0.3);
    model.component("comp2").material("mat1").set("metallic", 0);
    model.component("comp2").material("mat1").set("pearl", 0);
    model.component("comp2").material("mat1").set("diffusewrap", 0);
    model.component("comp2").material("mat1").set("clearcoat", 0);
    model.component("comp2").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp2").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp2").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat1").propertyGroup("Enu").set("E", "");
    model.component("comp2").material("mat1").propertyGroup("Enu").set("nu", "");
    model.component("comp2").material("mat1").propertyGroup("Enu").set("E", "");
    model.component("comp2").material("mat1").propertyGroup("Enu").set("nu", "");
    model.component("comp2").material("mat1").propertyGroup("Enu").set("E", "200e9[Pa]");
    model.component("comp2").material("mat1").propertyGroup("Enu").set("nu", "0.30");
    model.component("comp2").material("mat1").propertyGroup("Murnaghan").set("l", "");
    model.component("comp2").material("mat1").propertyGroup("Murnaghan").set("m", "");
    model.component("comp2").material("mat1").propertyGroup("Murnaghan").set("n", "");
    model.component("comp2").material("mat1").propertyGroup("Murnaghan").set("l", "");
    model.component("comp2").material("mat1").propertyGroup("Murnaghan").set("m", "");
    model.component("comp2").material("mat1").propertyGroup("Murnaghan").set("n", "");
    model.component("comp2").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp2").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp2").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp2").material("mat1").propertyGroup("Lame").label("Lam\u00e9 parameters");
    model.component("comp2").material("mat1").propertyGroup("Lame").set("lambLame", "");
    model.component("comp2").material("mat1").propertyGroup("Lame").set("muLame", "");
    model.component("comp2").material("mat1").propertyGroup("Lame").set("lambLame", "");
    model.component("comp2").material("mat1").propertyGroup("Lame").set("muLame", "");
    model.component("comp2").material("mat1").propertyGroup("Lame").set("lambLame", "1.15e11[Pa]");
    model.component("comp2").material("mat1").propertyGroup("Lame").set("muLame", "7.69e10[Pa]");
    model.component("comp2").material("mat1").set("family", "custom");
    model.component("comp2").material("mat1").set("lighting", "cooktorrance");
    model.component("comp2").material("mat1").set("fresnel", 0.9);
    model.component("comp2").material("mat1").set("roughness", 0.3);
    model.component("comp2").material("mat1").set("metallic", 0);
    model.component("comp2").material("mat1").set("pearl", 0);
    model.component("comp2").material("mat1").set("diffusewrap", 0);
    model.component("comp2").material("mat1").set("clearcoat", 0);
    model.component("comp2").material("mat1").set("reflectance", 0);
    model.component("comp2").material("mat1").set("ambient", "custom");
    model.component("comp2").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat1").set("diffuse", "custom");
    model.component("comp2").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat1").set("specular", "custom");
    model.component("comp2").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp2").material("mat1").set("noisecolor", "custom");
    model.component("comp2").material("mat1").set("customnoisecolor", new double[]{0, 0, 0});
    model.component("comp2").material("mat1").set("noisescale", 0);
    model.component("comp2").material("mat1").set("noise", "off");
    model.component("comp2").material("mat1").set("noisefreq", 1);
    model.component("comp2").material("mat1").set("normalnoisebrush", "0");
    model.component("comp2").material("mat1").set("normalnoisetype", "0");
    model.component("comp2").material("mat1").set("alpha", 1);

    model.component("comp2").physics().create("solid", "SolidMechanics", "geom2");

    model.component("comp2").pair().create("p1", "Contact");
    model.component("comp2").pair("p1").source().named("geom2_cro1_cad1_Faceset2pipe_bnd");
    model.component("comp2").pair("p1").destination().named("geom2_cro1_cad1_Faceset2adaptor_bnd");
    model.component("comp2").pair().create("p2", "Contact");
    model.component("comp2").pair("p2").source().named("geom2_cro1_cad1_Faceset1housing_bnd");
    model.component("comp2").pair("p2").destination().named("geom2_cro1_cad1_Faceset1adaptor_bnd");
    model.component("comp2").pair().create("p3", "Contact");
    model.component("comp2").pair("p3").source().named("geom2_cro1_cad1_Faceset2housing_bnd");
    model.component("comp2").pair("p3").destination().named("geom2_cro1_cad1_Faceset1pipe_bnd");

    model.component("comp2").physics("solid").create("cnt1", "SolidContact", 1);
    model.component("comp2").physics("solid").feature("cnt1").set("pairs", new String[]{"p1", "p2", "p3"});
    model.component("comp2").physics("solid").feature("cnt1").set("ContactMethodCtrl", "AugmentedLagrange");
    model.component("comp2").physics("solid").feature("cnt1").create("fric1", "Friction", 1);
    model.component("comp2").physics("solid").feature("cnt1").feature("fric1").set("mu_fric", "mu");
    model.component("comp2").physics("solid").feature("cnt1").feature("fric1")
         .set("ContactPreviousStep", "InContact");
    model.component("comp2").physics("solid").create("roll1", "Roller", 1);
    model.component("comp2").physics("solid").feature("roll1").selection().set(14);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("d0", "8.75[mm]", "Thread mean diameter");
    model.param().set("d1", "5.8[mm]", "Nominal thread diameter");
    model.param().set("p", "1.5[mm]", "Pitch");
    model.param().set("beta", "30[deg]", "Semi thread angle");
    model.param().set("T", "1000[N*mm]", "Applied torque");
    model.param().set("A", "p/(pi*d0)", "Tangent of the helix angle");
    model.param().set("mu", "0.15", "Friction coefficient");
    model.param()
         .set("W", "2*T*(1-mu*A*sec(beta))/(d0*(A+mu*sec(beta))+mu*sec(0.7854)*d1*(1-mu*A*sec(beta)))", "Axial preload");
    model.param().set("k", "1e5[N/m^3]", "Spring coefficient");

    model.component("comp2").cpl().create("intop1", "Integration");
    model.component("comp2").cpl("intop1").set("axisym", true);
    model.component("comp2").cpl("intop1").selection().named("geom2_cro1_cad1_Pretension_domain_dom");
    model.component("comp2").cpl("intop1").set("frame", "material");

    model.component("comp2").physics("solid").create("ge1", "GlobalEquations", -1);
    model.component("comp2").physics("solid").feature("ge1").setIndex("name", "eZ", 0, 0);
    model.component("comp2").physics("solid").feature("ge1")
         .setIndex("equation", "intop1(solid.SZ/0.2[mm])+W", 0, 0);
    model.component("comp2").physics("solid").feature("ge1").setIndex("initialValueU", 0.1, 0, 0);
    model.component("comp2").physics("solid").feature("ge1").set("DependentVariableQuantity", "strain");
    model.component("comp2").physics("solid").feature("ge1").set("SourceTermQuantity", "force");
    model.component("comp2").physics("solid").feature("lemm1").create("iss1", "InitialStressandStrain", 2);
    model.component("comp2").physics("solid").feature("lemm1").feature("iss1").selection().set();
    model.component("comp2").physics("solid").feature("lemm1").feature("iss1").selection()
         .named("geom2_cro1_cad1_Pretension_domain_dom");
    model.component("comp2").physics("solid").feature("lemm1").feature("iss1")
         .set("eil", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "eZ"});
    model.component("comp2").physics("solid").create("spf1", "SpringFoundation1", 1);
    model.component("comp2").physics("solid").feature("spf1").selection().set(5, 63);
    model.component("comp2").physics("solid").feature("spf1")
         .set("kPerArea", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "k"});

    model.component("comp2").mesh("mesh2").automatic(false);
    model.component("comp2").mesh("mesh2").feature("ftri1").create("size1", "Size");
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").selection().geom("geom2", 1);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").selection()
         .named("geom2_cro1_cad1_Faceset2adaptor_bnd");
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hmax", 0.1);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").activate("solid", true);

    model.component("comp1").geom("geom1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "d0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "d0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "T", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "100 500 1e3 5e3", 0);
    model.study("std1").feature("stat").setIndex("punit", "N*mm", 0);

    model.sol().create("sol1");
    model.sol("sol1").study("std1");

    model.study("std1").feature("stat").set("notlistsolnum", 1);
    model.study("std1").feature("stat").set("notsolnum", "1");
    model.study("std1").feature("stat").set("listsolnum", 1);
    model.study("std1").feature("stat").set("solnum", "1");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp2_solid_Tt_p1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_solid_Tt_p2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_solid_Tn_p3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_solid_Tn_p2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_solid_Tt_p3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_solid_Tn_p1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_solid_Tt_p1").set("scaleval", "10000000");
    model.sol("sol1").feature("v1").feature("comp2_solid_Tt_p2").set("scaleval", "10000000");
    model.sol("sol1").feature("v1").feature("comp2_u").set("scaleval", "1e-2*0.041816663932385346");
    model.sol("sol1").feature("v1").feature("comp2_solid_Tn_p3").set("scaleval", "100000000");
    model.sol("sol1").feature("v1").feature("comp2_solid_Tn_p2").set("scaleval", "100000000");
    model.sol("sol1").feature("v1").feature("comp2_solid_Tt_p3").set("scaleval", "10000000");
    model.sol("sol1").feature("v1").feature("comp2_solid_Tn_p1").set("scaleval", "100000000");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").create("p1", "Parametric");
    model.sol("sol1").feature("s1").feature().remove("pDef");
    model.sol("sol1").feature("s1").feature("p1").set("porder", "constant");
    model.sol("sol1").feature("s1").feature("p1").set("control", "stat");
    model.sol("sol1").feature("s1").set("control", "stat");
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").create("se1", "Segregated");
    model.sol("sol1").feature("s1").feature("se1").feature().remove("ssDef");
    model.sol("sol1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp2_u", "comp2_ODE1"});
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subdtech", "ddog");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subtermauto", "itertol");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subiter", 7);
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subntolfact", 1);
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("linsolver", "dDef");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").label("\u56fa\u4f53\u529b\u5b66");
    model.sol("sol1").feature("s1").feature("se1").create("ls1", "LumpedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ls1")
         .set("segvar", new String[]{"comp2_solid_Tn_p2", "comp2_solid_Tt_p2", "comp2_solid_Tn_p3", "comp2_solid_Tt_p3", "comp2_solid_Tn_p1", "comp2_solid_Tt_p1"});
    model.sol("sol1").feature("s1").feature("se1").set("maxsegiter", 15);
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subtermauto", "tol");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.mises"});
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "rev1");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.mises"});
    model.result("pg2").label("\u5e94\u529b, 3D (solid)");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("rev1").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", "1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u63a5\u89e6\u529b (solid)");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg3").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "gray");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def").set("scale", 1);
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().set(1, 2, 3, 4, 5);
    model.result("pg3").create("arwl1", "ArrowLine");
    model.result("pg3").feature("arwl1").set("expr", new String[]{"solid.cnt1.Tnr", "solid.cnt1.Tnz"});
    model.result("pg3").feature("arwl1").set("placement", "gausspoints");
    model.result("pg3").feature("arwl1").set("arrowbase", "head");
    model.result("pg3").feature("arwl1").set("gporder", 4);
    model.result("pg3").feature("arwl1").label("\u63a5\u89e6 1a, \u538b\u529b");
    model.result("pg3").feature().move("surf1", 1);
    model.result("pg3").feature("arwl1").set("inheritplot", "none");
    model.result("pg3").feature("arwl1").set("color", "green");
    model.result("pg3").feature("arwl1").create("col", "Color");
    model.result("pg3").feature("arwl1").feature("col").set("expr", "comp2.solid.cnt1.Tn");
    model.result("pg3").feature("arwl1").feature("col").set("coloring", "gradient");
    model.result("pg3").feature("arwl1").feature("col").set("topcolor", "green");
    model.result("pg3").feature("arwl1").feature("col").set("bottomcolor", "custom");
    model.result("pg3").feature("arwl1").feature("col")
         .set("custombottomcolor", new double[]{0.509804, 0.54902, 0.509804});
    model.result("pg3").feature("arwl1").create("def", "Deform");
    model.result("pg3").feature("arwl1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg3").feature("arwl1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").feature("arwl1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("arwl1").feature("def").set("scale", 1);
    model.result("pg3").set("legendpos", "rightdouble");
    model.result("pg3").create("arwl2", "ArrowLine");
    model.result("pg3").feature("arwl2").set("expr", new String[]{"solid.cnt1.Ttr", "solid.cnt1.Ttz"});
    model.result("pg3").feature("arwl2").set("placement", "gausspoints");
    model.result("pg3").feature("arwl2").set("arrowbase", "tail");
    model.result("pg3").feature("arwl2").set("gporder", 4);
    model.result("pg3").feature("arwl2").label("\u63a5\u89e6 1a, \u6469\u64e6\u529b");
    model.result("pg3").feature().move("surf1", 2);
    model.result("pg3").feature("arwl2").set("inheritplot", "none");
    model.result("pg3").feature("arwl2").set("color", "magenta");
    model.result("pg3").feature("arwl2").create("col", "Color");
    model.result("pg3").feature("arwl2").feature("col").set("expr", "comp2.solid.cnt1.Ttnorm");
    model.result("pg3").feature("arwl2").feature("col").set("coloring", "gradient");
    model.result("pg3").feature("arwl2").feature("col").set("topcolor", "magenta");
    model.result("pg3").feature("arwl2").feature("col").set("bottomcolor", "custom");
    model.result("pg3").feature("arwl2").feature("col")
         .set("custombottomcolor", new double[]{0.54902, 0.509804, 0.54902});
    model.result("pg3").feature("arwl2").create("def", "Deform");
    model.result("pg3").feature("arwl2").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg3").feature("arwl2").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").feature("arwl2").feature("def").set("scaleactive", true);
    model.result("pg3").feature("arwl2").feature("def").set("scale", 1);

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("unit", "MPa");
    model.result("pg2").run();
    model.result("pg2").set("showlegendsmaxmin", true);

    model.title("\u5bf9\u6765\u81ea CAD \u6587\u4ef6\u7684\u7ba1\u4ef6\u8fdb\u884c\u5e94\u529b\u5206\u6790");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6a21\u62df\u4e00\u4e2a\u4e09\u7ef4\u87ba\u7eb9\u7ba1\u4ef6\uff0c\u6f14\u793a\u5982\u4f55\u8bbe\u7f6e\u901a\u8fc7\u63a5\u89e6\u6267\u884c\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u5e94\u529b\u5206\u6790\u3002\n\n\u672c\u4f8b\u8ba8\u8bba\u4e09\u7ef4 SOLIDWORKS\u00ae \u51e0\u4f55\uff0c\u4ee5\u53ca\u6307\u5b9a\u63a5\u89e6\u9762\u7684\u9009\u62e9\u4e0e COMSOL Multiphysics\u00ae \u4e2d\u4e8c\u7ef4\u51e0\u4f55\u7684\u540c\u6b65\u3002\u5728 COMSOL Multiphysics\u00ae \u4e2d\u5b9a\u4e49\u622a\u9762\uff0c\u6839\u636e SOLIDWORKS\u00ae \u4e09\u7ef4\u5bf9\u8c61\u521b\u5efa\u4e8c\u7ef4\u51e0\u4f55\uff0c\u5728\u540c\u6b65\u540e\uff0c\u65e0\u9700\u5728 COMSOL Multiphysics\u00ae \u73af\u5883\u4e2d\u91cd\u65b0\u5b9a\u4e49\u4efb\u4f55\u9009\u62e9\u3002\u8fd9\u610f\u5473\u7740\u60a8\u53ef\u4ee5\u6839\u636e\u4e8c\u7ef4\u5206\u6790\u8fd0\u884c\u53c2\u6570\u5316\u7814\u7a76\u6216\u4f18\u5316\u4e09\u7ef4 CAD \u8bbe\u8ba1\u3002\n\n\u5728\u76f8\u5173\u535a\u5ba2\u6587\u7ae0\u4e2d\u4e86\u89e3\u6709\u5173\u6b64\u6559\u5b66\u6a21\u578b\u7684\u66f4\u591a\u4fe1\u606f\uff1a\"\u6839\u636e CAD \u88c5\u914d\u7814\u7a76\u87ba\u7eb9\u7ba1\u4ef6\u8bbe\u8ba1\u4e2d\u7684\u5e94\u529b\"\n\nSOLIDWORKS \u662f Dassault Syst\u00e8mes SOLIDWORKS Corp. \u7684\u6ce8\u518c\u5546\u6807\u3002");

    model.label("pipe_fitting_llsw.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
