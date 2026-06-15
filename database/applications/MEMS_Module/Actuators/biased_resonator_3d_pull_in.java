/*
 * biased_resonator_3d_pull_in.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:53 by COMSOL 6.3.0.290. */
public class biased_resonator_3d_pull_in {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").prop("ShapeProperty").set("order_electricpotential", "2m");
    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid");
    model.component("comp1").physics("es").feature("ccns1").selection().all();

    model.component("comp1").multiphysics().create("eme1", "ElectromechanicalForces", 3);
    model.component("comp1").multiphysics("eme1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("eme1").set("Electrostatics_physics", "es");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().set();
    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().set();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/eme1", true);

    model.component("comp1").geom("geom1").insertFile("biased_resonator_3d_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("sel2");

    model.param().set("Vdc", "35[V]");
    model.param().descr("Vdc", "\u76f4\u6d41\u504f\u538b");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection("sel1").label("\u6240\u6709\u57df");
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").set("zmin", -2);
    model.component("comp1").selection("box1").set("zmax", -1);
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").label("\u63a5\u5730\u5e73\u9762");
    model.component("comp1").selection().create("box2", "Box");
    model.component("comp1").selection("box2").set("zmin", -1);
    model.component("comp1").selection("box2").set("zmax", -0.9);
    model.component("comp1").selection("box2").label("\u6c27\u5316\u7269");
    model.component("comp1").selection().create("box3", "Box");
    model.component("comp1").selection("box3").set("zmin", -0.4);
    model.component("comp1").selection("box3").set("zmax", -0.35);
    model.component("comp1").selection("box3").label("\u6c2e\u5316\u7269");
    model.component("comp1").selection().create("box4", "Box");
    model.component("comp1").selection("box4").set("xmin", -0.1);
    model.component("comp1").selection("box4").set("xmax", 0.1);
    model.component("comp1").selection("box4").set("ymin", -4.2);
    model.component("comp1").selection("box4").set("zmin", -0.15);
    model.component("comp1").selection("box4").set("zmax", -0.1);
    model.component("comp1").selection("box4").label("\u7535\u6781");
    model.component("comp1").selection().create("ball1", "Ball");
    model.component("comp1").selection("ball1").set("posz", 1);
    model.component("comp1").selection("ball1").set("r", 0.1);
    model.component("comp1").selection().create("box5", "Box");
    model.component("comp1").selection("box5").set("ymax", 4.8);
    model.component("comp1").selection("box5").set("zmin", -0.35);
    model.component("comp1").selection("box5").set("zmax", 0.05);
    model.component("comp1").selection("box5").set("condition", "inside");
    model.component("comp1").selection().duplicate("box6", "box5");
    model.component("comp1").selection("box6").set("xmin", -15);
    model.component("comp1").selection("box6").set("xmax", 15);
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").set("add", new String[]{"ball1", "box5"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"box6"});
    model.component("comp1").selection("dif1").label("\u8c10\u632f\u5668");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("input", new String[]{"box4", "dif1"});
    model.component("comp1").selection("uni1").label("\u591a\u6676\u7845");
    model.component("comp1").selection().create("dif2", "Difference");
    model.component("comp1").selection("dif2").set("add", new String[]{"sel1"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"box2", "box3", "uni1"});
    model.component("comp1").selection("dif2").label("\u7a7a\u6c14");
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").set("input", new String[]{"dif1"});
    model.component("comp1").selection("adj1").label("\u8c10\u632f\u5668\u8fb9\u754c");
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").set("input", new String[]{"box4"});
    model.component("comp1").selection("adj2").label("\u7535\u6781\u8fb9\u754c");
    model.component("comp1").selection().create("adj3", "Adjacent");
    model.component("comp1").selection("adj3").set("input", new String[]{"box3"});
    model.component("comp1").selection("adj3").label("\u6c2e\u5316\u7269\u8fb9\u754c");
    model.component("comp1").selection().create("adj4", "Adjacent");
    model.component("comp1").selection("adj4").set("input", new String[]{"sel1"});
    model.component("comp1").selection("adj4").label("\u51e0\u4f55\u7ed3\u6784\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection().create("dif3", "Difference");
    model.component("comp1").selection("dif3").set("entitydim", 2);
    model.component("comp1").selection("dif3").set("add", new String[]{"adj1"});
    model.component("comp1").selection("dif3").set("subtract", new String[]{"adj4"});
    model.component("comp1").selection("dif3").label("\u8c10\u632f\u5668\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection().create("dif4", "Difference");
    model.component("comp1").selection("dif4").set("entitydim", 2);
    model.component("comp1").selection("dif4").set("add", new String[]{"adj2"});
    model.component("comp1").selection("dif4").set("subtract", new String[]{"adj4"});
    model.component("comp1").selection("dif4").label("\u7535\u6781\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"adj1", "adj3"});
    model.component("comp1").selection("int1").label("\u56fa\u5b9a\u8fb9\u754c");
    model.component("comp1").selection().create("box7", "Box");
    model.component("comp1").selection("box7").set("entitydim", 2);
    model.component("comp1").selection("box7").set("xmin", -0.1);
    model.component("comp1").selection("box7").set("xmax", 0.1);
    model.component("comp1").selection("box7").set("condition", "inside");
    model.component("comp1").selection("box7").label("\u5bf9\u79f0\u8fb9\u754c");

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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Si3N4 - Silicon nitride");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.3e-6[1/K]", "0", "0", "0", "2.3e-6[1/K]", "0", "0", "0", "2.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"9.7", "0", "0", "0", "9.7", "0", "0", "0", "9.7"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "3100[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"20[W/(m*K)]", "0", "0", "0", "20[W/(m*K)]", "0", "0", "0", "20[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "250e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.23");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").label("SiO2 - Silicon oxide");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.5e-6[1/K]", "0", "0", "0", "0.5e-6[1/K]", "0", "0", "0", "0.5e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "730[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.2", "0", "0", "0", "4.2", "0", "0", "0", "4.2"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "2200[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "70e9[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat1").selection().named("uni1");
    model.component("comp1").material("mat2").selection().named("box3");
    model.component("comp1").material("mat3").selection().named("box2");

    model.component("comp1").physics("solid").selection().named("dif1");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("int1");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("box7");

    model.component("comp1").common("free1").selection().named("dif2");
    model.component("comp1").common("sym1").selection().named("box7");

    model.component("comp1").physics("es").feature("ccns1").selection().set(1, 2, 4, 5, 7, 8, 10, 11, 13, 14);
    model.component("comp1").physics("es").create("term1", "DomainTerminal", 3);
    model.component("comp1").physics("es").feature("term1").selection().named("dif1");
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term1").set("V0", 0);
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().named("box1");
    model.component("comp1").physics("es").create("term2", "DomainTerminal", 3);
    model.component("comp1").physics("es").feature("term2").selection().named("geom1_boxsel4");
    model.component("comp1").physics("es").feature("term2").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term2").set("V0", "Vdc");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_sel2");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").run("swe1");

    model.study("std1").label("\u7a33\u6001");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u4f4d\u79fb (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "SpectrumLight");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "V");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Dipole");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("expr", "es.normE");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg3").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg3").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg3").feature("mslc1").set("colortable", "Prism");
    model.result("pg3").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg3").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg3").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg3").feature("strmsl1").set("titletype", "none");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.02);
    model.result("pg3").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg3").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("inheritcolor", false);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u52a8\u7f51\u683c");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("meshdomain", "volume");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection()
         .set(3, 6, 9, 12, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").set("data", "mir1");
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", "w");
    model.result("pg4").feature("vol1").set("descr", "\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf");
    model.result("pg4").feature("vol1").set("colortabletrans", "reverse");
    model.result("pg4").run();
    model.result("pg4").create("iso1", "Isosurface");
    model.result("pg4").feature("iso1").set("expr", "V");
    model.result("pg4").feature("iso1").set("descr", "\u7535\u52bf");
    model.result("pg4").feature("iso1").set("levelmethod", "levels");
    model.result("pg4").feature("iso1").set("levels", "10 20 30");
    model.result("pg4").feature("iso1").set("colortable", "Traffic");
    model.result("pg4").feature("iso1").set("colorlegend", false);
    model.result("pg4").run();
    model.result("pg4").label("\u504f\u7f6e\u4f4d\u79fb");

    model.title("\u504f\u538b\u8c10\u632f\u5668\u7684\u7a33\u6001\u5206\u6790 - \u4e09\u7ef4");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u4e00\u4e2a\u9759\u7535\u9a71\u52a8\u7684 MEMS \u8c10\u632f\u5668\u3002\u8be5\u88c5\u7f6e\u7531\u5e73\u884c\u677f\u7535\u5bb9\u5668\u4e0a\u65bd\u52a0\u7684\u4ea4\u6d41 + \u76f4\u6d41\u504f\u538b\u9a71\u52a8\u3002\u5176\u4e2d\u8ba1\u7b97\u8c10\u632f\u5668\u5728\u5916\u52a0\u76f4\u6d41\u504f\u538b\u4f5c\u7528\u4e0b\u7684\u53d8\u5f62\u3002");

    model.label("biased_resonator_3d_basic.mph");

    model.result("pg4").run();

    model.param().set("zset", "100[nm]");
    model.param().descr("zset", "\u8bbe\u5b9a\u70b9 z \u5750\u6807");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(253);

    model.component("comp1").physics("es").prop("ShapeProperty").set("order_electricpotential", 2);
    model.component("comp1").physics("es").feature("term2").set("V0", "VdcSP");
    model.component("comp1").physics("es").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("es").feature("ge1").setIndex("name", "VdcSP", 0, 0);
    model.component("comp1").physics("es").feature("ge1").setIndex("equation", "intop1(z)-zset", 0, 0);
    model.component("comp1").physics("es").feature("ge1")
         .setIndex("description", "\u8fbe\u5230 zset \u6240\u9700\u7684\u5916\u52a0\u504f\u538b (V)", 0, 0);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/es", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/eme1", true);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "Vdc", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("pname", "Vdc", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("pname", "zset", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(1.2e-7,2.0e-9,1.4e-7)", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").set("control", "user");
    model.sol("sol2").feature("v1").feature("comp1_spatial_disp").set("scalemethod", "auto");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "auto");
    model.sol("sol2").feature("v1").feature("comp1_V").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_V").set("scaleval", 100);
    model.sol("sol2").feature("v1").feature("comp1_ODE1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_ODE1").set("scaleval", 100);
    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol2").feature("s1").feature("fc1").set("dtech", "hnlin");

