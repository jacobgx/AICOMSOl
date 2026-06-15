/*
 * disk_stack_heat_sink.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:29 by COMSOL 6.3.0.290. */
public class disk_stack_heat_sink {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Thermal_Contact_and_Friction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("CB_w", "150[mm]", "\u7535\u8def\u677f\u5bbd\u5ea6");
    model.param().set("CB_l", "150[mm]", "\u7535\u8def\u677f\u957f\u5ea6");
    model.param().set("CB_t", "3[mm]", "\u7535\u8def\u677f\u539a\u5ea6");
    model.param().set("IC1_w", "50[mm]", "IC 1 \u5bbd\u5ea6");
    model.param().set("IC1_l", "50[mm]", "IC 1 \u957f\u5ea6");
    model.param().set("IC1_t", "3[mm]", "IC 1 \u539a\u5ea6");
    model.param().set("IC2_w", "15[mm]", "IC 2 \u5bbd\u5ea6");
    model.param().set("IC2_l", "20[mm]", "IC 2 \u957f\u5ea6");
    model.param().set("IC2_t", "3[mm]", "IC 2 \u539a\u5ea6");
    model.param().set("IC3_w", "15[mm]", "IC 3 \u5bbd\u5ea6");
    model.param().set("IC3_l", "40[mm]", "IC 3 \u957f\u5ea6");
    model.param().set("IC3_t", "3[mm]", "IC2 3 \u539a\u5ea6");
    model.param().set("P1", "20[W]", "IC 1 \u8017\u6563\u7684\u529f\u7387");
    model.param().set("P2", "1[W]", "IC 2 \u8017\u6563\u7684\u529f\u7387");
    model.param().set("P3", "2[W]", "IC 3 \u8017\u6563\u7684\u529f\u7387");
    model.param().set("htc", "20[W/(m^2*K)]", "\u4f20\u70ed\u7cfb\u6570");
    model.param().set("T0", "273.15[K]", "\u6c14\u6d41\u6e29\u5ea6");
    model.param().set("e_fins", "0.3[mm]", "\u7fc5\u7247\u539a\u5ea6");
    model.param().set("i_radius", "5[mm]", "\u5185\u534a\u5f84");
    model.param().set("o_radius", "25[mm]", "\u5916\u534a\u5f84");
    model.param().set("air_sp", "3[mm]", "\u6563\u70ed\u5668\u7fc5\u7247\u4e4b\u95f4\u7684\u7a7a\u6c14\u95f4\u8ddd");
    model.param().set("n_fins", "10", "\u7fc5\u7247\u6570");
    model.param().set("t_h", "air_sp*n_fins", "\u6563\u70ed\u5668\u7ba1\u9ad8\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"CB_w", "CB_l", "CB_t"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-CB_w/2", "-CB_l/2", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "-CB_t", 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"IC1_w", "IC1_l", "IC1_t"});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"-CB_w/2+IC1_w", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "-CB_l/2+IC1_l", 1);
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"IC2_l", "IC2_w", "IC2_t"});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new int[]{-60, -60, 0});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("blk3");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", "0 0 0 0 range(30,30,60) 30");
    model.component("comp1").geom("geom1").feature("copy1").set("disply", "range(25, 25, 100) 0 0 100");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new String[]{"IC3_w", "IC3_l", "IC3_t"});
    model.component("comp1").geom("geom1").feature("blk4").set("pos", new int[]{40, -50, 0});
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("copy2", "Copy");
    model.component("comp1").geom("geom1").feature("copy2").selection("input").set("blk4");
    model.component("comp1").geom("geom1").feature("copy2").set("disply", 50);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u96c6\u6210\u7535\u8def\uff0c1 \u7c7b");
    model.component("comp1").selection("sel1").set(9);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u96c6\u6210\u7535\u8def\uff0c2 \u7c7b");
    model.component("comp1").selection("sel2").set(2, 3, 4, 5, 6, 7, 8, 10);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u96c6\u6210\u7535\u8def\uff0c3 \u7c7b");
    model.component("comp1").selection("sel3").set(11, 12);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u96c6\u6210\u7535\u8def");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2", "sel3"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").label("Silica glass");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.99);
    model.component("comp1").material("mat1").set("roughness", 0.02);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "703[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"3.75", "0", "0", "0", "3.75", "0", "0", "0", "3.75"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2203[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "73.1[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.45", "0", "0", "0", "1.45", "0", "0", "0", "1.45"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("FR4 (Circuit Board)");
    model.component("comp1").material("mat2").set("family", "pcb");
    model.component("comp1").material("mat2").set("color", "custom");
    model.component("comp1").material("mat2").set("customcolor", "0 0.5019607843137255 0.25098039215686274");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.004[S/m]", "0", "0", "0", "0.004[S/m]", "0", "0", "0", "0.004[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18e-6[1/K]", "0", "0", "0", "18e-6[1/K]", "0", "0", "0", "18e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "1369[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "1900[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "22[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.15");
    model.component("comp1").material("mat1").selection().named("uni1");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"80[W/(m*K)]", "80[W/(m*K)]", "0.3[W/(m*K)]"});

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").selection().named("sel1");
    model.component("comp1").physics("ht").feature("hs1").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs1").set("P0", "P1");
    model.component("comp1").physics("ht").create("hs2", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs2").selection().named("sel2");
    model.component("comp1").physics("ht").feature("hs2").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs2").set("P0", "P2*8");
    model.component("comp1").physics("ht").create("hs3", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs3").selection().named("sel3");
    model.component("comp1").physics("ht").feature("hs3").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs3").set("P0", "P3*2");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "htc");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T0");
    model.component("comp1").physics("ht").feature("hf1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 14, 15, 16, 17, 19, 20, 21, 22, 24, 25, 26, 27, 29, 30, 31, 32, 33, 34, 35, 36, 37, 39, 40, 41, 42, 44, 45, 46, 47, 49, 50, 51, 52, 53, 54, 56, 57, 58, 59, 60, 61, 63, 64, 65, 66, 68, 69, 70, 71, 72);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").label("\u65e0\u6563\u70ed\u5668\u7684\u6e29\u5ea6");

    model.component("comp1").geom("geom1").run("copy2");
    model.component("comp1").geom("geom1").create("blk5", "Block");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new String[]{"IC1_w", "IC1_l", "IC1_t"});
    model.component("comp1").geom("geom1").feature("blk5").set("pos", new String[]{"-CB_w/2+IC1_w", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk5").setIndex("pos", "-CB_l/2+IC1_l", 1);
    model.component("comp1").geom("geom1").feature("blk5").setIndex("pos", "IC1_t", 2);
    model.component("comp1").geom("geom1").run("blk5");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "i_radius");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "t_h");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "IC1_t*2"});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7fc5\u7247");
    model.component("comp1").geom("geom1").feature("cyl1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("blk5", 4);
    model.component("comp1").geom("geom1").feature("wp1").set("offset", "air_sp");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "i_radius");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", "o_radius");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr1").set("linearsize", "n_fins");
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "0", "air_sp"});
    model.component("comp1").geom("geom1").feature("arr1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat3").label("Aluminum");
    model.component("comp1").material("mat3").set("family", "aluminum");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat4").label("Aluminum 1");
    model.component("comp1").material("mat4").set("family", "aluminum");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat4").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material("mat3").selection().set(10);
    model.component("comp1").material("mat4").label("\u94dd\u7fc5\u7247");
    model.component("comp1").material("mat4").selection().geom("geom1", 2);
    model.component("comp1").material("mat4").selection().named("geom1_csel1_bnd");

    model.component("comp1").physics("ht").feature("hf1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 14, 15, 16, 17, 19, 20, 21, 22, 24, 25, 26, 27, 29, 30, 31, 32, 33, 34, 35, 36, 37, 39, 40, 41, 42, 44, 45, 46, 47, 49, 50, 51, 52, 63, 64, 65, 66, 88, 89, 91, 92, 113, 114, 115, 116, 117, 119, 120, 121, 122, 124, 125, 126, 127, 128);
    model.component("comp1").physics("ht").create("tc1", "ThermalContact", 2);
    model.component("comp1").physics("ht").feature("tc1").selection().set(51);
    model.component("comp1").physics("ht").feature("tc1").set("hgType", "PPG");
    model.component("comp1").physics("ht").feature("tc1").set("Tn", "20[kPa]");
    model.component("comp1").physics("ht").feature("tc1").set("Hmic", "165[MPa]");
    model.component("comp1").physics("ht").feature("tc1").set("kgap_mat", "userdef");
    model.component("comp1").physics().create("htlsh", "HeatTransferInShellsLM", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/htlsh", true);

    model.component("comp1").physics("htlsh").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("htlsh").create("hfi1", "HeatFluxInterface", 2);
    model.component("comp1").physics("htlsh").feature("hfi1").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("htlsh").feature("hfi1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("htlsh").feature("hfi1").set("h", "htc");
    model.component("comp1").physics("htlsh").feature("hfi1").set("Text", "T0");

    model.component("comp1").material("mat4").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp1").material("mat4").propertyGroup("shell").set("lth", new String[]{"e_fins"});

    model.component("comp1").multiphysics().create("tcls1", "ThermalConnectionLayeredShellEdges", 1);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/physics/htlsh", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/tcls1", true);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("data", "dset2");
    model.result().dataset("lshl1").selection().geom("geom1", 2);
    model.result().dataset("lshl1").selection()
         .set(53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 67, 68, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6\uff0c\u58f3 (htlsh)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("data", "lshl1");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "T2");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "lshl1");
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result().remove("pg3");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6 (tcls1)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature().create("vol2", "Volume");
    model.result("pg2").feature("vol2").set("solutionparams", "parent");
    model.result("pg2").feature("vol2").set("expr", "T2");
    model.result("pg2").feature("vol2").set("titletype", "none");
    model.result("pg2").feature("vol2").set("smooth", "internal");
    model.result("pg2").feature("vol2").set("showsolutionparams", "on");
    model.result("pg2").feature("vol2").set("data", "lshl1");
    model.result("pg2").feature("vol2").set("inheritplot", "vol1");
    model.result("pg2").feature().create("line1", "Line");
    model.result("pg2").feature("line1").set("solutionparams", "parent");
    model.result("pg2").feature("line1").set("expr", "1");
    model.result("pg2").feature("line1").set("titletype", "none");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "fromtheme");
    model.result("pg2").feature("line1").set("smooth", "internal");
    model.result("pg2").feature("line1").set("showsolutionparams", "on");
    model.result("pg2").feature("line1").set("data", "lshl1");
    model.result("pg2").label("\u6e29\u5ea6 (tcls1)");
    model.result("pg2").run();
    model.result("pg2").label("\u6709\u6563\u70ed\u5668\u7684\u6e29\u5ea6");
    model.result("pg2").run();

    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat5").label("Copper");
    model.component("comp1").material("mat5").set("family", "copper");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat5").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat5").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat5").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat5").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat5").propertyGroup("linzRes").addInput("temperature");

    model.component("comp1").physics("ht").create("sls1", "SolidLayeredShell", 2);
    model.component("comp1").physics("ht").feature("sls1").set("LayerType", "Conductive");
    model.component("comp1").physics("ht").feature("sls1").selection().set(3);

    model.component("comp1").material("mat5").selection().geom("geom1", 2);
    model.component("comp1").material("mat5").selection().set(3);
    model.component("comp1").material("mat5").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp1").material("mat5").propertyGroup("shell").set("lth", new String[]{"e_fins"});

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u63a5\u89e6\u6e29\u5ea6 (ht)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").feature().create("slit1", "SurfaceSlit");
    model.result("pg3").feature("slit1").set("solutionparams", "parent");
    model.result("pg3").feature("slit1").set("updescractive", true);
    model.result("pg3").feature("slit1").set("upexpr", "ht.Tu");
    model.result("pg3").feature("slit1").set("updescr", "\u4e0a\u4fa7\u6e29\u5ea6");
    model.result("pg3").feature("slit1").set("downdescractive", true);
    model.result("pg3").feature("slit1").set("downexpr", "ht.Td");
    model.result("pg3").feature("slit1").set("downdescr", "\u4e0b\u4fa7\u6e29\u5ea6");
    model.result("pg3").feature("slit1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("slit1").set("smooth", "internal");
    model.result("pg3").feature("slit1").set("showsolutionparams", "on");
    model.result("pg3").feature("slit1").set("data", "parent");
    model.result("pg3").feature("slit1").feature().create("sel1", "Selection");
    model.result("pg3").feature("slit1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg3").feature("slit1").feature("sel1").selection().set(51);
    model.result("pg3").label("\u63a5\u89e6\u6e29\u5ea6 (ht)");
    model.result("pg3").run();
    model.result("pg3").label("\u4e0e\u6563\u70ed\u5668\u7684\u63a5\u89e6\u6e29\u5ea6");

    return model;
  }

  public static Model run2(Model model) {
    model.result().numerical().create("max1", "MaxVolume");
    model.result().numerical("max1").label("\u6700\u9ad8\u6e29\u5ea6");
    model.result().numerical("max1").set("data", "dset2");
    model.result().numerical("max1").selection().all();
    model.result().numerical("max1").setIndex("descr", "\u6700\u9ad8\u6e29\u5ea6", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u6700\u9ad8\u6e29\u5ea6");
    model.result().numerical("max1").set("table", "tbl1");
    model.result().numerical("max1").setResult();
    model.result("pg3").run();

    model.title("\u53e0\u7247\u5f0f\u6563\u70ed\u5668");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u7535\u5b50\u5143\u4ef6\u4e2d\u53e0\u7247\u5f0f\u6563\u70ed\u5668\u7684\u6563\u70ed\u6548\u679c\u3002\u6563\u70ed\u5668\u7531\u6570\u4e2a\u8584\u94dd\u5706\u76d8\u7ed5\u4e2d\u7a7a\u5706\u67f1\u5806\u53e0\u800c\u6210\uff0c\u8fd9\u4e00\u914d\u7f6e\u6709\u5229\u4e8e\u901a\u8fc7\u73af\u5883\u6e29\u5ea6\u4e0b\u7684\u7a7a\u6c14\u5b9e\u73b0\u94dd\u7fc5\u7247\u4e0a\u8f83\u5927\u9762\u79ef\u7684\u6563\u70ed\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("disk_stack_heat_sink.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
