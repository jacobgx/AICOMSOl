/*
 * campbell_plot_comparison.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:46 by COMSOL 6.3.0.290. */
public class campbell_plot_comparison {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("rotsld", "SolidRotor", "geom1");
    model.component("comp1").physics().create("srotf", "SolidRotorFixedFrame", "geom1");
    model.component("comp1").physics().create("rotbm", "BeamRotor", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("ftplistmethod", "manual");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/rotsld", true);
    model.study("std1").feature("stat").setSolveFor("/physics/srotf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/rotbm", true);
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("ftplistmethod", "manual");
    model.study("std1").feature("eig").set("geometricNonlinearity", true);
    model.study("std1").feature("eig").set("linpsolnum", "auto");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/rotsld", true);
    model.study("std1").feature("eig").setSolveFor("/physics/srotf", true);
    model.study("std1").feature("eig").setSolveFor("/physics/rotbm", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("fr", "1000[rpm]", "\u8f74\u7684\u89d2\u901f\u5ea6");
    model.param().set("kb", "1e8[N/m]", "\u8f74\u627f\u521a\u5ea6");
    model.param().label("\u53c2\u6570\uff1a\u901a\u7528");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("x1", "0[m]", "\u7ad9 1 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x2", "0.01[m]", "\u7ad9 2 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x3", "0.015[m]", "\u7ad9 3 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x4", "0.025[m]", "\u7ad9 4 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x5", "0.0375[m]", "\u7ad9 5 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x6", "0.05[m]", "\u7ad9 6 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x7", "0.06[m]", "\u7ad9 7 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x8", "0.09[m]", "\u7ad9 8 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x9", "0.095[m]", "\u7ad9 9 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x10", "0.11[m]", "\u7ad9 10 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x11", "0.125[m]", "\u7ad9 11 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x12", "0.135[m]", "\u7ad9 12 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x13", "0.145[m]", "\u7ad9 13 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x14", "0.155[m]", "\u7ad9 14 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x15", "0.17[m]", "\u7ad9 15 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x16", "0.185[m]", "\u7ad9 16 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x17", "0.2[m]", "\u7ad9 17 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x18", "0.21[m]", "\u7ad9 18 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x19", "0.225[m]", "\u7ad9 19 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x20", "0.265[m]", "\u7ad9 20 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x21", "0.325[m]", "\u7ad9 21 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x22", "0.365[m]", "\u7ad9 22 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x23", "0.375[m]", "\u7ad9 23 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x24", "0.395[m]", "\u7ad9 24 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x25", "0.405[m]", "\u7ad9 25 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x26", "0.42[m]", "\u7ad9 26 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x27", "0.435[m]", "\u7ad9 27 \u7684\u4f4d\u7f6e");
    model.param("par2").set("x28", "0.465[m]", "\u7ad9 28 \u7684\u4f4d\u7f6e");
    model.param("par2").set("xTol", "1e-5[m]", "\u8f74\u5411\u6b65\u8ddd\u5bb9\u5dee");
    model.param("par2").label("\u53c2\u6570\uff1a\u5de5\u4f4d");
    model.param().create("par3");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("d_1_2", "0.03[m]", "\u7ad9 1 \u548c 2 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_2_3", "0.04[m]", "\u7ad9 2 \u548c 3 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_3_4", "0.03[m]", "\u7ad9 3 \u548c 4 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_4_5", "0.05[m]", "\u7ad9 4 \u548c 5 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_5_6", "0.05[m]", "\u7ad9 5 \u548c 6 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_6_7", "0.08[m]", "\u7ad9 6 \u548c 7 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_7_8", "0.4[m]", "\u7ad9 7 \u548c 8 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_8_9", "0.2[m]", "\u7ad9 8 \u548c 9 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_9_10", "0.05[m]", "\u7ad9 9 \u548c 10 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_10_11", "0.05[m]", "\u7ad9 10 \u548c 11 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_11_12", "0.06[m]", "\u7ad9 11 \u548c 12 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_12_13", "0.07[m]", "\u7ad9 12 \u548c 13 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_13_14", "0.08[m]", "\u7ad9 13 \u548c 14 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_14_15", "0.05[m]", "\u7ad9 14 \u548c 15 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_15_16", "0.05[m]", "\u7ad9 15 \u548c 16 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_16_17", "0.06[m]", "\u7ad9 16 \u548c 17 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_17_18", "0.08[m]", "\u7ad9 17 \u548c 18 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_19_20", "0.1[m]", "\u7ad9 19 \u548c 20 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_20_21", "0.13[m]", "\u7ad9 20 \u548c 21 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_21_22", "0.1[m]", "\u7ad9 21 \u548c 22 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_23_24", "0.08[m]", "\u7ad9 23 \u548c 24 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_24_25", "0.06[m]", "\u7ad9 24 \u548c 25 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_25_26", "0.05[m]", "\u7ad9 25 \u548c 26 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_26_27", "0.05[m]", "\u7ad9 26 \u548c 27 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").set("d_27_28", "0.08[m]", "\u7ad9 27 \u548c 28 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par3").label("\u53c2\u6570\uff1a\u8f74\u76f4\u5f84");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("table", new String[][]{{"x1", "0"}, 
         {"x1", "d_1_2/2"}, 
         {"x2", "d_1_2/2"}, 
         {"x2", "d_2_3/2"}, 
         {"x3", "d_2_3/2"}, 
         {"x3", "d_3_4/2"}, 
         {"x4", "d_3_4/2"}, 
         {"x4", "d_4_5/2"}, 
         {"x5", "d_4_5/2"}, 
         {"x6", "d_5_6/2"}, 
         {"x6", "d_6_7/2"}, 
         {"x7", "d_6_7/2"}, 
         {"x7", "d_7_8/2"}, 
         {"x8", "d_7_8/2"}, 
         {"x8", "d_8_9/2"}, 
         {"x9", "d_8_9/2"}, 
         {"x9", "d_9_10/2"}, 
         {"x10", "d_9_10/2"}, 
         {"x11", "d_10_11/2"}, 
         {"x11", "d_11_12/2"}, 
         {"x12", "d_11_12/2"}, 
         {"x12", "d_12_13/2"}, 
         {"x13", "d_12_13/2"}, 
         {"x13", "d_13_14/2"}, 
         {"x14", "d_13_14/2"}, 
         {"x14", "d_14_15/2"}, 
         {"x15", "d_14_15/2"}, 
         {"x16", "d_15_16/2"}, 
         {"x16", "d_16_17/2"}, 
         {"x17", "d_16_17/2"}, 
         {"x17", "d_17_18/2"}, 
         {"x18", "d_17_18/2"}, 
         {"x19", "d_19_20/2"}, 
         {"x20", "d_19_20/2"}, 
         {"x20", "d_20_21/2"}, 
         {"x21", "d_20_21/2"}, 
         {"x21", "d_21_22/2"}, 
         {"x22", "d_21_22/2"}, 
         {"x23", "d_23_24/2"}, 
         {"x24", "d_23_24/2"}, 
         {"x24", "d_24_25/2"}, 
         {"x25", "d_24_25/2"}, 
         {"x25", "d_25_26/2"}, 
         {"x26", "d_25_26/2"}, 
         {"x27", "d_26_27/2"}, 
         {"x27", "d_27_28/2"}, 
         {"x28", "d_27_28/2"}, 
         {"x28", "0"}, 
         {"x27", "0"}, 
         {"x26", "0"}, 
         {"x25", "0"}, 
         {"x24", "0"}, 
         {"x23", "0"}, 
         {"x22", "0"}, 
         {"x21", "0"}, 
         {"x20", "0"}, 
         {"x19", "0"}, 
         {"x18", "0"}, 
         {"x17", "0"}, 
         {"x16", "0"}, 
         {"x15", "0"}, 
         {"x14", "0"}, 
         {"x13", "0"}, 
         {"x12", "0"}, 
         {"x11", "0"}, 
         {"x10", "0"}, 
         {"x9", "0"}, 
         {"x8", "0"}, 
         {"x7", "0"}, 
         {"x6", "0"}, 
         {"x5", "0"}, 
         {"x4", "0"}, 
         {"x3", "0"}, 
         {"x2", "0"}});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").set("axis", new int[]{1, 0});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(1);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection("sel1").set(10);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection("sel1").label("\u6881\u8f6c\u5b50");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(29);
    model.component("comp1").selection("sel2").set("groupcontang", true);
    model.component("comp1").selection("sel2").label("\u8f74\u9888\u8f74\u627f 1");
    model.component("comp1").selection().duplicate("sel3", "sel2");
    model.component("comp1").selection("sel3").remove(28, 29, 31, 33, 34, 35, 36, 37);
    model.component("comp1").selection("sel3").add(100, 101, 103, 105, 106, 107, 108, 109);
    model.component("comp1").selection("sel3").label("\u8f74\u9888\u8f74\u627f 2");
    model.component("comp1").selection().duplicate("sel4", "sel3");
    model.component("comp1").selection("sel4").remove(100, 101, 103, 105, 106, 107, 108, 109);
    model.component("comp1").selection("sel4").add(168, 169, 171, 173, 174, 175, 176, 177);
    model.component("comp1").selection("sel4").label("\u8f74\u9888\u8f74\u627f 3");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1")
         .set("table", new String[][]{{"x1", "d_1_2"}, 
         {"x2-xTol", "d_1_2"}, 
         {"x2", "d_2_3"}, 
         {"x3-xTol", "d_2_3"}, 
         {"x3", "d_3_4"}, 
         {"x4-xTol", "d_3_4"}, 
         {"x4", "d_4_5"}, 
         {"x5-xTol", "d_4_5"}, 
         {"x5", "d_5_6"}, 
         {"x6-xTol", "d_5_6"}, 
         {"x6", "d_6_7"}, 
         {"x7-xTol", "d_6_7"}, 
         {"x7", "d_7_8"}, 
         {"x8-xTol", "d_7_8"}, 
         {"x8", "d_8_9"}, 
         {"x9-xTol", "d_8_9"}, 
         {"x9", "d_9_10"}, 
         {"x10-xTol", "d_9_10"}, 
         {"x10", "d_10_11"}, 
         {"x11-xTol", "d_10_11"}, 
         {"x11", "d_11_12"}, 
         {"x12-xTol", "d_11_12"}, 
         {"x12", "d_12_13"}, 
         {"x13-xTol", "d_12_13"}, 
         {"x13", "d_13_14"}, 
         {"x14-xTol", "d_13_14"}, 
         {"x14", "d_14_15"}, 
         {"x15-xTol", "d_14_15"}, 
         {"x15", "d_15_16"}, 
         {"x16-xTol", "d_15_16"}, 
         {"x16", "d_16_17"}, 
         {"x17-xTol", "d_16_17"}, 
         {"x17", "d_17_18"}, 
         {"x18", "d_17_18"}, 
         {"x19", "d_19_20"}, 
         {"x20-xTol", "d_19_20"}, 
         {"x20", "d_20_21"}, 
         {"x21-xTol", "d_20_21"}, 
         {"x21", "d_21_22"}, 
         {"x22", "d_21_22"}, 
         {"x23", "d_23_24"}, 
         {"x24-xTol", "d_23_24"}, 
         {"x24", "d_24_25"}, 
         {"x25-xTol", "d_24_25"}, 
         {"x25", "d_25_26"}, 
         {"x26-xTol", "d_25_26"}, 
         {"x26", "d_26_27"}, 
         {"x27-xTol", "d_26_27"}, 
         {"x27", "d_27_28"}, 
         {"x28", "d_27_28"}});
    model.component("comp1").func("int1").label("\u63d2\u503c\uff1a\u8f6c\u5b50\u76f4\u5f84");
    model.component("comp1").func("int1").set("funcname", "dia");
    model.component("comp1").func("int1").setIndex("fununit", "m", 0);
    model.component("comp1").func("int1").setIndex("argunit", "m", 0);

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.material("mat1").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.material("mat1").propertyGroup().create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.material("mat1").label("Structural steel");
    model.material("mat1").set("family", "custom");
    model.material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat1").set("diffuse", "custom");
    model.material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat1").set("ambient", "custom");
    model.material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat1").set("noise", true);
    model.material("mat1").set("fresnel", 0.9);
    model.material("mat1").set("roughness", 0.3);
    model.material("mat1").set("diffusewrap", 0);
    model.material("mat1").set("reflectance", 0);
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.material("mat1").propertyGroup("Enu").func("int2").set("funcnametable", new String[][]{{"int1", "1"}});
    model.material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.material("mat1").propertyGroup("Enu").addInput("temperature");
    model.material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.material("mat1").propertyGroup("ElastoplasticModel").label("Elastoplastic material model");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.material("mat1").propertyGroup("Voce").addInput("temperature");
    model.material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.material("mat1").propertyGroup("Norton").label("Norton");
    model.material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").label("Chaboche viscoplasticity");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").label("\u6750\u6599\u94fe\u63a5\uff1a\u5b9e\u5fc3");
    model.component("comp1").material().duplicate("matlnk2", "matlnk1");
    model.component("comp1").material("matlnk2").selection().geom("geom1", 1);
    model.component("comp1").material("matlnk2").selection().named("sel1");
    model.component("comp1").material("matlnk2").label("\u6750\u6599\u94fe\u63a5\uff1a\u6881");

    model.component("comp1").physics("rotsld").prop("RotorProperties").set("freqr", "fr");
    model.component("comp1").physics("rotsld").feature("raxi1").set("specifiedBy", "Edge");
    model.component("comp1").physics("rotsld").feature("raxi1").feature("axis1").selection().named("sel1");
    model.component("comp1").physics("rotsld").feature("far1").selection().set(186, 187, 188, 189);
    model.component("comp1").physics("rotsld").create("jrb1", "JournalBearing", 2);
    model.component("comp1").physics("rotsld").feature("jrb1").selection().named("sel2");
    model.component("comp1").physics("rotsld").feature("jrb1").set("BearingModel", "kTot");
    model.component("comp1").physics("rotsld").feature("jrb1").set("k_u", new String[]{"kb", "0", "0", "kb"});
    model.component("comp1").physics("rotsld").feature("jrb1").set("k_th", new int[]{0, 0, 0, 0});
    model.component("comp1").physics("rotsld").feature().duplicate("jrb2", "jrb1");
    model.component("comp1").physics("rotsld").feature("jrb2").selection().named("sel3");
    model.component("comp1").physics("rotsld").feature("jrb2").set("k_th", new int[]{0, 0, 0, 0});
    model.component("comp1").physics("rotsld").feature().duplicate("jrb3", "jrb2");
    model.component("comp1").physics("rotsld").feature("jrb3").selection().named("sel4");
    model.component("comp1").physics("rotsld").feature("jrb3").set("k_th", new int[]{0, 0, 0, 0});
    model.component("comp1").physics("srotf").prop("RotorProperties").set("freqr", "fr");
    model.component("comp1").physics("srotf").feature("raxi1").set("specifiedBy", "Edge");
    model.component("comp1").physics("srotf").feature("raxi1").feature("axis1").selection().named("sel1");
    model.component("comp1").physics("srotf").feature("far1").selection().set(186, 187, 188, 189);
    model.component("comp1").physics("srotf").feature().copy("jrb1", "rotsld/jrb1");
    model.component("comp1").physics("srotf").feature().copy("jrb2", "rotsld/jrb2");
    model.component("comp1").physics("srotf").feature().copy("jrb3", "rotsld/jrb3");
    model.component("comp1").physics("srotf").feature("jrb1").set("k_th", new int[]{0, 0, 0, 0});
    model.component("comp1").physics("srotf").feature("jrb2").set("k_th", new int[]{0, 0, 0, 0});
    model.component("comp1").physics("srotf").feature("jrb3").set("k_th", new int[]{0, 0, 0, 0});
    model.component("comp1").physics("rotbm").selection().named("sel1");
    model.component("comp1").physics("rotbm").prop("RotorProperties").set("freqr", "fr");
    model.component("comp1").physics("rotbm").prop("Results").set("createUndefGeom", false);
    model.component("comp1").physics("rotbm").feature("rcs1").set("do_circ", "dia(x)");
    model.component("comp1").physics("rotbm").create("jrb1", "JournalBearing", 0);
    model.component("comp1").physics("rotbm").feature("jrb1").selection().set(42);
    model.component("comp1").physics("rotbm").feature("jrb1").set("BearingModel", "kTot");
    model.component("comp1").physics("rotbm").feature("jrb1").set("k_u", new String[]{"kb", "0", "0", "kb"});
    model.component("comp1").physics("rotbm").feature().duplicate("jrb2", "jrb1");
    model.component("comp1").physics("rotbm").feature("jrb2").selection().set(117);
    model.component("comp1").physics("rotbm").feature().duplicate("jrb3", "jrb2");
    model.component("comp1").physics("rotbm").feature("jrb3").selection().set(196);

    model.component("comp1").mesh("mesh1").label("\u7f51\u683c\uff1a\u5b9e\u5fc3");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 16);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh2").label("\u7f51\u683c\uff1a\u6881");
    model.component("comp1").mesh("mesh2").create("edg1", "Edge");
    model.component("comp1").mesh("mesh2").feature("edg1").selection().named("sel1");
    model.component("comp1").mesh("mesh2").run();