    model.study("std2").label("\u5438\u5408");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 11, 0);
    model.result("pg5").label("\u4f4d\u79fb (solid) 1");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", new String[]{"solid.disp"});
    model.result("pg5").feature("vol1").set("threshold", "manual");
    model.result("pg5").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("vol1").set("colortable", "Rainbow");
    model.result("pg5").feature("vol1").set("colortabletrans", "none");
    model.result("pg5").feature("vol1").set("colorscalemode", "linear");
    model.result("pg5").feature("vol1").set("resolution", "custom");
    model.result("pg5").feature("vol1").set("refine", 2);
    model.result("pg5").feature("vol1").set("colortable", "SpectrumLight");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u7535\u52bf (es) 1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("solutionparams", "parent");
    model.result("pg6").feature("mslc1").set("expr", "V");
    model.result("pg6").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg6").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg6").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg6").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg6").feature("mslc1").set("colortable", "Dipole");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg6").feature("mslc1").set("data", "parent");
    model.result("pg6").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg6").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg6").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg6").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg6").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg6").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg6").feature("strmsl1").set("titletype", "none");
    model.result("pg6").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg6").feature("strmsl1").set("udist", 0.02);
    model.result("pg6").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg6").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("inheritcolor", false);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("data", "parent");
    model.result("pg6").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg6").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg6").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg6").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg6").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg6").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u7535\u573a (es) 1");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 11, 0);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").feature().create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("solutionparams", "parent");
    model.result("pg7").feature("mslc1").set("expr", "es.normE");
    model.result("pg7").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg7").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg7").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg7").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg7").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg7").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg7").feature("mslc1").set("colortable", "Prism");
    model.result("pg7").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg7").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("data", "parent");
    model.result("pg7").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg7").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg7").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg7").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg7").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg7").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg7").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg7").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg7").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg7").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg7").feature("strmsl1").set("titletype", "none");
    model.result("pg7").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg7").feature("strmsl1").set("udist", 0.02);
    model.result("pg7").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg7").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg7").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("strmsl1").set("inheritcolor", false);
    model.result("pg7").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg7").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg7").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg7").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg7").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("strmsl1").set("data", "parent");
    model.result("pg7").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg7").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg7").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg7").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg7").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg7").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg7").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg7").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg7").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevel", 11, 0);
    model.result("pg8").label("\u52a8\u7f51\u683c");
    model.result("pg8").create("mesh1", "Mesh");
    model.result("pg8").feature("mesh1").set("meshdomain", "volume");
    model.result("pg8").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg8").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg8").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg8").feature("mesh1").feature("sel1").selection()
         .set(3, 6, 9, 12, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36);
    model.result("pg8").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg8").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg8").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg5").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").set("data", "dset2");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").set("expr", new String[]{"VdcSP"});
    model.result("pg9").feature("glob1")
         .set("descr", new String[]{"\u8fbe\u5230 zset \u6240\u9700\u7684\u5916\u52a0\u504f\u538b (V)"});
    model.result("pg9").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg9").feature("glob1").set("xdata", "expr");
    model.result("pg9").feature("glob1").set("xdataexpr", "zset");
    model.result("pg9").run();
    model.result("pg9").label("\u5438\u5408\u56fe");
    model.result("pg9").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 3, 0);
    model.result("pg5").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"es/ge1"});

    model.title("\u504f\u538b\u8c10\u632f\u5668\u7684\u5438\u5408\u7535\u538b - \u4e09\u7ef4");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u4e00\u4e2a\u9759\u7535\u9a71\u52a8\u7684 MEMS \u8c10\u632f\u5668\u3002\u8be5\u88c5\u7f6e\u7531\u5e73\u884c\u677f\u7535\u5bb9\u5668\u4e0a\u65bd\u52a0\u7684\u4ea4\u6d41 + \u76f4\u6d41\u504f\u538b\u9a71\u52a8\u3002\u5176\u4e2d\u8ba1\u7b97\u8c10\u632f\u5668\u7684\u5438\u5408\u7535\u538b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("biased_resonator_3d_pull_in.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
