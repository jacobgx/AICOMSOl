/*
 * thermal_actuator_tem_parameterized.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:16 by COMSOL 6.3.0.290. */
public class thermal_actuator_tem_parameterized {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Thermal-Structure_Interaction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");

    model.component("comp1").multiphysics().create("te1", "ThermalExpansion", 3);
    model.component("comp1").multiphysics("te1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("te1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("te1").selection().all();
    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 3);
    model.component("comp1").multiphysics("emh1").set("EMHeat_physics", "ec");
    model.component("comp1").multiphysics("emh1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("emh1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/te1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/emh1", true);

    model.component("comp1").label("\u70ed\u6267\u884c\u5668");

    model.param().set("d", "3[um]");
    model.param().descr("d", "\u70ed\u81c2\u7684\u9ad8\u5ea6");
    model.param().set("dw", "15[um]");
    model.param().descr("dw", "\u51b7\u81c2\u7684\u9ad8\u5ea6");
    model.param().set("gap", "3[um]");
    model.param().descr("gap", "\u81c2\u95f4\u7684\u95f4\u9699");
    model.param().set("wb", "10[um]");
    model.param().descr("wb", "\u5e95\u5ea7\u5bbd\u5ea6");
    model.param().set("wv", "25[um]");
    model.param().descr("wv", "\u70ed\u81c2\u4e4b\u95f4\u7684\u957f\u5ea6\u5dee\u5f02");
    model.param().set("L", "240[um]");
    model.param().descr("L", "\u6267\u884c\u5668\u957f\u5ea6");
    model.param().set("L1", "L-wb");
    model.param().descr("L1", "\u6700\u957f\u70ed\u81c2\u7684\u957f\u5ea6");
    model.param().set("L2", "L-wb-wv");
    model.param().descr("L2", "\u6700\u77ed\u70ed\u81c2\u7684\u957f\u5ea6");
    model.param().set("L3", "L-2*wb-wv-L/48-L/6");
    model.param().descr("L3", "\u51b7\u81c2\u7684\u957f\u5ea6\uff0c\u539a\u90e8\u5206");
    model.param().set("L4", "L/6");
    model.param().descr("L4", "\u51b7\u81c2\u7684\u957f\u5ea6\uff0c\u8584\u90e8\u5206");
    model.param().set("htc_s", "0.04[W/(m*K)]/2[um]");
    model.param().descr("htc_s", "\u4f20\u70ed\u7cfb\u6570");
    model.param().set("htc_us", "0.04[W/(m*K)]/100[um]");
    model.param().descr("htc_us", "\u4f20\u70ed\u7cfb\u6570\uff0c\u4e0a\u8868\u9762");
    model.param().set("DV", "5[V]");
    model.param().descr("DV", "\u5916\u52a0\u7535\u538b");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"L3", "dw"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new String[]{"L-L3", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new String[]{"L4", "d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"L-L3-L4", "dw-d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new String[]{"wb", "dw"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"L-L3-L4-wb", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("size", new String[]{"L2", "d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("pos", new String[]{"L-L2", "dw+gap"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .set("size", new String[]{"wb", "dw+gap+d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .set("pos", new String[]{"L-L2-wb", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6").set("size", new String[]{"L1", "d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6")
         .set("pos", new String[]{"L-L1", "dw+d+2*gap"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r6");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7")
         .set("size", new String[]{"wb", "dw+gap+d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7")
         .set("pos", new String[]{"0", "dw+d+2*gap"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r7");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8").set("size", new String[]{"d", "gap"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8")
         .set("pos", new String[]{"L-d", "dw+gap+d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r8");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r9", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9").set("size", new String[]{"d", "gap"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9").set("pos", new String[]{"L-d", "dw"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r9");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("r1", "r2", "r3", "r4", "r5", "r6", "r7", "r8", "r9");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "d/3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point")
         .set("uni1", 1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 16, 17, 19, 20, 21, 22, 23, 28);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "2e-6", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"wb-2*d", "2.5*(wb-2*d)"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new String[]{"d", "(dw+d+2*gap)+(dw+gap+d)-2.5*(wb-2*d)-d"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .set("size", new String[]{"wb-2*d", "2.5*(wb-2*d)"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .set("pos", new String[]{"L-L2-wb+d", "d"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r3")
         .set("size", new String[]{"wb-2*d", "2.5*(wb-2*d)"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r3")
         .set("pos", new String[]{"L-L3-L4-wb+d", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r3").setIndex("pos", "d", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").set("radius", "d/3");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").selection("pointinsketch")
         .set("r2", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").selection("pointinsketch")
         .set("r3", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "d/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1")
         .set("pos", new String[]{"L-L3/4", "dw/2"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c2").set("r", "d/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c2")
         .set("pos", new String[]{"L-L3/2", "dw/2"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c3").set("r", "d/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c3")
         .set("pos", new String[]{"L-3*L3/4", "dw/2"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c3");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "2e-6", 0);
    model.component("comp1").geom("geom1").feature("ext2").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ext1", "ext2");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(10, 30, 50, 70, 76, 82);
    model.component("comp1").selection("sel1").label("\u57fa\u677f\u63a5\u89e6");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Si - Polycrystalline silicon");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.7);
    model.component("comp1").material("mat1").set("roughness", 0.5);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "678[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2320[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"34[W/(m*K)]", "0", "0", "0", "34[W/(m*K)]", "0", "0", "0", "34[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "160e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.22");
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"5e4"});

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(10, 30, 50);
    model.component("comp1").physics("solid").create("roll1", "Roller", 2);
    model.component("comp1").physics("solid").feature("roll1").selection().set(70, 76, 82);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().all();
    model.component("comp1").physics("ht").feature("hf1").selection()
         .set(1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "htc_s");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf2").selection().set(4);
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "htc_us");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().named("sel1");
    model.component("comp1").physics("ec").feature("cucn1").setIndex("materialType", "solid", 0);
    model.component("comp1").physics("ec").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ec").feature("gnd1").selection().set(10);
    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("ec").feature("pot1").selection().set(30);
    model.component("comp1").physics("ec").feature("pot1").set("V0", "DV");

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("geometricNonlinearity", true);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subdamp", "1");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subdamp", "1");
    model.sol("sol1").feature("s1").feature("se1").feature("ss3").set("subtermconst", "tol");
    model.sol("sol1").feature("s1").feature("se1").feature("ss3").set("linsolver", "i3");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("expr", "T");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u52bf (ec)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "V");
    model.result("pg3").feature("vol1").set("colortable", "Dipole");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u573a (ec)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("solutionparams", "parent");
    model.result("pg4").feature("mslc1").set("expr", "ec.normE");
    model.result("pg4").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg4").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg4").feature("mslc1").set("colortable", "Prism");
    model.result("pg4").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result("pg4").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg4").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg4").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg4").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg4").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("strmsl1").set("zcoord", "ec.CPz");
    model.result("pg4").feature("strmsl1").set("titletype", "none");
    model.result("pg4").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg4").feature("strmsl1").set("udist", 0.02);
    model.result("pg4").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg4").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("inheritcolor", false);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("data", "parent");
    model.result("pg4").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg4").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg4").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg4").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg4").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u4f4d\u79fb");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("unit", "\u00b5m");
    model.result("pg5").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg5").feature("surf1").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg2").run();

    model.title("\u70ed\u6267\u884c\u5668 - \u53c2\u6570\u5316");

    model
         .description("\u8fd9\u4e2a\u53cc\u70ed\u81c2\u70ed\u6267\u884c\u5668\u793a\u4f8b\u8026\u5408\u4e86\u4e09\u79cd\u4e0d\u540c\u7684\u7269\u7406\u73b0\u8c61\uff1a\u7535\u6d41\u4f20\u5bfc\u3001\u542b\u751f\u70ed\u7684\u70ed\u4f20\u5bfc\u3001\u70ed\u81a8\u80c0\u5f15\u8d77\u7684\u7ed3\u6784\u5e94\u529b\u548c\u5e94\u53d8\u3002\u672c\u793a\u4f8b\u7248\u672c\u5c06\u51e0\u4f55\u53c2\u6570\u5316\uff0c\u4ece\u800c\u5206\u6790\u6267\u884c\u5668\u5c3a\u5bf8\u53d8\u5316\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("thermal_actuator_tem_parameterized.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