    model.study("std1").label("\u7814\u7a76\uff1a\u5b9e\u5fc3\u8f6c\u5b50");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "d_1_2", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std1").feature("param").setIndex("pname", "d_1_2", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "fr", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0,2000,50000)", 0);
    model.study("std1").feature("param").setIndex("punit", "rpm", 0);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").setSolveFor("/physics/srotf", false);
    model.study("std1").feature("stat").setSolveFor("/physics/rotbm", false);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"srotf", "rotbm"});
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 8);
    model.study("std1").feature("eig").set("useadvanceddisable", true);
    model.study("std1").feature("eig").setSolveFor("/physics/srotf", false);
    model.study("std1").feature("eig").setSolveFor("/physics/rotbm", false);
    model.study("std1").feature("eig").set("disabledphysics", new String[]{"srotf", "rotbm"});
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").label("\u8f74");
    model.result().dataset("cln1").set("data", "none");
    model.result().dataset("cln1")
         .set("genpoints", new String[][]{{"comp1.rotsld.raxi1.r1x", "comp1.rotsld.raxi1.r1y", "comp1.rotsld.raxi1.r1z"}, {"comp1.rotsld.raxi1.r2x", "comp1.rotsld.raxi1.r2y", "comp1.rotsld.raxi1.r2z"}});
    model.result().dataset("cln1").set("bounded", false);
    model.result().dataset("cln1").set("data", "dset3");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u632f\u578b (rotsld)");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 26, 1);
    model.result("pg1").set("showlegends", "off");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6da1\u52a8 (rotsld)");
    model.result("pg2").set("data", "cln1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").setIndex("looplevel", 26, 1);
    model.result("pg2").set("showlegends", "off");
    model.result("pg2").feature().create("wp1", "Whirl");
    model.result("pg2").feature("wp1").set("nrings", 10);
    model.result("pg2").feature("wp1").set("colortable", "TrafficLight");
    model.result("pg2").feature("wp1").set("smooth", "internal");
    model.result("pg2").feature("wp1").set("data", "parent");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset3");
    model.result().evaluationGroup("std1EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76\uff1a\u5b9e\u5fc3\u8f6c\u5b50)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{6, 26});
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{6, 26});
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").create("lnsg1", "LineSegments");
    model.result("pg3").feature("lnsg1").set("data", "dset3");
    model.result("pg3").feature("lnsg1").set("looplevelinput", new String[][]{{"last", "last"}});
    model.result("pg3").feature("lnsg1").set("xexpr", new String[]{"0[Hz]", "rotsld.freqr"});
    model.result("pg3").feature("lnsg1").set("xunit", new String[]{"Hz", "Hz"});
    model.result("pg3").feature("lnsg1").set("xdescr", new String[]{"\u539f\u70b9", "\u89d2\u901f\u5ea6"});
    model.result("pg3").feature("lnsg1").set("yexpr", new String[]{"0[Hz]", "0[Hz]"});
    model.result("pg3").feature("lnsg1").set("yunit", new String[]{"Hz", "Hz"});
    model.result("pg3").feature("lnsg1").set("ydescr", new String[]{"\u539f\u70b9", "0 \u00d7 \u89d2\u901f\u5ea6"});
    model.result("pg3").feature("lnsg1").set("linestyle", "cycle");
    model.result("pg3").feature("lnsg1").set("linecolor", "black");
    model.result("pg3").feature("lnsg1").set("linewidth", 1);
    model.result("pg3").feature("lnsg1").set("legend", true);
    model.result("pg3").feature("lnsg1").set("legendmethod", "manual");
    model.result("pg3").feature("lnsg1").set("legends", "0\\times\\Omega");
    model.result("pg3").feature("lnsg1").label("\u8f85\u52a9\u7ebf (0\u00d7\u03a9)");
    model.result("pg3").create("lnsg2", "LineSegments");
    model.result("pg3").feature("lnsg2").set("data", "dset3");
    model.result("pg3").feature("lnsg2").set("looplevelinput", new String[][]{{"last", "last"}});
    model.result("pg3").feature("lnsg2").set("xexpr", new String[]{"0[Hz]", "min(real(freq)/2,rotsld.freqr)"});
    model.result("pg3").feature("lnsg2").set("xunit", new String[]{"Hz", "Hz"});
    model.result("pg3").feature("lnsg2").set("xdescr", new String[]{"\u539f\u70b9", "\u89d2\u901f\u5ea6"});
    model.result("pg3").feature("lnsg2").set("yexpr", new String[]{"0[Hz]", "min(real(freq),2*rotsld.freqr)"});
    model.result("pg3").feature("lnsg2").set("yunit", new String[]{"Hz", "Hz"});
    model.result("pg3").feature("lnsg2").set("ydescr", new String[]{"\u539f\u70b9", "2 \u00d7 \u89d2\u901f\u5ea6"});
    model.result("pg3").feature("lnsg2").set("linestyle", "cycle");
    model.result("pg3").feature("lnsg2").set("linecolor", "black");
    model.result("pg3").feature("lnsg2").set("linewidth", 1);
    model.result("pg3").feature("lnsg2").set("legend", true);
    model.result("pg3").feature("lnsg2").set("legendmethod", "manual");
    model.result("pg3").feature("lnsg2").set("legends", "2\\times\\Omega");
    model.result("pg3").feature("lnsg2").label("\u8f85\u52a9\u7ebf (2\u00d7\u03a9)");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("expr", "real(freq)");
    model.result("pg3").feature("glob1").set("descr", "\u963b\u5c3c\u56fa\u6709\u9891\u7387");
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "rotsld.freqr");
    model.result("pg3").feature("glob1").set("xdataunit", "Hz");
    model.result("pg3").feature("glob1").set("xdatadescr", "\u65cb\u8f6c\u901f\u5ea6");
    model.result("pg3").feature("glob1").set("linestyle", "solid");
    model.result("pg3").feature("glob1").set("linecolor", "cycle");
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg3").feature("glob1").label("\u56fa\u6709\u9891\u7387");
    model.result("pg3").feature("glob1").create("col1", "Color");
    model.result("pg3").feature("glob1").feature("col1").set("expr", "rotsld.i_sd");
    model.result("pg3").feature("glob1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("glob1").feature("col1").label("\u65b9\u5411\u6027");
    model.result("pg3").feature("glob1").feature("col1").set("colortable", "Spectrum");
    model.result("pg3").feature("glob1").feature("col1").set("rangecoloractive", true);
    model.result("pg3").feature("glob1").feature("col1").set("rangecolormin", -1.3);
    model.result("pg3").feature("glob1").feature("col1").set("rangecolormax", 1.3);
    model.result("pg3").feature("glob1").feature("col1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg3").feature("glob1").feature("gmrk1").set("displaymode", "intersection");
    model.result("pg3").feature("glob1").feature("gmrk1").set("intersectionline", "general");
    model.result("pg3").feature("glob1").feature("gmrk1").set("generalcoeffc", 0);
    model.result("pg3").feature("glob1").feature("gmrk1").set("generalcoeffa", " -0 -2");
    model.result("pg3").feature("glob1").feature("gmrk1").set("generalcoeffb", 1);
    model.result("pg3").feature("glob1").feature("gmrk1").set("includexcoord", false);
    model.result("pg3").feature("glob1").feature("gmrk1").set("includeycoord", false);
    model.result("pg3").feature("glob1").feature("gmrk1").set("precision", 3);
    model.result("pg3").feature("glob1").feature("gmrk1").set("pointradius", 2.5);
    model.result("pg3").feature("glob1").feature("gmrk1").set("anchorpoint", "lowermiddle");
    model.result("pg3").feature("glob1").feature("gmrk1").label("\u4ea4\u96c6 ([0,2]\u00d7\u03a9)");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u574e\u8d1d\u5c14\u56fe");
    model.result("pg3").label("\u574e\u8d1d\u5c14\u56fe (rotsld)");
    model.result("pg3").label("\u574e\u8d1d\u5c14\u56fe (rotsld)");
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").setIndex("expr", "abs(freq)", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "Natural frequency", 0);
    model.result("pg3").run();

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
    model.study("std2").feature("stat").setSolveFor("/physics/rotsld", false);
    model.study("std2").feature("stat").setSolveFor("/physics/srotf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/rotbm", false);
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("ftplistmethod", "manual");
    model.study("std2").feature("eig").set("geometricNonlinearity", true);
    model.study("std2").feature("eig").set("linpsolnum", "auto");
    model.study("std2").feature("eig").set("solnum", "auto");
    model.study("std2").feature("eig").set("notsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/rotsld", false);
    model.study("std2").feature("eig").setSolveFor("/physics/srotf", true);
    model.study("std2").feature("eig").setSolveFor("/physics/rotbm", false);
    model.study().create("std3");
    model.study("std3").create("eig", "Eigenfrequency");
    model.study("std3").feature("eig").setSolveFor("/physics/rotsld", false);
    model.study("std3").feature("eig").setSolveFor("/physics/srotf", false);
    model.study("std3").feature("eig").setSolveFor("/physics/rotbm", true);
    model.study("std2").label("\u7814\u7a76\uff1aSRFF");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "d_1_2", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "d_1_2", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "fr", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(0,2000,50000)", 0);
    model.study("std2").feature("param").setIndex("punit", "rpm", 0);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"rotsld", "rotbm"});
    model.study("std2").feature("stat").setEntry("mesh", "geom1", "mesh1");
    model.study("std2").feature("eig").set("useadvanceddisable", true);
    model.study("std2").feature("eig").set("disabledphysics", new String[]{"rotsld", "rotbm"});
    model.study("std2").feature("eig").setEntry("mesh", "geom1", "mesh1");
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 9);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol32");
    model.sol("sol32").study("std2");
    model.sol("sol32").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol32");
    model.batch("p2").run("compute");

    model.result().dataset().create("cln2", "CutLine3D");
    model.result().dataset("cln2").label("\u8f74 1");
    model.result().dataset("cln2").set("data", "none");
    model.result().dataset("cln2")
         .set("genpoints", new String[][]{{"comp1.srotf.raxi1.r1x", "comp1.srotf.raxi1.r1y", "comp1.srotf.raxi1.r1z"}, {"comp1.srotf.raxi1.r2x", "comp1.srotf.raxi1.r2y", "comp1.srotf.raxi1.r2z"}});
    model.result().dataset("cln2").set("bounded", false);
    model.result().dataset("cln2").set("spacevars", new String[]{"cln1x"});
    model.result().dataset("cln2").set("tangent", new String[]{"cln1tx", "cln1ty", "cln1tz"});
    model.result().dataset("cln2").set("data", "dset6");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u632f\u578b (srotf)");
    model.result("pg4").set("data", "dset6");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").setIndex("looplevel", 26, 1);
    model.result("pg4").set("showlegends", "off");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "srotf.disp");
    model.result("pg4").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("def1", "Deform");
    model.result("pg4").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6da1\u52a8 (srotf)");
    model.result("pg5").set("data", "cln2");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").setIndex("looplevel", 26, 1);
    model.result("pg5").set("showlegends", "off");
    model.result("pg5").feature().create("wp1", "Whirl");
    model.result("pg5").feature("wp1").set("nrings", 10);
    model.result("pg5").feature("wp1").set("colortable", "TrafficLight");
    model.result("pg5").feature("wp1").set("smooth", "internal");
    model.result("pg5").feature("wp1").set("data", "parent");
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset6");
    model.result().evaluationGroup("std2EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76\uff1aSRFF)");
    model.result().evaluationGroup("std2EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std2EvgFrq").run();
    model.result("pg4").run();
    model.result("pg4").set("looplevel", new int[]{8, 26});
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{8, 26});
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset6");
    model.result("pg6").create("lnsg1", "LineSegments");
    model.result("pg6").feature("lnsg1").set("data", "dset6");
    model.result("pg6").feature("lnsg1").set("looplevelinput", new String[][]{{"last", "last"}});
    model.result("pg6").feature("lnsg1").set("xexpr", new String[]{"0[Hz]", "min(real(freq),srotf.freqr)"});
    model.result("pg6").feature("lnsg1").set("xunit", new String[]{"Hz", "Hz"});
    model.result("pg6").feature("lnsg1").set("xdescr", new String[]{"\u539f\u70b9", "\u89d2\u901f\u5ea6"});
    model.result("pg6").feature("lnsg1").set("yexpr", new String[]{"0[Hz]", "min(real(freq),srotf.freqr)"});
    model.result("pg6").feature("lnsg1").set("yunit", new String[]{"Hz", "Hz"});
    model.result("pg6").feature("lnsg1").set("ydescr", new String[]{"\u539f\u70b9", "\u89d2\u901f\u5ea6"});
    model.result("pg6").feature("lnsg1").set("linestyle", "cycle");
    model.result("pg6").feature("lnsg1").set("linecolor", "black");
    model.result("pg6").feature("lnsg1").set("linewidth", 1);
    model.result("pg6").feature("lnsg1").set("legend", true);
    model.result("pg6").feature("lnsg1").set("legendmethod", "manual");
    model.result("pg6").feature("lnsg1").set("legends", "1\\times\\Omega");
    model.result("pg6").feature("lnsg1").label("\u8f85\u52a9\u7ebf (1\u00d7\u03a9)");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("expr", "real(freq)");
    model.result("pg6").feature("glob1").set("descr", "\u963b\u5c3c\u56fa\u6709\u9891\u7387");
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "srotf.freqr");
    model.result("pg6").feature("glob1").set("xdataunit", "Hz");
    model.result("pg6").feature("glob1").set("xdatadescr", "\u65cb\u8f6c\u901f\u5ea6");
    model.result("pg6").feature("glob1").set("linestyle", "solid");
    model.result("pg6").feature("glob1").set("linecolor", "cycle");
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").feature("glob1").set("legend", false);
    model.result("pg6").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg6").feature("glob1").label("\u56fa\u6709\u9891\u7387");
    model.result("pg6").feature("glob1").create("col1", "Color");
    model.result("pg6").feature("glob1").feature("col1").set("expr", "srotf.i_sd");
    model.result("pg6").feature("glob1").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("glob1").feature("col1").label("\u65b9\u5411\u6027");
    model.result("pg6").feature("glob1").feature("col1").set("colortable", "Spectrum");
    model.result("pg6").feature("glob1").feature("col1").set("rangecoloractive", true);
    model.result("pg6").feature("glob1").feature("col1").set("rangecolormin", -1.3);
    model.result("pg6").feature("glob1").feature("col1").set("rangecolormax", 1.3);
    model.result("pg6").feature("glob1").feature("col1").set("colorscalemode", "linearsymmetric");
    model.result("pg6").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg6").feature("glob1").feature("gmrk1").set("displaymode", "intersection");
    model.result("pg6").feature("glob1").feature("gmrk1").set("intersectionline", "general");
    model.result("pg6").feature("glob1").feature("gmrk1").set("generalcoeffc", 0);
    model.result("pg6").feature("glob1").feature("gmrk1").set("generalcoeffa", " -1");
    model.result("pg6").feature("glob1").feature("gmrk1").set("generalcoeffb", 1);
    model.result("pg6").feature("glob1").feature("gmrk1").set("includexcoord", false);
    model.result("pg6").feature("glob1").feature("gmrk1").set("includeycoord", false);
    model.result("pg6").feature("glob1").feature("gmrk1").set("precision", 3);
    model.result("pg6").feature("glob1").feature("gmrk1").set("pointradius", 2.5);
    model.result("pg6").feature("glob1").feature("gmrk1").set("anchorpoint", "lowermiddle");
    model.result("pg6").feature("glob1").feature("gmrk1").label("\u4ea4\u96c6 (1\u00d7\u03a9)");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u574e\u8d1d\u5c14\u56fe");
    model.result("pg6").label("\u574e\u8d1d\u5c14\u56fe (srotf)");
    model.result("pg6").label("\u574e\u8d1d\u5c14\u56fe (srotf)");
    model.result("pg6").run();
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("axislimits", true);
    model.result("pg6").set("ymax", 1200);
    model.result("pg6").run();
    model.result("pg6").feature("glob1").setIndex("expr", "abs(freq)", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "Natural frequency", 0);
    model.result("pg6").run();
    model.result("pg6").create("glob2", "Global");
    model.result("pg6").feature("glob2").set("markerpos", "datapoints");
    model.result("pg6").feature("glob2").set("linewidth", "preference");
    model.result("pg6").feature("glob2").set("data", "dset3");
    model.result("pg6").feature("glob2").setIndex("expr", "abs(rotsld.omega_fix/2/pi)", 0);
    model.result("pg6").feature("glob2").setIndex("descr", "Natural Frequency", 0);
    model.result("pg6").feature("glob2").set("xdatasolnumtype", "outer");
    model.result("pg6").feature("glob2").set("xdata", "expr");
    model.result("pg6").feature("glob2").set("xdataexpr", "rotsld.freqr");
    model.result("pg6").feature("glob2").set("xdatadescr", "\u5355\u4f4d\u65f6\u95f4\u8f6c\u6570");
    model.result("pg6").feature("glob2").set("linestyle", "none");
    model.result("pg6").feature("glob2").set("linemarker", "circle");
    model.result("pg6").feature("glob2").set("linecolor", "black");
    model.result("pg6").feature("glob2").set("legend", false);
    model.result("pg6").run();

    model.study("std3").label("\u7814\u7a76\uff1a\u6881\u8f6c\u5b50");
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "d_1_2", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m", 0);
    model.study("std3").feature("param").setIndex("pname", "d_1_2", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m", 0);
    model.study("std3").feature("param").setIndex("pname", "fr", 0);
    model.study("std3").feature("param").setIndex("plistarr", "range(0,2000,50000)", 0);
    model.study("std3").feature("param").setIndex("punit", "rpm", 0);
    model.study("std3").feature("eig").set("useadvanceddisable", true);
    model.study("std3").feature("eig").set("disabledphysics", new String[]{"rotsld", "srotf"});
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol60");
    model.sol("sol60").study("std3");
    model.sol("sol60").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("p3").feature("so1").set("psol", "sol60");
    model.batch("p3").run("compute");

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset8");
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").setIndex("looplevel", 26, 1);
    model.result("pg7").label("\u6da1\u52a8 (rotbm)");
    model.result("pg7").create("wp1", "Whirl");
    model.result("pg7").feature("wp1").set("expr", new String[]{"u3", "v3", "w3"});
    model.result("pg7").feature("wp1").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg7").feature("wp1").set("nplanes", "1");
    model.result("pg7").feature("wp1").set("nrings", "10");
    model.result("pg7").feature("wp1").set("colortable", "TrafficLight");
    model.result("pg7").feature("wp1").set("ringcolor", "blue");
    model.result("pg7").feature("wp1").selection().geom("geom1", 1);
    model.result("pg7").feature("wp1").selection()
         .set(10, 24, 41, 58, 70, 84, 101, 118, 135, 147, 161, 178, 195, 212, 224, 238, 255, 267, 276, 290, 307, 319, 328, 342, 359, 371, 385);
    model.result("pg7").feature("wp1").selection().inherit(false);
    model.result("pg7").feature("wp1").selection().embedded(false);
    model.result().evaluationGroup().create("std3EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std3EvgFrq").set("data", "dset8");
    model.result().evaluationGroup("std3EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76\uff1a\u6881\u8f6c\u5b50)");
    model.result().evaluationGroup("std3EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std3EvgFrq").run();
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset8");
    model.result("pg8").create("lnsg1", "LineSegments");
    model.result("pg8").feature("lnsg1").set("data", "dset8");
    model.result("pg8").feature("lnsg1").set("looplevelinput", new String[][]{{"last", "last"}});
    model.result("pg8").feature("lnsg1").set("xexpr", new String[]{"0[Hz]", "min(real(freq),rotbm.freqr)"});
    model.result("pg8").feature("lnsg1").set("xunit", new String[]{"Hz", "Hz"});
    model.result("pg8").feature("lnsg1").set("xdescr", new String[]{"\u539f\u70b9", "\u89d2\u901f\u5ea6"});
    model.result("pg8").feature("lnsg1").set("yexpr", new String[]{"0[Hz]", "min(real(freq),rotbm.freqr)"});
    model.result("pg8").feature("lnsg1").set("yunit", new String[]{"Hz", "Hz"});
    model.result("pg8").feature("lnsg1").set("ydescr", new String[]{"\u539f\u70b9", "\u89d2\u901f\u5ea6"});
    model.result("pg8").feature("lnsg1").set("linestyle", "cycle");
    model.result("pg8").feature("lnsg1").set("linecolor", "black");
    model.result("pg8").feature("lnsg1").set("linewidth", 1);
    model.result("pg8").feature("lnsg1").set("legend", true);
    model.result("pg8").feature("lnsg1").set("legendmethod", "manual");
    model.result("pg8").feature("lnsg1").set("legends", "1\\times\\Omega");
    model.result("pg8").feature("lnsg1").label("\u8f85\u52a9\u7ebf (1\u00d7\u03a9)");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("expr", "real(freq)");
    model.result("pg8").feature("glob1").set("descr", "\u963b\u5c3c\u56fa\u6709\u9891\u7387");
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").feature("glob1").set("xdataexpr", "rotbm.freqr");
    model.result("pg8").feature("glob1").set("xdataunit", "Hz");
    model.result("pg8").feature("glob1").set("xdatadescr", "\u65cb\u8f6c\u901f\u5ea6");
    model.result("pg8").feature("glob1").set("linestyle", "solid");
    model.result("pg8").feature("glob1").set("linecolor", "cycle");
    model.result("pg8").feature("glob1").set("linewidth", 2);
    model.result("pg8").feature("glob1").set("legend", false);
    model.result("pg8").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg8").feature("glob1").label("\u56fa\u6709\u9891\u7387");
    model.result("pg8").feature("glob1").create("col1", "Color");
    model.result("pg8").feature("glob1").feature("col1").set("expr", "rotbm.i_sd");
    model.result("pg8").feature("glob1").feature("col1").set("colorlegend", false);
    model.result("pg8").feature("glob1").feature("col1").label("\u65b9\u5411\u6027");
    model.result("pg8").feature("glob1").feature("col1").set("colortable", "Spectrum");
    model.result("pg8").feature("glob1").feature("col1").set("rangecoloractive", true);
    model.result("pg8").feature("glob1").feature("col1").set("rangecolormin", -1.3);
    model.result("pg8").feature("glob1").feature("col1").set("rangecolormax", 1.3);
    model.result("pg8").feature("glob1").feature("col1").set("colorscalemode", "linearsymmetric");
    model.result("pg8").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg8").feature("glob1").feature("gmrk1").set("displaymode", "intersection");
    model.result("pg8").feature("glob1").feature("gmrk1").set("intersectionline", "general");
    model.result("pg8").feature("glob1").feature("gmrk1").set("generalcoeffc", 0);
    model.result("pg8").feature("glob1").feature("gmrk1").set("generalcoeffa", " -1");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg8").feature("glob1").feature("gmrk1").set("generalcoeffb", 1);
    model.result("pg8").feature("glob1").feature("gmrk1").set("includexcoord", false);
    model.result("pg8").feature("glob1").feature("gmrk1").set("includeycoord", false);
    model.result("pg8").feature("glob1").feature("gmrk1").set("precision", 3);
    model.result("pg8").feature("glob1").feature("gmrk1").set("pointradius", 2.5);
    model.result("pg8").feature("glob1").feature("gmrk1").set("anchorpoint", "lowermiddle");
    model.result("pg8").feature("glob1").feature("gmrk1").label("\u4ea4\u96c6 (1\u00d7\u03a9)");
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u574e\u8d1d\u5c14\u56fe");
    model.result("pg8").label("\u574e\u8d1d\u5c14\u56fe (rotbm)");
    model.result("pg8").label("\u574e\u8d1d\u5c14\u56fe (rotbm)");
    model.result("pg8").run();
    model.result("pg8").set("axislimits", true);
    model.result("pg8").set("ymax", 900);
    model.result("pg8").set("legendpos", "upperleft");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("expr", "abs(freq)", 0);
    model.result("pg8").feature("glob1").setIndex("descr", "Natural frequency", 0);
    model.result("pg8").run();

    model.title("\u6bd4\u8f83\u4e0d\u540c\u8f6c\u5b50\u63a5\u53e3\u751f\u6210\u7684\u574e\u8d1d\u5c14\u56fe");

    model
         .description("\u60a8\u53ef\u4ee5\u4f7f\u7528\u4e0d\u540c\u7c7b\u578b\u7684\u5355\u5143\u5bf9\u8f6c\u5b50\u8fdb\u884c\u5efa\u6a21\uff0c\u5177\u4f53\u53d6\u51b3\u4e8e\u5f85\u5efa\u6a21\u7cfb\u7edf\u7684\u590d\u6742\u7a0b\u5ea6\u548c\u7c7b\u578b\u3002\u968f\u7740\u6240\u91c7\u7528\u7684\u7406\u60f3\u5316\u6a21\u578b\u7684\u4e0d\u540c\uff0c\u5efa\u6a21\u6b65\u9aa4\u548c\u7ed3\u679c\u8868\u793a\u4e5f\u4f1a\u76f8\u5e94\u53d8\u5316\u3002\u672c\u6559\u5b66\u6a21\u578b\u4f7f\u7528\u4ee5\u4e0b\u4e09\u79cd\u4e0d\u540c\u7684\u8f6c\u5b50\u52a8\u529b\u5b66\u7269\u7406\u573a\u63a5\u53e3\u5bf9\u9636\u68af\u8f6c\u5b50\u6267\u884c\u7279\u5f81\u9891\u7387\u5206\u6790\uff1a\u201c\u5b9e\u5fc3\u8f6c\u5b50\u201d\u3001\u201c\u5b9e\u5fc3\u8f6c\u5b50\uff0c\u56fa\u5b9a\u5750\u6807\u7cfb\u201d\u548c\u201c\u6881\u8f6c\u5b50\u201d\uff1b\u5e76\u5c06\u8fd9\u4e9b\u63a5\u53e3\u751f\u6210\u7684\u574e\u8d1d\u5c14\u56fe\u76f8\u4e92\u8fdb\u884c\u6bd4\u8f83\u3002\u6b64\u5916\uff0c\u8be5\u6a21\u578b\u8fd8\u6709\u52a9\u4e8e\u7406\u89e3\u4f7f\u7528\u6bcf\u4e2a\u63a5\u53e3\u65f6\u6d89\u53ca\u7684\u5404\u4e2a\u6b65\u9aa4\u3002");

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
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();
    model.sol("sol34").clearSolutionData();
    model.sol("sol35").clearSolutionData();
    model.sol("sol36").clearSolutionData();
    model.sol("sol37").clearSolutionData();
    model.sol("sol38").clearSolutionData();
    model.sol("sol39").clearSolutionData();
    model.sol("sol40").clearSolutionData();
    model.sol("sol41").clearSolutionData();
    model.sol("sol42").clearSolutionData();
    model.sol("sol43").clearSolutionData();
    model.sol("sol44").clearSolutionData();
    model.sol("sol45").clearSolutionData();
    model.sol("sol46").clearSolutionData();
    model.sol("sol47").clearSolutionData();
    model.sol("sol48").clearSolutionData();
    model.sol("sol49").clearSolutionData();
    model.sol("sol50").clearSolutionData();
    model.sol("sol51").clearSolutionData();
    model.sol("sol52").clearSolutionData();
    model.sol("sol53").clearSolutionData();
    model.sol("sol54").clearSolutionData();
    model.sol("sol55").clearSolutionData();
    model.sol("sol56").clearSolutionData();
    model.sol("sol57").clearSolutionData();
    model.sol("sol58").clearSolutionData();
    model.sol("sol59").clearSolutionData();
    model.sol("sol60").clearSolutionData();
    model.sol("sol61").clearSolutionData();
    model.sol("sol62").clearSolutionData();
    model.sol("sol63").clearSolutionData();
    model.sol("sol64").clearSolutionData();
    model.sol("sol65").clearSolutionData();
    model.sol("sol66").clearSolutionData();
    model.sol("sol67").clearSolutionData();
    model.sol("sol68").clearSolutionData();
    model.sol("sol69").clearSolutionData();
    model.sol("sol70").clearSolutionData();
    model.sol("sol71").clearSolutionData();
    model.sol("sol72").clearSolutionData();
    model.sol("sol73").clearSolutionData();
    model.sol("sol74").clearSolutionData();
    model.sol("sol75").clearSolutionData();
    model.sol("sol76").clearSolutionData();
    model.sol("sol77").clearSolutionData();
    model.sol("sol78").clearSolutionData();
    model.sol("sol79").clearSolutionData();
    model.sol("sol80").clearSolutionData();
    model.sol("sol81").clearSolutionData();
    model.sol("sol82").clearSolutionData();
    model.sol("sol83").clearSolutionData();
    model.sol("sol84").clearSolutionData();
    model.sol("sol85").clearSolutionData();
    model.sol("sol86").clearSolutionData();

    model.label("campbell_plot_comparison.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
