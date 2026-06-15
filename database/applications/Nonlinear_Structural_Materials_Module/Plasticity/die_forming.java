/*
 * die_forming.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:33 by COMSOL 6.3.0.290. */
public class die_forming {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("sigma_y0", "124[MPa]");
    model.param().descr("sigma_y0", "\u521d\u59cb\u5c48\u670d\u5e94\u529b");
    model.param().set("U_p", "40[mm]");
    model.param().descr("U_p", "\u51b2\u5934\u4f4d\u79fb");
    model.param().set("R0", "0.69");
    model.param().descr("R0", "Lankford \u7cfb\u6570 r0");
    model.param().set("R45", "0.69");
    model.param().descr("R45", "Lankford \u7cfb\u6570 r45");
    model.param().set("R90", "0.69");
    model.param().descr("R90", "Lankford \u7cfb\u6570 90");
    model.param().set("F", "R0/(R90*(R0+1))/sigma_y0^2");
    model.param().descr("F", "Hill \u7cfb\u6570");
    model.param().set("G", "1/(R0+1)/sigma_y0^2");
    model.param().descr("G", "Hill \u7cfb\u6570");
    model.param().set("H", "R0/(R0+1)/sigma_y0^2");
    model.param().descr("H", "Hill \u7cfb\u6570");
    model.param().set("L", "0");
    model.param().descr("L", "Hill \u7cfb\u6570");
    model.param().set("M", "0");
    model.param().descr("M", "Hill \u7cfb\u6570");
    model.param().set("N", "0");
    model.param().descr("N", "Hill \u7cfb\u6570");
    model.param().set("p", "0");
    model.param().descr("p", "\u8f7d\u8377\u53c2\u6570");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 42, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 88.5, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 42, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 88.5, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 2, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 257.5, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 2, 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 257.5, 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 40, 4, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("pol1", 2);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 16);
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("fil2").selection("pointinsketch").set("fil1", 3, 5);
    model.component("comp1").geom("geom1").feature("fil2").set("radius", 18);
    model.component("comp1").geom("geom1").run("fil2");
    model.component("comp1").geom("geom1").create("thi1", "Thicken2D");
    model.component("comp1").geom("geom1").feature("thi1").selection("input").set("fil2");
    model.component("comp1").geom("geom1").feature("thi1").set("offset", "asymmetric");
    model.component("comp1").geom("geom1").feature("thi1").set("upthick", 4);
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pol1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("thi1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("\u51b2\u5934");
    model.component("comp1").geom("geom1").run("thi1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").nodeGroup("grp1").remove("pol2", false);
    model.component("comp1").geom("geom1").feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 86, 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 86, 2, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", -40, 2, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 260, 3, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", -40, 3, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 260, 4, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 4, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 386, 5, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 5, 1);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("fil3", "Fillet");
    model.component("comp1").geom("geom1").feature("fil3").selection("point").set("pol2", 3, 5);
    model.component("comp1").geom("geom1").feature("fil3").set("radius", 16);
    model.component("comp1").geom("geom1").run("fil3");
    model.component("comp1").geom("geom1").create("fil4", "Fillet");
    model.component("comp1").geom("geom1").feature("fil4").selection("point").set("fil3", 3, 5);
    model.component("comp1").geom("geom1").feature("fil4").set("radius", 16);
    model.component("comp1").geom("geom1").run("fil4");
    model.component("comp1").geom("geom1").create("thi2", "Thicken2D");
    model.component("comp1").geom("geom1").feature("thi2").selection("input").set("fil4");
    model.component("comp1").geom("geom1").feature("thi2").set("offset", "asymmetric");
    model.component("comp1").geom("geom1").feature("thi2").set("downthick", 4);
    model.component("comp1").geom("geom1").nodeGroup().create("grp2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("pol2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("fil3");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("fil4");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("thi2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").label("\u6a21\u5177");
    model.component("comp1").geom("geom1").run("thi2");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").nodeGroup("grp2").remove("r1", false);
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{80, 20});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{300, 2});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{380, 2});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").nodeGroup().create("grp3");
    model.component("comp1").geom("geom1").nodeGroup("grp3").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("r1");
    model.component("comp1").geom("geom1").nodeGroup("grp3").label("\u538b\u8fb9\u5668");
    model.component("comp1").geom("geom1").nodeGroup().create("grp4");
    model.component("comp1").geom("geom1").nodeGroup("grp4").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("r2");
    model.component("comp1").geom("geom1").nodeGroup("grp4").label("\u576f\u4ef6");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("pairtype", "contact");
    model.component("comp1").geom("geom1").run("fin");

    model.func().create("int1", "Interpolation");
    model.func("int1").label("\u6307\u5b9a\u51b2\u5934\u4f4d\u79fb");
    model.func("int1").set("funcname", "U_punch");
    model.func("int1").setIndex("table", 0, 0, 0);
    model.func("int1").setIndex("table", 0, 0, 1);
    model.func("int1").setIndex("table", 1, 1, 0);
    model.func("int1").setIndex("table", "-U_p", 1, 1);
    model.func("int1").setIndex("table", 2, 2, 0);
    model.func("int1").setIndex("table", 0, 2, 1);
    model.func("int1").setIndex("argunit", 1, 0);
    model.func("int1").setIndex("fununit", "m", 0);
    model.func().create("int2", "Interpolation");
    model.func("int2").label("\u786c\u5316\u51fd\u6570");
    model.func("int2").set("funcname", "sigma_h");
    model.func("int2").setIndex("table", 0, 0, 0);
    model.func("int2").setIndex("table", 0, 0, 1);
    model.func("int2").setIndex("table", 0.02, 1, 0);
    model.func("int2").setIndex("table", 43, 1, 1);
    model.func("int2").setIndex("table", 0.05, 2, 0);
    model.func("int2").setIndex("table", 76, 2, 1);
    model.func("int2").setIndex("table", 0.1, 3, 0);
    model.func("int2").setIndex("table", 103, 3, 1);
    model.func("int2").setIndex("table", 0.15, 4, 0);
    model.func("int2").setIndex("table", 115, 4, 1);
    model.func("int2").setIndex("table", 0.2, 5, 0);
    model.func("int2").setIndex("table", 127, 5, 1);
    model.func("int2").setIndex("table", 0.3, 6, 0);
    model.func("int2").setIndex("table", 129, 6, 1);
    model.func("int2").setIndex("table", 0.4, 7, 0);
    model.func("int2").setIndex("table", 129.3, 7, 1);
    model.func("int2").setIndex("table", 0.5, 8, 0);
    model.func("int2").setIndex("table", 129.4, 8, 1);
    model.func("int2").setIndex("argunit", 1, 0);
    model.func("int2").setIndex("fununit", "MPa", 0);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(3);
    model.component("comp1").cpl("intop1").set("method", "summation");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(26, 28, 30, 34, 35, 37, 39);
    model.component("comp1").selection("sel1").label("\u51b2\u5934\u63a5\u89e6\u5bf9");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u6a21\u5177\u63a5\u89e6\u5bf9");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(3, 5, 7, 8, 11, 14, 16, 18, 19);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u538b\u8fb9\u5668\u63a5\u89e6\u5bf9");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(42);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u576f\u4ef6\u4e0a\u4fa7\u63a5\u89e6\u5bf9");
    model.component("comp1").selection("sel4").geom(1);
    model.component("comp1").selection("sel4").set(23);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u576f\u4ef6\u4e0b\u4fa7\u63a5\u89e6\u5bf9");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").set(22);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").set(1);
    model.component("comp1").selection("sel6").label("\u6a21\u5177");
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").set(3);
    model.component("comp1").selection("sel7").label("\u51b2\u5934");
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("\u538b\u8fb9\u5668");
    model.component("comp1").selection("sel8").set(4);
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").set(2);
    model.component("comp1").selection("sel9").label("\u576f\u4ef6");

    model.component("comp1").cpl().create("genproj1", "GeneralProjection");
    model.component("comp1").cpl("genproj1").selection().set(2);
    model.component("comp1").cpl("genproj1").set("srcmap", new String[]{"R", "Z"});
    model.component("comp1").cpl("genproj1").set("dstmap", new String[]{"R"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("th", "genproj1(1)");
    model.component("comp1").variable("var1").descr("th", "\u539a\u5ea6");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().set(2);

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u6a21\u5177\u548c\u538b\u8fb9\u5668");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel6", "sel8"});

    model.component("comp1").pair("ap1").manualSelection(true);
    model.component("comp1").pair("ap1").source().named("sel2");
    model.component("comp1").pair("ap1").destination().named("sel5");
    model.component("comp1").pair("ap1").label("\u6a21\u5177-\u576f\u4ef6");
    model.component("comp1").pair("ap2").manualSelection(true);
    model.component("comp1").pair("ap2").source().named("sel1");
    model.component("comp1").pair("ap2").destination().named("sel4");
    model.component("comp1").pair("ap2").label("\u51b2\u5934-\u576f\u4ef6");
    model.component("comp1").pair("ap3").manualSelection(true);
    model.component("comp1").pair("ap3").source().named("sel3");
    model.component("comp1").pair("ap3").destination().named("sel4");
    model.component("comp1").pair("ap3").label("\u538b\u8fb9\u5668-\u576f\u4ef6");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").label("Aluminum");
    model.component("comp1").material("mat2").set("family", "aluminum");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material("mat2").selection().named("sel9");

    return model;
  }

  public static Model run2(Model model) {

    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1").selection().named("sel9");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1").set("YieldFunction", "hill");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("HillPlasticityOptions", "Hillcoefficients");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("IsotropicHardeningModel", "HardeningFunction");
    model.component("comp1").physics("solid").feature("dcnt1").set("ContactMethodCtrl", "AugmentedLagrange");
    model.component("comp1").physics("solid").feature("dcnt1").set("SolutionMethod", "coupled");
    model.component("comp1").physics("solid").feature("dcnt1").set("penaltyCtrlAuglag", "ManualTuning");
    model.component("comp1").physics("solid").feature("dcnt1").set("pfm", "5e-4");
    model.component("comp1").physics("solid").feature("dcnt1").set("automaticQuadrature", false);
    model.component("comp1").physics("solid").feature("dcnt1").set("integrationOrder", 6);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("uni1");
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().named("sel7");
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "U_punch(p)", 2);

    model.component("comp1").material("mat2").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"F", "G", "H", "L", "M", "N"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("sigmagh", new String[]{"sigma_h(solid.epe)"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("sel9");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(21);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(23);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 400);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(14, 16, 18, 19, 35, 37, 39);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", 1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmin", 0.001);
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(41, 43);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").showAutoSequences("all");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "sigma_y0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "sigma_y0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "p", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.01,2)", 0);

    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("s1").feature("pDef").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("pDef").set("pinitstep", "1e-5");
    model.sol("sol1").feature("s1").feature("pDef").set("pminstep", "1e-5");
    model.sol("sol1").feature("s1").feature("pDef").set("pmaxstep", 0.01);
    model.sol("sol1").feature("s1").feature("pDef").set("porder", "linear");
    model.sol("sol1").feature("s1").feature("pDef").create("st1", "StopCondition");
    model.sol("sol1").feature("s1").feature("pDef").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s1").feature("pDef").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s1").feature("pDef").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s1").feature("pDef").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s1").feature("pDef").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s1").feature("pDef").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s1").feature("pDef").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s1").feature("pDef").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s1").feature("pDef").feature("st1")
         .setIndex("stopcondarr", "comp1.solid.gapmin_ap2>1e-4", 0);
    model.sol("sol1").feature("s1").feature("pDef").feature("st1").set("storestopcondsol", "stepafter");

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1solidrev");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").label("\u5e94\u529b, 3D (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").run();

    model.study("std1").feature("stat").set("plot", true);
    model.study("std1").feature("stat").set("probefreq", "psteps");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").label("\u6b8b\u4f59\u5e94\u529b");
    model.result("pg1").run();
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").label("\u7814\u7a76 1/\u89e3 1\uff0c\u576f\u4ef6");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(2);
    model.result().dataset("dset1solidrev").set("data", "dset2");
    model.result().dataset("dset1solidrev").set("startangle", 0);
    model.result().dataset("dset1solidrev").set("revangle", 360);
    model.result("pg2").run();
    model.result("pg2").label("\u53d8\u5f62\u5f62\u72b6\uff0c\u4e09\u7ef4");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("descr", "\u53d8\u5f62\u5f62\u72b6\uff0c\u4e09\u7ef4");
    model.result("pg2").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("mtrl1").set("material", "mat2");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 109, 0);
    model.result("pg3").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.epeGp"});
    model.result("pg3").feature("surf1").set("inheritplot", "none");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortabletype", "discrete");
    model.result("pg3").feature("surf1").set("bandcount", 11);
    model.result("pg3").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg3").feature("surf1").set("descractive", true);
    model.result("pg3").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg3").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u576f\u4ef6\u539a\u5ea6");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "th");
    model.result("pg4").feature("surf1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u51b2\u538b\u529b");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "-intop1(solid.RFz)", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "kN", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u51b2\u538b\u529b", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "-U_punch(p)");
    model.result("pg5").feature("glob1").set("xdatadescractive", true);
    model.result("pg5").feature("glob1").set("xdatadescr", "\u5782\u76f4\u4f4d\u79fb");
    model.result("pg5").feature("glob1").set("legend", false);
    model.result("pg5").run();
    model.result("pg5").run();

    model.title("\u51b2\u538b\u6210\u5f62");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u4f7f\u7528\u5706\u67f1\u5f62\u521a\u6027\u51b2\u5934\u8fdb\u884c\u5851\u6027\u91d1\u5c5e\u677f\u7684\u6210\u5f62\uff0c\u4ee5\u53ca\u8fd9\u4e2a\u8fc7\u7a0b\u4e2d\u7684\u56de\u5f39\u73b0\u8c61\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("die_forming.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
